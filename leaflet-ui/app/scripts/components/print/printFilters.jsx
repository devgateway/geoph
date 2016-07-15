import React from 'react';
import { connect } from 'react-redux'
import translate from '../../util/translate';
import {getSelectedFilterNames} from '../../util/filterUtil';

class FilterList extends React.Component {

  	render() {
  		return (
	    	<ul className="filter-list">
	    		{this.props.items.map((item) => {
	    			return <FilterListItem {...item} />
	    		})}
            </ul>
	    ); 
  	}
}

class FilterListItem extends React.Component {
	
  	render() {
  		return (
  			<li>{this.props.name}
  			{this.props.childSelection?
				<FilterList items={this.props.childSelection}/>
			: null}
			</li>
  		);
  	}
}

class PrintableFilters extends React.Component {

	render() {
  		let filtersSelected = getSelectedFilterNames(this.props.filters);
  		return (
	    	<div className="print-filters">
	    		<h1>{translate('header.filters.title')}</h1>
	    		{filtersSelected["ft"] && filtersSelected["ft"].length>0?
	    			<div>	
			    		<div className="print-filter-title">
			    			{translate('filters.funding.fundingtypeoda')}
			    		</div>
			    		<div className="print-filter-list">
			    			<FilterList items={filtersSelected["ft"]}/>
			    		</div>
		    		</div>
		    	: null}
		    	{filtersSelected["fa"] && filtersSelected["fa"].length>0?
		    		<div>
			    		<div className="print-filter-title">
			    			{translate('filters.funding.financinginstitutionoda')}
			    		</div>
			    		<div className="print-filter-list">
			    			<FilterList items={filtersSelected["fa"]}/>
			    		</div>
		    		</div>
		    	: null}
		    	{filtersSelected["ia"] && filtersSelected["ia"].length>0?
	    			<div>	
			    		<div className="print-filter-title">
			    			{translate('filters.agency.implementingagency')}
			    		</div>
			    		<div className="print-filter-list">
			    			<FilterList items={filtersSelected["ia"]}/>
			    		</div>
		    		</div>
		    	: null}
		    	{filtersSelected["st"] && filtersSelected["st"].length>0?
	    			<div>	
			    		<div className="print-filter-title">
			    			{translate('filters.sectors.sector')}
			    		</div>
			    		<div className="print-filter-list">
			    			<FilterList items={filtersSelected["st"]}/>
			    		</div>
		    		</div>
		    	: null}
		    	{filtersSelected["cc"] && filtersSelected["cc"].length>0?
	    			<div>	
			    		<div className="print-filter-title">
			    			{translate('filters.sectors.relevanceclimate')}
			    		</div>
			    		<div className="print-filter-list">
			    			<FilterList items={filtersSelected["cc"]}/>
			    		</div>
		    		</div>
		    	: null}
		    	{filtersSelected["gr"] && filtersSelected["gr"].length>0?
	    			<div>	
			    		<div className="print-filter-title">
			    			{translate('filters.sectors.gender')}
			    		</div>
			    		<div className="print-filter-list">
			    			<FilterList items={filtersSelected["gr"]}/>
			    		</div>
		    		</div>
		    	: null}
		    	{filtersSelected["sa"] && filtersSelected["sa"].length>0?
	    			<div>	
			    		<div className="print-filter-title">
			    			{translate('filters.status.financingstatus')}
			    		</div>
			    		<div className="print-filter-list">
			    			<FilterList items={filtersSelected["sa"]}/>
			    		</div>
		    		</div>
		    	: null}
		    	{filtersSelected["ph"] && filtersSelected["ph"].length>0?
	    			<div>	
			    		<div className="print-filter-title">
			    			{translate('filters.physical.physicalstatus')}
			    		</div>
			    		<div className="print-filter-list">
			    			<FilterList items={filtersSelected["ph"]}/>
			    		</div>
		    		</div>
		    	: null}
		    	{filtersSelected["dt_start"]?
	    			<div>	
			    		<div className="print-filter-title">
			    			{translate('filters.dates.implementperiodstart')}
			    		</div>
			    		{filtersSelected["dt_start"].from?
				    		<div className="print-filter-date">
				    			<b>{translate('filters.dates.from')}: </b>{filtersSelected["dt_start"].from}
				    		</div>
				    	:null}
				    	{filtersSelected["dt_start"].to?
				    		<div className="print-filter-date">
				    			<b>{translate('filters.dates.to')}: </b>{filtersSelected["dt_start"].to}
				    		</div>
				    	:null}
		    		</div>
		    	: null}
		    	{filtersSelected["dt_end"]?
	    			<div>	
			    		<div className="print-filter-title">
			    			{translate('filters.dates.implementperiodend')}
			    		</div>
			    		{filtersSelected["dt_end"].from?
				    		<div className="print-filter-date">
				    			<b>{translate('filters.dates.from')}: </b>{filtersSelected["dt_end"].from}
				    		</div>
				    	:null}
				    	{filtersSelected["dt_end"].to?
				    		<div className="print-filter-date">
				    			<b>{translate('filters.dates.to')}: </b>{filtersSelected["dt_end"].to}
				    		</div>
				    	:null}
		    		</div>
		    	: null}
		    	{filtersSelected["pp_start"]?
	    			<div>	
			    		<div className="print-filter-title">
			    			{translate('filters.dates.validityperiodstart')}
			    		</div>
			    		{filtersSelected["pp_start"].from?
				    		<div className="print-filter-date">
				    			<b>{translate('filters.dates.from')}: </b>{filtersSelected["pp_start"].from}
				    		</div>
				    	:null}
				    	{filtersSelected["pp_start"].to?
				    		<div className="print-filter-date">
				    			<b>{translate('filters.dates.to')}: </b>{filtersSelected["pp_start"].to}
				    		</div>
				    	:null}
		    		</div>
		    	: null}
		    	{filtersSelected["pp_end"]?
	    			<div>	
			    		<div className="print-filter-title">
			    			{translate('filters.dates.validityperiodend')}
			    		</div>
			    		{filtersSelected["pp_end"].from?
				    		<div className="print-filter-date">
				    			<b>{translate('filters.dates.from')}: </b>{filtersSelected["pp_end"].from}
				    		</div>
				    	:null}
				    	{filtersSelected["pp_end"].to?
				    		<div className="print-filter-date">
				    			<b>{translate('filters.dates.to')}: </b>{filtersSelected["pp_end"].to}
				    		</div>
				    	:null}
		    		</div>
		    	: null}
		    	{filtersSelected["pr"]?
	    			<div>	
			    		<div className="print-filter-title">
			    			{translate('filters.physical.percentagereached')}
			    		</div>
			    		{filtersSelected["pr"].from?
				    		<div className="print-filter-date">
				    			<b>{translate('filters.dates.from')}: </b>{filtersSelected["pr"].from}
				    		</div>
				    	:null}
				    	{filtersSelected["pr"].to?
				    		<div className="print-filter-date">
				    			<b>{translate('filters.dates.to')}: </b>{filtersSelected["pr"].to}
				    		</div>
				    	:null}
		    		</div>
		    	: null}
		    	{filtersSelected["fin_amount"]?
	    			<div>	
			    		<div className="print-filter-title">
			    			{translate('filters.financialamount.financialamount')}
			    		</div>
			    		{filtersSelected["fin_amount"].from?
				    		<div className="print-filter-date">
				    			<b>{translate('filters.dates.from')}: </b>{filtersSelected["fin_amount"].from}
				    		</div>
				    	:null}
				    	{filtersSelected["fin_amount"].to?
				    		<div className="print-filter-date">
				    			<b>{translate('filters.dates.to')}: </b>{filtersSelected["fin_amount"].to}
				    		</div>
				    	:null}
		    		</div>
		    	: null}
		    	
            </div>
	    );
	   
  	}
}

const mapStateToProps = (state, props) => {
  return {
    filters: state.filters.filterMain, language: state.language
  }
}

export default connect(mapStateToProps)(PrintableFilters);;
