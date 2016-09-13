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
		const notAvailable = translate('project.notavailable');
		return (
    		<Modal animation={false} aria-labelledby='contained-modal-title-lg'  
    		show={isPopupOpen} 
			onHide={this.close.bind(this)} 
			dialogClassName="project-page-dialog">
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
								<div className="project-page-title">
							      {title}
							    </div>
							    <div className="project-field">
							      <p>{translate('project.projectcost')}</p>
							      <div>{totalProjectAmount? "₱ "+formatValue(totalProjectAmount) : notAvailable}</div>
							    </div>
							     
							    <div className="project-field">
							      <p>{translate('project.fundingagency')}</p>
							      <div>{fundingAgency? fundingAgency.name : notAvailable}</div>
							    </div>

							    <div className="project-field">
							      <p>{translate('project.implementingagencies')}</p>
							      <div>
							      	<ul>
							      	{implementingAgencies? implementingAgencies.map(ia=>{return <li>{ia.pk.agency.name}</li>}) : notAvailable}
							      	</ul>
							      </div>
							    </div>

							    <div className="project-field">
							      <p>{translate('project.sectors')}</p>
							      <div>
							      	<ul>
							      	{sectors? sectors.map(sc=>{return <li>{sc.pk.sector.name}</li>}) : notAvailable}
							      	</ul>
							      </div>
							    </div>

							    <div className="project-field">
							      <p>{translate('project.locations')}</p>
							      <div>
							      	<ul>
							      	{locations? locations.map(loc=>{return <li>{loc.pk.location.name}</li>}) : notAvailable}
							      	</ul>
							      </div>
							    </div>

							    <div className="project-field-pair">
								    <div className="project-field">
								      <p>{translate('project.physicalStartingDate')}</p>
								      <div>{periodPerformanceStart? Moment(periodPerformanceStart).format("YYYY-MM-DD") : notAvailable}</div>
								    </div>

								    <div className="project-field">
								      <p>{translate('project.physicalClosingDate')}</p>
								      <div>{periodPerformanceEnd? Moment(periodPerformanceEnd).format("YYYY-MM-DD") : notAvailable}</div>
								    </div>
								</div>

							    <div className="project-field-pair">
									<div className="project-field">
								      <p>{translate('project.status')}</p>
								      <div>{status? status.name : notAvailable}</div>
								    </div>

								    <div className="project-field">
								      <p>{translate('project.physicalstatus')}</p>
								      <div>{physicalStatus? physicalStatus.name : notAvailable}</div>
								    </div>
							    </div>
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
