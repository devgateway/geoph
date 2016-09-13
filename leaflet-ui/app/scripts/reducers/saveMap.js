import * as Constants from '../constants/constants';
import {Map} from 'immutable'


const saveMap =(state = new Map({id:'', name:'', description:'', saving: false}), action) => {

  //console.log("--- saveMap reducer ---" + action);
  switch (action.type) {
    
    case Constants.SAVED_MAP_LOADED:
      const {id, name, description} = action.storedMap;
      state = new Map({id, name, description});
      return state; 

    case Constants.CHANGE_SAVE_PROPERTY:
      return state.set(action.property,action.value);
    
    case Constants.UPDATE_SAVE_ERRORS:
     return state.set('errors', action.errors);

    case Constants.REQUEST_SAVE_MAP:
      return state.set('saving', true);

    case Constants.REQUEST_SAVE_MAP_ERROR:
      state = state.set('saving', false);
      return state.set('httpError', action.httpError);

    case Constants.REQUEST_SAVE_MAP_OK:
      state = new Map({name:'',description:'', saving:false});
      return state;
    
    default:
      return state
  }
}

export default saveMap