import * as Constants from '../constants/constants';
import Connector from '../connector/connector';

export const saveOK = (results) => {
  return {
    type: Constants.REQUEST_SAVE_MAP,
    results: results
  }
}

export const saveError = (message) => {
  return {
    type: Constants.REQUEST_SAVE_MAP,
    message: message
  }
}

export const requestSaveMap = (dataToSave) => {
  return (dispatch, getState) =>{
    console.log("--- requestSaveMap ---");
    Connector.saveMap(dataToSave).then((results)=>{
        dispatch(saveOK(results));
    }).catch((err)=>{
        dispatch(saveError(err.data.message));
    });
  }

}
