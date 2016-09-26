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
import Export from '../controls/export'
import Print from '../controls/print'
import ProjectPage from '../project/projectPage'
import {Tooltip} from 'react-bootstrap';
import Basemap from '../controls/baseMap.jsx'
import SaveMap from '../save-restore/save'
import {Message} from '../lan/'
import {showSaveMap} from '../../actions/saveAndRestoreMap';
import translate from '../../util/translate';
import Disclaimer from './disclaimer.jsx';

require("./root.scss");

const items=
[
{
  id:'filters',
  key:'filters',
  label:'Filters',
  className:'filters',
  disableOnClickOutside:true,
  tooltip: <Tooltip id="help.header.filter">Click here to apply filters to the data in the map and charts</Tooltip>,
  //tooltip: <Tooltip id="help.header.filter">{translate('help.header.filter')}</Tooltip>,
  children:FilterPopup,

},
{
  id:'settings',
  key:'settings',
  label:'Settings',
  children:Settings,
  tooltip: <Tooltip id="help.header.filter">Click here to select the funding type</Tooltip>,
  //tooltip: <Tooltip id="help.header.settings">{translate('help.header.settings')}</Tooltip>,
  className:'settings'
},

{
  id:'basemaps',
  key:'basemaps',
  label:'Base Maps',
  children:Basemap,
  tooltip: <Tooltip id="help.header.basemap">Click here to select the basemap</Tooltip>,
  //tooltip: <Tooltip id="help.header.basemap">{translate('help.header.basemap')}</Tooltip>,
  className:'basemaps'
},

{
  id:'save',
  key:'save',
  label:'Save',
  children:SaveMap,
  tooltip: <Tooltip id="help.header.save">Click here to save this map</Tooltip>,
  //tooltip: <Tooltip id="help.header.save">{translate('help.header.save')}</Tooltip>,
  className:'mini save',
  secure:true
},
{
  id:'share',
  key:'share',
  label:'Share',
  tooltip: <Tooltip id="help.header.share">Click here to share a link to this dashboard, including applied filters</Tooltip>,
  //tooltip: <Tooltip id="help.header.share">{translate('help.header.share')}</Tooltip>,
  children:Share,
  className:'mini share'
},
{
  id:'print',
  key:'print',
  label:'Print',
  children:Print,
  tooltip: <Tooltip id="help.header.print">Click here to create a PDF image of this map</Tooltip>,
  //tooltip: <Tooltip id="help.header.print">{translate('help.header.print')}</Tooltip>,
  className:'mini print'
},
{
  id:'export',
  key:'export',
  label:'Export',
  children:Export,
  tooltip: <Tooltip id="help.header.export">Click here to export project locations</Tooltip>,
  //tooltip: <Tooltip id="help.header.export">{translate('help.header.export')}</Tooltip>,
  className:'mini export'
}

]

export default class DefaultLayout extends React.Component {

  constructor() {
    super();
  }

  render() {
    const {title} = this.props;
    return (
      <div className="root">
        <Disclaimer/>
        <ProjectPage/>
        <Header>
          <Menu title={title} items={items}/>
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
