import * as Constants from '../constants/constants';
import Settings from '../util/settings';
import Connector from '../connector/connector';



export const login = (options,getState) => {
  //TODO:take filtes
  return (dispatch, getState) =>{
    Connector.login(options).then((results)=>{
        dispatch(loginSuccess(results));
      }).catch((err)=>{ 
       dispatch(loginFailed(err));
      });
    } 
}



const loginSuccess=(info)=>{
  return {type:Constants.LOGIN_SUCCESS,info}
}

const loginFailed=(error)=>{
  return {type:Constants.LOGIN_FAILURE,error}
}


