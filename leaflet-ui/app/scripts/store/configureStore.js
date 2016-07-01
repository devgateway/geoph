import { applyMiddleware, compose, createStore } from 'redux';

import thunkMiddleware from 'redux-thunk';


import rootReducer from '../reducers/index';

import { routerMiddleware, push,replace  } from 'react-router-redux'





	export default function configureStore(initialState, browserHistory,redirectMiddleWare,securityMiddleWare) {

		let historyMiddleware = routerMiddleware(browserHistory);
		let middleware = applyMiddleware(thunkMiddleware, historyMiddleware,redirectMiddleWare);
		const store = middleware(createStore)(rootReducer, initialState);

		return store;
	}