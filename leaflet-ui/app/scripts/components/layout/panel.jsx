import React from 'react';
import { Link  } from 'react-router';
import {Tabs,Tab} from 'react-bootstrap';
import { connect } from 'react-redux'
require("./panel.scss");

export default class Panel extends React.Component {

  constructor() {
    super();
  }


  render() {
    return (

      <div className="panel">
        <ul>
          <li className={(this.props.currentView=='/tools')?"active":""}>
              <Link to="/tools">
             
                  <div className="icon tools"/>
                 <span>Tool View</span>
              </Link>
          </li>
          <li className={(this.props.currentView=='/charts')?"active":""}>
            <Link to="/charts">
              <div className="icon chart"/>
              <span>Chart View</span>
              </Link>
          </li>
          </ul>
          
              {this.props.children}
      </div>
      )
  }
}

 
const mapStateToProps = (state, props) => {
      return {currentView:state.routing.locationBeforeTransitions.pathname}
}


const mapDispatchToProps = (dispatch, ownProps) => {
 return {}
}

export default connect(mapStateToProps,mapDispatchToProps)(Panel);;



