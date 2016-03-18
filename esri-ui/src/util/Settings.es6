import Constants from 'app/constants/constants'

export default class Settings {

	constructor() {
		this.env = (document.location.host.indexOf('localhost') > -1 ? 'development' : 'production');
	}


	static initialize(settings){
		if (!window._setting_instance) {
			window._setting_instance = new Settings()
		}
		window._setting=settings;
	}

	static get(module, name) {
		
		if (window._setting==null || !window._setting){
			throw Error('Settings  should be initialized first');
		}else{
			return window._setting_instance.getSetting(module, name);
		}
	}



	getSetting(module, name) {
		if(!window._setting){
			throw new Error('Settings should be initialize');
		}
		let mod = window._setting[module];
		let item = mod[name];

		if (item instanceof Object) {

			return item[this.env]; //return env based settin
		} else {
			return item;
		}
	}

	set(name, value) {
		window._setting[name] = value;
	}

	getEnv() {
		return this.env

	}

	setEnv(env) {
		this.env = env;
	}

}