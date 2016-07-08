import {PropTypes} from 'react';
import {geoJson, latlng, marker, divIcon, DomEvent} from 'leaflet';
//import L from 'leaflet';
import {MapLayer} from 'react-leaflet';
import React from 'react';
import d3 from 'd3';
import { render, unmountComponentAtNode } from 'react-dom';

/**
 * @author Sebas
 */
 export default class D3Layer extends MapLayer {



  constructor() {
    super();
  }

  componentDidUpdate(nextProps, nextState) {
    const {data, ...props} = this.props;
    this.mapmove();
  }

  componentWillUnmount() {
    this.svg.remove();  
    //this.svg=null;  
  }


  componentWillMount() {

    super.componentWillMount();
    this.leafletElement = geoJson();
    
    this.svg = d3.select(this.props.map.getPanes().overlayPane).append("svg"); 
    this.svg.style("z-index",this.props.zIndex);
    this.g = this.svg.append("g").attr("class", "leaflet-zoom-hide");
    this.props.map.on('moveend', this.mapmove.bind(this));
    this.mapmove();//trigger first update
  }

  
  getValues(features){
    const valprop=this.props.valueProperty;
    return features.map(function(f) { return +f.properties[valprop]});
  }



  renderPaths(data){
    
   const {features}=data;
   const {map,size,border,type}=this.props;
   
    // Use Leaflet to implement a D3 geometric transformation.
    function projectPoint(x, y) {
      var point = map.latLngToLayerPoint(new L.LatLng(y, x));
      this.stream.point(point.x, point.y);
    }
    const transform = d3.geo.transform({ point: projectPoint});
    const path = d3.geo.path().projection(transform);
    this.setSvgSize(path,data); //set svg area 

    
    path.pointRadius((d)=>{
      return (size/2 * map.getZoom()) ;
    });
    
    var shapes = this.g.selectAll("path").data((type=="points")? this.filter(features):features);
    shapes.enter().append("path");
    shapes.exit().remove();
    shapes.attr("class", function(f) {return this.getClass(f);}.bind(this));
    shapes.attr("stroke-width",border || 0);
    shapes.attr("d", (feature)=>{ return path(feature)});
    shapes.on("click",this.onClick.bind(this)); 


  }


  setSvgSize(path,data){
    debugger

    const {map,size,border}=this.props;
    let markersize=0; //in case of markers we should calculate the size that the markers will takes frm the center to the radio outside of the bounds 
    let radio=0;
    if (size){
      markersize=Math.ceil(size * map.getZoom()) + (border || 0); 
      radio=Math.ceil(markersize/2);         
    }

    var bounds = path.bounds(data), //get path area
    topLeft = bounds[0],
    bottomRight = bounds[1];
    
    var width=(bottomRight[0] - topLeft[0]) + markersize; //add 1 marker size to cover the full size of the marker located in the borders;
    var height=(bottomRight[1] - topLeft[1]) + markersize; //add 1 marker size to cover the full size of the marker located in the borders;
    var left=topLeft[0]-radio; //move  left positon half marker size to make room for makers on borders  
    var top=topLeft[1]-radio //move  top  positon half marker size to make  room for makers on borders

    var translateX=-(left) ; 
    var translateY=-(top)   ;
    //set SVG size and position
    this.svg.attr("width",width+"px").attr("height",height+"px" ).style("left",left).style("top", top);
    //
    this.g.attr("transform", "translate(" + translateX + "," + translateY+ ")");
  }

  filter(data){
    var bounds=this.props.map.getBounds();
    const filtered = data.filter((f)=>f.geometry?bounds.contains(L.geoJson(f).getBounds()):false).sort((f)=>{f.properties[this.props.valueProperty]})
    console.log('Removed =>'+(data.length - filtered.length));
    return filtered;
  }

  onClick(properties){
    d3.event.stopPropagation();
    this.renderPopupContent(properties);
  }

  mapmove(e) {
    if (this.props.data && this.props.data.features){
      this.values=this.getValues(this.props.data.features);//isolate features values 
      this.renderPaths(this.props.data);
    } else {
      console.log('Dataset is empty');
    }
  }

  renderPopupContent(feature) {
    
    if (!feature){
      return null;
    }
    let popup = L.popup({maxWidth:"400", minWidth:"400", maxHeight:"280"})
    .setLatLng(L.latLng(feature.geometry.coordinates[1],feature.geometry.coordinates[0]))
    .openOn(this.props.map);
    if (this.props.children) {

      render(React.cloneElement(React.Children.only(this.props.children), {feature, store:this.context.store}), popup._contentNode);
      popup._updateLayout();
      popup._updatePosition()
      popup._adjustPan();
    } 
  }

  getClass(d){    
    if (this.props.cssProvider){
      if (!this.cssProvider)
        this.cssProvider=new this.props.cssProvider(this.values,this.props.thresholds);
      var className=this.props.classes+this.cssProvider.getCssClass(d.properties[this.props.valueProperty]);
      return className;  
    }
  }

  render() {
    return this.renderChildrenWithProps({
      popupContainer: this.leafletElement,
    });
  }
}

const  storeShape=PropTypes.shape({
  subscribe: PropTypes.func.isRequired,
  dispatch: PropTypes.func.isRequired,
  getState: PropTypes.func.isRequired
})

D3Layer.contextTypes = {
  store: storeShape
}

D3Layer.propTypes = {
  store: storeShape
}
