import React from 'react';
import DefaultLayout from './defaultLayout';
import { connect } from 'react-redux'

require("./root.scss");

export default class App extends DefaultLayout {

  constructor() {
    super();
  }

  componentWillMount() {
       const {onLoad}=this.props;
       debugger;
       onLoad(); 
  }

}



const mapDispatchToProps=(dispatch,ownProps)=>{
  return {
    onLoad:()=>{dispatch(requestRestoreMap())},
    
  }
}

const stateToProps = (state,props) => { 
  return {
    		
  };
}

export default connect(stateToProps,mapDispatchToProps)(App);




//
//
//
//