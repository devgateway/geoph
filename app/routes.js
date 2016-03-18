define(['exports', 'react', 'react-router', 'app/components/layout/root', 'app/components/layout/landing'], function (exports, _react, _reactRouter, _root, _landing) {
		'use strict';

		Object.defineProperty(exports, "__esModule", {
				value: true
		});

		var _react2 = _interopRequireDefault(_react);

		var _root2 = _interopRequireDefault(_root);

		var _landing2 = _interopRequireDefault(_landing);

		function _interopRequireDefault(obj) {
				return obj && obj.__esModule ? obj : {
						default: obj
				};
		}

		exports.default = _react2.default.createElement(
				_reactRouter.Route,
				{ path: '/', component: _root2.default },
				_react2.default.createElement(_reactRouter.IndexRoute, { component: _landing2.default })
		);
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInJvdXRlcy5qcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7Ozs7Ozs7Ozs7Ozs7Ozs7O29CQVFHOztNQUFPLE1BQUssR0FBTCxFQUFTLDJCQUFoQjtJQUNBLHlEQUFZLDhCQUFaLENBREEiLCJmaWxlIjoicm91dGVzLmpzIiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IHsgUm91dGUsIEluZGV4Um91dGUgfSBmcm9tICdyZWFjdC1yb3V0ZXInO1xyXG4vKiBjb250YWluZXJzICovXHJcbmltcG9ydCBBcHAgZnJvbSAnYXBwL2NvbXBvbmVudHMvbGF5b3V0L3Jvb3QnO1xyXG5cclxuaW1wb3J0IExhbmRpbmcgZnJvbSAnYXBwL2NvbXBvbmVudHMvbGF5b3V0L2xhbmRpbmcnO1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgKFxyXG4gIFx0PFJvdXRlIHBhdGg9XCIvXCIgY29tcG9uZW50PXtBcHB9PlxyXG5cdCAgPEluZGV4Um91dGUgY29tcG9uZW50PXtMYW5kaW5nfSAvPlxyXG5cdDwvUm91dGU+XHJcbik7Il19