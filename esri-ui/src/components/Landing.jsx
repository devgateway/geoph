import React from 'react';
import { Link  } from 'react-router';

export default class Header extends React.Component {

  constructor() {
    super();
  }

  render() {
    return (
      <div className="root">
        Landing Page <Link to="map">Go to map </Link> 
      </div>
      )
  }
}
