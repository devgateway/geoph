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


const markerSymbol = new SimpleMarkerSymbol({
  color: [226, 119, 40],
  outline: new SimpleLineSymbol({
    color: [255, 255, 255],
    width: 2
  })
});

const MapComponent = React.createClass({

render(){

 return (
 <Map className="map" basemap="streets">
 
  <GraphicsLayer>
    <Graphic geometry={point} symbol={markerSymbol}/>
    <Graphic geometry={point1} symbol={markerSymbol}/>
    <Graphic geometry={point2} symbol={markerSymbol}/>
  </GraphicsLayer>

  <MapView center={[-48, 19]} zoom={3}>
    <BaseMapToggle/>
    <Zoom/>
  </MapView>

</Map>
  )
}

});


export default MapComponent;
