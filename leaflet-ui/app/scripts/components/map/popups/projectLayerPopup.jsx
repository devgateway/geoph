
import React from 'react';
import {Tabs, Tab, Button, Label} from 'react-bootstrap';
import { connect } from 'react-redux'
import Chart from '../../charts/chartComponent'
import ProjectList from './projectListTab'
import onClickOutside from 'react-onclickoutside'
import {collectValues} from '../../../util/filterUtil';
import { fetchPopupData } from '../../../actions/popup.js'
import translate from '../../../util/translate.js';

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
    const {filters, projectSearch, feature} = this.props;
    if (feature){
      let filt = collectValues(filters, projectSearch);  
      Object.assign(filt, {'lo': [feature.properties.id]});  
      if (tab=='projectList'){
        Object.assign(filt, {'page': 0, 'size': 25}); 
      }  
      this.props.onGetPopupData(filt, tab);
    }
  },

  render() {
    const {charts, fundingType, feature} = this.props;
    return (
      <div className="popup-container">
        <div className="popup-title">
          <h2>{feature? feature.properties.name : ""} </h2>
        </div>
        <div className="">
          <ul className='popup-tabs' role='tablist' >
            <li className={this.state.tabSelected=='fundingAgency'? 'active' : ''} role='tab' >
              <div onClick={this.changeTab.bind(this, 'fundingAgency')}>
                <span>{translate('infowindow.tab.financinginstitution')}</span>
              </div>
            </li>
            <li className={this.state.tabSelected=='implementingAgency'? 'active' : ''} role='tab' >
              <div onClick={this.changeTab.bind(this, 'implementingAgency')}>
                <span>{translate('infowindow.tab.implementingagency')}</span>
              </div>
            </li>
            <li className={this.state.tabSelected=='physicalStatus'? 'active' : ''} role='tab' >
              <div onClick={this.changeTab.bind(this, 'physicalStatus')}>
                <span>{translate('infowindow.tab.physicalstatus')}</span>
              </div>
            </li>
            <li className={this.state.tabSelected=='sector'? 'active' : ''} role='tab' >
              <div onClick={this.changeTab.bind(this, 'sector')}>
                <span>{translate('infowindow.tab.sector')}</span>
              </div>
            </li>
            <li className={this.state.tabSelected=='projectList'? 'active' : ''} role='tab' >
              <div onClick={this.changeTab.bind(this, 'projectList')}>
                <span>{translate('infowindow.tab.projectlist')}</span>
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
                  measure={fundingType} 
                  chartType='pie'
                  width='400'
                  height='200'
                  showTotalHeader={true}
                  dimension="name"/>
                </div>
              : <div className="loading-css"><div></div></div>
            :null}
          </div>
        :null}
        {this.state.tabSelected=='implementingAgency'?
          <div className="popup-tab-content">
            {charts.implementingAgency?
              !charts.implementingAgency.isFetching?
                <div className="">
                  <Chart chartData={charts.implementingAgency}
                  measure={fundingType} 
                  chartType='pie'
                  width='400'
                  height='200'
                  showTotalHeader={true}
                  dimension="name"/>
                </div>
              : <div className="loading-css"><div></div></div>
            :null}
          </div>
        :null}
        {this.state.tabSelected=='physicalStatus'?
          <div className="popup-tab-content">
            {charts.physicalStatus?
              !charts.physicalStatus.isFetching?
                <div className="">
                  <Chart chartData={charts.physicalStatus}
                  measure={fundingType} 
                  chartType='pie'
                  width='400'
                  height='200'
                  showTotalHeader={true}
                  dimension="name"/>
                </div>
              : <div className="loading-css"><div></div></div>
            :null}
          </div>
        :null}
        {this.state.tabSelected=='sector'?
          <div className="popup-tab-content">
            {charts.sector?
              !charts.sector.isFetching?
                <div className="">
                  <Chart chartData={charts.sector}
                  measure={fundingType} 
                  chartType='pie'
                  width='400'
                  height='200'
                  showTotalHeader={true}
                  dimension="name"/>
                </div>
              : <div className="loading-css"><div></div></div>
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
    projectSearch: state.projectSearch,
    language: state.language
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(ProjectLayerPopup);;

