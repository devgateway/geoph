import React from 'react';
import {Tabs, Tab, Button, Label} from 'react-bootstrap';
import FilterList from './filterListWithSearch'
import FilterDate from './filterDateRange'
import FilterSlider from './filterSliderRange'
import { connect } from 'react-redux'

class FilterTabContent extends React.Component {

	constructor() {
	    super();
	    this.state = {};
	}

  	componentDidMount() {
		
	}

	getFilterData(filterType){
		return this.props.filters.get('filters').toJS().find(function(it){return it.filterType==filterType})		
	}

  	render() {
  		debugger;
  		return (
	    	<div className="tab-container">
	    		<Tabs defaultActiveKey={1}>
	    			<Tab className="filter-tab-content" eventKey={1} title="Funding">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Funding Source">
								Funding Source Tab content
						    </Tab>
						    <Tab className="filter-list-content" eventKey={2} title="Funding Type (ODA)">
								<FilterList filterType="ft" {...this.getFilterData("ft")} />
						    </Tab>
						    <Tab className="filter-list-content" eventKey={3} title="Financing Institution">
								<FilterList filterType="fa" {...this.getFilterData("fa")} />
						    </Tab>
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={2} title="Agencies">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Implementing Agency">
								<FilterList filterType="ia" {...this.getFilterData("ia")} />
						    </Tab>						   
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={3} title="Sectors">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Sectors">
								<FilterList filterType="st" {...this.getFilterData("st")} />
						    </Tab>
						    <Tab className="filter-list-content" eventKey={2} title="Philippines Development Priority">
								Philippines Development Priority Tab content
						    </Tab>
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={4} title="Locations">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Coverage Scope">
								Coverage Scope Tab content
						    </Tab>
						    <Tab className="filter-list-content" eventKey={2} title="Coverage">
								Coverage Tab content
						    </Tab>
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={5} title="Dates">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Implementation period">
								<FilterDate filterType="ip" lang={this.props.language.lan} {...this.getFilterData("ip")}/>
						    </Tab>
						    <Tab className="filter-list-content" eventKey={2} title="Loan/Grant Validity Period">
								<FilterDate startDateLabel='Effective Date' endDateLabel='Loan Closing Date' filterType="gp" lang={this.props.language.lan} {...this.getFilterData("gp")}/>
						    </Tab>
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={6} title="Status">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Financing Status">
								<FilterList filterType="fs" {...this.getFilterData("fs")}/>
						    </Tab>
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={7} title="Financial Amount">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Financial Amount">
								
						    </Tab>
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={8} title="Physical and Financial">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Percentage of target reached">
								<FilterSlider filterType="pr" valueSymbol="%" {...this.getFilterData("pr")}/>
						    </Tab>
						    <Tab className="filter-list-content" eventKey={2} title="Physical Status">
								
						    </Tab>
						    <Tab className="filter-list-content" eventKey={3} title="Alert Level">
								
						    </Tab>
						</Tabs>
	                </Tab>
				</Tabs>
            </div>
	    );
	   
  	}
}

const mapStateToProps = (state, props) => {
  return {
    filters: state.filters, language: state.language
  }
}

export default connect(mapStateToProps)(FilterTabContent);;
