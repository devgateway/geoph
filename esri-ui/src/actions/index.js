import * as Constants from 'app/constants/constants';
import AjaxUtil from 'app/util/AjaxUtil';
import Settings from 'app/util/Settings';

export const setLanguage = (lang) => {
  return {
    type: Constants.SET_APP_LANGUAGE,
    lang 
  }
}

