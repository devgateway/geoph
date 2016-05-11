import * as Constants from '../constants/constants';
import Connector from '../connector/connector';
import {applyFiltersToLayers} from './map';
import {fetchChartData} from './charts';
import {collectValues} from '../util/filterUtil';

export const requestFilterData = (filterType) => {
  return {
    type: Constants.REQUEST_FILTER_DATA,
    filterType
  }
}

export const applyFilter = (filterType) => {
  return (dispatch, getState) => {
    let filters = collectValues(getState().filters);
    dispatch(applyFiltersToLayers(filters));
    dispatch(fetchChartData(filters));
  }
}

export const receiveFilterData = (filterType, data) => {
  return {
    type: Constants.RECEIVE_FILTER_DATA,
    filterType,
    data: data,
    receivedAt: Date.now()
  }
}

export const fetchFilterData = (filterType) => {
  return dispatch => {
    dispatch(requestFilterData(filterType))
    return Connector.getFilterData(filterType)
    .then(req => dispatch(receiveFilterData(filterType, req)))
  }
}

export const shouldFetchFilterData = (state, filterType) => {
  const list = state.filters[filterType]
  if (!list) {
    return true
  } else if (list.isFetching) {
    return false
  } else {
    return false
  }
}

export const fetchFilterDataIfNeeded = (filterType) => {
  return (dispatch, getState) => {
    if (shouldFetchFilterData(getState(), filterType)) {
      return dispatch(fetchFilterData(filterType))
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
    type: Constants.SEARCH_FILTER_LIST_BY_TEXT,
    filterType: filterSearch.filterType,
    text: filterSearch.text
  }
}

export const setFilterRange = (filter) => {
  return {
    type: Constants.FILTER_SET_RANGE,
    filterType: filter.filterType,
    filter: filter
  }
}


