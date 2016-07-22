import * as Constants from '../constants/constants';
import Connector from '../connector/connector';
import {applyFilter, loadAllFilterLists} from './filters';
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


export const restoreError = (message) => {
  return {
    type: Constants.STATE_RESTORE_ERROR,
    message: message
  }
}


 /*
 const loadIndicatorList =()=>{
  return new Promise( (resolve, reject) => {
      Connector.getIndicatorList().then((indicatorData) => {              
        resolve(indicatorData);   
      }).catch(reject)
    });
} 

*/
const loadIndicatorList =()=>{
  return Connector.getIndicatorList();
} 

const loadMap =(mapKey)=>{
  return Connector.restoreMap(mapKey);
} 

export const requestRestoreMap = (mapKey) => {
  return (dispatch, getState) =>{
    loadIndicatorList().then((data) => {
      dispatch(makeAction(Constants.INDICATOR_LIST_LOADED,{data}));
      dispatch(loadAllFilterLists());
      loadMap(mapKey).then((storedMap)=>{
          if(storedMap) {
            dispatch(makeAction(Constants.STATE_RESTORE,{storedMap}));
            dispatch(applyFilter(storedMap.data.filters));
            storedMap.data.map.visibleLayers.forEach(l=>{
              dispatch(toggleVisibility(l, false));
            });

          } else {
            dispatch(makeAction(Constants.STATE_RESTORE_ERROR,'No map!'));
          }
          
      }).catch((err)=>{
          dispatch(makeAction(Constants.STATE_RESTORE_ERROR,{err}));
      });
      
    }).catch((err)=>{
      dispatch(makeAction(Constants.INDICATOR_FAILED,{err}));
    });
  }

}

const makeAction=(name, data)=>{
 return {type:name,...data} 
}