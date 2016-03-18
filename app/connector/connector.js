define(['exports', 'axios', 'app/constants/constants', 'app/util/settings'], function (exports, _axios, _constants, _settings) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _axios2 = _interopRequireDefault(_axios);

	var _settings2 = _interopRequireDefault(_settings);

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

	console.log(_settings2.default);

	var POST = 'POST';
	var GET = 'GET';
	var PUT = 'PUT';
	var DELETE = 'DELETE';

	var Connector = function () {
		function Connector() {
			_classCallCheck(this, Connector);
		}

		_createClass(Connector, [{
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
		}, {
			key: 'post',
			value: function post(url) {
				var body = arguments.length <= 1 || arguments[1] === undefined ? {} : arguments[1];


				return new Promise(function (resolve, reject) {
					_axios2.default.put(url, body).then(function (response) {
						resolve(response);
					}).catch(function (response) {
						reject(response);
					});
				});
			}
		}, {
			key: 'call',
			value: function call(verb, endpoint, params) {

				var apiRoot = _settings2.default.get('API', _constants.API_BASE_URL);

				var url = '' + apiRoot + endpoint;

				var caller = void 0;
				if (verb == GET) caller = this.get;
				if (verb == POST) caller = this.post;
				if (verb == PUT) caller = this.put;

				return new Promise(function (resolve, reject) {
					caller(url, params).then(function (data) {
						resolve(data.data);
					}).catch(function (err) {
						console.log('Failed lading api data');
						reject(err);
					});
				});
			}
		}, {
			key: 'getProjectsGeoJson',
			value: function getProjectsGeoJson(level, params) {
				var _this = this;

				return new Promise(function (resolve, reject) {
					var url = _settings2.default.get('API', 'PROJECT_GEOJSON');
					debugger;
					_this.call(GET, url.replace('${level}', level), params).then(function (data) {
						/*apply any data transformation*/
						resolve(data); ////resolve with original data or perform any data transformation needed
					}).catch(reject);
				});
			}
		}, {
			key: 'getFilterList',
			value: function getFilterList(filterType) {
				var _this2 = this;

				return new Promise(function (resolve, reject) {
					_this2.call(GET, _settings2.default.get('API', 'FILTER_LIST')[filterType], {}).then(function (data) {
						resolve(data); ////resolve with original data or perform any data transformation needed			
					}).catch(reject);
				});
			}
		}]);

		return Connector;
	}();

	var connector = new Connector();

	exports.default = connector;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbm5lY3RvclxcY29ubmVjdG9yLmpzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7O0FBS0EsU0FBUSxHQUFSOztBQUVBLEtBQU0sT0FBTSxNQUFOO0FBQ04sS0FBTSxNQUFLLEtBQUw7QUFDTixLQUFNLE1BQUssS0FBTDtBQUNOLEtBQU0sU0FBUSxRQUFSOztLQUVBOzs7Ozs7O3VCQUdELEtBQWtCO1FBQWIsK0RBQVMsa0JBQUk7O0FBQ3JCLFdBQU8sSUFBSSxPQUFKLENBQ04sVUFBUyxPQUFULEVBQWtCLE1BQWxCLEVBQTBCOztBQUN6QixxQkFBTSxHQUFOLENBQVUsR0FBVixFQUFlO0FBQ2Qsb0JBQWMsTUFBZDtBQUNBLGNBQVEsTUFBUjtNQUZELEVBSUMsSUFKRCxDQUlNLFVBQVMsUUFBVCxFQUFtQjtBQUN4QixjQUFRLFFBQVIsRUFEd0I7TUFBbkIsQ0FKTixDQU9DLEtBUEQsQ0FPTyxVQUFTLFFBQVQsRUFBbUI7QUFDekIsYUFBTyxRQUFQLEVBRHlCO01BQW5CLENBUFAsQ0FEeUI7S0FBMUIsQ0FERCxDQURxQjs7Ozt1QkFnQmxCLEtBQWdCO1FBQVgsNkRBQU8sa0JBQUk7O0FBQ25CLFdBQU8sSUFBSSxPQUFKLENBQ04sVUFBUyxPQUFULEVBQWtCLE1BQWxCLEVBQTBCO0FBQ3pCLHFCQUFNLEdBQU4sQ0FBVSxHQUFWLEVBQWUsSUFBZixFQUNDLElBREQsQ0FDTSxVQUFTLFFBQVQsRUFBbUI7QUFDeEIsY0FBUSxRQUFSLEVBRHdCO01BQW5CLENBRE4sQ0FJQyxLQUpELENBSU8sVUFBUyxRQUFULEVBQW1CO0FBQ3pCLGFBQU8sUUFBUCxFQUR5QjtNQUFuQixDQUpQLENBRHlCO0tBQTFCLENBREQsQ0FEbUI7Ozs7d0JBY2YsS0FBZ0I7UUFBWCw2REFBTyxrQkFBSTs7O0FBRXBCLFdBQU8sSUFBSSxPQUFKLENBQ04sVUFBUyxPQUFULEVBQWtCLE1BQWxCLEVBQTBCO0FBQ3pCLHFCQUFNLEdBQU4sQ0FBVSxHQUFWLEVBQWUsSUFBZixFQUNDLElBREQsQ0FDTSxVQUFTLFFBQVQsRUFBbUI7QUFDeEIsY0FBUSxRQUFSLEVBRHdCO01BQW5CLENBRE4sQ0FJQyxLQUpELENBSU8sVUFBUyxRQUFULEVBQW1CO0FBQ3pCLGFBQU8sUUFBUCxFQUR5QjtNQUFuQixDQUpQLENBRHlCO0tBQTFCLENBREQsQ0FGb0I7Ozs7d0JBZWhCLE1BQUssVUFBVSxRQUFROztBQUczQixRQUFJLFVBQVEsbUJBQVMsR0FBVCxDQUFhLEtBQWIsMEJBQVIsQ0FIdUI7O0FBSzNCLFFBQUksV0FBTyxVQUFVLFFBQWpCLENBTHVCOztBQVEzQixRQUFJLGVBQUosQ0FSMkI7QUFTM0IsUUFBSSxRQUFRLEdBQVIsRUFBYSxTQUFTLEtBQUssR0FBTCxDQUExQjtBQUNBLFFBQUksUUFBUSxJQUFSLEVBQWMsU0FBUyxLQUFLLElBQUwsQ0FBM0I7QUFDQSxRQUFJLFFBQVEsR0FBUixFQUFjLFNBQVMsS0FBSyxHQUFMLENBQTNCOztBQUlBLFdBQU8sSUFBSSxPQUFKLENBQVksVUFBQyxPQUFELEVBQVUsTUFBVixFQUFxQjtBQUN2QyxZQUFPLEdBQVAsRUFBWSxNQUFaLEVBQW9CLElBQXBCLENBQXlCLFVBQUMsSUFBRCxFQUFVO0FBQ2xDLGNBQVEsS0FBSyxJQUFMLENBQVIsQ0FEa0M7TUFBVixDQUF6QixDQUVHLEtBRkgsQ0FFUyxVQUFDLEdBQUQsRUFBUztBQUNqQixjQUFRLEdBQVIsQ0FBWSx3QkFBWixFQURpQjtBQUVqQixhQUFPLEdBQVAsRUFGaUI7TUFBVCxDQUZULENBRHVDO0tBQXJCLENBQW5CLENBZjJCOzs7O3NDQTBCVCxPQUFNLFFBQVE7OztBQUNoQyxXQUFPLElBQUksT0FBSixDQUFhLFVBQUMsT0FBRCxFQUFVLE1BQVYsRUFBcUI7QUFDeEMsU0FBSSxNQUFJLG1CQUFTLEdBQVQsQ0FBYSxLQUFiLEVBQW1CLGlCQUFuQixDQUFKLENBRG9DO0FBRXhDLGNBRndDO0FBR3hDLFdBQUssSUFBTCxDQUFVLEdBQVYsRUFBYyxJQUFJLE9BQUosQ0FBWSxVQUFaLEVBQXVCLEtBQXZCLENBQWQsRUFBNkMsTUFBN0MsRUFBcUQsSUFBckQsQ0FBMEQsVUFBQyxJQUFELEVBQVU7O0FBRW5FLGNBQVEsSUFBUjtNQUZ5RCxDQUExRCxDQUlHLEtBSkgsQ0FJUyxNQUpULEVBSHdDO0FBRzRCLEtBSGpELENBQXBCLENBRGdDOzs7O2lDQVluQixZQUFZOzs7QUFDekIsV0FBTyxJQUFJLE9BQUosQ0FBYSxVQUFDLE9BQUQsRUFBVSxNQUFWLEVBQXFCO0FBQ3hDLFlBQUssSUFBTCxDQUFVLEdBQVYsRUFBYyxtQkFBUyxHQUFULENBQWEsS0FBYixFQUFtQixhQUFuQixFQUFrQyxVQUFsQyxDQUFkLEVBQTZELEVBQTdELEVBQWlFLElBQWpFLENBQXNFLFVBQUMsSUFBRCxFQUFVO0FBQy9FLGNBQVEsSUFBUjtBQUQrRSxNQUFWLENBQXRFLENBRUcsS0FGSCxDQUVTLE1BRlQsRUFEd0M7S0FBckIsQ0FBcEIsQ0FEeUI7Ozs7U0F0RnJCOzs7QUFnR04sS0FBTSxZQUFVLElBQUksU0FBSixFQUFWOzttQkFFUyIsImZpbGUiOiJjb25uZWN0b3JcXGNvbm5lY3Rvci5qcyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBBeGlvcyBmcm9tICdheGlvcyc7XHJcbmltcG9ydCB7QVBJX0JBU0VfVVJMfSAgZnJvbSAnYXBwL2NvbnN0YW50cy9jb25zdGFudHMnO1xyXG5pbXBvcnQgU2V0dGluZ3MgZnJvbSAnYXBwL3V0aWwvc2V0dGluZ3MnO1xyXG5cclxuXHJcbmNvbnNvbGUubG9nKFNldHRpbmdzKTtcclxuXHJcbmNvbnN0IFBPU1Q9ICdQT1NUJztcclxuY29uc3QgR0VUPSAnR0VUJztcclxuY29uc3QgUFVUPSAnUFVUJztcclxuY29uc3QgREVMRVRFPSAnREVMRVRFJztcclxuXHJcbmNsYXNzIENvbm5lY3RvciB7XHJcblxyXG5cclxuXHRnZXQodXJsLCBwYXJhbXMgPSB7fSkge1xyXG5cdFx0cmV0dXJuIG5ldyBQcm9taXNlKFxyXG5cdFx0XHRmdW5jdGlvbihyZXNvbHZlLCByZWplY3QpIHsgLy8gKEEpXHJcblx0XHRcdFx0QXhpb3MuZ2V0KHVybCwge1xyXG5cdFx0XHRcdFx0cmVzcG9uc2VUeXBlOiAnanNvbicsXHJcblx0XHRcdFx0XHRwYXJhbXM6IHBhcmFtc1xyXG5cdFx0XHRcdH0pXHJcblx0XHRcdFx0LnRoZW4oZnVuY3Rpb24ocmVzcG9uc2UpIHtcclxuXHRcdFx0XHRcdHJlc29sdmUocmVzcG9uc2UpO1xyXG5cdFx0XHRcdH0pXHJcblx0XHRcdFx0LmNhdGNoKGZ1bmN0aW9uKHJlc3BvbnNlKSB7XHJcblx0XHRcdFx0XHRyZWplY3QocmVzcG9uc2UpO1xyXG5cdFx0XHRcdH0pO1xyXG5cdFx0XHR9KTtcclxuXHR9XHJcblxyXG5cdHB1dCh1cmwsIGJvZHkgPSB7fSkge1xyXG5cdFx0cmV0dXJuIG5ldyBQcm9taXNlKFxyXG5cdFx0XHRmdW5jdGlvbihyZXNvbHZlLCByZWplY3QpIHtcclxuXHRcdFx0XHRBeGlvcy5wdXQodXJsLCBib2R5KVxyXG5cdFx0XHRcdC50aGVuKGZ1bmN0aW9uKHJlc3BvbnNlKSB7XHJcblx0XHRcdFx0XHRyZXNvbHZlKHJlc3BvbnNlKTtcclxuXHRcdFx0XHR9KVxyXG5cdFx0XHRcdC5jYXRjaChmdW5jdGlvbihyZXNwb25zZSkge1xyXG5cdFx0XHRcdFx0cmVqZWN0KHJlc3BvbnNlKTtcclxuXHRcdFx0XHR9KTtcclxuXHRcdFx0fSk7XHJcblx0fVxyXG5cclxuXHJcblx0cG9zdCh1cmwsIGJvZHkgPSB7fSkge1xyXG5cdFx0XHJcblx0XHRyZXR1cm4gbmV3IFByb21pc2UoXHJcblx0XHRcdGZ1bmN0aW9uKHJlc29sdmUsIHJlamVjdCkge1xyXG5cdFx0XHRcdEF4aW9zLnB1dCh1cmwsIGJvZHkpXHJcblx0XHRcdFx0LnRoZW4oZnVuY3Rpb24ocmVzcG9uc2UpIHtcclxuXHRcdFx0XHRcdHJlc29sdmUocmVzcG9uc2UpO1xyXG5cdFx0XHRcdH0pXHJcblx0XHRcdFx0LmNhdGNoKGZ1bmN0aW9uKHJlc3BvbnNlKSB7XHJcblx0XHRcdFx0XHRyZWplY3QocmVzcG9uc2UpO1xyXG5cdFx0XHRcdH0pO1xyXG5cdFx0XHR9KTtcclxuXHR9XHJcblxyXG5cdC8qQSBtZXRob2Qgc2hvdWxkIGFsd2F5cyByZXR1cm4gYSBwcm9taXNlKi9cclxuXHRjYWxsKHZlcmIsZW5kcG9pbnQsIHBhcmFtcykge1xyXG5cdFx0XHJcblx0XHRcclxuXHRcdGxldCBhcGlSb290PVNldHRpbmdzLmdldCgnQVBJJyxBUElfQkFTRV9VUkwpO1xyXG5cdFx0XHJcblx0XHRsZXQgdXJsPWAke2FwaVJvb3R9JHtlbmRwb2ludH1gOyBcclxuXHJcblxyXG5cdFx0bGV0IGNhbGxlcjtcclxuXHRcdGlmICh2ZXJiID09IEdFVCkgY2FsbGVyID0gdGhpcy5nZXQ7XHJcblx0XHRpZiAodmVyYiA9PSBQT1NUKSBjYWxsZXIgPSB0aGlzLnBvc3Q7XHJcblx0XHRpZiAodmVyYiA9PSBQVVQgKSBjYWxsZXIgPSB0aGlzLnB1dDtcclxuXHJcblxyXG5cclxuXHRcdHJldHVybiBuZXcgUHJvbWlzZSgocmVzb2x2ZSwgcmVqZWN0KSA9PiB7XHJcblx0XHRcdGNhbGxlcih1cmwsIHBhcmFtcykudGhlbigoZGF0YSkgPT4ge1xyXG5cdFx0XHRcdHJlc29sdmUoZGF0YS5kYXRhKTtcclxuXHRcdFx0fSkuY2F0Y2goKGVycikgPT4ge1xyXG5cdFx0XHRcdGNvbnNvbGUubG9nKCdGYWlsZWQgbGFkaW5nIGFwaSBkYXRhJylcclxuXHRcdFx0XHRyZWplY3QoZXJyKTtcclxuXHRcdFx0fSlcclxuXHRcdH0pXHJcblx0fVxyXG5cclxuXHQvKiovXHJcblx0Z2V0UHJvamVjdHNHZW9Kc29uKGxldmVsLHBhcmFtcykge1xyXG5cdFx0cmV0dXJuIG5ldyBQcm9taXNlKCAocmVzb2x2ZSwgcmVqZWN0KSA9PiB7XHJcblx0XHRcdGxldCB1cmw9U2V0dGluZ3MuZ2V0KCdBUEknLCdQUk9KRUNUX0dFT0pTT04nKTtcclxuXHRcdFx0ZGVidWdnZXI7XHJcblx0XHRcdHRoaXMuY2FsbChHRVQsdXJsLnJlcGxhY2UoJyR7bGV2ZWx9JyxsZXZlbCksIHBhcmFtcykudGhlbigoZGF0YSkgPT4ge1xyXG5cdFx0XHRcdC8qYXBwbHkgYW55IGRhdGEgdHJhbnNmb3JtYXRpb24qL1xyXG5cdFx0XHRcdHJlc29sdmUoZGF0YSk7IC8vLy9yZXNvbHZlIHdpdGggb3JpZ2luYWwgZGF0YSBvciBwZXJmb3JtIGFueSBkYXRhIHRyYW5zZm9ybWF0aW9uIG5lZWRlZFxyXG5cdFx0XHRcclxuXHRcdFx0fSkuY2F0Y2gocmVqZWN0KVxyXG5cdFx0fSk7XHJcblx0fVxyXG5cclxuXHRnZXRGaWx0ZXJMaXN0KGZpbHRlclR5cGUpIHtcclxuXHRcdHJldHVybiBuZXcgUHJvbWlzZSggKHJlc29sdmUsIHJlamVjdCkgPT4ge1xyXG5cdFx0XHR0aGlzLmNhbGwoR0VULFNldHRpbmdzLmdldCgnQVBJJywnRklMVEVSX0xJU1QnKVtmaWx0ZXJUeXBlXSwge30pLnRoZW4oKGRhdGEpID0+IHtcclxuXHRcdFx0XHRyZXNvbHZlKGRhdGEpOyAvLy8vcmVzb2x2ZSB3aXRoIG9yaWdpbmFsIGRhdGEgb3IgcGVyZm9ybSBhbnkgZGF0YSB0cmFuc2Zvcm1hdGlvbiBuZWVkZWRcdFx0XHRcclxuXHRcdFx0fSkuY2F0Y2gocmVqZWN0KVxyXG5cdFx0fSk7XHJcblx0fVxyXG59XHJcblxyXG5cclxuY29uc3QgY29ubmVjdG9yPW5ldyBDb25uZWN0b3IoKTtcclxuXHJcbmV4cG9ydCBkZWZhdWx0IGNvbm5lY3RvcjsiXX0=