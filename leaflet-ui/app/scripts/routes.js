import React from 'react';
import { Route, IndexRoute ,IndexRedirect} from 'react-router';
/* containers */
import Regular from './components/layout/regular';
import Restore from './components/layout/restore';

import Landing from './components/layout/landing';
import Tools from './components/layout/tools';
import Charts from './components/charts/chartsTab';
import Admin from './components/admin/admin';
import AddIndicator from './components/admin/addIndicator.jsx';
import ListIndicator from './components/admin/listIndicator.jsx';

const NoMatch = React.createClass({
  
  render() {
    return (
      <div>
        <h2>404</h2>
      </div>
    )
  }
})

const Root = React.createClass({
  render() {
    return (<div>{this.props.children}</div>)
  }
})



export default (
  <Route path="/" component={Root}>
  
    <Route path="/map" component={Regular}>
      <Route path="tools" component={Tools}/>
      <Route path="charts" component={Charts}/>
      <Route path="admin" component={Admin}>
        <Route path="/admin/add/indicator" component={AddIndicator}/>
        <Route path="/admin/list/indicator" component={ListIndicator}/>
      </Route>
      <IndexRoute component={Tools}/>
    </Route>
  
    <Route path="/map/:key" component={Restore}>
      <Route path="tools" component={Tools}/>
      <Route path="charts" component={Charts}/>
      <Route path="admin" component={Admin}>
        <Route path="/admin/add/indicator" component={AddIndicator}/>
        <Route path="/admin/list/indicator" component={ListIndicator}/>
      </Route>
    <IndexRoute component={Tools}/>
    </Route>
 
  <IndexRedirect from="" to="/map/tools"/>
  <Route path="*" component={NoMatch}/>
  </Route>
  );