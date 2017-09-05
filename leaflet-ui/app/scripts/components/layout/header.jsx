import React from 'react';
import {LangSwitcher} from '../lan/'

require('./header.scss');

export default class HeaderComponent extends React.Component {
  render() {
    return (
      <div className="header">
        <div className="heading">
          <div className="language-selector"><LangSwitcher/></div>
        </div>
        
        {this.props.children}
      
      </div>
    )
  }
}

