import React from 'react';
import {LangSwitcher} from '../lan/'
import FilterPopup from '../filter/filters.jsx'
import Settings from '../controls/settings'
import Basemap from '../map/baseMap/baseMap'
import SaveMap from '../save-restore/save'
import ShareMap from '../save-restore/share'
import {Message} from '../lan/'
import onClickOutside from 'react-onclickoutside';
import {showSaveMap} from '../../actions/saveAndRestoreMap';
import {connect} from 'react-redux';
import * as Constants from '../../constants/constants';

require('./header.scss');

const MenuItem =onClickOutside(React.createClass({
	
	handleClickOutside (evt) {
		const {onClickOutside}=this.props;
		this.props.onDeactivate(this.props.id);
	},

	handleClick(){
		const {id,label,className}=this.props;
		const active=this.props[id];
		if (!active){
			this.props.onActivate(this.props.id);
		}else{
			this.props.onDeactivate(this.props.id);
		}
	},


	render() {
		const {id,label,className}=this.props;
		const active=this.props[id];
		return (
			<li>
			<div onClick={this.handleClick}>
			<div className={"options-icons "+className}></div>
			{label?<span>{label}</span>:<span>&nbsp;</span>}
			</div>
			{
				React.Children.map(this.props.children,(element)=>{return  React.cloneElement(element,{visible:active});})
			}
			</li>)

	}
}));


const items=[
{
	id:'filters',
	key:'filters',
	label:'Filters',
	className:'filters',
	disableOnClickOutside:true,
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
	
},
{
	id:'share',
	key:'share',
	label:'',
	children:ShareMap,
	className:'share',
	
}]


class HeaderComponent extends React.Component {
	
	constructor() {
		super();
	}

	render() {
		console.log(this.props);
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
		onActivate:(key)=>{dispatch({type:Constants.ACTIVATE_COMPONENT,key})},
		onDeactivate:(key)=>{dispatch({type:Constants.DEACTIVATE_COMPONENT,key})}
	}
}

const mapStateToProps = (state, props) => {
	return state.header.toJS()
}

export default connect(mapStateToProps,mapDispatchToProps)(HeaderComponent);