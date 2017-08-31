import React from 'react';
import { connect } from 'react-redux';

class SavedMaps extends React.Component {
  
  render() {
    return (<div className="saved-maps">
        Test
      </div>
    )
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
  
  }
};

const stateToProps = (state, props) => {
  return {
  }
};

export default connect(stateToProps, mapDispatchToProps)(SavedMaps);
