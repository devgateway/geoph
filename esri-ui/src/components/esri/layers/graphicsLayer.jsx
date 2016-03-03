import React from 'react';
import ReactDOM from 'react/react-dom';
import GraphicsLayer from 'esri/layers/GraphicsLayer';
import Graphic from "esri/Graphic";


import domReady from "dojo/domReady!";


const graphicsLayer = React.createClass({

	componentWillMount() {
		this.element = new GraphicsLayer();
		this.props.map.add(this.element)
	},


	getClonedChildrenWithMap(extra) {
		const { children, map } = this.props;
		const props = Object.assign({map}, extra);

		return React.Children.map(children, child => {
			return child ? React.cloneElement(child, props) : null;
		});
	},

	renderChildrenWithProps: function(props) {
		const children =this.getClonedChildrenWithMap(props);
		return <div style={{display: 'none'}}>{children}</div>;
	},

	render() {
		debugger;
		return this.renderChildrenWithProps({layer:this.element});
	}

});

export default graphicsLayer;
