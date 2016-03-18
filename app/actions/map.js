define(['exports', 'app/constants/constants', 'app/connector/connector.js'], function (exports, _constants, _connector) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});
	exports.loadProjects = undefined;

	var _connector2 = _interopRequireDefault(_connector);

	function _interopRequireDefault(obj) {
		return obj && obj.__esModule ? obj : {
			default: obj
		};
	}

	var loadProjectsCompleted = function loadProjectsCompleted(data) {
		return {
			type: _constants.LOAD_PROJECT_GEOJSON_SUCCESS,
			data: data
		};
	};

	var loadProjectsFailed = function loadProjectsFailed(error) {
		return {
			type: _constants.LOAD_PEOJECT_GEOJSON_FAILED,
			error: error
		};
	};

	var loadProjects = exports.loadProjects = function loadProjects(level, params) {
		return function (dispatch, getState) {
			_connector2.default.getProjectsGeoJson(level, params).then(function (data) {

				dispatch(loadProjectsCompleted(data));
			}).catch(function (err) {
				debugger;
				console.log(err);
				dispatch(loadProjectsFailed(err));
			});
		};
	};
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImFjdGlvbnNcXG1hcC5qcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7Ozs7Ozs7Ozs7Ozs7O0FBSUEsS0FBTSx3QkFBc0IsU0FBdEIscUJBQXNCLENBQUMsSUFBRCxFQUFRO0FBQ25DLFNBQU87QUFDTixnREFETTtBQUVOLFNBQUssSUFBTDtHQUZELENBRG1DO0VBQVI7O0FBTzVCLEtBQU0scUJBQW1CLFNBQW5CLGtCQUFtQixDQUFDLEtBQUQsRUFBUztBQUNsQyxTQUFPO0FBQ0wsK0NBREs7QUFFTCxlQUZLO0dBQVAsQ0FEa0M7RUFBVDs7QUFTbEIsS0FBTSxzQ0FBZSxTQUFmLFlBQWUsQ0FBQyxLQUFELEVBQU8sTUFBUCxFQUFrQjtBQUM3QyxTQUFPLFVBQUMsUUFBRCxFQUFXLFFBQVgsRUFBdUI7QUFDN0IsdUJBQVUsa0JBQVYsQ0FBNkIsS0FBN0IsRUFBbUMsTUFBbkMsRUFDQyxJQURELENBQ00sVUFBQyxJQUFELEVBQVE7O0FBRVosYUFBUyxzQkFBc0IsSUFBdEIsQ0FBVCxFQUZZO0lBQVIsQ0FETixDQUlHLEtBSkgsQ0FJUyxVQUFDLEdBQUQsRUFBTztBQUNkLGFBRGM7QUFFZCxZQUFRLEdBQVIsQ0FBWSxHQUFaLEVBRmM7QUFHZCxhQUFTLG1CQUFtQixHQUFuQixDQUFULEVBSGM7SUFBUCxDQUpULENBRDZCO0dBQXZCLENBRHNDO0VBQWxCIiwiZmlsZSI6ImFjdGlvbnNcXG1hcC5qcyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7TE9BRF9QUk9KRUNUX0dFT0pTT05fU1VDQ0VTUyxMT0FEX1BFT0pFQ1RfR0VPSlNPTl9GQUlMRUQsQ0hBTkdFX0xBWUVSX0xFVkVMfSAgZnJvbSAnYXBwL2NvbnN0YW50cy9jb25zdGFudHMnO1xyXG5pbXBvcnQgQ29ubmVjdG9yIGZyb20gJ2FwcC9jb25uZWN0b3IvY29ubmVjdG9yLmpzJztcclxuXHJcblxyXG5jb25zdCBsb2FkUHJvamVjdHNDb21wbGV0ZWQ9KGRhdGEpPT57XHJcblx0cmV0dXJuIHtcclxuXHRcdHR5cGU6TE9BRF9QUk9KRUNUX0dFT0pTT05fU1VDQ0VTUyxcclxuXHRcdGRhdGE6ZGF0YVxyXG5cdH1cclxufVxyXG5cclxuY29uc3QgbG9hZFByb2plY3RzRmFpbGVkPShlcnJvcik9PntcclxucmV0dXJuIHtcclxuXHRcdHR5cGU6TE9BRF9QRU9KRUNUX0dFT0pTT05fRkFJTEVELFxyXG5cdFx0ZXJyb3JcclxuXHR9XHRcclxufVxyXG5cclxuXHJcblxyXG5leHBvcnQgY29uc3QgbG9hZFByb2plY3RzID0gKGxldmVsLHBhcmFtcykgPT4ge1xyXG5cdHJldHVybiAoZGlzcGF0Y2gsIGdldFN0YXRlKSA9PntcclxuXHRcdENvbm5lY3Rvci5nZXRQcm9qZWN0c0dlb0pzb24obGV2ZWwscGFyYW1zKVxyXG5cdFx0LnRoZW4oKGRhdGEpPT57XHJcblx0XHRcdFx0XHJcblx0XHRcdFx0ZGlzcGF0Y2gobG9hZFByb2plY3RzQ29tcGxldGVkKGRhdGEpKX1cclxuXHRcdFx0KS5jYXRjaCgoZXJyKT0+eyBcclxuXHRcdFx0XHRkZWJ1Z2dlcjtcclxuXHRcdFx0XHRjb25zb2xlLmxvZyhlcnIpO1xyXG5cdFx0XHRcdGRpc3BhdGNoKGxvYWRQcm9qZWN0c0ZhaWxlZChlcnIpKTtcclxuXHRcdFx0fSk7XHJcblx0XHR9IFxyXG5cclxuXHR9XHJcblxyXG4iXX0=