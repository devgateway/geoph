import Axios from 'axios';
import {API_BASE_URL}  from '../constants/constants';
import Settings from '../util/settings';
import Qs from 'qs';

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
					params: params,
					paramsSerializer: function(params) {
						return Qs.stringify(params, {arrayFormat: 'repeat'})
					},

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
	call(verb,endpoint, params, absolute) {
		
		let apiRoot = absolute? "" : Settings.get('API',API_BASE_URL);		
		let url = `${apiRoot}${endpoint}`; 

		let caller;
		if (verb == GET) caller = this.get;
		if (verb == POST) caller = this.post;
		if (verb == PUT ) caller = this.put;

		return new Promise((resolve, reject) => {
			caller(url, params).then((data) => {
				resolve(data.data);
			}).catch((err) => {
				console.log('Error when trying to get backend data')
				reject(err);
			})
		})
	}

	/**/
	loadLayerByOptions(options,params={}) {

		return new Promise( (resolve, reject) => {
			
			let url=Settings.get('API',options.ep);
			const {level,quality} = options.settings;
			const {id, filters}=options;
	
			if (level){
				url=url.replace('${level}',level);
			}
			if (quality){
				Object.assign(params,{quality})
			}
			Object.assign(params, filters)
			
			this.call(GET,url, params).then((data) => {
				/*apply any data transformation*/
				
				resolve({id,data}); ////resolve with original data or perform any data transformation needed

			}).catch(reject)
		});
	}



	getFilterData(filterType) {
		return new Promise( (resolve, reject) => {
			let path = Settings.get('API','FILTER_LIST')[filterType];
			if (path.mock) {
				this.call(GET,path.path, {}, true).then((data) => {
					resolve(data); ////resolve with original data or perform any data transformation needed			
				}).catch(reject)
			} else {
				this.call(GET, path, {}).then((data) => {
					resolve(data); ////resolve with original data or perform any data transformation needed			
				}).catch(reject)
			}
		});
	}

	getChartData(filters) {
		return new Promise( (resolve, reject) => {
			let path = Settings.get('API','CHARTS');
			if (path.mock) {
				this.call(GET,path.path, {}, true).then((data) => {
					resolve(data);		
				}).catch(reject)
			} else {
				this.call(GET, path, filters).then((data) => {
					resolve(data); 	
				}).catch(reject)
			}
		});
	}

	getProjectPopupData(filters, tab) {
		return new Promise( (resolve, reject) => {
			let path = Settings.get('API','PROJECT_POPUP')[tab];
			this.call(GET, path, filters).then((data) => {
				resolve(data); 	
			}).catch(reject)
		});
	}

	getProjectsWithFilters(filters) {
		return new Promise( (resolve, reject) => {
			let path = Settings.get('API','PROJECT_LIST');
			this.call(GET, path, filters).then((data) => {
				resolve(data); 	
			}).catch(reject)
		});
	}

	getStats(filters) {
		return new Promise( (resolve, reject) => {
			let path = Settings.get('API','STATS');
			this.call(GET, path, filters).then((data) => {
				resolve(data); 	
			}).catch(reject)
		});
	}
}


const connector=new Connector();

export default connector;