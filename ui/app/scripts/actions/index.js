import Constants from '../constants/constants.es6';
import i18next from 'i18next';

export const setLanguage = (lang) => {
  i18next.changeLanguage(lang);
  return {
    type: Constants.SET_APP_LANGUAGE,
    lang 
  }
}