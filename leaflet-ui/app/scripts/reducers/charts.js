import * as Constants from '../constants/constants';

const charts = (state = {}, action) => {
  switch (action.type) {
    case Constants.RECEIVE_CHART_DATA:
    case Constants.REQUEST_CHART_DATA:
      return Object.assign({}, state, {
        [action.chart]: chart(state[action.chart], action)
      })
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
      return Object.assign({}, state, action.data, {
        isFetching: false,
        lastUpdated: action.receivedAt
      })

    default:
      return state
  }
}

export default charts