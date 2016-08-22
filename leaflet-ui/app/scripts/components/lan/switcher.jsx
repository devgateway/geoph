import React from 'react';
import translate from '../../util/translate.js';
import i18next from 'i18next';
import { connect } from 'react-redux'
import {showLanSelector,setLanguage} from '../../actions/index.js'
require('./lan.scss');

export default class LangSwitcher extends React.Component {

	
	constructor() {
		super();
	}

	changeLanguage(lan){
		i18next.changeLanguage(lan, (err, t) => {
			this.props.onChangeLanguage(lan);
		});
	}

	render() {
		let content;

		let children=null;

		if (this.props.visible){
			children=<ul>
						<li className={(this.props.lan=='en')?"selected":""} href="" onClick={this.changeLanguage.bind(this,'en')}>{translate('header.language.english')}</li>
						<li className={(this.props.lan=='ph')?"selected":""} href="" onClick={this.changeLanguage.bind(this,'ph')}>{translate('header.language.philippine')}</li>
				 	 </ul>
		}else{
			//children=<span>	{this.props.lan.toUpperCase()} </span>
		}

		return <ul className="lan-selector">
					<li onClick={this.props.onClick}>
					<div className="icon"></div> 
					<div className="arrow"></div>
						<span>	{this.props.lan.toUpperCase()} </span>
				
						{children}

					</li>
		</ul>;
	}
}


const mapStateToProps = (state, props) => {
	return {
		lan: state.language.lan,
		visible:state.language.selector.visible
	}
}


const mapDispatchToProps = (dispatch, ownProps) => {
 	return {
	  	onClick:()=>{
	    	dispatch(showLanSelector())
	  	},
	  	onChangeLanguage:(lan)=>{
	   		dispatch(setLanguage(lan))
	  	}
	}
}



export default connect(mapStateToProps,mapDispatchToProps)(LangSwitcher);;
