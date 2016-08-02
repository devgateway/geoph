import React from 'react';
import { connect } from 'react-redux'
import translate from '../../util/translate.js';

require('./print.scss');

const Share =React.createClass({

  render() {
    const {visible}=this.props;

    return (
     <div>
        {visible?
          <div className="print-container">
            <h2>{translate('header.settings.print')}</h2>
              
          </div>

        : null}
      </div>
    );
  }
});

const mapDispatchToProps = (dispatch, ownProps) => {
  return {}
}

const mapStateToProps = (state, props) => {
  return {}
}

export default connect(mapStateToProps, mapDispatchToProps)(Share);
