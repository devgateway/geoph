
import React from 'react';
import {Tabs, Tab, Button, Label} from 'react-bootstrap';
import { connect } from 'react-redux'
import Chart from '../../charts/chartComponent'
import { fetchPopupChartData } from '../../../actions/charts.js'

require('./projectLayerPopup.scss');

export default class ProjectLayerPopup extends React.Component {

  constructor() {
    super();
    this.state = {'tabSelected': 0};
  }

  componentDidMount() {
    //this.props.onLoadChartData(this.props.filters);    
  }

  changeTab(tabSelected){
    this.setState({'tabSelected': tabSelected});
  }

  render() {
    let charts = this.props.charts || {}
    return (
      <div className="popup-container">
        <div className="popup-title">
          <h2>{(this.props.properties? this.props.properties.name : "") + (this.props.fundingType? " ("+ this.props.fundingType.type + " " + this.props.fundingType.measure +")" : "")} </h2>
        </div>
        <div className="">
          <ul className='popup-tabs' role='tablist' >
            <li className={this.state.tabSelected==0? 'active' : ''} role='tab' >
              <div onClick={this.changeTab.bind(this, 0)}>
                <span>Funding Agency</span>
              </div>
            </li>
            <li className={this.state.tabSelected==1? 'active' : ''} role='tab' >
              <div onClick={this.changeTab.bind(this, 1)}>
                <span>Implementing Agency</span>
              </div>
            </li>
            <li className={this.state.tabSelected==2? 'active' : ''} role='tab' >
              <div onClick={this.changeTab.bind(this, 2)}>
                <span>Physical Status</span>
              </div>
            </li>
            <li className={this.state.tabSelected==3? 'active' : ''} role='tab' >
              <div onClick={this.changeTab.bind(this, 3)}>
                <span>Sector</span>
              </div>
            </li>
            <li className={this.state.tabSelected==4? 'active' : ''} role='tab' >
              <div onClick={this.changeTab.bind(this, 4)}>
                <span>Project List</span>
              </div>
            </li>
          </ul>
        </div>
        {this.state.tabSelected==0?
          <div className="popup-tab-content">
            {charts.fundingAgency?
              <div className="">
                <Chart chartData={charts.fundingAgency.data}
                measure={this.props.fundingType} 
                chartType='pie'
                width='400'
                height='200'
                showMeasureSelector={false}
                dimension="name"/>
              </div>
            :null}
          </div>
        :null}
        {this.state.tabSelected==1?
          <div className="popup-tab-content">
            {charts.implementingAgency?
              <div className="">
                <Chart chartData={charts.implementingAgency.data}
                measure={this.props.fundingType} 
                chartType='pie'
                width='400'
                height='200'
                showMeasureSelector={false}
                dimension="name"/>
              </div>
            :null}
          </div>
        :null}
        {this.state.tabSelected==2?
          <div className="popup-tab-content">
            {charts.physicalStatus?
              <div className="">
                <Chart chartData={charts.physicalStatus.data}
                measure={this.props.fundingType} 
                chartType='pie'
                width='400'
                height='200'
                showMeasureSelector={false}
                dimension="name"/>
              </div>
            :null}
          </div>
        :null}
        {this.state.tabSelected==3?
          <div className="popup-tab-content">
            {charts.sector?
              <div className="">
                <Chart chartData={charts.sector.data}
                measure={this.props.fundingType} 
                chartType='pie'
                width='400'
                height='200'
                showMeasureSelector={false}
                dimension="name"/>
              </div>
            :null}
          </div>
        :null} 
        {this.state.tabSelected==4?
          <div className="popup-tab-content">
            <div className="">
              Project List
            </div>
          </div>
        :null}
      </div>
    )
  }
}



