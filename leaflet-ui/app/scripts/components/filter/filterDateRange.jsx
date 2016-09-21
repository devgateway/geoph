import React from 'react';
import { connect } from 'react-redux'
import DatePicker from 'react-date-picker'
import Moment from 'moment'
import { setFilterRange, fetchFilterDataIfNeeded } from '../../actions/filters.js'
import translate from '../../util/translate.js';
import HelpIcon from './filterHelpIcon'
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
  		const {startMinDate, startMaxDate, endMinDate, endMaxDate, lang, helpTextKey, startDateLabel, endDateLabel, maxSelected, minSelected} = this.props;
  		let lng = lang=='ph'? 'tl-ph' : lang; //workaraound for momentjs locale issue
  		return (
	        <div className="date-picker-container">
	        	<div className="date-picker-div">
	        		<span>{startDateLabel || translate('filters.dates.from')}: <b>{minSelected || translate('filters.dates.notset')}</b></span>
	        		{minSelected?
	        			<span className="clear-date" onClick={this.handleStartDate.bind(this, null)}>X</span>
	        		: null}
	        		<DatePicker 
	        			hideFooter={true}
	        			ref="startDate" 
	        			locale={lng} 
	        			minDate={startMinDate} 
	        			maxDate={startMaxDate} 
	        			date={minSelected} 
	        			onChange={this.handleStartDate.bind(this)} />
		        </div>
		        <div className="date-picker-divisor"/>	
		        <div className="date-picker-div">
	        		<span>{endDateLabel || translate('filters.dates.to')}: <b>{maxSelected || translate('filters.dates.notset')}</b></span>
	        		{maxSelected?
	        			<span className="clear-date" onClick={this.handleEndDate.bind(this, null)}>X</span>
	        		: null}
	        		<DatePicker 
	        			hideFooter={true}
	        			ref="endDate" 
	        			locale={lng} 
	        			minDate={endMinDate} 
	        			maxDate={endMaxDate} 
	        			date={maxSelected} 
	        			onChange={this.handleEndDate.bind(this)} />	 
		        </div>
		        <HelpIcon helpTextKey={helpTextKey}/>
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
