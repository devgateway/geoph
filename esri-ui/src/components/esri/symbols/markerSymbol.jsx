import React from 'react';
import ReactDOM from 'react/react-dom';
import SimpleMarkerSymbol from "esri/symbols/SimpleMarkerSymbol";



const simpleMarkerSymbol= React.createClass({

	componentWillMount() {
		this.element = new SimpleMarkerSymbol(this.props);
	},

	render: function() {  
		return (<div></div>);
	}

});

export default simpleMarkerSymbol;
