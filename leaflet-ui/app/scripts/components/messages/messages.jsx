import React from 'react'
require('./messages.scss')


class Message extends React.Component {
	render(){
		const {message:message=""}=this.props;
		return (
			<li className={this.props.className}>
			{message}
			</li>
			)
	}

}


class MessageList extends React.Component {
	render(){
		const {messages:messages=[]}=this.props;
		
		return (
			<div className="bs-callout bs-callout-message">
			<ul>
			{messages.map(m=><Message className="message"  message={m}/>)}
			</ul>
			</div>
			)
	}
}

class Errors extends React.Component {
	render(){
		const {errors:errors=[]}=this.props;
		return (
			<div className="bs-callout bs-callout-error">
			<ul>
			{errors.map(e=><Message className="error" message={e}/>)}
			</ul>
			</div>
			)
	}

}

class HttpError extends React.Component {


	render(){
		const {data,status,statusText,message} = this.props.httpError;
		debugger;
		return (
			<div className="bs-callout bs-callout-error">
		
			<ul>
				<li>
					{status==401?<span>Invalid user name or password provided </span>:<span>Got an error please try again {message?`(${message})`: data.message? `(${data.message})`: null}</span>}
				</li>
			</ul>
			</div>
			)
	}

}



class Messages extends React.Component {
	render(){
		let {messages,errors,httpError,location={}}=this.props;
		messages=messages?messages:[];
		errors=errors?errors:[];

		let {state:state={}}=location;
		if(state==null){
			state={};
		}

		let {messages:redirectMessages,errors:redirectErrors,httpError:redirectHttpError}=state;
		
		if (redirectMessages){
			messages=messages.concat(redirectMessages);
		}
		if (redirectErrors){
			errors=errors.concat(redirectErrors);
		}
		if (redirectHttpError){
			httpError=redirectHttpError;
		}

		return (

			<div className="messages">
			{messages.length>0?<MessageList messages={messages}/>:null}
			{errors.length>0?<Errors errors={errors}/>:null}
			{httpError?<HttpError httpError={httpError}/>:null}
			</div>
			)
	}
}
export default Messages;
