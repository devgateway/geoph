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
		return null;

		const {stats, settings} = this.props;
		const {fundingType} = settings;	
		const {regional, national} = stats;
		let fundingLabel = translate('stats.'+fundingType.type) + " " +  translate('stats.'+fundingType.measure);
		return (
			<div className="stats-container">
				<div className="stats-pair">
					<p>{translate('stats.totalSubNational')}</p>
					<div className="stats">
	            		<h1>{regional.projectCount}</h1>
	            		<p>{translate('stats.projects')}</p>
	       			</div>
	        		<div className="stats">
	          			<h1>₱{formatValue(regional[fundingType.measure][fundingType.type], 1)}</h1>
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
	          			<h1>₱{formatValue(national[fundingType.measure][fundingType.type], 1)}</h1>
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


