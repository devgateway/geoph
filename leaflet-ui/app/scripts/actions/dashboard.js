import * as Constants from '../constants/constants';
import Connector from '../connector/connector';
import {applyFilter, loadAllFilterLists} from './filters';
import {toggleVisibility} from './map';
import {getList} from './indicators';
import {collectValuesToSave}  from '../util/saveUtil';
import * as HtmlUtil from '../util/htmlUtil';

export const getMapList =()=>{
	 return (dispatch, getState) =>{
	 	 Connector.getMapList().then((data)=>dispatch( {type: Constants.REQUEST_MAP_LIST_OK,data}));
	 }
}