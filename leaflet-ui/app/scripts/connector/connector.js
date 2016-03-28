import Axios from 'axios';
import {API_BASE_URL}  from '../constants/constants';
import Settings from '../util/settings';


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
		
		
		let apiRoot=Settings.get('API',API_BASE_URL);
		
		let url=`${apiRoot}${endpoint}`; 


		let caller;
		if (verb == GET) caller = this.get;
		if (verb == POST) caller = this.post;
		if (verb == PUT ) caller = this.put;



		return new Promise((resolve, reject) => {
			caller(url, params).then((data) => {
				resolve(data.data);
			}).catch((err) => {
				console.log('Failed lading api data')
				reject(err);
			})
		})
	}

	/**/
	getProjectsGeoJson(level,params) {
		return new Promise( (resolve, reject) => {
			let url=Settings.get('API','PROJECT_GEOJSON');
			
			this.call(GET,url.replace('${level}',level), params).then((data) => {
				/*apply any data transformation*/
				resolve(data); ////resolve with original data or perform any data transformation needed
			
			}).catch(reject)
		});
	}

	getFilterList(filterType) {
		return new Promise( (resolve, reject) => {
			this.call(GET,Settings.get('API','FILTER_LIST')[filterType], {}).then((data) => {
				resolve(data); ////resolve with original data or perform any data transformation needed			
			}).catch(reject)
		});
	}
}


const connector=new Connector();

export default connector;