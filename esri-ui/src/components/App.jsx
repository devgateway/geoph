import React from 'react';
import { Link  } from 'react-router';
import Header  from 'app/components/Header.jsx';
import Footer  from 'app/components/Footer.jsx';
import Landing  from 'app/components/Landing';
import FilterListContainer from 'app/containers/FilterListContainer.jsx';

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
