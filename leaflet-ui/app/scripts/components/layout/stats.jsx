import React from 'react';
import translate from '../../util/translate';
import {formatValue} from '../../util/transactionUtil';
import { connect } from 'react-redux';
require("./stats.scss");

class Stats extends React.Component {
	
	constructor() {
		super();
	}

	render() {	
		const {stats, settings} = this.props;
		const {type, measure} = settings.fundingType;	
		const {regional, national} = stats;
		let fundingLabel = translate('header.settings.'+type) + " " +  translate('header.settings.'+measure);
		let regionalValue = regional[measure]? regional[measure][type] || 0 : 0;
		let nationalValue = national[measure]? national[measure][type] || 0 : 0;
		return (
			<div className="stats-container">
				<div className="stats-pair">
					<p>{translate('stats.totalSubNational')}</p>
					<div className="stats">
	            		<h1>{regional.projectCount}</h1>
	            		<p>{translate('stats.projects')}</p>
	       			</div>
	        		<div className="stats">
	          			<h1>₱{formatValue(regionalValue, 1)}</h1>
	          			<p>{fundingLabel}</p>
	        		</div>
			    </div>
			    <div className="stats-pair">
			    	<p>{translate('stats.totalNational')}</p>
					<div className="stats">
	            		<h1>{national.projectCount}</h1>
	            		<p>{translate('stats.projects')}</p>
	       			</div>
	        		<div className="stats">
	          			<h1>₱{formatValue(nationalValue, 1)}</h1>
	          			<p>{fundingLabel}</p>
	        		</div>
			    </div>
			</div>
		)
	}
}

const mapStateToProps = (state, props) => {
  
  return {
    settings: state.settings,
    stats: state.stats,
    language: state.language
  }
}

export default connect(mapStateToProps)(Stats);;


