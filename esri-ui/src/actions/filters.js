import * as Constants from 'app/constants/constants';
import Connector from 'app/connector/connector.js';

export const requestFilterList = (filter) => {
  return {
    type: Constants.REQUEST_FILTER_LIST,
    filter
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

export const searchItemByText = (filterSearch) => {
  return {
    type: Constants.SELECT_ALL_FILTER_LIST,
    filterType: filterSearch.filterType,
    text: filterSearch.text
  }
}
