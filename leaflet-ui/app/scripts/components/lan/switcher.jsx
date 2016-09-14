import React from 'react';
import translate from '../../util/translate.js';
import i18next from 'i18next';
import { connect } from 'react-redux'
import {showLanSelector,setLanguage} from '../../actions/index.js'
require('./lan.scss');

class LangSwitcher extends React.Component {

	
	constructor() {
		super();
	}

	changeLanguage(lan){
		i18next.changeLanguage(lan, (err, t) => {
			this.props.onChangeLanguage(lan);
		});
	}

	render() {
		const {lan} = this.props;
		return (
			<div className="lan-selector">
				<div className={lan=='en'? "usa-icon" : "usa-icon-disabled"} onClick={this.changeLanguage.bind(this,'en')}></div> 
				<div className={lan=='ph'? "ph-icon" : "ph-icon-disabled"} onClick={this.changeLanguage.bind(this,'ph')}></div>		
			</div>
		);
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
