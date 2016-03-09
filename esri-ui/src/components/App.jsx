import React from 'react';
import { Link  } from 'react-router';
import Header  from 'app/components/Header';
import Footer  from 'app/components/Footer';
import Landing  from 'app/components/Landing';
import FilterListContainer from 'app/containers/FilterListContainer';

export default class App extends React.Component {

  constructor() {
    super();
  }

  render() {
    console.log('Render App')
    return (
      <div className="app">
        <Header/>
          {this.props.children}
        <FilterListContainer type="s3" />
        <Footer/>
      </div>
    )
  }
}
