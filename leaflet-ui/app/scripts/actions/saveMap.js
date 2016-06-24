import * as Constants from '../constants/constants';

export const requestSaveMap = (saveMap) => {
  return {
    type: Constants.REQUEST_SAVE_MAP,
    saveMap: saveMap
  }
}

