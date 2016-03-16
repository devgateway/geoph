import {LOAD_PROJECT_GEOJSON_SUCCESS,LOAD_PEOJECT_GEOJSON_FAILED}  from 'app/constants/constants';
import Connector from 'app/connector/connector.js';


const loadProjectsCompleted=(data)=>{
	return {
		type:LOAD_PROJECT_GEOJSON_SUCCESS,
		data:data
	}
}

const loadProjectsFailed=(error)=>{
return {
		type:LOAD_PEOJECT_GEOJSON_FAILED,
		error
	}	
}


export const loadProjects = () => {
	return (dispatch, getState) =>{
		Connector.getProjectsGeoJson()
		.then((data)=>{
				
				dispatch(loadProjectsCompleted(data))}
			).catch((err)=>{ 
				debugger;
				dispatch(loadProjectsFailed(err));
			});
		} 

	}

