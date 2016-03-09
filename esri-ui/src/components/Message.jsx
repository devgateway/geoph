import React from 'react';

export default class Message extends React.Component {
	render(){
		return <span className={this.props.className}>{this.props.message}</span>		
	}
}

