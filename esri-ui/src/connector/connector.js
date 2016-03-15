import Axios from 'axios';
import Constants from 'app/constants/constants';
import Settings from 'app/util/Settings';

debugger;
console.log(Settings);

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
	call(url, params, verb) {

		let base=Settings.get('API',Constants.API_BASE_URL);
		console.log(base);

		let caller;
		if (verb == 'get') caller = get;
		if (verb == 'post') caller = post;
		if (verb == 'put') caller = put;

		return new Promise((resolve, reject) => {
			caller(url, params).then((data) => {
				resolve(data);
			}).catch((err) => {
				console.log('Failed lading api data')
				reject(err);
			})
		})
	}

	getProjectsByLevel(level) {
		return new Promise( (resolve, reject) => {
			this.call('', {
				level
			}).then((data) => {
				resolve(data); ////resolve with original data or perform any data transformation needed
			}).catch(reject)
		});

	}
}

export default new Connector();