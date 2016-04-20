import * as Constants from '../constants/constants';

const settings = (state = {'fundingType': 'pd'}, action) => {
  switch (action.type) {
    case Constants.SET_FUNDING_TYPE:
      return Object.assign({}, state, {'fundingType': action.fundingType})
    default:
      return state
  }
}

export default settings