import * as Constants from '../constants/constants';
import Connector from '../connector/connector';

export const requestPopupData = (tab) => {
  return {
    type: Constants.REQUEST_POPUP_DATA,
    tab
  }
}

export const receivePopupData = (data, tab) => {
  return {
    type: Constants.RECEIVE_POPUP_DATA,
    data: data,
    receivedAt: Date.now(),
    tab
  }
}

export const fetchPopupData = (filters, tab) => {
  return dispatch => {
    dispatch(requestPopupData(tab));
    return Connector.getProjectPopupData(filters, tab)
      .then(req => dispatch(receivePopupData(req, tab)))
  }
}