import * as Constants from '../constants/constants';
import {Map} from 'immutable'


const saveMap =(state = new Map({name:'',description:''}), action) => {

  //console.log("--- saveMap reducer ---" + action);
  switch (action.type) {
    
    case Constants.CHANGE_SAVE_PROPERTY:
    return state.set(action.property,action.value);
    case Constants.UPDATE_SAVE_ERRORS:    
    return state.setIn('visible',false);
    case Constants.REQUEST_SAVE_MAP_ERROR:
    return state.set('httpError',action.httpError);
    case Constants.REQUEST_SAVE_MAP_OK:
      state=new Map({name:'',description:''});
    return state;
    default:
    return state
  }
}

export default saveMap