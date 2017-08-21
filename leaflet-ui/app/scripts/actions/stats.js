import * as Constants from '../constants/constants';
import Connector from '../connector/connector';

export const requestStats = () => {
  return {
    type: Constants.REQUEST_STATS
  }
};

export const receiveStats = (data) => {
  return {
    type: Constants.RECEIVE_STATS,
    data: data,
    receivedAt: Date.now()
  }
};

export const fetchStats = (filters) => {
  return dispatch => {
    dispatch(requestStats());
    return Connector.getStats(filters)
      .then(req => dispatch(receiveStats(req)))
  }
};

export const requestLocationStats = (mapId) => {
  return {
    mapId,
    type: Constants.REQUEST_LOCATION_STATS
  }
};

export const receiveLocationStats = (mapId, data) => {
  return {
    mapId,
    type: Constants.RECEIVE_LOCATION_STATS,
    data: data,
    receivedAt: Date.now()
  }
};

export const fetchLocationStats = (mapId, filters) => {
  return dispatch => {
    dispatch(requestLocationStats(mapId));
    return Connector.getLocationStats(filters)
      .then(req => dispatch(receiveLocationStats(mapId, req)))
  }
};
