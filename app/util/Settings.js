define(['exports', 'app/constants/constants'], function (exports, _constants) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _constants2 = _interopRequireDefault(_constants);

	function _interopRequireDefault(obj) {
		return obj && obj.__esModule ? obj : {
			default: obj
		};
	}

	function _classCallCheck(instance, Constructor) {
		if (!(instance instanceof Constructor)) {
			throw new TypeError("Cannot call a class as a function");
		}
	}

	var _createClass = function () {
		function defineProperties(target, props) {
			for (var i = 0; i < props.length; i++) {
				var descriptor = props[i];
				descriptor.enumerable = descriptor.enumerable || false;
				descriptor.configurable = true;
				if ("value" in descriptor) descriptor.writable = true;
				Object.defineProperty(target, descriptor.key, descriptor);
			}
		}

		return function (Constructor, protoProps, staticProps) {
			if (protoProps) defineProperties(Constructor.prototype, protoProps);
			if (staticProps) defineProperties(Constructor, staticProps);
			return Constructor;
		};
	}();

	var Settings = function () {
		function Settings() {
			_classCallCheck(this, Settings);

			this.env = document.location.host.indexOf('localhost') > -1 ? 'development' : 'production';
		}

		_createClass(Settings, [{
			key: 'getSetting',
			value: function getSetting(module, name) {
				if (!window._setting) {
					throw new Error('Settings should be initialize');
				}
				var mod = window._setting[module];
				var item = mod[name];

				if (item instanceof Object) {

					return item[this.env]; //return env based settin
				} else {
						return item;
					}
			}
		}, {
			key: 'set',
			value: function set(name, value) {
				window._setting[name] = value;
			}
		}, {
			key: 'getEnv',
			value: function getEnv() {
				return this.env;
			}
		}, {
			key: 'setEnv',
			value: function setEnv(env) {
				this.env = env;
			}
		}], [{
			key: 'initialize',
			value: function initialize(settings) {
				if (!window._setting_instance) {
					window._setting_instance = new Settings();
				}
				window._setting = settings;
			}
		}, {
			key: 'get',
			value: function get(module, name) {

				if (window._setting == null || !window._setting) {
					throw Error('Settings  should be initialized first');
				} else {
					return window._setting_instance.getSetting(module, name);
				}
			}
		}]);

		return Settings;
	}();

	exports.default = Settings;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInV0aWxcXFNldHRpbmdzLmVzNiJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7S0FFcUI7QUFFcEIsV0FGb0IsUUFFcEIsR0FBYzt5QkFGTSxVQUVOOztBQUNiLFFBQUssR0FBTCxHQUFZLFNBQVMsUUFBVCxDQUFrQixJQUFsQixDQUF1QixPQUF2QixDQUErQixXQUEvQixJQUE4QyxDQUFDLENBQUQsR0FBSyxhQUFuRCxHQUFtRSxZQUFuRSxDQURDO0dBQWQ7O2VBRm9COzs4QkF5QlQsUUFBUSxNQUFNO0FBQ3hCLFFBQUcsQ0FBQyxPQUFPLFFBQVAsRUFBZ0I7QUFDbkIsV0FBTSxJQUFJLEtBQUosQ0FBVSwrQkFBVixDQUFOLENBRG1CO0tBQXBCO0FBR0EsUUFBSSxNQUFNLE9BQU8sUUFBUCxDQUFnQixNQUFoQixDQUFOLENBSm9CO0FBS3hCLFFBQUksT0FBTyxJQUFJLElBQUosQ0FBUCxDQUxvQjs7QUFPeEIsUUFBSSxnQkFBZ0IsTUFBaEIsRUFBd0I7O0FBRTNCLFlBQU8sS0FBSyxLQUFLLEdBQUwsQ0FBWjtBQUYyQixLQUE1QixNQUdPO0FBQ04sYUFBTyxJQUFQLENBRE07TUFIUDs7Ozt1QkFRRyxNQUFNLE9BQU87QUFDaEIsV0FBTyxRQUFQLENBQWdCLElBQWhCLElBQXdCLEtBQXhCLENBRGdCOzs7OzRCQUlSO0FBQ1IsV0FBTyxLQUFLLEdBQUwsQ0FEQzs7OzswQkFLRixLQUFLO0FBQ1gsU0FBSyxHQUFMLEdBQVcsR0FBWCxDQURXOzs7OzhCQTFDTSxVQUFTO0FBQzFCLFFBQUksQ0FBQyxPQUFPLGlCQUFQLEVBQTBCO0FBQzlCLFlBQU8saUJBQVAsR0FBMkIsSUFBSSxRQUFKLEVBQTNCLENBRDhCO0tBQS9CO0FBR0EsV0FBTyxRQUFQLEdBQWdCLFFBQWhCLENBSjBCOzs7O3VCQU9oQixRQUFRLE1BQU07O0FBRXhCLFFBQUksT0FBTyxRQUFQLElBQWlCLElBQWpCLElBQXlCLENBQUMsT0FBTyxRQUFQLEVBQWdCO0FBQzdDLFdBQU0sTUFBTSx1Q0FBTixDQUFOLENBRDZDO0tBQTlDLE1BRUs7QUFDSixZQUFPLE9BQU8saUJBQVAsQ0FBeUIsVUFBekIsQ0FBb0MsTUFBcEMsRUFBNEMsSUFBNUMsQ0FBUCxDQURJO0tBRkw7Ozs7U0FoQm1CIiwiZmlsZSI6InV0aWxcXFNldHRpbmdzLmVzNiIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBDb25zdGFudHMgZnJvbSAnYXBwL2NvbnN0YW50cy9jb25zdGFudHMnXHJcblxyXG5leHBvcnQgZGVmYXVsdCBjbGFzcyBTZXR0aW5ncyB7XHJcblxyXG5cdGNvbnN0cnVjdG9yKCkge1xyXG5cdFx0dGhpcy5lbnYgPSAoZG9jdW1lbnQubG9jYXRpb24uaG9zdC5pbmRleE9mKCdsb2NhbGhvc3QnKSA+IC0xID8gJ2RldmVsb3BtZW50JyA6ICdwcm9kdWN0aW9uJyk7XHJcblx0fVxyXG5cclxuXHJcblx0c3RhdGljIGluaXRpYWxpemUoc2V0dGluZ3Mpe1xyXG5cdFx0aWYgKCF3aW5kb3cuX3NldHRpbmdfaW5zdGFuY2UpIHtcclxuXHRcdFx0d2luZG93Ll9zZXR0aW5nX2luc3RhbmNlID0gbmV3IFNldHRpbmdzKClcclxuXHRcdH1cclxuXHRcdHdpbmRvdy5fc2V0dGluZz1zZXR0aW5ncztcclxuXHR9XHJcblxyXG5cdHN0YXRpYyBnZXQobW9kdWxlLCBuYW1lKSB7XHJcblx0XHRcclxuXHRcdGlmICh3aW5kb3cuX3NldHRpbmc9PW51bGwgfHwgIXdpbmRvdy5fc2V0dGluZyl7XHJcblx0XHRcdHRocm93IEVycm9yKCdTZXR0aW5ncyAgc2hvdWxkIGJlIGluaXRpYWxpemVkIGZpcnN0Jyk7XHJcblx0XHR9ZWxzZXtcclxuXHRcdFx0cmV0dXJuIHdpbmRvdy5fc2V0dGluZ19pbnN0YW5jZS5nZXRTZXR0aW5nKG1vZHVsZSwgbmFtZSk7XHJcblx0XHR9XHJcblx0fVxyXG5cclxuXHJcblxyXG5cdGdldFNldHRpbmcobW9kdWxlLCBuYW1lKSB7XHJcblx0XHRpZighd2luZG93Ll9zZXR0aW5nKXtcclxuXHRcdFx0dGhyb3cgbmV3IEVycm9yKCdTZXR0aW5ncyBzaG91bGQgYmUgaW5pdGlhbGl6ZScpO1xyXG5cdFx0fVxyXG5cdFx0bGV0IG1vZCA9IHdpbmRvdy5fc2V0dGluZ1ttb2R1bGVdO1xyXG5cdFx0bGV0IGl0ZW0gPSBtb2RbbmFtZV07XHJcblxyXG5cdFx0aWYgKGl0ZW0gaW5zdGFuY2VvZiBPYmplY3QpIHtcclxuXHJcblx0XHRcdHJldHVybiBpdGVtW3RoaXMuZW52XTsgLy9yZXR1cm4gZW52IGJhc2VkIHNldHRpblxyXG5cdFx0fSBlbHNlIHtcclxuXHRcdFx0cmV0dXJuIGl0ZW07XHJcblx0XHR9XHJcblx0fVxyXG5cclxuXHRzZXQobmFtZSwgdmFsdWUpIHtcclxuXHRcdHdpbmRvdy5fc2V0dGluZ1tuYW1lXSA9IHZhbHVlO1xyXG5cdH1cclxuXHJcblx0Z2V0RW52KCkge1xyXG5cdFx0cmV0dXJuIHRoaXMuZW52XHJcblxyXG5cdH1cclxuXHJcblx0c2V0RW52KGVudikge1xyXG5cdFx0dGhpcy5lbnYgPSBlbnY7XHJcblx0fVxyXG5cclxufSJdfQ==