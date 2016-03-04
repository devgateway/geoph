import React from 'react';
import { Link  } from 'react-router';
import Map from 'esri/Map';
export default class Header extends React.Component {

  constructor() {
    super();
  }

  render() {
    return (
      <div className="root">
        Map View
      </div>
      )
  }
}
