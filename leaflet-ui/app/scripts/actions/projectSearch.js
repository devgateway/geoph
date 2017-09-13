import * as Constants from '../constants/constants';
import Connector from '../connector/connector';

export const requestProjectsByText = (filters) => {
  return {
    type: Constants.REQUEST_PROJECT_BY_TEXT,
    filters
  }
};

export const setKeyword = (keyword) => {
  return {
    type: Constants.SET_KEYWORD,
    keyword
  }
};

export const toggleProjectSelection = (project) => {
  return {
    type: Constants.TOGGLE_PROJECT_SELECTION,
    project: project
  }
};

export const selectAllMatchedProject = () => {
  return {
    type: Constants.SELECT_ALL_MATCHED_PROJECT
  }
};

export const clearAllProjectSelected = () => {
  return {
    type: Constants.CLEAR_ALL_PROJECT_SELECTED
  }
};

export const applyProjectSelected = () => {
  return {
    type: Constants.APPLY_PROJECT_SELECTED
  }
};

export const receiveProjectsByText = (filters, data) => {
  return {
    type: Constants.RECEIVE_PROJECT_BY_TEXT,
    filters,
    data: data,
    receivedAt: Date.now()
  }
};

export const searchProjectsByText = (filters) => {
  return dispatch => {
    dispatch(requestProjectsByText(filters));
    return Connector.getProjectsWithFilters(filters)
      .then(req => dispatch(receiveProjectsByText(filters, req)))
  }
};

export const clearAllResults = () => {
  return {
    type: Constants.CLEAR_ALL_RESULTS
  }
};

export const copyCompareProjectSearch = () => {
  return (dispatch, getState) => {
    const projectSearch = getState().compare.get("projectSearch");
    dispatch({type: Constants.COPY_COMPARE_PROJECT_SEARCH, projectSearch})
  }
};
