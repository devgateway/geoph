import React from 'react';
import ReactDOM from 'react/react-dom';
import MapView from 'esri/views/MapView';
import domReady from "dojo/domReady!";


const ReactMapView = React.createClass({

	componentWillMount(){
	},

	componentDidMount() {

		const node = ReactDOM.findDOMNode(this.refs.mapView);
		this.view= new MapView({container: node,...this.props});
		window.view=this.view;
		this.view.then(function(){
			this.forceUpdate(); //render child elements
		}.bind(this),function(){
			console.log('Map view has failed');
		})

		this.view.watch('zoom',(zoom)=>{
			console.log(zoom);
		})
		this.view.watch('animation',(response)=>{
			if(response && response.state === "running"){
				console.log("Animation in progress");
			}
			else{
				console.log("No animation");
			}
		})
	},


	render: function() {  
		const view=this.view;
		const children = this.view ? React.Children.map(this.props.children, child => {return child ? React.cloneElement(child, {view}) : null;}) : null;
		return (<div ref='mapView'>{children}</div>);
	}
});

export default ReactMapView;
