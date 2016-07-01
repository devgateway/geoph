import React from 'react'
import { connect } from 'react-redux'
import {login} from '../../actions/security'
import {Map} from 'immutable'
import { Link } from 'react-router'
import Login  from './login.jsx'
import {Messages,Errors} from '../messages/messages.jsx'

require('./admin.scss')

class AdminIndex extends React.Component {
	validate(){
		//TODO:validate inputs
	}

	render(){
		
		const {location} = this.props;
		
		const {state} = location;

		const {messages,errors} = state || {}; 
		
		
		return (
			<div id="admin-index">
			<ul className="menu">
			<li className={(this.props.currentView=='admin/list/indicator')?"active":""}> <Link to="admin/list/indicator"> Indicators</Link></li>
			<li className={(this.props.currentView=='admin/add/indicator')?"active":""}> <Link to="admin/add/indicator">New Indicators</Link></li>
			</ul>
			<div className="clearFix"></div>
				{messages?<Messages messages={messages}/>:null}

				{errors?<div><h2>Plase review errors below</h2> <Errors errors={errors}/></div>:null}
				{this.props.children}
			</div>
			)
	}
}


class AdminView extends React.Component {

	constructor() {
		super();
	}

	render() {
		const logged=this.props.accountNonExpired && this.props.accountNonLocked&& this.props.enabled && this.props.credentialsNonExpired;
		let view=null;
		if (logged){
			view=<AdminIndex {...this.props}> {this.props.children}</AdminIndex>
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
}

const mapStateToProps = (state, props) => {
	
	const {security} = state;
	
	return {...security.toObject()}
}

export default connect(mapStateToProps, mapDispatchToProps)(AdminView);;



