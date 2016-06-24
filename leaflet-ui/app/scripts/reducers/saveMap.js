import * as Constants from '../constants/constants';

const saveMap = (state = {}, action) => {
  console.log("--- saveMap action ---" + action);
  switch (action.type) {
    case Constants.REQUEST_SAVE_MAP:
      /*let chartsObj = {lastUpdate: action.receivedAt};
      for(var key in action.data){
          let act = {type: action.type, data: action.data[key]};
          chartsObj[key] = chart(state[key], act);
      }
      return Object.assign({}, state, chartsObj);*/

      console.log("--- saveMap action2 ---" + action);
      return state
    default:
      return state
  }
}

export default saveMap