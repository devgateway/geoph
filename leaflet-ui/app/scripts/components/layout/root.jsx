import React from 'react';
import { Link } from 'react-router';
import Header from './header.jsx';
import Footer from './footer.jsx';
import Panel  from './panel.jsx';

export default class App extends React.Component {

  constructor() {
    super();
  }

  render() {

    return (
      <div className="root">
        <Header/>
        <Menu/>
          {this.props.children}
        <Footer/>
        <Panel/>
      </div>
    )
  }
}
