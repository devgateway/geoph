import React from 'react';
import { connect } from 'react-redux';
import { hashHistory } from 'react-router';

import { clone, clean }  from '../../../reducers/compare';
import { copyCompare }  from '../../../reducers/map';
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
  
  closeCompareView() {
    this.props.onDeactivate("compare");
    this.props.copyCompare();
    hashHistory.push('/map');
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
            <div className="close-map" onClick={this.closeCompareView.bind(this)}></div>
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
    onMount:      () => dispatch(clone()),
    onUnMount:    () => dispatch(clean()),
    copyCompare:  () => dispatch(copyCompare()),
    onDeactivate: (key) => dispatch({type: Constants.DEACTIVATE_COMPONENT, key})
  }
};

const mapStateToProps = (state, props) => {
  return {
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(CompareMapViewLayout);
