import React from 'react';
import { connect } from 'react-redux'
import { setBaseMap } from '../../../actions/map'
import onClickOutside from 'react-onclickoutside'
import translate from '../../../util/translate.js';

require('./baseMap.scss');

var basemaps = {
  'street': '//server.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer/tile/{z}/{y}/{x}',
  'imagery':  '//server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}',
  'topographic': '//server.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer/tile/{z}/{y}/{x}',
  'terrain': '//server.arcgisonline.com/ArcGIS/rest/services/World_Terrain_Base/MapServer/tile/{z}/{y}/{x}',

  'gray': '//server.arcgisonline.com/ArcGIS/rest/services/Canvas/World_Light_Gray_Base/MapServer/tile/{z}/{y}/{x}',
  'oceans': '//server.arcgisonline.com/ArcGIS/rest/services/Ocean_Basemap/MapServer/tile/{z}/{y}/{x}',
  'openstreetmap': '//{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
  'darkgray': '//{s}.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png'
}

const Basemap = onClickOutside(React.createClass({

  getInitialState() {
    return {'showBasemap': false};
  },

  toggleBasemapView() {
    this.setState({'showBasemap': !this.state.showBasemap});
  },

  changeBaseMap(basemap){
    this.props.onSetBaseMap({name: basemap, url: basemaps[basemap]});
  },

  handleClickOutside (evt) {
    if (this.state.showBasemap){
      this.setState({'showBasemap': false});
    }
  },

  render() {
    let baseSelected = this.props.map.get('basemap').get('name');
    return (
      <li ><div className="options-icons basemaps" onClick={this.toggleBasemapView}></div><span onClick={this.toggleBasemapView}>{translate("header.basemap.title")}</span>
        {this.state.showBasemap?
          <div className="basemap-container">
            <h2>{translate('header.basemap.select')}</h2>
            <br />
            <div className="basemap-row">
              <div className="basemap-item imagery" onClick={this.changeBaseMap.bind(this, 'imagery')}>
                <div className={baseSelected=='imagery'? "basemap-image imagery selected" : "basemap-image imagery"}> </div>
                <span className="basemap-label">{translate('header.basemap.imagery')}</span> 
              </div>
              <div className="basemap-item" onClick={this.changeBaseMap.bind(this, 'street')}>
                <div className={baseSelected=='street'? "basemap-image street selected" : "basemap-image street"}> </div>
                <span className="basemap-label">{translate('header.basemap.street')}</span> 
              </div>                             
            </div> 
            <div className="basemap-row">
              <div className="basemap-item" onClick={this.changeBaseMap.bind(this, 'gray')}>
                <div className={baseSelected=='gray'? "basemap-image gray selected" : "basemap-image gray"}> </div>
                <span className="basemap-label">{translate('header.basemap.ligthgray')}</span> 
              </div>
              <div className="basemap-item" onClick={this.changeBaseMap.bind(this, 'darkgray')}>
                <div className={baseSelected=='darkgray'? "basemap-image darkgray selected" : "basemap-image darkgray"}> </div>
                <span className="basemap-label">{translate('header.basemap.darkgray')}</span> 
              </div>                             
            </div>
            <div className="basemap-row">
              <div className="basemap-item" onClick={this.changeBaseMap.bind(this, 'terrain')}>
                <div className={baseSelected=='terrain'? "basemap-image terrain selected" : "basemap-image terrain"}> </div>
                <span className="basemap-label">{translate('header.basemap.terrain')}</span> 
              </div>
              <div className="basemap-item" onClick={this.changeBaseMap.bind(this, 'topographic')}>
                <div className={baseSelected=='topographic'? "basemap-image topographic selected" : "basemap-image topographic"}> </div>
                <span className="basemap-label">{translate('header.basemap.topographic')}</span> 
              </div>                             
            </div>
            <div className="basemap-row">
              <div className="basemap-item" onClick={this.changeBaseMap.bind(this, 'oceans')}>
                <div className={baseSelected=='oceans'? "basemap-image oceans selected" : "basemap-image oceans"}> </div>
                <span className="basemap-label">{translate('header.basemap.oceans')}</span> 
              </div>
              <div className="basemap-item" onClick={this.changeBaseMap.bind(this, 'openstreetmap')}>
                <div className={baseSelected=='openstreetmap'? "basemap-image openstreet selected" : "basemap-image openstreet"}> </div>
                <span className="basemap-label">{translate('header.basemap.openstreetmap')}</span> 
              </div>                             
            </div>
          </div>
        : null}
      </li>
    );
  }
}));

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onSetBaseMap: (baseMap) => {
      dispatch(setBaseMap(baseMap));
    }
  }
}

const mapStateToProps = (state, props) => {
  return {
    map: state.map
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Basemap);;
