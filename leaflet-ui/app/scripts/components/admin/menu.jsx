import React from 'react';
import {connect} from 'react-redux';
import {login} from '../../actions/security';
import { resetFilter } from '../../actions/filters';
import {Link} from 'react-router';
import {Messages, Errors} from '../messages/messages.jsx';

require('./admin.scss');

class AdminMenu extends React.Component {
  render() {
    const { onFilterReset } = this.props;
    
    return (
      <ul className="options">
        <li className={(this.props.currentView == 'admin/list/indicator') ? "active" : ""}>
          <span className="admin-menu-item"><Link to="admin/list/indicator"> Indicators</Link></span></li>
        
        <li className={(this.props.currentView == 'admin/add/indicator') ? "active" : ""}>
          <span className="admin-menu-item"><Link to="admin/add/indicator">New Indicator</Link></span></li>
        
        <li className={(this.props.currentView == 'dashboard') ? "active" : ""}>
          <span className="admin-menu-item"><Link to="dashboard">Dashboards</Link></span></li>
        
        <li className={(this.props.currentView == 'dashboard') ? "active" : ""} onClick={onFilterReset}>
          <span className="admin-menu-item"><Link to="map">New Dashboard</Link></span></li>
      </ul>
    )
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onLogin: (loginData) => {
      dispatch(login(loginData));
    },
    onFilterReset: () => {
      dispatch(resetFilter());
    }
  }
};

const mapStateToProps = (state, props) => {
  const {security} = state;
  return {...security.toObject(), ...props}
};

export default connect(mapStateToProps, mapDispatchToProps)(AdminMenu);



