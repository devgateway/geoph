import * as Constants from '../constants/constants';
import { searchProjectsByText } from '../actions/projectSearch';


const restoreMap = (state = {}, action) => { 
  console.log('restore reducer');
  switch (action.type) {
    case Constants.REQUEST_RESTORE_MAP:
      return Object.assign({}, state, {'message': action.restoreMap.message})
    default:
      return state
  }
}

export default restoreMap