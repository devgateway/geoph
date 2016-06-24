import {SET_APP_LANGUAGE,TOGGLE_SELECTOR } from '../constants/constants';

const language = (state = {lan:"en",selector:{visible:false}}, action) => {
	
	let newState=Object.assign({},state);
	switch (action.type) {
		case SET_APP_LANGUAGE:
			
			return  Object.assign(newState,{lan:action.lang})

		case TOGGLE_SELECTOR:
			
			return  Object.assign(newState,{selector:{visible:!state.selector.visible}})

		default:
			return state
	}
}

export default language