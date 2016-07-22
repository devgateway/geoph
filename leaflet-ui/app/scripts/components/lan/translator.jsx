import React from 'react';
import i18next from 'i18next';

export default class Translator extends React.Component {
	render(){
		const e=i18next;
		let key="";
		
		if (this.props.prefix){
			key=this.props.prefix+'.'+this.props.k;
		} else {
			key=this.props.k;
		}
		
		return <span className={this.props.className}>{i18next.t(key)}</span>		
	}
}