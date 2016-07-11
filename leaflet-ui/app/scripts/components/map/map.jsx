import React from 'react';
import ReactDOM from 'react';

import { connect } from 'react-redux'
import {loadProjects} from '../../actions/map.js'
import {getList} from '../../actions/indicators'

import SvgLayer from './layers/svg.jsx'
import {latLngBounds,latLng} from 'leaflet'
import * as Constants from '../../constants/constants.js'
import {loadDefaultLayer} from '../../actions/map.js'
import { L , Popup, Map, Marker, TileLayer,ZoomControl,MapLayer,ScaleControl,LayerGroup} from 'react-leaflet'

import ProjectPopup from './popups/projectLayerPopup'
import Test from '../controls/settings'

require('leaflet/dist/leaflet.css')
require('./map.scss');

var southWest = latLng(4.3245014930192, 115.224609375),
northEast = latLng(23.140359987886118,134.3408203125),
bounds = latLngBounds(southWest, northEast);


const Layer=React.createClass({
	closePopup(){
		this.props.map.closePopup();
	},

	getSvgLayer(){
		const {layer}=this.props;
			let prefix=layer.get('cssPrefix')
			let css=layer.getIn(['settings','css']);
			let classes=prefix+' '+css ;
		const layerProps={classes,...layer.toJS()}

		return (	<SvgLayer  
			map={this.props.map}
			{...layerProps}
			data={layer.get('data')?layer.get('data').toJS():null}>
			<ProjectPopup onClosePopup={this.closePopup}/>
			</SvgLayer>)
	},

	render(){
		const {layer}=this.props;
		return (layer.get('visible')==true)?this.getSvgLayer():null;

	}
})


const Layers=React.createClass({

	render(){

		var children=this.props.layers.map((l)=>{

			if (l.get('layers')){
				return <Layers key={l.get('id')} map={this.props.map} layers={l.get('layers')}/>
			} else { 
				return <Layer key={l.get('id')} map={this.props.map} layer={l}/>
			}
		})

		return <div>{children}</div>
	}
})



const view = React.createClass({

	getInitialState() {
		return {};
	},

	componentWillMount(){
		this.props.onLoad();
	},

	render(){
		return (
			<Map className="map" zoom={13} bounds={bounds}>
				<TileLayer url={this.props.map.get('basemap').get('url')}/>
			
				<Layers layers={this.props.map.get('layers')} charts={this.props.charts} fundingType={this.props.fundingType}/>
			
			</Map>
			)
	}
});


const mapDispatchToProps=(dispatch,ownProps)=>{
	return {
		onLoad:()=>{
			dispatch(getList());
			dispatch(loadDefaultLayer());
		}
	}
}


const stateToProps = (state,props) => {	
	return {
		map:state.map
	};
}

const MapView=connect(stateToProps,mapDispatchToProps)(view);


export default MapView;

