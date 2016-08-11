import * as Constants from '../constants/constants';
import {Map} from 'immutable'

const security = (state = new Map({}), action) => {
  state=state.delete('httpError')//cleaning up errors
  switch (action.type) {
    case Constants.LOGIN_SUCCESS:
    	return state.merge(action.info);
    case Constants.LOGIN_FAILURE:
    	return state.set('httpError',action.error);
    default:
      return state;
  }
}

export default security