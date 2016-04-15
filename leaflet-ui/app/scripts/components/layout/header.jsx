import React from 'react';
import { connect } from 'react-redux'
import {LangSwitcher} from '../lan/'
import FilterPopup from '../filter/filterPopup'

require('./header.scss');
export default class Header extends React.Component {

  constructor() {
    super();
  }

  render() {
    return (
      <div className="header">
      <div className="heading">
      <h1>National Economic Development Authority (NEDA)</h1>
      </div>
      <div className="title">
      <h2>Philippines Closed / Terminated Project Data Map</h2>
      <ul className="options">
          <FilterPopup />
          <li onClick={this.props.onFliterClick}><div className="options-icons settings"></div>Settings </li>
          <li onClick={this.props.onFliterClick}><div className="options-icons basemaps"></div>Basemap</li>
          <LangSwitcher/>

     </ul>
      </div>
      </div>
      )
  }
}




export default Header;