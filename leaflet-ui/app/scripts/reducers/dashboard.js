
import * as Constants from '../constants/constants';
import {cloneDeep} from '../util/filterUtil';
import {Map} from 'immutable'

const stats = (state = new Map({}), action) => {
  switch (action.type) {
     case Constants.REQUEST_MAP_LIST_OK:
    return state.setIn(['results'],action.data);
    default:
      return state
  }
}

export default stats