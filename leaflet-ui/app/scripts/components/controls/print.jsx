import React from 'react';
import {connect} from 'react-redux'
import {capture} from '../../actions/capture.js';
import Settings from '../../util/settings';

require('./print.scss');

const Share = React.createClass({
  render() {
    const {onCapure, loading, captures, visible} = this.props;
    return (
      
      <div>
        {visible ?
          <div className="print-container">
            <div className="new_loading">{loading ? <img src="../../../assets/png/loading.gif"/> :
              <div className="new">
                <div className="icon" onClick={onCapure}></div>
                <span>create</span></div>}</div>
            <span c className={captures.length > 0 ? "small" : "big"}>Click on create icon to generate a pdf of current map</span>
            {captures.map((file) => <a target="_blank" href={Settings.get('API', 'PDF_DOWNLOAD') + file.name}>
              <div className="icon"></div>
              <span>{"Pdf# " + (file.count)}</span></a>)}
          </div>
          : null}
      </div>
    );
  }
});

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onCapure: _ => dispatch(capture())
  }
};

const mapStateToProps = (state, props) => {
  return {...state.print.toJS()}
};

export default connect(mapStateToProps, mapDispatchToProps)(Share);
