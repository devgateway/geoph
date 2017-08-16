import * as Constants from '../constants/constants';
import Connector from '../connector/connector';
import {collectValuesToSave}  from '../util/saveUtil';
import * as HtmlUtil from '../util/htmlUtil';
import translate from '../util/translate';

export const capture=()=>{
  const mapName = translate('header.mapForPrint');
  const {outerHTML,clientWidth,clientHeight} = HtmlUtil.getMapElementProperties();
  
  return (dispatch, getState) =>{
    const data = collectValuesToSave(getState());
    dispatch(export2Pdf(outerHTML,clientWidth,clientHeight, mapName, data));
  }
};

const export2Pdf = (html,width,height,name,data) => {
  return (dispatch, getState) =>{
    
    dispatch({type: Constants.CAPTURE_START});
    
    Connector.export2Pdf({html,width,height,name,data})
      .then( data=>{dispatch({type: Constants.CAPTURE_OK,data})})
      .catch(data=>{dispatch({type: Constants.CAPTURE_FAILED,data})})
  }
};
