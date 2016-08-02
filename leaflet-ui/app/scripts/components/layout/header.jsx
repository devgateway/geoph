import React from 'react';
import {LangSwitcher} from '../lan/'
import FilterPopup from '../filter/filters.jsx'
import Settings from '../controls/settings'
import Share from '../controls/share'
import Print from '../controls/print'

import Basemap from '../controls/baseMap.jsx'
import SaveMap from '../save-restore/save'
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
		const {id,label,className,onDeactivate}=this.props;
		const active=this.props[id];
		return (
			<li className={active?`active ${className}`:className}>
			<div className="link" onClick={this.handleClick}>
			<div  className={"options-icons "+className}></div>
			<span>{label}</span>
			</div>
			{

				React.Children.map(this.props.children,(element)=>{return  React.cloneElement(element,{visible:active,
					onHide:()=>{
						onDeactivate(id);
					}});})
			}
			

			</li>)

	}
}));


const items=
[
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
	className:'settings'
},

{
	id:'basemaps',
	key:'basemaps',
	label:'Base Maps',
	children:Basemap,
	className:'basemaps'
},

{
	id:'save',
	key:'save',
	label:'Save',
	children:SaveMap,
	className:'mini save',
	secure:true
},
{
	id:'share',
	key:'share',
	label:'Share',
	children:Share,
	className:'mini share'
},
{
	id:'print',
	key:'print',
	label:'Print',
	children:Print,
	className:'mini print'
}
]


class HeaderComponent extends React.Component {
	
	constructor() {
		super();
	}

	render() {
		const {loggedin} = this.props;
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
				const visible=(!item.secure || (item.secure&&loggedin));
				return (visible)?<MenuItem {...this.props}  {...item}><Component/></MenuItem>:null;
			})}
					<li className="last"></li>
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
	const {accountNonExpired,accountNonLocked,enabled,credentialsNonExpired}=state.security.toJS()
	const loggedin=(accountNonExpired && accountNonLocked&& enabled && credentialsNonExpired);
	
	return {...state.header.toJS(),loggedin}
}

export default connect(mapStateToProps,mapDispatchToProps)(HeaderComponent);