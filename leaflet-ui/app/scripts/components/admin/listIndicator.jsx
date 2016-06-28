import React from 'react'
import { connect } from 'react-redux'
import {Map} from 'immutable'
import {getList,deleteIndicator,editIndicator} from '../../actions/indicators.js'
import {Messages} from '../messages/messages.jsx'

require('./admin.scss')

const Indicator = class extends React.Component {


  render() {
    return (
      <li>
        {this.props.name}
          <button className="pull-right" onClick={()=>{this.props.onEdit(this.props)}}>Edit</button>
          <button className="pull-right"  onClick={()=>{this.props.onDelete(this.props)}}>Remove</button>
      </li>
      );
  }
}


export default class listIndicator extends React.Component {
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
    debugger;
    return (
      <div className="indicator-list">
        <h2>List of indicators</h2>
            {(indicators.length > 0)?this.list(indicators):this.noRecords()}
      </div>);
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
	return {indicators:indicators.get("indicators")};
};

export default connect(mapStateToProps, mapDispatchToProps)(listIndicator);

