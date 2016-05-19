
import React from 'react';
import { Pagination, Grid, Row, Col } from 'react-bootstrap';
import { connect } from 'react-redux'
import { fetchPopupChartData } from '../../../actions/charts.js'
import { sumarizeValues } from '../../../util/transactionUtil.js'

require('./projectLayerPopup.scss');

var pageSize = 5;

export default class ProjectListTab extends React.Component {

  constructor() {
    super();
    this.state = {'activePage': 1};
  }

  handleSelect(eventKey) {
    //workaround for fix bootstrap paginator issues
    switch(eventKey.target.innerText) {
      case "»":
        this.setState({activePage: this.getPageAmount()});
        break;
      case "›":
        this.setState({activePage: this.state.activePage+1});
        break;
      case "«":
        this.setState({activePage: 1});
        break;
      case "‹":
        this.setState({activePage: this.state.activePage-1});
        break;
      default :
        this.setState({activePage: parseInt(eventKey.target.innerHTML)});
        break;
    }
  }

  paginateProjects() {
    let projects = this.props.projects;
    let start = pageSize*(this.state.activePage-1);
    let end = pageSize*(this.state.activePage);
    return projects.slice(start, end);
  }

  getPageAmount(){
    if (this.props.projects){
      return parseInt(this.props.projects.length/pageSize + (this.props.projects.length%pageSize>0? 1 : 0));
    } else {
      return 0;
    }
  }

  render() {
    let projectsToShow = this.paginateProjects() || [];
    return (
      <div className="">
        <div className="project-list-div">
          <Grid className='project-list'>
            <Row className="project-list-header">
              <Col md={6}>Title</Col>
              <Col md={3}>Commitments</Col>
              <Col md={3}>Disbursements</Col>
            </Row>  
            {projectsToShow.map((project) => {
              let transactions = sumarizeValues(project.transactions);
              return <Row className="project-list-item">
                  <Col className="project-title" md={6}>{project.title}</Col>
                  <Col md={3}>{transactions.actualCommitments}</Col>
                  <Col md={3}>{transactions.actualDisbursements}</Col>
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
            items={this.getPageAmount()}
            activePage={this.state.activePage}
            onSelect={(eventKey) => {this.handleSelect(eventKey)}} />
        </div>
      </div>
    )
  }
}



