import * as Constants from 'app/constants/constants';
import Connector from 'app/connector/connector.js';


const loadProjectsCompleted=(data)=>{
	return {
    	type: Constants.REQUEST_FILTER_LIST,
    	data:data
 	}
}

const loadProjectsFailed=()=>{
	
}


export const loadProjects = () => {

	return (dispatch, getState) =>{
		
		Connector.getProjectsGeoJson()
		.then((data)=>{
			dispatch(loadProjectsCompleted)}
		).catch(()=>{ 
			dispatch(loadProjectsFailed);
		});
	} 

}

