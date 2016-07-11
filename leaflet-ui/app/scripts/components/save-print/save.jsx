import React from 'react';

import { connect } from 'react-redux';
import i18next from 'i18next';
import {requestSaveMap}  from '../../actions/saveMap';
import {collectValuesToSave}  from '../../util/saveUtil';
import {Modal, Button} from 'react-bootstrap';
import onClickOutside from 'react-onclickoutside';
import { Input } from 'react-bootstrap';
require('./save.scss');

const Save = onClickOutside(React.createClass({

	getInitialState() {
    	return {'showSave': false};
  	},

  	toggleSaveView() {
    	this.setState({'saveName': '', 'saveDesc': '', 'showSave': !this.state.showSave});
  	},

  	handleNameChange(e) {
    	let saveName = e.target.value;
		this.setState({saveName: saveName, typingName: true});
  	},

  	handleDescChange(e) {
    	let saveDesc = e.target.value;
		this.setState({saveDesc: saveDesc, typingDesc: true});
  	},

	handleClickOutside (evt) {
		if (this.state.showSave){
			this.setState({'showSave': false});
		}
	},

	saveMapState() {
		//console.log("---saveMapState---");
		let dataToSave = collectValuesToSave(this.props.stateToCollect);
	    this.props.onRequestSaveMap({
	    	name : this.state.saveName, 
	    	description : this.state.saveDesc,
	    	dataToSave : dataToSave});
	},

	validateNameState() {
    	const length = this.state.saveName.length;
    	const typingName = this.state.typingName;
    	if (length>3){
    		return 'success';
    	} else {
    		return 'error';
    	}
    },

	validateDescState() {
    	const length = this.state.saveDesc.length;
    	const typingDesc = this.state.typingDesc;
    	if (length>3){
    		return 'success';
    	} else {
    		return 'error';
    	}
    },

	render() {
    return (
      <li ><div className="options-icons basemaps" onClick={this.toggleSaveView}></div><span onClick={this.toggleSaveView}>Save</span>
        {this.state.showSave?
          <div className="save-container">
            <h2>Save Map</h2>
            <br />
            <div className="chart-type-selector">
              <Input className={this.state.saveName.length==0? 'save-input-empty' : 'save-input-filled'} type="text" value={this.state.saveName}  
	            placeholder="Add a Name"  
	            bsStyle={this.validateNameState()}   
	            bsSize="medium"  ref="saveName"
			    onChange={this.handleNameChange.bind(this)}/>
			  <Input className={this.state.saveDesc.length==0? 'save-input-empty' : 'save-input-filled'} type="text" value={this.state.saveDesc}  
	            placeholder="Add a Description"  
	            bsStyle={this.validateDescState()}   
	            bsSize="medium"  ref="saveDesc"
			    onChange={this.handleDescChange.bind(this)}/>
			</div> 
			<br />
			<div className="chart-type-selector">			  
			  <Button className="btn btn-sm" bsStyle='success' onClick={this.saveMapState.bind(this)}>Save</Button>       
            </div>
            {this.props.stateToCollect.saveMap.message!=undefined?            	
            	<div className="chart-type-selector">			  
				  {this.props.stateToCollect.saveMap.message} 
	            </div>
            	: null} 
          </div>
        : null}
      </li>
    );
  }
}));


const mapDispatchToProps = (dispatch, ownProps) => {
 return {
    onRequestSaveMap: (dataToSave) => {
      dispatch(requestSaveMap(dataToSave));
    }
  }
}

const mapStateToProps = (state, props) => {
  return {
    stateToCollect: state
  }
}

export default connect(mapStateToProps,mapDispatchToProps)(Save);