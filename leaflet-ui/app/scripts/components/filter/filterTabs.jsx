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
							{/*<Tab className="filter-list-content" eventKey={1} title="Funding Source">
								NOT YET IMPLEMENTED
						    </Tab>*/}
						    <Tab className="filter-list-content" eventKey={1} title="Funding Type (ODA)">
								<FilterList filterType="ft" {...this.props.filters["ft"]} />
						    </Tab>
						    <Tab className="filter-list-content" eventKey={2} title="Financing Institution (ODA)">
								<FilterList filterType="fa" {...this.props.filters["fa"]} />
						    </Tab>
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={2} title="Agency">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Implementing Agency">
								<FilterList filterType="ia" {...this.props.filters["ia"]} />
						    </Tab>						   
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={3} title="Sectors">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Sector">
								<FilterList filterType="st" {...this.props.filters["st"]} />
						    </Tab>
						    {/*<Tab className="filter-list-content" eventKey={2} title="Philippines Development Priority">
								NOT YET IMPLEMENTED
						    </Tab>*/}
						    <Tab className="filter-list-content" eventKey={2} title="Relevance to Climate">
								<FilterList filterType="cc" {...this.props.filters["cc"]}  showCode={true}/>
						    </Tab>
						    <Tab className="filter-list-content" eventKey={3} title="Gender">
								<FilterList filterType="gr" {...this.props.filters["gr"]} showCode={true}/>
						    </Tab>
						</Tabs>
	                </Tab>
	                {/*<Tab className="filter-tab-content" eventKey={4} title="Locations">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Coverage Scope">
								NOT YET IMPLEMENTED
						    </Tab>
						    <Tab className="filter-list-content" eventKey={2} title="Coverage">
								NOT YET IMPLEMENTED
						    </Tab>
						</Tabs>
	                </Tab>*/}
	                <Tab className="filter-tab-content" eventKey={4} title="Dates">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Implementation Period Start">
								<FilterDate filterType="dt_start" 
									lang={this.props.language.lan} {...this.props.filters["dt_start"]}
									dateMin={this.props.filters["dt_start"]? this.props.filters["dt_start"].items[1] : ''} 
									dateMax={this.props.filters["dt_start"]? this.props.filters["dt_start"].items[0] : ''}/>
						    </Tab>
						    <Tab className="filter-list-content" eventKey={2} title="Implementation Period End">
								<FilterDate filterType="dt_end" 
									lang={this.props.language.lan} {...this.props.filters["dt_end"]}
									dateMin={this.props.filters["dt_end"]? this.props.filters["dt_end"].items[3] : ''} 
									dateMax={this.props.filters["dt_end"]? this.props.filters["dt_end"].items[2] : ''}/>
						    </Tab>
						    <Tab className="filter-list-content" eventKey={3} title="Loan/Grant Validity Period Start">
								<FilterDate filterType="pp_start" 
									lang={this.props.language.lan} {...this.props.filters["pp_start"]}
									dateMin={this.props.filters["pp_start"]? this.props.filters["pp_start"].items[1] : ''} 
									dateMax={this.props.filters["pp_start"]? this.props.filters["pp_start"].items[0] : ''}/>
						    </Tab>
						    <Tab className="filter-list-content" eventKey={4} title="Loan/Grant Validity Period End">
								<FilterDate filterType="pp_end" 
									lang={this.props.language.lan} {...this.props.filters["pp_end"]}
									dateMin={this.props.filters["pp_end"]? this.props.filters["pp_end"].items[3] : ''} 
									dateMax={this.props.filters["pp_end"]? this.props.filters["pp_end"].items[2] : ''}/>
						    </Tab>
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={5} title="Status">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Financing Status">
								<FilterList filterType="sa" {...this.props.filters["sa"]}/>
						    </Tab>
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={6} title="Financial Amount">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Financial Amount">
								<FilterSlider filterType="fin_amount" valueSymbol="PHP" {...this.props.filters["fin_amount"]} 
									valueMin={this.props.filters["fin_amount"]? parseInt(this.props.filters["fin_amount"].items[1]) : 0} 
									valueMax={this.props.filters["fin_amount"]? parseInt(this.props.filters["fin_amount"].items[0]) : 100}/>
						    </Tab>
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={7} title="Physical and Financial">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Percentage of Target Reached">
								<FilterSlider filterType="pr" valueSymbol="%" {...this.props.filters["pr"]} 
									valueMin={this.props.filters["pr"]? parseInt(this.props.filters["pr"].items[1]) : 0} 
									valueMax={this.props.filters["pr"]? parseInt(this.props.filters["pr"].items[0]) : 100}/>
						    </Tab>
						    <Tab className="filter-list-content" eventKey={2} title="Physical Status">
								<FilterList filterType="ph" {...this.props.filters["ph"]} />
						    </Tab>
						    {/*<Tab className="filter-list-content" eventKey={3} title="Alert Level">
								NOT YET IMPLEMENTED
						    </Tab>*/}
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
