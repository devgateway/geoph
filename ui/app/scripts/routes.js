import React from 'react';
import { Route, IndexRoute } from 'react-router';

/* containers */
import App from './components/App';
import NoMatch from './components/NoMatch';
import Landing from './components/Landing';

export default (
  	<Route path="/" component={App}>
	  <IndexRoute component={Landing} />
	</Route>
);