import React from 'react';
import { connect } from 'react-redux';

import DefaultMapLayout from './defaultMapLayout';
import Map from '../../map/map';
import Panel from '../panel';

class CompareMapViewLayout extends DefaultMapLayout {
  render() {
    console.log(">>>> CompareMapViewLayout");
    
    return (<div>
        <div className="landing">
          <div className="main">
            <Map/>
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
