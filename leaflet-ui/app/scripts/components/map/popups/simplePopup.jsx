
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
    const {name, value,avgActual,avgTarget, layerName,valueProperty} = ftr.properties;
    debugger;   
    return (
      <div className="simple-popup-container">
      
      <div className="popup-title">
         <h2>{name}</h2>
          <div className="simple-popup-value">
       
              {valueProperty=='avgTarget'?<div>{layerName}<div> Average Target : {avgTarget}</div></div>:null}
              {valueProperty=='avgActual'?<div>{layerName} <div>Average Actual : {avgActual}</div></div>:null}
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

