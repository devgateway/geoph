import React from 'react';
import { Link } from 'react-router';
import Header from './header.jsx';
import Footer from './footer.jsx';
import Panel  from './panel.jsx';
import Landing  from './landing.jsx';
require("./root.scss");
/*        <Landing/>            
        <Panel>
            {this.props.children}        
        </Panel>
        <Footer/>*/
export default class DefaultLayout extends React.Component {

  constructor() {
    super();
  }


  render() {

    return (
      <div className="root">
      <Header/>

      </div>
    )
  }
}
