import React from 'react';
import { Link  } from 'react-router';
import Header  from 'app/components/layout/head';
import Footer  from 'app/components/layout/foot';

export default class App extends React.Component {

  constructor() {
    super();
  }

  render() {

    return (
      <div className="root">
        <Header/>
          {this.props.children}
        <Footer/>
      </div>
    )
  }
}
