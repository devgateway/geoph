import * as Constants from '../constants/constants';

const settings = (state = {'fundingType': {measure: 'disbursements', type: 'actual'}}, action) => {
  switch (action.type) {
    case Constants.SET_FUNDING_TYPE:
      return Object.assign({}, state, {'fundingType': action.fundingType});
    case Constants.STATE_RESTORE:
    	debugger;
    	return  Object.assign({}, state, {'fundingType': action.storedMap.data.settings.fundingType});
    default:
      return state
  }
}

export default settings