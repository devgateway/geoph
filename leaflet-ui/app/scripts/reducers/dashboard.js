import * as Constants from '../constants/constants';
import Immutable from "immutable";

const initialState = Immutable.fromJS({
  results: []
});

const stats = (state = initialState, action) => {
  switch (action.type) {
    case Constants.REQUEST_MAP_LIST_OK:
      return state.setIn(['results'], action.data.content)
        .setIn(['first'], action.data.first)
        .setIn(['last'], action.data.last)
        .setIn(['totalPages'], action.data.totalPages);
    
    case Constants.REQUEST_DELETE_MAP_OK:
      const results = state.get('results').filter((dash)=>{
        return (dash.key !== action.key)
      });
      return state.setIn(['results'], Object.assign(results, results));
    
    case Constants.ACTIVATE_SAVED_MAP:
      const { index } = action;
      
      return state.set('results', state.get('results').map((map, idx)=>{
        map.selected = (index === idx);
        return map;
      }));
    
    case Constants.RESET_FEATURED_MAP:
      return state.setIn(['results'], state.getIn(['results']).map(item => {
        item.selected = false;
        return item;
      }));
    
    default:
      return state;
  }
};

export default stats;
