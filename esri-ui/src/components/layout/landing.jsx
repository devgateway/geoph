import React from 'react';
import { Link  } from 'react-router';
import {Message} from 'app/components/lan/LanContainer';
import Map2d from 'app/components/map2d'
export default class Landing extends React.Component {

  constructor() {
    super();
  }

  render() {
    return (
      <div className="landing">
      <div className="map">
       <Map2d/>
      </div>
      <div className="panel">
      </div>
      </div>
      )
  }
}
