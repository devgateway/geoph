
import React from 'react';
import {Tabs, Tab, Button, Label} from 'react-bootstrap';
import { connect } from 'react-redux'
import Chart from '../../charts/chartComponent'
import ProjectList from './projectListTab'
import onClickOutside from 'react-onclickoutside'
import {collectValues} from '../../../util/filterUtil';
import { fetchPopupData } from '../../../actions/popup.js';
import {fetchLocationStats} from '../../../actions/stats.js';
import translate from '../../../util/translate.js';
import {Tooltip, OverlayTrigger} from 'react-bootstrap';
import {formatValue} from '../../../util/format';

require('./projectLayerPopup.scss');

const ProjectLayerPopup = onClickOutside(React.createClass({

  getInitialState() {
    return {'tabSelected': 'fundingAgency', 'measureType': 'projectCount'};
  },

  changeTab(tabSelected){
    this.setState({'tabSelected': tabSelected});
    this.getTabData(tabSelected);
  },

  changeMeasure(chart, measureType){
    this.setState({'measureType': measureType});
  },

  handleClickOutside (evt) {
    if (this.props.onClosePopup){
      this.props.onClosePopup();
    }
  },

  componentWillMount(){
    const {feature} = this.props;
    if (feature){
      this.getTabData('fundingAgency');
      const {projectSearch, filters} = this.props;
      let filtersCollected = collectValues(filters, projectSearch);
      Object.assign(filtersCollected, {'lo': [feature.properties.id]}); 
      this.props.onLoadLocationStats(filtersCollected); 
    }    
  },

  getTabData(tab){
    const {filters, projectSearch, feature} = this.props;
    if (feature){
      let filtersCollected = collectValues(filters, projectSearch);  
      Object.assign(filtersCollected, {'lo': [feature.properties.id]});  
      if (tab=='projectList'){
        Object.assign(filtersCollected, {'page': 0, 'size': 25}); 
      }  
      this.props.onGetPopupData(filtersCollected, tab);
    }
  },

  render() {
    const {stats, charts, fundingType, feature, popupMaxHeight, popupMaxWidth} = this.props;
    if (!feature){
      return null;
    }
    const {level, name} = feature.properties;
    const {type, measure} = fundingType;
    const statsData = stats.get('location').get('data')[0] || {};
    const statsLoading = stats.get('location').get('isFetching');
    const {projectCount, trxAmounts={}} = statsData;
    let fundingLabel = translate('header.settings.'+type) + " " +  translate('header.settings.'+measure);
    let fundingValue = trxAmounts[measure]? trxAmounts[measure][type] || 0 : 0;
    return (
      <div className="popup-container" style={{width: (popupMaxWidth-10), height: (popupMaxHeight-10)}}>
        <div className="popup-title">
          <h2>{name || ""} </h2>
        </div>
        <div className="popup-stats">
          <div className="projects">
              <p>{translate('stats.projects')}</p>
              <div>{statsLoading?"0":projectCount}</div>
          </div>
          <div className="funding">
              <p>{fundingLabel}</p>
              <div>₱{statsLoading?"0":formatValue(fundingValue)}</div>
          </div>  
        </div>
        <div className="">
          <ul className='popup-tabs' role='tablist' >
            <li className={this.state.tabSelected=='fundingAgency'? 'active' : ''} role='tab' >
              <OverlayTrigger delayShow={1000} placement="top" overlay={(<Tooltip id="help.infowindow.financinginstitution">{translate('help.infowindow.financinginstitution')}</Tooltip>)}>
                <div onClick={this.changeTab.bind(this, 'fundingAgency')}>
                  <span>{translate('infowindow.tab.financinginstitution')}</span>
                </div>
              </OverlayTrigger>
            </li>
            <li className={this.state.tabSelected=='implementingAgency'? 'active' : ''} role='tab' >
              <OverlayTrigger delayShow={1000} placement="top" overlay={(<Tooltip id="help.infowindow.implementingagency">{translate('help.infowindow.implementingagency')}</Tooltip>)}>
                <div onClick={this.changeTab.bind(this, 'implementingAgency')}>
                  <span>{translate('infowindow.tab.implementingagency')}</span>
                </div>
              </OverlayTrigger>
            </li>
            <li className={this.state.tabSelected=='physicalStatus'? 'active' : ''} role='tab' >
              <OverlayTrigger delayShow={1000} placement="top" overlay={(<Tooltip id="help.infowindow.physicalstatus">{translate('help.infowindow.physicalstatus')}</Tooltip>)}>
                <div onClick={this.changeTab.bind(this, 'physicalStatus')}>
                  <span>{translate('infowindow.tab.physicalstatus')}</span>
                </div>
              </OverlayTrigger>
            </li>
            <li className={this.state.tabSelected=='sector'? 'active' : ''} role='tab' >
              <OverlayTrigger delayShow={1000} placement="top" overlay={(<Tooltip id="help.infowindow.sector">{translate('help.infowindow.sector')}</Tooltip>)}>
                <div onClick={this.changeTab.bind(this, 'sector')}>
                  <span>{translate('infowindow.tab.sector')}</span>
                </div>
              </OverlayTrigger>
            </li>
            <li className={this.state.tabSelected=='projectList'? 'active' : ''} role='tab' >
              <OverlayTrigger delayShow={1000} placement="top" overlay={(<Tooltip id="help.infowindow.projectlist">{translate('help.infowindow.projectlist')}</Tooltip>)}>
                <div onClick={this.changeTab.bind(this, 'projectList')}>
                  <span>{translate('infowindow.tab.projectlist')}</span>
                </div>
              </OverlayTrigger>
            </li>
          </ul>
        </div>
        {this.state.tabSelected=='fundingAgency'?
          <div className="popup-tab-content">
            {charts.fundingAgency?
              !charts.fundingAgency.isFetching?
                <div className="">
                  <Chart chartData={Object.assign(charts.fundingAgency, {'measureType': this.state.measureType})}
                  measure={fundingType} 
                  chartType='pie'
                  width={popupMaxWidth}
                  height={popupMaxHeight/1.6}
                  showTotalHeader={true}
                  onChangeMeasure={level==1? this.changeMeasure:null}
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
                  <Chart chartData={Object.assign(charts.implementingAgency, {'measureType': this.state.measureType})}
                  measure={fundingType} 
                  chartType='pie'
                  width={popupMaxWidth}
                  height={popupMaxHeight/1.6}
                  showTotalHeader={true}
                  onChangeMeasure={level==1? this.changeMeasure:null}
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
                  <Chart chartData={Object.assign(charts.physicalStatus, {'measureType': this.state.measureType})}
                  measure={fundingType} 
                  chartType='pie'
                  width={popupMaxWidth}
                  height={popupMaxHeight/1.6}
                  showTotalHeader={true}
                  onChangeMeasure={level==1? this.changeMeasure:null}
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
                  <Chart chartData={Object.assign(charts.sector, {'measureType': this.state.measureType})}
                  measure={fundingType} 
                  chartType='pie'
                  width={popupMaxWidth}
                  height={popupMaxHeight/1.6}
                  showTotalHeader={true}
                  onChangeMeasure={level==1? this.changeMeasure:null}
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
    },
    onLoadLocationStats: (filters) => {
      dispatch(fetchLocationStats(filters));
    }
  }
}

const mapStateToProps = (state, props) => {
  return {
    fundingType: state.settings.fundingType,
    charts: state.popup,
    filters: state.filters.filterMain,
    projectSearch: state.projectSearch,
    project: state.project,
    language: state.language,
    stats: state.stats
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(ProjectLayerPopup);;

