import React from 'react';
import { connect } from 'react-redux'
import {LayerControl} from '../controls/layer';
import ExpandableControl from '../controls/expandableControl';
import ProjectFilter from '../filter/projectFilter';

export default class Tools extends React.Component {

  constructor() {
    super();
  }


  render() {
  	
    return (
    	<div className="tools-view">
            <hr/>
            <ExpandableControl title="Project Search">
              <div><ProjectFilter/></div>
            </ExpandableControl>
            <LayerControl/>
            
        </div>
      )
  }
}

 


