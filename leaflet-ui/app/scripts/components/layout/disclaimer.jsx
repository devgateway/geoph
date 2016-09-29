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
					<div className="bs-callout bs-callout-message">
						This is a Beta version of the website which is still undergoing final testing before its official release. The primary purpose of this Beta testing is to obtain feedback on site functionality and performance, and accuracy of data reflected herein. All figures reported are as of December 2015 and were sourced from submission of implementing agencies, development partners, and other sources during NEDA's conduct of periodic updating of status of ODA and locally-funded programs and projects.
						Should you have any suggestions, please contact us at e-mail address: <a href="mailto:jpowell@developmentgateway.org">jpowell@developmentgateway.org</a> 
					</div>
					<div className="bs-callout bs-callout-message">
						Ito ay Beta bersyon ng website na kasalukuyang sumasailalim ng pinal na testing bago opisyal na mailabas. Ang pangunahing layunin ng Beta testing ay makakuha ng katugunan ukol sa site functionality at performance nito at pati na rin ang kawastuhan ng datos. Lahat ng numero na nai-ulat ay mula Disyembre 2015 na nakuha mula sa implementing agencies at development partners sa panahon ng pagsasagawa ng pana-panahong pag-update ng kalagayan ng ODA at LFPPs.
						Kung may suhestyon, maaari po kayong kumontak sa email address na eto: <a href="mailto:jpowell@developmentgateway.org">jpowell@developmentgateway.org</a>
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