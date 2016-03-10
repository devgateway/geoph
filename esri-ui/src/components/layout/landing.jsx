import React from 'react';
import { Link  } from 'react-router';
import {Message} from 'app/components/lan/container';

export default class Landing extends React.Component {

  constructor() {
    super();
  }

  render() {
    return (
      <div className="landing">
      <div className="map">
        <Message k='welcome'>Welcome to this application</Message>
      </div>
      <div className="panel">
      </div>
      </div>
      )
  }
}
