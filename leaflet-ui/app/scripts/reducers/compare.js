import Immutable from 'immutable';
import { onLoadLayer } from './map';

// ------------------------------------ Constants ------------------------------------
export const CLONE_MAP_DONE = 'CLONE_MAP_DONE';
const CLONE_MAP_CLEAN = 'CLONE_MAP_CLEAN';
const TOGGLE_COMPARE_LEGENDS_VIEW = 'TOGGLE_COMPARE_LEGENDS_VIEW';
const LAYER_COMPARE_LOAD_SUCCESS = 'LAYER_COMPARE_LOAD_SUCCESS';
const CHANGE_COMPARE_MAP_BOUNDS = 'CHANGE_COMPARE_MAP_BOUNDS';

export const clone = () => {
  return (dispatch, getState) => {
    // don't clone if we have something in the state - most probably we have a shared map with a comparison
    if (getState().compare.size === 0) {
      const map = getState().map;
      const filters = getState().filters;
      const projectSearch = getState().projectSearch;
      const settings = getState().settings;
      
      dispatch({type: CLONE_MAP_DONE, map, filters, projectSearch, settings});
    }
  }
};

export const clean = () => {
  return {type: CLONE_MAP_CLEAN}
};

export const toggleCompareLegendsView = () => {
  return {
    type: TOGGLE_COMPARE_LEGENDS_VIEW
  }
};

export const loadComparisonLayerCompleted = (results) => {
  return {
    type: LAYER_COMPARE_LOAD_SUCCESS,
    results
  }
};

/**
 * Update map settings for a comparison map.
 */
export const updateCompareBounds = (newBounds, newCenter, newZoom) => {
  return {
    type: CHANGE_COMPARE_MAP_BOUNDS,
    bounds: newBounds,
    center: newCenter,
    zoom: newZoom
  };
};

// ------------------------------------ Action Handlers ------------------------------------
const ACTION_HANDLERS = {
  [ CLONE_MAP_CLEAN ]: (state, action) => {
    return state
      .deleteIn(['map'])
      .deleteIn(['filters'])
      .deleteIn(['projectSearch'])
      .deleteIn(['settings']);
  },
  
  [ CLONE_MAP_DONE ]: (state, action) => {
    const { map, filters, projectSearch, settings } = action;
    
    return state
      .setIn(['map'], map)
      .setIn(['filters'], filters)
      .setIn(['projectSearch'], projectSearch)
      .setIn(['settings'], settings);
  },
  
  [ TOGGLE_COMPARE_LEGENDS_VIEW ]: (state, action) => {
    return state.setIn(['map', 'legends', 'visible'], !state.getIn(['map', 'legends', 'visible']));
  },
  
  [ LAYER_COMPARE_LOAD_SUCCESS ]: (state, action) => {
    const { results } = action;
    const newCompareMap = onLoadLayer(state.get("map"), {...results, fundingType: state.get("settings").fundingType});
    
    return state.set("map", newCompareMap);
  },
  
  [ CHANGE_COMPARE_MAP_BOUNDS ]: (state, action) => {
    return state
      .setIn(['map', 'bounds'], Immutable.fromJS({southWest: action.bounds._southWest, northEast: action.bounds._northEast}))
      .setIn(['map', 'center'], Immutable.fromJS(action.center))
      .setIn(['map', 'zoom'], action.zoom);
  }
};

// ------------------------------------ Reducer ------------------------------------
const initialState = Immutable.fromJS({});

// reducer is returned as default
export default function compare(state = initialState, action) {
  const handler = ACTION_HANDLERS[action.type];
  return handler ? handler(state, action) : state
}
