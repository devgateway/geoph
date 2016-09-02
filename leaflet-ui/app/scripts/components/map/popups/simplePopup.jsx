
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
    
    const {feature} = this.props;
    if (!feature){
      return null;
    }
    const {name, value, physicalProgress, layerName, valueProperty} = feature.properties;
    return (
      <div className="simple-popup-container">
        <div className="popup-title">
          <h2>{name}</h2>
           <div className="simple-popup-value">
              {valueProperty=='physicalProgress'?<div>{layerName}<div> Value: {Number(physicalProgress).toFixed(2)}%</div></div>:null}
              {valueProperty=='value'?<div>{layerName} : {value}</div>:null}
            </div>
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

