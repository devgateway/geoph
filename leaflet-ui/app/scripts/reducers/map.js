import {LOAD_PROJECT_GEOJSON_SUCCESS,LOAD_PEOJECT_GEOJSON_FAILED } from '../constants/constants';

const map = (state = {
  layers: {
    projects: {
      'name': 'Project Layers',
      'data': null
    }
  }
}, action) => {
;
  switch (action.type) {
    case LOAD_PROJECT_GEOJSON_SUCCESS:
    let newState=Object.assign({}, state)
    return  Object.assign(newState, {
      layers:{
        level:action.level,
        projects: {
          data: action.data,
          name: 'Project Layers'
        }}
      });
    case LOAD_PEOJECT_GEOJSON_FAILED:
    return null;
    default:
    return state

  }
}



export
default map;