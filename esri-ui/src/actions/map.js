import Constants from 'app/constants/constants';
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
		Connector.getProjectsByLevel().then(()=>dispatch(loadProjectsCompleted)).catch(()=>{ 
			dispatch(loadProjectsFailed);
		});
	} 

}

