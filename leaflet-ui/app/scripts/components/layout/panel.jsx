import React from 'react';
import { Link  } from 'react-router';

import {LayerControl} from '../controls/layer';

require("./pane.scss");

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
   
      <LayerControl/>
      </div>
      )
  }
}
