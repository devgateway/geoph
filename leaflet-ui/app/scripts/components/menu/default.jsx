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
import MenuItem from './item.jsx';

class MenuBar extends React.Component{
	render(){
		const {loggedin, items=[], title} = this.props;
		return (
			<div className="title">
				<h2><b>{title}</b></h2>
				<ul className="options">
					{items.map(item=>{
						const Component=item.children;
						const visible=(!item.secure || (item.secure&&loggedin));
						return (visible)?<MenuItem {...this.props}  {...item}><Component/></MenuItem>:null;
					})}
					<li className="last"></li>
				</ul>
				{this.props.children}
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
	return {...state.header.toJS(), loggedin}
}

export default connect(mapStateToProps,mapDispatchToProps)(MenuBar);