import React from 'react';
import { connect } from 'react-redux'
import {loadProjects,loadFunding,toggleVisibility} from '../../actions/map.js'
import * as Constants from '../../constants/constants.js';

require('./layers.scss');


class Toggler extends React.Component {
	static propTypes = {
		onToggle:React.PropTypes.func.isRequired
	}

	toggle(){
		debugger;
		this.props.onToggle(this.props.name,!this.props.visible);
	}

	render(){
		return (<button onClick={this.toggle.bind(this)} className={`btn btn-xs  ${this.props.visible?"btn-success":"btn-default"}`}> {this.props.visible?"ON":"OFF"}</button>)
	}
}


class Leveler extends React.Component {

	setLevel(level){
		this.props.onChange(level);
	}

	render(){
		return (
			<ul>
			<li><input onChange={this.setLevel.bind(this,'region')} type="radio" name='level' value='region' checked={this.props.level=='region'}/>Region</li>
			<li><input onChange={this.setLevel.bind(this,'province')} type="radio" name='level' value='province' checked={this.props.level=='province'}/>Department</li>
			<li><input onChange={this.setLevel.bind(this,'municipality')} type="radio" name='level' value='municipality' checked={this.props.level=='municipality'}/>Municipalities</li>
			</ul>
			)
	}
}




class Component extends React.Component {

	constructor(props) {
		super(props);
	}

	static propTypes = {
		isProjectVisible: React.PropTypes.bool,
		isTotalFundingVisible: React.PropTypes.bool,
		onToggleLayerVisibility:React.PropTypes.func,

		onLoadProjects:React.PropTypes.func.isRequired,
		onLoadFunding:React.PropTypes.func.isRequired
	}

	componentDidMount(){
		
	}

	onChangeLevel(level){	
		debugger;

	}


	render(){
		return (
		<div className="layers-control">
			<ul>
				<li>Project and Statistical
					<ul>
						<li>
							Projects 
							<Toggler name="project" visible={this.props.isProjectVisible} onToggle={this.props.onToggleLayerVisibility.bind(this)}/> 
							<Leveler level='region' onChange={this.onChangeLevel}/>
						</li>
					</ul>
				</li>
				<li>Statistical
					<ul>
						<li>
							Total Funding layers 
							<Toggler name="funding" visible={this.props.isTotalFundingVisible} onToggle={this.props.onToggleLayerVisibility.bind(this)}/> 

						</li>
						<li>
							Physical progress <Toggler name="physical" visible={this.props.isPhysicalVisible} onToggle={this.props.onToggleLayerVisibility.bind(this)}/> 
						</li>
						<li>Geotagged Photos <Toggler name="geotagged" visible={this.props.isGeotaggedVisible} onToggle={this.props.onToggleLayerVisibility.bind(this)}/> 
						</li>
					</ul>
				</li>


			</ul>
			
		</div>)
	}
}




const stateToProps = (state, props) => {
	return {
		isProjectVisible:(state.map.layers.findIndex(l=>l.name=='project') > -1),
		isTotalFundingVisible:state.map.layers.findIndex(l=>l.name=='funding') > -1,
		isPhysicalVisible:false,
		isGeotaggedVisible:false
	}  
}


const dispatchToProps = (dispatch, ownProps) => {
	return {
		onLoadProjects: (level) => {
			dispatch(loadProjects(level));
		},
		onLoadFunding: (level) => {
			dispatch(loadFunding(level));
		},

		onToggleLayerVisibility:(name,visible)=>{
			dispatch(toggleVisibility(name,visible));
		}
	}
}
/*Connect map component to redux state*/
const LayerControl=connect(stateToProps,dispatchToProps)(Component);


export {LayerControl};


