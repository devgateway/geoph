import * as Constants from '../constants/constants';
import Settings from '../util/settings';
import Connector from '../connector/connector';

export const requestChartData = (fromPopup) => {
  return {
    type: Constants.REQUEST_CHART_DATA,
    fromPopup: fromPopup
  }
}

export const receiveChartData = (data, fromPopup) => {
  return {
    type: Constants.RECEIVE_CHART_DATA,
    data: data,
    receivedAt: Date.now(),
    fromPopup: fromPopup
  }
}

export const fetchChartData = (filters) => {
  return dispatch => {
    dispatch(requestChartData(false))
    return Connector.getChartData(filters)
    .then(req => dispatch(receiveChartData(req, false)))
  }
}

export const fetchPopupChartData = (filters) => {
  return dispatch => {
    dispatch(requestChartData(true));
    return Connector.getProjectPopupData(filters)
    .then(req => dispatch(receiveChartData(req, true)))
  }
}