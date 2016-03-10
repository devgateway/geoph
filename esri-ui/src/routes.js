import React from 'react';
import { Route, IndexRoute } from 'react-router';
/* containers */
import App from 'app/components/layout/root';

import Landing from 'app/components/layout/landing';

export default (
  	<Route path="/" component={App}>
	  <IndexRoute component={Landing} />
	</Route>
);