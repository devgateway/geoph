import React from 'react';
import { Link  } from 'react-router';

export default class Header extends React.Component {

  constructor() {
    super();
  }

  levelChanged(evt){
      alert(evt.target.value);
  }

  render() {
    return (
      <div className="panel">
      
      </div>
      )
  }
}
