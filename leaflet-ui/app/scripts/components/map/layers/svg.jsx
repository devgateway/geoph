import {PropTypes} from 'react';
import {geoJson, latlng, marker} from 'leaflet';
import {MapLayer} from 'react-leaflet';
import React from 'react';
import d3 from 'd3';
import { render, unmountComponentAtNode } from 'react-dom';

/**
 * @author Sebas
 */
//Leaflet deals with event listeners by reference, so if you want to add a listener and then remove it, define it as a function:
function clickListener(evt){
  this.mapClick(evt)
}

function mapUpdateListener(evt){
  this.mapUpdate(evt)
}


export default class D3Layer extends MapLayer {

  constructor() {
    super();
  }

  componentDidUpdate(nextProps, nextState) {
    const {data, ...props} = this.props;
    
    this.mapUpdate();
  }

  componentWillUnmount() {
    super.componentWillUnmount();
    const {map}=this.props;
    
    map.removeEventListener("click",clickListener,this);
    map.off("moveend", mapUpdateListener,this);

    this.svg.remove();
  }


  mapClick(evt){
    if(this.props.id==evt.originalEvent.layer_id){
      this.renderPopupContent(Object.assign({}, evt.originalEvent.features, {latlng: evt.latlng}));
    }
  }
  
  componentWillMount() {
    //generate unique id 
    super.componentWillMount();
    const {map}=this.props;
    this.leafletElement = geoJson();
    
    this.svg = d3.select(this.props.map.getPanes().overlayPane).append("svg"); 
    this.svg.style("z-index",this.props.zIndex);
    this.g = this.svg.append("g").attr("class", "leaflet-zoom-hide");
    map.addEventListener('click',clickListener,this)
    map.on('moveend', mapUpdateListener,this);
    //map.on('click', this.mapClick.bind(this));
    this.mapUpdate();//trigger first update
  }

  filter(features){
    var bounds = this.props.map.getBounds();
    const filtered = features.filter((f)=>{
       let retval=false;
      try{
         if(f.geometry && !f.geometry.coordinates.length ==0){
            retval=bounds.intersects(L.geoJson(f).getBounds());
          }else{
            console.log('feature without geometry or coordinates '+f.properties.name);
          }
      }catch(e){
         console.log("Error on svg layer please check filter method ")
      }
      return retval
    });
    return filtered;

  }

  setSvgSize(path, data, size, border){
   const {map}=this.props;
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

  onClick(properties){
    
    d3.event.features=properties; 
    d3.event.layer_id=this.props.id;  
  }

  mapUpdate(e) {
    this.renderLayersPaths(this.props.features);
  }

  renderLayersPaths(features){
    const {map,showLabels}=this.props;
    const size=20, border=10;

    // Use Leaflet to implement a D3 geometric transformation.
    function projectPoint(x, y) {
      var point = map.latLngToLayerPoint(new L.LatLng(y, x));
      this.stream.point(point.x, point.y);
    }
    const transform = d3.geo.transform({ point: projectPoint});
    const path = d3.geo.path().projection(transform);
    
    features=this.filter(features);

    if (features.length!=0){
      this.setSvgSize(path, {type: "FeatureCollection", features}, size, border); //set svg area 
    }
    path.pointRadius((d)=>{
      return (d.properties.size/2 * map.getZoom()) ;
    });
    
    var shapes = this.g.selectAll("path").data(features);
    shapes.enter().append("path");
    shapes.exit().remove();
    shapes.attr("class", function(f) {return f.properties.className;}.bind(this));
    shapes.attr("stroke-width", function(f) {return f.properties.border || 0;}.bind(this));
    shapes.attr("d", (feature)=>{ return path(feature)});

    shapes.attr("id", (feature)=>{ return "path_"+feature.properties.id});
    shapes.on("click",this.onClick.bind(this)); 

      const element=this.g;
      element.selectAll(".label").remove();  
 
    if(this.props.showLabels==true){
           features.forEach((f)=>{
        const {id}=f.properties;
        element.insert("text","#path_"+id+" +*") //add label text before path in order to get the rigth order 
        .text(function(d){
          return f.properties.label;
        })
        .attr("x", function(d){
         return path.centroid(f)[0];
       })
        .attr("y", function(d){
          return  path.centroid(f)[1];
        })
        .attr("dy","3px")
        .attr("class", "label "+f.properties.className+"")
        .attr("text-anchor","middle");
      });
    }
 }


  renderPopupContent(feature) {
    if (!feature || !feature.geometry){
      return null;
    }
    let latLong;
    if (feature.geometry.type=="MultiPolygon"){
      latLong = feature.latlng;
    } else {
      latLong = L.latLng(feature.geometry.coordinates[1],feature.geometry.coordinates[0])
    }
    let popup = L.popup({maxWidth:"400", maxHeight:"330"})
    .setLatLng(latLong)
    .openOn(this.props.map);
    if (this.props.children) {
      render(React.cloneElement(React.Children.only(this.props.children), {feature, store:this.context.store}), popup._contentNode);
      popup._updateLayout();
      popup._updatePosition()
      popup._adjustPan();
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
