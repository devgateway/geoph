import React from 'react';
import { connect } from 'react-redux'
import {Message} from '../lan/'
import {loadProjects,loadFunding,toggleVisibility,setSetting} from '../../actions/map.js'
import * as Constants from '../../constants/constants.js';
import {collectValues} from '../../util/filterUtil';
import translate from '../../util/translate.js';
require('./layers.scss');
const prefix="toolview.layers";

import InputRange from 'react-input-range';

class Settings extends React.Component {
	set(setting,value){
		let filters = collectValues(this.props.filters, this.props.projectSearch);
		this.props.onSettingChanged(this.props.id, setting, value, filters);
	}
	setQuality(slider,value){
		this.set('quality',value)
	}

	render(){
		let settings=this.props.settings?this.props.settings.toJS():{}; 
		return (
			<ul className="settings">
			{(settings['level'])?<li>
			<ul className="level">
                <li>{translate('toolview.layers.level')}:</li>

                <li className={settings['level']=="region"?"active":""}  onClick={()=>{this.set('level','region')}}>{translate('toolview.layers.region')}</li>
				<li className={settings['level']=="province"?"active":""} onClick={()=>{this.set('level','province')}}>{translate('toolview.layers.province')}</li>
				<li className={settings['level']=="municipality"?"active":""} onClick={()=>{this.set('level','municipality')}}>{translate('toolview.layers.municipality')}</li>
			</ul>
			</li>:null}
			{(settings['quality'])?<li>
                <ul>
                    <li>{translate('toolview.layers.quality')}</li>
                    <li>
                        <InputRange maxValue={100} minValue={1} value={settings['quality']} onChange={this.setQuality.bind(this)}/>
                    </li>
                </ul>
                </li>:null}
                {(settings['css'])?<li>
			    	<ul  className="css colors">
                    <li>{translate('toolview.layers.colors')}</li>
					<li className={settings['css']=="red"?"scheme red active":"scheme red "}  onClick={()=>{this.set('css','red')}} ></li>
					<li className={settings['css']=="yellow"?"scheme yellow active":"scheme yellow "} onClick={()=>{this.set('css','yellow')}}></li>
					<li className={settings['css']=="green"?"scheme green active":"scheme green "} onClick={()=>{this.set('css','green')}}></li>
                    <li className={settings['css']=="orange"?"scheme orange active":"scheme orange "} onClick={()=>{this.set('css','orange')}}></li>
                    <li className={settings['css']=="blue"?"scheme blue active":"scheme blue "} onClick={()=>{this.set('css','blue')}}></li>
				</ul>
			</li>:null}
			</ul>);
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
 		let filters = collectValues(this.props.filters, this.props.projectSearch);
 		this.props.onToggleLayer(this.props.id, null, filters);
 	}

 	getChildProperties(){
 		return {
 			onToggleLayer: this.props.onToggleLayer,
 			onSettingChanged: this.props.onSettingChanged,
 			filters: this.props.filters,
 			projectSearch: this.props.projectSearch
 		};
 	}

 	getCheckbox(){
		let selectionClass = "selectable " + (this.props.visible? "selected" : "");
  		return(	
 			<div className="group-title" onClick={this.onChange.bind(this)}> 
 				<div className={selectionClass}/>
 				<Message prefix={prefix} k={this.props.keyName}/>
 			</div>
 			)
 	}

 	getSettings(){
 			let childProperties=this.getChildProperties();
 		return <Settings {...this.props} {...childProperties}/>
 	}

 	renderChildren(){
 		let childProperties = this.getChildProperties();	
 		return this.props.layers.map((l)=>{
 			if (l.get('layers')){
 				return 	<LayerGroup key={l.get('id')} id={l.get('id')}  visible={l.get('visible')} keyName={l.get('keyName')} layers={l.get('layers')}   {...childProperties} />
 			}else{
 				return 	<Layer  key={l.get('id')} id={l.get('id')}    visible={l.get('visible')}  keyName={l.get('keyName')}  {...childProperties} settings={l.get('settings')} />
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
				<div className="breadcrums">
					({this.props.layers.filter(l=>l.get('visible')).size}/{this.props.layers.size})
				</div>
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
 		filters: state.filters.filterMain,
 		projectSearch: state.projectSearch,
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
 			dispatch(toggleVisibility(name, visible, filters));
 		},

 		onSettingChanged:(name, setting, value, filters)=>{
 			dispatch(setSetting(name, setting, value, filters));
 		}
 	}
 }
 /*Connect map component to redux state*/
 const LayerControl=connect(stateToProps,dispatchToProps)(Component);


 export {LayerControl};


