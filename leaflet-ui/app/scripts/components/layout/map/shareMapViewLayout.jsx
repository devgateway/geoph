import React from 'react';
import { connect } from 'react-redux'
import {requestRestoreMap}  from '../../../actions/saveAndRestoreMap';

import DefaultMapLayout from './defaultMapLayout';
import Map from '../../map/map';
import Panel from '../panel';

require("./mapLayout.scss");

class ShareMapViewLayout extends DefaultMapLayout {
  componentWillMount() {
    let key = this.props.routeParams.key;
    this.props.onRequestRestoreMap(key);
  }
  
  render() {
    return (<div className="map-layout">
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
    onRequestRestoreMap: (mapToRestore) => dispatch(requestRestoreMap(mapToRestore))
  }
};

const mapStateToProps = (state, props) => {
  return {
    language: state.language,
    title: state.saveMap.get("name"),
    type: state.saveMap.get("type")
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(ShareMapViewLayout);
