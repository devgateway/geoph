import * as Constants from '../constants/constants';
import Connector from '../connector/connector';

export const getGeophotosList=()=>{
  return (dispatch, getState) =>{
    Connector.getGeophotosList().then((data)=>{
      dispatch(makeAction(Constants.GEOPHOTOS_LIST_LOADED,{data}));
    }).catch((error)=>{
      dispatch(makeAction(Constants.GEOPHOTOS_FAILED,{error}));
    });
  }
}

const makeAction=(name,data)=>{
  return {type:name,...data}
}