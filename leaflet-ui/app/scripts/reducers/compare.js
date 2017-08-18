import Immutable from 'immutable';

// ------------------------------------ Constants ------------------------------------
const CLONE_MAP_DONE = 'CLONE_MAP_DONE';
const CLONE_MAP_CLEAN = 'CLONE_MAP_CLEAN';

export const clone = () => {
  return (dispatch, getState) => {
    const map = getState().map;
    dispatch({type: CLONE_MAP_DONE, map})
  }
};

export const clean = () => {
  return {type: CLONE_MAP_CLEAN}
};

// ------------------------------------ Action Handlers ------------------------------------
const ACTION_HANDLERS = {
  [ CLONE_MAP_CLEAN ]: (state, action) => {
    return state.deleteIn(['map']);
  },
  
  [ CLONE_MAP_DONE ]: (state, action) => {
    const {map} = action;
    return state.setIn(['map'], map);
  }
};

// ------------------------------------ Reducer ------------------------------------
const initialState = Immutable.fromJS({});

// reducer is returned as default
export default function compare(state = initialState, action) {
  const handler = ACTION_HANDLERS[action.type];
  return handler ? handler(state, action) : state
}
