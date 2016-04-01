require('bootstrap/dist/css/bootstrap.css');
require('font-awesome/css/font-awesome.css');
require('react-date-picker/base.css');
require('react-date-picker/theme/hackerone.css');
require('../stylesheets/main.scss');

 
import babelPolyfill from 'babel-polyfill';

import React from 'react';
import {render} from 'react-dom';

import i18next from 'i18next';
import XHR from 'i18next-xhr-backend';

import { Provider } from 'react-redux'
import configureStore from './store/configureStore';

import { Router,browserHistory } from 'react-router';
import {syncHistoryWithStore} from 'react-router-redux';
import routes from './routes';

import AjaxUtil from './util/ajax';
import Setting from './util/settings';


const store = configureStore({}, browserHistory);
const history = syncHistoryWithStore(browserHistory, store);

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

