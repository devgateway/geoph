import React from 'react';
import ReactDOM from 'react/react-dom';
import Map from 'app/components/esri/map';
import SceneView from 'app/components/esri/sceneView';
import MapView from 'app/components/esri/mapview';

import Zoom from 'app/components/esri/widgets/zoom';
import BaseMapToggle from 'app/components/esri/widgets/basemaptoggle';
import GraphicsLayer from 'app/components/esri/layers/graphicsLayer';
import Graphic from 'app/components/esri/graphic';

import Point from "esri/geometry/Point";
import SimpleMarkerSymbol from "esri/symbols/SimpleMarkerSymbol";
import SimpleLineSymbol from "esri/symbols/SimpleLineSymbol";


const point = new Point({
  longitude: -49.97,
  latitude: 41.73
});

const point1 = new Point({
  longitude: -48.97,
  latitude: 19.73
});


const point2 = new Point({
  longitude: -39.97,
  latitude: 45.73
});

const point3 = new Point({
  longitude: -38.97,
  latitude: 45.73
});




const markerSymbol = new SimpleMarkerSymbol({
  color: [226, 119, 40],
  outline: new SimpleLineSymbol({
    color: [255, 255, 255],
    width: 2
  })
});

const MapComponent = React.createClass({


 getInitialState(){
  return {mode:'3d'};
},

toggle(){
 if (this.state.mode=='3d'){
  this.setState({mode:'2d'})
}else{
  this.setState({mode:'3d'})
}
},

render(){
  if (this.state.mode=='3d'){
   return this.render3d();
 }else{
  return this.render2d();
}
},


render3d() {

 return (
  <div>
  <a href="javascript:void()" onClick={this.toggle}>Toggle view</a>
  <div id="map3d">
      <Map className="map"  basemap="streets">
        <GraphicsLayer>
          <Graphic geometry={point} symbol={markerSymbol}/>
          <Graphic geometry={point1} symbol={markerSymbol}/>
          <Graphic geometry={point2} symbol={markerSymbol}/>
          <Graphic geometry={point3} symbol={markerSymbol}/>
        </GraphicsLayer>
        <SceneView center={[-48, 19]} zoom={3}></SceneView>
     </Map>
  </div>
  </div>
  )
},

render2d() {

 return (
  <div id="map2d">
  <a href="javascript:void()" onClick={this.toggle}>Toggle view</a>
  <Map className="map"  basemap="streets">
  <GraphicsLayer>
    <Graphic geometry={point} symbol={markerSymbol}/>
    <Graphic geometry={point1} symbol={markerSymbol}/>
    <Graphic geometry={point2} symbol={markerSymbol}/>
  </GraphicsLayer>

  <MapView center={[-48, 19]} zoom={3}></MapView>

  </Map>
  </div>
  )
}

});

export default MapComponent;
