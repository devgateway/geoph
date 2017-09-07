import React from 'react';
import { connect } from 'react-redux'
import { Button } from 'react-bootstrap';
import Connector from '../../connector/connector';
import { collectValues } from '../../util/filterUtil';

require('./export.scss');

class Export extends React.Component {
  static propTypes = {
    isCompare:   React.PropTypes.bool,
  };
  
  getDownloadURL(type, options) {
    const { isCompare, filters, projectSearch, filtersCompare, projectSearchCompare } = this.props;
    let fc;
    
    if (!isCompare) {
      fc = collectValues(filters, projectSearch);
    } else {
      if (options !== undefined) {
        // only export the main map
        if (options.right === true) {
          fc = collectValues(filters, projectSearch);
        }
        // only export the comparison map
        if (options.left === true) {
          fc = collectValues(filtersCompare, projectSearchCompare);
        }
      }
    }
    
    return Connector.getExportURL(type, fc);
  };
  
  render() {
    const { visible, isCompare } = this.props;
    
    return (
      <div>
        {visible ?
          <div className="export-container">
            {
              isCompare !== true
                ? <div>
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
                
                : <div>
                  <div className="export-csv compare">
                    <a target="_blank" href={this.getDownloadURL('csv', {left: true})}>
                      <Button className="btn btn-xs" bsStyle='success'>Download Left Map data in <strong>CSV</strong> format</Button>
                    </a>
                  </div>
                  <div className="export-xls compare">
                    <a target="_blank" href={this.getDownloadURL('xls', {left: true})}>
                      <Button className="btn btn-xs" bsStyle='success'>Download Left Map data in <strong>XLS</strong> format</Button>
                    </a>
                  </div>
                  
                  <div className="export-csv compare">
                    <a target="_blank" href={this.getDownloadURL('csv', {right: true})}>
                      <Button className="btn btn-xs" bsStyle='success'>Download Right Map data in <strong>CSV</strong> format</Button>
                    </a>
                  </div>
                  <div className="export-xls compare">
                    <a target="_blank" href={this.getDownloadURL('xls', {right: true})}>
                      <Button className="btn btn-xs" bsStyle='success'>Download Right Map data in <strong>XLS</strong> format</Button>
                    </a>
                  </div>
                </div>
            }
          </div>
          : null}
      </div>
    );
  }
}

const mapStateToProps = (state, props) => {
  const isCompare = state.compare.size !== 0;
  return {
    lang: state.language.lan,
    
    // filters and project search for the main map
    filters: state.filters.filterMain,
    projectSearch: state.projectSearch,
    
    // filters and project search for the compare map
    filtersCompare: state.compare.get("filters") !== undefined ? state.compare.get("filters").filterMain : undefined,
    projectSearchCompare: state.compare.get("projectSearch"),
    
    isCompare
  };
};

export default connect(mapStateToProps)(Export);
