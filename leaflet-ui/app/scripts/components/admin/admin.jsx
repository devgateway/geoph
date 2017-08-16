import React from 'react'
import { connect } from 'react-redux'
import {login} from '../../actions/security'
import Login  from './login.jsx'
import {Messages} from '../messages/messages.jsx'

require('./admin.scss');

class AdminView extends React.Component {
  constructor() {
    super();
  }
  
  render() {
    
    const logged=this.props.accountNonExpired && this.props.accountNonLocked&& this.props.enabled && this.props.credentialsNonExpired;
    let view=null;
    if (logged){
      view=<div> {this.props.children}</div>
    }else{
      view =<Login {...this.props}></Login>
    }
    return (<div className="admin-view">{view}</div>)
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onLogin: (loginData) => {
      dispatch(login(loginData));
    }
  }
};

const mapStateToProps = (state, props) => {
  const {security} = state;
  return {...security.toObject()}
};

export default connect(mapStateToProps, mapDispatchToProps)(AdminView);;



