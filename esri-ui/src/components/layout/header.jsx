import React from 'react';
import { Link  } from 'react-router';
import {LangSwitcher} from 'app/components/lan/container'
import {FilterPopup} from 'app/components/filter/filterPopup'
export default class Header extends React.Component {

  constructor() {
    super();
  }

  render() {
    return (
      <div className="header">
        <LangSwitcher/>
        <FilterPopup/>
      </div>
      )
  }
}
