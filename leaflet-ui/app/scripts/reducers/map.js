import {
    LOAD_PROJECT_GEOJSON_SUCCESS,
    LOAD_PEOJECT_GEOJSON_FAILED,
    
    LOAD_FUNDING_GEOJSON_SUCCESS,
    LOAD_FUNDING_GEOJSON_FAILED} from '../constants/constants';

const addOrUpdate=(newState,newlayer)=>{
 let index=newState.layers.findIndex(function(l){return l.name==newlayer.name});
 if (index > 0){
  newState.layers[index]=newlayer;
}else{
  newState.layers.push(newlayer);
}
return newState;
}


const map = (state = {layers: []}, action) => {
  let newState=Object.assign({}, state)
  switch (action.type) {
   case LOAD_PROJECT_GEOJSON_SUCCESS:
   let projectLayer=  {name:'project',data:action.data,autoZoom:true};
   return Object.assign(addOrUpdate(newState,projectLayer), {updated:new Date()});

   case LOAD_FUNDING_GEOJSON_SUCCESS:
   let layer=  {name:'funding',data:action.data,autoZoom:false};
   return Object.assign(addOrUpdate(newState,layer), {updated:new Date()});


   case LOAD_FUNDING_GEOJSON_FAILED:
   case LOAD_PEOJECT_GEOJSON_FAILED:
   return null;

   default:
   return state
 }
}


export default map;

