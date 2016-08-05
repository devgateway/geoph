import * as Constants from '../constants/constants';
import Settings from '../util/settings';
import Connector from '../connector/connector';





export const capture=()=>{
  const element=document.getElementsByClassName("map")[0]
  const {outerHTML,clientWidth,clientHeight,offsetWidth,offsetHeight }=element;
    return (dispatch, getState) =>{
      dispatch(export2Pdf(outerHTML,clientWidth,clientHeight))
   }

}

 const export2Pdf = (html,width,height) => {
  return (dispatch, getState) =>{

     dispatch({type: Constants.CAPTURE_START});
    
    Connector.export2Pdf({html,width,height})
    .then( data=>{dispatch({type: Constants.CAPTURE_OK,data})})
    .catch(data=>{dispatch({type: Constants.CAPTURE_FAILED,data})})
    
  }

}


