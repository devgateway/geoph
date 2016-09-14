
import React from 'react';
import { Pagination, Grid, Row, Col } from 'react-bootstrap';
import { connect } from 'react-redux'
import { formatValue } from '../../../util/format'
import {collectValues} from '../../../util/filterUtil';
import {getActivePage} from '../../../util/paginatorUtil';
import translate from '../../../util/translate.js';
import ProjectLink from '../../project/projectLink'

require('./projectLayerPopup.scss');

var pageSize = 25;

export default class ProjectListTab extends React.Component {

  constructor() {
    super();
  }

  handleSelect(eventKey) {
    const {totalPages, number} = this.props.charts.projectList.data;
    this.getListData(getActivePage(eventKey, totalPages, number));
  }

  getListData(activePage){
    const {filters, projectSearch, feature} = this.props;
    let filtersApplied = collectValues(filters, projectSearch);    
    Object.assign(filtersApplied, {
      'lo': [feature.properties.id],
      'page': activePage,
      'size': pageSize
    });    
    this.props.onGetPopupData(filtersApplied, 'projectList');
    this.setState({activePage: activePage});
  }

  render() {
    const {content: projectsToShow=[], totalPages, number} = this.props.charts.projectList.data;
    return(
      <div className="">
        <div className="project-list-div">
          <Grid className='project-list'>
            <Row className="project-list-header">
              <Col md={5}>{translate('infowindow.projectlist.title')}</Col>
              <Col md={3}>{translate('infowindow.projectlist.financinginstitution')}</Col>
              <Col md={2}>{translate('infowindow.projectlist.actualcommitments')}</Col>
              <Col md={2}>{translate('infowindow.projectlist.actualdisbursements')}</Col>
            </Row>  
            {projectsToShow.map((project) => {
              const {commitments, disbursements} = project.trxAmounts;
              return <Row className="project-list-item" key={project.id}>
                  <Col className="project-title" title={project.title} md={5}>
                    <ProjectLink {...project} store={this.props.store}/>
                  </Col>
                  <Col md={3}>{project.fundingAgency.code}</Col>
                  <Col md={2}>₱ {formatValue(commitments.actual)}</Col>
                  <Col md={2}>₱ {formatValue(disbursements.actual)}</Col>
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
            items={totalPages}
            activePage={number+1}
            onSelect={(eventKey) => {this.handleSelect(eventKey)}} />
        </div>
      </div>
    )
  }
}



