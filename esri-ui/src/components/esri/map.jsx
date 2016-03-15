import React from 'react';
import ReactDOM from 'react/react-dom';
import Map from 'esri/Map';

const map = new Map({ basemap: 'streets'});


const MapComponent = React.createClass({

	componentWillMount() {

		this.element=new Map(this.props);
		this.element.then(
		()=>{
			console.log('map ready');
		},
		()=>{
			console.log('map failed');
		});


	},

	render: function() {

		const children = this.element ? React.Children.map(this.props.children, child => {return child ? React.cloneElement(child, {map:this.element}) : null;}) : null;
		return (
			<div className={this.props.className} style={this.props.style}>
			{children}
			</div>
			);
	}
});

export default MapComponent;
