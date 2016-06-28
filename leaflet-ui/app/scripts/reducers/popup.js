import * as Constants from '../constants/constants';

const popup = (state = {}, action) => {
  switch (action.type) {
    case Constants.RECEIVE_POPUP_DATA:
    case Constants.REQUEST_POPUP_DATA:
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
    case Constants.REQUEST_POPUP_DATA:
      return Object.assign({}, state, {
        isFetching: true,
      })
    case Constants.RECEIVE_POPUP_DATA:
      return Object.assign({}, state, {
        data: action.data, 
        isFetching: false
      })
    default:
      return state
  }
}

export default popup