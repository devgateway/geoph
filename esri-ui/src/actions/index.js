import Constants from 'app/constants/constants';
import i18next from 'i18next';
import AjaxUtil from 'app/util/AjaxUtil';
import Settings from 'app/util/Settings';

let settings=Settings.getInstace();

export const setLanguage = (lang) => {
  debugger;
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
    return AjaxUtil.get(settings.get('API','FILTER_ENDPOINTS')[filterType])
      .then(req => dispatch(receiveFilterList(filterType, req.data)))
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
