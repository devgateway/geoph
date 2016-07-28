
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
    const {name, feature} = this.props;
    let value = feature? feature.properties.value : '';
    return (
      <div className="simple-popup-container">
        <div className="popup-title">
          <h2>{name || ""} </h2>
        </div>
        <div className="simple-popup-value">
          Value: <span>{value}</span>
        </div>  
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

