import React from 'react';
import { connect } from 'react-redux'
import {LangSwitcher} from '../lan/'
import FilterPopup from '../filter/filterPopup'
import Settings from '../controls/settings'
import Basemap from '../map/baseMap/baseMap'
import SaveMap from '../save-restore/save'
import {Message} from '../lan/'

require('./header.scss');
export default class HeaderComponent extends React.Component {

  constructor() {
    super();
  }

  render() {
    return (
      <div className="header">
        <div className="heading">
          <h1><Message k={"header.title"}/></h1>
        </div>
        <div className="title">
          <h2>Philippines Closed / Terminated Project Data Map</h2>
          <ul className="options">
            <FilterPopup />
            <Settings />
            <Basemap />
            <LangSwitcher/>
            <SaveMap/>
          </ul>
        </div>
      </div>
    )
  }
}
