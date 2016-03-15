import React from 'react';
import {Tabs, Tab, Button, Label} from 'react-bootstrap';

export default class FilterTabContent extends React.Component {

	constructor() {
	    super();
	    this.state = {};
	}

  	componentDidMount() {
		
	}

  	render() {
  		return (
	    	<div className="tab-container">
				<Tabs defaultActiveKey={0} position="left" tabWidth={3}>
					{this.props.tabs.map((filter, idx) => {
						return <Tab className="" eventKey={idx} key={idx} title={filter.name}>
							{filter.name} Tab content
					    </Tab>
					})}		             
				</Tabs>
            </div>
	    );
	   
  	}
}