import * as Constants from '../constants/constants';
import Connector from '../connector/connector';

export const requestStats = () => {
  return {
    type: Constants.REQUEST_STATS
  }
}

export const receiveStats = (data) => {
  return {
    type: Constants.RECEIVE_STATS,
    data: data,
    receivedAt: Date.now()
  }
}

export const fetchStats = (filters) => {
  return dispatch => {
    dispatch(requestStats())
    return Connector.getStats(filters)
      .then(req => dispatch(receiveStats(req)))
  }
}

export const requestLocationStats = () => {
  return {
    type: Constants.REQUEST_LOCATION_STATS
  }
}

export const receiveLocationStats = (data) => {
  return {
    type: Constants.RECEIVE_LOCATION_STATS,
    data: data,
    receivedAt: Date.now()
  }
}

export const fetchLocationStats = (filters) => {
  return dispatch => {
    dispatch(requestLocationStats())
    return Connector.getLocationStats(filters)
      .then(req => dispatch(receiveLocationStats(req)))
  }
}
