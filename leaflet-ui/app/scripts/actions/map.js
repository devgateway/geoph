import {REFRESH_LAYER, TOGGLE_LEGENDS_VIEW, SET_BASEMAP, TOGGLE_LAYER, LAYER_LOAD_SUCCESS, LAYER_LOAD_FAILURE, SET_LAYER_SETTING, CHANGE_MAP_BOUNDS, LOAD_LAYER_BY_ID}  from '../constants/constants.js';
import Connector from '../connector/connector.js';
import {getPath, getDefaults, getVisibles} from '../util/layersUtil.js';
import {collectValues} from '../util/filterUtil';

const getFilters=(getState)=>{
	const filters = getState().filters.filterMain;
	const projectSearch= getState().projectSearch;
	return collectValues(filters,projectSearch);
}


const loadLayerCompleted=(results, getState)=>{
	return {type:LAYER_LOAD_SUCCESS,...results, fundingType: getState().settings.fundingType}
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
		getVisibles(getState().map.get('layers')).forEach(l=>{
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
		if (name=='level' || name=='detail'){
			//reaload layer if level was changed
			dispatch(loadLayerById(dispatch, getState,id));
		}else{
			//regenerate layer if a setting was changed
			dispatch({type: REFRESH_LAYER,id,fundingType: getState().settings.fundingType});
		}
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
		geophotos_id:layer.get("geophotos_id"), 
		ep:layer.get('ep'),settings:layer.get('settings')?layer.get('settings').toObject():{}, 
		filters: getFilters(getState)
	};
	
	dispatch(loadLayer(options, getState));
	return {'type': LOAD_LAYER_BY_ID};
}
/*Get data of an specif layer passing layer options and getstate in order to take current filters*/

const loadLayer=(options, getState)=>{
	
	return (dispatch, getState) =>{
		Connector.loadLayerByOptions(options).then(
			(results)=>{
				dispatch(loadLayerCompleted(results, getState))
			}).catch((err)=>{ 
				console.log(err);
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

export const toggleLegendsView=()=>{
	return {
		type: TOGGLE_LEGENDS_VIEW
	}
}
