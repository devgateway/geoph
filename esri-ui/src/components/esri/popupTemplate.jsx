import React from 'react';
import ReactDOM from 'react/react-dom';
import domReady from "dojo/domReady!";


const PopupTemplate = React.createClass({

	componentWillMount(){
	
		this.popupTemplate=new PopupTemplate(this.props)
		this.popupTemplate.content = "<p>As of 2015, <b>{MARRIEDRATE}%</b> of the" +
									" population in this zip code is married.</p>" +
									"<ul><li>{MARRIED_CY} people are married</li>" +
									"<li>{NEVMARR_CY} have never married</li>" +
									"<li>{DIVORCD_CY} are divorced</li><ul>";

		this.props.layer.popupTemplate=this.popupTemplate;
	},

	componentDidMount() {
		debugger;
		//popupTemplate.content=
	},

	render() { 
		return <div style={{'display': 'none'}}></div>; 
	}

});

export default PopupTemplate;
