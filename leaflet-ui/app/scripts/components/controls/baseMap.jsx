import React from 'react';
import {connect} from 'react-redux';
import {setBaseMap} from '../../actions/map';
import translate from '../../util/translate.js';

require('./baseMap.scss');

const basemaps = [
  {id: 'openstreetmap', url: '//{s}.tile.openselectstreetmap.org/{z}/{x}/{y}.png'},
  {id: 'imagery', url: '//server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}'},
  {id: 'topographic', url: '//server.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer/tile/{z}/{y}/{x}'},
  {id: 'terrain', url: '//server.arcgisonline.com/ArcGIS/rest/services/World_Terrain_Base/MapServer/tile/{z}/{y}/{x}'},
  {id: 'oceans', url: '//server.arcgisonline.com/ArcGIS/rest/services/Ocean_Basemap/MapServer/tile/{z}/{y}/{x}'},
  {id: 'street', url: '//server.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer/tile/{z}/{y}/{x}'},
  {
    id: 'gray',
    url: '//server.arcgisonline.com/ArcGIS/rest/services/Canvas/World_Light_Gray_Base/MapServer/tile/{z}/{y}/{x}'
  },
  {id: 'darkgray', url: '//{s}.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png'}
];

class BaseMapItem extends React.Component {
  
  select() {
    const {id, url} = this.props;
    
    this.props.onSetBaseMap({id: id, url: url});
  }
  
  render() {
    const {id, selected, url} = this.props;
    
    return (
      <div className="item" onClick={this.select.bind(this)}>
        <div className={selected ? "image " + id + " selected" : "image " + id}></div>
        <span className="label">{translate(`header.basemap.${id}`)}</span>
      </div>
    )
  }
}

class Basemap extends React.Component {
  render() {
    const {visible, map} = this.props;
    const baseSelected = map.get('basemap').get('id');
    
    return (
      <div>{visible ?
        <div className="basemaps-container">
          <h2>{translate('header.basemap.select')}</h2>
          <div className="list">
            {basemaps.map((item) => {
              return <BaseMapItem key={item.id} {...item} {...this.props} selected={item.id == baseSelected}/>
            })}
          </div>
        </div>
        : null}
      </div>
    );
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onSetBaseMap: (baseMap) => {
      dispatch(setBaseMap(baseMap));
    }
  }
};

const mapStateToProps = (state, props) => {
  return {
    map: state.map
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(Basemap);

