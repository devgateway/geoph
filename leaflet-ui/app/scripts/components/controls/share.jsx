import React from 'react';
import { connect } from 'react-redux'
import translate from '../../util/translate.js';

require('./share.scss');

const Share =React.createClass({

  render() {
    const {visible}=this.props;

    return (
     <div>
        {visible?
          <div className="share-container">
            <h2>{translate('header.settings.share')}</h2>
           
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
