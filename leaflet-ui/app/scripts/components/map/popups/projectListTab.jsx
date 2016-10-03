
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
          <div className='project-list'>
            <div className="project-list-header">
              <div className="project-list-item-col5">{translate('infowindow.projectlist.title')}</div>
              <div className="project-list-item-col3">{translate('infowindow.projectlist.financinginstitution')}</div>
              <div className="project-list-item-col2 funding-title">{translate('infowindow.projectlist.actualcommitments')}</div>
              <div className="project-list-item-col2 funding-title">{translate('infowindow.projectlist.actualdisbursements')}</div>
            </div>  
            {projectsToShow.map((project) => {
              const {commitments, disbursements} = project.trxAmounts;
              return (
                <div className="project-list-item" key={project.id}>
                  <div className="project-title" title={project.title} className="project-list-item-col5">
                    <ProjectLink {...project} store={this.props.store}/>
                  </div>
                  <div className="project-list-item-col3">{project.fundingAgency.code}</div>
                  <div className="project-list-item-col2">₱ {formatValue(commitments.actual)}</div>
                  <div className="project-list-item-col2">₱ {formatValue(disbursements.actual)}</div>
                </div>
              )             
            })}            
          </div>
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



