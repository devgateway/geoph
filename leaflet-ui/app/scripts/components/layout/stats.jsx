import React from 'react';
import {LangSwitcher} from '../lan/'
import translate from '../../util/translate';
import {formatValue} from '../../util/transactionUtil';
require("./stats.scss");

export default class Stats extends React.Component {
	
	constructor() {
		super();
	}

	render() {	
		const {stats} = this.props;	
		const {countOthers=0, amountOthers=0, countNational=0, amountNational=0} = stats;	
		return (
			<div className="stats-container">
				<div className="stats-pair">
					<p>{translate('stats.totalLocated')}</p>
					<div className="stats">
	            		<h1>{countOthers}</h1>
	            		<p>{translate('stats.projects')}</p>
	       			</div>
	        		<div className="stats">
	          			<h1>₱{formatValue(amountOthers, 1)}</h1>
	          			<p>{translate('stats.commitments')}</p>
	        		</div>
			    </div>
			    <div className="stats-pair">
			    	<p>{translate('stats.totalNational')}</p>
					<div className="stats">
	            		<h1>{countNational}</h1>
	            		<p>{translate('stats.projects')}</p>
	       			</div>
	        		<div className="stats">
	          			<h1>₱{formatValue(amountNational, 1)}</h1>
	          			<p>{translate('stats.commitments')}</p>
	        		</div>
			    </div>
			</div>
		)
	}
}

