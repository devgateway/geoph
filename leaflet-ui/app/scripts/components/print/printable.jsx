import React from 'react';
import translate from '../../util/translate';
import Filters from './printFilters';
import Map from '../map/map.jsx';
import { connect } from 'react-redux'
import {requestRestoreMap}  from '../../actions/saveAndRestoreMap';
require('./printable.scss');

class Printable extends React.Component {

  componentWillMount() {
    let key = this.props.routeParams.key;
    this.props.onRequestRestoreMap(key);
  }

  render() {
    return (
      <div className="printable">
        <div className="print-header">
          <h1>{translate("header.title")}</h1>
        </div>
        <div className="full-map">
          <Map/>
        </div>
        <Filters/>
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

export default connect(mapStateToProps, mapDispatchToProps)(Printable);;
