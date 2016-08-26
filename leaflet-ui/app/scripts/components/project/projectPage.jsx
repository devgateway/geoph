import React from 'react';
import ReactDOM from 'react-dom';
import {Modal, Button, Grid, Row, Col} from 'react-bootstrap';
import {closeProjectPage}  from '../../actions/project';
import { connect } from 'react-redux'
import translate from '../../util/translate.js';
import {formatValue} from '../../util/format.js';
import Moment from 'moment'
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
		const {title, totalProjectAmount, fundingAgency, implementingAgencies, sectors, locations, periodPerformanceStart, periodPerformanceEnd, status, physicalStatus} = projectData;
		debugger;
		return (
    		<Modal animation={false} aria-labelledby='contained-modal-title-lg'  
    		show={isPopupOpen} 
			onHide={this.close.bind(this)} >
			
				<Modal.Header closeButton >
					<Modal.Title>
						{translate('project.projectdetails')}
					</Modal.Title>
				</Modal.Header>
				{isPopupOpen?
					loadingData?
						<div className="loading-css"><div></div></div>
					: 
						<Modal.Body>
							<div className="project-grid"> 
							<Grid>
							    <Row className="project-page-title">
							      <Col md={12}>{title}</Col>
							    </Row>
							    <Row className="project-field">
							      <Col md={5}><h2>{translate('project.projectcost')}</h2></Col>
							      <Col md={7}>{totalProjectAmount || "Not Available"}</Col>
							    </Row>
							     
							    <Row className="project-field">
							      <Col md={5}><h2>{translate('project.fundingagency')}</h2></Col>
							      <Col md={7}>{fundingAgency? fundingAgency.name : "Not Available"}</Col>
							    </Row>

							    <Row className="project-field">
							      <Col md={5}><h2>{translate('project.implementingagencies')}</h2></Col>
							      <Col md={7}>
							      	<ul>
							      	{implementingAgencies? implementingAgencies.map(ia=>{return <li>{ia.pk.agency.name}</li>}) : "Not Available"}
							      	</ul>
							      </Col>
							    </Row>

							    <Row className="project-field">
							      <Col md={5}><h2>{translate('project.sectors')}</h2></Col>
							      <Col md={7}>
							      	<ul>
							      	{sectors? sectors.map(sc=>{return <li>{sc.pk.sector.name}</li>}) : "Not Available"}
							      	</ul>
							      </Col>
							    </Row>

							    <Row className="project-field">
							      <Col md={5}><h2>{translate('project.locations')}</h2></Col>
							      <Col md={7}>
							      	<ul>
							      	{locations? locations.map(loc=>{return <li>{loc.name}</li>}) : "Not Available"}
							      	</ul>
							      </Col>
							    </Row>

							    <Row className="project-field">
							      <Col md={5}><h2>{translate('project.physicalStartingDate')}</h2></Col>
							      <Col md={7}>{periodPerformanceStart? Moment(periodPerformanceStart).format("YYYY-MM-DD") : "Not Available"}</Col>
							    </Row>

							    <Row className="project-field">
							      <Col md={5}><h2>{translate('project.physicalClosingDate')}</h2></Col>
							      <Col md={7}>{periodPerformanceEnd? Moment(periodPerformanceEnd).format("YYYY-MM-DD") : "Not Available"}</Col>
							    </Row>

							    <Row className="project-field">
							      <Col md={5}><h2>{translate('project.status')}</h2></Col>
							      <Col md={7}>{status? status.name : "Not Available"}</Col>
							    </Row>

							    <Row className="project-field">
							      <Col md={5}><h2>{translate('project.physicalstatus')}</h2></Col>
							      <Col md={7}>{physicalStatus? physicalStatus.name : "Not Available"}</Col>
							    </Row>
							    
							</Grid>
							</div>
						</Modal.Body>
				: null}
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
