import {LOAD_PROJECT_GEOJSON_SUCCESS,LOAD_PEOJECT_GEOJSON_FAILED,CHANGE_LAYER_LEVE,LOAD_FUNDING_GEOJSON_SUCCESS}  from '../constants/constants.js';
import Connector from '../connector/connector.js';

const loadLayerCompleted=(level,data,type)=>{
	return {type,data,level}
}

const loadLayerFailed=(type,error)=>{
	return {type,error}	
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
