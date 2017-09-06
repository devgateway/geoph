import { hashHistory } from 'react-router';
import * as Constants from '../constants/constants';
import Connector from '../connector/connector';
import { applyFilter, loadAllFilterLists } from './filters';
import { setVisibilityOnByIdAndName, loadLayerById } from './map';
import { collectValuesToSave } from '../util/saveUtil';
import { getMapElementProperties } from '../util/htmlUtil';
import { getVisiblesFromObjects } from '../util/layersUtil';
import { CLONE_MAP_DONE } from '../reducers/compare';
import { restoreFilters } from '../reducers/filters';
import Immutable from 'immutable';

export const changeProperty = (property, value) => {
  return {type: Constants.CHANGE_SAVE_PROPERTY, property, value}
};

export const updateErrors = (errors) => {
  return {type: Constants.UPDATE_SAVE_ERRORS, errors}
};

export const saveMapRequested = () => {
  return {
    type: Constants.REQUEST_SAVE_MAP
  }
};

export const saveOK = (data) => {
  return (dispatch, getState) => {
    dispatch({type: Constants.REQUEST_SAVE_MAP_OK, data: data});
    dispatch({type: Constants.DEACTIVATE_COMPONENT, key: 'save'});
  }
};

export const saveError = (err) => {
  return {
    type: Constants.REQUEST_SAVE_MAP_ERROR,
    httpError: err
  }
};

export const shareOK = (data) => {
  return (dispatch, getState) => {
    dispatch({
      type: Constants.REQUEST_SHARE_MAP_OK,
      data: data
    });
  }
};

export const shareError = (err) => {
  return {
    type: Constants.REQUEST_SHARE_MAP_ERROR,
    httpError: err
  }
};

export const shareMap = () => {
  return (dispatch, getState) => {
    const data = collectValuesToSave(getState());
    dispatch(requestShareMap({data}));
  }
  
};

const requestShareMap = (dataToShare) => {
  return (dispatch, getState) => {
    Connector.shareMap(dataToShare).then((data) => {
      dispatch(shareOK(data));
    }).catch((results) => {
      dispatch(shareError(results));
    });
  }
};

export const saveMap = () => {
  return (dispatch, getState) => {
    const scaleWidth = 800;
    const {outerHTML: html, clientWidth: width, clientHeight: height} = getMapElementProperties();
    const data = collectValuesToSave(getState());
    const {name, description, id, type} = getState().saveMap.toJS();
    dispatch(requestSaveMap({id, name, description, type, data, html, width, height, scaleWidth}));
  }
};

const requestSaveMap = (dataToSave) => {
  return (dispatch, getState) => {
    dispatch(saveMapRequested());
    Connector.saveMap(dataToSave).then((data) => {
      dispatch(saveOK(data));
    }).catch((results) => {
      dispatch(saveError(results));
    });
  }
};

const loadIndicatorList = () => {
  return Connector.getIndicatorList();
};

const loadMap = (mapKey) => {
  return Connector.restoreMap(mapKey);
};

export const requestRestoreMap = (mapKey) => {
  return (dispatch, getState) => {
    loadIndicatorList().then((data) => {
      dispatch(makeAction(Constants.INDICATOR_LIST_LOADED, {data}));
      dispatch(makeAction(Constants.REQUEST_STATE_RESTORE, {mapKey}));
      dispatch(loadAllFilterLists(true));
    }).catch((err) => {
      dispatch(makeAction(Constants.INDICATOR_FAILED, {err}));
    });
  }
};

export const finishRestoreMap = () => {//resume map restore after all filters are loaded
  return (dispatch, getState) => {
    loadMap(getState().saveMap.get('mapKey')).then((storedMap) => {
      if (storedMap) {
        let compareData;
        
        // Check if we have a comparison. If yes, then the first element is the main map.
        if (storedMap.data instanceof Array) {
          if (storedMap.data.length > 1) {
            compareData = storedMap.data[1];
            storedMap.data = storedMap.data[0];
          } else {
            if (storedMap.data.length === 1) {
              storedMap.data = storedMap.data[0];
            }
          }
        }
        
        dispatch(makeAction(Constants.STATE_RESTORE, {storedMap}));
        dispatch(applyFilter(storedMap.data.filters));
        let visibleLayers = getVisiblesFromObjects(storedMap.data.map.layers);
        Connector.getIndicatorList().then((data) => {
          dispatch(makeAction(Constants.INDICATOR_LIST_LOADED, {data}));
          visibleLayers.forEach(layer => {
            dispatch(setVisibilityOnByIdAndName(layer.id, layer.name));
          });
        }).catch((error) => {
          dispatch(makeAction(Constants.INDICATOR_FAILED, {error}));
        });
        
        // if we have a comparison then we can use the same action *CLONE_MAP_DONE* to copy the second map properties
        if (compareData !== undefined) {
          const compareVisibleLayers = getVisiblesFromObjects(compareData.map.layers);
          
          // restore the compare map filters with the main filter object *filterMain*
          compareData.filters.filterMain = restoreFilters(getState().filters, compareData.filters);
          
          // we are using 2 methods to keep the application state: 1. plain objects, 2. immutable objects (don't ask me why...)
          // so we try to convert the map object to a immutable Map object
          compareData.map = Immutable.fromJS(compareData.map);
          
          compareVisibleLayers.forEach(layer => {
            dispatch(loadLayerById(dispatch, compareData, layer.id, true));
          });
          
          dispatch({type: CLONE_MAP_DONE, ...compareData});
          hashHistory.push('/map/compare');
        }
      } else {
        dispatch(makeAction(Constants.STATE_RESTORE_ERROR, 'No map!'));
      }
    }).catch((err) => {
      console.error(err);
      dispatch(makeAction(Constants.STATE_RESTORE_ERROR, {err}));
    });
  }
};

const makeAction = (name, data) => {
  return {type: name, ...data}
};