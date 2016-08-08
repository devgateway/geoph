
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

  createMarkupHTML(){ 
    return {__html: "<div className='photo-popup-container'>"+this.props.feature.properties.popupHTML+"</div>"}; 
  },

  render() {
    //const {feature: ftr={properties: {value: '', locationName: ''}}} = this.props;
    //const {locationName, value, layerName} = ftr.properties;
    return (
      <div dangerouslySetInnerHTML={this.createMarkupHTML()}></div>
    )
  }
}));

const mapStateToProps = (state, props) => {
  return {
    language: state.language
  }
}

export default connect(mapStateToProps)(PhotoPopup);;

