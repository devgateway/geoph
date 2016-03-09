import { applyMiddleware, compose, createStore } from 'redux';

import thunkMiddleware from 'redux-thunk';

import routerMiddleware from 'react-redux-router/middleware';

import rootReducer from 'app/reducers/index';

export default function configureStore(initialState, browserHistory) {
  
  let historyMiddleware = routerMiddleware(browserHistory);
  let middleware = applyMiddleware(thunkMiddleware, historyMiddleware);
  const store = middleware(createStore)(rootReducer, initialState);
  
  return store;
}