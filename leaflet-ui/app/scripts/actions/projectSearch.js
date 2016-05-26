import * as Constants from '../constants/constants';
import Connector from '../connector/connector';
import {applyFiltersToLayers} from './map';
import {fetchChartData} from './charts';
import {collectValues} from '../util/filterUtil';

export const requestProjectsByText = (filters) => {
  return {
    type: Constants.REQUEST_PROJECT_BY_TEXT,
    filters
  }
}

export const toggleProjectSelection = (project) => {
  return {
    type: Constants.TOGGLE_PROJECT_SELECTION,
    project: project
  }
}

export const selectAllMatchedProject = () => {
  return {
    type: Constants.SELECT_ALL_MATCHED_PROJECT
  }
}

export const clearAllProjectSelected = () => {
  return {
    type: Constants.CLEAR_ALL_PROJECT_SELECTED
  }
}

export const receiveProjectsByText = (filters, data) => {
  return {
    type: Constants.RECEIVE_PROJECT_BY_TEXT,
    filters,
    data: data,
    receivedAt: Date.now()
  }
}

export const searchProjectsByText = (filters) => {
  return dispatch => {
    dispatch(requestProjectsByText(filters))
    return Connector.getProjectsWithFilters(filters)
    .then(req => dispatch(receiveProjectsByText(filters, req)))
  }
}

