import React from 'react';
import ReactDOM from 'react-dom';
import {Modal, Button, Grid, Row, Col} from 'react-bootstrap';
import {connect} from 'react-redux';

class Disclaimer extends React.Component {	
	constructor() {
	    super();
	    this.state = {show: true};
	}
	
	close (){
		this.setState({show: false})
	}

	render() {
		const {loggedin} = this.props;
		const {show} = this.state;
		let shouldShowDisclaimer = !loggedin? show : false;
		return (
    		<Modal animation={false} aria-labelledby='contained-modal-title-lg'  
    		show={shouldShowDisclaimer} onHide={this.close.bind(this)}>
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

const mapStateToProps = (state, props) => {
	const {accountNonExpired,accountNonLocked,enabled,credentialsNonExpired}=state.security.toJS()
	const loggedin=(accountNonExpired && accountNonLocked && enabled && credentialsNonExpired);	
	return {loggedin}
}

export default connect(mapStateToProps)(Disclaimer);