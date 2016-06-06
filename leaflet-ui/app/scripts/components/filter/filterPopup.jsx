import React from 'react';
import ReactDOM from 'react-dom';
import {Modal, Button} from 'react-bootstrap';
import FilterTabs from './filterTabs.jsx';
import {applyFilter, openFilter, cancelFilter, resetFilter}  from '../../actions/filters';
import { clearAllResults, clearAllProjectSelected } from '../../actions/projectSearch';
import { connect } from 'react-redux'
require('./filters.scss');

class FilterPopup extends React.Component {

	constructor() {
	    super();
	    this.state = {'showModal': false};
	}

  	componentDidMount() {
	
	}

	showFilterPopup() {
	    this.props.onFilterOpen();
	    this.setState({'showModal': true});
	}

  	hideFilterPopup() {
  		this.setState({'showModal': false});
	}

  	reset() {
	   this.props.onFilterReset();	    
	}

  	cancel() {
	    this.props.onFilterCancel();
	    this.hideFilterPopup();
	}

  	apply() {  		
	    this.props.onFilterApply();
	    this.hideFilterPopup();
	}

	render() {
		return (
    	<li onClick={this.showFilterPopup.bind(this)}><div className="options-icons filters"></div>Filters
			<Modal bsSize='large' aria-labelledby='contained-modal-title-lg'
			 show={this.state.showModal} onHide={this.cancel.bind(this)} >
				<Modal.Header closeButton >
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
		</li>
      	);
  	}
}



const mapDispatchToProps = (dispatch, ownProps) => {
 return {
    onFilterApply: () => {
      dispatch(clearAllResults());
      dispatch(clearAllProjectSelected());
      dispatch(applyFilter());
    },

    onFilterCancel: () => {
      dispatch(cancelFilter());
    },

    onFilterOpen: () => {
      dispatch(openFilter());
    },

    onFilterReset: () => {
      dispatch(resetFilter());
    }
  }
}




export default connect(null,mapDispatchToProps)(FilterPopup);;
