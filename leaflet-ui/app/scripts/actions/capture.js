import * as Constants from '../constants/constants';
import Settings from '../util/settings';
import Connector from '../connector/connector';





export const capture=()=>{
  const element=document.getElementsByClassName("map")[0]
  const {outerHTML,clientWidth,clientHeight,offsetWidth,offsetHeight }=element;
  debugger;

   return (dispatch, getState) =>{
      dispatch(export2Pdf(outerHTML,clientWidth,clientHeight))
   }

}

 const export2Pdf = (html,width,height) => {
  return (dispatch, getState) =>{
    Connector.export2Pdf({html,width,height}).then((data)=>{
        debugger;
    }).catch((results)=>{
        dispatch(saveError(results));
    });
  }

}


