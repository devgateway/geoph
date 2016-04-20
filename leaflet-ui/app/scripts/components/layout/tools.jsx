import React from 'react';
import { connect } from 'react-redux'
import {LayerControl} from '../controls/layer';

export default class Tools extends React.Component {

  constructor() {
    super();
  }


  render() {
  	
    return (
    	<div className="tools-view">
            <p>Explore this in-depth profile of Philippines to find out overall lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras eu elit sed urna tempus cursus sed in felis. In tempus, lectus sit amet pharetra tempor, risus diam viverra magna.</p>
            	
            <LayerControl/>
            
        </div>
      )
  }
}

 


