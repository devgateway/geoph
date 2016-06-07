import React from 'react'
import { connect } from 'react-redux'
import {login} from '../../actions/security'
import {Map} from 'immutable'
import { Link } from 'react-router'

require('./admin.scss')

class Login extends React.Component {

	validate(){
		let username=this.state.data.get('username') || '';
		let password=this.state.data.get('password') || '';
		if((username=='' || username.length < 1) || (password=='' || password.length < 1)) {
			this.setState({data:this.state.data.set('error','Please enter a user name and password')});
		}else{
			this.props.onLogin({username,password}) 	
		}
	}

	constructor(props) {
		super(props);
		this.state = {data:Map()} ;
	}

	handleChangeUsername(event) {
		let data=this.state.data.set("username",event.target.value);
		this.setState({data:data});
	}

	handleChangePassword(event) {
		let data=this.state.data.set("password",event.target.value);
		this.setState({data:data});
	}

	render(){
		return (
			<div>
			<p>Please provide user name and password</p>
				{this.props.children}
				<div>	User      <input  onChange={this.handleChangeUsername.bind(this)} value={this.state.data.get('username')} type="text"></input></div>
			<div>   Password  <input  onChange={this.handleChangePassword.bind(this)} value={this.state.data.get('password')} type="password"></input></div>
			<div> <input type="button" onClick={this.validate.bind(this)} value="Login"></input></div>
			</div>
			)
	}
}


class Admin extends React.Component {

	validate(){
		//TODO:validate inputs
	}

	render(){
		console.log(this.props.currentView)
		return (
			<div id="admin-main">
				<ul className="menu">  <li className={(this.props.currentView=='/admin/indicators')?"active":""}> <Link to="admin/indicators">New Indicator</Link></li></ul>

	          	    {this.props.children}
			</div>
			)
	}
}

class Error extends React.Component{
	render(){

		return (<div className="error">
			{this.props.error==401?<p>Bad Credentials</p>:<p>There was an error please try again</p>}
			</div>)
	}
}


class AdminView extends React.Component {

	constructor() {
		super();
	}

	componentDidMount() {}

	render() {

		const logged=this.props.accountNonExpired && this.props.accountNonLocked&& this.props.enabled && this.props.credentialsNonExpired;
		
		let view;
		if (logged){
			view=<Admin {...this.props}>{this.props.children}</Admin>
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



