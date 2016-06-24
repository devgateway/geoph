import * as Constants from '../constants/constants';
import Settings from '../util/settings';
import Connector from '../connector/connector';

export const requestChartData = (fromPopup) => {
  return {
    type: Constants.REQUEST_CHART_DATA,
    fromPopup: fromPopup
  }
}

export const changeItemsToShow = (chart, value) => {
  return {
    type: Constants.CHANGE_ITEMS_TO_SHOW,
    chart,
    value
  }
}

export const changeMeasureType = (chart, value) => {
  return {
    type: Constants.CHANGE_MEASURE_TYPE,
    chart,
    value
  }
}

export const changeChartType = (chart, value) => {
  return {
    type: Constants.CHANGE_CHART_TYPE,
    chart,
    value
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
