import * as Constants from 'app/constants/constants';
const map = (state = {
	layers:{
		projects:{
			'name':'Project Layers',
      'data':''
		}
	}
}, action) => {
console.log(state);
  switch (action.type) {
    case Constants.LOAD_PEOJECT_GEOJSON:
      debugger;
      return null;
    default:
      return state
  }
}



export default  map;