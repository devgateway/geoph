import React from 'react';
import translate from '../..//util/translate.js';
import i18next from 'i18next';
import { connect } from 'react-redux'
export default class LangSwitcher extends React.Component {

	
	constructor() {
		super();
	}


	changeLanguage(lan){
		debugger;
		this.props.onChangeLanguage(lan);
	}

	render() {
		let content;
		if (this.props.visible){
			content=(<li onClick={this.props.onClick}><div onClick={this.props.onLanClick}  className="options-icons basemaps"></div>

						<kbd  onClick={this.changeLanguage.bind(this,'en')}>{translate('header.language.english')}</kbd>
						<kbd onClick={this.changeLanguage.bind(this,'es')}>{translate('header.language.spanish')}</kbd>
				</li>)
		}else{

			content=(<li onClick={this.props.onClick}><div onClick={this.props.onLanClick}  className="options-icons basemaps"></div>{this.props.lan.toUpperCase()} </li>)


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

export default connect(mapStateToProps)(LangSwitcher);;
