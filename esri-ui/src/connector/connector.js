import Axios from 'axios';
import Constants from 'app/constants/constants';
import Settings from 'app/util/Settings';


console.log(Settings);

const POST= 'POST';
const GET= 'GET';
const PUT= 'PUT';
const DELETE= 'DELETE';

class Connector {


	get(url, params = {}) {
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

	put(url, body = {}) {
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


	post(url, body = {}) {
		
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

	/*A method should always return a promise*/
	call(verb,endpoint, params) {
		debugger;
		
		let apiRoot=Settings.get('API',Constants.API_BASE_URL);
		let url=`$apiRoot\\$endpoint`;


		let caller;
		if (verb == GET) caller = this.get;
		if (verb == POST) caller = this.post;
		if (verb == PUT ) caller = this.put;



		return new Promise((resolve, reject) => {
			caller(url, params).then((data) => {
				resolve(data);
			}).catch((err) => {
				console.log('Failed lading api data')
				reject(err);
			})
		})
	}

	/**/
	getProjects() {
		return new Promise( (resolve, reject) => {
			
			this.call(GET,Settings.get('API','PROJECT_GEO_JSON'), {}).then((data) => {
				/*apply any data transformation*/
				resolve(data); ////resolve with original data or perform any data transformation needed
			
			}).catch(reject)
		});

	}

	
}


export default new Connector();