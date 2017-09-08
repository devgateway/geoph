import React from 'react';
import { connect } from 'react-redux'
import { LayerControl } from '../controls/layer';
import ExpandableControl from '../controls/expandableControl';
import ProjectFilter from '../filter/projectFilter';
import SavedMaps from '../map/saved/savedMaps';
import translate from '../../util/translate.js';
import { getMapList } from '../../actions/dashboard';

require('./tools.scss');

class Tools extends React.Component {
  static propTypes = {
    onGetList:        React.PropTypes.func.isRequired
  };
  
  componentWillMount() {
    const {savedMaps, onGetList} = this.props;
    
    // load the saved maps only if we didn't fetched them yet
    if (savedMaps.length === 0) {
      onGetList();
    }
  }
  
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

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onGetList: (params = {'type': 'all'}) => {
      dispatch(getMapList(params));
    }
  }
};

const stateToProps = (state, props) => {
  return {
    language:   state.language,
    savedMaps:  state.dashboard.toJS().results
  };
};

export default connect(stateToProps, mapDispatchToProps)(Tools);
