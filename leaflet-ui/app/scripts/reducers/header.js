import * as Constants from '../constants/constants';
import { Map } from 'immutable';

const header = (state = new Map({}), action) => {
  switch (action.type) {
    case  Constants.ACTIVATE_COMPONENT:
      return state.set(action.key, true);
    
    case  Constants.DEACTIVATE_COMPONENT:
      return state.set(action.key, false);
    
    default:
      return state;
    
  }
};

export default header;
