import * as Constants from '../constants/constants';
import {Map} from 'immutable'

const security = (state = new Map({}), action) => {
  switch (action.type) {
    case Constants.LOGIN_SUCCESS:
    	return new Map(action.info)
    case Constants.LOGIN_FAILURE:
    	return new Map({error:action.error})
    default:
      return state
  }
}

export default security