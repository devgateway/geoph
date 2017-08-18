import React from 'react';
import { connect } from 'react-redux';
import { getList } from '../../../actions/indicators.js';
import { loadDefaultLayer, loadDefaultMapState } from '../../../actions/map.js';
import { loadAllFilterLists } from '../../../actions/filters.js';
import { fetchStats } from '../../../actions/stats.js';
import { fetchChartData } from '../../../actions/charts.js';
import { getGeophotosList } from '../../../actions/geophotos.js';

import DefaultMapLayout from './defaultMapLayout';
import Map from '../../map/map';
import Panel from '../panel';

require("./mapLayout.scss");

class SingleMapViewLayout extends DefaultMapLayout {
  componentWillMount() {
    this.props.onLoadDefaultMapState();
    this.props.onLoadAllFilters();
    this.props.onLoadIndicatorList();
    this.props.onLoadDefaultCharts();
    this.props.onFetchStats();
    this.props.onLoadDefaultLayer();
  }
  
  render() {
    return (<div>
        <div className="main">
          <Map mapId="main"/>
        </div>
        
        <Panel showHelp={this.showHelp}>
          {this.props.children}
        </Panel>
      </div>
    )
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onLoadAllFilters: () => dispatch(loadAllFilterLists()),
    onLoadIndicatorList: () => dispatch(getList()),
    onLoadGeophotosList: () => dispatch(getGeophotosList()),
    onLoadDefaultLayer: () => dispatch(loadDefaultLayer()),
    onLoadDefaultCharts: () => dispatch(fetchChartData()),
    onLoadDefaultMapState: () => dispatch(loadDefaultMapState()),
    onFetchStats: () => dispatch(fetchStats())
  }
};

const stateToProps = (state, props) => {
  return {
    mapId: state.saveMap.get('id')
  }
};

export default connect(stateToProps, mapDispatchToProps)(SingleMapViewLayout);