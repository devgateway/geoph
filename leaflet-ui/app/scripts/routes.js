import React from 'react';
import { Route, IndexRoute } from 'react-router';
/* containers */
import App from './components/layout/root';

import Landing from './components/layout/landing';
import Tools from './components/layout/tools';
import Charts from './components/charts/chartsTab';

const NoMatch = React.createClass({
  
  render() {
    return (
      <div>
        <h2>404</h2>
      </div>
    )
  }
})


export default (
  	<Route path="/" component={App}>
  	<Route path="tools" component={Tools}/>
  	<Route path="charts" component={Charts}/>
    <IndexRoute path="tools" component={Tools}/>
  	<Route path="*" component={NoMatch}/>
	</Route>
);