import React from 'react';
import ReactDOM from 'react-dom';
import {Modal, Button, Tabs, Tab} from 'react-bootstrap';
//import FilterMap from 'conf/filterMap';
import FilterTabContent from 'app/components/filter/filterTabContent';
import AjaxUtil from 'app/util/AjaxUtil';
import Setting from 'app/util/settings';

let settings=Setting.getInstace();

const customStyles = {
  content : {
    top                   : '50%',
    left                  : '50%',
    right                 : 'auto',
    bottom                : 'auto',
    marginRight           : '-50%',
    transform             : 'translate(-50%, -50%)'
  }
};

export default class FilterPopup extends React.Component {

	constructor() {
	    super();
	    this.state = {
	    	'showModal': false, 
	    	'filterMap': settings.get('FILTERS','FILTER_MAP'), 
	    	'activeID': 1
	    };
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
			
			<Modal className='' bsSize='large' aria-labelledby='contained-modal-title-lg'
			 show={this.state.showModal} onHide={this.hideFilterPopup.bind(this)}>
				<Modal.Header>
					<Modal.Title>
						Filters
					</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					<div className="tab-container">
		              <Tabs defaultActiveKey={1}>
		              	{filters.map((filter) => {
		              		return <Tab className="" eventKey={filter.id} key={filter.id} title={filter.title}>
			                  	<FilterTabContent  {...filter}/>
			                </Tab>
				        })}		             
		              </Tabs>
		            </div>
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