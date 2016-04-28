import React from 'react';
import ReactDOM from 'react';

import { connect } from 'react-redux'
import {loadProjects} from '../../actions/map.js'
import SvgLayer from './layers/svg.jsx'
import {latLngBounds,latLng} from 'leaflet'
import * as Constants from '../../constants/constants.js';
import JenksCssProvider from '../../util/jenksUtil.js'
import {L, Popup, Map, Marker, TileLayer,ZoomControl,MapLayer,ScaleControl,LayerGroup} from 'react-leaflet'; 

require('leaflet/dist/leaflet.css')
require('./map.scss');

var southWest = latLng(4.3245014930192, 115.224609375),
northEast = latLng(23.140359987886118,134.3408203125),
bounds = latLngBounds(southWest, northEast);

const PathInitializer=React.createClass({

	componentWillMount() {
		
		//this.props.map._initPathRoot();
	},

	render(){
		return null;
	}
});


const Layer=React.createClass({
	
	render(){
			let prefix=this.props.layer.get('cssPrefix')
			let css=(this.props.layer.get('settings')?this.props.layer.get('settings').get('css'):'');
			let classes=prefix+' '+css ;
			return (this.props.layer.get('visible')==true)?<SvgLayer  
				map={this.props.map}
				sizeFactor={0.6}  
				minSize={10} 
				maxSize={20}
				zIndex={this.props.layer.get('zIndex')}
				relativeToZoom={true}  
				valueProperty="projectCount"  
				cssProvider={JenksCssProvider}
				classes={classes}  
				thresholds={5} 
				visible={this.props.layer.get('visible')} 
				key={this.props.layer.get('keName')} 
				data={this.props.layer.get('data')?this.props.layer.get('data').toJS():null}/>:null;
	
	}
})


const Layers=React.createClass({

	render(){
	
	var children=this.props.layers.map((l)=>{
			if (l.get('layers')){
				return <Layers map={this.props.map} layers={l.get('layers')}/>
			}else{ 
				return <Layer map={this.props.map} layer={l}/>
			}
		})
			
		return <div>{children}</div>
		}
})



const view = React.createClass({

	getInitialState() {
		return {};
	},

	render(){
		return (
			<Map className="map" zoom={13} bounds={bounds}>
				<TileLayer url={this.props.map.get('basemap').get('url')}/>
				<Layers layers={this.props.map.get('layers')}/>
			</Map>
			)
	}
});


const stateToProps = (state,props) => {
	
	return {map:state.map};
}

const MapView=connect(stateToProps)(view);


export default MapView;

