import React from 'react';
import ReactDOM from 'react-dom';
import {Modal, Button} from 'react-bootstrap';
import {closeProjectPage}  from '../../actions/project';
import { connect } from 'react-redux'
import translate from '../../util/translate.js';
require("./project.scss");

class ProjectPage extends React.Component {

	constructor() {
	    super();
	}

  	close() {
  		this.props.onClose();
	}

	render() {
		const {project} = this.props;
		const {projectData, isPopupOpen, loadingData} = project;
		return (
    		<Modal animation={false} bsSize='large' aria-labelledby='contained-modal-title-lg'  
    		show={isPopupOpen} 
			onHide={this.close.bind(this)} >
				<Modal.Header closeButton >
					<Modal.Title>
						Project Page
					</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					
				</Modal.Body>
			</Modal>
	  	);
  	}
}



const mapDispatchToProps = (dispatch, ownProps) => {
 return {
    onClose: () => {
      dispatch(closeProjectPage());
    }
  }
}


const stateToProps = (state, props) => {
  return {
    project: state.project.toJS()
  };
}

export default connect(stateToProps, mapDispatchToProps)(ProjectPage);;
