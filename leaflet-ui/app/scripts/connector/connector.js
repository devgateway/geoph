import Axios from 'axios';
import {API_BASE_URL}  from '../constants/constants';
import Settings from '../util/settings';
import Qs from 'qs';

const POST = 'POST';
const GET = 'GET';
const PUT = 'PUT';
const DELETE = 'DELETE';

class Connector {
  setAuthToken(token){
    this.token=token;
  }
  
  getSecurityHeader(){
    return {'X-Security-token': this.token};
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
  
  delete(url, data = {}, config={}) {
    return new Promise(
      function(resolve, reject) {
        Axios.delete(url, config)
          .then(function(response) {
            resolve(response);
          })
          .catch(function(response) {
            reject(response);
          });
      });
  }
  
  post(url, data = {}, config={}) {
    return new Promise(
      function(resolve, reject) {
        Axios.post(url, data, config)
          .then(function(response) {
            resolve(response);
          })
          .catch(function(response) {
            reject(response);
          });
      });
  }
  
  /*A method should always return a promise*/
  call(verb,endpoint, params , config) {
    
    let apiRoot = Settings.get('API',API_BASE_URL);
    let url = `${apiRoot}${endpoint}`;
    
    let caller;
    if (verb == GET) caller = this.get;
    if (verb == POST) caller = this.post;
    if (verb == PUT ) caller = this.put;
    if (verb == DELETE ) caller = this.delete;
    
    return new Promise((resolve, reject) => {
      caller(url, params, config).then((response) => {
        resolve(response.data);
      }).catch((err) => {
        //console.log('Error when trying to get backend data')
        reject(err);
      })
    })
  }
  
  /**/
  loadLayerByOptions(options,params={}) {
    
    return new Promise( (resolve, reject) => {
      let url=Settings.get('API',options.ep);
      const {level,detail} = options.settings;
      const {id, filters, indicator_id, geophotos_id}=options;
      if (level){
        url=url.replace('${level}',level);
      } else {
        url=url.replace('${level}', 'region');//if not level, load region by defult
      }
      if (detail){
        url=url.replace('${detail}',detail);
      }
      if (indicator_id){
        url=url.replace('${indicator_id}',indicator_id);
      }
      if (geophotos_id){
        url=url.replace('${geophotos_id}',geophotos_id);
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
    let apiRoot = Settings.get('API',API_BASE_URL);
    let endpoint = Settings.get('API','LOGIN');
    let url = `${apiRoot}${endpoint}`;
    
    return new Promise( (resolve, reject) => {
      const {username,password} = options;
      
      this.post(url, {username:username,password:password}).then((response) => {
        //console.log(response.headers["x-security-token"]);
        this.setAuthToken(response.headers["x-security-token"]) ;
        resolve(response.data);
      })
        .catch((error)=>{
          reject(error);
        })
    })
  }
  
  logout(){
  
  }
  
  getIndicatorList(){
    return this.call(GET,Settings.get('API','INDICATOR_LIST'),{});
  }
  
  
  getMapList(params){
    return this.call(GET,Settings.get('API','MAP_LIST'),params);
  }
  
  
  removeIndicator(id){
    let url=Settings.get('API','REMOVE_INDICATOR');
    url=url.replace('${indicator_id}',id);
    return this.call(DELETE,url,{},{ headers: this.getSecurityHeader()});
  }
  
  removeDashboard(key){
    let url=Settings.get('API','REMOVE_MAP');
    url=url.replace('${key}',key);
    return this.call(DELETE,url,{},{ headers: this.getSecurityHeader()});
  }
  
  
  uploadIndicator(options){
    const URL=Settings.get('API',API_BASE_URL) + Settings.get('API','INDICATOR_UPLOAD');
    return new Promise( (resolve, reject) => {
      const {file,name,template,css} = options;
      
      let url = Settings.get('API','INDICATOR_UPLOAD');
      var data = new FormData();
      data.append('name', name);
      data.append('admLevel', template);
      data.append('colorScheme', css);
      data.append('file',file);
      this.call(POST,url,data,{ headers: this.getSecurityHeader()}).then(resolve).catch(reject);
    })
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
  
  getLocationStats(filters) {
    return new Promise( (resolve, reject) => {
      let path = Settings.get('API','LOCATION_STATS');
      this.call(GET, path, filters).then((data) => {
        resolve(data);
      }).catch(reject)
    });
  }
  
  saveMap(dataToSave) {
    return new Promise( (resolve, reject) => {
      let path = Settings.get('API','SAVE');
      
      this.call(POST, path, dataToSave,{ headers: this.getSecurityHeader()}).then((data) => {
        resolve(data);
      }).catch(reject)
    });
  }
  
  export2Pdf(data) {
    return new Promise( (resolve, reject) => {
      let path = Settings.get('API','PDF');
      this.call(POST, path, data).then((data) => {
        resolve(data);
      }).catch(reject)
    });
  }
  
  
  shareMap(dataToShare) {
    return new Promise( (resolve, reject) => {
      let path = Settings.get('API','SHARE');
      this.call(POST, path, dataToShare).then((data) => {
        let url = Settings.get('UI','SHARE_URL');
        data.shareUrl=url+data.key;
        resolve(data);
      }).catch(reject)
    });
  }
  
  restoreMap(mapKey) {
    return new Promise( (resolve, reject) => {
      let url=Settings.get('API','RESTORE');
      url=url.replace('${mapId}',mapKey);
      this.call(GET, url, {}).then((savedData) => {
        if(savedData) {
          savedData.data = JSON.parse(savedData.jsonAppMap);
        }
        resolve(savedData);
      }).catch(reject)
    });
  }
  
  getGeophotosList(){
    return this.call(GET,Settings.get('API','GEOPHOTOS_LIST'),{});
  }
  
  getProjectInfo(id) {
    return new Promise( (resolve, reject) => {
      let path = Settings.get('API','PROJECT_INFO');
      path=path.replace('${id}',id);
      this.call(GET, path, id).then((data) => {
        resolve(data);
      }).catch(reject)
    });
  }
  
  getExportURL(type, filters) {
    let apiRoot = Settings.get('API', 'API_BASE_URL');
    let path = Settings.get('API','EXPORT_DATA');
    path=path.replace('${type}', type);
    let params = Qs.stringify(filters, {arrayFormat: 'repeat'});
    let url = `${apiRoot}${path}?${params}`;
    return url;
  }
  
  getDownloadTemplateURL(level) {
    let apiRoot = Settings.get('API', 'API_BASE_URL');
    let path = Settings.get('API','EXPORT_TEMPLATE');
    path=path.replace('${level}', level);
    let url = `${apiRoot}${path}`;
    return url;
  }
}

if (!window.__connector){ //singleton connector
  window.__connector=new Connector();
}
export default window.__connector;
