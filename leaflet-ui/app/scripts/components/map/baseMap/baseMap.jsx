import React from 'react';
import { connect } from 'react-redux'
require('./baseMap.scss');

class Basemap extends React.Component {

  constructor() {
    super();
    this.state = {'showBasemap': false};
  }

  toggleBasemapView() {
    this.setState({'showBasemap': !this.state.showBasemap});
  }

  render() {
    return (
      <li ><div className="options-icons basemaps" onClick={this.toggleBasemapView.bind(this)}></div><span onClick={this.toggleBasemapView.bind(this)}>Basemap</span>
        {this.state.showBasemap?
          <div className="basemap-container">
            <h2>Select Basemap</h2>
            <br />
            <div className="basemap-row">
              <div className="basemap-item">
                <div className="basemap-image"> </div>
                <span className="basemap-label">Imagery</span> 
              </div>
              <div className="basemap-item">
                <div className="basemap-image"> </div>
                <span className="basemap-label">Street</span> 
              </div>                             
            </div> 
            <div className="basemap-row">
              <div className="basemap-item">
                <div className="basemap-image"> </div>
                <span className="basemap-label">Ligth Gray</span> 
              </div>
              <div className="basemap-item">
                <div className="basemap-image"> </div>
                <span className="basemap-label">Dark Gray</span> 
              </div>                             
            </div>
            <div className="basemap-row">
              <div className="basemap-item">
                <div className="basemap-image"> </div>
                <span className="basemap-label">Satellite</span> 
              </div>
              <div className="basemap-item">
                <div className="basemap-image"> </div>
                <span className="basemap-label">Topographic</span> 
              </div>                             
            </div>
            <div className="basemap-row">
              <div className="basemap-item">
                <div className="basemap-image"> </div>
                <span className="basemap-label">Oceans</span> 
              </div>
              <div className="basemap-item">
                <div className="basemap-image"> </div>
                <span className="basemap-label">Open Street Map</span> 
              </div>                             
            </div>
            <hr /> 
          </div>
        : null}
      </li>
    );
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onSetFundingType: (type) => {
      dispatch(setFundingType(type));
    }
  }
}

const mapStateToProps = (state, props) => {
  return {
    settings: state.settings
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Basemap);;
