import React from 'react';
import { Route, IndexRoute ,IndexRedirect} from 'react-router';

/* containers */
import DefaultMapLayout from './components/layout/map/defaultMapLayout';
import SingleMapViewLayout from './components/layout/map/singleMapViewLayout';
import ShareMapViewLayout from './components/layout/map/shareMapViewLayout';
import CompareMapViewLayout from './components/layout/map/compareMapViewLayout';
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
    
    <Route path="/" component={DefaultMapLayout}>
      <Route path="map" component={SingleMapViewLayout}>
        <Route path="tools" component={Tools}/>
        <Route path="charts" component={Charts}/>
        <IndexRoute component={Tools}/>
      </Route>
      
      <Route path="map/compare" component={CompareMapViewLayout}>
        <IndexRoute component={Tools}/>
      </Route>
      
      <Route path="map/:key" component={ShareMapViewLayout}>
        <Route path="tools" component={Tools}/>
        <Route path="charts" component={Charts}/>
        <IndexRoute component={Tools}/>
      </Route>
      
      <IndexRoute component={Tools}/>
    </Route>
    
    <IndexRedirect from="" to="/map/tools"/>
    <Route path="*" component={NoMatch}/>
  </Route>
);
