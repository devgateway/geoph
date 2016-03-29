import * as Constants from '../constants/constants';

const filters = (state = {}, action) => {
	switch (action.type) {
    case Constants.SELECT_FILTER_ITEM:
    case Constants.SELECT_ALL_FILTER_LIST:
    case Constants.RECEIVE_FILTER_LIST:
    case Constants.REQUEST_FILTER_LIST:
      let fl = filter(state[action.filterType], action);
      updateFilterCounters(fl);
      return Object.assign({}, state, {
        [action.filterType]: fl
      })
    default:
      return state
  }
}

const filter = (state = {
  isFetching: false,
  items: []
}, action) => {
  switch (action.type) {
    case Constants.REQUEST_FILTER_LIST:
      return Object.assign({}, state, {
        isFetching: true,
      })
    case Constants.RECEIVE_FILTER_LIST:
      return Object.assign({}, state, action.data, {
        isFetching: false,
        lastUpdated: action.receivedAt
      })

    case Constants.SELECT_FILTER_ITEM:
      return Object.assign({}, state, {
          isFetching: false,
          items: state.items.map(i => filterItem(i, action))
      })
    case Constants.SELECT_ALL_FILTER_LIST:
    	return Object.assign({}, state, {
	        isFetching: false,
          selected: action.item.selected,
	        items: state.items.map(i => filterItem(i, action))
	    })

     

    default:
      return state
  }
}

const filterItem = (state = {
  selected: false
}, action) => {
  let copyState = Object.assign({}, state); 
  switch (action.type) {
    case Constants.SELECT_FILTER_ITEM:
      updateFilterSelection(copyState, action.item.id, action.item.selected); 
      return copyState
    case Constants.SELECT_ALL_FILTER_LIST:
      updateFilterSelection(copyState, 'all', action.item.selected); 
      return copyState
    default:
      return state
  }
}

//This function iterates over all children items and select the given one
const updateFilterSelection = (item, id, selection) => { 
  if (item.id === id || 'all' === id){
    updateItemAndChildren(item, selection);
  } else if (item.items && item.items.length>0){
    item.items.map(it => updateFilterSelection(it, id, selection));
  }
}

const updateItemAndChildren = (item, selection) => { 
  Object.assign(item, {'selected': selection});
  if (item.items && item.items.length>0){
    item.items.map(it => updateItemAndChildren(it, selection));
  }  
}

//This function add the total and selected counter fields to each object that has children
const updateFilterCounters = (filterObject) => { 
  if (filterObject.items && filterObject.items.length>0){
    Object.assign(filterObject, {'totalCounter': filterObject.items.length});
    Object.assign(filterObject, {'selectedCounter': filterObject.items.filter((it) => {return it.selected}).length});
    filterObject.items.forEach((item) => {updateFilterCounters(item)});
  }
}


export default filters