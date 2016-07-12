import * as Constants from '../constants/constants';

const saveMap = (state = {}, action) => {
  switch (action.type) {
    case Constants.REQUEST_SAVE_MAP:

      return Object.assign({}, state, {'message': action.saveMap.message})
    default:
      return state
  }
}

export default saveMap