import * as Constants from '../constants/constants';
import Connector from '../connector/connector';
import {collectValuesToSave} from '../util/saveUtil';
import { getMapElementProperties } from '../util/htmlUtil';
import translate from '../util/translate';

export const capture = (options) => {
  const mapName = translate('header.mapForPrint');
  const { outerHTML, clientWidth, clientHeight } = getMapElementProperties(options);
  
  return (dispatch, getState) => {
    const data = collectValuesToSave(getState(), options);
    dispatch(export2Pdf(outerHTML, clientWidth, clientHeight, mapName, data));
  }
};

const export2Pdf = (html, width, height, name, data) => {
  return (dispatch, getState) => {
    
    dispatch({type: Constants.CAPTURE_START});
    
    Connector.export2Pdf({html, width, height, name, data})
      .then(data => {
        dispatch({type: Constants.CAPTURE_OK, data})
      })
      .catch(data => {
        dispatch({type: Constants.CAPTURE_FAILED, data})
      })
  }
};
