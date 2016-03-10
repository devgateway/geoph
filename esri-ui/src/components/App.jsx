import React from 'react';
import { Link  } from 'react-router';
import Header  from 'app/components/Header';
import Footer  from 'app/components/Footer';
import Landing  from 'app/components/Landing';

export default class App extends React.Component {

  constructor() {
    super();
  }

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
