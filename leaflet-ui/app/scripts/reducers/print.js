import * as Constants from '../constants/constants';
import {Map,List} from 'immutable'

var count=0;
const share =(state = new Map({captures:new List()}), action) => {

  
  //console.log("--- saveMap reducer ---" + action);
  switch (action.type) {

    case Constants.CAPTURE_START:
    return state.setIn(['loading'],true);
    
    case Constants.CAPTURE_OK:
    debugger
    return state.setIn(['loading'],false).setIn(['captures',count++],action.data.file);
    
    case Constants.CAPTURE_RESET:
    count=0;
    return state.setIn('captures', new List());
    
    case Constants.CAPTURE_FAILED:
    return state.setIn(['loading'],false).setIn('error',"");
    
    default:
    return state
  }
}

export default share