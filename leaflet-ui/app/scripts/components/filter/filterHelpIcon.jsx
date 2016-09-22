import React from 'react';
import translate from '../../util/translate.js';
import {Tooltip, OverlayTrigger} from 'react-bootstrap';

export default class FilterHelpIcon extends React.Component {

  	render() {
  		const {helpTextKey} = this.props;
  		return (
  			<OverlayTrigger delayShow={100} placement="top" overlay={(<Tooltip id={helpTextKey}>{translate(helpTextKey)}</Tooltip>)}>
	        	<div className="filter-help-icon"/>	        	
		    </OverlayTrigger>
      	);	  
  	}
}
