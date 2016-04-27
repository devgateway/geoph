import * as Constants from '../constants/constants';

export const setFundingType = (fundingType) => {
  return {
    type: Constants.SET_FUNDING_TYPE,
    fundingType: fundingType
  }
}
