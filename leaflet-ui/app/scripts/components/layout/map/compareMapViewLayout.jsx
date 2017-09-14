import React from 'react';
import { connect } from 'react-redux';
import { hashHistory } from 'react-router';

import { clone, clean }  from '../../../reducers/compare';
import { copyCompareMap }  from '../../../reducers/map';
import { copyCompareFilters } from '../../../reducers/filters';
import { copyCompareSettings } from '../../../actions/settings';
import { copyCompareProjectSearch } from '../../../actions/projectSearch';
import * as Constants from '../../../constants/constants';

import DefaultMapLayout from './defaultMapLayout';
import Map from '../../map/map';
import Panel from '../panel';

require("./mapLayout.scss");

class CompareMapViewLayout extends DefaultMapLayout {
  componentWillMount() {
    const { isPanelVisible, onTogglePanel } = this.props;
    
    this.props.onMount();
    
    if (isPanelVisible) {
      onTogglePanel();
    }
  
    this.props.onActivate("compare");    // be sure that the compare component is active
  }
  
  componentWillUnmount() {
    this.props.onUnMount();
  }
  
  closeCompareView(copyCompare) {
    if (confirm("Are you sure you want to close this map?") === true) {
      // copy the compare map on top of the main map
      if (copyCompare === true) {
        this.props.copyCompareMap();
        this.props.copyCompareFilters();
        this.props.copyCompareSettings();
        this.props.copyCompareProjectSearch();
      }
      this.props.onDeactivate("compare");
      hashHistory.push('/map');
    }
  }
  
  render() {
    return (<div className="map-layout compare-layout">
        <div className="main">
          <Map mapId="left"/>
          <div className="close-map" onClick={this.closeCompareView.bind(this)}></div>
        </div>
        
        <div className="main">
          <Map mapId="main"/>
          <div className="close-map" onClick={this.closeCompareView.bind(this, true)}></div>
        </div>
        
        <Panel showHelp={this.showHelp} compareClass={"panel-compare"}>
          {this.props.children}
        </Panel>
      </div>
    )
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onMount:                  () => dispatch(clone()),
    onUnMount:                () => dispatch(clean()),
    copyCompareMap:           () => dispatch(copyCompareMap()),
    copyCompareFilters:       () => dispatch(copyCompareFilters()),
    copyCompareSettings:      () => dispatch(copyCompareSettings()),
    copyCompareProjectSearch: () => dispatch(copyCompareProjectSearch()),
    onActivate:            (key) => dispatch({type: Constants.ACTIVATE_COMPONENT, key}),
    onDeactivate:          (key) => dispatch({type: Constants.DEACTIVATE_COMPONENT, key}),
    onTogglePanel:            () => dispatch({type: Constants.TOGGLE_PANEL}),
  }
};

const mapStateToProps = (state, props) => {
  return {
    isPanelVisible: state.panel.get('visible')
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(CompareMapViewLayout);
