import {SET_BASEMAP, LAYER_LOAD_SUCCESS, LAYER_LOAD_FAILURE, TOGGLE_LAYER, SET_LAYER_SETTING, INDICATOR_LIST_LOADED, STATE_RESTORE} from '../constants/constants';
import JenksCssProvider from '../util/jenksUtil.js'
import Immutable from 'immutable';
import {getPath,getShapeLayers} from '../util/layersUtil.js';

const statsIndex = 1;
const indicatorsIndex = 2;
const size = 9;

const defaultState = Immutable.fromJS(
{

  bounds:{
      southWest: [4.3245014930192, 115.224609375],
      northEast:[23.140359987886118,134.3408203125]
  },
  basemap: {
    name: 'street',
    url: '//server.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer/tile/{z}/{y}/{x}'
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
                supportFilters:true
            }
          ]
  } 
, {
  id: '1',
  keyName: 'stats',
  layers: [{
            id: '1-0',
            type: 'shapes',
            ep: 'FUNDING_GEOJSON',
            settings: {
              'css': 'red'
            },
            default: false,
            border: 2,
            zIndex: 99,
            cssProvider: JenksCssProvider,
            thresholds: 5,
            valueProperty: "projectCount",
            keyName: 'funding',
            zIndex: 99,
            supportFilters:true
          }
        ]
},
{
  id: '2',
  keyName: 'indicators',
  layers: []
}

, {
  id: '3',
  keyName: 'geophotos',
   layers: []
}]
});



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
  
  return state.setIn(["layers",indicatorsIndex,"layers"], Immutable.fromJS(layers));
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
    var {id, data} = action;
    if (getType(state, id) == "points") {
      state = resize(state, id)
    }
    return state.setIn(getPath(id, ["data"]), Immutable.fromJS(data));;

    case SET_BASEMAP:
    return state.set('basemap', Immutable.fromJS(action.basemap));

    case STATE_RESTORE:
    //restore 1) zoom and center,or map bounds, and layers  
    debugger;
    console.log('map STATE_RESTORE');
    return state.set('basemap', Immutable.fromJS(action.mapData.filters.basemap));

    case INDICATOR_LIST_LOADED:
    return setIndicators(state, action.data);

    case LAYER_LOAD_FAILURE:
    default:

    return state
  }
}

export default map;