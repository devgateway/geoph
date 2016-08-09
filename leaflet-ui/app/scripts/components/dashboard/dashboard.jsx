import React from 'react';
import { Link  } from 'react-router';
import {connect} from 'react-redux';
import {getMapList} from '../../actions/saveAndRestoreMap.js';

require("./dashboard.scss");

 class Dashboard extends React.Component {
  constructor() {
    super();
  }

  componentWillMount() {
    debugger;
    this.props.onLoad()
  }

getPreview(d){
  return (
    <div className="item">
    <h1>{d.name}</h1>
     <div className="preview" >
       <Link to={`/map/${d.key}`}>
       <img src={`data:image/png;base64,${d.base64preview}`}/>
      </Link>
     </div>
    <p>{d.description}</p>
  </div>)
}

  render() {
    const {content=[],first,last,number,numberOfElements,size,sort,totalElements,totalPages}=this.props;
    return (<div className="dashboard">
                  {content.map(d=>{
                    return this.getPreview(d);
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