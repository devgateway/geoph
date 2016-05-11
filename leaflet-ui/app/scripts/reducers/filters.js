import * as Constants from '../constants/constants';

import Immutable from 'immutable';

const defaultState = Immutable.fromJS({
  filters: [
    {
      filterType: 'ft',
      name: '',
      items: [],
      isFetching: false
    },
    {
      filterType: 'fa',
      name: '',
      items: [],
      isFetching: false
    },
    {
      filterType: 'ia',
      name: '',
      items: [],
      isFetching: false
    },
    {
      filterType: 'ip',
      name: '',
      items: [],
      isFetching: false
    },
    {
      filterType: 'gp',
      name: '',
      items: [],
      isFetching: false
    },
    {
      filterType: 'fs',
      name: '',
      items: [],
      isFetching: false
    },
    {
      filterType: 'pr',
      name: '',
      items: [],
      isFetching: false
    }
  ]
});

const defaultFilterState = Immutable.fromJS({
  isFetching: false,
  items: []  
});

const defaultItemState = Immutable.fromJS({
  selected: false
});


const filters = (state = defaultState, action) => {
  switch (action.type) {
    case Constants.SELECT_FILTER_ITEM:
    case Constants.SELECT_ALL_FILTER_LIST:
    case Constants.RECEIVE_FILTER_DATA:
    case Constants.REQUEST_FILTER_DATA:
    case Constants.FILTER_SET_RANGE:
    case Constants.SEARCH_FILTER_LIST_BY_TEXT:
      return state.set('filters', setDataToFilters(state.get('filters'), action));

      let fl = filter(state[action.filterType], action);
      let flObj = {}
      return state.set((flObj[action.filterType] = updateFilterCounters(fl)));
      /*updateFilterCounters(fl);
      return Object.assign({}, state, {
        [action.filterType]: fl
      })*/
    default:
      return state
  }
}

const setDataToFilters = (filters, action) => {
  return filters.map((f)=>{
    if (f.get('filterType') == action.filterType){
      return f.merge(filter(f, action));
    } 
    return f;
  });
}

const filter = (state = defaultFilterState, action) => {
  let fl;
  switch (action.type) {
    case Constants.REQUEST_FILTER_DATA:
      return state.set('isFetching', true);
      /*return Object.assign({}, state, {
        isFetching: true,
      })*/
    case Constants.RECEIVE_FILTER_DATA:
      debugger;
      return state.merge({
          'isFetching': false, 
          'lastUpdated': action.receivedAt
        }, 
        action.data
      );
      /*return Object.assign({}, state, action.data, {
        isFetching: false,
        lastUpdated: action.receivedAt
      })*/

    case Constants.FILTER_SET_RANGE:
      return state.merge({
          'isRange': true, 
          'minSelected': action.filter.minSelected,
          'maxSelected': action.filter.maxSelected
        }, 
        action.data
      );
      /*return Object.assign({}, state, {
          isRange: true,
          minSelected: action.filter.minSelected,
          maxSelected: action.filter.maxSelected
      })*/
    case Constants.SELECT_FILTER_ITEM:
      return state.set('items', setDataToFilterItem(state.get('items'), action));

      fl = Object.assign({}, state, {items: state.items.map(i => filterItem(i, action))});
      return state.set(fl);
      /*return Object.assign({}, state, {
          isFetching: false,
          items: state.items.map(i => filterItem(i, action))
      })*/
    case Constants.SELECT_ALL_FILTER_LIST:
      fl = Object.assign({}, state, {
          selected: action.item.selected,
          items: state.items.map(i => filterItem(i, action))
      });
      return state.set(fl);
      /*return Object.assign({}, state, {
          isFetching: false,
          selected: action.item.selected,
          items: state.items.map(i => filterItem(i, action))
      })*/
    case Constants.SEARCH_FILTER_LIST_BY_TEXT:
      fl = Object.assign({}, state, {items: state.items.map(i => filterItem(i, action))});
      return state.set(fl);
      /*return Object.assign({}, state, {
          isFetching: false,
          items: state.items.map(i => filterItem(i, action))
      })*/
    default:
      return state
  }
}

const setDataToFilterItem = (filterItems, action) => {
  return filterItems.map((f)=>{
    if (f.get('id') == action.item.id){
      return f.merge(filterItem(f, action));
    } 
    return f;
  });
}

