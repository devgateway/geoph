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

  	render() {
  		return (
	    	<div className="tab-container">
	    		<Tabs defaultActiveKey={1}>
	    			<Tab className="filter-tab-content" eventKey={1} title="Funding">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Funding Source">
								
						    </Tab>
						    <Tab className="filter-list-content" eventKey={2} title="Funding Type (ODA)">
								<FilterList filterType="ft" {...this.props.filters["ft"]} />
						    </Tab>
						    <Tab className="filter-list-content" eventKey={3} title="Financing Institution">
								<FilterList filterType="fa" {...this.props.filters["fa"]} />
						    </Tab>
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={2} title="Agencies">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Implementing Agency">
								<FilterList filterType="ia" {...this.props.filters["ia"]} />
						    </Tab>						   
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={3} title="Sectors">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Sectors">
								<FilterList filterType="st" {...this.props.filters["st"]} />
						    </Tab>
						    <Tab className="filter-list-content" eventKey={2} title="Philippines Development Priority">
								Philippines Development Priority Tab content
						    </Tab>
						    <Tab className="filter-list-content" eventKey={3} title="Relevance to climate">
								<FilterList filterType="cc" {...this.props.filters["cc"]} />
						    </Tab>
						    <Tab className="filter-list-content" eventKey={4} title="Gender">
								<FilterList filterType="gr" {...this.props.filters["gr"]} />
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
								<FilterDate filterType="dt" lang={this.props.language.lan} {...this.props.filters["dt"]}/>
						    </Tab>
						    <Tab className="filter-list-content" eventKey={2} title="Loan/Grant Validity Period">
								<FilterDate startDateLabel='Effective Date' endDateLabel='Loan Closing Date' filterType="pp" lang={this.props.language.lan} {...this.props.filters["pp"]}/>
						    </Tab>
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={6} title="Status">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Financing Status">
								<FilterList filterType="sa" {...this.props.filters["sa"]}/>
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
								<FilterSlider filterType="pr" valueSymbol="%" {...this.props.filters["pr"]}/>
						    </Tab>
						    <Tab className="filter-list-content" eventKey={2} title="Physical Status">
								<FilterList filterType="ph" {...this.props.filters["ph"]} />
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
    filters: state.filters.filterMain, language: state.language
  }
}

export default connect(mapStateToProps)(FilterTabContent);;
