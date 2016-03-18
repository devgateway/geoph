import {LOAD_PROJECT_GEOJSON_SUCCESS,LOAD_PEOJECT_GEOJSON_FAILED,CHANGE_LAYER_LEVEL}  from 'app/constants/constants';
import Connector from 'app/connector/connector.js';


const loadProjectsCompleted=(data)=>{
	return {
<<<<<<< HEAD
		type:LOAD_PROJECT_GEOJSON_SUCCESS,
		data:data
	}
=======
    	type: Constants.REQUEST_FILTER_LIST,
    	data:data
 	}
>>>>>>> master
}

const loadProjectsFailed=(error)=>{
return {
		type:LOAD_PEOJECT_GEOJSON_FAILED,
		error
	}	
}



export const loadProjects = (level,params) => {
	return (dispatch, getState) =>{
		Connector.getProjectsGeoJson(level,params)
		.then((data)=>{
<<<<<<< HEAD
				
				dispatch(loadProjectsCompleted(data))}
			).catch((err)=>{ 
				debugger;
				console.log(err);
				dispatch(loadProjectsFailed(err));
			});
		} 

	}
=======
			dispatch(loadProjectsCompleted)}
		).catch(()=>{ 
			dispatch(loadProjectsFailed);
		});
	} 

}
>>>>>>> master

