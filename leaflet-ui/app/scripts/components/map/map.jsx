import React from 'react';
import ReactDOM from 'react';

import { connect } from 'react-redux'
import {loadProjects} from '../../actions/map.js'
import SvgLayer from './layers/svg.jsx'
import {latLngBounds,latLng} from 'leaflet'
import * as Constants from '../../constants/constants.js';

import {Popup, Map, Marker, TileLayer,ZoomControl,MapLayer,ScaleControl,LayerGroup} from 'react-leaflet'; 
import L from 'leaflet';

require('leaflet/dist/leaflet.css')
require('./map.scss');

var southWest = latLng(4.3245014930192, 115.224609375),
northEast = latLng(23.140359987886118,134.3408203125),
bounds = latLngBounds(southWest, northEast);

const styleProvider=(d)=>{
  return {
    fill:()=>{return '#ff9966'},
    stroke:()=>{return '#993300'},
    fillOpacity:()=>{0.5},
    strokeOpacity:()=>{0.7},
  }
}

const higthligthStyleProvider=()=>{
 return {
  fill:()=>{return '#FF0000'},
  stroke:()=>{return '#993300'},
  fillOpacity:()=>{0.5},
  strokeOpacity:()=>{0.7},
}
}


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
			}else{
				let data=l.get('data')?l.get('data').toJS():null;
				return (<SvgLayer key={l.get('id')} data={data} styleProvider={styleProvider} higthligthStyleProvider={higthligthStyleProvider}/>)
			} 
		}).reduce((l)=>{
			if (l)
				return l
			
		})
		
	},

	render(){
		return (
			<Map className="map" zoom={13} bounds={bounds}>
				<TileLayer url={this.props.map.get('basemap').get('url')}/>
				{this.getLayers()}
			</Map>
		)
	}
});


const stateToProps = (state,props) => {
	
	return {map: state.map};
}

const MapView=connect(stateToProps)(view);


export default MapView;

