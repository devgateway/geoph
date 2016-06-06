import * as Constants from '../constants/constants';
import {cloneDeep} from '../util/filterUtil';

const stats = (state = {}, action) => {
  switch (action.type) {
    case Constants.REQUEST_STATS:
      return Object.assign({}, state, {
        isFetching: true,
      })
    case Constants.RECEIVE_STATS:
      let stateCloned = cloneDeep(state);
      return Object.assign({}, stateCloned, action.data);
    default:
      return state
  }
}

export default stats