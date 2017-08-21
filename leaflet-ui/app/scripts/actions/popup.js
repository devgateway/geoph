import * as Constants from '../constants/constants';
import Connector from '../connector/connector';

export const requestPopupData = (mapId, tab) => {
  return {
    mapId,
    type: Constants.REQUEST_POPUP_DATA,
    tab
  }
};

export const receivePopupData = (mapId, data, tab) => {
  return {
    mapId,
    type: Constants.RECEIVE_POPUP_DATA,
    data: data,
    receivedAt: Date.now(),
    tab
  }
};

export const fetchPopupData = (mapId, filters, tab) => {
  return dispatch => {
    dispatch(requestPopupData(mapId, tab));
    return Connector.getProjectPopupData(filters, tab)
      .then(req => dispatch(receivePopupData(mapId, req, tab)))
  }
};