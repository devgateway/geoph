import React from 'react';
import translate from '../../util/translate';
import {formatValue} from '../../util/format';
import { connect } from 'react-redux';
import {Tooltip, OverlayTrigger} from 'react-bootstrap';
require("./stats.scss");

class Stats extends React.Component {
	
	constructor() {
		super();
	}

	render() {	
		const {stats, settings} = this.props;
		const {type, measure} = settings.fundingType;	
		const {regional={}, national={}} = stats;
		let fundingLabel = translate('header.settings.'+type) + " " +  translate('header.settings.'+measure);
		let regionalValue = regional[measure]? regional[measure][type] || 0 : 0;
		let nationalValue = national[measure]? national[measure][type] || 0 : 0;
		return (
			<OverlayTrigger delayShow={1000} placement="top" overlay={(<Tooltip id="help.stats">{translate('help.stats')}</Tooltip>)}>
				<div className="stats-container">
					<div className="stats-pair">
						<div className="stats-title">
							<p>{translate('stats.totalSubNational')}</p>
						</div>
						<div className="stats-projects">
		            		<p>{translate('stats.projects')}</p>
		            		<h1>{regional.projectCount}</h1>
		       			</div>
		        		<div className="stats-funding">
		          			<p>{fundingLabel}</p>
		          			<h1>₱{formatValue(regionalValue, 1)}</h1>
		        		</div>
				    </div>
				    <div className="stats-pair">
				    	<div className="stats-title">
							<p>{translate('stats.totalNational')}</p>
						</div>
						<div className="stats-projects">
		            		<p>{translate('stats.projects')}</p>
		            		<h1>{national.projectCount}</h1>
		       			</div>
		        		<div className="stats-funding">
		          			<p>{fundingLabel}</p>
		          			<h1>₱{formatValue(nationalValue, 1)}</h1>
		        		</div>
				    </div>
				</div>
			</OverlayTrigger>
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


