import {TOGGLE_LEGENDS_VIEW, SET_FUNDING_TYPE, SET_BASEMAP, LAYER_LOAD_SUCCESS, LAYER_LOAD_FAILURE, TOGGLE_LAYER, SET_LAYER_SETTING, INDICATOR_LIST_LOADED, GEOPHOTOS_LIST_LOADED, STATE_RESTORE, CHANGE_MAP_BOUNDS} from '../constants/constants';
import JenksCssProvider from '../util/jenksUtil.js'
import Immutable from 'immutable';
import {getPath, getShapeLayers, createLegendsAndClassesForLayer, getVisibles} from '../util/layersUtil.js';

const statsIndex = 1;
const indicatorsIndex = 2;
const geophotosIndex = 3;
const size = 9;

const defaultState = Immutable.fromJS(
{

  bounds:{
      southWest: {lat:4.3245014930192,lng: 115.224609375},
      northEast:{lat:23.140359987886118,lng:134.3408203125}
  },

  basemap: {
    id:'openstreetmap',
    url: '//{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
  },    

  legends: {
    visible: false
  },    

  layers: [
    {
      id:0,
      keyName:'projects',
      layers:[
          {
            id: '0-0', //unique id 
            default: true,
            type: 'points',
            ep: 'PROJECT_GEOJSON', //api end point 
            settings: {
              'level': 'region',
              'css': 'yellow'
            }, //settings
            keyName: 'projects', //i18n key
            cssPrefix: 'points', //markers css prefix 
            zIndex: 100,
            size: size, //size of markers
            border: 4, //size of stroke borders 
            valueProperty: "projectCount", //value property 
            cssProvider: JenksCssProvider, //color provider 
            thresholds: 5, //number of breaks 
            popupId: "projectPopup",   
            supportFilters:true
        }
      ]
    },
    {
      id: '1',
      keyName: 'stats',
      layers: [
        {
          id: '1-0',
          type: 'shapes',
          ep: 'FUNDING_GEOJSON',
          settings: {
            'css': 'red'
          },
          cssPrefix: 'funding', //markers css prefix 
          default: false,
          border: 2,
          zIndex: 99,
          cssProvider: JenksCssProvider,
          thresholds: 5,
          valueProperty: "funding",
          keyName: 'funding',
          zIndex: 99,
          popupId: "projectPopup",   
          supportFilters:true
        }
      ]
    },
    {
      id: '2',
      keyName: 'indicators',
      layers: []
    },
    {
      id: '3',
      keyName: 'geophotos',
       layers: []
    }
  ]
}
);



const setIndicators = (state, indicators) => {
  var index = 0;
  let layers= indicators.map(it => {
    const {id, colorScheme: css,name,keyName,unit} = it;
    const layerId =  indicatorsIndex + "-" + index++;
    return {
      id: layerId,
      indicator_id: id,
      keyName,
      border:2,
      ep: "INDICATOR",
      type: 'shapes',
      zIndex: 98,
      cssPrefix:'indicators',
      cssProvider: JenksCssProvider,
      thresholds: 5,
      valueProperty: "value",
      name,
      settings: {
       'css': css || 'red'
      }
    }
  });
  
  return state.setIn(["layers", indicatorsIndex, "layers"], Immutable.fromJS(layers));
}


const setGeophotos = (state, geophotos) => {
  var index = 0;
  let layers = geophotos.map(it => {
    const {id, colorScheme: css, name} = it;
    const layerId =  geophotosIndex + "-" + index++;
    return {
      id: layerId,
      geophotos_id: id,
      zIndex: 101,
      ep: "GEOPHOTOS",
      type: 'points',
      name,
      size: 4, 
      border: 2, 
      popupId: "photoPopup",   
      cssPrefix: 'points', 
      settings: {
       'css': css || 'blue'
      }
   }
 });
  
  return state.setIn(["layers", geophotosIndex, "layers"], Immutable.fromJS(layers));
}


