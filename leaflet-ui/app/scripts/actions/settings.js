import * as Constants from '../constants/constants';

export const setFundingType = (fundingType) => {
  return {
    type: Constants.SET_FUNDING_TYPE,
    fundingType: fundingType
  }
};

export const copyCompareSettings = () => {
  return (dispatch, getState) => {
    const settings = getState().compare.get("settings");
    dispatch({type: Constants.COPY_COMPARE_SETTINGS, settings})
  }
};
