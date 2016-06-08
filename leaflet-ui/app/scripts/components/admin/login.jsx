import React from 'react'
import { connect } from 'react-redux'
import {login} from '../../actions/security'
import {Map} from 'immutable'
import { Link } from 'react-router'
import BaseForm from './baseForm.jsx'

export default class Login extends React.Component {

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
        const errors={};

        return (
            <form id="login-form" className="form">
                <p>Please provide user name and password</p>
                <div className={errors.username?"form-group has-error":"form-group"}>
                    <label for="name">User Name</label>
                    <input  onChange={this.handleChangeUsername.bind(this)} value={this.state.data.get('username')} type="text"></input>
                </div>
                <div className={errors.password?"form-group has-error":"form-group"}>
                    <label for="name">Password</label>
                    <input  onChange={this.handleChangePassword.bind(this)} value={this.state.data.get('password')} type="password"></input>
                </div>
                <div className="form-group">
                    <input className="btn btn-sm btn-success pull-right" type="button" onClick={this.validate.bind(this)} value="Login"></input>
                </div>
            </form>
        )
    }
}