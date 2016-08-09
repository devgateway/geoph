import * as Constants from '../constants/constants';
import Settings from '../util/settings';
import Connector from '../connector/connector';
<<<<<<< .merge_file_a99768
import * as HtmlUtil from '../util/htmlUtil';

=======
import {collectValuesToSave}  from '../util/saveUtil';
>>>>>>> .merge_file_a00552




export const capture=()=>{
<<<<<<< .merge_file_a99768
    return (dispatch, getState) =>{
        const {outerHTML,clientWidth,clientHeight} = HtmlUtil.getMapElementProperties();
        dispatch(export2Pdf(outerHTML,clientWidth,clientHeight));
=======
  const mapName = 'Map generated from print';
  const element = document.getElementsByClassName("map")[0];  
  const {outerHTML,clientWidth,clientHeight,offsetWidth,offsetHeight }=element;
    return (dispatch, getState) =>{
      const data = collectValuesToSave(getState());
      dispatch(export2Pdf(outerHTML,clientWidth,clientHeight, mapName, data))
>>>>>>> .merge_file_a00552
   }

}

 const export2Pdf = (html,width,height,name,data) => {
  return (dispatch, getState) =>{

     dispatch({type: Constants.CAPTURE_START});
    
    Connector.export2Pdf({html,width,height,name,data})
    .then( data=>{dispatch({type: Constants.CAPTURE_OK,data})})
    .catch(data=>{dispatch({type: Constants.CAPTURE_FAILED,data})})
    
  }

}


