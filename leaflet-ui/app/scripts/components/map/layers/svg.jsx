import {PropTypes} from 'react';
import {geoJson, latlng, marker, divIcon} from 'leaflet';
import {MapLayer} from 'react-leaflet';
import React from 'react';
import d3 from 'd3';
import { render, unmountComponentAtNode } from 'react-dom';
import geostats from  '../../../util/geostats';

/**
 * @author Sebas
 */
 export default class D3Layer extends MapLayer {

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


  getValues(features){
    const valprop=this.props.valueProperty;
    return features.map(function(f) { return +f.properties[valprop]});
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
    const size=this.props.size*(this.props.relativeToZoom?this.props.map.getZoom():1);
    console.log(size);
    path.pointRadius((f)=>size);


    var points = this.g.selectAll("path").data(this.filter(data));
    
    points.enter().append("path");
    points.exit().remove();

    points.attr("d", path)
    .on("click",this.onClick.bind(this));

    points.attr("class", function(d) {
      return this.getClass(d);
    }.bind(this));

  }

  filter(data){
    console.log('Total points=> '+data.length)
    var bounds=this.props.map.getBounds();
    const filtered = data.filter((f)=>bounds.contains(L.geoJson(f).getBounds()))
    console.log('Removed =>'+(data.length - filtered.length));
    return filtered;

  }


  onClick(properties){
    L.DomEvent._getEvent().stopPropagation();
    this.renderPopupContent(properties);
  }


  mapmove(e) {
    if (this.props.data && this.props.data.features){
      this.values=this.getValues(this.props.data.features);//isolate features values 
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


  getClass(d){
    return this.props.classProvider? this.props.classProvider(d.properties[this.props.valueProperty],this.values):"q0-9";
  }



  render() {
    return this.renderChildrenWithProps({
      popupContainer: this.leafletElement,
    });
  }

}

