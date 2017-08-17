import React from 'react';
import L from 'leaflet';
import { connect } from 'react-redux';
import { loadProjects, updateBounds } from '../../actions/map.js';
import { Map, TileLayer } from 'react-leaflet';
import SvgLayer from './layers/svg.jsx';
import ClusteredLayer from './layers/clusteredLayer.jsx';
import ProjectPopup from './popups/projectLayerPopup';
import SimplePopup from './popups/simplePopup';
import PhotoPopup from './popups/photoPopup';
import {getVisibles} from '../../util/layersUtil.js';
import Legends from './legends/legends';

require('leaflet/dist/leaflet.css');
require('./map.scss');

const MapView = React.createClass({
  
  getInitialState() {
    return {bounds: {}};
  },
  
  handleChangeBounds(e) {
    this.props.onUpdateBounds(e.target.getBounds());
  },
  
  closePopup() {
    let map = this.refs.map;
    if (map) {
      map.leafletElement.closePopup();
    }
  },
  
  getPopUp(id) {
    if (id === "projectPopup") {
      return (<ProjectPopup onClosePopup={this.closePopup}/>)
    }
    if (id = "defaultPopup") {
      return (<SimplePopup onClosePopup={this.closePopup}/>)
    }
    
    if (id = "photoPopup") {
      return <PhotoPopup onClosePopup={this.closePopup}/>
    }
  },
  
  getLayer(l) {
    const {data, type, popupId, id, zIndex, settings} = l;
    const {showLabels} = settings || {};
    
    if (type === 'clustered') {
      return (
        <ClusteredLayer key={id} data={data}>
          <PhotoPopup onClosePopup={this.closePopup}/>
        </ClusteredLayer>
      );
    } else {
      return (
        <SvgLayer showLabels={showLabels} key={id} id={id} zIndex={zIndex} features={data.features}>
          {this.getPopUp(popupId)}
        </SvgLayer>
      )
    }
  },
  
  render() {
    const {map} = this.props;
    const {southWest, northEast} = map.get('defaultBounds').toJS();
    const bounds = L.latLngBounds(L.latLng(southWest.lat, southWest.lng), L.latLng(northEast.lat, northEast.lng));
    let layers = getVisibles(this.props.map.get('layers')).toJS();
    let loading = map.get('loading');
    
    return (
      <div className={loading ? "half-opacity" : ""}>
        <Map ref="map" className="map" bounds={bounds} onMoveEnd={this.handleChangeBounds}>
          <TileLayer url={this.props.map.get('basemap').get('url')}/>
          {layers.map((l) => {
            const {data, type} = l;
            return (data && data.features) ? this.getLayer(l) : null;
          })}
        </Map>
        <Legends layers={this.props.map.get('layers')}/>
        {loading
          ? <div className="loading-map">
            <div className="loading-css">
              <div></div>
            </div>
          </div>
          : null}
      </div>
    );
  }
});

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onUpdateBounds: (newBounds) => {
      dispatch(updateBounds(newBounds));
    }
  }
};

const stateToProps = (state, props) => {
  return {
    map: state.map,
    fundingType: state.settings.fundingType
  };
};

export default connect(stateToProps, mapDispatchToProps)(MapView);

