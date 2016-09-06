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



export const getList=()=>{
 return (dispatch, getState) =>{
  Connector.getIndicatorList().then((data)=>{
    dispatch(makeAction(Constants.INDICATOR_LIST_LOADED,{data}));
  }).catch((error)=>{
    dispatch(makeAction(Constants.INDICATOR_FAILED,{error}));
  });
}
}

export const deleteIndicator=(indicator)=>{
 return (dispatch, getState) =>{
  Connector.removeIndicator(indicator.id).then((data)=>{
     dispatch(getList());
     dispatch(redirect('/admin/list/indicator',[`Indicator ${indicator.name} was removed`]));

 }).catch((httpError)=>{
    dispatch(redirect('/admin/list/indicator',[],[],httpError));
});
}
}



const makeAction=(name,data)=>{
 return {type:name,...data} 
}

const redirect=(url,messages,errors,httpError)=>{
  
 return {
  type:"REDIRECT",
  transition:{
    pathname: url,
    state: {messages,errors,httpError}
  }
};
}

export const upload=(options)=>{
  return (dispatch, getState) =>{
    

    Connector.uploadIndicator(getState().indicators.toJS()).then((data)=>{
     dispatch(uploadOK(data));
     dispatch(getList());
   }).catch((httpError)=>{
       dispatch(makeAction(Constants.INDICATOR_UPLOAD_FAILURE,{httpError:httpError}));
   });
 }

}


const uploadOK=(data)=>{
  const {name,id}=data;
  const url= '/admin/list/indicator';
  const {errors}=data;
  return redirect(url,[`Indicator "${name}" was added`],errors);

}

export const downloadTemplate=(level)=>{
  return (dispatch, getState) =>{
    Connector.uploadIndicator(getState().indicators.toJS()).then((data)=>{
     dispatch(uploadOK(data));
     dispatch(getList());
   }).catch((httpError)=>{
       dispatch(makeAction(Constants.INDICATOR_UPLOAD_FAILURE,{httpError:httpError}));
   });
 }

}