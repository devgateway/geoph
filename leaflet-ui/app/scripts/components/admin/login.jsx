import React from 'react'
import { connect } from 'react-redux'
import {login} from '../../actions/security'

import {Map} from 'immutable'
import { Link } from 'react-router'
import BaseForm from './baseForm.jsx'
import Messages from '../messages/messages.jsx'

export default class Login extends React.Component {

    validate(){
        let username=this.state.data.get('username') || '';
        let password=this.state.data.get('password') || '';
        let errors={}
       this.setState({data:this.state.data.setIn(['errors'],errors)});

        if(username=='' || username.length < 1) {
            Object.assign(errors,{username:true})
        }

        if (password=='' || password.length < 1){
            Object.assign(errors,{password:true})
            
        }
        
        if  (errors.username!=true && errors.password!=true){
            this.props.onLogin({username,password})
        }else{
             this.setState({data:this.state.data.setIn(['errors'],errors)});
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

        const errors=(this.state!=null&&this.state.data.get("errors")!=null)?this.state.data.get("errors"):{};
           debugger;
        return (
            <form id="login-form" className="form">
                <p>Please provide user name and password</p>

                <Messages {...this.props}/>

                <div className={errors.username?"form-group has-error":"form-group"}>
                    <label>User Name</label>
                    <input className="form-control"  onChange={this.handleChangeUsername.bind(this)} value={this.state.data.get('username')} type="text"></input>
                    {errors.username?  <i className="small errors">* Please enter a valid user name</i>:null}
                </div>
                 
                <div className={errors.password?"form-group has-error":"form-group"}>
                    <label >Password</label>
                    <input  className="form-control" onChange={this.handleChangePassword.bind(this)} value={this.state.data.get('password')} type="password"></input>
                     {errors.password?  <i className="small errors">* Please enter a password </i>:null}
                </div>
                <div className="form-group">
                    <input className="btn btn-sm btn-success pull-right" type="button" onClick={this.validate.bind(this)} value="Login"></input>
                </div>
            </form>
        )
    }
}