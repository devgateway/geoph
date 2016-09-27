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


 	componentDidUpdate(nextProps, nextState) {
 		const {data,map}=this.props;
 		map.removeLayer(this.leafletElement);
     this.createLayer();
   }

   componentWillMount() {
    this.createLayer();
  }

  createLayer() {
    const {data,map}=this.props;
    var geojson = geoJson(data, 
    {

      onEachFeature: function (feature, layer) {
       layer.on('click', function (e) {
        this.renderPopupContent(feature);
      }.bind(this));
     }.bind(this),

     style: function (feature) {

     },

     pointToLayer: function (feature, latlng) {
       return L.marker(latlng, {icon: myIcon});
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
    maxClusterRadius:20,
    spiderfyOnMaxZoom:true,
    disableClusteringAtZoom: 19,
    showCoverageOnHover:false,});
 		map._layersMaxZoom = 18; //workaround for avoid error en restore (ask @sebas)
 		this.leafletElement.addLayer(geojson);
 		map.addLayer(this.leafletElement);
 	}


 	renderPopupContent(feature) {
 		if (!feature || !feature.geometry){
 			return null;
 		}
 		const latLong = L.latLng(feature.geometry.coordinates[1],feature.geometry.coordinates[0])
 		
 		let popup = L.popup({maxWidth:"500",maxHeight:"400"})
 		.setLatLng(latLong)
 		.openOn(this.props.map);
 		if (this.props.children) {
 			render(React.cloneElement(React.Children.only(this.props.children), {feature, store:this.context.store}), popup._contentNode);
 			popup._updateLayout();
 			popup._updatePosition()
 			popup._adjustPan();
 		} 
 	}


 	render() {
 		return this.renderChildrenWithProps({
 			popupContainer: this.leafletElement,
 		});
 	}

 }


 const  storeShape=PropTypes.shape({
  subscribe: PropTypes.func.isRequired,
  dispatch: PropTypes.func.isRequired,
  getState: PropTypes.func.isRequired
})

 ClusteredLayer.contextTypes = {
  store: storeShape
}

ClusteredLayer.propTypes = {
  store: storeShape
}
