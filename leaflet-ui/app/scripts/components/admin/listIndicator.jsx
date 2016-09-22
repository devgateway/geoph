import React from 'react'
import { connect } from 'react-redux'
import {Map} from 'immutable'
import {getList,deleteIndicator,editIndicator} from '../../actions/indicators.js'
import Messages from '../messages/messages.jsx'

require('./admin.scss')

const Indicator = class extends React.Component {


  render() {
    return (
      <li className="item">
        <div>
          <h2> {this.props.name}</h2>
          <p>{this.props.description}</p>
        </div>
        <button className="pull-right btn-sm btn-danger"  onClick={()=>{this.props.onDelete(this.props)}}>Remove</button>
      </li>
    );
  }
}


class listIndicator extends React.Component {
  static propTypes = {
    name: React.PropTypes.string,
  };

  constructor(props) {
    super(props);
  }
  
  componentWillMount() {
    this.props.onLoad() 
  }

  noRecords(){
    return (<Messages messages={["There are no indicators yet."]}/>)
  }

  list(indicators){
    return  (<ul>{indicators.map(indicator=><Indicator {...this.props} {...indicator}/>)}</ul>)
  }

  render() {
    const {indicators}=this.props;
    return (
      <div className="indicator-list">
        <h1>List of indicators</h1>
        <Messages {...this.props}/>
        {(indicators && indicators.length > 0)?this.list(indicators):this.noRecords()}
      </div>
    );
  }
}


const mapDispatchToProps = (dispatch, ownProps) => {
	return {
		onLoad: () => {
			dispatch(getList());	
		},
    onDelete:(id)=>{
        dispatch(deleteIndicator(id))
    },
    onEdit:(id)=>{
        dispatch(editIndicator(id))
    }
	}
}


const mapStateToProps = (state, props) => {
 	const {indicators} = state;
	return {...indicators.toObject()};
};

export default connect(mapStateToProps, mapDispatchToProps)(listIndicator);

