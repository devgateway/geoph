import * as Constants from '../constants/constants';

const settings = (state = {'fundingType': {measure: 'commitments', type: 'actual'}}, action) => {
  switch (action.type) {
    case Constants.SET_FUNDING_TYPE:
      return Object.assign({}, state, {'fundingType': action.fundingType});
    
    case Constants.STATE_RESTORE:
      return Object.assign({}, state, {'fundingType': action.storedMap.data.settings.fundingType});
    
    case Constants.COPY_COMPARE_SETTINGS:
      return action.settings;
    
    default:
      return state
  }
};

export default settings;
