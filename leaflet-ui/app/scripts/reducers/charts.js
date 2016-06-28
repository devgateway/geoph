import * as Constants from '../constants/constants';
import {cloneDeep} from '../util/filterUtil';

const charts = (state = {}, action) => {
  switch (action.type) {
    case Constants.CHANGE_ITEMS_TO_SHOW:
    case Constants.CHANGE_MEASURE_TYPE:
    case Constants.CHANGE_CHART_TYPE:
      let clonedState = cloneDeep(state);    
      clonedState[action.chart] = chart(state[action.chart], action);  
      return clonedState;
    case Constants.RECEIVE_CHART_DATA:
    case Constants.REQUEST_CHART_DATA:
      let chartsObj = {lastUpdate: action.receivedAt};
      for(var key in action.data){
          let act = {type: action.type, data: action.data[key]};
          chartsObj[key] = chart(state[key], act);
      }
      return Object.assign({}, state, chartsObj);
    default:
      return state
  }
}

const chart = (state = {
  isFetching: false,
  itemsToShow: Constants.CHART_ITEMS_STEP_AMOUNT,
  chartType:'bar',
  measureType: 'funding',
  data: []
}, action) => {
  switch (action.type) {
    case Constants.CHANGE_ITEMS_TO_SHOW:
      return Object.assign({}, state, {
        itemsToShow: action.value,
      })
    case Constants.CHANGE_MEASURE_TYPE:
      return Object.assign({}, state, {
        measureType: action.value,
      })
    case Constants.CHANGE_CHART_TYPE:
      return Object.assign({}, state, {
        chartType: action.value,
      })
    case Constants.REQUEST_CHART_DATA:
      return Object.assign({}, state, {
        isFetching: true,
      })
    case Constants.RECEIVE_CHART_DATA:
      return Object.assign({}, state, {
        data: action.data, 
        isFetching: false
      })
    default:
      return state
  }
}

export default charts