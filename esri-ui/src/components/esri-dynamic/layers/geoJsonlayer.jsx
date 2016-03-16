import React from 'react';
import ReactDOM from 'react/react-dom';
import EsriGraphicsLayer from 'esri/layers/GraphicsLayer';
import Layer from 'app/components/esri/layers/Layer';
import Graphic from "esri/Graphic";
import domReady from "dojo/domReady!";

class GeoJsonLayer extends Layer{
	componentWillMount() {
		this.element = new EsriGraphicsLayer();
		this.props.map.add(this.element)
	}


	componentWillUpdate(nextProps, nextState) {

		this.loadGeoJson(nextProps.data); 
	}

	loadGeoJson(json){
		const features=Terraformer.ArcGIS.convert(json);
		debugger;

	}

	addGraphics(arcgisJson) {}

	createGraphic(arcgisJson) {
		var graphic;
            // This magically sets geometry type!
            graphic = new Graphic(arcgisJson);
            // Set the correct symbol based on type and render - NOTE: Only supports simple renderers
            if (this.renderer && this.renderer.symbol) {
                //graphic.setSymbol(this.render.getSymbol(graphic));  // use for complex renderers
                graphic.setSymbol(this.renderer.symbol);
            } else {
            	graphic.setSymbol(this._getEsriSymbol(graphic.geometry.type));
            }
            // Update SR because terraformer sets incorrect spatial reference
            graphic.geometry.setSpatialReference(this.props.spatialReference); // NOTE: Has to match features!
            return graphic;
        }
    }


    export default GeoJsonLayer;
