
import React from 'react';
import { Pagination, Grid, Row, Col } from 'react-bootstrap';
import { connect } from 'react-redux'
import { sumarizeValues } from '../../../util/transactionUtil'
import {collectValues} from '../../../util/filterUtil';

require('./projectLayerPopup.scss');

var pageSize = 25;

export default class ProjectListTab extends React.Component {

  constructor() {
    super();
  }

  handleSelect(eventKey) {
    //workaround for fix bootstrap paginator issues
    let activePage = 1;
    const {totalPages, number} = this.props.charts.projectList.data;
    switch(eventKey.target.innerText) {
      case "»":
        activePage = totalPages-1;
        break;
      case "›":
        activePage = number+1;
        break;
      case "«":
        activePage = 1;
        break;
      case "‹":
        activePage = number-1;
        break;
      default :
        activePage = parseInt(eventKey.target.innerHTML);
        break;
    }
    this.getListData(activePage);
  }

  getListData(activePage){
    const {filtes, projectSearch, feature} = this.props;
    let filters = collectValues(filters, projectSearch);    
    Object.assign(filters, {
      'lo': [feature.properties.id],
      'page': activePage,
      'size': pageSize
    });    
    this.props.onGetPopupData(filters, 'projectList');
    this.setState({activePage: activePage});
  }

  dropLongText(text, length){
    return text.length>length? text.substr(0,length-3)+'...' : text;           
  }

  render() {
    const {content: projectsToShow=[], totalPages, number} = this.props.charts.projectList.data;
    return(
      <div className="">
        <div className="project-list-div">
          <Grid className='project-list'>
            <Row className="project-list-header">
              <Col md={5}>Title</Col>
              <Col md={3}>Funding Agency</Col>
              <Col md={2}>Commitments</Col>
              <Col md={2}>Disbursements</Col>
            </Row>  
            {projectsToShow.map((project) => {
              let transactions = sumarizeValues(project.transactions);
              return <Row className="project-list-item">
                  <Col className="project-title" title={project.title} md={5}>{this.dropLongText(project.title, 27)}</Col>
                  <Col md={3}>{project.fundingAgency.code}</Col>
                  <Col md={2}>{transactions.actualCommitments}</Col>
                  <Col md={2}>{transactions.actualDisbursements}</Col>
                </Row>             
            })}            
          </Grid>
        </div>
        <div className="projects-paginator">
          <Pagination 
            bsSize="small"
            prev
            next
            first
            last
            ellipsis
            boundaryLinks
            maxButtons={3}
            items={totalPages-1}
            activePage={number}
            onSelect={(eventKey) => {this.handleSelect(eventKey)}} />
        </div>
      </div>
    )
  }
}



