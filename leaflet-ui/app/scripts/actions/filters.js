import * as Constants from '../constants/constants';
import Connector from '../connector/connector';
import {applyFiltersToLayers} from './map';
import {fetchChartData} from './charts';
import {collectValues} from '../util/filterUtil';
import {fetchStats} from './stats';
import {finishRestoreMap} from './saveAndRestoreMap';

const filterTypes = ['ft', 'fa', 'ia', 'st', 'cc', 'gr', 'dt_start', 'dt_end', 'pp_start', 'pp_end', 'sa', 'fin_amount', 'ao', 'ph', 'cl', 'php'];

export const applyFilter = (filtersToApply) => {
  return (dispatch, getState) => {
    let filters = filtersToApply || collectValues(getState().filters.filterMain);
    
    dispatch(applyFiltersToLayers());
    dispatch(fetchChartData(filters));
    dispatch(fetchStats(filters));
  }
};

export const cancelFilter = (filterType) => {
  return {
    type: Constants.CANCEL_FILTER,
    filterType
  }
};

export const openFilter = (filterType) => {
  return {
    type: Constants.OPEN_FILTER,
    filterType
  }
};

export const resetFilter = (filterType) => {
  return {
    type: Constants.RESET_FILTER,
    filterType
  }
};

export const requestFilterData = () => {
  return {
    type: Constants.REQUEST_FILTER_DATA
  }
};

export const receiveFilterData = (data, filterTypes) => {
  return {
    type: Constants.RECEIVE_FILTER_DATA,
    data,
    filterTypes,
    receivedAt: Date.now()
  }
};

export const fetchFilterData = (filterTypes, fromRestore) => {
  return dispatch => {
    dispatch(requestFilterData());
    return Connector.getFilterData()
      .then(req => {
        dispatch(receiveFilterData(req, filterTypes));
        
        // if we have a restore map we continue processing it after we received all the filters
        if (fromRestore) {
          dispatch(finishRestoreMap());
        }
      })
  }
};

/**
 * We should know only from checking 1 entry from *filterTypes* array if the filters are already fetched
 */
export const shouldFetchFilterData = (state, filterTypes) => {
  const list = state.filters.filterMain[filterTypes[0]];
  return !list;
};

export const fetchFilterDataIfNeeded = (filterTypes, fromRestore) => {
  return (dispatch, getState) => {
    if (shouldFetchFilterData(getState(), filterTypes)) {
      return dispatch(fetchFilterData(filterTypes, fromRestore))
    } else {
      // if we have a restore map we continue processing it after we received all the filters
      if (fromRestore) {
        dispatch(finishRestoreMap());
      }
    }
  }
};

export const loadAllFilterLists = (fromRestore) => {
  return (dispatch, getState) => {
    dispatch(fetchFilterDataIfNeeded(filterTypes, fromRestore));
  }
};

export const selectFilterItem = (filterItem) => {
  return {
    type: Constants.SELECT_FILTER_ITEM,
    filterType: filterItem.filterType,
    item: filterItem
  }
};

export const selectAllFilterList = (filterItem) => {
  return {
    type: Constants.SELECT_ALL_FILTER_LIST,
    filterType: filterItem.filterType,
    item: filterItem
  }
};

export const searchItemByText = (filterSearch) => {
  return {
    type: Constants.SEARCH_FILTER_LIST_BY_TEXT,
    filterType: filterSearch.filterType,
    text: filterSearch.text
  }
};

export const setFilterRange = (filter) => {
  return {
    type: Constants.FILTER_SET_RANGE,
    filterType: filter.filterType,
    filter: filter
  }
};
