import React from 'react';
import { Link  } from 'react-router';
import {connect} from 'react-redux';
import Messages from '../messages/messages.jsx'	
import {getMapList,edit,remove} from '../../actions/dashboard.js';
require("./dashboard.scss");

var pageSize = 5;

class Item extends React.Component {
  render(){
    const {description, name, mapKey, base64preview, created} = this.props;
    return (
      <div className="item">
        <Link to={`/map/${mapKey}`}>
          <div className="preview" >
            <img src={`data:image/png;base64,${base64preview}`}/>
          </div>
          <div className="title" >
            {name}
          </div>
        </Link>
        <div className="description" >
          {description}
        </div>
        <div className="created" >
          {created || "No creation date"}
        </div>
        <div className="actions" >
          {this.props.children}
        </div>
      </div>
    )
  }
}

class AdminActions extends React.Component {
  render(){
    const {id,mapKey,onDelete,onEdit}=this.props;
    return (
      <div>
        <a href={`#/map/${mapKey}`}  className="btn btn-sm btn-info">Edit</a>
        <button className="btn btn-sm btn-danger" onClick={_=>onDelete(mapKey)}>Delete</button>
      </div>
    )
  }
}

class Dashboard extends React.Component {
  constructor() {
    super();
    this.state = {'activePage': 0};
  }

  componentWillMount() {
     this.getListData(0);
  }

  getListData(activePage){
    let params ={
      'page': activePage,
      'size': pageSize
    };    
    this.props.onGetList(params);
    this.setState({activePage: activePage});
  }

  render() {
    const {content=[], first, last, number, numberOfElements, size, sort, totalElements, totalPages, loggedIn} = this.props;
    const {activePage} = this.state;
    return (  
      <div className="dashboard-main">
        <Messages {...this.props}/>
        <div className="list-header">
          <div className="preview" />
          <div className="title" >
            Map Title
          </div>
          <div className="description" >
            Description
          </div>
          <div className="created" >
            Created
          </div>
          <div className="actions" >
            Actions
          </div>
        </div>
        {content.map(d=>{
          return ( 
            <Item {...d} mapKey={d.key}>
              {loggedIn?<AdminActions {...this.props} mapKey={d.key}/>:null}
            </Item>
          )
        })}
        <div className="pager">
          <button className="btn btn-sm btn-default" disabled={first? "disabled":""} onClick={this.getListData.bind(this, activePage-1)}>{"<"}</button>
          <div className={"pager-state"}>{"page "+(activePage+1) + " of "+totalPages}</div>
          <button className="btn btn-sm btn-default" disabled={last? "disabled":""} onClick={this.getListData.bind(this, activePage+1)}>{">"}</button>
        </div>
      </div> 
    )
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onGetList:(params)=>{dispatch(getMapList(params))},
    onEdit:(key)=>{dispatch(edit(key))},
    onDelete:(key)=>{dispatch(remove(key))}
  }
}

const mapStateToProps = (state, props) => {
  const {results}=state.dashboard.toJS();
  return {...results}
}

export default connect(mapStateToProps,mapDispatchToProps)(Dashboard);