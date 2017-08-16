import { applyMiddleware, createStore, compose } from 'redux';
import thunkMiddleware from 'redux-thunk';
import rootReducer from '../reducers/index';
import { routerMiddleware } from 'react-router-redux';

export default function configureStore(initialState, browserHistory, redirectMiddleWare, securityMiddleWare) {
  const enhancers = [];
  if (process.env.NODE_ENV === undefined || process.env.NODE_ENV === 'development') {
    const devToolsExtension = window.devToolsExtension;
    if (typeof devToolsExtension === 'function') {
      enhancers.push(devToolsExtension());
    }
  }
  
  const historyMiddleware = routerMiddleware(browserHistory);
  const middleware = compose(applyMiddleware(thunkMiddleware, historyMiddleware, redirectMiddleWare), ...enhancers);
  return middleware(createStore)(rootReducer, initialState);
}