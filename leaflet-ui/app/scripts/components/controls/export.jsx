import React from 'react';
import { connect } from 'react-redux'
import translate from '../../util/translate.js';
import {Button} from 'react-bootstrap';
import Connector from '../../connector/connector';
import { collectValues } from '../../util/filterUtil';
require('./export.scss');

const Export = React.createClass({

  getDownloadURL(type){
    const {filters} = this.props;
    let fc = collectValues(filters.filterMain);
    return Connector.getExportURL(type, fc);
  },

  render() {
    const {visible}=this.props;   
    return (
      <div>
        {visible?
          <div className="export-container">
            <div className="export-csv">
              <a target="_blank" href={this.getDownloadURL('csv')}>
                <Button className="btn btn-xs" bsStyle='success'>Download data in <strong>CSV</strong> format</Button>
              </a>
            </div>
            <div className="export-xls">
              <a target="_blank" href={this.getDownloadURL('xls')}>
                <Button className="btn btn-xs" bsStyle='success'>Download data in <strong>XLS</strong> format</Button>
              </a>
            </div>
          </div> 
        : null}
      </div>
    );
  }
});

const mapStateToProps = (state, props) => {
  return {
    lang: state.language.lan,
    filters: state.filters
  };
}

export default connect(mapStateToProps)(Export);
