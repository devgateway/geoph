import * as Constants from '../constants/constants';

const language = (state = 'en', action) => {
	console.log("lang: "+action.lang);
  switch (action.type) {
    case Constants.SET_APP_LANGUAGE:
      return action.lang;
    default:
      return state
  }
}

export default language