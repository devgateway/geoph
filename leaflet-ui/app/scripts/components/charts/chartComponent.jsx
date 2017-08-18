import React from 'react';
import { Button } from 'react-bootstrap';
import * as Constants from '../../constants/constants';
import { parseDataChart } from '../../util/chartUtil';
import { formatValue } from '../../util/format.js';
import translate from '../../util/translate';
import { Tooltip, OverlayTrigger } from 'react-bootstrap';

import createPlotlyComponent from 'react-plotlyjs';
// only load the basic plotly bundle
import Plotly from 'plotly.js/dist/plotly-basic';

const PlotlyComponent = createPlotlyComponent(Plotly);

require("./charts.scss");

export default class ChartComponent extends React.Component {
  
  constructor() {
    super();
    this.state = {'chartType': 'bar', 'measType': 'funding', 'hiddenlabels': [], 'shouldRedrawChart': false};
  }
  
  setChartType(type) {
    this.props.onChangeType(this.props.chart, type);
  }
  
  setMeasType(type) {
    this.props.onChangeMeasure(this.props.chart, type);
  }
  
  setItemsToShow(value) {
    let val = this.props.chartData.itemsToShow;
    if (value == "less") {
      if (this.props.chartData.itemsToShow > Constants.CHART_ITEMS_STEP_AMOUNT) {
        val = this.props.chartData.itemsToShow - Constants.CHART_ITEMS_STEP_AMOUNT;
      }
    } else {
      if (this.props.chartData.itemsToShow < this.props.chartData.data.length) {
        val = this.props.chartData.itemsToShow + Constants.CHART_ITEMS_STEP_AMOUNT;
      }
    }
    this.props.onChangeItemToShow(this.props.chart, val);
  }
  
  handleResize(e) {
    this.forceUpdate();
  }
  
  componentWillReceiveProps(nextProps) {
    let data = this.props.chartData;
    let newData = nextProps.chartData;
    //force redraw chart only when amount of items to show has changed
    if (data.itemsToShow && (data.itemsToShow != newData.itemsToShow)) {
      this.setState({shouldRedrawChart: true});
    } else {
      this.setState({shouldRedrawChart: false});
    }
  }
  
  componentDidMount() {
    window.addEventListener('resize', this.handleResize.bind(this));
  }
  
  componentWillUnmount() {
    window.removeEventListener('resize', this.handleResize.bind(this));
  }
  
  onChartClick(evt) {
    if (evt.currentTarget && evt.currentTarget.layout.hiddenlabels.length != this.state.hiddenlabels.length) {
      this.setState({'hiddenlabels': evt.currentTarget.layout.hiddenlabels});
    }
  }
  
  hasValuesToShow(chartData) {
    if (chartData.data[0].type == 'bar') {
      return chartData.data[0].x && chartData.data[0].x.length > 0
    } else {
      return chartData.data[0].values && chartData.data[0].values.length > 0
    }
  }
  
  getChartInfo(chartType) {
    let properties = Object.assign({}, this.props, {hiddenlabels: this.state.hiddenlabels});//add hidden labels to props
    return parseDataChart(chartType, properties, this.refs.chartContainer);
  }
  
