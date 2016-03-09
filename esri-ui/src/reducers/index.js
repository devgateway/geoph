import { combineReducers } from 'redux';
import language from './language';
import filters from './filters';
import { routerReducer } from 'app/react-redux-router/reducer';

const geophApp = combineReducers({
  language,
  filters,
  routing: routerReducer
})

export default geophApp