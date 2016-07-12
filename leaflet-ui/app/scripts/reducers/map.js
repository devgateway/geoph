import {SET_BASEMAP, LAYER_LOAD_SUCCESS,LAYER_LOAD_FAILURE , TOGGLE_LAYER,SET_LAYER_SETTING, REQUEST_RESTORE_MAP} from '../constants/constants';

import Immutable from 'immutable';

const defaultState = Immutable.fromJS({
  basemap: {name: 'street', url: '//server.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer/tile/{z}/{y}/{x}'},
  layers: [
    {
      id: '0',
      defaultLoaded: true,
      ep:'PROJECT_GEOJSON',
      settings:{'level': 'region','css': 'yellow'},
      keyName: 'projects',
      'cssPrefix':'points',
      'zIndex':100, 
    }, 
    {
      id: '1',
      keyName: 'stats',
      layers: [{
        id: '10',
        ep:'FUNDING_GEOJSON',
        settings:{'quality':0.1,'css':'red'},
        keyName: 'funding',
        'cssPrefix':'shapes',
        'zIndex':99,
      }, {
        id: '11',
        keyName: 'indicators',
        layers: [{
          id: '111',
          keyName: 'poverty'
        }, {
          id: '112',
          keyName: 'population'
        }]
      }]
    }, 
    {
      id: '2',
      keyName: 'geophotos'
    }
  ]
});



const setPropsToLayer=(layers, id, properties)=>{
  return layers.map((l)=>{
    if (l.get('id')==id){
      return l.merge(properties);
    } else if (l.get('layers')){
      l=l.set('layers', setPropsToLayer(l.get('layers'), id, properties))
      return l;
    }
    return l;
  });
}

const setSettingToLayer=(layers, id, name, value)=>{
  return layers.map((l)=>{
    if (l.get('id')==id){
      return l.setIn(['settings', name], value);
    } else if (l.get('layers')){
      return l.set('layers', setSettingToLayer(l.get('layers'), id, name, value));
    }
    return l;
  });
}

const setProps=(layers, props)=>{
  return layers.map((l)=>{
    if (l.get('layers')){
      l=l.set('layers', setProps(l.get('layers'), props));
    }
    return l.merge(props);
  })
}


const toogle=(layers, id, property, visible)=>{
  return layers.map((l)=>{
    if (l.get('id')==id || id==null){
      if (visible==null){
        l=l.set(property, !l.get(property)); //toggle property of current layer
      } else {
        l=l.set(property, visible);
      }
      if (l.get('layers')){
          //if it is a group update child with curren parrent value
        l=l.set('layers', setProps(l.get('layers'), {'visible':l.get('visible')})) 
      }
      return l;
    } else if (l.get('layers')){
       return l.set('layers', toogle(l.get('layers'), id, property, visible))
    }
    return l;
  })
}


const map = (state = defaultState, action) => {
  let newState;
  switch (action.type) {

    case TOGGLE_LAYER:
    return state.set('layers', toogle(state.get('layers'), action.id, 'visible', action.visible));

    case SET_LAYER_SETTING:
    console.log(action.name+'--'+action.value);
    return state.set('layers', setSettingToLayer(state.get('layers'), action.id, action.name, action.value));

    case LAYER_LOAD_SUCCESS:
    newState= state.set('layers', setPropsToLayer(state.get('layers'), action.id, {data:action.data}));    
    return newState;
    
    case SET_BASEMAP:
    newState= state.set('basemap', Immutable.fromJS(action.basemap));    
    return newState;

    case REQUEST_RESTORE_MAP:
      console.log('basemap reducer');
      let mapToRestore = JSON.parse(action.restoreMap.mapToRestore);
      if(mapToRestore.basemap){
        newState= state.set('basemap', Immutable.fromJS(mapToRestore.basemap));    
        return newState;  
      } else {
        return state;
      }
    
    
    case LAYER_LOAD_FAILURE:
    default:
    
    return state
  }
}


export default map;

