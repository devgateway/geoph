import React from 'react';
import { connect } from 'react-redux'
import { searchItemByText } from '../../actions/filters'
import translate from '../../util/translate.js';
import {Tooltip, OverlayTrigger} from 'react-bootstrap';

class SearchText extends React.Component {

	constructor() {
	    super();
	    this.state = {};
	}

	onKeyUp() {
		let keyword = this.refs.keyword.value;
		if (keyword.length != 1) {
          this.props.onTriggerSearch({filterType: this.props.filterType, text: keyword});
        }
	}

	triggerSearch(text) {
		this.props.onTriggerSearch({filterType: this.props.filterType, text: this.refs.keyword.value});
	}

  	render() {
  		return (
	        <div>
	        	<div className="input-group">
			      	<OverlayTrigger delayShow={1000} placement="top" overlay={(<Tooltip id="help.filters.search">{translate('help.filters.search')}</Tooltip>)}>
						<input ref="keyword" type="text" className="form-control" placeholder={translate('filters.buttons.search')+"..."} onKeyUp={this.onKeyUp.bind(this)}/>
					</OverlayTrigger>
			      <span className="input-group-btn">
			        <OverlayTrigger delayShow={1000} placement="top" overlay={(<Tooltip id="help.filters.go">{translate('help.filters.go')}</Tooltip>)}>
						<button className="btn btn-success" type="button" onClick={this.triggerSearch.bind(this)}>{translate('filters.buttons.go')}!</button>
					</OverlayTrigger>
			      </span>
			    </div>
       
	        </div>
      	);
  	}
}


const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onTriggerSearch: (filterSearch) => {
      dispatch(searchItemByText(filterSearch));
    }
  }
}

export default connect(null,mapDispatchToProps)(SearchText);