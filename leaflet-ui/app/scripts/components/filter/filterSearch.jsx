import React from 'react';
import { connect } from 'react-redux'
import { searchItemByText } from '../../actions/filters'
import translate from '../../util/translate.js';

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
			      <input ref="keyword" type="text" className="form-control" placeholder={translate('filters.buttons.search')+"..."} onKeyUp={this.onKeyUp.bind(this)}/>
			      <span className="input-group-btn">
			        <button className="btn btn-success" type="button" onClick={this.triggerSearch.bind(this)}>{translate('filters.buttons.go')}!</button>
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