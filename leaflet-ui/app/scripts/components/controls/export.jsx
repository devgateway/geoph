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
  
  getDownloadURL(type) {
    const {filters, projectSearch} = this.props;
    let fc = collectValues(filters, projectSearch);
    
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
                    <a target="_blank" href={this.getDownloadURL('csv')}>
                      <Button className="btn btn-xs" bsStyle='success'>Download Left Map data in <strong>CSV</strong> format</Button>
                    </a>
                  </div>
                  <div className="export-xls compare">
                    <a target="_blank" href={this.getDownloadURL('xls')}>
                      <Button className="btn btn-xs" bsStyle='success'>Download Left Map data in <strong>XLS</strong> format</Button>
                    </a>
                  </div>
                  <div className="export-csv compare">
                    <a target="_blank" href={this.getDownloadURL('csv')}>
                      <Button className="btn btn-xs" bsStyle='success'>Download Right Map data in <strong>CSV</strong> format</Button>
                    </a>
                  </div>
                  <div className="export-xls compare">
                    <a target="_blank" href={this.getDownloadURL('xls')}>
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
    filters: state.filters.filterMain,
    projectSearch: state.projectSearch,
    isCompare
  };
};

export default connect(mapStateToProps)(Export);
