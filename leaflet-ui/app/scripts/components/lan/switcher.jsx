import React from 'react';
import translate from '../../util/translate.js';
import i18next from 'i18next';
import { connect } from 'react-redux'
import {showLanSelector,setLanguage} from '../../actions/index.js'

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
		if (this.props.visible){
			content=(<li onClick={this.props.onClick}><div onClick={this.props.onClick}  className="options-icons basemaps"></div>
						<ul>
							<li className={(this.props.lan=='en')?"selected":""} href="" onClick={this.changeLanguage.bind(this,'en')}>{translate('header.language.english')}</li>
							<li className={(this.props.lan=='ph')?"selected":""} href="" onClick={this.changeLanguage.bind(this,'ph')}>{translate('header.language.philippine')}</li>
						</ul>
				</li>)
		} else {
			content=(<li onClick={this.props.onClick}><div onClick={this.props.onClick}  className="options-icons basemaps"></div>{this.props.lan.toUpperCase()} </li>)
		}
		return content;
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
