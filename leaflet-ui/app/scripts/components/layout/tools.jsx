import React from 'react';
import { connect } from 'react-redux'
import {LayerControl} from '../controls/layer';
import ExpandableControl from '../controls/expandableControl';
import ProjectFilter from '../filter/projectFilter';

import { collectValues } from '../../util/filterUtil';
require('./tools.scss');

export default class Tools extends React.Component {

  constructor() {
    super();
  }

  componentDidMount(){
  }

  render() {
    return (
    	<div className="tools-view">
        <hr/>
        <ExpandableControl title="Project Search" iconClass="search-icon">
          <div><ProjectFilter/></div>
        </ExpandableControl>
        <ExpandableControl title="Adjust layers to see detailed data" defaultExpanded={true}  iconClass="layers-icon">
          <div><LayerControl/></div>
        </ExpandableControl>        
      </div>
    )
  }
}



