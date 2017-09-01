import * as Constants from '../constants/constants';
import {Map} from 'immutable';

const stats = (state = new Map({}), action) => {
  switch (action.type) {
    case Constants.REQUEST_MAP_LIST_OK:
      return state.setIn(['results'], action.data.content);
    
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
    
    default:
      return state;
  }
};

export default stats;
