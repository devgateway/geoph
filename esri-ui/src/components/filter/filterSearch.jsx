import React from 'react';
import { connect } from 'react-redux'
import { searchItemByText } from 'app/actions/filters'

class SearchText extends React.Component {

	constructor() {
	    super();
	    this.state = {};
	}

	triggerSearch(text) {
		
	}

  	render() {
  		return (
	        <div>
	        	<div className="input-group">
			      <input type="text" className="form-control" placeholder="Search..." />
			      <span className="input-group-btn">
			        <button className="btn btn-success" type="button">Go!</button>
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
