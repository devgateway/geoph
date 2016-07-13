import React from 'react';
import DefaultLayout from './defaultLayout.jsx';
import { connect } from 'react-redux'
import {getList} from '../../actions/indicators.js'
import {loadDefaultLayer} from '../../actions/map.js'

require("./root.scss");

export default class Root extends DefaultLayout {

  constructor() {
    super();
  }

  componentWillMount() {
     this.props.onLoadIndicatorList(); 
     this.props.onLoadDefaultLayer(); 
  }

}



const mapDispatchToProps=(dispatch,ownProps)=>{
  return {
    onLoadIndicatorList:()=>{dispatch(getList())},
    onLoadDefaultLayer:()=>{dispatch(loadDefaultLayer())},  
  }
}

const stateToProps = (state,props) => { 
  return {
    
  };
}

export default connect(stateToProps,mapDispatchToProps)(Root);
