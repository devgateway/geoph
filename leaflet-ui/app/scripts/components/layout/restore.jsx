import React from 'react';
import DefaultLayout from './defaultLayout';
import { connect } from 'react-redux';
require("./root.scss");
import {requestRestoreMap}  from '../../actions/saveAndRestoreMap';

export default class App extends DefaultLayout {

  constructor() {
    super();
  }

  componentWillMount() {
    let key = this.props.routeParams.key;
    this.props.onRequestRestoreMap(key);
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

export default connect(mapStateToProps, mapDispatchToProps)(App);;

