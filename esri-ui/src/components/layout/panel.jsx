import React from 'react';
import { Link  } from 'react-router';
import FilterPopup from 'app/components/filter/filterPopup'

export default class Header extends React.Component {

  constructor() {
    super();
  }

<<<<<<< HEAD
  levelChanged(evt){
      alert(evt.target.value);
  }

  render() {
    return (
      <div className="panel">
      
=======
  render() {
    return (
      <div className="panel">
        <FilterPopup/>
>>>>>>> master
      </div>
      )
  }
}
