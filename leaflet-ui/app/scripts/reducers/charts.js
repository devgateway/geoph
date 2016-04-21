import * as Constants from '../constants/constants';

const charts = (state = {}, action) => {
  switch (action.type) {
    case Constants.RECEIVE_CHART_DATA:
    case Constants.REQUEST_CHART_DATA:
      let chartsObj = {};
      for(var key in action.data){
          let act = {type: action.type, data: action.data[key]};
          //Object.assign(chartsObj, {key: chart(state[key], act)})
          chartsObj[key] = chart(state[key], act);
      }
      return Object.assign({}, state, chartsObj)
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