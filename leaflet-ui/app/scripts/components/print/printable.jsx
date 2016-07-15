import React from 'react';
import { Link  } from 'react-router';
import Map from '../map/map';
import translate from '../../util/translate';
import Filters from './printFilters';
require('./printable.scss');

export default class Printable extends React.Component {

  render() {
    return (
      <div className="printable">
        <div className="print-header">
          <h1>{translate("header.title")}</h1>
        </div>
        <div className="full-map">
          <Map/>
        </div>
        <Filters/>
      </div>
    )
  }
}
