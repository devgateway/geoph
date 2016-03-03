import React from 'react';
import ReactDOM from 'react/react-dom';
import Point from "esri/geometry/Point";



const point= React.createClass({

	componentWillMount() {
		this.element = new Point({
        longitude: this.props.longitude,
        latitude: this.props.latitude
      });

	},

	render: function() {  
		return (<div></div>);
	}

});

export default point;