  render() {
    const {measure, chartData, chartType, helpKey} = this.props;
    const {chartType: chType, itemsToShow, measureType} = chartData;
    let chartInfo;
    let key = this.state.shouldRedrawChart ? new Date().getTime() : null;
    if (chartType) {
      chartInfo = this.getChartInfo(chartType);
    } else {
      chartInfo = this.getChartInfo(chartData.chartType);
    }
    return (
      <div className="chart" ref="chartContainer">
        {this.props.title ?
          <div className="chart-title">
            <div className="chart-title-text">
              <OverlayTrigger delayShow={1000} placement="top"
                              overlay={(<Tooltip id={helpKey}>{translate(helpKey)}</Tooltip>)}>
                <div className="title">
                  {this.props.title || ""}
                </div>
              </OverlayTrigger>
              <div className="subtitle">
                {measure ? translate('header.settings.' + measure.type) + " " + translate('header.settings.' + measure.measure) : ""}
              </div>
            </div>
          </div>
          : null}
        {this.props.onChangeItemToShow ?
          <div className="chart-items-selector">
            <OverlayTrigger delayShow={1000} placement="top"
                            overlay={(<Tooltip id="help.chartview.less">{translate('help.chartview.less')}</Tooltip>)}>
              <Button disabled={itemsToShow > Constants.CHART_ITEMS_STEP_AMOUNT ? false : true}
                      onClick={this.setItemsToShow.bind(this, "less")}>
                <span>{"<"}</span><span className="less-items">{translate('chartview.less')}</span>
              </Button>
            </OverlayTrigger>
            <OverlayTrigger delayShow={1000} placement="top"
                            overlay={(<Tooltip id="help.chartview.more">{translate('help.chartview.more')}</Tooltip>)}>
              <Button disabled={chartData.data && (itemsToShow < chartData.data.length) ? false : true}
                      onClick={this.setItemsToShow.bind(this, "more")}>
                <span className="more-items">{translate('chartview.more')}</span><span>{">"}</span>
              </Button>
            </OverlayTrigger>
          </div>
          : null}
        {this.props.onChangeType ?
          <div className="chart-type-selector">
            <div className="toggle-button-pair">
              <OverlayTrigger delayShow={1000} placement="top" overlay={(
                <Tooltip id="help.chartview.barchart">{translate('help.chartview.barchart')}</Tooltip>)}>
                <div className={chType === 'bar' ? "active" : ""} onClick={this.setChartType.bind(this, 'bar')}>
                  <div className={chType === 'bar' ? "chart-bar-icon" : "chart-bar-icon-disabled"}></div>
                </div>
              </OverlayTrigger>
              <OverlayTrigger delayShow={1000} placement="top" overlay={(
                <Tooltip id="help.chartview.piechart">{translate('help.chartview.piechart')}</Tooltip>)}>
                <div className={chType === 'pie' ? "active" : ""} onClick={this.setChartType.bind(this, 'pie')}>
                  <div className={chType === 'pie' ? "chart-pie-icon" : "chart-pie-icon-disabled"}></div>
                </div>
              </OverlayTrigger>
            </div>
          </div>
          : null}
        {this.props.onChangeMeasure ?
          <div className="chart-measure-selector">
            <div className="toggle-button-pair">
              <OverlayTrigger delayShow={1000} placement="top" overlay={(
                <Tooltip id="help.chartview.funding">{translate('help.chartview.funding')}</Tooltip>)}>
                <div className={measureType === 'funding' ? "active" : ""}
                     onClick={this.setMeasType.bind(this, 'funding')}>
                  <div
                    className={measureType === 'funding' ? "chart-funding-icon" : "chart-funding-icon-disabled"}></div>
                </div>
              </OverlayTrigger>
              <OverlayTrigger delayShow={1000} placement="top" overlay={(
                <Tooltip id="help.chartview.projectcount">{translate('help.chartview.projectcount')}</Tooltip>)}>
                <div className={measureType === 'projectCount' ? "active" : ""}
                     onClick={this.setMeasType.bind(this, 'projectCount')}>
                  <div
                    className={measureType === 'projectCount' ? "chart-projects-icon" : "chart-projects-icon-disabled"}></div>
                </div>
              </OverlayTrigger>
            </div>
          </div>
          : null}
        {this.props.showTotalHeader ?
          <div className="total-funding-chart">{translate('infowindow.tab.totalamount')}:
            <div>{(measureType == 'funding' ? "₱" : "") + formatValue(chartInfo.totalAmount)}</div>
          </div>
          : null}
        {!this.hasValuesToShow(chartInfo) ?
          <div className="no-data">
            {translate('chartview.nodata')}
          </div>
          :
          <div>
            <PlotlyComponent key={key} onClick={this.onChartClick.bind(this)} className="" data={chartInfo.data}
                             layout={chartInfo.layout} config={chartInfo.config}/>
          </div>
        }
      
      </div>
    );
  }
}

