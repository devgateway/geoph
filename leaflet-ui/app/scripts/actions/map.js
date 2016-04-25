import  {TOGGLE_LAYER,LAYER_LOAD_SUCCESS,LAYER_LOAD_FAILURE}  from '../constants/constants.js';

import {getLayerById} from '../util/layersUtil.js';

import Connector from '../connector/connector.js';



const loadLayerCompleted=(results)=>{
	
	return {type:LAYER_LOAD_SUCCESS,...results}
}

const loadLayerFailed=(type,error)=>{
	return {type:LAYER_LOAD_FAILURE,error}
}


const loadLayerIfNeeded=(dispatch,getState,layers)=>{
	layers.forEach((l)=>{
		if (l.get('layers')){ //it is a group 
			 loadLayerIfNeeded(dispatch,getState,l.get('layers'));
		}else if (l.get('visible') && !l.get('data')){
			const options={id:l.get('id'),ep:l.get('ep'),settings:l.get('settings').toObject()};
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
		loadLayerIfNeeded(dispatch,getState,getState().map.get('layers'));
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

