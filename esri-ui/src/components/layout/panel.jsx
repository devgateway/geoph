import React from 'react';
import { Link  } from 'react-router';
import FilterPopup from 'app/components/filter/filterPopup'
import {LayerControl} from 'app/components/controls/layer';
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
