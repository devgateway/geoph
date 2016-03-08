import React from 'react';
import {Translate} from '../containers/LangMessage';

export default class LangSwitcher extends React.Component {

	changeLanguage(evt){
		let lan=evt.target.value;
	    this.props.onChangeLanguaje(lan);
	}

  	render() {
    	console.log('Render LangSwitcher')
    	return (
	        <div className="nav navbar-rigth lan-selector-container">
	        	<select  value={this.props.selected} name="lan" className="pull-right" onChange={this.changeLanguage.bind(this)}>
	              <option value="en">{Translate('header.language.english')}</option>
	              <option value="es">{Translate('header.language.spanish')}</option>
	            </select>
	        </div>
      	);
  	}
}