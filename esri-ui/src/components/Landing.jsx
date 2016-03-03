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
       <li><Link to="map2d">2d Map </Link></li> 
        <li><Link to="map3d">3d Map </Link> </li>
      </ul>
      </div>
      )
  }
}
