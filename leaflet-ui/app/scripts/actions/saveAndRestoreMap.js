import * as Constants from '../constants/constants';
import Connector from '../connector/connector';
import {applyFilter} from './filters';

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

export const restoreOK = (results) => {
  console.log('restoreOK action');  
  return {
    type: Constants.STATE_RESTORE,
    restoreMap: {
      message: 'Restore map done!',
      map: results
    }
  }
}

export const restoreError = (message) => {
  return {
    type: Constants.STATE_RESTORE_ERROR,
    restoreMap: {message: message}
  }
}

export const requestRestoreMap = (mapKey) => {
  return (dispatch, getState) =>{
    Connector.restoreMap(mapKey).then((results)=>{
        dispatch(restoreOK(results));
    }).catch((err)=>{
        dispatch(restoreError(err.data.message));
    });
  }

}
