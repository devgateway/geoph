import React from 'react';
import ReactDOM from 'react';

import { connect } from 'react-redux'
import {loadProjects, updateBounds} from '../../actions/map.js'
import {getList} from '../../actions/indicators'

import SvgLayer from './layers/svg.jsx'
import {latLngBounds,latLng} from 'leaflet'
import * as Constants from '../../constants/constants.js'
import {loadDefaultLayer} from '../../actions/map.js'
import { L , Popup, Map, Marker, TileLayer,ZoomControl,MapLayer,ScaleControl,LayerGroup} from 'react-leaflet'

import ProjectPopup from './popups/projectLayerPopup'
import SimplePopup from './popups/simplePopup'

require('leaflet/dist/leaflet.css')
require('./map.scss');

const Layers=React.createClass({

	getVisibleLayersArray(layers){
		let layerArray = [];
		layers.map((l)=>{
			if (l.get('layers')){
				layerArray = layerArray.concat(this.getVisibleLayersArray(l.get('layers')));
			} else { 
				if (l.get('visible')==true){
					layerArray.push(l.toJS())
				}
			}
		})
		return layerArray
	},

	closePopup(){
		this.props.map.closePopup();
	},

	render(){
		let layers = this.getVisibleLayersArray(this.props.layers);
		return (
			<div>
				<SvgLayer  
					map={this.props.map}
					layers={layers}
					fundingType={this.props.fundingType}>
					<ProjectPopup id="projectPopup" onClosePopup={this.closePopup}/>
					<SimplePopup id="defaultPopup" onClosePopup={this.closePopup}/>
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
        this.props.onUpdateBounds(e.target.getBounds());
    },

	render(){
		console.log('render component map.jsx');
		const {southWest, northEast} = this.props.map.get('bounds').toJS();
		const bounds = latLngBounds(latLng(southWest[0], southWest[1]),latLng(northEast[0],northEast[1]));
		return (
			<Map className="map" bounds={bounds} onMoveEnd={this.handleChangeBounds}>
				<TileLayer url={this.props.map.get('basemap').get('url')}/>
				<Layers layers={this.props.map.get('layers')} fundingType={this.props.fundingType}/>			
			</Map>
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
		map: state.map,
		fundingType: state.settings.fundingType
	};
}

const MapView=connect(stateToProps,mapDispatchToProps)(view);
export default MapView;

