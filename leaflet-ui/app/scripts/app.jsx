require('bootstrap/dist/css/bootstrap.css');
//require('font-awesome/css/font-awesome.css');
require('./app.scss');

import babelPolyfill from 'babel-polyfill';

import React from 'react';
import {render} from 'react-dom';

import i18next from 'i18next';
import XHR from 'i18next-xhr-backend';

import { Provider } from 'react-redux'
import configureStore from './store/configureStore';

import { Router,browserHistory,hashHistory } from 'react-router';
import {syncHistoryWithStore} from 'react-router-redux';
import routes from './routes';

import AjaxUtil from './util/ajax';
import Setting from './util/settings';
import Connector from './connector/connector.js';


const redirectMiddleWare = store => next => action => {
  
  if (action.transition){
    history.push(action.transition);
  }else{
   return next(action)
 }
 
};

const store = configureStore({}, browserHistory,redirectMiddleWare);
const history = syncHistoryWithStore(hashHistory, store);

import {push} from 'react-router-redux';



AjaxUtil.get('conf/settings.json').then((conf)=>{
  Setting.initialize(conf.data);
  const options = Setting.get('I18N', 'OPTIONS');

  i18next.use(XHR).init(options, (err, t) => {
  	if(err){
      console.log(err);
    }

    render((
      <Provider store={store}>
      <Router history={history} routes={routes} />
      </Provider>
      ), document.getElementById('root'))
  });

})

