//import es6Promise from 'es6-promise';
import babelPolyfill from 'babel-polyfill';

import React from 'react';
import { render } from 'react/react-dom';

import i18next from 'i18next';
import XHR from 'i18next-xhr-backend';

import { Provider } from 'react-redux'
import configureStore from 'app/store/configureStore';

import { Router,browserHistory } from 'react-router';
import {syncHistoryWithStore} from 'react-redux-router';
import routes from 'app/routes';

import AjaxUtil from 'app/util/ajax';
import Setting from 'app/util/setting';


const store = configureStore({}, browserHistory);
const history = syncHistoryWithStore(browserHistory, store);

AjaxUtil.get('conf/settings.json').then((conf)=>{
  Setting.initialize(conf.data);
  const options = Setting.get('I18N', 'OPTIONS');

  i18next.use(XHR).init(options, (err, t) => {
    render((
      <Provider store={store}>
        <Router history={history} routes={routes} />
      </Provider>
      ), document.getElementById('root'))
  });

})

