import React from 'react';
import { Link  } from 'react-router';
import {connect} from 'react-redux';
import {getMapList} from '../../actions/dashboard.js';
require("./dashboard.scss");

class Item extends React.Component {
  render(){
  const {description,name}=this.props;
   return (
    <div className="item">
      <h1>{name}</h1>
      <div className="preview" >
      <Link to={`/map/${d.key}`}>
        <img src={`data:image/png;base64,${d.base64preview}`}/>
      </Link>
    </div>
      <p>{description}</p>
      {this.props.children}
    </div>)
 }
}

class DeleteButton extends React.Component {
  render(){
    return <button className="btn btn-sm btn-warning">Delete</button>
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

      <h2>List of executive dashboards</h2>

      {content.map(d=>{
        return <Item {...d}>
          {loggedIn?<DeleteButton key={d.key}/>:null}

        </Item>
      })}
      </div>
      )
  }
}



const mapDispatchToProps = (dispatch, ownProps) => {
  return {

    onLoad:(key)=>{dispatch(getMapList())}
  }
}

const mapStateToProps = (state, props) => {
  const {results}=state.dashboard.toJS();
  return {...results}
}

export default connect(mapStateToProps,mapDispatchToProps)(Dashboard);