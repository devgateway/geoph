
import * as Constants from '../constants/constants';
import {cloneDeep} from '../util/filterUtil';
import {Map} from 'immutable'

const stats = (state = new Map({}), action) => {
  	switch (action.type) {
     	case Constants.REQUEST_MAP_LIST_OK:
     		return state.setIn(['results'],action.data);
		case Constants.REQUEST_DELETE_MAP_OK:
     		action.key
     		let results = state.get('results');
     		let content = results.content.filter((dash)=>{			
				return (dash.key!=action.key)
			});
    		return state.setIn(['results'], Object.assign(results, {content}));
		default:
	  		return state
  	}
}

export default stats