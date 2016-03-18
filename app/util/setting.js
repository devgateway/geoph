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
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInV0aWxcXHNldHRpbmcuZXM2Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7OztLQUVxQjtBQUVwQixXQUZvQixRQUVwQixHQUFjO3lCQUZNLFVBRU47O0FBQ2IsUUFBSyxHQUFMLEdBQVksU0FBUyxRQUFULENBQWtCLElBQWxCLENBQXVCLE9BQXZCLENBQStCLFdBQS9CLElBQThDLENBQUMsQ0FBRCxHQUFLLGFBQW5ELEdBQW1FLFlBQW5FLENBREM7R0FBZDs7ZUFGb0I7OzhCQXlCVCxRQUFRLE1BQU07QUFDeEIsUUFBRyxDQUFDLE9BQU8sUUFBUCxFQUFnQjtBQUNuQixXQUFNLElBQUksS0FBSixDQUFVLCtCQUFWLENBQU4sQ0FEbUI7S0FBcEI7QUFHQSxRQUFJLE1BQU0sT0FBTyxRQUFQLENBQWdCLE1BQWhCLENBQU4sQ0FKb0I7QUFLeEIsUUFBSSxPQUFPLElBQUksSUFBSixDQUFQLENBTG9COztBQU94QixRQUFJLGdCQUFnQixNQUFoQixFQUF3Qjs7QUFFM0IsWUFBTyxLQUFLLEtBQUssR0FBTCxDQUFaO0FBRjJCLEtBQTVCLE1BR087QUFDTixhQUFPLElBQVAsQ0FETTtNQUhQOzs7O3VCQVFHLE1BQU0sT0FBTztBQUNoQixXQUFPLFFBQVAsQ0FBZ0IsSUFBaEIsSUFBd0IsS0FBeEIsQ0FEZ0I7Ozs7NEJBSVI7QUFDUixXQUFPLEtBQUssR0FBTCxDQURDOzs7OzBCQUtGLEtBQUs7QUFDWCxTQUFLLEdBQUwsR0FBVyxHQUFYLENBRFc7Ozs7OEJBMUNNLFVBQVM7QUFDMUIsUUFBSSxDQUFDLE9BQU8saUJBQVAsRUFBMEI7QUFDOUIsWUFBTyxpQkFBUCxHQUEyQixJQUFJLFFBQUosRUFBM0IsQ0FEOEI7S0FBL0I7QUFHQSxXQUFPLFFBQVAsR0FBZ0IsUUFBaEIsQ0FKMEI7Ozs7dUJBT2hCLFFBQVEsTUFBTTs7QUFFeEIsUUFBSSxPQUFPLFFBQVAsSUFBaUIsSUFBakIsSUFBeUIsQ0FBQyxPQUFPLFFBQVAsRUFBZ0I7QUFDN0MsV0FBTSxNQUFNLHVDQUFOLENBQU4sQ0FENkM7S0FBOUMsTUFFSztBQUNKLFlBQU8sT0FBTyxpQkFBUCxDQUF5QixVQUF6QixDQUFvQyxNQUFwQyxFQUE0QyxJQUE1QyxDQUFQLENBREk7S0FGTDs7OztTQWhCbUIiLCJmaWxlIjoidXRpbFxcc2V0dGluZy5lczYiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgQ29uc3RhbnRzIGZyb20gJ2FwcC9jb25zdGFudHMvY29uc3RhbnRzJ1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgY2xhc3MgU2V0dGluZ3Mge1xyXG5cclxuXHRjb25zdHJ1Y3RvcigpIHtcclxuXHRcdHRoaXMuZW52ID0gKGRvY3VtZW50LmxvY2F0aW9uLmhvc3QuaW5kZXhPZignbG9jYWxob3N0JykgPiAtMSA/ICdkZXZlbG9wbWVudCcgOiAncHJvZHVjdGlvbicpO1xyXG5cdH1cclxuXHJcblxyXG5cdHN0YXRpYyBpbml0aWFsaXplKHNldHRpbmdzKXtcclxuXHRcdGlmICghd2luZG93Ll9zZXR0aW5nX2luc3RhbmNlKSB7XHJcblx0XHRcdHdpbmRvdy5fc2V0dGluZ19pbnN0YW5jZSA9IG5ldyBTZXR0aW5ncygpXHJcblx0XHR9XHJcblx0XHR3aW5kb3cuX3NldHRpbmc9c2V0dGluZ3M7XHJcblx0fVxyXG5cclxuXHRzdGF0aWMgZ2V0KG1vZHVsZSwgbmFtZSkge1xyXG5cdFx0XHJcblx0XHRpZiAod2luZG93Ll9zZXR0aW5nPT1udWxsIHx8ICF3aW5kb3cuX3NldHRpbmcpe1xyXG5cdFx0XHR0aHJvdyBFcnJvcignU2V0dGluZ3MgIHNob3VsZCBiZSBpbml0aWFsaXplZCBmaXJzdCcpO1xyXG5cdFx0fWVsc2V7XHJcblx0XHRcdHJldHVybiB3aW5kb3cuX3NldHRpbmdfaW5zdGFuY2UuZ2V0U2V0dGluZyhtb2R1bGUsIG5hbWUpO1xyXG5cdFx0fVxyXG5cdH1cclxuXHJcblxyXG5cclxuXHRnZXRTZXR0aW5nKG1vZHVsZSwgbmFtZSkge1xyXG5cdFx0aWYoIXdpbmRvdy5fc2V0dGluZyl7XHJcblx0XHRcdHRocm93IG5ldyBFcnJvcignU2V0dGluZ3Mgc2hvdWxkIGJlIGluaXRpYWxpemUnKTtcclxuXHRcdH1cclxuXHRcdGxldCBtb2QgPSB3aW5kb3cuX3NldHRpbmdbbW9kdWxlXTtcclxuXHRcdGxldCBpdGVtID0gbW9kW25hbWVdO1xyXG5cclxuXHRcdGlmIChpdGVtIGluc3RhbmNlb2YgT2JqZWN0KSB7XHJcblxyXG5cdFx0XHRyZXR1cm4gaXRlbVt0aGlzLmVudl07IC8vcmV0dXJuIGVudiBiYXNlZCBzZXR0aW5cclxuXHRcdH0gZWxzZSB7XHJcblx0XHRcdHJldHVybiBpdGVtO1xyXG5cdFx0fVxyXG5cdH1cclxuXHJcblx0c2V0KG5hbWUsIHZhbHVlKSB7XHJcblx0XHR3aW5kb3cuX3NldHRpbmdbbmFtZV0gPSB2YWx1ZTtcclxuXHR9XHJcblxyXG5cdGdldEVudigpIHtcclxuXHRcdHJldHVybiB0aGlzLmVudlxyXG5cclxuXHR9XHJcblxyXG5cdHNldEVudihlbnYpIHtcclxuXHRcdHRoaXMuZW52ID0gZW52O1xyXG5cdH1cclxuXHJcbn0iXX0=