import React from 'react';
import { Link  } from 'react-router';
import {connect} from 'react-redux';
import Messages from '../messages/messages.jsx'	
import {getMapList,edit,remove} from '../../actions/dashboard.js';
require("./dashboard.scss");

class Item extends React.Component {
  render(){
  const {description,name,mapKey,base64preview}=this.props;
   return (
    <div className="item">
      <h1>{name}</h1>
      <div className="preview" >
      <Link to={`/map/${mapKey}`}>
        <img src={`data:image/png;base64,${base64preview}`}/>
      </Link>
    </div>
      <p>{description}</p>
      {this.props.children}
    </div>)
 }
}

class AdminActions extends React.Component {
  render(){
   const {id,mapKey,onDelete,onEdit}=this.props;
    return (<div>
    		
    		<button className="btn btn-sm btn-warning pull-right" onClick={_=>onDelete(mapKey)}>Delete</button>
			<a target="new" href={`#/map/${mapKey}`}  className="btn btn-sm btn-info">Edit</a>

    </div>)
  }
}




class Dashboard extends React.Component {
  constructor() {
    super();
  }

  componentWillMount() {
     this.props.onLoad()
  }

  render() {
    const {content=[],first,last,number,numberOfElements,size,sort,totalElements,totalPages,loggedIn}=this.props;
    return (  <div className="dashboard-main">

      <h1>List of executive dashboards</h1>
		<Messages {...this.props}/>
      {content.map(d=>{
        return <Item {...d} mapKey={d.key}>
          {loggedIn?<AdminActions {...this.props} mapKey={d.key}/>:null}

        </Item>
      })}
      </div>
      )
  }
}



const mapDispatchToProps = (dispatch, ownProps) => {
  return {

    onLoad:()=>{dispatch(getMapList())},
    onEdit:(key)=>{dispatch(edit(key))},
    onDelete:(key)=>{dispatch(remove(key))}
  }
}

const mapStateToProps = (state, props) => {
   const {results}=state.dashboard.toJS();

  return {...results}
}

export default connect(mapStateToProps,mapDispatchToProps)(Dashboard);