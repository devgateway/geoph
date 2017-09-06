import React from 'react';
import { connect } from 'react-redux'
import { capture } from '../../actions/capture.js';
import Settings from '../../util/settings';

require('./print.scss');

class Share extends React.Component {
  static propTypes = {
    isCompare:   React.PropTypes.bool,
  };
  
  render() {
    const { onCapure, loading, captures, visible, isCompare } = this.props;
    
    return (
      <div>
        {visible ?
          <div className="print-container">
            <div className="new_loading">
              {
                loading === true
                  ? <img src="../../../assets/png/loading.gif"/>
                  : <div>
                    {
                      isCompare === true
                        ? <div className="new-compare">
                          <div className="new">
                            <div className="icon" onClick={() => onCapure({left: true})}></div>
                            <span>create left</span>
                          </div>
                          <div className="new">
                            <div className="icon" onClick={() => onCapure()}></div>
                            <span>create both</span>
                          </div>
                          <div className="new">
                            <div className="icon" onClick={() => onCapure({right: true})}></div>
                            <span>create right</span>
                          </div>
                        </div>
                        : <div className="new">
                          <div className="icon" onClick={() => onCapure()}></div>
                          <span>create</span>
                        </div>
                    }
                  </div>
              }
            </div>
            <span className={captures.length > 0 ? "small" : "big"}>Click on create icon to generate a pdf of current map</span>
            {
              captures.map(file => <a target="_blank" href={Settings.get('API', 'PDF_DOWNLOAD') + file.name}>
                <div className="icon"></div>
                <span>{"Pdf# " + (file.count)}</span>
              </a>)
            }
          </div>
          : null}
      </div>
    );
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onCapure: (options) => dispatch(capture(options))
  }
};

const mapStateToProps = (state, props) => {
  const isCompare = state.compare.size !== 0;
  return {...state.print.toJS(), isCompare }
};

export default connect(mapStateToProps, mapDispatchToProps)(Share);
