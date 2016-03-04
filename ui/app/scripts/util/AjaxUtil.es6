import Axios from 'axios';
//TODO: Complete with POST and DELTE methods 
//TODO : Security Integration
export default class AjaxUtil {
	static get(url, params = {}) {
		return new Promise(
			function(resolve, reject) { // (A)
				Axios.get(url, {
						responseType: 'json',
						params: params
					})
					.then(function(response) {
						resolve(response);
					})
					.catch(function(response) {
						reject(response);
					});
			});
	}

	static put(url, body = {}) {
		return new Promise(
			function(resolve, reject) { 
				Axios.put(url, body)
					.then(function(response) {
						resolve(response);
					})
					.catch(function(response) {
						reject(response);
					});
			});
	}


}