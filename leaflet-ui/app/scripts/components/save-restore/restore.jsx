import React from 'react';
import { connect } from 'react-redux';
import {requestRestoreMap}  from '../../actions/saveAndRestoreMap';


class Restore extends React.Component {

  constructor() {
    super()
  }

  componentDidMount() {     
    let key = this.props.routeParams.key;
    this.props.onRequestRestoreMap(key);
  }

  render() {
    return (
        <div className="chart-view">
            <p>Map ID: {this.props.routeParams.key}</p>
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