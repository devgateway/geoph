import Constants from 'app/constants/constants.es6';

const filters = (state = {}, action) => {
	switch (action.type) {
    case Constants.SELECT_FILTER_ITEM:
    case Constants.SELECT_ALL_FILTER_LIST:
    case Constants.RECEIVE_FILTER_LIST:
    case Constants.REQUEST_FILTER_LIST:
      return Object.assign({}, state, {
        [action.filterType]: filter(state[action.filterType], action)
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
      return Object.assign({}, state, {
        isFetching: false,
        items: action.items,
        lastUpdated: action.receivedAt
      })

    case Constants.SELECT_FILTER_ITEM:
    case Constants.SELECT_ALL_FILTER_LIST:
    	return Object.assign({}, state, {
	        isFetching: false,
	        items: state.items.map(i => filterItem(i, action)),
	        lastUpdated: action.receivedAt
	    })
    default:
      return state
  }
}

const filterItem = (state = {
  selected: false
}, action) => {
  switch (action.type) {
    case Constants.SELECT_FILTER_ITEM:
    	if (state.id !== action.item.id) {
	        return state
	    }
	    return Object.assign({}, state, {
	        selected: action.item.selected
	    })
    case Constants.SELECT_ALL_FILTER_LIST:
      	return Object.assign({}, state, {
	        selected: action.item.selected
	    })
    default:
      return state
  }
}


export default filters