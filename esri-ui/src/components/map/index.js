
import { connect } from 'react-redux'
import {loadProjects} from 'app/actions/map'
import MapComponent from 'app/components/map/map'
import Constants from 'app/constants/constants';

const stateToProps = (state, props) => {
  
  return {
  	...state.map  
  }
}


const dispatchToProps = (dispatch, ownProps) => {
  return {
    onLoadProjects: (level) => {
      dispatch(loadProjects(level));
    },
  }
}
/*Connect map component to redux state*/
const Map=connect(stateToProps,dispatchToProps)(MapComponent);

export {Map};
 