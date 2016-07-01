import React from 'react'

/*A view to render HTTP errors*/

export default class Error extends React.Component {
    

    render(){
    	const {data,status,statusText} = this.props.error
         return (
            <p className="alert alert-danger small">
                
                {status==401?<span>Invalid user name or password provided </span>:<span>{data?data:"There was an error please try again"}</span>}
            </p>
        )
    }

}