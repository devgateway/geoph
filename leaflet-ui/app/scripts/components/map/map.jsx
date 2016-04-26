import React from 'react';
import ReactDOM from 'react';

import { connect } from 'react-redux'
import {loadProjects} from '../../actions/map.js'
import SvgLayer from './layers/svg.jsx'
import {latLngBounds,latLng} from 'leaflet'
import * as Constants from '../../constants/constants.js';
import {classProvider} from '../../util/jenksUtil.js'
import {L, Popup, Map, Marker, TileLayer,ZoomControl,MapLayer,ScaleControl,LayerGroup} from 'react-leaflet'; 

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

	getLayers(layers=this.props.map.get('layers')){
		
		return	layers.map((l)=>{
			if (l.get('layers')){
				return this.getLayers(l.get('layers'));
			}else if(l.get('visible')){
				let data=l.get('data')?l.get('data').toJS():null;
				return (<SvgLayer size={1.5} relativeToZoom={true}  valueProperty="projectCount"  classProvider={classProvider} visible={l.get('visible')} key={l.get('id')} data={data}/>)
			} 
		}).reduce((l)=>{
			if (l)
				return l
			
		})
		
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
	
	return {map:state.map};
}

const MapView=connect(stateToProps)(view);


export default MapView;

