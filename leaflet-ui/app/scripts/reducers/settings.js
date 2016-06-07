import * as Constants from '../constants/constants';

const settings = (state = {'fundingType': {measure: 'disbursements', type: 'actual'}}, action) => {
  switch (action.type) {
    case Constants.SET_FUNDING_TYPE:
      return Object.assign({}, state, {'fundingType': action.fundingType})
    default:
      return state
  }
}

export default settings