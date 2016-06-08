import * as Constants from '../constants/constants';
import {cloneDeep} from '../util/filterUtil';

const panel = (state = {'expanded': false}, action) => {
  let stateCloned;
  switch (action.type) {
    case Constants.TOGGLE_PANEL_EXPANDED:
      stateCloned = cloneDeep(state);
      Object.assign(stateCloned, {'expanded': !state.expanded});
      return stateCloned;
    default:
      return state
  }
}

export default panel