
import React from 'react';
import { connect } from 'react-redux'
import onClickOutside from 'react-onclickoutside'
import translate from '../../../util/translate.js';

require('./projectLayerPopup.scss');

const SimplePopup = onClickOutside(React.createClass({

  getInitialState() {
    return {};
  },

  handleClickOutside (evt) {
    if (this.props.onClosePopup){
      this.props.onClosePopup();
    }
  },

  render() {
    const {feature: ftr={properties: {value: '', locationName: ''}}} = this.props;
    const {locationName, value, layerName} = ftr.properties;
    return (
      <div className="simple-popup-container">
        <div className="popup-title">
          <h2>{locationName || ""} </h2>
        </div>
        <div className="simple-popup-value">{layerName}: <div>{value}</div></div>
      </div>
    )
  }
}));

const mapStateToProps = (state, props) => {
  return {
    fundingType: state.settings.fundingType,
    charts: state.popup,
    filters: state.filters.filterMain,
    projectSearch: state.projectSearch,
    language: state.language
  }
}

export default connect(mapStateToProps)(SimplePopup);;

