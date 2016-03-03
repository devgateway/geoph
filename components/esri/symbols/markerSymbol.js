define(['exports', 'react', 'react/react-dom', 'esri/symbols/SimpleMarkerSymbol'], function (exports, _react, _reactDom, _SimpleMarkerSymbol) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _react2 = _interopRequireDefault(_react);

	var _reactDom2 = _interopRequireDefault(_reactDom);

	var _SimpleMarkerSymbol2 = _interopRequireDefault(_SimpleMarkerSymbol);

	function _interopRequireDefault(obj) {
		return obj && obj.__esModule ? obj : {
			default: obj
		};
	}

	var simpleMarkerSymbol = _react2.default.createClass({
		displayName: 'simpleMarkerSymbol',
		componentWillMount: function componentWillMount() {
			this.element = new _SimpleMarkerSymbol2.default(this.props);
		},


		render: function render() {
			return _react2.default.createElement('div', null);
		}

	});

	exports.default = simpleMarkerSymbol;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmlcXHN5bWJvbHNcXG1hcmtlclN5bWJvbC5qc3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7Ozs7Ozs7OztBQU1BLEtBQU0scUJBQW9CLGdCQUFNLFdBQU4sQ0FBa0I7O0FBRTNDLG9EQUFxQjtBQUNwQixRQUFLLE9BQUwsR0FBZSxpQ0FBdUIsS0FBSyxLQUFMLENBQXRDLENBRG9CO0dBRnNCOzs7QUFNM0MsVUFBUSxrQkFBVztBQUNsQixVQUFRLDBDQUFSLENBRGtCO0dBQVg7O0VBTmlCLENBQXBCOzttQkFZUyIsImZpbGUiOiJjb21wb25lbnRzXFxlc3JpXFxzeW1ib2xzXFxtYXJrZXJTeW1ib2wuanN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IFJlYWN0RE9NIGZyb20gJ3JlYWN0L3JlYWN0LWRvbSc7XHJcbmltcG9ydCBTaW1wbGVNYXJrZXJTeW1ib2wgZnJvbSBcImVzcmkvc3ltYm9scy9TaW1wbGVNYXJrZXJTeW1ib2xcIjtcclxuXHJcblxyXG5cclxuY29uc3Qgc2ltcGxlTWFya2VyU3ltYm9sPSBSZWFjdC5jcmVhdGVDbGFzcyh7XHJcblxyXG5cdGNvbXBvbmVudFdpbGxNb3VudCgpIHtcclxuXHRcdHRoaXMuZWxlbWVudCA9IG5ldyBTaW1wbGVNYXJrZXJTeW1ib2wodGhpcy5wcm9wcyk7XHJcblx0fSxcclxuXHJcblx0cmVuZGVyOiBmdW5jdGlvbigpIHsgIFxyXG5cdFx0cmV0dXJuICg8ZGl2PjwvZGl2Pik7XHJcblx0fVxyXG5cclxufSk7XHJcblxyXG5leHBvcnQgZGVmYXVsdCBzaW1wbGVNYXJrZXJTeW1ib2w7XHJcbiJdfQ==