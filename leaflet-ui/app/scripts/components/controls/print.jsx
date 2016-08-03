import React from 'react';
import { connect } from 'react-redux'
import translate from '../../util/translate.js';
import {capture}  from '../../actions/capture.js';

require('./print.scss');

const Share =React.createClass({

  componentDidMount() {
      debugger;  
  },

  componentWillReceiveProps(nextProps) {
     const {visible}=nextProps;
     if (visible){
        this.props.onCapure();
     }
  
  },

  render() {
     const {visible}=this.props;

    return (


     <div>
        {visible?
          <div className="print-container">
              <div className="loader">Loading...</div>
          </div>

        : null}
      </div>
    );
  }
});

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onCapure:_=>dispatch(capture())
  }
}

const mapStateToProps = (state, props) => {
  return {}
}

export default connect(mapStateToProps, mapDispatchToProps)(Share);
