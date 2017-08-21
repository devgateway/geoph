import * as Constants from '../constants/constants';
import { cloneDeep } from '../util/filterUtil';

const popup = (state = {}, action) => {
  switch (action.type) {
    case Constants.RECEIVE_POPUP_DATA:
    case Constants.REQUEST_POPUP_DATA:
      const { mapId } = action;
      
      // create the state for this particular *mapId*
      if (state[mapId] === undefined) {
        state[mapId] = {};
      }
      
      let clonedState = cloneDeep(state);
      clonedState[mapId][action.tab] = tab(state[mapId][action.tab], action);
      
      return clonedState;
    default:
      return state
  }
};

const tab = (state = {
  isFetching: false,
  itemsToShow: Constants.CHART_ITEMS_STEP_AMOUNT,
  chartType: 'bar',
  measureType: 'funding',
  data: []
}, action) => {
  switch (action.type) {
    case Constants.REQUEST_POPUP_DATA:
      return Object.assign({}, state, {
        isFetching: true,
      });
    case Constants.RECEIVE_POPUP_DATA:
      return Object.assign({}, state, {
        data: action.data,
        isFetching: false
      });
    default:
      return state
  }
};

export default popup;