import React from 'react';
import ReactDOM from 'react/react-dom';
import EsriGraphicsLayer from 'esri/layers/GraphicsLayer';
import Layer from 'app/components/esri/layers/Layer';
import Graphic from "esri/Graphic";
import domReady from "dojo/domReady!";


class GraphicsLayer extends Layer{
	componentWillMount() {
		this.element = new EsriGraphicsLayer();
		this.props.map.add(this.element)
	}

}

export default GraphicsLayer;
