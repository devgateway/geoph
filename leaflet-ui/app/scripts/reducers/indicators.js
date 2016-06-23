import * as Constants from '../constants/constants';
import {Map} from 'immutable'

const indicatorWizard = (state = new Map({step:'template',template:'region',name:'',css:'red'}), action) => {

  switch (action.type) {
    
    case Constants.CHANGE_STEP:
    	return state.set('step',action.step)
    
    case Constants.CHANGE_PROPERTY:
    	return state.set(action.property,action.value);

    case Constants.UPDATE_ERRORS:
      return state.set('errors',action.errors);
    
    case Constants.INDICATOR_UPLOAD_FAILURE:
      
      return state.set('errors',action.errors);
    
    case Constants.INDICATOR_UPLOAD_SUCCESS:
      
      return state.set('errors',action.errors);
    
    default:
      return state
  }
}

export default indicatorWizard