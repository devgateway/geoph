import React from 'react';
import { Link } from 'react-router';
import Header from './header.jsx';
import Footer from './footer.jsx';
import Panel  from './panel.jsx';
import Landing  from './landing.jsx';
import Dashboard  from '../dashboard/dashboard.jsx';
import Menu from '../menu/default.jsx';
import AdminMenu from '../admin/menu.jsx';

import { connect } from 'react-redux'

require("./root.scss");

class DefaultLayout extends React.Component {

	constructor() {
		super();
	}



	render() {

		const {loggedIn}=this.props;

		var newChildren = React.Children.map(this.props.children, child=>React.cloneElement(child, {loggedIn}));

		return (
			<div className="root">
			<Header>
			<Menu title="Executive Dashboards">
			{loggedIn?<AdminMenu/>:null}
			</Menu>
			</Header>
			<div className="dashboard">
			{newChildren}
			</div>
			</div>
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
	const {security} = state;
	const {accountNonExpired, accountNonLocked,enabled , credentialsNonExpired} = security.toObject();
	const loggedIn=accountNonExpired && accountNonLocked&& enabled && credentialsNonExpired;
	return {loggedIn}
}

export default connect(mapStateToProps, mapDispatchToProps)(DefaultLayout);;

