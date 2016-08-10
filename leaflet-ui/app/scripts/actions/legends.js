import * as Constants from '../constants/constants';

export const createLegendsFromLayers = (layers, fundingType) => {
  return {
    type: Constants.CREATE_LEGENDS,
    layers, 
    fundingType
  }
}
