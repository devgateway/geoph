import React from 'react';
import {LangSwitcher} from '../lan/'
import FilterPopup from '../filter/filters.jsx'
import onClickOutside from 'react-onclickoutside';

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
					<div className={"options-icons "+className}></div>
					<span>{label}</span>
				</div>
				{
					React.Children.map(this.props.children,(element)=>{return  React.cloneElement(element,{visible:active,
					onHide:()=>{
						onDeactivate(id);
					}});})
				}		
			</li>
		)
	}
}));


export default  MenuItem;