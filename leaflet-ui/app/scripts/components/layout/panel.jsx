import React from 'react';
import { Link  } from 'react-router';
import FilterPopup from '../filter/filterPopup'
import {LayerControl} from '../controls/layer';
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
      <FilterPopup/>

      <LayerControl/>
      </div>
      )
  }
}
