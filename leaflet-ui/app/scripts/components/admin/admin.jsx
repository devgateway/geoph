import React from 'react'
import { connect } from 'react-redux'
import {login} from '../../actions/security'
import {Map} from 'immutable'
import { Link } from 'react-router'
import Login  from './login.jsx'
require('./admin.scss')


class Error extends React.Component{
	render(){

		return (<div className="error">
			{this.props.error==401?<p>Bad Credentials</p>:<p>There was an error please try again</p>}
		</div>)
	}
}

class AdminIndex extends React.Component {
	validate(){
		//TODO:validate inputs
	}

	render(){
		console.log(this.props.currentView)
		return (
			<div id="admin-index">
				<ul className="menu">
					<li className={(this.props.currentView=='/admin/indicators')?"active":""}> <Link to="admin/indicators">New Indicator</Link></li>
					<li className={(this.props.currentView=='/admin/indicators')?"active":""}> <Link to="admin/indicators">Load Photos</Link></li>
					<li className={(this.props.currentView=='/admin/indicators')?"active":""}> <Link to="admin/indicators">Import Data</Link></li>
				</ul>
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
		let view;
		if (logged){
			view=<AdminIndex {...this.props}> {this.props.children}</AdminIndex>
		}else{
			view =<Login {...this.props}>{this.props.error?<Error error={this.props.error}/>:null}</Login>
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



