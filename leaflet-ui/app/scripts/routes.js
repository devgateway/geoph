import React from 'react';
import { Route, IndexRoute ,IndexRedirect} from 'react-router';

/* containers */
import Regular from './components/layout/regular';
import Restore from './components/layout/restore';
import Tools from './components/layout/tools';
import Charts from './components/charts/chartsTab';
import Admin from './components/admin/admin';
import AddIndicator from './components/admin/addIndicator.jsx';
import ListIndicator from './components/admin/listIndicator.jsx';
import Printable from './components/print/printable.jsx';
import About from './components/layout/aboutPage';
import DashboardLayout from './components/layout/dashboardLayout.jsx';
import Dashboard from './components/dashboard/dashboard.jsx';

const NoMatch = React.createClass({
  render() {
    return (
      <div>
        <h2>404</h2>
      </div>
    )
  }
});

const Root = React.createClass({
  render() {
    return (<div>{this.props.children}</div>)
  }
});


export default (
  
  <Route path="/" component={Root}>
    
    <Route path="/" component={DashboardLayout}>
      <Route path="/dashboard" component={Dashboard}/>
      <Route path="/about" component={About}/>
      <Route path="/admin" component={Admin}>
        <Route path="/admin/add/indicator" component={AddIndicator}/>
        <Route path="/admin/list/indicator" component={ListIndicator}/>
      </Route>
      
      <IndexRoute component={Dashboard}/>
    </Route>
    
    <Route path="/print/:key" component={Printable}></Route>
    
    <Route path="/map" component={Regular}>
      <Route path="tools" component={Tools}/>
      <Route path="charts" component={Charts}/>
      
      <Route path="/map/admin" component={Admin}>
        <Route path="/map/admin/add/indicator" component={AddIndicator}/>
        <Route path="/map/admin/list/indicator" component={ListIndicator}/>
      </Route>
      
      <IndexRoute component={Tools}/>
    </Route>
    
    <Route path="/map/:key" component={Restore}>
      <Route path="tools" component={Tools}/>
      <Route path="charts" component={Charts}/>
      <IndexRoute component={Tools}/>
    </Route>
    
    <IndexRedirect from="" to="/map/tools"/>
    <Route path="*" component={NoMatch}/>
  </Route>
);