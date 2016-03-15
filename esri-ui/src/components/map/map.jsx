import React from 'react';
import ReactDOM from 'react/react-dom';
import Map from 'app/components/esri/map';
import SceneView from 'app/components/esri/sceneView';
import MapView from 'app/components/esri/mapview';

import Zoom from 'app/components/esri/widgets/zoom';
import BaseMapToggle from 'app/components/esri/widgets/basemaptoggle';
import GraphicsLayer from 'app/components/esri/layers/graphicsLayer';
import Graphic from 'app/components/esri/graphic';
import Extent from 'app/components/esri/geometry/extend'

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

onLoadProjects(){
  debugger;
  this.props.onLoadProjects(1)
},

componentDidMount() {
  debugger;
    this.onLoadProjects();  
},


render(){
 return (
       <Map className="map" basemap="streets">
        <GraphicsLayer>
          <Graphic geometry={point} symbol={markerSymbol}/>
          <Graphic geometry={point1} symbol={markerSymbol}/>
          <Graphic geometry={point2} symbol={markerSymbol}/>
        </GraphicsLayer>
        <MapView>
          <Extent  xmin= {12925933.579460911} ymin= {278072.4096790361} xmax= {14706610.590391904} ymax= {2291117.986596903} spatialReference={102100}/>
        </MapView>
      </Map>
  )
}

});


export default MapComponent;
