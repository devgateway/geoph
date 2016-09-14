import React from 'react';
import { Link  } from 'react-router';
import {Tabs,Tab} from 'react-bootstrap';
import { connect } from 'react-redux'
import { togglePanelExpand } from '../../actions/panel';
import translate from '../../util/translate';
import Stats from './stats';
import {Tooltip, OverlayTrigger} from 'react-bootstrap';
require("./panel.scss");

class Panel extends React.Component {

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
          <Link to="/map/tools" >
            <OverlayTrigger delayShow={1000} placement="top" overlay={(<Tooltip id="help.toolview.toolviewtab">{translate('help.toolview.toolviewtab')}</Tooltip>)}>
              <li id='tools-tab' className={(this.props.currentView!='/map/charts')?"panel-tab active":"panel-tab"}>
                <div onClick={this.togglePanel.bind(this)}>
                  <div className="icon tools"/>
                  <span>{translate('toolview.title')}</span>                
                </div>
              </li>
            </OverlayTrigger>
          </Link>
          <Link to="/map/charts">
            <OverlayTrigger delayShow={1000} placement="top" overlay={(<Tooltip id="help.chartview.chartviewtab">{translate('help.chartview.chartviewtab')}</Tooltip>)}>
              <li id='charts-tab' className={(this.props.currentView=='/map/charts')?"panel-tab active":"panel-tab"}>
                <div className="icon chart"/>
                <span>{translate('chartview.title')}</span>
              </li>
            </OverlayTrigger>
          </Link>
        </ul>
        <Stats/>
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



