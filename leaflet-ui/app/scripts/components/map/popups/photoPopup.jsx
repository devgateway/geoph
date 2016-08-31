
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
  const {name,urls}   =properties;
  console.log(urls)
     //const {feature: ftr={properties: {value: '', locationName: ''}}} = this.props;
    //const {locationName, value, layerName} = ftr.properties;
    return (
      <div>
        <h2>{name}</h2>

         {urls.map(url=>{return <div><img width="300" src={url}/></div>})}
        
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

