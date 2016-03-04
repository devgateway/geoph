import React from 'react';
import { Router, Route ,Redirect,IndexRoute ,hashHistory} from 'react-router';
import { render } from 'react/react-dom';
import Landing from 'app/components/Landing';
import Header from 'app/components/Header'
import Footer from 'app/components/Footer'
import Map from 'app/components/map';

/*Layout elements*/

/**
 * Root view
 */
 class App extends React.Component {
   render() {
    return (
      <div className="app">
       <Header/>
         {this.props.children}
       <Footer/>
      </div>
      )
  }
}


/*
Not found view
*/
class NoMatch extends React.Component{
  render(){
    return <h1>Not found</h1>
  }
}

 render((
      <Router history={hashHistory} >

        <Route path="/" component={App}>
          <IndexRoute component={Landing} />
            <Route path="map" component={Map}/>
            
        </Route>
      <Route path="*" component={NoMatch}/>

      </Router>
      ), document.getElementById('root'))

