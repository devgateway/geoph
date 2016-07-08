import  {SET_BASEMAP, TOGGLE_LAYER,LAYER_LOAD_SUCCESS,LAYER_LOAD_FAILURE,SET_LAYER_SETTING }  from '../constants/constants.js';
import Connector from '../connector/connector.js';
import {getPath} from '../util/layersUtil.js';


const loadLayerCompleted=(results)=>{
	return {type:LAYER_LOAD_SUCCESS,...results}
}

const loadLayerFailed=(type,error)=>{
	return {type:LAYER_LOAD_FAILURE,error}
}


export const applyFiltersToLayers=(filters)=>{
	return (dispatch, getState) => {		
		//loadLayerTree(dispatch, getState, getState().map.get('layers'), filters, true);
	}	
}

export const setSetting=(id, name, value, filters)=>{
	return (dispatch, getState) => {
		dispatch( {
			type: SET_LAYER_SETTING,
			id,
			name,
			value
		});
		dispatch(loadLayerById(dispatch, getState,id,filters));
	}
}

export const toggleVisibility=(id,visibility, filters)=>{
	debugger;
	return (dispatch, getState) => {
		dispatch({type: TOGGLE_LAYER,visible:visibility,id});
		if (!visibility){
			loadLayerById(dispatch, getState, id,filters);
		}
	}
}

export const loadDefaultLayers=(layers, filters)=>{
	return (dispatch, getState) => {
		toggleDefaultLayers(dispatch, layers, filters);
	}
}


const loadLayerById=(dispatch, getState, id,filters)=>{
	const layer=getState().map.getIn(getPath(id));
	const options={id:layer.get('id'), indicator_id:layer.get("indicator_id"), ep:layer.get('ep'),settings:layer.get('settings').toObject(), filters: filters};
		dispatch(loadLayer(options, getState));
	}


/*
const toggleDefaultLayers=(dispatch, layers, filters)=>{
	layers.map((l)=>{
		if (l.get('defaultLoaded')){
			dispatch(toggleVisibility(l.get('id'), true, filters));
		} else if (l.get('layers')){
			toggleDefaultLayers(dispatch, l.get('layers'), filters);
		}
	});
}
*/
/*Get data of an specif layer passing layer options and getstate in order to take current filters*/
const loadLayer=(options, getState)=>{
	
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
