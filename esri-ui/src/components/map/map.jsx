import React from 'react';
import ReactDOM from 'react/react-dom';
import Map from 'app/components/esri/map';

import SceneView from 'app/components/esri/sceneView';
import MapView from 'app/components/esri/mapview';
import GraphicsLayer from 'app/components/esri/layers/graphicsLayer';
import GeoJsonLayer from 'app/components/esri-dynamic/layers/geoJsonLayer';

import Graphic from 'app/components/esri/graphic';
import Extent from 'app/components/esri/geometry/extend'


const MapComponent = React.createClass({

  

  render(){
   return (
     <Map className="map" basemap="streets" >
     <GeoJsonLayer data={this.props.layers.projects.data} name={this.props.layers.projects.name}></GeoJsonLayer>
     <MapView>
        <Extent  xmin= {12925933.579460911} ymin= {278072.4096790361} xmax= {14706610.590391904} ymax= {2291117.986596903} spatialReference={102100}/>
     </MapView>
     </Map>
     )
 }

});


export default MapComponent;
