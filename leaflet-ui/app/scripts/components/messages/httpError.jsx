import React from 'react'

/*A view to render HTTP errors*/

export default class Error extends React.Component {
    

    render(){
        debugger
        return (
            <p className="alert alert-danger small">
                
                {this.props.error.status==401?<span>Invalid user name or password provided </span>:<span>There was an error please try again</span>}
            </p>
        )
    }

}