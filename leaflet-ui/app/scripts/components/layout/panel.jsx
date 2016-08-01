import React from 'react';
import { Link  } from 'react-router';
import {Tabs,Tab} from 'react-bootstrap';
import { connect } from 'react-redux'
import { togglePanelExpand } from '../../actions/panel';
import translate from '../../util/translate.js';
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
          <li className={(this.props.currentView=='/map' || this.props.currentView=='/map/tools')?"panel-tab active":"panel-tab"}>
            <div onClick={this.togglePanel.bind(this)}>
              <Link to="map/tools" >             
                <div className="icon tools"/>
                <span>{translate('toolview.title')}</span>
              </Link>
            </div>
          </li>
          <li className={(this.props.currentView=='/map/charts')?"panel-tab active":"panel-tab"}>
            <Link to="map/charts">
              <div className="icon chart"/>
              <span>{translate('chartview.title')}</span>
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
    panel: state.panel,
    language: state.language
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



