import React from 'react';
import ReactDOM from 'react-dom';
import {Modal, Button} from 'react-bootstrap';
import FilterTabs from './filterTabs.jsx';
import {applyFilter, openFilter, cancelFilter, resetFilter}  from '../../actions/filters';
import { clearAllResults, clearAllProjectSelected } from '../../actions/projectSearch';
import { connect } from 'react-redux'
import translate from '../../util/translate.js';
import {Tooltip, OverlayTrigger} from 'react-bootstrap';

require('./filters.scss');

class FilterPopup extends React.Component {

	constructor() {
	    super();
	}

  	reset() {
	   this.props.onFilterReset();	    
	}

  	cancel() {
  		this.props.onFilterCancel();
	    this.props.onHide();
	}

  	apply() {  		
	    this.props.onFilterApply();
	    this.props.onHide();
	}

	componentWillReceiveProps(nextProps){
		if (nextProps.visible==true && !this.props.visible){
			this.props.onFilterOpen();
		}
	}

	render() {
		const {visible=false}=this.props;
		return (
    		<Modal animation={false} bsSize='large' aria-labelledby='contained-modal-title-lg'  
    		show={visible} 
			onHide={this.cancel.bind(this)} >
				<Modal.Header closeButton >
					<Modal.Title>
						{translate('header.filters.title')}
					</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					<FilterTabs />
				</Modal.Body>
				<Modal.Footer>
					<OverlayTrigger delayShow={1000} placement="top" overlay={(<Tooltip id="help.filters.reset">{translate('help.filters.reset')}</Tooltip>)}>
						<Button className="btn btn-sm" bsStyle='danger' onClick={this.reset.bind(this)}>{translate('filters.buttons.reset')}</Button>
        			</OverlayTrigger>
        			<OverlayTrigger delayShow={1000} placement="top" overlay={(<Tooltip id="help.filters.cancel">{translate('help.filters.cancel')}</Tooltip>)}>
        				<Button className="btn btn-sm" bsStyle='warning' onClick={this.cancel.bind(this)}>{translate('filters.buttons.cancel')}</Button>
        			</OverlayTrigger>
        			<OverlayTrigger delayShow={1000} placement="top" overlay={(<Tooltip id="help.filters.apply">{translate('help.filters.apply')}</Tooltip>)}>
        				<Button className="btn btn-sm" bsStyle='success' onClick={this.apply.bind(this)}>{translate('filters.buttons.apply')}</Button>
					</OverlayTrigger>
        		</Modal.Footer>
			</Modal>
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


const stateToProps = (state, props) => {
  return {
    language: state.language
  };
}

export default connect(stateToProps, mapDispatchToProps)(FilterPopup);;
