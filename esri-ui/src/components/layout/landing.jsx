import React from 'react';
import { Link  } from 'react-router';
import {Map} from 'app/components/map/index';

export default class Landing extends React.Component {

  constructor() {
    super();
  }

  render() {
    return (<div className="landing">
              <div className="main">
                <Map/>
              </div>
                <div className="panel"></div>
              </div>
          )
    }
}
