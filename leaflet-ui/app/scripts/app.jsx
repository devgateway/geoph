require('bootstrap/dist/css/bootstrap.css');
require('./app.scss');

import React from 'react';
import {render} from 'react-dom';

import i18next from 'i18next';
import XHR from 'i18next-xhr-backend';

import { Provider } from 'react-redux'
import configureStore from './store/configureStore';

import { Router, browserHistory, hashHistory } from 'react-router';
import {syncHistoryWithStore} from 'react-router-redux';
import routes from './routes';

import AjaxUtil from './util/ajax';
import Setting from './util/settings';


const redirectMiddleWare = store => next => action => {
  if (action.transition) {
    history.push(action.transition);
  } else {
    return next(action)
  }
};

const store = configureStore({}, browserHistory, redirectMiddleWare);
const history = syncHistoryWithStore(hashHistory, store);

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
  
});
