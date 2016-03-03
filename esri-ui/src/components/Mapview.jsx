import React from 'react';
import ReactDOM from 'react/react-dom';
import Map from 'app/components/esri/map';
import SceneView from 'app/components/esri/sceneView';
import MapView from 'app/components/esri/mapview';

import Zoom from 'app/components/esri/widgets/zoom';
import BaseMapToggle from 'app/components/esri/widgets/basemaptoggle';

const MapComponent = React.createClass({


  getInitialState(){
  return {view:'3d'}
},


toggleView(){
  if (this.state.view=='3d'){
    this.setState({view:'2d'})
  }else{
    this.setState({view:'3d'})
  }
},

render() {
	if (this.state.view=='3d'){
  return (
    <div>
    <div>{this.state.view} view  <a href="#map" onClick={this.toggleView}> toggle view</a></div>
    <Map className="map"  basemap="streets">
      <SceneView center={[-112, 38]} zoom={1}></SceneView>
    </Map>
    </div>
    )}else{

   return (
    <div>
    <div>{this.state.view} view  <a href="#map" onClick={this.toggleView}> toggle view</a></div>
    <Map className="map"  basemap="streets">
  	<MapView center={[-112, 38]} zoom={7}></MapView>
    </Map>
    </div>
    )
   }
}
});

export default MapComponent;
