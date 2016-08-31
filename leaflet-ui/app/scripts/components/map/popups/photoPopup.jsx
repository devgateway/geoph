
import React from 'react';
import { connect } from 'react-redux'
import onClickOutside from 'react-onclickoutside'
import translate from '../../../util/translate.js';

require('./projectLayerPopup.scss');

const PhotoPopup = onClickOutside(React.createClass({

  getInitialState() {
    return {};
  },

  handleClickOutside (evt) {
    if (this.props.onClosePopup){
      this.props.onClosePopup();
    }
  },


  render() {
    debugger;
    //const {feature: ftr={properties: {value: '', locationName: ''}}} = this.props;
    //const {locationName, value, layerName} = ftr.properties;
    return (
      <div>
        <h2>Title</h2>
          foto1 
          foto2
      </div>
    )
  }
}));

const mapStateToProps = (state, props) => {
  return {
    language: state.language
  }
}

export default connect(mapStateToProps)(PhotoPopup);;

