import React from 'react'
require('./messages.scss')


class Message extends React.Component {
    render(){
    	const {message:message=""}=this.props;
         return (
            <p className={this.props.className}>
               {message}
            </p>
        )
    }

}


class Messages extends React.Component {
	debugger;
	render(){
		const {messages:messages=[]}=this.props;
		debugger;
		return (
			<div className="bs-callout bs-callout-message">
			{messages.map(m=><Message className="alert alert-success small"  message={m}/>)}
			</div>
			)
	}
}

class Errors extends React.Component {
	debugger;
	render(){
		const {errors:errors=[]}=this.props;
		debugger;
		return (
			<div className="bs-callout bs-callout-error">
				{errors.map(e=><Message className="alert alert-error small" message={e}/>)}
			</div>
			)
	}

}

export {Messages,Errors}
