import React from 'react';
import ReactDOM from 'react-dom';
import * as Intro from 'intro.js';
import Help from './Help.jsx';
import translate from '../../util/translate';

export default class ProjectSearchHelp extends Help{
 
  help() {
    
    this.options=[
      {
        element:this.node().querySelector('#ps-selectall'),
        intro: translate('help.projectsearch.selectall'),
        position: 'left'
      },
      {
        element:this.node().querySelector('#ps-selectedresults'),
        intro: translate('help.projectsearch.selectedresults'),
        position: 'left'
      },
      {
        element:this.node().querySelector('#ps-searchresults'),
        intro: translate('help.projectsearch.searchresults'),
        position: 'left'
      },
      {
        element:this.node().querySelector('#ps-apply'),
        intro: translate('help.projectsearch.apply'),
        position: 'left'
      },
      {
        element:this.node().querySelector('#ps-clearall'),
        intro: translate('help.projectsearch.clearall'),
        position: 'left'
      }
    ];
    this.show();
   }
}