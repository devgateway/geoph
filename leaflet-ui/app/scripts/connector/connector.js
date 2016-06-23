import Axios from 'axios';
import {API_BASE_URL}  from '../constants/constants';
import Settings from '../util/settings';
import {connect } from 'react-redux'
import Store from '../store/configureStore.js';
import Qs from 'qs';


console.log(Settings);

const POST= 'POST';
const GET= 'GET';
const PUT= 'PUT';
const DELETE= 'DELETE';

class Connector {


	setStore(store){
		debugger;
		this.store=store;
	}

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
				Axios.post(url, body)
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
		let apiRoot = Settings.get('API',API_BASE_URL);
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


	login(options){
		return new Promise( (resolve, reject) => {
			const {username,password} = options;
			let url = Settings.get('API','LOGIN');

			this.call(POST,url, {username:username,password:password}).then((data) => {
				resolve(data);	
			})
			.catch((error)=>{
				reject(error);	
			})
		})
	}


	uploadIndicator(options){
		debugger;
		const URL=Settings.get('API',API_BASE_URL) + Settings.get('API','INDICATOR_UPLOAD');

			return new Promise( (resolve, reject) => {
			const {file,name,template,color} = options;
			let url = Settings.get('API','INDICATOR_UPLOAD');
			var data = new FormData();
			data.append('name', name);
			data.append('template', template);
			data.append('color', color);
			data.append('file',file);
			var config = {
				progress: function(progressEvent) {
					
					var percentCompleted = progressEvent.loaded / progressEvent.total;
				}
			};

			Axios.post(URL, data, config)
				.then(function (res) {
					resolve(res.data);
				})
				.catch(function (res) {
					debugger;
				});
		})
	}

	getProjectPopupData(filters) {
		return new Promise( (resolve, reject) => {
			let path = Settings.get('API','PROJECT_POPUP');
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


if (!window.__connector){ //singleton connector 
	window.__connector=new Connector();
}
export default window.__connector;