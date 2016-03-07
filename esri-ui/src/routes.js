import React from 'react';
import { Route, IndexRoute } from 'react-router';

/* containers */
import App from 'app/components/App.jsx';
import Landing from 'app/components/Landing.jsx';

export default (
  	<Route path="/" component={App}>
	  <IndexRoute component={Landing} />
	</Route>
);