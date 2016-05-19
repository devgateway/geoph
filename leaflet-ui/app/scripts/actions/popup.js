import * as Constants from '../constants/constants';
import Settings from '../util/settings';
import Connector from '../connector/connector';

export const requestPopupData = (fromPopup) => {
  return {
    type: Constants.REQUEST_POPUP_DATA,
    fromPopup: fromPopup
  }
}

export const receivePopupData = (data, fromPopup) => {
  return {
    type: Constants.RECEIVE_POPUP_DATA,
    data: data,
    receivedAt: Date.now(),
    fromPopup: fromPopup
  }
}

export const fetchPopupData = (filters) => {
  return dispatch => {
    dispatch(requestPopupData(true));
    return Connector.getProjectPopupData(filters)
    .then(req => dispatch(receivePopupData(req, true)))
  }
}