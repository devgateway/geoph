import * as Constants from '../constants/constants';
import Immutable from 'immutable';

const defaultState = Immutable.fromJS({
  'global': {
    'isFetching': false, 'data': {}, 'lastUpdated': 0
  }
});

const stats = (state = defaultState, action) => {
  const { mapId } = action;
  
  switch (action.type) {
    case Constants.REQUEST_STATS:
      return state.setIn(['global', 'isFetching'], true);
    
    case Constants.RECEIVE_STATS:
      state = state.setIn(['global', 'isFetching'], false);
      state = state.setIn(['global', 'lastUpdated'], action.receivedAt);
      return state.setIn(['global', 'data'], action.data);
    
    case Constants.REQUEST_LOCATION_STATS:
      return state.setIn([mapId, 'location', 'isFetching'], true);
    
    case Constants.RECEIVE_LOCATION_STATS:
      return state.setIn([mapId, 'location', 'isFetching'], false)
        .setIn([mapId, 'location', 'data'], action.data);
    
    default:
      return state
  }
};

export default stats;