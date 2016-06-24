import React from 'react';

import { connect } from 'react-redux';
import i18next from 'i18next';
import {requestSaveMap}  from '../../actions/saveMap';

class Save extends React.Component {
 	constructor() {
		super();
	}

	saveMapState() {
		console.log("---saveMapState---");
	    this.props.onRequestSaveMap(this.props.saveMap);
	}

	render() {
		let content;
		content=(<li onClick={this.saveMapState.bind(this)}><div onClick={this.saveMapState.bind(this)}  className="options-icons basemaps"></div>Save</li>)	

		return content;
	}
}

const mapDispatchToProps = (dispatch, ownProps) => {
 return {
    onRequestSaveMap: (saveMap) => {
      dispatch(requestSaveMap(saveMap));
    }
  }
}

const mapStateToProps = (state, props) => {
  return {
    saveMap: state,
  }
}

export default connect(mapStateToProps,mapDispatchToProps)(Save);;