import React from 'react';
import { connect } from 'react-redux';
import { hashHistory } from 'react-router';

import DefaultMapLayout from './defaultMapLayout';
import Map from '../../map/map';
import Panel from '../panel';

import './compare.scss';

class CompareMapViewLayout extends DefaultMapLayout {
  render() {
    console.log(">>>> CompareMapViewLayout");
    
    return (<div>
        <div className="compare-layout">
          <div className="main">
            <Map id="left"/>
            <div className="close-map" onClick={e => hashHistory.push('/map')}></div>
          </div>
          
          <div className="main right-map">
            <Map id="main"/>
            <div className="close-map" onClick={e => hashHistory.push('/map')}></div>
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
  
  }
};

const stateToProps = (state, props) => {
  return {
  }
};

export default connect(stateToProps, mapDispatchToProps)(CompareMapViewLayout);
