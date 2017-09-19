import * as Constants from '../constants/constants';
import {COPY_COMPARE_FILTERS} from '../constants/constants';
import {cloneDeep} from '../util/filterUtil';

const filters = (state = {filterMain: {}}, action) => {
  let filterMain;
  switch (action.type) {
    case Constants.SELECT_FILTER_ITEM:
    case Constants.SELECT_ALL_FILTER_LIST:
    case Constants.FILTER_SET_RANGE:
    case Constants.SEARCH_FILTER_LIST_BY_TEXT:
      let fl = filter(state.filterMain[action.filterType], action);
      updateFilterCounters(fl);
      filterMain = cloneDeep(state.filterMain);
      Object.assign(filterMain, {[action.filterType]: fl});
      return Object.assign({}, state, {
        filterMain: filterMain
      });
    
    case Constants.RECEIVE_FILTER_DATA:
      let copyState = cloneDeep(state.filterMain);
      let filters = cloneDeep(state.filterMain);
      
      action.filterTypes.forEach(filterType => {
        Object.assign(filters, {[filterType]: action.data[filterType]});
      });
      
      copyState = Object.assign({}, copyState, {
        filterMain: filters,
        isFetching: false,
        isLoaded: true,
        lastUpdated: action.receivedAt
      });
      
      return copyState;
    
    case Constants.REQUEST_FILTER_DATA:
      return Object.assign({}, state, {
        isFetching: true,
      });
    
    case Constants.OPEN_FILTER:
      filterMain = cloneDeep(state.filterMain);
      makeAllOptionsVisible(filterMain);
      return Object.assign({}, state, {
        filterBackup: filterMain,
        filterMain: filterMain
      });
    
    case Constants.CANCEL_FILTER:
      let filterBackup = cloneDeep(state.filterBackup);
      return Object.assign({}, state, {
        filterMain: filterBackup
      });
    
    case Constants.STATE_RESTORE:
      return Object.assign({}, state, {filterMain: restoreFilters(state, action.storedMap.data.filters)});
    
    case Constants.RESET_FILTER:
      filterMain = cloneDeep(state.filterMain);
      filterMain = resetAllFilters(filterMain);
      return Object.assign({}, state, {
        filterMain: filterMain
      });
    
    case COPY_COMPARE_FILTERS:
      return action.filters;
    
    default:
      return state
  }
};

/**
 * Legacy code that should take the object *filterMain* and update it with the values of  *filters*.
 */
export const restoreFilters = (state, filters) => {
  let copyState = cloneDeep(state.filterMain);
  copyState = resetAllFilters(copyState);
  
  if (filters) {
    Object.keys(filters).map(k => {
      if (k.indexOf("_min") != -1 || k.indexOf("_max") != -1) {
        let param = k.indexOf("_min") != -1 ? k.substring(0, k.search("_min")) : k.substring(0, k.search("_max"));
        let value = k.indexOf("_min") != -1 ? {'minSelected': filters[k]} : {'maxSelected': filters[k]};
        Object.assign(copyState[param], value, {'isRange': true});
      } else if (k === 'pr') {
        let value = {'id': filters[k],};
        if (copyState[k] === undefined) {
          copyState[k] = {};
        }
        Object.assign(copyState[k], value);
      } else if (k === 'pr_keyword') {
        copyState[k] = filters[k];
      } else {
        filters[k].forEach(e => {
          updateFilterSelection(copyState[k], e, true);
          updateFilterCounters(copyState[k]);
        });
      }
    });
  }
  
  return copyState;
};

const filter = (state = {
  isLoaded: false,
  isFetching: false,
  items: []
}, action) => {
  switch (action.type) {
    case Constants.FILTER_SET_RANGE:
      return Object.assign({}, state, {
        isRange: true,
        minSelected: action.filter.minSelected,
        maxSelected: action.filter.maxSelected
      });
    
    case Constants.SELECT_ALL_FILTER_LIST:
      return Object.assign({}, state, {
        selected: action.item.selected,
        items: state.items !== undefined ? state.items.map(i => filterItem(i, action)) : undefined
      });
    
    case Constants.SELECT_FILTER_ITEM:
    case Constants.SEARCH_FILTER_LIST_BY_TEXT:
      return Object.assign({}, state, {
        items: state.items !== undefined ? state.items.map(i => filterItem(i, action)) : undefined
      });
    
    default:
      return state
  }
};

