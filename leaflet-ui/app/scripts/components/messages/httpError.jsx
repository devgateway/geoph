import React from 'react'

/*A view to render HTTP errors*/

export default class HttpError extends React.Component {
    

    render(){
    	debugger
    	const {data,status,statusText} = this.props.error;
    	const {message=""}=data
    	console.log(data)
         return (
            <p className="alert alert-danger small">
                {status==401?<span>Invalid user name or password provided </span>:<span>{message?message:"There was an error please try again"}</span>}
            </p>
        )
    }

}