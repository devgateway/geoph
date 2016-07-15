import * as Constants from '../constants/constants';
import Connector from '../connector/connector';
import {applyFilter} from './filters';
import {toggleVisibility} from './map';
import {getList} from './indicators';

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

const restoreOK = (results) => { 
  return {
    type: Constants.STATE_RESTORE,
    storedMap: results
  }
}

 const loadAll = (results) => { 

  /*return {
    type: Constants.STATE_RESTORE,
    storedMap: results
  }*/
}

export const restoreError = (message) => {
  return {
    type: Constants.STATE_RESTORE_ERROR,
    message: message
  }
}

export const requestRestoreMap = (mapKey) => {
  return (dispatch, getState) =>{
    dispatch(getList());

    Connector.restoreMap(mapKey).then((results)=>{
        if(results) {

          dispatch(restoreOK(results));

          results.data.map.visibleLayers.forEach(l=>{
            dispatch(toggleVisibility(l, false));
          });
          //dispatch(loadAll(results));

        } else {
          restoreError('No map!')
        }
        
    }).catch((err)=>{
        dispatch(restoreError(err.message));
    });
    
  }

}

const makeAction=(name,data)=>{
 return {type:name,...data} 
}