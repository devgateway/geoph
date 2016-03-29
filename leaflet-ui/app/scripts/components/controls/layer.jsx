import React from 'react';
import { connect } from 'react-redux'
import {loadProjects,loadFunding} from '../../actions/map.js'
import * as Constants from '../../constants/constants.js';

class Component extends React.Component {
	
	componentDidMount(){
		this.props.onLoadProjects('region');
		this.props.onLoadFunding('region');
	}

	onChangeLevel(e){
		this.props.onLoadProjects(e.target.value);
		this.props.onLoadFunding(e.target.value);

	}

	render(){
		return (<div>
				<select name='level' onChange={this.onChangeLevel.bind(this)}>
					<option value='region'>Region</option>
					<option value='province'>Department</option>
					<option value='municipality'>Municipalities</option>
				</select>
			</div>)
	}
}




const stateToProps = (state, props) => {
	return state.map  
}


const dispatchToProps = (dispatch, ownProps) => {
	return {
		onLoadProjects: (level) => {
			dispatch(loadProjects(level));
		},
		onLoadFunding: (level) => {
			dispatch(loadFunding(level));
		}
	}
}
/*Connect map component to redux state*/
const LayerControl=connect(stateToProps,dispatchToProps)(Component);


export {LayerControl};
