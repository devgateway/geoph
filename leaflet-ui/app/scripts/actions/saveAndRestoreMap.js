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

export const c = (results) => {
  console.log('restoreOK action');  
  return {
    type: Constants.STATE_RESTORE,
    mapData: results
  }
}

export const restoreError = (message) => {
  return {
    type: Constants.STATE_RESTORE_ERROR,
    message: message
  }
}

export const requestRestoreMap = (mapKey) => {
  return (dispatch, getState) =>{
    Connector.restoreMap(mapKey).then((results)=>{
        debugger;
        dispatch(restoreOK(results));
        dispatch(loadAll(results));
        
    }).catch((err)=>{
        dispatch(restoreError(err.data.message));
    });
  }

}
