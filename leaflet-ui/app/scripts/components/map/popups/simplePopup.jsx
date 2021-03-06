import React from 'react';
import { connect } from 'react-redux'
import onClickOutside from 'react-onclickoutside'

require('./projectLayerPopup.scss');

class SimplePopup extends React.Component {
  handleClickOutside(evt) {
    if (this.props.onClosePopup) {
      this.props.onClosePopup();
    }
  }
  
  /**
   * Format a number with commas.
   */
  numberWithCommas = (x) => {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  }
  
  render() {
    const {feature} = this.props;
    if (!feature) {
      return null;
    }
    
    const {name, value, physicalProgress, layerName, valueProperty, indicatorName} = feature.properties;
    
    return (
      <div className="simple-popup-container">
        <div className="popup-title">
          <h2>{name}</h2>
          <div className="simple-popup-value">
            {valueProperty === 'physicalProgress'
              ? <div>{layerName} : {Number(physicalProgress).toFixed(2)}%</div>
              : null}
            {valueProperty === 'value' ? <div>{indicatorName} : {this.numberWithCommas(value) || "No Data"}</div> : null}
          </div>
        </div>
      </div>
    
    )
  }
}

const mapStateToProps = (state, props) => {
  const { mapId } = props;
  
  let filters;
  let fundingType;
  let projectSearch;
  if (mapId === 'main') {
    filters = state.filters.filterMain;
    fundingType = state.settings.fundingType;
    projectSearch = state.projectSearch;
  } else {
    // here id should be 'left'
    filters = state.compare.get("filters") !== undefined ? state.compare.get("filters").filterMain : undefined;
    fundingType = state.compare.get("settings") !== undefined ? state.compare.get("settings").fundingType : {};
    projectSearch = state.compare.get("projectSearch");
  }
  
  return {
    fundingType: fundingType,
    charts: state.popup[mapId],
    filters: filters,
    projectSearch: projectSearch,
    language: state.language
  }
};

export default connect(mapStateToProps)(onClickOutside(SimplePopup));

