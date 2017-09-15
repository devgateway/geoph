import React from 'react';
import { connect } from 'react-redux';
import Header from '../header.jsx';
import Menu from '../../menu/default.jsx';
import Footer from '../footer.jsx';
import FilterPopup from '../../filter/filters.jsx'
import Settings from '../../controls/settings'
import Share from '../../controls/share'
import Export from '../../controls/export'
import Print from '../../controls/print'
import ProjectPage from '../../project/projectPage'
import Basemap from '../../controls/baseMap.jsx'
import SaveMap from '../../save-restore/save'
import { showSaveMap } from '../../../actions/saveAndRestoreMap';
import translate from '../../../util/translate';
import Disclaimer from '../disclaimer.jsx';
import NoResultsPopup from '../../filter/noResultsPopup.jsx';

class DefaultMapLayout extends React.Component {
  
  static propTypes = {
    type:     React.PropTypes.string,
    title:    React.PropTypes.string,
    children: React.PropTypes.element
  };
  
  render () {
    const { type } = this.props;
    let title = this.props.title;
    
    if (type === "share") {
      title = translate('header.sharedtitle');
    }
    
    return (
      <div className="root">
        <Disclaimer/>
        <NoResultsPopup/>
        <ProjectPage/>
        <Header>
          <Menu title={title} items={items}/>
        </Header>
        
        {this.props.children}
        
        <Footer/>
      </div>
    )
  };
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
  }
};

const mapStateToProps = (state, props) => {
  return {
    title: state.saveMap.get("name"),
    type:  state.saveMap.get("type")
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(DefaultMapLayout);

const items = [
  {
    id: 'compare',
    key: 'compare',
    label: 'Compare Maps',
    tooltipKey: 'help.header.compare',
    className: 'compare'
  },
  {
    id: 'filters',
    key: 'filters',
    label: 'Filters',
    className: 'filters',
    disableOnClickOutside: true,
    tooltipKey: 'help.header.filter',
    children: FilterPopup,
    
  }, {
    id: 'settings',
    key: 'settings',
    label: 'Settings',
    children: Settings,
    tooltipKey: 'help.header.settings',
    className: 'settings'
  }, {
    id: 'basemaps',
    key: 'basemaps',
    label: 'Base Maps',
    children: Basemap,
    tooltipKey: 'help.header.basemap',
    className: 'basemaps'
  }, {
    id: 'save',
    key: 'save',
    label: 'Save',
    children: SaveMap,
    tooltipKey: 'help.header.save',
    className: 'mini save',
    secure: true
  }, {
    id: 'share',
    key: 'share',
    label: 'Share',
    tooltipKey: 'help.header.share',
    children: Share,
    className: 'mini share'
  }, {
    id: 'print',
    key: 'print',
    label: 'Print',
    children: Print,
    tooltipKey: 'help.header.print',
    className: 'mini print'
  }, {
    id: 'export',
    key: 'export',
    label: 'Export',
    children: Export,
    tooltipKey: 'help.header.export',
    className: 'mini export'
  }
];
