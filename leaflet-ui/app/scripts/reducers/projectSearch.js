import * as Constants from '../constants/constants';
import {cloneDeep} from '../util/filterUtil';

const projectSearch = (state = {'selected': [], 'applied': [], 'results': {}, 'keyword': ''}, action) => {
  let projectSearchResults;
  let stateCloned;
  
  switch (action.type) {
    case Constants.TOGGLE_PROJECT_SELECTION:
      stateCloned = cloneDeep(state);
      let itemIndex = -1;
      
      stateCloned.selected.forEach((item, index) => {
        if (item.id == action.project.id) {
          itemIndex = index;
        }
      });
      if (itemIndex != -1) {
        stateCloned.selected.splice(itemIndex, 1);
      } else {
        stateCloned.selected.push(action.project);
      }
      return stateCloned;
    
    case Constants.SELECT_ALL_MATCHED_PROJECT:
      stateCloned = cloneDeep(state);
      if (stateCloned.results.content !== undefined) {
        stateCloned.results.content.forEach((item) => {
          let isAlreadyAdded = false;
          stateCloned.selected.forEach((it) => {
            it.id == item.id ? isAlreadyAdded = true : null;
          });
          if (!isAlreadyAdded) {
            stateCloned.selected.push(item);
          }
        });
      }
      return stateCloned;
    
    case Constants.STATE_RESTORE:
      stateCloned = cloneDeep(state);
      stateCloned.selected = [];
      stateCloned.applied = [];
      stateCloned.results = [];
      stateCloned.keyword = "";
      action.storedMap.data.filters['pr'].forEach(e => {
        stateCloned.selected.push({id: e});
      });
      if (action.storedMap.data.filters['pr_keyword'] !== undefined) {
        stateCloned.keyword = action.storedMap.data.filters['pr_keyword'];
      }
      return stateCloned;
    
    case Constants.APPLY_PROJECT_SELECTED:
      stateCloned = cloneDeep(state);
      Object.assign(stateCloned, {'applied': state.selected});
      return stateCloned;
    
    case Constants.CLEAR_ALL_PROJECT_SELECTED:
      stateCloned = cloneDeep(state);
      Object.assign(stateCloned, {'selected': []});
      Object.assign(stateCloned, {'applied': []});
      return stateCloned;
    
    case Constants.CLEAR_ALL_RESULTS:
      stateCloned = cloneDeep(state);
      Object.assign(stateCloned, {'keyword': ''});
      Object.assign(stateCloned, {'results': {}});
      return stateCloned;
    
    case Constants.REQUEST_PROJECT_BY_TEXT:
      projectSearchResults = {isFetching: true};
      return Object.assign({}, state, {'results': projectSearchResults});
    
    case Constants.SET_KEYWORD:
      return Object.assign({}, state, {'keyword': action.keyword});
    
    case Constants.RECEIVE_PROJECT_BY_TEXT:
      stateCloned = cloneDeep(state);
      
      // check if we have selected projects but without title. in that case try to match the titles from the newly fetched results
      if (stateCloned.selected.length !== 0 && stateCloned.selected[0].title === undefined) {
        const selected = [];
        stateCloned.selected.forEach(selectedItem => {
          action.data.content.forEach(item => {
            if (selectedItem.id === item.id) {
              selected.push(item);
            }
          });
        });
        
        Object.assign(stateCloned, {'selected': selected});
      }
      
      projectSearchResults = {lastUpdate: action.receivedAt, isFetching: false};
      Object.assign(projectSearchResults, action.data);
      Object.assign(stateCloned, {'results': projectSearchResults});
      
      return stateCloned;
    
    case Constants.COPY_COMPARE_PROJECT_SEARCH:
      return action.projectSearch;
    
    default:
      return state;
  }
};

export default projectSearch;
