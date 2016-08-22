import React from 'react';
import {LangSwitcher} from '../lan/'
import * as Constants from '../../constants/constants';
require('./header.scss');

export default class HeaderComponent extends React.Component {
	
	constructor() {
		super();
	}

	render() {
		
		return (
			<div className="header">
				<div className="heading">
					<LangSwitcher/>		
				</div>
					
					{this.props.children}
			
		    </div>
			)
	}
}

