import React from 'react';
import {LangSwitcher} from '../lan/'
import FilterPopup from '../filter/filterPopup'
import Settings from '../controls/settings'
import Basemap from '../controls/baseMap'
import SaveMap from '../save-restore/save'
import {Message} from '../lan/'
import onClickOutside from 'react-onclickoutside';
import {showSaveMap} from '../../actions/saveAndRestoreMap.js';
import {connect} from 'react-redux';

require('./header.scss');

class MenuItem extends React.Component {
	
	

	constructor(props) {
		super(props);
		
	}


	handleClick(){
		if (this.props.onActivate){
			this.props.onActivate(this.props.id)
		}
	}


	render() {
		const {key,label,className,active}=this.props;
		debugger;
		return (
			<li>
			<div onClick={this.handleClick.bind(this)}>
			<div  className={"options-icons "+className}></div>
			<span>{label}</span>
			</div>
			{
				React.Children.map(this.props.children,
					(element)=>{
						return  React.cloneElement(element,{visible:active,onClickOutside:()=>{
							console.log('click onClickOutside');
						}})
					}
					)}
				</li>)

	}
}


const items=[
{
	id:'filters',
	key:'filters',
	label:'Filters',
	className:'filters',
	children:FilterPopup,
	
},
{
	id:'settings',
	key:'settings',
	label:'Settings',
	children:Settings,
	className:'settings',
	
},
{
	id:'save',
	key:'save',
	label:'Save',
	children:SaveMap,
	className:'save',
	
}]


class HeaderComponent extends React.Component {
	
	constructor() {
		super();
	}

	render() {
		return (
			<div className="header">
			<div className="heading">
			<LangSwitcher/>
			</div>
			<div className="title">
			<h2>Philippines Closed > <b> Terminated Project Data Map</b></h2>
			<ul className="options">
			{items.map(item=>{
				const Component=item.children;
				return <MenuItem {...this.props}  {...item}><Component/></MenuItem>
			})}
			</ul>
			</div>
			</div>
			)
	}
}




const mapDispatchToProps = (dispatch, ownProps) => {
	return {
		onActivate:(key)=>{
		debugger;
		dispatch({type:"TOGGLE_ACTIVE_COMPONENT",key})
	}}
}

const mapStateToProps = (state, props) => {
	return {}
}

export default connect(mapStateToProps,mapDispatchToProps)(HeaderComponent);