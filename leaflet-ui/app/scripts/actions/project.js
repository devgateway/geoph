import * as Constants from '../constants/constants';
import Connector from '../connector/connector';

export const openProjectPage = (id) => {
  return dispatch => {
    dispatch(requestProjectInfo());
    return Connector.getProjectInfo(id)
      .then(req => dispatch(receiveProjectInfo(req)))
  }
};

export const requestProjectInfo = () => {
  return {
    type: Constants.OPEN_PROJECT_PAGE
  }
};

export const closeProjectPage = () => {
  return {
    type: Constants.CLOSE_PROJECT_PAGE
  }
};

export const receiveProjectInfo = (data) => {
  return {
    type: Constants.PROJECT_LOADED,
    data,
    receivedAt: Date.now()
  }
};