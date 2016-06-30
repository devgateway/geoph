import * as Constants from '../constants/constants';
import Connector from '../connector/connector';

export const saveOK = () => {
  return {
    type: Constants.REQUEST_SAVE_MAP,
    saveMap: {message: 'Save map done!'}
  }
}

export const saveError = (message) => {
  return {
    type: Constants.REQUEST_SAVE_MAP,
    saveMap: {message: message}
  }
}

export const requestSaveMap = (dataToSave) => {
  return (dispatch, getState) =>{
    Connector.saveMap(dataToSave).then((results)=>{
        dispatch(saveOK());
    }).catch((err)=>{
        dispatch(saveError(err.data.message));
    });
  }

}
