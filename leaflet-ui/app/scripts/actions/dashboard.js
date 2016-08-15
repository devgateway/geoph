import * as Constants from '../constants/constants';
import Connector from '../connector/connector';
import {applyFilter, loadAllFilterLists} from './filters';
import {toggleVisibility} from './map';
import {getList} from './indicators';
import {collectValuesToSave}  from '../util/saveUtil';
import * as HtmlUtil from '../util/htmlUtil';


const redirect=(url,messages,errors,httpError)=>{
 return {
  type:"REDIRECT",
  transition:{
    pathname: url,
    state: {messages,errors,httpError}
  }
};
}


export const getMapList =()=>{
	 return (dispatch, getState) =>{
	 	 Connector.getMapList({type:"save"}).then((data)=>dispatch( {type: Constants.REQUEST_MAP_LIST_OK,data}));
	 }
}


export const edit =(key)=>{

	 return (dispatch, getState) =>{
	 	   dispatch(redirect(`/map/${key}`));
	 }
}


export const remove=(key)=>{
 return (dispatch, getState) =>{
  Connector.removeDashboard(key).then((data)=>{
     dispatch(getMapList());
     dispatch(redirect('/dashboard',[`Dashboard was removed`]));

 }).catch((httpError)=>{
    dispatch(redirect('/dashboard',[],[],httpError));
});
}
}
