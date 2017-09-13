import * as Constants from '../constants/constants';
import {Map} from 'immutable';

const project = (state = new Map({projectData: {}, isPopupOpen: false, loadingData: false}), action) => {
  
  switch (action.type) {
    case Constants.OPEN_PROJECT_PAGE:
      state = state.set('loadingData', true);
      return state.set('isPopupOpen', true);
    
    case Constants.CLOSE_PROJECT_PAGE:
      state = state.set('loadingData', false);
      state = state.set('projectData', {});
      return state.set('isPopupOpen', false);
    
    case Constants.PROJECT_LOADED:
      state = state.set('loadingData', false);
      return state.set('projectData', action.data);
    
    default:
      return state;
  }
};

export default project;