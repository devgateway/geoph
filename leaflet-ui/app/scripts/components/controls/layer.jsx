import React from 'react';
import { connect } from 'react-redux'
import {Message} from '../lan/'
import {loadProjects,loadFunding,toggleVisibility} from '../../actions/map.js'
import * as Constants from '../../constants/constants.js';
require('./layers.scss');
const prefix="control.layers";
/**
 * Base Control which holds some comoons functions
 */
 class ControlComponent extends React.Component {

 	static propTypes = {
 		onToggleLayer:React.PropTypes.func.isRequired
 	}
 	onChange(){
 		this.props.onToggleLayer(this.props.id);
 	}

 	getChildProperties(){
 		return {onToggleLayer:this.props.onToggleLayer};
 	}

 	getCheckbox(){
 	
 		return(	<input type="checkbox" checked={this.props.visible} onChange={this.onChange.bind(this)}/>)
 	}

 	renderChildren(){
 		let childProperties=this.getChildProperties();

 		
 		return this.props.layers.map((l)=>{
 			if (l.get('layers')){
 				return 	<LayerGroup key={l.get('id')} id={l.get('id')}  visible={l.get('visible')}   keyName={l.get('keyName')} layers={l.get('layers')}   {...childProperties} />
 			}else{
 				
 				return 	<Layer  key={l.get('id')} id={l.get('id')}    visible={l.get('visible')}  keyName={l.get('keyName')}  {...childProperties} />
 			}
 		})
 	}
 }

/**
 * 
 */
 class LayerGroup extends ControlComponent {
 	render(){
 		return( 
 			<li className="group">
 			{this.getCheckbox()}
 			<Message prefix={prefix} k={this.props.keyName}/>
 			<div className="breadcrums">{this.props.layers.filter(l=>l.get('visible')).size} / {this.props.layers.size}</div>
 			<ul>
 			{this.renderChildren()}
 			</ul>
 			</li>)
 	}
 }


/**
 * 
 */
 class Layer extends ControlComponent {
 	
 	render(){
 		console.log(this.props.keyName+' visible :'+this.props.visible);
 		return <li className="layer">{this.getCheckbox()}<Message prefix={prefix} k={this.props.keyName}/></li>
 	}
 }

/**
 * 
 */
 class Component extends ControlComponent {

 	constructor(props) {
 		super(props);
 	}
 	static propTypes = {
 		onToggleLayer:React.PropTypes.func,
 		onLoadProjects:React.PropTypes.func.isRequired,
 		onLoadFunding:React.PropTypes.func.isRequired
 	}

 	componentWillReceiveProps(nextProps) {
 		
 	}

 	render(){

 		return (
 			<div>
 			Layers
 			<ul>
 			{this.renderChildren()}
 			</ul>
 			</div>
 			)
 	}
 }


 const stateToProps = (state, props) => {
 	return {layers:state.map.get('layers')};
 }


 const dispatchToProps = (dispatch, ownProps) => {
 	return {
 		onLoadProjects: (level) => {
 			dispatch(loadProjects(level));
 		},
 		onLoadFunding: (level) => {
 			dispatch(loadFunding(level));
 		},

 		onToggleLayer:(name,visible)=>{
 			dispatch(toggleVisibility(name,visible));
 		}
 	}
 }
 /*Connect map component to redux state*/
 const LayerControl=connect(stateToProps,dispatchToProps)(Component);


 export {LayerControl};


