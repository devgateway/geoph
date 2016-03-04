import React from 'react';
import { Link  } from 'react-router';

export default class Header extends React.Component {

  constructor() {
    super();
  }

  render() {
    return (
      <div className="root">
      <ul>
       <li><Link to="map">Go to map</Link></li> 
       </ul>
      </div>
      )
  }
}
