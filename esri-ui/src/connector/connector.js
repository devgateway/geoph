import Settings from 'app/util/Settings';
import Axios from 'axios';

export
default class Connector {

	authenticate() {

	}


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
			call(settings.get(constants.PROJECT_GEOJSON_URL), {
				level
			}).then((data) => {
				resolve(data); ////resolve with original data or perform any data transformation needed
			}).catch(reject)
		});

	}

