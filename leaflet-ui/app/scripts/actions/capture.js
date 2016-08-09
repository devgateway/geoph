import * as Constants from '../constants/constants';
import Settings from '../util/settings';
import Connector from '../connector/connector';
import {collectValuesToSave}  from '../util/saveUtil';
import * as HtmlUtil from '../util/htmlUtil';




export const capture=()=>{
  const mapName = 'Map generated from print';
  const element = document.getElementsByClassName("map")[0];  
  const {outerHTML,clientWidth,clientHeight} = HtmlUtil.getMapElementProperties();
  const data = collectValuesToSave(getState());
  return (dispatch, getState) =>{
    dispatch(export2Pdf(outerHTML,clientWidth,clientHeight, mapName, data));
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


