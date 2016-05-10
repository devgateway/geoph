import * as Constants from '../constants/constants';

const charts = (state = {}, action) => {
  switch (action.type) {
    case Constants.RECEIVE_CHART_DATA:
    case Constants.REQUEST_CHART_DATA:
      let chartsObj = {lastUpdate: action.receivedAt};
      for(var key in action.data){
          let act = {type: action.type, data: action.data[key]};
          chartsObj[key] = chart(state[key], act);
      }
      if (action.fromPopup){
        return Object.assign({}, state, {popupCharts: chartsObj});
      } else {
        return Object.assign({}, state, {sideCharts: chartsObj});
      }
    default:
      return state
  }
}

const chart = (state = {
  isFetching: false,
  data: []
}, action) => {
  switch (action.type) {
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