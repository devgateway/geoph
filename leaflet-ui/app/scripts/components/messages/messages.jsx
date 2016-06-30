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


class Messages extends React.Component {
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

export {Messages,Errors}
