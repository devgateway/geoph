import * as Constants from '../constants/constants';
import Connector from '../connector/connector';
import {applyFilter, loadAllFilterLists} from './filters';
import {setVisibilityOnByIdAndName} from './map';
import {collectValuesToSave} from '../util/saveUtil';
import * as HtmlUtil from '../util/htmlUtil';
import {getVisiblesFromObjects} from '../util/layersUtil';

export const changeProperty = (property, value) => {
  return {type: Constants.CHANGE_SAVE_PROPERTY, property, value}
}

export const updateErrors = (errors) => {
  return {type: Constants.UPDATE_SAVE_ERRORS, errors}
}

export const saveMapRequested = () => {
  return {
    type: Constants.REQUEST_SAVE_MAP
  }
}

export const saveOK = (data) => {
  return (dispatch, getState) => {
    dispatch({type: Constants.REQUEST_SAVE_MAP_OK, data: data});
    dispatch({type: Constants.DEACTIVATE_COMPONENT, key: 'save'});
  }
}

export const saveError = (err) => {
  return {
    type: Constants.REQUEST_SAVE_MAP_ERROR,
    httpError: err
  }
}

export const shareOK = (data) => {
  return (dispatch, getState) => {
    dispatch({
      type: Constants.REQUEST_SHARE_MAP_OK,
      data: data
    });
  }
}

export const shareError = (err) => {
  return {
    type: Constants.REQUEST_SHARE_MAP_ERROR,
    httpError: err
  }
}

export const shareMap = () => {
  return (dispatch, getState) => {
    const data = collectValuesToSave(getState());
    dispatch(requestShareMap({data}));
  }
  
}

const requestShareMap = (dataToShare) => {
  return (dispatch, getState) => {
    Connector.shareMap(dataToShare).then((data) => {
      dispatch(shareOK(data));
    }).catch((results) => {
      dispatch(shareError(results));
    });
  }
  
}

export const saveMap = () => {
  return (dispatch, getState) => {
    const scaleWidth = 800;
    const {outerHTML: html, clientWidth: width, clientHeight: height} = HtmlUtil.getMapElementProperties();
    const data = collectValuesToSave(getState());
    const {name, description, id, type} = getState().saveMap.toJS();
    dispatch(requestSaveMap({id, name, description, type, data, html, width, height, scaleWidth}));
  }
}

const requestSaveMap = (dataToSave) => {
  return (dispatch, getState) => {
    dispatch(saveMapRequested());
    Connector.saveMap(dataToSave).then((data) => {
      dispatch(saveOK(data));
    }).catch((results) => {
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

const loadIndicatorList = () => {
  return Connector.getIndicatorList();
}

const loadMap = (mapKey) => {
  return Connector.restoreMap(mapKey);
}

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
  
}

export const finishRestoreMap = (mapKey) => {//resume map restore after all filters are loaded
  return (dispatch, getState) => {
    loadMap(getState().saveMap.get('mapKey')).then((storedMap) => {
      if (storedMap) {
        dispatch(makeAction(Constants.STATE_RESTORE, {storedMap}));
        dispatch(applyFilter(storedMap.data.filters));
        let visibleLayers = getVisiblesFromObjects(storedMap.data.map.layers);
        Connector.getIndicatorList().then((data) => {
          dispatch(makeAction(Constants.INDICATOR_LIST_LOADED, {data}));
          visibleLayers.forEach(l => {
            dispatch(setVisibilityOnByIdAndName(l.id, l.name));
          });
        }).catch((error) => {
          dispatch(makeAction(Constants.INDICATOR_FAILED, {error}));
        });
      } else {
        dispatch(makeAction(Constants.STATE_RESTORE_ERROR, 'No map!'));
      }
    }).catch((err) => {
      console.error(err);
      dispatch(makeAction(Constants.STATE_RESTORE_ERROR, {err}));
    });
  }
}

const makeAction = (name, data) => {
  return {type: name, ...data}
}