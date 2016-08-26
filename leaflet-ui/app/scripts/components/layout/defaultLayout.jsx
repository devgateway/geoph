import React from 'react';
import { Link } from 'react-router';
import Header from './header.jsx';
import Menu from '../menu/default.jsx';
import Footer from './footer.jsx';
import Panel  from './panel.jsx';
import Landing  from './landing.jsx';
import FilterPopup from '../filter/filters.jsx'
import Settings from '../controls/settings'
import Share from '../controls/share'
import Print from '../controls/print'
import ProjectPage from '../project/projectPage'

import Basemap from '../controls/baseMap.jsx'
import SaveMap from '../save-restore/save'
import {Message} from '../lan/'
import {showSaveMap} from '../../actions/saveAndRestoreMap';

require("./root.scss");

const items=
[
{
  id:'filters',
  key:'filters',
  label:'Filters',
  className:'filters',
  disableOnClickOutside:true,
  children:FilterPopup,

},
{
  id:'settings',
  key:'settings',
  label:'Settings',
  children:Settings,
  className:'settings'
},

{
  id:'basemaps',
  key:'basemaps',
  label:'Base Maps',
  children:Basemap,
  className:'basemaps'
},

{
  id:'save',
  key:'save',
  label:'Save',
  children:SaveMap,
  className:'mini save',
  secure:true
},
{
  id:'share',
  key:'share',
  label:'Share',
  children:Share,
  className:'mini share'
},
{
  id:'print',
  key:'print',
  label:'Print',
  children:Print,
  className:'mini print'
}
]

export default class DefaultLayout extends React.Component {

  constructor() {
    super();
  }

  render() {
    return (
      <div className="root">
        <ProjectPage/>
        <Header>
          <Menu items={items}/>
        </Header>
        <Landing/>
        <Panel showHelp={this.showHelp}>
            {this.props.children}
        </Panel>
        <Footer/>
      </div>
    )
  }
}
