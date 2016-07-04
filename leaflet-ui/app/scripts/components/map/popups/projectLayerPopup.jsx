
import React from 'react';
import {Tabs, Tab, Button, Label} from 'react-bootstrap';
import { connect } from 'react-redux'
import Chart from '../../charts/chartComponent'
import ProjectList from './projectListTab'
import onClickOutside from 'react-onclickoutside'
import {collectValues} from '../../../util/filterUtil';
import { fetchPopupData } from '../../../actions/popup.js'

require('./projectLayerPopup.scss');

const ProjectLayerPopup = onClickOutside(React.createClass({

  getInitialState() {
    return {'tabSelected': 'fundingAgency'};
  },

  changeTab(tabSelected){
    this.setState({'tabSelected': tabSelected});
    this.getTabData(tabSelected);
  },

  handleClickOutside (evt) {
    if (this.props.onClosePopup){
      this.props.onClosePopup();
    }
  },

  componentDidMount(){
    this.getTabData('fundingAgency');
  },

  getTabData(tab){
    const {filtes, projectSearch, feature} = this.props;
    let filters = collectValues(filters, projectSearch);    
    Object.assign(filters, {'lo': [feature.properties.id]});  
    if (tab=='projectList'){
      Object.assign(filters, {'page': 1, 'size': 25}); 
    }  
    this.props.onGetPopupData(filters, tab);
  },

  render() {
    let charts = this.props.charts || {}
    debugger;
    return (
      <div className="popup-container">
        <div className="popup-title">
          <h2>{this.props.feature? this.props.feature.properties.name : ""} </h2>
        </div>
        <div className="">
          <ul className='popup-tabs' role='tablist' >
            <li className={this.state.tabSelected=='fundingAgency'? 'active' : ''} role='tab' >
              <div onClick={this.changeTab.bind(this, 'fundingAgency')}>
                <span>Financing Institution</span>
              </div>
            </li>
            <li className={this.state.tabSelected=='implementingAgency'? 'active' : ''} role='tab' >
              <div onClick={this.changeTab.bind(this, 'implementingAgency')}>
                <span>Implementing Agency</span>
              </div>
            </li>
            <li className={this.state.tabSelected=='physicalStatus'? 'active' : ''} role='tab' >
              <div onClick={this.changeTab.bind(this, 'physicalStatus')}>
                <span>Physical Status</span>
              </div>
            </li>
            <li className={this.state.tabSelected=='sector'? 'active' : ''} role='tab' >
              <div onClick={this.changeTab.bind(this, 'sector')}>
                <span>Sector</span>
              </div>
            </li>
            <li className={this.state.tabSelected=='projectList'? 'active' : ''} role='tab' >
              <div onClick={this.changeTab.bind(this, 'projectList')}>
                <span>Project List</span>
              </div>
            </li>
          </ul>
        </div>
        {this.state.tabSelected=='fundingAgency'?
          <div className="popup-tab-content">
            {charts.fundingAgency?
              !charts.fundingAgency.isFetching?
                <div className="">
                  <Chart chartData={charts.fundingAgency}
                  measure={this.props.fundingType} 
                  chartType='pie'
                  width='400'
                  height='200'
                  showMeasureSelector={false}
                  dimension="name"/>
                </div>
              : <div className='uil-ring-css'><div></div></div>
            :null}
          </div>
        :null}
        {this.state.tabSelected=='implementingAgency'?
          <div className="popup-tab-content">
            {charts.implementingAgency?
              !charts.implementingAgency.isFetching?
                <div className="">
                  <Chart chartData={charts.implementingAgency}
                  measure={this.props.fundingType} 
                  chartType='pie'
                  width='400'
                  height='200'
                  dimension="name"/>
                </div>
              : <div className='uil-ring-css'><div></div></div>
            :null}
          </div>
        :null}
        {this.state.tabSelected=='physicalStatus'?
          <div className="popup-tab-content">
            {charts.physicalStatus?
              !charts.physicalStatus.isFetching?
                <div className="">
                  <Chart chartData={charts.physicalStatus}
                  measure={this.props.fundingType} 
                  chartType='pie'
                  width='400'
                  height='200'
                  dimension="name"/>
                </div>
              : <div className='uil-ring-css'><div></div></div>
            :null}
          </div>
        :null}
        {this.state.tabSelected=='sector'?
          <div className="popup-tab-content">
            {charts.sector?
              !charts.sector.isFetching?
                <div className="">
                  <Chart chartData={charts.sector}
                  measure={this.props.fundingType} 
                  chartType='pie'
                  width='400'
                  height='200'
                  dimension="name"/>
                </div>
              : <div className='uil-ring-css'><div></div></div>
            :null}
          </div>
        :null} 
        {this.state.tabSelected=='projectList'?
          <div className="popup-tab-content">
            {charts.projectList?
                <div className="">
                  <ProjectList {...this.props}/>
                </div>
            :null}
          </div>
        :null}
      </div>
    )
  }
}));

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onGetPopupData: (filters, tab) => {
      dispatch(fetchPopupData(filters, tab));
    }
  }
}

const mapStateToProps = (state, props) => {
  return {
    fundingType: state.settings.fundingType,
    charts: state.popup,
    filters: state.filters.filterMain,
    projectSearch: state.projectSearch
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(ProjectLayerPopup);;

