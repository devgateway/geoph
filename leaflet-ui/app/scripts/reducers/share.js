import * as Constants from '../constants/constants';
import {Map} from 'immutable'

const share =(state = new Map({shareUrl:'', isShareNeeded:false}), action) => {
  
  //console.log("--- saveMap reducer ---" + action);
  switch (action.type) {
    
    case Constants.REQUEST_SHARE_MAP_OK:
      state = state.set('isShareNeeded',false);
      return state.set('shareUrl',action.data.shareUrl);
    
    case Constants.ACTIVATE_COMPONENT:
      if(action.key=='share'){
        return state.set('isShareNeeded',true);
      } else {
        return state;
      }
    
    case Constants.DEACTIVATE_COMPONENT:
      return state.set('isShareNeeded',false);
    
    case Constants.REQUEST_SHARE_MAP_ERROR:
      return state.set('httpError',action.httpError);
    
    default:
      return state
  }
}

export default share