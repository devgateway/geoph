import * as Constants from '../constants/constants';
import Settings from '../util/settings';
import Connector from '../connector/connector';

export const requestChartData = (chart) => {
  return {
    type: Constants.REQUEST_CHART_DATA,
    chart
  }
}

export const receiveChartData = (chart, data) => {
  return {
    type: Constants.RECEIVE_CHART_DATA,
    chart,
    data: data,
    receivedAt: Date.now()
  }
}

export const fetchChartData = (chart) => {
  return dispatch => {
    dispatch(requestChartData(chart))
    return Connector.getChartData(chart)
    .then(req => dispatch(receiveChartData(chart, req)))
  }
}