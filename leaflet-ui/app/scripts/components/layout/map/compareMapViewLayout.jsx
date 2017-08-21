import React from 'react';
import { connect } from 'react-redux';
import { hashHistory } from 'react-router';

import { clone, clean }  from '../../../reducers/compare';
import { copyCompareMap }  from '../../../reducers/map';
import { copyCompareFilters } from '../../../reducers/filters';
import * as Constants from '../../../constants/constants';

import DefaultMapLayout from './defaultMapLayout';
import Map from '../../map/map';
import Panel from '../panel';

import './compare.scss';

class CompareMapViewLayout extends DefaultMapLayout {
  componentWillMount() {
    this.props.onMount();
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
      }
      this.props.onDeactivate("compare");
      hashHistory.push('/map');
    }
  }
  
  render() {
    return (<div>
        <div className="compare-layout">
          <div className="main">
            <Map mapId="left"/>
            <div className="close-map" onClick={this.closeCompareView.bind(this)}></div>
          </div>
          
          <div className="main right-map">
            <Map mapId="main"/>
            <div className="close-map" onClick={this.closeCompareView.bind(this, true)}></div>
          </div>
        </div>
        
        <Panel showHelp={this.showHelp}>
          {this.props.children}
        </Panel>
      </div>
    )
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onMount:            () => dispatch(clone()),
    onUnMount:          () => dispatch(clean()),
    copyCompareMap:     () => dispatch(copyCompareMap()),
    copyCompareFilters: () => dispatch(copyCompareFilters()),
    onDeactivate:    (key) => dispatch({type: Constants.DEACTIVATE_COMPONENT, key})
  }
};

const mapStateToProps = (state, props) => {
  return {
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(CompareMapViewLayout);
