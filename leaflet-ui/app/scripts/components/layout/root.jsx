import React from 'react';
import { Link } from 'react-router';
import Header from './header.jsx';
import Footer from './footer.jsx';
import Panel  from './panel.jsx';
import Landing  from './landing.jsx';

require("./root.scss");

export default class App extends React.Component {

  constructor() {
    super();
  }

  render() {

    return (
      <div className="root">
        <Header/>
        <Landing/>            
        <Panel>
            {this.props.children}
        </Panel>
        <Footer/>
      
      </div>
    )
  }
}
