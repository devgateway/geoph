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

export const upload=(options)=>{
    debugger;
  return (dispatch, getState) =>{
    Connector.uploadIndicator(getState().indicators.toJS()).then((results)=>{
        debugger;
    }).catch((err)=>{
        debugger;
    });
  }

}
