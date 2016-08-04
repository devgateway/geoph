import * as Constants from '../constants/constants';
import {Map} from 'immutable'


const share =(state = new Map({}), action) => {

  //console.log("--- saveMap reducer ---" + action);
  switch (action.type) {
    
    case Constants.CAPTURE_OK:
    debugger;
    return state.set('image',action.data.image);

    default:
    return state
  }
}

export default share