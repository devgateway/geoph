import React from 'react';
import { Route, IndexRoute } from 'react-router';
/* containers */
import App from './components/layout/root';

import Landing from './components/layout/landing';
import Tools from './components/layout/tools';
import Charts from './components/charts/chartsTab';
import Admin from './components/admin/admin';
import AddIndicator from './components/admin/addIndicator.jsx';
import ListIndicator from './components/admin/listIndicator.jsx';
import Restore from './components/save-restore/restore.jsx';

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
    <Route path="map/:key" component={Restore}/>
    <Route path="admin" component={Admin}>
      <Route path="/admin/add/indicator" component={AddIndicator}/>
      <Route path="/admin/list/indicator" component={ListIndicator}/>
    </Route>
    <IndexRoute component={Tools}/>
  	<Route path="*" component={NoMatch}/>
	</Route>
);