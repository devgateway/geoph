import React from 'react';
import { Route, IndexRoute } from 'react-router';
/* containers */
import App from './components/layout/root';

import Landing from './components/layout/landing';

export default (
  	<Route path="/geoph" component={App}>
	  <IndexRoute component={Landing} />
	</Route>
);