import React from 'react';
import { Link  } from 'react-router';
import Map from '../map/map.jsx';
require("./landing.scss");

export default class Landing extends React.Component {

  constructor() {
    super();
  }

  render() {
    return (<div className="landing">
              <div className="main">
                 <Map/>
              </div>
                
              </div>
          )
    }
}
