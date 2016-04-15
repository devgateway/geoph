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

 	getChildProperties(){
 		return {onToggleLayer:this.props.onToggleLayer};
 	}

 	getCheckbox(){
 		let selected=this.props.layers.findIndex(l=>l.selected).length > -1;
 		return (<input type="checkbox" checked={selected}/>)
 	}

 	renderChildren(){
 		let childProperties=this.getChildProperties();


 		return this.props.layers.map((l)=>{
 			if (l.layers){
 				return 	<LayerGroup key={l.id} {...l} {...childProperties} />
 			}else{
 				return 	<Layer  key={l.id} {...l} {...childProperties} />
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
 			 ({this.props.layers.length}/{this.props.layers.find(l=>l.visible)})
 			<ul>
 			{this.renderChildren()}
 			</ul>
 			</li>)
 	}
 }


/**
 * 
 */
 class Layer extends React.Component {
 	static propTypes = {
 		onToggleLayer:React.PropTypes.func.isRequired
 	}
 	onChange(){
 		this.props.onToggleLayer(this.props.id);
 	}
 	render(){
 		console.log('visible '+this.props.visible);
 		return <li className="layer"><input type="checkbox" checked={this.props.visible} onChange={this.onChange.bind(this)}/><Message prefix={prefix} k={this.props.keyName}/></li>
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
 		debugger;
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
 	debugger;
 	return {layers:state.map.layers,updated:state.map.updated};
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


