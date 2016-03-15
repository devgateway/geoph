import Constants from 'app/constants/constants';
import Connector from 'app/connector/connector.js';


const loadProjectsCompleted=()=>{
	debugger;
}

const loadProjectsFailed=()=>{
	debugger;
}

export const loadProjects = (level) => {

	return (dispatch, getState) =>{
		Connector.getProjectsByLevel(level).then(()=>dispatch(loadProjectsCompleted)).catch();
	} 

}

