import * as Constants from '../constants/constants';
import Connector from '../connector/connector';
import {loadProjects} from './map';
import {collectValues} from '../util/filterUtil';
export const requestFilterList = (filter) => {
  return {
    type: Constants.REQUEST_FILTER_LIST,
    filter
  }
}




export const applyFilter = (filterType) => {
  return (dispatch, getState) => {
    debugger;
    //call to all filters changed related eventes 
    return dispatch(loadProjects('region',collectValues(getState().filters)));
  }
}


export const receiveFilterList = (filterType, data) => {
  return {
    type: Constants.RECEIVE_FILTER_LIST,
    filterType,
    data: data,
    receivedAt: Date.now()
  }
}

export const fetchFilterList = (filterType) => {
  return dispatch => {
    dispatch(requestFilterList(filterType))
    return Connector.getFilterList(filterType)
    .then(req => dispatch(receiveFilterList(filterType, req)))
  }
}

export const shouldFetchFilterList = (state, filterType) => {
  const list = state.filters[filterType]
  if (!list) {
    return true
  } else if (list.isFetching) {
    return false
  } else {
    return false
  }
}

export const fetchFilterListIfNeeded = (filterType) => {
  return (dispatch, getState) => {
    if (shouldFetchFilterList(getState(), filterType)) {
      return dispatch(fetchFilterList(filterType))
    }
  }
}

export const selectFilterItem = (filterItem) => {
  return {
    type: Constants.SELECT_FILTER_ITEM,
    filterType: filterItem.filterType,
    item: filterItem
  }
}

export const selectAllFilterList = (filterItem) => {
  return {
    type: Constants.SELECT_ALL_FILTER_LIST,
    filterType: filterItem.filterType,
    item: filterItem
  }
}
