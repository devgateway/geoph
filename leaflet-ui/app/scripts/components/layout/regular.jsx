import React from 'react';
import DefaultLayout from './defaultLayout.jsx';
import { connect } from 'react-redux'
import {getList} from '../../actions/indicators.js'
import {loadDefaultLayer} from '../../actions/map.js'
import {loadAllFilterLists} from '../../actions/filters.js'
import {fetchStats} from '../../actions/stats.js'

require("./root.scss");

export default class Root extends DefaultLayout {

  constructor() {
    super();
  }

  componentWillMount() {
    this.props.onLoadAllFilters();
    this.props.onLoadIndicatorList(); 
    this.props.onLoadDefaultLayer();
    this.props.onFetchStats();  
  }

}

const mapDispatchToProps=(dispatch,ownProps)=>{
  return {
    onLoadAllFilters:()=>{dispatch(loadAllFilterLists())},
    onLoadIndicatorList:()=>{dispatch(getList())},
    onLoadDefaultLayer:()=>{dispatch(loadDefaultLayer())},
    onFetchStats :_=>{dispatch(fetchStats())}
  }
}

const stateToProps = (state,props) => { 
  return {
    
  };
}

export default connect(stateToProps,mapDispatchToProps)(Root);
