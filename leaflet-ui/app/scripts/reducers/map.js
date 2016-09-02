import {REFRESH_LAYER,TOGGLE_LEGENDS_VIEW, SET_FUNDING_TYPE, SET_BASEMAP, LAYER_LOAD_SUCCESS, LAYER_LOAD_FAILURE, TOGGLE_LAYER, SET_LAYER_SETTING, INDICATOR_LIST_LOADED, GEOPHOTOS_LIST_LOADED, STATE_RESTORE, CHANGE_MAP_BOUNDS} from '../constants/constants';
import JenksCssProvider from '../util/jenksUtil.js'
import Immutable from 'immutable';
import {getPath, getShapeLayers, createCSSProviderInstance, getStyledGeoJson, 
  createLegendsByDomain , getVisibles,getValues} from '../util/layersUtil.js';

  const geophotosIndex = 1;
  const statsIndex = 2;
  const indicatorsIndex = 3;
  const size = 9;

  const defaultState = Immutable.fromJS(
  {

    bounds: {
      southWest: {
        lat: 4.3245014930192,
        lng: 115.224609375
      },
      northEast: {
        lat: 23.140359987886118,
        lng: 134.3408203125
      }
    },

    basemap: {
      id: 'openstreetmap',
      url: '//{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
    },


    legends: {
      visible: false
    },

    layers: [
    {
      id: '0',
      keyName: 'projects',
      layers: [
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
          supportFilters: true
        }
        ]
      }, 
      {
        id: '1',
        keyName: 'photos',
        layers: [{
          id: '1-0',
          keyName: 'geophotos',
          helpKey: "help.toolview.geophotos",
          type: 'clustered',
          ep: 'GEOPHOTOS_GEOJSON',
          cssPrefix: 'geophotos', //markers css prefix 
          default: false,
          border: 2,
          popupId:"PhotoPopup",
          name:'Geotagged Photos',
          computeOnload:false, //disable css addition for this later
          legends:[
            {cls: 'legend-photos more-100', 'label': 'more than 100'},
            {cls: 'legend-photos less-100', 'label': 'less than 100'},
            {cls: 'legend-photos less-10', 'label': 'less than 10'},
            {cls: 'legend-photos single-photo', 'label': 'single photo'},
          ]

        }]
      }, 
      {
        id: '2',
        keyName: 'stats',
        layers: [{
          id: '2-0',
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
          popupId: "projectPopup",
          supportFilters: true
        }]
      }, 
      {
        id: '3',
        keyName: 'indicators',
        layers: []
      },
      {
        id: '4',
        keyName: 'progress',
        layers: [{
          id: '4-0',
          type: 'shapes',
          ep: 'PHYSICAL_GEOJSON',
          settings: {
            'css': 'red','valueProperty':'physicalProgress'
          },
          cssPrefix: 'funding', //markers css prefix 
          default: false,
          border: 2,
          name:'Physical Progress',
          zIndex: 99,
          cssProvider: JenksCssProvider,
          thresholds: 5,
          keyName: 'physical',
          popupId: "defaultPopup",
          supportFilters: true
        }]
      }

      ]
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
        zIndex: 98,
        cssPrefix:'indicators',
        cssProvider: JenksCssProvider,
        thresholds: 5,
        valueProperty: "value",
        name,
        popupId: "defaultPopup",
        settings: {
          'css': css || 'red'
        }
      }
    });

    return state.setIn(["layers", indicatorsIndex, "layers"], Immutable.fromJS(layers));
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

//get current layer configuration by path
const getLayer=(state,id)=>{
  return state.getIn(getPath(id))
}


/*Extract layer properties and create legends*/
const getLegends=(settings,classProviderInstance)=>{
  const {cssPrefix,css}=settings;
  return createLegendsByDomain(classProviderInstance.getDomain(),cssPrefix,css);
}

/*Extract layer properties and set feature styles*/
const makeStyledGeoJson=(settings,data, fundingType, classProviderInstance)=>{
  const {valueProperty, size, border, popupId, name,css,cssPrefix}=settings;
  return  getStyledGeoJson(data,{valueProperty, size, border, popupId, name,fundingType,css,cssPrefix},classProviderInstance);

}

