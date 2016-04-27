import * as Constants from '../constants/constants';
import Settings from '../util/settings';
import Connector from '../connector/connector';

export const requestChartData = () => {
  return {
    type: Constants.REQUEST_CHART_DATA
  }
}

export const receiveChartData = (data) => {
  return {
    type: Constants.RECEIVE_CHART_DATA,
    data: data,
    receivedAt: Date.now()
  }
}

export const fetchChartData = (filters) => {
  return dispatch => {
    dispatch(requestChartData())
    return Connector.getChartData(filters)
    .then(req => dispatch(receiveChartData(req)))
  }
}