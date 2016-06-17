import React from 'react';
import { connect } from 'react-redux'
import DatePicker from 'react-date-picker'
import Moment from 'moment'
import { setFilterRange, fetchFilterDataIfNeeded } from '../../actions/filters.js'
require('react-date-picker/base.css');
require('react-date-picker/theme/hackerone.css');

class FilterDate extends React.Component {

	constructor() {
	    super();
	    this.state = {'errorMessage': '', 'dateNow': Date.now()};
	}

	componentDidMount() {
		this.props.onLoadFilterData(this.props.filterType);		
	}

	handleStartDate(date) {
		let sd = Moment(date);
		let ed = this.refs.endDate.getDate();//.format("YYYY-MM-DD");	
		if (date==this.props.minSelected){
			date = null;
		}	
		if (this.validateDates(sd, ed)){
			this.props.onDateChange({filterType: this.props.filterType, minSelected:date, maxSelected: this.props.maxSelected});
		}
	}

  	handleEndDate(date) {
		let ed = Moment(date);
		let sd = this.refs.startDate.getDate();//.format("YYYY-MM-DD");
		if (date==this.props.maxSelected){
			date = null;
		}	
		if (this.validateDates(sd, ed)){
			this.props.onDateChange({filterType: this.props.filterType, minSelected: this.props.minSelected, maxSelected: date});
		}
	}

	validateDates(startDate, endDate){
		if (!startDate || !endDate){
			return true;
		}
		if (startDate.isAfter(endDate) || endDate.isBefore(startDate)){
			this.setState({'errorMessage': 'End Date should be greater than Start Date'});
			return false;
		} else {
			this.setState({'errorMessage': ''});
			return true;
		}
	}

  	render() {
  		let startMinDate = this.props.dateMin;
  		let startMaxDate = this.props.maxSelected;
  		let endMinDate = this.props.minSelected;
  		let endMaxDate = this.props.dateMax;
  		return (
	        <div className="date-picker-container">
	        	<div className="date-picker-div">
	        		<span>{this.props.startDateLabel || 'From'}: <b>{this.props.minSelected || "Not Set"}</b></span>
	        		{this.props.minSelected?
	        			<span className="clear-date" onClick={this.handleStartDate.bind(this, null)}>X</span>
	        		: null}
	        		<DatePicker 
	        			hideFooter={true}
	        			ref="startDate" 
	        			locale={this.props.lang} 
	        			minDate={startMinDate} 
	        			maxDate={startMaxDate} 
	        			date={this.props.minSelected} 
	        			onChange={this.handleStartDate.bind(this)} />
		        </div>
		        <div className="date-picker-divisor"/>	
		        <div className="date-picker-div">
	        		<span>{this.props.endDateLabel || 'To'}: <b>{this.props.maxSelected || "Not Set"}</b></span>
	        		{this.props.maxSelected?
	        			<span className="clear-date" onClick={this.handleEndDate.bind(this, null)}>X</span>
	        		: null}
	        		<DatePicker 
	        			hideFooter={true}
	        			ref="endDate" 
	        			locale={this.props.lang} 
	        			minDate={endMinDate} 
	        			maxDate={endMaxDate} 
	        			date={this.props.maxSelected} 
	        			onChange={this.handleEndDate.bind(this)} />	 
		        </div>
		        {this.state.errorMessage.length>0?
					<div className="error-message">
		        		<b>Error: </b>
		        		{this.state.errorMessage}
			        </div>
		        : null}      
	        </div>
      	);
  	}
}


const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onLoadFilterData: (type) => {
      dispatch(fetchFilterDataIfNeeded(type));
    },

    onDateChange: (filterRange) => {
      dispatch(setFilterRange(filterRange));
    }
  }
}

export default connect(null,mapDispatchToProps)(FilterDate);