const getLayerSettings=(layer)=>{
  const thresholds = layer.get('thresholds')
  const cssProvider = layer.get('cssProvider');
  const valueProperty =layer.get('valueProperty') || layer.getIn(['settings','valueProperty']);
  const cssPrefix=layer.get('cssPrefix');
  const css=layer.getIn(['settings','css']);
  const name=layer.get('name');
  const popupId=layer.get('popupId');
  const border=layer.get('border');
  const size=layer.get('size');
  return {thresholds,cssProvider,valueProperty,cssPrefix,css,name,popupId,border,size}
}

/*Extract layer properties and create class provider */
const getClassProvider=(settings,features,fundingType)=>{
  const {thresholds,cssProvider,valueProperty}=settings;
  const values = getValues(features, valueProperty, fundingType); //extract values from features 
  return createCSSProviderInstance(thresholds, values, cssProvider);
}


const onLoadLayer = (state,action) => {
  var {id, data, fundingType} = action;
  
  if (state.getIn(getPath(id, ["keyName"])) == "projects") {
    state = resize(state, id); 
  }

   return updateLayer(state,action)
  
}

const onToggleLayer=(state,action)=>{
  var {id, visible} = action;
  if (getType(state, id) == "shapes") {
    getShapeLayers(state.get('layers')).forEach(l=>{
      state=state.setIn(getPath(l.get('id'), ['visible']), false)
    }); 
  }
  return state.setIn(getPath(id, ['visible']), !visible)
}



const onSetSetting=(state,action)=>{
  var {id, name, value} = action;
  
  return state.setIn(getPath(id, ["settings", name]), value);
}

const updateLayer=(state,action,id)=>{

  var {fundingType,data} = action;
  id= id || action.id; 
  if (state.getIn(getPath(id, ["computeOnload"])) == false) {
    return state.setIn(getPath(id, ["data"]),action.data);
  }
 
  const layer=state.getIn(getPath(id));
  data= data || layer.get('data').toJS();


  const {features}=data;

  const classProviderInstance = getClassProvider(getLayerSettings(layer),features,fundingType);
  const newData=Immutable.fromJS(makeStyledGeoJson(getLayerSettings(layer),data,fundingType,classProviderInstance));
  const newLegends=Immutable.fromJS(getLegends(getLayerSettings(layer),classProviderInstance));

  const legendPath=getPath(id, ["legends"]);
  const dataPath=getPath(id, ["data"]);
  return state.setIn(dataPath,newData).setIn(legendPath,newLegends);
}

const onChangeFundingType=(state,action)=>{
  var {fundingType} = action;
  let layers = getVisibles(state.get('layers'));
  
  layers.forEach((layer)=>{
      state=updateLayer(state,action,layer.get('id')) //update this layer

    })
  return state;
}


const map = (state = defaultState, action) => {
  let newState;
  switch (action.type) {

    case TOGGLE_LAYER:
    return onToggleLayer(state,action);

    case SET_LAYER_SETTING:
    return onSetSetting(state,action);

    case REFRESH_LAYER:
    return updateLayer(state,action);

    case LAYER_LOAD_SUCCESS:
    return onLoadLayer(state,action);

    case SET_FUNDING_TYPE:
    return onChangeFundingType(state,action);

    case SET_BASEMAP:
    return state.set('basemap', Immutable.fromJS(action.basemap));

    case STATE_RESTORE:
    return  Immutable.fromJS( action.storedMap.data.map);

    case CHANGE_MAP_BOUNDS:
    return state.set('bounds', Immutable.fromJS({southWest:action.bounds._southWest, northEast:action.bounds._northEast}));

    case INDICATOR_LIST_LOADED:
    return setIndicators(state, action.data);

    case GEOPHOTOS_LIST_LOADED:
    
    //return setGeophotos(state, action.data);
    
    case TOGGLE_LEGENDS_VIEW:
    return state.setIn(['legends', 'visible'], !state.getIn(['legends', 'visible']));

    case LAYER_LOAD_FAILURE:
    console.log('Error loading layer',action)
    default:
    return state
  }
}

export default map;