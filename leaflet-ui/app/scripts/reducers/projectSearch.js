import * as Constants from '../constants/constants';
import {cloneDeep} from '../util/filterUtil';

const projectSearch = (state = {'selected': [], 'applied': [], 'results': {}}, action) => {
  let projectSearchResults;
  let stateCloned;
  switch (action.type) {
    case Constants.TOGGLE_PROJECT_SELECTION:
      stateCloned = cloneDeep(state);
      let itemIndex = -1
      stateCloned.selected.forEach((item, index) => {
        if (item.id == action.project.id){
          itemIndex = index;
        }
      })
      if (itemIndex!=-1){
        stateCloned.selected.splice(itemIndex, 1);
      } else {
        stateCloned.selected.push(action.project);
      }
      return stateCloned;
    case Constants.SELECT_ALL_MATCHED_PROJECT:
      stateCloned = cloneDeep(state);
      stateCloned.results.content.forEach((item) => {
        let isAlreadyAdded = false;
        stateCloned.selected.forEach((it) => {it.id==item.id? isAlreadyAdded=true : null;});
        if (!isAlreadyAdded){
          stateCloned.selected.push(item);
        }
      })
      return stateCloned;
    case Constants.STATE_RESTORE:
      stateCloned = cloneDeep(state);
      action.storedMap.data.filters['pr'].forEach(e=>{
        stateCloned.selected.push({id: e});
      }); 
      return stateCloned;

    case Constants.APPLY_PROJECT_SELECTED:
      stateCloned = cloneDeep(state);
      Object.assign(stateCloned, {'applied': state.selected});
      return stateCloned;

    case Constants.CLEAR_ALL_PROJECT_SELECTED:
      stateCloned = cloneDeep(state);
      Object.assign(stateCloned, {'selected':[]});
      Object.assign(stateCloned, {'applied':[]});
      return stateCloned;

    case Constants.CLEAR_ALL_RESULTS:
      stateCloned = cloneDeep(state);
      Object.assign(stateCloned, {'results': {}});
      return stateCloned;

    case Constants.REQUEST_PROJECT_BY_TEXT:
      projectSearchResults = {isFetching: true};
      return Object.assign({}, state, {'results': projectSearchResults});
    case Constants.REQUEST_RESTORE_MAP:
    case Constants.RECEIVE_PROJECT_BY_TEXT:
      console.log('projectSearch reducer')
      projectSearchResults = {lastUpdate: action.receivedAt, isFetching: false};
      Object.assign(projectSearchResults, action.data);
      return Object.assign({}, state, {'results': projectSearchResults});
    default:
      return state
  }
}


export default projectSearch