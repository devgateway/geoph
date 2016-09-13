import * as Constants from '../constants/constants';
import {Map,List} from 'immutable'

var count=0;

const share =(state = new Map({captures:new List()}), action) => {

  
  //console.log("--- saveMap reducer ---" + action);
  switch (action.type) {

    case Constants.CAPTURE_START:
    return state.setIn(['loading'],true);
    
    case Constants.CAPTURE_OK:
 
    if (count > 7){ //0 1 2 
       state=state.set("captures",state.get('captures').shift());
    }

    count++
    return state.setIn(['loading'],false).setIn(['captures',state.get("captures").size],{name:action.data.file,count:count});
    
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