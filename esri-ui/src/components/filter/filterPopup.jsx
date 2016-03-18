import React from 'react';
import ReactDOM from 'react-dom';
import {Modal, Button, Tabs, Tab} from 'react-bootstrap';
import FilterTabs from 'app/components/filter/filterTabs';

export default class FilterPopup extends React.Component {

	constructor() {
	    super();
	    this.state = {'showModal': false};
	}

  	componentDidMount() {
	
	}

	showFilterPopup() {
	    this.setState({'showModal': true});
	}

  	hideFilterPopup() {
	    this.setState({'showModal': false});
	}

  	reset() {
	   
	}

  	cancel() {
	    this.setState({'showModal': false});
	}

  	apply() {
	    
	}

  	render() {
  		let filters = this.state.filterMap || [];
  		return (
    	<div>
	        <Button className='' onClick={this.showFilterPopup.bind(this)}>Filters</Button>
			<Modal bsSize='large' aria-labelledby='contained-modal-title-lg'
			 show={this.state.showModal} onHide={this.hideFilterPopup.bind(this)}>
				<Modal.Header>
					<Modal.Title>
						Filters
					</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					<FilterTabs />
				</Modal.Body>
				<Modal.Footer>
					<Button className="btn btn-sm" bsStyle='danger' onClick={this.reset.bind(this)}>Reset</Button>
        			<Button className="btn btn-sm" bsStyle='warning' onClick={this.cancel.bind(this)}>Cancel</Button>
        			<Button className="btn btn-sm" bsStyle='success' onClick={this.apply.bind(this)}>Apply</Button>
				</Modal.Footer>
			</Modal>
		</div>
      	);
  	}
}