const filterItem = (state = {
  selected: false
}, action) => {
  let copyState = cloneDeep(state);
  switch (action.type) {
    case Constants.SELECT_FILTER_ITEM:
      updateFilterSelection(copyState, action.item.id, action.item.selected);
      return Object.assign({}, copyState);
    
    case Constants.SELECT_ALL_FILTER_LIST:
      updateFilterSelection(copyState, 'all', action.item.selected);
      return copyState;
    
    case Constants.SEARCH_FILTER_LIST_BY_TEXT:
      searchByTextIntoChildren(copyState, action.text);
      return copyState;
    
    default:
      return state
  }
};

//This function iterates over all children items and select the given one
const updateFilterSelection = (item, id, selection) => {
  if (item.id === id || 'all' === id) {
    updateItemAndChildren(item, selection);
  } else if (item.items && item.items.length > 0) {
    item.items.forEach(it => updateFilterSelection(it, id, selection));
    let selectionLength = item.items.filter((it) => {
      return it.selected
    }).length;
    if (item.items.length == selectionLength) {
      Object.assign(item, {'selected': true});
    } else {
      Object.assign(item, {'selected': false});
    }
  }
};

const updateItemAndChildren = (item, selection) => {
  Object.assign(item, {'selected': selection});
  if (item.items && item.items.length > 0) {
    item.items.forEach(it => updateItemAndChildren(it, selection));
  }
};

//This function add the total and selected counter fields to each object that has children
const updateFilterCounters = (filterObject) => {
  let count = 0;
  let countSel = 0;
  if (filterObject && filterObject.items && filterObject.items.length > 0) {
    count = filterObject.items.length;
    countSel = filterObject.items.filter((it) => {
      return it.selected
    }).length;
    filterObject.items.forEach((item) => {
      let cnts = updateFilterCounters(item);
      count = count + cnts.count;
      countSel = countSel + cnts.countSel;
    });
    Object.assign(filterObject, {'totalCounter': count, 'selectedCounter': countSel});
  }
  return {count: count, countSel: countSel}
};

//This function search by text into the items and its children
const searchByTextIntoChildren = (item, keyword) => {
  let itemMatch = itemMatchs(item, keyword);
  let childrenMatch = false;
  if (item.items && item.items.length > 0) {
    item.items.forEach((it) => {
      if (searchByTextIntoChildren(it, keyword)) {
        childrenMatch = true;
      }
    });
  }
  if (itemMatch || childrenMatch) {
    Object.assign(item, {'hide': false});
    return true;
  } else {
    Object.assign(item, {'hide': true});
    return false;
  }
};

const itemMatchs = (item, keyword) => {
  if (keyword.length > 1) {
    const pattern = new RegExp(keyword, 'i');
    return pattern.test(item.name);
  } else {
    return true;
  }
};

const makeAllOptionsVisible = (filters) => {
  for (let fltr in filters) {
    if (!filters[fltr].isRange) {
      if (filters[fltr].items && filters[fltr].items.length > 0) {
        filters[fltr].items.forEach((it) => {
          makeVisibleIntoChildren(it);
        });
      }
    }
  }
};

const makeVisibleIntoChildren = (item) => {
  Object.assign(item, {'hide': false});
  if (item.items && item.items.length > 0) {
    item.items.forEach((it) => {
      makeVisibleIntoChildren(it);
    });
  }
};

const resetAllFilters = (filterMain) => {
  let actionDummy = {type: Constants.SELECT_ALL_FILTER_LIST, item: {selected: false}};
  for (let filterKey in filterMain) {
    let filterClean;
    if (!filterMain[filterKey].isRange) {
      filterClean = filter(filterMain[filterKey], actionDummy);
      updateFilterCounters(filterClean);
    } else {
      filterClean = cloneDeep(filterMain[filterKey]);
      delete filterClean['minSelected'];
      delete filterClean['maxSelected'];
    }
    Object.assign(filterMain, {[filterKey]: filterClean});
  }
  return filterMain;
};

// copy the compare map over the main map
export const copyCompareFilters = () => {
  return (dispatch, getState) => {
    const filters = getState().compare.get("filters");
    dispatch({type: COPY_COMPARE_FILTERS, filters})
  }
};

export default filters;