import {LOAD_PROJECT_GEOJSON_SUCCESS,LOAD_PEOJECT_GEOJSON_FAILED } from 'app/constants/constants';

const map = (state = {
  layers: {
    projects: {
      'name': 'Project Layers',
      'data': ''
    }
  }
}, action) => {

  console.log(state);
  switch (action.type) {
    case LOAD_PROJECT_GEOJSON_SUCCESS:
    let newState=Object.assign({}, state)
    return  Object.assign(newState, {
      layers:{
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