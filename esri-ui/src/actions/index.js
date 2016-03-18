import * as Constants from 'app/constants/constants';
import Settings from 'app/util/setting';

export const setLanguage = (lang) => {
  return {
    type: Constants.SET_APP_LANGUAGE,
    lang 
  }
}

