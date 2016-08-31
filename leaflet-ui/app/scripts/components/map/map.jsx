import React from 'react';
import ReactDOM from 'react';

import { connect } from 'react-redux'
import {loadProjects, updateBounds} from '../../actions/map.js'

import SvgLayer from './layers/svg.jsx'
import ClusteredLayer from './layers/clusteredLayer.jsx'

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

const view=React.createClass({

	getInitialState() {
		return {};
	},

	handleChangeBounds(e) {		
		this.props.onUpdateBounds(e.target.getBounds());
	},

	getPopUp(id){
		debugger;
		if (id=="projectPopup"){
			return (<ProjectPopup  onClosePopup={this.closePopup}/>)
		}
		if (id="defaultPopup"){
			return (<SimplePopup  onClosePopup={this.closePopup}/>)
		}

		if (id="photoPopup"){
			return <PhotoPopup  onClosePopup={this.closePopup}/>
		}
	},

	getLayer(l){
		
		console.log(l);
		const {data,type,popupId}=l;
		if (type=='clustered'){

			return (<ClusteredLayer data={data}>
				<PhotoPopup  onClosePopup={this.closePopup}/>
				</ClusteredLayer>);
		}else{
			return (<SvgLayer zIndex={l.zIndex}  features={data.features}>
				{this.getPopUp(popupId)}
				</SvgLayer>)
		}
	},

	render(){
		const {map} = this.props;
		const {southWest, northEast} = map.get('bounds').toJS();		
		const bounds = L.latLngBounds(L.latLng(southWest.lat, southWest.lng),L.latLng(northEast.lat,northEast.lng));
		let layers = getVisibles(this.props.map.get('layers')).toJS();
		
		return (
			<div>
			<Map className="map" bounds={bounds}>
			<TileLayer url={this.props.map.get('basemap').get('url')}/>
			{layers.map((l)=>{
				const {data,type}=l;
				return (data && data.features)?this.getLayer(l):null;
			})}

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

