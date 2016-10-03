import React from 'react';
import {LangSwitcher} from '../lan/'
import onClickOutside from 'react-onclickoutside';
import {Tooltip, OverlayTrigger} from 'react-bootstrap';

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
		const {id,label,className,onDeactivate, tooltip}=this.props;
		const active=this.props[id];
		return (
			<li className={active?`active ${className}`:className}>
				<OverlayTrigger delayShow={1000} placement="bottom" overlay={tooltip}>
					<div className="link" onClick={this.handleClick}>
						<div className={"options-icons "+className}></div>
						<span>{label}</span>
					</div>
				</OverlayTrigger>
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