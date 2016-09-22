import * as Constants from '../constants/constants';
import Immutable from 'immutable';

const defaultState = Immutable.fromJS(
  {step:'template', template:'region', name:'', css:'yellow', status:"CREATE_NEW"}
);

const indicatorWizard = (state = defaultState, action) => {

  state=state.delete('httpError').delete('errors');

  switch (action.type) {

    case Constants.INDICATOR_LIST_LOADED:
    state = defaultState; //clean creation data
    return state.set('indicators',action.data);
    
    case Constants.CHANGE_STEP:
    return state.set('step',action.step);
    
    case Constants.CHANGE_PROPERTY:
    return state.set(action.property,action.value);

    case Constants.UPDATE_ERRORS:
    return state.set('errors',action.errors);

    case Constants.INDICATOR_UPLOAD_FAILURE:
    return state.set('httpError',action.httpError);


    default:
    return state
}
}

export default indicatorWizard