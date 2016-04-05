import * as Constants from '../constants/constants';
import Settings from '../util/settings';

export const setLanguage = (lang) => {
  return {
    type: Constants.SET_APP_LANGUAGE,
    lang 
  }
}


export const showLanSelector = () => {
  return {
    type: Constants.TOGGLE_SELECTOR
    
  }
}
