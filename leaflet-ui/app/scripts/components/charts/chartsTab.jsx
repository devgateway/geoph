import React from 'react';
import { connect } from 'react-redux';
import Chart from '../charts/chartComponent';
import { fetchChartData, changeItemsToShow, changeMeasureType, changeChartType } from '../../actions/charts';
import { togglePanelExpand } from '../../actions/panel';
import {collectValues} from '../../util/filterUtil';
import { Button } from 'react-bootstrap';
import translate from '../../util/translate.js';
import {Tooltip, OverlayTrigger} from 'react-bootstrap';

class Charts extends React.Component {

  constructor() {
    super();
  }

  componentDidMount() {
    let filters = collectValues(this.props.filters, this.props.projectSearch);
    //this.props.onLoadChartData(filters);    
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
    const {fundingAgency, implementingAgency, physicalStatus, sector} = charts;
    const {fundingType, panel} = this.props;
    let helpKeyExpand = panel.expanded? "help.chartview.collapse" : "help.chartview.expand";
    return (
      <div className="chart-view">
        <OverlayTrigger placement="left" overlay={(<Tooltip id={helpKeyExpand}>{translate(helpKeyExpand)}</Tooltip>)}>
          <div className="expand-button" onClick={this.togglePanel.bind(this)}>
            <div className={panel.expanded? "chart-collapse-icon" : "chart-expand-icon"}/>
            <div className="expand-button-legend">
              {panel.expanded? translate('chartview.collapsepanel') : translate('chartview.expandpanel')}
            </div>
          </div> 
        </OverlayTrigger>
        <div className="charts-container">
          <Chart chartData={fundingAgency || {}}
            title={translate('chartview.fundingagency')}
            helpKey="help.chartview.financinginstitution"
            chart='fundingAgency'
            measure={fundingType} 
            onChangeItemToShow={this.changeItemToShow.bind(this)}
            onChangeMeasure={this.changeMeasure.bind(this)}
            onChangeType={this.changeType.bind(this)}
            dimension="name"/>
          <Chart chartData={implementingAgency || {}}
            title={translate('chartview.implementingagency')} 
            helpKey="help.chartview.implementingagency"
            chart='implementingAgency'
            measure={fundingType} 
            onChangeItemToShow={this.changeItemToShow.bind(this)}
            onChangeMeasure={this.changeMeasure.bind(this)}
            onChangeType={this.changeType.bind(this)}
            dimension="name"/>
          <Chart chartData={physicalStatus || {}}
            title={translate('chartview.physicalstatus')}
            helpKey="help.chartview.physicalstatus"
            chart='physicalStatus'
            measure={fundingType} 
            onChangeItemToShow={this.changeItemToShow.bind(this)}
            onChangeMeasure={this.changeMeasure.bind(this)}
            onChangeType={this.changeType.bind(this)}
            dimension="name"/>
          <Chart chartData={sector || {}}
            title={translate('chartview.sector')}
            helpKey="help.chartview.sector"
            chart='sector'
            measure={fundingType} 
            onChangeItemToShow={this.changeItemToShow.bind(this)}
            onChangeMeasure={this.changeMeasure.bind(this)}
            onChangeType={this.changeType.bind(this)}
            dimension="name"/>
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



