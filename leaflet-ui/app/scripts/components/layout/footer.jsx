import React from 'react';
import { Link  } from 'react-router';

require('./footer.scss');

export default class Header extends React.Component {

  constructor() {
    super();
  }



  render() {
    return (
      <div className="footer">
        <p>The National Economic and Development Authority  No. 12 St. Jose Maria Escriva Drive, Ortigas Center, Pasig City  Trunkline: (+632) 631.0945 - 56</p>
        <Link to="about">About</Link>
        <div className="logo"/>
      </div>
      )
  }
}
