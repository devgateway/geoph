import React from 'react';
import ReactDOM from 'react/react-dom';
import Extent from "esri/geometry/Extent";



const extend= React.createClass({

	componentWillMount() {
		const {xmax,xmin,ymin,ymax,spatialReference} = this.props;
		this.extent= new Extent({xmax,xmin,ymin,ymax,spatialReference});
		this.props.view.extent =this.extent;

		
	},


	render: function() {  
		return (<div/>);
	}

});

export default extend;
