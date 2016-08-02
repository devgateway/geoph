import * as Constants from '../constants/constants';
import Connector from '../connector/connector';
import {applyFilter, loadAllFilterLists} from './filters';
import {toggleVisibility} from './map';
import {getList} from './indicators';
import {collectValuesToSave}  from '../util/saveUtil';

export const changeProperty=(property,value)=>{
  return {type:Constants.CHANGE_SAVE_PROPERTY,property,value}
}

export const updateErrors=(errors)=>{
  return {type:Constants.UPDATE_SAVE_ERRORS,errors}
}

export const saveOK = (data) => {
   return (dispatch, getState) =>{
    dispatch({type: Constants.REQUEST_SAVE_MAP_OK , data:data});
    dispatch({type: Constants.DEACTIVATE_COMPONENT,key:'save'});
   }
}

export const saveError = (err) => {
  return {
    type: Constants.REQUEST_SAVE_MAP_ERROR,
    httpError:err
  }
}

export const shareOK = (data) => {
   return (dispatch, getState) =>{
    dispatch({
      type: Constants.REQUEST_SHARE_MAP_OK, 
      data:data
    });
   }
}

export const shareError = (err) => {
  return {
    type: Constants.REQUEST_SHARE_MAP_ERROR,
    httpError:err
  }
}

export const shareMap=()=>{
  return (dispatch, getState) =>{
    const  data = collectValuesToSave(getState());
    dispatch(requestShareMap({data}));
  }
  
}

const requestShareMap = (dataToShare) => {
  return (dispatch, getState) =>{
    Connector.shareMap(dataToShare).then((data)=>{
        dispatch(shareOK(data));
    }).catch((results)=>{
        dispatch(shareError(results));
    });
  }

}

export const saveMap=()=>{
   return (dispatch, getState) =>{
	 	const  data = collectValuesToSave(getState());
	 	const {name,description}=getState().saveMap.toJS()
    dispatch(requestSaveMap({name,description,data}));
	 }
	
}

 const requestSaveMap = (dataToSave) => {
  return (dispatch, getState) =>{
    Connector.saveMap(dataToSave).then((data)=>{
        dispatch(saveOK(data));
    }).catch((results)=>{
        dispatch(saveError(results));
    });
  }

}


export const restoreError = (message) => {
  return {
    type: Constants.STATE_RESTORE_ERROR,
    message: message
  }
}


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