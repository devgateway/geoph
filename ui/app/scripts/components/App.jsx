import React from 'react';
import { Link  } from 'react-router';
import Header  from './Header.jsx';
import Footer  from './Footer.jsx';
import Landing  from './Landing';
import FilterListContainer from '../containers/FilterListContainer.jsx';

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
