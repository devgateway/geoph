import Constants from '../constants/constants.es6';
import i18next from 'i18next';
import fetch from 'isomorphic-fetch';
import Settings from '../util/Settings.es6';

let settings=Settings.getInstace();

export const setLanguage = (lang) => {
  i18next.changeLanguage(lang);
  return {
    type: Constants.SET_APP_LANGUAGE,
    lang 
  }
}

export const requestFilterList = (filter) => {
  return {
    type: Constants.REQUEST_FILTER_LIST,
    filter
  }
}

export const receiveFilterList = (filterType, json) => {
  return {
    type: Constants.RECEIVE_FILTER_LIST,
    filterType,
    items: [...json.hits],
    receivedAt: Date.now()
  }
}

export const fetchFilterList = (filterType) => {
  return dispatch => {
    dispatch(requestFilterList(filterType))
    return fetch(settings.get('API','FILTER_ENDPOINTS')[filterType])
      .then(req => req.json())
      .then(json => dispatch(receiveFilterList(filterType, json)))
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
    item: filterItem.item //it should be {id, selected}
  }
}

export const selectAllFilterList = (filter) => {
  return {
    type: Constants.SELECT_ALL_FILTER_LIST,
    filter //it should be {filterType, selected}
  }
}
