import {SET_BASEMAP,LAYER_LOAD_SUCCESS,LAYER_LOAD_FAILURE,TOGGLE_LAYER,SET_LAYER_SETTING,INDICATOR_LIST_LOADED} from '../constants/constants';
import JenksCssProvider from '../util/jenksUtil.js'

import Immutable from 'immutable';
const statsIndex=1;
const indicatorsIndex=1;
const size=9;
const defaultState = Immutable.fromJS({
  basemap: {name: 'street', url: '//server.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer/tile/{z}/{y}/{x}'},
  layers: [
  {
    id: '0', //unique id 
    defaultLoaded: true,
    ep:'PROJECT_GEOJSON',  //api end point 
    settings:{'level': 'region','css': 'yellow'}, //settings
    keyName: 'projects', //i18n key
    cssPrefix:'points', //markers css prefix 
    zIndex:100,
    size:size, //size of markers
    border:8, //size of stroke borders 
    valueProperty:"projectCount", //value property   
    cssProvider:JenksCssProvider, //color provider 
    thresholds:5  ,  //number of breaks 
  }, 
  {
    id: '1',
    keyName: 'stats',
    layers: [
    { id: '10',
      ep:'FUNDING_GEOJSON',
      settings:{'quality':0.1,'css':'red'},
      cssPrefix:'points',
      zIndex:99,
      cssProvider:JenksCssProvider,
      thresholds:5, 
      valueProperty:"projectCount", 
      keyName: 'funding',
      'cssPrefix':'shapes',
      'zIndex':99
    }, 
    {id: '11', keyName: 'indicators', layers: []}]}, 
    {id:'2',keyName: 'geophotos'}
    ]
  });


const addIndicatorLayers=(newState,indicators)=>{
  const layerList=new Immutable.List(indicators.map(it=>{
    const {id,colorScheme:css,name,keyName,unit} = it;
    const layerId=statsIndex+""+indicatorsIndex+""+id;
    return new Immutable.fromJS({
        id:layerId, 
        ep:"INDICATOR", 
        indicator_id:id,
        keyName,
        cssProvider:JenksCssProvider,
        thresholds:5, 
        valueProperty:"value",  name ,  
        settings:{'quality':0.1,'css':css||'red'}
      }) 
  }));

  return newState.setIn(["layers",statsIndex,"layers",indicatorsIndex,"layers"],layerList);
}

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
      /*if (action.name=='level' && action.value=='region'){
       state= state.setIn(["layers",0,"size"],size);
      }
      if (action.name=='level' && action.value=='province'){
       state= state.setIn(["layers",0,"size"],size/2);
      }
      if (action.name=='level' && action.value=='municipality'){
       state= state.setIn(["layers",0,"size"],size/3);
      }*/
       return state.set('layers', setSettingToLayer(state.get('layers'), action.id, action.name, action.value));

    case LAYER_LOAD_SUCCESS:
       return state.set('layers', setPropsToLayer(state.get('layers'), action.id, {data:action.data}));    
      
    case SET_BASEMAP:
      return  state.set('basemap', Immutable.fromJS(action.basemap));    
    case INDICATOR_LIST_LOADED:
      return  addIndicatorLayers(state,action.data);
    case LAYER_LOAD_FAILURE:
    default:

    return state
  }
}


export default map;

