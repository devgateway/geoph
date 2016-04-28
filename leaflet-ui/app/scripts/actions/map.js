import  {SET_BASEMAP, TOGGLE_LAYER,LAYER_LOAD_SUCCESS,LAYER_LOAD_FAILURE,SET_LAYER_SETTING }  from '../constants/constants.js';

import {getLayerById} from '../util/layersUtil.js';

import Connector from '../connector/connector.js';



const loadLayerCompleted=(results)=>{
	
	return {type:LAYER_LOAD_SUCCESS,...results}
}

const loadLayerFailed=(type,error)=>{
	return {type:LAYER_LOAD_FAILURE,error}
}

export const applyFiltersToLayers=(filters)=>{
	return (dispatch, getState) => {		
		loadLayerTree(dispatch,getState,getState().map.get('layers'), filters, true);
	}	
}

const loadLayerTree=(dispatch,getState,layers,filters,force)=>{
	layers.forEach((l)=>{
		if (l.get('layers')){ //it is a group 
			loadLayerTree(dispatch,getState,l.get('layers'),filters,force);
		}else if (l.get('visible') && (!l.get('data')||force)){
			const options={id:l.get('id'),ep:l.get('ep'),settings:l.get('settings').toObject(), filters: filters};
			dispatch(loadLayer(options,getState));
		}
	})
	
}


const loadLayerById=(dispatch,getState,layers,filters,id)=>{
	layers.forEach((l)=>{
		if (l.get('layers')){ //it is a group 
			loadLayerTree(dispatch,getState,l.get('layers'),filters,id);
		}else if (l.get('visible')  && l.get('id')==id){
			const options={id:l.get('id'),ep:l.get('ep'),settings:l.get('settings').toObject(), filters: filters};
			dispatch(loadLayer(options,getState));
		}
	})
}


export const toggleVisibility=(id,visible,params)=>{
	return (dispatch, getState) => {
		dispatch({
			type: TOGGLE_LAYER,
			id
		});
		loadLayerById(dispatch,getState,getState().map.get('layers'),{},id);
	}
}




export const setSetting=(id,name,value)=>{
	//TODO:reload layer if setting is quality or level
	return (dispatch, getState) => {
		dispatch({
			type: SET_LAYER_SETTING,
			id,
			name,
			value
		});
		loadLayerTree(dispatch,getState,getState().map.get('layers'),{},true);
	}


}


/*Get data of an specif layer passing layer options and getstate in order to take current filters*/
const loadLayer=(options,getState)=>{
	//TODO:take filtes
	return (dispatch, getState) =>{
		
		Connector.loadLayerByOptions(options).then(
			(results)=>{
				dispatch(loadLayerCompleted(results))
			}).catch((err)=>{ 
				console.error(err);
				dispatch(loadLayerFailed(err));
			});
		} 
}

export const setBaseMap=(basemap)=>{
	return {
	    type: SET_BASEMAP,
	    basemap: basemap
	}
}
