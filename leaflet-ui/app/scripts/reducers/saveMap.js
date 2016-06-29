import * as Constants from '../constants/constants';

const saveMap = (state = {}, action) => {
  console.log("--- saveMap action ---" + action);
  switch (action.type) {
    case Constants.REQUEST_SAVE_MAP:
      return state
    default:
      return state
  }
}

export default saveMap