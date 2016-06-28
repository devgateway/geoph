
import React from 'react';
import { connect } from 'react-redux'


 class Popup  extends React.Component {
  static propTypes = {
    name: React.PropTypes.string,
  };

  componentWillUnmount() {
      debugger;  
  }

  constructor(props) {
    super(props);
  }

  render() {

    if (!this.props.feature){return null};
    debugger;
    return (
      <div>HELLO {this.props.feature.properties.id} {this.props.state.language.lan}</div>
      );
  }
}




const mapDispatchToProps = (dispatch, ownProps) => {
  return {


  }
}

const mapStateToProps = (state, props) => {
  return {state}
 }

 export default connect(mapStateToProps, mapDispatchToProps)(Popup);;