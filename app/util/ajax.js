define(['exports', 'axios'], function (exports, _axios) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _axios2 = _interopRequireDefault(_axios);

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

	var AjaxUtil = function () {
		function AjaxUtil() {
			_classCallCheck(this, AjaxUtil);
		}

		_createClass(AjaxUtil, null, [{
			key: 'get',
			value: function get(url) {
				var params = arguments.length <= 1 || arguments[1] === undefined ? {} : arguments[1];

				return new Promise(function (resolve, reject) {
					// (A)
					_axios2.default.get(url, {
						responseType: 'json',
						params: params
					}).then(function (response) {
						resolve(response);
					}).catch(function (response) {
						reject(response);
					});
				});
			}
		}, {
			key: 'put',
			value: function put(url) {
				var body = arguments.length <= 1 || arguments[1] === undefined ? {} : arguments[1];

				return new Promise(function (resolve, reject) {
					_axios2.default.put(url, body).then(function (response) {
						resolve(response);
					}).catch(function (response) {
						reject(response);
					});
				});
			}
		}]);

		return AjaxUtil;
	}();

	exports.default = AjaxUtil;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInV0aWxcXGFqYXguZXM2Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7OztLQUdxQjs7Ozs7Ozt1QkFDVCxLQUFrQjtRQUFiLCtEQUFTLGtCQUFJOztBQUM1QixXQUFPLElBQUksT0FBSixDQUNOLFVBQVMsT0FBVCxFQUFrQixNQUFsQixFQUEwQjs7QUFDekIscUJBQU0sR0FBTixDQUFVLEdBQVYsRUFBZTtBQUNiLG9CQUFjLE1BQWQ7QUFDQSxjQUFRLE1BQVI7TUFGRixFQUlFLElBSkYsQ0FJTyxVQUFTLFFBQVQsRUFBbUI7QUFDeEIsY0FBUSxRQUFSLEVBRHdCO01BQW5CLENBSlAsQ0FPRSxLQVBGLENBT1EsVUFBUyxRQUFULEVBQW1CO0FBQ3pCLGFBQU8sUUFBUCxFQUR5QjtNQUFuQixDQVBSLENBRHlCO0tBQTFCLENBREQsQ0FENEI7Ozs7dUJBZ0JsQixLQUFnQjtRQUFYLDZEQUFPLGtCQUFJOztBQUMxQixXQUFPLElBQUksT0FBSixDQUNOLFVBQVMsT0FBVCxFQUFrQixNQUFsQixFQUEwQjtBQUN6QixxQkFBTSxHQUFOLENBQVUsR0FBVixFQUFlLElBQWYsRUFDRSxJQURGLENBQ08sVUFBUyxRQUFULEVBQW1CO0FBQ3hCLGNBQVEsUUFBUixFQUR3QjtNQUFuQixDQURQLENBSUUsS0FKRixDQUlRLFVBQVMsUUFBVCxFQUFtQjtBQUN6QixhQUFPLFFBQVAsRUFEeUI7TUFBbkIsQ0FKUixDQUR5QjtLQUExQixDQURELENBRDBCOzs7O1NBakJQIiwiZmlsZSI6InV0aWxcXGFqYXguZXM2Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IEF4aW9zIGZyb20gJ2F4aW9zJztcclxuLy9UT0RPOiBDb21wbGV0ZSB3aXRoIFBPU1QgYW5kIERFTFRFIG1ldGhvZHMgXHJcbi8vVE9ETyA6IFNlY3VyaXR5IEludGVncmF0aW9uXHJcbmV4cG9ydCBkZWZhdWx0IGNsYXNzIEFqYXhVdGlsIHtcclxuXHRzdGF0aWMgZ2V0KHVybCwgcGFyYW1zID0ge30pIHtcclxuXHRcdHJldHVybiBuZXcgUHJvbWlzZShcclxuXHRcdFx0ZnVuY3Rpb24ocmVzb2x2ZSwgcmVqZWN0KSB7IC8vIChBKVxyXG5cdFx0XHRcdEF4aW9zLmdldCh1cmwsIHtcclxuXHRcdFx0XHRcdFx0cmVzcG9uc2VUeXBlOiAnanNvbicsXHJcblx0XHRcdFx0XHRcdHBhcmFtczogcGFyYW1zXHJcblx0XHRcdFx0XHR9KVxyXG5cdFx0XHRcdFx0LnRoZW4oZnVuY3Rpb24ocmVzcG9uc2UpIHtcclxuXHRcdFx0XHRcdFx0cmVzb2x2ZShyZXNwb25zZSk7XHJcblx0XHRcdFx0XHR9KVxyXG5cdFx0XHRcdFx0LmNhdGNoKGZ1bmN0aW9uKHJlc3BvbnNlKSB7XHJcblx0XHRcdFx0XHRcdHJlamVjdChyZXNwb25zZSk7XHJcblx0XHRcdFx0XHR9KTtcclxuXHRcdFx0fSk7XHJcblx0fVxyXG5cclxuXHRzdGF0aWMgcHV0KHVybCwgYm9keSA9IHt9KSB7XHJcblx0XHRyZXR1cm4gbmV3IFByb21pc2UoXHJcblx0XHRcdGZ1bmN0aW9uKHJlc29sdmUsIHJlamVjdCkgeyBcclxuXHRcdFx0XHRBeGlvcy5wdXQodXJsLCBib2R5KVxyXG5cdFx0XHRcdFx0LnRoZW4oZnVuY3Rpb24ocmVzcG9uc2UpIHtcclxuXHRcdFx0XHRcdFx0cmVzb2x2ZShyZXNwb25zZSk7XHJcblx0XHRcdFx0XHR9KVxyXG5cdFx0XHRcdFx0LmNhdGNoKGZ1bmN0aW9uKHJlc3BvbnNlKSB7XHJcblx0XHRcdFx0XHRcdHJlamVjdChyZXNwb25zZSk7XHJcblx0XHRcdFx0XHR9KTtcclxuXHRcdFx0fSk7XHJcblx0fVxyXG5cclxuXHJcbn0iXX0=