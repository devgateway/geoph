import React from 'react';
import translate from 'app/util/translate';

console.log(translate)
export default class LangSwitcher extends React.Component {

	changeLanguage(evt){
		let lan=evt.target.value;
	    this.props.onChangeLanguage(lan);
	}

  	render() {
    	
    	return (
	        <div className="nav navbar-rigth lan-selector-container">
	        	<select  value={this.props.selected} name="lan" className="pull-right" onChange={this.changeLanguage.bind(this)}>
	              <option value="en">{translate('header.language.english')}</option>
	              <option value="es">{translate('header.language.spanish')}</option>
	            </select>
	        </div>
      	);
  	}
}