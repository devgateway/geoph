import 
{TOGGLE_LAYER,LOAD_PROJECT_GEOJSON_SUCCESS,LOAD_PEOJECT_GEOJSON_FAILED,CHANGE_LAYER_LEVE,LOAD_FUNDING_GEOJSON_SUCCESS}  from '../constants/constants.js';
import Connector from '../connector/connector.js';



const loadLayerCompleted=(level,data,type)=>{
	return {type,data,level}
}

const loadLayerFailed=(type,error)=>{
	return {type,error}	
}


export const toggleVisibility=(name,visible,params)=>{

	return (dispatch, getState) => {
	debugger;
		let layers=getState().map.layers;
		if (!layers.find(l=> l.name==name)){
			//TODO:take params from filters state
			return dispatch(loadLayer(name,'region',params));
		}else{
			debugger;
		}


	}

}


const loadLayer=(name,level,params)=>{
	return dispatch => {
		switch (name){
			case 'project':
				return  dispatch(loadProjects(level,params));

			case 'funding':
				return   dispatch(loadFunding(level,params));
		}
	}	
}


export const loadProjects = (level,params) => {
	return (dispatch, getState) =>{
		Connector.getProjectsGeoJson(level,params)
		.then((data)=>{
			dispatch(loadLayerCompleted(level,data,LOAD_PROJECT_GEOJSON_SUCCESS))}
			).catch((err)=>{ 
				console.error(err);
				dispatch(loadLayerFailed(err,LOAD_PEOJECT_GEOJSON_FAILED));
			});
		} 

	}

	export const loadFunding = (level,params) => {
		return (dispatch, getState) =>{
			Connector.getFundingGeoJson(level,params)
			.then((data)=>{

				dispatch(loadLayerCompleted(level,data,LOAD_FUNDING_GEOJSON_SUCCESS))}
				).catch((err)=>{ 
					console.error(err);
					dispatch(loadLayerFailed(err,LOAD_FUNDING_GEOJSON_FAILED));
				});
			} 

		}


