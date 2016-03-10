import React from 'react';
import { Link  } from 'react-router';
import {LangSwitcher} from 'app/components/lan/LanContainer'
export default class Header extends React.Component {

  constructor() {
    super();
  }



  render() {
    return (
      <div className="header">
        <LangSwitcher/>
      </div>
      )
  }
}
