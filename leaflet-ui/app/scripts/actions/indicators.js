import * as Constants from '../constants/constants';
import Settings from '../util/settings';
import Connector from '../connector/connector';


export const changeStep=(step)=>{
  return {type:Constants.CHANGE_STEP,step}
}

export const changeProperty=(property,value)=>{
  return {type:Constants.CHANGE_PROPERTY,property,value}
}

export const updateErrors=(errors)=>{
  return {type:Constants.UPDATE_ERRORS,errors}
}

export const uploadOK=()=>{
  return {type:Constants.INDICATOR_UPLOAD_SUCCESS}
}

export const uploadError=()=>{
  return {type:Constants.INDICATOR_UPLOAD_FAILURE}
}
export const upload=(options)=>{
    
  return (dispatch, getState) =>{
    Connector.uploadIndicator(getState().indicators.toJS()).then((results)=>{
        dispatch(uploadOK());
    }).catch((err)=>{
        
        dispatch(uploadError());
    });
  }

}
