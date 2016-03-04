import React from 'react';
import ReactDOM from 'react/react-dom';
import Graphic from "esri/Graphic";

const graphic= React.createClass({

	componentWillMount() {
		this.element = new Graphic(this.props);
		this.props.layer.add(this.element);
	},

	render: function() {  
		return null;
	}

});

export default graphic;
