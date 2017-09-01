import React from 'react';
import { connect } from 'react-redux'
import { LayerControl } from '../controls/layer';
import ExpandableControl from '../controls/expandableControl';
import ProjectFilter from '../filter/projectFilter';
import SavedMaps from '../map/saved/savedMaps';
import translate from '../../util/translate.js';

require('./tools.scss');

class Tools extends React.Component {
  render() {
    return (
      <div className="tools-view">
        <ExpandableControl title={translate('toolview.projectsearch.title')} tooltipText="help.toolview.projectsearch">
          <ProjectFilter/>
        </ExpandableControl>
        
        <ExpandableControl title={translate('toolview.layers.title')} tooltipText="help.toolview.layerscontrol"
                           defaultExpanded={true}>
          <LayerControl/>
        </ExpandableControl>
        
        <ExpandableControl title={translate('toolview.savedmaps.title')} tooltipText="help.toolview.savedmapscontrol">
          <SavedMaps/>
        </ExpandableControl>
      </div>
    )
  }
}

const stateToProps = (state, props) => {
  return {
    language: state.language
  };
};

export default connect(stateToProps)(Tools);

