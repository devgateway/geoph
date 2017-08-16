import * as Constants from '../constants/constants';
import Connector from '../connector/connector';

const redirect=(url,messages,errors,httpError)=>{
  return {
    type:"REDIRECT",
    transition:{
      pathname: url,
      state: {messages,errors,httpError}
    }
  };
}

export const getMapList =(params)=>{
  return (dispatch, getState) =>{
    Connector.getMapList(params).then((data)=>{
      dispatch( {type: Constants.REQUEST_MAP_LIST_OK,data})
    });
  }
}

export const edit =(key)=>{
  return (dispatch, getState) =>{
    dispatch(redirect(`/map/${key}`));
  }
}

export const remove=(key)=>{
  return (dispatch, getState) =>{
    Connector.removeDashboard(key).then((data)=>{
      dispatch({type: Constants.REQUEST_DELETE_MAP_OK, key});
      dispatch(redirect('/dashboard',[`Dashboard was removed`]));
    }).catch((httpError)=>{
      dispatch(redirect('/dashboard',[],[],httpError));
    });
  }
}
