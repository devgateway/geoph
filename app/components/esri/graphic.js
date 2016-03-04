define(['exports', 'react', 'react/react-dom', 'esri/Graphic', 'dojo/domReady!'], function (exports, _react, _reactDom, _Graphic, _domReady) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _react2 = _interopRequireDefault(_react);

	var _reactDom2 = _interopRequireDefault(_reactDom);

	var _Graphic2 = _interopRequireDefault(_Graphic);

	var _domReady2 = _interopRequireDefault(_domReady);

	function _interopRequireDefault(obj) {
		return obj && obj.__esModule ? obj : {
			default: obj
		};
	}

	var graphic = _react2.default.createClass({
		displayName: 'graphic',
		componentWillMount: function componentWillMount() {

			this.element = new _Graphic2.default(this.props);
			this.props.layer.add(this.element);
		},


		render: function render() {
			return null;
		}

	});

	exports.default = graphic;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmlcXGdyYXBoaWMuanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7OztBQVNBLEtBQU0sVUFBUyxnQkFBTSxXQUFOLENBQWtCOztBQUVoQyxvREFBcUI7O0FBRXBCLFFBQUssT0FBTCxHQUFlLHNCQUFZLEtBQUssS0FBTCxDQUEzQixDQUZvQjtBQUdwQixRQUFLLEtBQUwsQ0FBVyxLQUFYLENBQWlCLEdBQWpCLENBQXFCLEtBQUssT0FBTCxDQUFyQixDQUhvQjtHQUZXOzs7QUFRaEMsVUFBUSxrQkFBVztBQUNsQixVQUFPLElBQVAsQ0FEa0I7R0FBWDs7RUFSTSxDQUFUOzttQkFjUyIsImZpbGUiOiJjb21wb25lbnRzXFxlc3JpXFxncmFwaGljLmpzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCBSZWFjdERPTSBmcm9tICdyZWFjdC9yZWFjdC1kb20nO1xyXG5cclxuaW1wb3J0IEdyYXBoaWMgZnJvbSBcImVzcmkvR3JhcGhpY1wiO1xyXG5cclxuXHJcbmltcG9ydCBkb21SZWFkeSBmcm9tIFwiZG9qby9kb21SZWFkeSFcIjtcclxuXHJcblxyXG5jb25zdCBncmFwaGljPSBSZWFjdC5jcmVhdGVDbGFzcyh7XHJcblxyXG5cdGNvbXBvbmVudFdpbGxNb3VudCgpIHtcclxuXHRcdFxyXG5cdFx0dGhpcy5lbGVtZW50ID0gbmV3IEdyYXBoaWModGhpcy5wcm9wcyk7XHJcblx0XHR0aGlzLnByb3BzLmxheWVyLmFkZCh0aGlzLmVsZW1lbnQpO1xyXG5cdH0sXHJcblxyXG5cdHJlbmRlcjogZnVuY3Rpb24oKSB7ICBcclxuXHRcdHJldHVybiBudWxsO1xyXG5cdH1cclxuXHJcbn0pO1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgZ3JhcGhpYztcclxuIl19