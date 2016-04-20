import { combineReducers } from 'redux';
import language from './language';
import filters from './filters';
import map from './map';
import charts from './charts';
import settings from './settings';

import {routerReducer}  from 'react-router-redux';

/*reducer names should match with a state property*/

const geophApp = combineReducers({
  language,
  filters,
  map,
  charts,
  settings,
  routing: routerReducer
})

export default geophApp