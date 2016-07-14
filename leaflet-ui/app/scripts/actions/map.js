import  {SET_BASEMAP, TOGGLE_LAYER,LAYER_LOAD_SUCCESS,LAYER_LOAD_FAILURE,SET_LAYER_SETTING,CHANGE_MAP_BOUNDS }  from '../constants/constants.js';
import Connector from '../connector/connector.js';
import {getPath,getDefaults} from '../util/layersUtil.js';
import {collectValues} from '../util/filterUtil';

const getFilters=(getState)=>{
	const filters = getState().filters.filterMain;
	const projectSearch= getState().projectSearch;
	return collectValues(filters,projectSearch);
}


const loadLayerCompleted=(results)=>{
	return {type:LAYER_LOAD_SUCCESS,...results}
}

const loadLayerFailed=(type,error)=>{
	return {type:LAYER_LOAD_FAILURE,error}
}

export const loadDefaultLayer=()=>{
	return (dispatch, getState) => {		
		getDefaults(getState().map.get('layers')).forEach(l=>{
			dispatch(toggleVisibility(l.get("id")));
		});
	}
}

export const applyFiltersToLayers=(filters)=>{
	return (dispatch, getState) => {		
		getDefaults(getState().map.get('layers')).forEach(l=>{
			loadLayerById(dispatch,getState,l.get("id"));
		});
	}	
}

export const setSetting=(id, name, value)=>{
	return (dispatch, getState) => {
		dispatch( {
			type: SET_LAYER_SETTING,
			id,
			name,
			value
		});
		dispatch(loadLayerById(dispatch, getState,id));
	}
}

export const toggleVisibility=(id,visibility)=>{
	return (dispatch, getState) => {

		dispatch({type: TOGGLE_LAYER,visible:visibility,id});
		if (!visibility){
			loadLayerById(dispatch, getState, id);
		}
	}
}

export const updateBounds=(newBounds)=>{
	return (dispatch, getState) => {
		dispatch({
			type: CHANGE_MAP_BOUNDS,
			bounds:newBounds
		  }
		);		
	}
}


const loadLayerById=(dispatch, getState, id)=>{
	const layer=getState().map.getIn(getPath(id));
	const options={
		id:layer.get('id'),
		indicator_id:layer.get("indicator_id"), 
		ep:layer.get('ep'),settings:layer.get('settings').toObject(), 
		filters: getFilters(getState)
	};

	dispatch(loadLayer(options, getState));
}
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
