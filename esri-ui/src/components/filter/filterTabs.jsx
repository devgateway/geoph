import React from 'react';
import {Tabs, Tab, Button, Label} from 'react-bootstrap';
import ItemComponent from 'app/components/filter/filterItemList'
import SearchComponent from 'app/components/filter/filterSearch'
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
								Funding Source Tab content
						    </Tab>
						    <Tab className="filter-list-content" eventKey={2} title="Funding Type (ODA)">
								Funding Type (ODA) Tab content
						    </Tab>
						    <Tab className="filter-list-content" eventKey={3} title="Financing Institution">
								<ItemComponent loadList={true} filterType="fa" {...this.props.filters["fa"]} />
						    </Tab>
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={2} title="Agencies">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Implementing Agency">
								<ItemComponent loadList={true} filterType="ia" {...this.props.filters["ia"]} />
						    </Tab>						   
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={3} title="Sectors">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Sectors">
								<SearchComponent />
								<ItemComponent loadList={true} filterType="st" {...this.props.filters["st"]} />
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
								Implementation period Tab content
						    </Tab>
						    <Tab className="filter-list-content" eventKey={2} title="Loan validity period">
								Loan validity period Tab content
						    </Tab>
						</Tabs>
	                </Tab>
	                <Tab className="filter-tab-content" eventKey={6} title="Financial ranges">
	                  	<Tabs defaultActiveKey={1} position="left" tabWidth={3}>
							<Tab className="filter-list-content" eventKey={1} title="Financial Amount">
								Financial Amount Tab content
						    </Tab>
						    <Tab className="filter-list-content" eventKey={2} title="Physical and Financial Performance">
								Physical and Financial Performance Tab content
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
    filters: state.filters
  }
}

export default connect(mapStateToProps)(FilterTabContent);;
