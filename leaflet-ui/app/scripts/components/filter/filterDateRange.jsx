import React from 'react';
import { connect } from 'react-redux'
import DatePicker from 'react-date-picker'
import { setFilterDate, fetchDatesLimitIfNeeded } from '../../actions/filters.js'


class FilterDate extends React.Component {

	constructor() {
	    super();
	    this.state = {'expanded': true, 'dateNow': Date.now()};
	}

	componentDidMount() {
		//this.props.onLoadDatesLimit(this.props.filterType);		
	}

	handleChange() {
		
	}

  	render() {
  		return (
	        <div className="date-picker-container">
	        	<div className="date-picker-div">
	        		<DatePicker minDate='2014-04-04' maxDate='2015-10-10' date={this.state.dateNow} onChange={this.handleChange} />
		        </div>	
		        <div className="date-picker-div">
	        		<DatePicker minDate='2014-04-04' maxDate='2015-10-10' date={this.state.dateNow} onChange={this.handleChange} />	 
		        </div>       
	        </div>
      	);
  	}
}


const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onLoadDatesLimit: (type) => {
      dispatch(fetchDatesLimitIfNeeded(type));
    },

    onDateChange: (filterItem) => {
      dispatch(setFilterDate(filterItem));
    }
  }
}

export default connect(null,mapDispatchToProps)(FilterDate);
