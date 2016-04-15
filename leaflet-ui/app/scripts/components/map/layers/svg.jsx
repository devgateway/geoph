import {PropTypes} from 'react';
import {geoJson, latlng, marker, divIcon} from 'leaflet';
import {MapLayer} from 'react-leaflet';
import React from 'react';
import d3 from 'd3';
import { render, unmountComponentAtNode } from 'react-dom';

/**
 * @author Sebas
 */
 export default class SvgLayer extends MapLayer {

   static propTypes = {
    higthligthStyleProvider: React.PropTypes.func,
  };


  constructor() {
    super();
  }

  componentWillMount() {
    super.componentWillMount();
    this.create()
  }


  componentDidUpdate(nextProps, nextState) {
    const {data, ...props} = this.props;
    this.update();

  }


  create(){
    this.leafletElement = geoJson();
    this.props.map._initPathRoot();
    this.svg= d3.select(this.props.map._container).select("svg"),
    this.g = this.svg.append("g").attr("class", "leaflet-zoom-hide");
    this.props.map.on('moveend', this.mapmove.bind(this));
    this.mapmove();
  }

  update(){
    //TODO:maybe a more efficent way can be implemented
    //clean
    this.mapmove();
  }

  renderPaths(data){

    var  map=this.props.map;

    // Use Leaflet to implement a D3 geometric transformation.
    function projectPoint(x, y) {
      var point = map.latLngToLayerPoint(new L.LatLng(y, x));
      this.stream.point(point.x, point.y);
    }

    var transform = d3.geo.transform({ point: projectPoint });
    var path = d3.geo.path().projection(transform);
    path.pointRadius(this.props.map.getZoom()*1.5);

    var points = this.g.selectAll("path").data(data, function(d) {return d.id;});

    points.enter().append("path");
    points.exit().remove();

    points.attr("d", path)
    .on("click",this.onClick.bind(this))
    .on("mouseover",this.onMouseover.bind(this))
    .on("mouseout",this.onMouseout.bind(this));

    if (this.props.styleProvider){
      this.style(points,this.props.styleProvider);
    }
  }

  onClick(properties){
    L.DomEvent._getEvent().stopPropagation();
    this.renderPopupContent(properties);
  }

  onMouseover(e){
    let target=d3.select(d3.event.target);
    if (this.props.higthligthStyleProvider)
      this.style(target,this.props.higthligthStyleProvider) ;
    target.node().parentNode.appendChild(target.node())
  }



  onMouseout(){
    let target=d3.select(d3.event.target);
    if (this.props.styleProvider)
      this.style(target,this.props.styleProvider) ;
  }

  style(points,styleProvider){
    points.style("fill", (d)=>{
     return styleProvider(d).fill();
   })
    .style("fill-opacity", (d)=>{
      return styleProvider(d).fillOpacity();

    })
    .style("stroke", (d)=>{
      return styleProvider(d).stroke();

    })
    .style("stroke-opacity", (d)=>{
      return styleProvider(d).strokeOpacity();
    });
    return points;
  }

  mapmove(e) {

    if (this.props.data && this.props.data.features){
      this.renderPaths(this.props.data.features);
    }else{
      console.log('Dataset is empty');
    }
  }



  renderPopupContent(feature) {
    let popup = L.popup()
    .setLatLng(L.latLng(feature.geometry.coordinates[1],feature.geometry.coordinates[0]))
    .openOn(this.props.map);


    if (this.props.children) {
      render(React.cloneElement(React.Children.only(this.props.children), feature.properties) ,popup._contentNode);
      popup._updateLayout();
      popup._updatePosition();
      popup._adjustPan();
    } 
  }



  render() {
    return this.renderChildrenWithProps({
      popupContainer: this.leafletElement,
    });
  }

}

