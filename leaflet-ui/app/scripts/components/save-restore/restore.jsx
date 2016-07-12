import React from 'react';
import { connect } from 'react-redux';
import {requestRestoreMap}  from '../../actions/saveAndRestoreMap';


class Restore extends React.Component {

  constructor() {
    super()
  }

  componentDidMount() {     
    let mapId = this.props.routeParams.mapID;
    this.props.onRequestRestoreMap({mapId : mapId});
  }

  render() {
    return (
        <div className="chart-view">
            <p>Map ID: {this.props.routeParams.mapID}</p>
        </div>
    )
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onRequestRestoreMap: (mapToRestore) => {
      dispatch(requestRestoreMap(mapToRestore));
    }
  }
}

const mapStateToProps = (state, props) => {
  return {
    language: state.language
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Restore);;