import React from 'react';
import { Link  } from 'react-router';
import LangSelector from '../containers/LangSelector.jsx';
import {LangMessage} from '../containers/LangMessage.jsx';

export default class Header extends React.Component {

  constructor() {
    super();
  }

  render() {
    console.log('Render Header')
    return (
      <div className="header">
        <LangSelector />
        <LangMessage k='general.hello' />
      </div>
      )
  }
}
