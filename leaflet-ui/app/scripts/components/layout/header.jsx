import React from 'react';
import { connect } from 'react-redux'
import {LangSwitcher} from '../lan/container'
import FilterPopup from '../filter/filterPopup'
import {showLanSelector,setLanguage} from '../../actions/index.js'
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
          <li onClick={this.props.onFliterClick}><div className="options-icons filters"></div>Filters (1/100)</li>
          <li onClick={this.props.onFliterClick}><div className="options-icons settings"></div>Settings </li>
          <li onClick={this.props.onFliterClick}><div className="options-icons basemaps"></div>Basemap</li>
          <LangSwitcher onClick={this.props.onLanClick} onChangeLanguage={this.props.onSetLanguage}/>

     </ul>
      </div>
      </div>
      )
  }
}


const mapDispatchToProps = (dispatch, ownProps) => {
 return {
  onLanClick:()=>{
    debugger;
    dispatch(showLanSelector())
  },
  onSetLanguage:()=>{
   dispatch(setLanguage())
  }
}
}



export default connect(null,mapDispatchToProps)(Header);;
