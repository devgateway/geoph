
import { connect } from 'react-redux'
import {loadProjects} from 'app/actions/map'
import MapComponent from 'app/components/map/map'
import * as Constants from 'app/constants/constants';

const stateToProps = (state, props) => {
  return state.map  
}


/*Connect map component to redux state*/
const Map=connect(stateToProps)(MapComponent);

export {Map};
 