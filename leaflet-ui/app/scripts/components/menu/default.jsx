import React from 'react';
import {connect} from 'react-redux';
import * as Constants from '../../constants/constants';
import {LangSwitcher} from '../lan/'
import {showSaveMap} from '../../actions/saveAndRestoreMap';
import {markApplied} from '../../util/filterUtil';
import {hashHistory} from 'react-router';
import MenuItem from './item.jsx';

class MenuBar extends React.Component {
  static propTypes = {
    isCompare:   React.PropTypes.bool,
  };
  
  componentWillUpdate(nextProps) {
    const {compare} = nextProps;
    
    // we need to be sure that the *compare* field has changes in order to launch the compare view
    if (compare !== this.props.compare) {
      if (compare === true) {
        hashHistory.push('/map/compare');
      } else {
        if (compare === false) {
          hashHistory.push('/map');
        }
      }
    }
  }
  
  render() {
    const {loggedin, items = [], title, onTogglePanel, filtersMain, isCompare} = this.props;
    
    return (
      <div className="title">
        <h2><b>{title}</b></h2>
        
        <ul className="options">
          {items.map(item => {
            const Component = item.children;
            const visible = (!item.secure || (item.secure && loggedin));
            if (item.id === 'filters') {
              item.label = 'Filters' + (markApplied(filtersMain));//add the mark for filters applied
            }
            return (visible) ?
              <MenuItem {...this.props} {...item}>{Component !== undefined ? <Component/> : null}</MenuItem> : null;
          })}
          <li className="lang-sm"><LangSwitcher/></li>
          <li className={"last " + (isCompare ? "compare" : "")} onClick={() => onTogglePanel()}></li>
        </ul>
        {this.props.children}
      </div>
    )
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onTogglePanel: () => {
      dispatch({type: Constants.TOGGLE_PANEL})
    },
    onActivate: (key) => {
      dispatch({type: Constants.ACTIVATE_COMPONENT, key})
    },
    onDeactivate: (key) => {
      dispatch({type: Constants.DEACTIVATE_COMPONENT, key})
    }
  }
};

const mapStateToProps = (state, props) => {
  const {accountNonExpired, accountNonLocked, enabled, credentialsNonExpired} = state.security.toJS();
  const loggedin = (accountNonExpired && accountNonLocked && enabled && credentialsNonExpired);
  const isCompare = state.compare.size !== 0;
  
  return {...state.header.toJS(), loggedin, filtersMain: state.filters.filterMain, language: state.language, isCompare}
};

export default connect(mapStateToProps, mapDispatchToProps)(MenuBar);