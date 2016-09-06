import React from 'react';
import { connect } from 'react-redux'
import {Message} from '../lan/'
import {loadProjects,loadFunding,toggleVisibility,setSetting} from '../../actions/map.js'
import * as Constants from '../../constants/constants.js';
import translate from '../../util/translate.js';
import {Tooltip, OverlayTrigger} from 'react-bootstrap';
require('./layers.scss');
const prefix="toolview.layers";

import InputRange from 'react-input-range';

class Settings extends React.Component {

	set(setting,value){
		const {id,onSettingChanged} =this.props;
	
		onSettingChanged(id, setting, value);
	}
	setQuality(slider,value){
		this.set('quality',value)
	}

	render(){
		
		let settings=this.props.settings?this.props.settings.toJS():{}; 
		return (
			<ul className="settings">
				{(settings['level'])?
					<li>
						<ul className="level">
							<li className="setting-name"><b>{translate('toolview.layers.level')}:</b></li>
							<li onClick={()=>{this.set('level','region')}}>
								<div className={settings['level']=="region"?"active":""} >{translate('toolview.layers.region')}</div></li>
							<li onClick={()=>{this.set('level','province')}}>
								<div className={settings['level']=="province"?"active":""} >{translate('toolview.layers.province')}</div></li>
							<li onClick={()=>{this.set('level','municipality')}}>
								<div className={settings['level']=="municipality"?"active":""} >{translate('toolview.layers.municipality')}</div></li>
						</ul>
						<div className="separator"/>				
					</li>
				:null}
				
				{(settings['detail'])?
						<li>
						<ul className="level">
							<li className="setting-name"><b>Details:</b></li>
							<li onClick={()=>{this.set('detail','low')}}>
								<div className={settings['detail']=="low"?"active":""} >Low</div></li>
							<li onClick={()=>{this.set('detail','medium')}}>
								<div className={settings['detail']=="medium"?"active":""}>medium</div></li>
							<li onClick={()=>{this.set('detail','high')}}>
								<div className={settings['detail']=="high"?"active":""}>high</div></li>
						</ul>
						<div className="separator"/>				
					</li>
				:null}

			
				{(settings['css'])?
					<li>
						<ul  className="css colors">
							<li className="setting-name"><b>{translate('toolview.layers.colors')}:</b></li>
							<li className={settings['css']=="red"?"scheme red active":"scheme red "}  onClick={()=>{this.set('css','red')}} ></li>
							<li className={settings['css']=="yellow"?"scheme yellow active":"scheme yellow "} onClick={()=>{this.set('css','yellow')}}></li>
							<li className={settings['css']=="green"?"scheme green active":"scheme green "} onClick={()=>{this.set('css','green')}}></li>
							<li className={settings['css']=="orange"?"scheme orange active":"scheme orange "} onClick={()=>{this.set('css','orange')}}></li>
							<li className={settings['css']=="blue"?"scheme blue active":"scheme blue "} onClick={()=>{this.set('css','blue')}}></li>
						</ul>
					</li>
				:null}

					

				
			</ul>
		);
	}
}

/**
 * Base Control which holds some comoons functions
 */
 class ControlComponent extends React.Component {

 	static propTypes = {
 		onToggleLayer:React.PropTypes.func.isRequired
 	}


 	onChange(){
 		const {id,visible:visible=false} = this.props;
 		
 		this.props.onToggleLayer(id,visible);
 	}

 	getChildProperties(){
 		return {
 			onToggleLayer: this.props.onToggleLayer,
 			onSettingChanged: this.props.onSettingChanged,
 		};
 	}

 	getTitle(){
 		const {keyName, name} = this.props;
 		return(	
 			<div className="group-title"> 
 				<div/>
 				{keyName?<Message prefix={prefix} k={keyName}/>:<span>{name}</span>}
 			</div>
 			)
 	}

 	getCheckbox(){
 		let selectionClass = "selectable " + (this.props.visible? "selected" : "");
 		const {keyName,name} = this.props;
 		return(	
 			<div className="layer-title" onClick={this.onChange.bind(this)}> 
 				<div className={selectionClass}/>
 				{keyName?<Message prefix={prefix} k={keyName}/>:<span>{name}</span>}
 			</div>
 			)
 	}

 	getSettings(){
 		let childProperties=this.getChildProperties();
 		return <Settings key={this.props.id} {...this.props} {...childProperties}/>
 	}

 	renderChildren(){

 		let childProperties = this.getChildProperties();	
 		return this.props.layers.map((l)=>{
 			var props={key:l.get('id'), id:l.get('id'), settings:l.get('settings') ,visible:l.get('visible'),name:l.get('name'), keyName:l.get('keyName'), helpKey:l.get('helpKey'), layers:l.get('layers')}
 			
 			if (l.get('layers')){
 				return 	<LayerGroup {...props} {...childProperties} />
 			} else {
 				return 	<Layer {...props} {...childProperties}  />
 			}
 		})
 	}
 }

 /**
 * 
 */
 class LayerGroup extends ControlComponent {
 	render(){
	 	const {helpKey} = this.props;
	 	return( 
 			<li className="group">
 				{helpKey?
 					<OverlayTrigger delayShow={1000} placement="top" overlay={(<Tooltip id={helpKey}>{translate(helpKey)}</Tooltip>)}>
 						{this.getTitle()}
 					</OverlayTrigger>
 				: 
 					this.getTitle()
 				}
				<div className="breadcrums">
					({this.props.layers.filter(l=>l.get('visible')).size}/{this.props.layers.size})
				</div>
 				<ul>
 					{this.renderChildren()}
 				</ul>
 			</li>
 		);
 	}
 }


 /**
 * 
 */
 class Layer extends ControlComponent {
 	render(){
 		return <li className="layer">
 		{this.getCheckbox()}
 		{this.getSettings()}
 		</li>
 	}
 }

 /**
 * 
 */
 class Component extends ControlComponent {
 	constructor(props) {
 		super(props);
 		this.state={expanded:false}
 	}
 	static propTypes = {
 		onToggleLayer:React.PropTypes.func,
 		onLoadProjects:React.PropTypes.func.isRequired,
 		onLoadFunding:React.PropTypes.func.isRequired,
 		onSettingChanged:React.PropTypes.func.isRequired
 	}

 	toggle(){
 		this.setState({expanded:!this.state.expanded})
 	}
 	render(){
 		return (<div className="layers-control"><ul>{this.renderChildren()}</ul></div>);
 	}
 }


 const stateToProps = (state, props) => {
 	return {
 		layers: state.map.get('layers'),
    	language: state.language
 	};
 }


 const dispatchToProps = (dispatch, ownProps) => {
 	return {
 		onLoadProjects: (level) => {
 			dispatch(loadProjects(level));
 		},
 		onLoadFunding: (level) => {
 			dispatch(loadFunding(level));
 		},

 		onToggleLayer:(name, visible, filters)=>{
 			dispatch(toggleVisibility(name, visible,filters));
 		},

 		onSettingChanged:(name, setting, value, filters)=>{
 			dispatch(setSetting(name, setting, value, filters));
 		}
 	}
 }
 /*Connect map component to redux state*/
 const LayerControl=connect(stateToProps,dispatchToProps)(Component);


 export {LayerControl};


