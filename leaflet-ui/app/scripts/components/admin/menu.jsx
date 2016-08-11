import React from 'react'
import { connect } from 'react-redux'
import {login} from '../../actions/security'
import {Map} from 'immutable'
import { Link } from 'react-router'
import Login  from './login.jsx'
import {Messages,Errors} from '../messages/messages.jsx'

require('./admin.scss')

class AdminMenu extends React.Component {
	render(){
		return (
				<ul className="options">
					<li className={(this.props.currentView=='admin/list/indicator')?"active":""}> <Link to="admin/list/indicator"> Indicators</Link></li>
					<li className={(this.props.currentView=='admin/add/indicator')?"active":""}> <Link to="admin/add/indicator">New Indicator</Link></li>
					<li className={(this.props.currentView=='dashboard')?"active":""}> <Link to="dashboard">Dashboard</Link></li>
					<li className={(this.props.currentView=='dashboard')?"active":""}> <Link to="map">Map</Link></li>
				</ul>
			)
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
	debugger;
	const {security} = state;
	
	return {...security.toObject(),...props}
}

export default connect(mapStateToProps, mapDispatchToProps)(AdminMenu);;



