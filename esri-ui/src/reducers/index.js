import { combineReducers } from 'redux';
import language from './language';
import filters from './filters';
import {routerReducer}  from 'react-redux-router';

/*reducer names should match with a state property*/

const geophApp = combineReducers({
  language,
  filters,
  routing: routerReducer
})

export default geophApp