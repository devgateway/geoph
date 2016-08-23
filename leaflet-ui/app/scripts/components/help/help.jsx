import React from 'react';
import ReactDOM from 'react-dom';
import * as Intro from 'intro.js';
require('./help.scss');
require('intro.js/introjs.css');

export default class Help extends React.Component{

	node(){
		return this.getDomObject();
	}

	show(){
		let intro=Intro.introJs();
		debugger;
		intro.setOptions(Object.assign(this.options,{ showStepNumbers:false}));
		intro.oncomplete(()=>{
			document.body.style.overflow = 'auto';  // firefox, chrome
		})
		document.body.style.overflow = 'hidden';  // firefox, chrome
		intro.start()
	}

	getDomObject(){
		return ReactDOM.findDOMNode(this).closest('#'+ this.props.parentId);
	}

	render(){
		return (
			<div className={"help"+(this.props.className?this.props.className:"")} onClick={this.help.bind(this)}>{""}</div>
		);
	}
}