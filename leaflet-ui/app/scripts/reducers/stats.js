import * as Constants from '../constants/constants';
import Immutable from 'immutable';

const defaultState = Immutable.fromJS({
  'global': {
    'isFetching': false, 'data': {}
  }, 
  'location': {
    'isFetching': false, 'data': {}
  }
})

const stats =(state = defaultState, action) => {
  switch (action.type) {    
    case Constants.REQUEST_STATS:
        return state.setIn(['global','isFetching'], true);
   
    case Constants.RECEIVE_STATS:
        state = state.setIn(['global','isFetching'], false);
        return state.setIn(['global','data'], action.data);
        
    case Constants.REQUEST_LOCATION_STATS:
        return state.setIn(['location','isFetching'], true);
   
    case Constants.RECEIVE_LOCATION_STATS:
        state = state.setIn(['location','isFetching'], false);
        return state.setIn(['location','data'], action.data);
        
    default:
        return state
  }
}

export default stats;