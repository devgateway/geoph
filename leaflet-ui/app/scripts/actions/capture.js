import * as Constants from '../constants/constants';
import Settings from '../util/settings';
import Connector from '../connector/connector';





export const capture=()=>{
  const html=document.getElementsByClassName("map")[0].outerHTML;
   return (dispatch, getState) =>{
      dispatch(export2Pdf(html))
   }

}

 const export2Pdf = (html) => {
  return (dispatch, getState) =>{
    Connector.export2Pdf({html}).then((data)=>{
        debugger;
    }).catch((results)=>{
        dispatch(saveError(results));
    });
  }

}


