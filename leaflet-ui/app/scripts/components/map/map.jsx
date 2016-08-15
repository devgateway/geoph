import React from 'react';
import ReactDOM from 'react';

import { connect } from 'react-redux'
import {loadProjects, updateBounds} from '../../actions/map.js'

import SvgLayer from './layers/svg.jsx'
import * as Constants from '../../constants/constants.js'
import {loadDefaultLayer} from '../../actions/map.js'
import {Popup, Map, Marker, TileLayer,ZoomControl,MapLayer,ScaleControl,LayerGroup} from 'react-leaflet'
import L from 'leaflet';
import ProjectPopup from './popups/projectLayerPopup'
import SimplePopup from './popups/simplePopup'
import PhotoPopup from './popups/photoPopup'
import {getVisibles} from '../../util/layersUtil.js';
import Legends from './legends/legends'

require('leaflet/dist/leaflet.css')
require('./map.scss');

const Layers=React.createClass({

	closePopup(){
		this.props.map.closePopup();
	},

	render(){
		let layers = getVisibles(this.props.layers).toJS();
		return (
			<div>
				<SvgLayer  
					map={this.props.map}
					layers={layers}
					fundingType={this.props.fundingType}>
					<ProjectPopup id="projectPopup" onClosePopup={this.closePopup}/>
					<SimplePopup id="defaultPopup" onClosePopup={this.closePopup}/>
					<PhotoPopup id="photoPopup" onClosePopup={this.closePopup}/>
				</SvgLayer>
			</div>
		)
	}
})

const view=React.createClass({

	getInitialState() {
		return {};
	},

	handleChangeBounds(e) {		
		debugger;
        this.props.onUpdateBounds(e.target.getBounds());
    },

	render(){

		console.log('render component map.jsx');
		const {map} = this.props;
		const {southWest, northEast} = map.get('bounds').toJS();
		
		const bounds = L.latLngBounds(L.latLng(southWest.lat, southWest.lng),L.latLng(northEast.lat,northEast.lng));
		if (!bounds){
			debugger;
		}
		console.log(southWest)
		console.log(bounds);
		return (
			<div>
				<Map className="map" bounds={bounds} onMoveEnd={this.handleChangeBounds}>
					<TileLayer url={this.props.map.get('basemap').get('url')}/>
					<Layers layers={this.props.map.get('layers')} fundingType={this.props.fundingType}/>			
				</Map>
				<Legends layers={this.props.map.get('layers')} />
			</div>
		);
	}
})


const mapDispatchToProps=(dispatch,ownProps)=>{
  return {
    onUpdateBounds: (newBounds) => {
      dispatch(updateBounds(newBounds));
    }
  }
}

const stateToProps = (state,props) => {	
	return {
		map:state.map,
		fundingType: state.settings.fundingType
	};
}

const MapView=connect(stateToProps,mapDispatchToProps)(view);
export default MapView;

