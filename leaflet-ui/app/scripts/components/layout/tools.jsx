import React from 'react';
import { connect } from 'react-redux'
import {LayerControl} from '../controls/layer';
import ExpandableControl from '../controls/expandableControl';
import ProjectFilter from '../filter/projectFilter';
import translate from '../../util/translate.js';
import { collectValues } from '../../util/filterUtil';
require('./tools.scss');

class Tools extends React.Component {

  constructor() {
    super();
  }

  componentDidMount(){
  }

  render() {
    return (
    	<div className="tools-view">
        <ExpandableControl title={translate('toolview.projectsearch.title')} iconClass="search-icon">
          <div><ProjectFilter/></div>
        </ExpandableControl>
        <ExpandableControl title={translate('toolview.layers.title')} defaultExpanded={true}  iconClass="layers-icon">
          <div><LayerControl/></div>
        </ExpandableControl>        
      </div>
    )
  }
}

const stateToProps = (state, props) => {
  return {
    language: state.language
  };
}

export default connect(stateToProps)(Tools);

