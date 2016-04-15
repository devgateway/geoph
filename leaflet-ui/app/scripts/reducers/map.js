import {
  LOAD_PROJECT_GEOJSON_SUCCESS,
  LOAD_PEOJECT_GEOJSON_FAILED,

  LOAD_FUNDING_GEOJSON_SUCCESS,
  LOAD_FUNDING_GEOJSON_FAILED,
  TOGGLE_LAYER} from '../constants/constants';


  import {toggleLayerProperty} from '../util/layersUtil.js';

  const defaultState={
    layers: [
    {id:'0',keyName:'projects', layers:[
    {id:'02', keyName:'project'}
    ]},

    {id:'1',keyName:'stats', layers:[

    {id:'02', keyName:'funding'},

    {id:'11', keyName:'indicators',layers:[
    {id:'111', keyName:'poverty'},
    {id:'112', keyName:'population'},

    ]}

    ]},

    {id:'2',keyName:'geophotos', layers:[]}
    ]
  }





  const map = (state = defaultState, action) => {
    switch (action.type) {
     case LOAD_PROJECT_GEOJSON_SUCCESS:
     return loadDataIntoLayer(state,action.data,'projects');

     case LOAD_FUNDING_GEOJSON_SUCCESS:
     return loadDataIntoLayer(state,action.data,'funding');
     case TOGGLE_LAYER:
     let newState=toggleLayerProperty(action.id,Object.assign({},state),'visible')
      Object.assign(newState,{updated:new Date()});
      console.log(newState.updated)
     return newState;

     case LOAD_FUNDING_GEOJSON_FAILED:
     case LOAD_PEOJECT_GEOJSON_FAILED:
    //set status to error
    default:
    return state
  }
}


export default map;

