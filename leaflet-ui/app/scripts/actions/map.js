import {
  CHANGE_MAP_BOUNDS,
  LAYER_LOAD_FAILURE,
  LAYER_LOAD_REQUEST,
  LAYER_LOAD_SUCCESS,
  LOAD_LAYER_BY_ID,
  REFRESH_LAYER,
  SET_BASEMAP,
  SET_LAYER_SETTING,
  TOGGLE_LAYER,
  TOGGLE_LEGENDS_VIEW
} from '../constants/constants.js';
import Connector from '../connector/connector.js';
import { getDefaults, getPath, getVisibles } from '../util/layersUtil.js';
import { collectValues } from '../util/filterUtil';
import { resetFeaturedMaps } from './dashboard';
import { loadComparisonLayerCompleted } from '../reducers/compare';

const getFilters = (state) => {
  const filters = state.filters.filterMain;
  const projectSearch = state.projectSearch;
  return collectValues(filters, projectSearch);
};

const loadLayerCompleted = (results, getState) => {
  return {type: LAYER_LOAD_SUCCESS, ...results, fundingType: getState().settings.fundingType}
};

const loadLayerRequest = () => {
  return {type: LAYER_LOAD_REQUEST}
};

const loadLayerFailed = (type, error) => {
  return {type: LAYER_LOAD_FAILURE, error}
};

export const loadDefaultLayer = () => {
  return (dispatch, getState) => {
    getDefaults(getState().map.get('layers')).forEach(l => {
      dispatch(toggleVisibility(l.get("id")));
    });
  }
};

export const applyFiltersToLayers = () => {
  return (dispatch, getState) => {
    getVisibles(getState().map.get('layers')).forEach(l => {
      loadLayerById(dispatch, getState(), l.get("id"));
    });
  }
};

export const setSetting = (id, name, value) => {
  return (dispatch, getState) => {
    dispatch({
      type: SET_LAYER_SETTING,
      id,
      name,
      value
    });
    if (name === 'level' || name === 'detail') {
      //reaload layer if level was changed
      dispatch(loadLayerById(dispatch, getState(), id));
    } else {
      //regenerate layer if a setting was changed
      dispatch({type: REFRESH_LAYER, id, fundingType: getState().settings.fundingType});
    }
  }
};

export const toggleVisibility = (id, visibility) => {
  return (dispatch, getState) => {
    dispatch({type: TOGGLE_LAYER, visible: visibility, id});
    
    if (!visibility) {
      loadLayerById(dispatch, getState(), id);
    }
    
    // deselect all featured maps
    dispatch(resetFeaturedMaps());
  }
};

export const setVisibilityOnByIdAndName = (id, name) => {
  return (dispatch, getState) => {
    const layer = getState().map.getIn(getPath(id));
    if (layer.get('name') === name) {
      dispatch({type: TOGGLE_LAYER, visible: false, id});
      loadLayerById(dispatch, getState(), id);
    }
  }
};

export const updateBounds = (newBounds, newCenter, newZoom) => {
  return (dispatch, getState) => {
    dispatch({
        type: CHANGE_MAP_BOUNDS,
        bounds: newBounds,
        center: newCenter,
        zoom: newZoom
      }
    );
  }
};

/**
 * Functions that gathers information about a layers and tries to fetch it's data.
 * The parameters {@link isCompare} indicates if this layers is for the main map or for a comparison map.
 */
export const loadLayerById = (dispatch, state, id, isCompare) => {
  const layer = state.map.getIn(getPath(id));
  
  const options = {
    id: layer.get('id'),
    indicator_id: layer.get("indicator_id"),
    geophotos_id: layer.get("geophotos_id"),
    ep: layer.get('ep'), settings: layer.get('settings') ? layer.get('settings').toObject() : {},
    filters: getFilters(state)
  };
  
  dispatch(loadLayer(options, isCompare));
  return {'type': LOAD_LAYER_BY_ID};
};

/**
 * Get data of an specif layer passing layer options.
 * The parameters {@link isCompare} indicates if this layers is for the main map or for a comparison map.
 */
const loadLayer = (options, isCompare) => {
  return (dispatch, getState) => {
    dispatch(loadLayerRequest());
    Connector.loadLayerByOptions(options).then(results => {
      // for a comparison we need to save the state of a layer in another object
      if (isCompare) {
        dispatch(loadComparisonLayerCompleted(results));
      } else {
        dispatch(loadLayerCompleted(results, getState))
      }
    }).catch((err) => {
      console.log(err);
      dispatch(loadLayerFailed(err));
    });
  }
};

export const setBaseMap = (basemap) => {
  return {
    type: SET_BASEMAP,
    basemap: basemap
  }
};

export const toggleLegendsView = () => {
  return {
    type: TOGGLE_LEGENDS_VIEW
  }
};
