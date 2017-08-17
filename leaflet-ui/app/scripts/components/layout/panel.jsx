import React from 'react';
import { Link } from 'react-router';
import { connect } from 'react-redux'
import { togglePanelExpand } from '../../actions/panel';
import translate from '../../util/translate';
import Stats from './stats';
import { Tooltip, OverlayTrigger } from 'react-bootstrap';

require("./panel.scss");

class Panel extends React.Component {
  togglePanel() {
    if (this.props.expanded) {
      this.props.onTogglePanel();
    }
  }
  
  buildPath(url) {
    let pathArray = this.props.currentView.split('/');
    
    let key = pathArray[pathArray.indexOf("map") + 1];
    if (!key || key == "tools" || key == "charts") {
      return `/map${url}`;
    } else {
      return `/map/${key}${url}`;
    }
  }
  
  getTabClass(tab, def) {
    const { currentView } = this.props;
    let currentTab = currentView.split('/').pop();
    if (currentTab === tab || (currentTab !== "charts" && currentTab !== "tools" && def)) {
      return "panel-tab active"
    } else {
      return "panel-tab"
    }
  }
  
  render() {
    const { expanded, visible, currentView } = this.props;
    const expandedClass = expanded ? 'panel-expanded' : '';
    const visibleClass = visible === true ? 'visible' : 'unseen';
    
    return (
      <div className={`panel ${expandedClass} ${visibleClass}`}>
        <ul>
          <Link to={this.buildPath("/tools")}>
            <OverlayTrigger delayShow={1000} placement="top" overlay={(
              <Tooltip id="help.toolview.toolviewtab">{translate('help.toolview.toolviewtab')}</Tooltip>)}>
              <li id='tools-tab' className={this.getTabClass("tools", true)}>
                <div onClick={this.togglePanel.bind(this)}>
                  <div className="icon tools"/>
                  <span>{translate('toolview.title')}</span>
                </div>
              </li>
            </OverlayTrigger>
          </Link>
          
          <Link to={this.buildPath("/charts")}>
            <OverlayTrigger delayShow={1000} placement="top" overlay={(
              <Tooltip id="help.chartview.chartviewtab">{translate('help.chartview.chartviewtab')}</Tooltip>)}>
              <li id='charts-tab' className={this.getTabClass("charts", false)}>
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
    expanded: state.panel.get('expanded'),
    visible: state.panel.get('visible'),
    language: state.language
  }
};

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onTogglePanel: () => {
      dispatch(togglePanelExpand());
    }
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(Panel);



