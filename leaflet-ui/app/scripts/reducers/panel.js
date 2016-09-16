import * as Constants from '../constants/constants';
import {Map} from 'immutable'

const panel = (state =new Map({'expanded': false,'visible':false}), action) => {
  switch (action.type) {
	case Constants.TOGGLE_PANEL:
	debugger;
      return state.set('visible',!state.get('visible'));
    case Constants.TOGGLE_PANEL_EXPANDED:

      return state.set('expanded',!state.get('expanded'));

    default:
      return state
  }
}

export default panel