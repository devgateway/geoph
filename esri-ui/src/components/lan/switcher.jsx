import React from 'react';
import translate from 'app/util/translate';
import i18next from 'i18next';

export default class LangSwitcher extends React.Component {

	changeLanguage(evt){
		let lan=evt.target.value;
		i18next.changeLanguage(lan, (err, t) => {
			this.props.onChangeLanguage(lan);
		});
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