const filterItem = (state = defaultItemState, action) => {
  //let copyState = Object.assign({}, state); 
  //let newState;
  switch (action.type) {
    case Constants.SELECT_FILTER_ITEM:
      return updateFilterSelection2(state, action.item.id, action.item.selected);
      //updateFilterSelection(copyState, action.item.id, action.item.selected); 
      //return copyState
    case Constants.SELECT_ALL_FILTER_LIST:
      return updateFilterSelection2(state, 'all', action.item.selected);
      //updateFilterSelection(copyState, 'all', action.item.selected); 
      //return copyState
    case Constants.SEARCH_FILTER_LIST_BY_TEXT:
      return searchByTextIntoChildren2(state, action.text);
      //searchByTextIntoChildren(copyState, action.text); 
      //return copyState
    default:
      return state
  }
}

//This function iterates over all children items and select the given one
const updateFilterSelection2 = (item, id, selection) => { 
  if (item.id === id || 'all' === id){
    return updateItemAndChildren2(item, selection);
  } else if (item.items && item.items.length>0){
    item.items.forEach(it => updateFilterSelection2(it, id, selection));
  }
}

const updateItemAndChildren2 = (item, selection) => { 
  if (item.items && item.items.length>0){
    return item.merge({'selected': selection, 'items': updateSubItems(item.items, selection)});
  } else {
    return item.set('selected', selection);
  }
}

const updateSubItems = (items, selection) => { 
  let itemsCopy = [];
  items.forEach(it => {
    if (it.items && it.items.length>0) {
      itemsCopy.push(it.set({'selected': selection, 'items': updateSubItems(it.items, selection)}));
    } else {
      itemsCopy.push(it.set({'selected': selection}));
    }
  }); 
  return itemsCopy;
}
/*
//This function iterates over all children items and select the given one
const updateFilterSelection = (item, id, selection) => { 
  if (item.id === id || 'all' === id){
    updateItemAndChildren(item, selection);
  } else if (item.items && item.items.length>0){
    item.items.forEach(it => updateFilterSelection(it, id, selection));
  }
}

const updateItemAndChildren = (item, selection) => { 
  Object.assign(item, {'selected': selection});
  if (item.items && item.items.length>0){
    item.items.forEach(it => updateItemAndChildren(it, selection));
  }  
}
*/
//This function add the total and selected counter fields to each object that has children
const updateFilterCounters = (filterObject) => { 
  return filterObject.set({'totalCounter': 0, 'selectedCounter': 0});
  if (filterObject.items && filterObject.items.length>0){
    Object.assign(filterObject, {'totalCounter': filterObject.items.length});
    Object.assign(filterObject, {'selectedCounter': filterObject.items.filter((it) => {return it.selected}).length});
    filterObject.items.forEach((item) => {updateFilterCounters(item)});
  }
}

//This function search by text into the items and its children
const searchByTextIntoChildren2 = (item, keyword) => { 
  let itemMatch = itemMatchs(item, keyword);
  let childrenMatch = false;
  let itemsCopy = [];
  if (item.items && item.items.length>0){
    item.items.forEach((it) => {
      let itChild = searchByTextIntoChildren(it, keyword);
      if (itChild.get('hide')==false){
        childrenMatch = true;
      }
      itemsCopy.push(itChild);
    });
  }
  if (itemMatch || childrenMatch){
    if (item.items && item.items.length>0){
      return item.set({'hide': false, 'items': itemsCopy});
    } else {
      return item.set({'hide': false});
    }
  } else {
    if (item.items && item.items.length>0){
      return item.set({'hide': true, 'items': itemsCopy});
    } else {
      return item.set({'hide': true});
    }
  }
}

/*
//This function search by text into the items and its children
const searchByTextIntoChildren = (item, keyword) => { 
  let itemMatch = itemMatchs(item, keyword);
  let childrenMatch = false;
  if (item.items && item.items.length>0){
    item.items.forEach((it) => {
      if (searchByTextIntoChildren(it, keyword)){
        childrenMatch = true;
      }
    });
  }
  if (itemMatch || childrenMatch){
    Object.assign(item, {'hide': false});
    return true;
  } else {
    Object.assign(item, {'hide': true});
    return false;
  }
}
*/
const itemMatchs = (item, keyword) => {
  if (keyword.length > 1) {
    var pattern = new RegExp(keyword, 'i');
    return pattern.test(item.name);
  } else {
    return true;
  }
}

export default filters