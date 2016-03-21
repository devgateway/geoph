import * as Constants from '../constants/constants';
import Settings from '../util/setting';

export const setLanguage = (lang) => {
  return {
    type: Constants.SET_APP_LANGUAGE,
    lang 
  }
}

