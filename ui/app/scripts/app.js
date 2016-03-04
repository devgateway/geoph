/*webpack require directives*/

require('bootstrap/dist/css/bootstrap.css');
require('font-awesome/css/font-awesome.css');
require('intro.js/introjs.css');
require('babel-polyfill');
require('../stylesheets/main.scss');

import React from 'react';
import { render } from 'react-dom';

import i18next from 'i18next';
import XHR from 'i18next-xhr-backend';

import AjaxUtil from './util/AjaxUtil.es6';
import Setting from './util/Settings.es6';

import { Provider } from 'react-redux'
import { Router, useRouterHistory } from 'react-router';
import { createHashHistory } from 'history';
import configureStore from './store/configureStore';
import routes from './routes';

const history = useRouterHistory(createHashHistory)({ queryKey: false });
const store = configureStore({}, history);

AjaxUtil.get('conf/settings.json').then((conf)=>{
  
  let settings=new Setting();
  settings.initialize(conf.data);
  const options = settings.get('I18N', 'OPTIONS');

  i18next.use(XHR).init(options, (err, t) => {
    //if a locale was loaded 

    render((
      <Provider store={store}>
        <Router history={history} routes={routes} />
      </Provider>
      ), document.getElementById('root'))
  });

})


