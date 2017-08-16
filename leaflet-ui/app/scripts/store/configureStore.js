import { applyMiddleware, createStore } from 'redux';
import thunkMiddleware from 'redux-thunk';
import rootReducer from '../reducers/index';
import { routerMiddleware } from 'react-router-redux';

export default function configureStore(initialState, browserHistory, redirectMiddleWare, securityMiddleWare) {
  let historyMiddleware = routerMiddleware(browserHistory);
  let middleware = applyMiddleware(thunkMiddleware, historyMiddleware, redirectMiddleWare);
  return middleware(createStore)(rootReducer, initialState);
}