import React from 'react';
import ReactDOM from 'react';

import { connect } from 'react-redux'
import {loadProjects} from '../../actions/map.js'
import SvgLayer from './layers/svg.jsx'
import {latLngBounds,latLng} from 'leaflet'
import * as Constants from '../../constants/constants.js';

import {L, Popup, Map, Marker, TileLayer,ZoomControl,MapLayer,ScaleControl} from 'react-leaflet'; 

require('leaflet/dist/leaflet.css')
require('./map.scss');

var southWest = latLng(4.3245014930192, 115.224609375),
    northEast = latLng(23.140359987886118,134.3408203125),
    bounds = latLngBounds(southWest, northEast);


const view = React.createClass({

	getInitialState() {
		return {};
	},

	componentWillUpdate(nextProps, nextState) {
		
	},


	getLayers(){
		if (this.props.layers){
			this.props.layers.map((l)=>{
				return (<div/>)
			})
		}
	},

	render(){
		
		return (
			<Map className="map" zoom={13} bounds={bounds}>
			   <TileLayer url='http://{s}.tile.osm.org/{z}/{x}/{y}.png' attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'/>
				{this.getLayers()}
			</Map>
			)
	}
});


const stateToProps = (state,props) => {
	debugger;
	return state.map;
}

const MapView=connect(stateToProps)(view);


export default MapView;

