import React from 'react';
import { connect } from 'react-redux';
import Chart from '../charts/chartComponent';
import { fetchChartData, changeItemsToShow, changeMeasureType, changeChartType } from '../../actions/charts';
import { togglePanelExpand } from '../../actions/panel';
import {collectValues} from '../../util/filterUtil';
import { Button } from 'react-bootstrap';
import translate from '../../util/translate.js';

class Charts extends React.Component {

  constructor() {
    super();
  }

  componentDidMount() {
    let filters = collectValues(this.props.filters, this.props.projectSearch);
    this.props.onLoadChartData(filters);    
  }

  togglePanel(){
    this.props.onTogglePanel();
    this.forceUpdate();
  }

  changeItemToShow(chart, value){
    this.props.onChangeItemsToShow(chart, value);
  }

  changeMeasure(chart, value){
    this.props.onChangeMeasureType(chart, value);
  }

  changeType(chart, value){
    this.props.onChangeChartType(chart, value);
  }

  render() {
    let charts = this.props.charts? this.props.charts : {}
    return (
      <div className="chart-view">
        <div className="charts-container">
          <Chart chartData={charts.fundingAgency || {}}
            title={translate('chartview.fundingagency')}
            chart='fundingAgency'
            measure={this.props.fundingType} 
            onChangeItemToShow={this.changeItemToShow.bind(this)}
            onChangeMeasure={this.changeMeasure.bind(this)}
            onChangeType={this.changeType.bind(this)}
            dimension="name"/>
          <Chart chartData={charts.implementingAgency || {}}
            title={translate('chartview.implementingagency')} 
            chart='implementingAgency'
            measure={this.props.fundingType} 
            onChangeItemToShow={this.changeItemToShow.bind(this)}
            onChangeMeasure={this.changeMeasure.bind(this)}
            onChangeType={this.changeType.bind(this)}
            dimension="name"/>
          <Chart chartData={charts.physicalStatus || {}}
            title={translate('chartview.physicalstatus')}
            chart='physicalStatus'
            measure={this.props.fundingType} 
            onChangeItemToShow={this.changeItemToShow.bind(this)}
            onChangeMeasure={this.changeMeasure.bind(this)}
            onChangeType={this.changeType.bind(this)}
            dimension="name"/>
          <Chart chartData={charts.sector || {}}
            title={translate('chartview.sector')}
            chart='sector'
            measure={this.props.fundingType} 
            onChangeItemToShow={this.changeItemToShow.bind(this)}
            onChangeMeasure={this.changeMeasure.bind(this)}
            onChangeType={this.changeType.bind(this)}
            dimension="name"/>
        </div>
        <div className="expand-button" >
          <div className={this.props.panel.expanded? "expand-button-arrow right" : "expand-button-arrow left"}/>
          <div className="expand-button-inner" onClick={this.togglePanel.bind(this)}>
            {this.props.panel.expanded? translate('chartview.collapsepanel') : translate('chartview.expandpanel')}
          </div>
        </div> 
      </div>
    )
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onLoadChartData: (filters) => {
      dispatch(fetchChartData(filters));
    },
    onTogglePanel: () => {
      dispatch(togglePanelExpand());
    },
    onChangeItemsToShow: (chart, value) => {
      dispatch(changeItemsToShow(chart, value));
    },
    onChangeMeasureType: (chart, value) => {
      dispatch(changeMeasureType(chart, value));
    },
    onChangeChartType: (chart, value) => {
      dispatch(changeChartType(chart, value));
    }
  }
}

const mapStateToProps = (state, props) => {
  return {
    charts: state.charts, 
    language: state.language,
    fundingType: state.settings.fundingType,
    filters: state.filters.filterMain,
    projectsSelected: state.projectSearch,
    panel: state.panel
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Charts);;



