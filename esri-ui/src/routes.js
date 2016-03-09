import React from 'react';
import { Route, IndexRoute } from 'react-router';

/* containers */
import App from 'app/components/App';
import Landing from 'app/components/Landing';

export default (
  	<Route path="/" component={App}>
	  <IndexRoute component={Landing} />
	</Route>
);