import React from 'react';
import { Link  } from 'react-router';
import {Tabs,Tab} from 'react-bootstrap';
import { connect } from 'react-redux'
import { togglePanelExpand } from '../../actions/panel';
require("./panel.scss");

export default class Panel extends React.Component {

  constructor() {
    super();
  }

  togglePanel(){
    if (this.props.panel.expanded){
      this.props.onTogglePanel();
    }
  }

  render() {
    return (

      <div className={this.props.panel.expanded? "panel panel-expanded" : "panel"}>
        <ul>
          <li className={(this.props.currentView=='/tools')?"panel-tab active":"panel-tab"}>
            <div onClick={this.togglePanel.bind(this)}>
              <Link to="/tools" >             
                <div className="icon tools"/>
                <span>Tool View</span>
              </Link>
            </div>
          </li>
          <li className={(this.props.currentView=='/charts')?"panel-tab active":"panel-tab"}>
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
  return {
    currentView: state.routing.locationBeforeTransitions.pathname,
    panel: state.panel
  }
}


const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onTogglePanel: () => {
      dispatch(togglePanelExpand());
    }
  }
}

export default connect(mapStateToProps,mapDispatchToProps)(Panel);;



