//import es6Promise from 'es6-promise';
import React from 'react';
import { render } from 'react/react-dom';
import Landing from 'app/components/Landing';
import Header from 'app/components/Header'
import Footer from 'app/components/Footer'
import Map2d from 'app/components/map2d';
import Map3d from 'app/components/map3d';

import Redux from 'redux';
import i18next from 'i18next';
import XHR from 'i18next-xhr-backend';
import { Provider } from 'react-redux'
import { Router, useRouterHistory } from 'react-router';
import { createHashHistory } from 'history';
import configureStore from 'app/store/configureStore';
import routes from 'app/routes';
import AjaxUtil from 'app/util/AjaxUtil';
import Setting from 'app/util/Settings';

const history = useRouterHistory(createHashHistory)({ queryKey: false });
const store = configureStore({}, history);


AjaxUtil.get('/settings.json').then((conf)=>{
  
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

