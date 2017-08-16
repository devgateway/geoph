import * as Constants from '../constants/constants';
import Connector from '../connector/connector';
import {applyFiltersToLayers} from './map';
import {fetchChartData} from './charts';
import {collectValues} from '../util/filterUtil';
import {fetchStats} from './stats';
import {finishRestoreMap} from './saveAndRestoreMap';

const filterTypes = ['ft', 'fa', 'ia', 'st', 'cc', 'gr', 'dt_start', 'dt_end', 'pp_start', 'pp_end', 'sa', 'fin_amount', 'ao', 'ph', 'cl', 'php'];
var filtersToLoad = [];
var loadingAllLists = false;

export const applyFilter = (filtersToApply) => {
  return (dispatch, getState) => {
    let filters = filtersToApply || collectValues(getState().filters.filterMain);
    dispatch(applyFiltersToLayers(filters));
    dispatch(fetchChartData(filters));
    dispatch(fetchStats(filters));
  }
}

export const cancelFilter = (filterType) => {
  return {
    type: Constants.CANCEL_FILTER,
    filterType
  }
}

export const openFilter = (filterType) => {
  return {
    type: Constants.OPEN_FILTER,
    filterType
  }
}

export const resetFilter = (filterType) => {
  return {
    type: Constants.RESET_FILTER,
    filterType
  }
}

export const requestFilterData = (filterType) => {
  return {
    type: Constants.REQUEST_FILTER_DATA,
    filterType
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

const checkLoadingPending = (dispatch, filterType) => {
  if (loadingAllLists){
    filtersToLoad = filtersToLoad.filter(function(i) {return i != filterType});//removes from the list of pendings
    if (filtersToLoad.length==0){
      loadingAllLists = false;
      dispatch(finishRestoreMap());
    }
  }
}

export const fetchFilterData = (filterType) => {
  return dispatch => {
    dispatch(requestFilterData(filterType))
    return Connector.getFilterData(filterType)
      .then(req => {
        checkLoadingPending(dispatch, filterType);
        dispatch(receiveFilterData(filterType, req));
      })
  }
}

export const shouldFetchFilterData = (state, filterType) => {
  const list = state.filters.filterMain[filterType];
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
    } else {
      checkLoadingPending(dispatch, filterType);
    }
  }
}

export const loadAllFilterLists = (fromRestore) => {
  if (fromRestore){
    loadingAllLists = true;
    filtersToLoad = filterTypes;
  }
  return (dispatch, getState) => {
    filterTypes.forEach(filterType => dispatch(fetchFilterDataIfNeeded(filterType)));
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

