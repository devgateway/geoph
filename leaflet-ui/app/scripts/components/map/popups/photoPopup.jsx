
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
    const {feature} = this.props;
    const {properties}   =feature;
    const {name,urls,project}   =properties;
    return (
     <div className="photo-popup-container">
        <div className="popup-title">
          <h2>{project.phId} {project.title}</h2>
       </div>
        <div className="images">
         {urls.map(url=>{return <div><img width="300" src={url}/></div>})}
        </div>
      
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

