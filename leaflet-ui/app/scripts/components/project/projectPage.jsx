import React from 'react';
import {Modal} from 'react-bootstrap';
import {closeProjectPage} from '../../actions/project';
import {connect} from 'react-redux'
import translate from '../../util/translate.js';
import {formatValue} from '../../util/format.js';
import Moment from 'moment'

require("./project.scss");

class Locations extends React.Component {
  
  render() {
    const {items} = this.props;
    return (
      <ul>
        {items.map(location => {
          const {id, name, childrens} = location;
          return (
            <li key={id}>
              <div>{name}</div>
              {childrens && childrens.length > 0 ?
                <Locations items={childrens}/>
                : null}
            </li>
          )
        })}
      </ul>
    );
  }
}

class ProjectPage extends React.Component {
  close() {
    this.props.onClose();
  }
  
  render() {
    const {project} = this.props;
    const {projectData, isPopupOpen, loadingData} = project;
    const {title, totalProjectAmount, fundingAgency, implementingAgencies, sectors, locationTree, periodPerformanceStart, periodPerformanceEnd, status, physicalStatus, trxAmounts = {}} = projectData;
    const {commitments, disbursements, expenditures} = trxAmounts;
    const notAvailable = translate('project.notavailable');
    return (
      <Modal animation={false} aria-labelledby='contained-modal-title-lg'
             show={isPopupOpen}
             onHide={this.close.bind(this)}
             dialogClassName="project-page-dialog">
        <Modal.Header closeButton>
          <Modal.Title>
            {translate('project.projectdetails')}
          </Modal.Title>
        </Modal.Header>
        {isPopupOpen ?
          loadingData ?
            <div className="loading-css">
              <div></div>
            </div>
            :
            <Modal.Body>
              <div className="project-grid">
                <div className="project-page-title">
                  {title}
                </div>
                <div className="project-field">
                  <p>{translate('project.projectcost')}</p>
                  <div>{totalProjectAmount ? "₱ " + formatValue(totalProjectAmount) : notAvailable}</div>
                </div>
                
                <div className="project-field">
                  <p>{translate('project.fundingtotals')}</p>
                  <div>
                    <ul>
                      {(commitments.actual && commitments.actual > 0) ?
                        <li>{"Actual Commitments: ₱ " + formatValue(commitments.actual)}</li> : null}
                      {(commitments.target && commitments.target > 0) ?
                        <li>{"Target Commitments: ₱ " + formatValue(commitments.target)}</li> : null}
                      {(commitments.cancelled && commitments.cancelled > 0) ?
                        <li>{"Cancelled Commitments: ₱ " + formatValue(commitments.cancelled)}</li> : null}
                      {(disbursements.actual && disbursements.actual > 0) ?
                        <li>{"Actual Disbursements: ₱ " + formatValue(disbursements.actual)}</li> : null}
                      {(disbursements.target && disbursements.target > 0) ?
                        <li>{"Target Disbursements: ₱ " + formatValue(disbursements.target)}</li> : null}
                      {(disbursements.cancelled && disbursements.cancelled > 0) ?
                        <li>{"Cancelled Disbursements: ₱ " + formatValue(disbursements.cancelled)}</li> : null}
                      {(expenditures.actual && expenditures.actual > 0) ?
                        <li>{"Actual Expenditures: ₱ " + formatValue(expenditures.actual)}</li> : null}
                      {(expenditures.target && expenditures.target > 0) ?
                        <li>{"Target Expenditures: ₱ " + formatValue(expenditures.target)}</li> : null}
                      {(expenditures.cancelled && expenditures.cancelled > 0) ?
                        <li>{"Cancelled Expenditures: ₱ " + formatValue(expenditures.cancelled)}</li> : null}
                    </ul>
                  </div>
                </div>
                
                <div className="project-field">
                  <p>{translate('project.fundingagency')}</p>
                  <div>{fundingAgency ? fundingAgency : notAvailable}</div>
                </div>
                
                <div className="project-field">
                  <p>{translate('project.implementingagencies')}</p>
                  <div>
                    <ul>
                      {implementingAgencies ? implementingAgencies.map(ia => {
                        return <li key={ia.id}>{ia.name}</li>
                      }) : notAvailable}
                    </ul>
                  </div>
                </div>
                
                <div className="project-field">
                  <p>{translate('project.sectors')}</p>
                  <div>
                    <ul>
                      {sectors ? sectors.map(sc => {
                        return <li key={sc.id}>{sc.name}</li>
                      }) : notAvailable}
                    </ul>
                  </div>
                </div>
                
                <div className="project-field">
                  <p>{translate('project.locations')}</p>
                  <div>
                    {locationTree && locationTree.length ? <Locations items={locationTree}/> : notAvailable}
                  </div>
                </div>
                
                <div className="project-field-pair">
                  <div className="project-field">
                    <p>{translate('project.physicalStartingDate')}</p>
                    <div>{periodPerformanceStart ? Moment(periodPerformanceStart).format("YYYY-MM-DD") : notAvailable}</div>
                  </div>
                  
                  <div className="project-field">
                    <p>{translate('project.physicalClosingDate')}</p>
                    <div>{periodPerformanceEnd ? Moment(periodPerformanceEnd).format("YYYY-MM-DD") : notAvailable}</div>
                  </div>
                </div>
                
                <div className="project-field-pair">
                  <div className="project-field">
                    <p>{translate('project.status')}</p>
                    <div>{status ? status : notAvailable}</div>
                  </div>
                  
                  <div className="project-field">
                    <p>{translate('project.physicalstatus')}</p>
                    <div>{physicalStatus ? physicalStatus : notAvailable}</div>
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
};

const stateToProps = (state, props) => {
  return {
    project: state.project.toJS()
  };
};

export default connect(stateToProps, mapDispatchToProps)(ProjectPage);
