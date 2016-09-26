import React from 'react';
import ReactDOM from 'react-dom';
import {Modal, Button, Grid, Row, Col} from 'react-bootstrap';

export default class Disclaimer extends React.Component {	
	constructor() {
	    super();
	    this.state = {show: true};
	}
	
	close (){
		this.setState({show: false})
	}

	render() {
		return (
    		<Modal animation={false} aria-labelledby='contained-modal-title-lg'  
    		show={this.state.show} onHide={this.close.bind(this)}>
				<Modal.Header closeButton >
					<Modal.Title>
						Disclaimer Message
					</Modal.Title>
				</Modal.Header>
				<Modal.Body>
					<div className="disclaimer-content">
						Some text to be provided by NEDA...
					</div>
				</Modal.Body>
			</Modal>
		)
	}	
}