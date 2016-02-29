import L from 'leaflet';
import * as Providers from 'leaflet-providers';
import {createStore} from 'reflux';
import Constants from '../constants/Contants.es6';
import {StoreMixins} from '../mixins/StoreMixins.es6';


const initialData=
{
	'OpenStreetMap': L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
		attribution: '&copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>'
	})
	,

	'Esri WorldStreetMap': L.tileLayer.provider('Esri.WorldStreetMap'),

	'Esri WorldTopoMap': L.tileLayer.provider('Esri.WorldTopoMap'),

	'Esri WorldImagery': L.tileLayer.provider('Esri.WorldImagery'),

	'Esri WorldTerrain': L.tileLayer.provider('Esri.WorldTerrain'),

	'Esri WorldPhysical': L.tileLayer.provider('Esri.WorldPhysical'),

	'Esri OceanBasemap': L.tileLayer.provider('Esri.OceanBasemap'),

	'Esri NatGeoWorldMap': L.tileLayer.provider('Esri.NatGeoWorldMap'),

	'Esri WorldGrayCanvas': L.tileLayer.provider('Esri.WorldGrayCanvas')
	
}

const BaseLayers = createStore({

	initialData: initialData,
	mixins: [StoreMixins],

});

export default	BaseLayers