import {PropTypes} from 'react';
import {geoJson, latlng, marker, divIcon, DomEvent} from 'leaflet';
import {MapLayer} from 'react-leaflet';
import React from 'react';
import d3 from 'd3';
import  MarkerClusterGroup from 'leaflet.markercluster'
import { render, unmountComponentAtNode } from 'react-dom';
require('./cluster.scss');
/**
 * @author Sebas
 */

 var myIcon = L.divIcon({ 
        iconSize: new L.Point(30, 30), 
        className: 'cluster-marker-div-icon'
    });

 export default class ClusteredLayer extends MapLayer {


 	componentWillMount() {
 		debugger;
 		
 		const {data,map}=this.props 
 		var geojson = geoJson(data, 
 		{
 			
 			onEachFeature: function (feature, layer) {},

 			style: function (feature) {
				
			},
			 pointToLayer: function (feature, latlng) {
        	 	return L.marker(latlng, {icon: myIcon});
    		},
			onEachFeature: function (feature, layer) {
				var popupText = 'geometry type: ' + feature.geometry.type;
				if (feature.properties.color) {
					popupText += '<br/>color: ' + feature.properties.color;
				}
				layer.bindPopup(popupText);
			}



 		});

 		this.leafletElement = L.markerClusterGroup(
 		{
 			iconCreateFunction: function(cluster) {
 				var childCount = cluster.getChildCount();

 				var c = ' marker-cluster-';
 				if (childCount < 10) {
 					c += 'small';
 				} else if (childCount < 100) {
 					c += 'medium';
 				} else {
 					c += 'large';
 				}

 				return new L.DivIcon({ html: '<div><span>' + childCount + '</span></div>', className: 'marker-cluster' + c, iconSize: new L.Point(50, 50) });
 			},
 			chunkedLoading:true,
 			maxClusterRadius:200,
 			spiderfyOnMaxZoom:10,
 			disableClusteringAtZoom: 17,
 			showCoverageOnHover:false,});
 		this.leafletElement.addLayer(geojson);
 		map.addLayer(this.leafletElement);
 	}

 	render() {
 		return this.renderChildrenWithProps({
 			popupContainer: this.leafletElement,
 		});
 	}

 }