const getType = (state, id) => {
  return state.getIn(getPath(id, ["type"]));
}

const resize = (state, id) => {
  var level = state.getIn(getPath(id, ['settings', 'level']));
  let newSize = size;
  if (level == 'province') {
    newSize = size / 2;
  } else
  if (level == 'municipality') {
    newSize = size / 3;
  }
  return state.setIn(getPath(id, ['size']), newSize)
}


const map = (state = defaultState, action) => {
  let newState;
  switch (action.type) {
    case TOGGLE_LAYER:
      var {id, visible} = action;
      if (getType(state, id) == "shapes") {
          getShapeLayers(state.get('layers')).forEach(l=>{
              state=state.setIn(getPath(l.get('id'), ['visible']), false)
          }); 
      }
      return state.setIn(getPath(id, ['visible']), !visible)

    case SET_LAYER_SETTING:
      var {id, name, value} = action;
      return state.setIn(getPath(id, ["settings", name]), value);

    case LAYER_LOAD_SUCCESS:
      var {id, data, fundingType} = action;
      if (state.getIn(getPath(id, ["keyName"])) == "projects") {
        state = resize(state, id);
      }
      let layerProcessed = {legends: [], data: []}
      let layer = state.getIn(getPath(id)).toJS();
      if (data && data.features){
        layerProcessed = createLegendsAndClassesForLayer(layer, data.features, fundingType);
      }
      const {legends, features} = layerProcessed;
      state = state.setIn(getPath(id, ["legends"]), Immutable.fromJS(legends));
      return state.setIn(getPath(id, ["data"]), Immutable.fromJS(Object.assign(data, {features})));
     
    case SET_FUNDING_TYPE:
      var {fundingType} = action;
      let layersVisible = getVisibles(state.get('layers'));
      layersVisible.map((lyr)=>{
        let id = lyr.get('id');
        let layerProcessed = {legends: [], data: []}
        let layer = state.getIn(getPath(id)).toJS();
        if (layer.data && layer.data.features){
          layerProcessed = createLegendsAndClassesForLayer(layer, layer.data.features, fundingType);
        }
        const {legends, features} = layerProcessed;
        state = state.setIn(getPath(id, ["legends"]), Immutable.fromJS(legends));
        state = state.setIn(getPath(id, ["data"]), Immutable.fromJS(Object.assign(layer.data, {features})));
      })
      return state;
    
    case SET_BASEMAP:
      return state.set('basemap', Immutable.fromJS(action.basemap));

    case STATE_RESTORE:
      //restore 1) zoom and center,or map bounds, and layers  
      let mapData = action.storedMap.data.map;
      /*state = state.set('bounds', Immutable.fromJS({
        southWest:{lat:mapData.bounds.southWest.lat,lng: mapData.bounds.southWest.lng}, 
        northEast:{lat:mapData.bounds.northEast.lat,lng: mapData.bounds.northEast.lng}
      }));
      state = state.set('basemap', Immutable.fromJS(mapData.basemap));
      getShapeLayers(state.get('layers')).forEach(l=>{
        let visibility = mapData.visibleLayers.find(e=>{
          return e===l.get('id');})
        state=state.setIn(getPath(l.get('id'), ['visible']), visibility?true:false);
      });*/
      state = Immutable.fromJS(mapData);
      return state;

    case CHANGE_MAP_BOUNDS:
      return state.set('bounds', Immutable.fromJS({southWest:action.bounds._southWest, northEast:action.bounds._northEast}));

    case INDICATOR_LIST_LOADED:
      return setIndicators(state, action.data);

    case GEOPHOTOS_LIST_LOADED:
      return setGeophotos(state, action.data);

    case TOGGLE_LEGENDS_VIEW:
      return state.setIn(['legends', 'visible'], !state.getIn(['legends', 'visible']));

    case LAYER_LOAD_FAILURE:
    default:
      return state
  }
}

export default map;