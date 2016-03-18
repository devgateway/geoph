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
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmlcXGdyYXBoaWMuanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7OztBQVNBLEtBQU0sVUFBUyxnQkFBTSxXQUFOLENBQWtCOztBQUVoQyxvREFBcUI7QUFDcEIsUUFBSyxPQUFMLEdBQWUsc0JBQVksS0FBSyxLQUFMLENBQTNCLENBRG9CO0FBRXBCLFFBQUssS0FBTCxDQUFXLEtBQVgsQ0FBaUIsR0FBakIsQ0FBcUIsS0FBSyxPQUFMLENBQXJCLENBRm9CO0dBRlc7OztBQU9oQyxVQUFRLGtCQUFXO0FBQ2xCLFVBQU8sSUFBUCxDQURrQjtHQUFYOztFQVBNLENBQVQ7O21CQWFTIiwiZmlsZSI6ImNvbXBvbmVudHNcXGVzcmlcXGdyYXBoaWMuanN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IFJlYWN0RE9NIGZyb20gJ3JlYWN0L3JlYWN0LWRvbSc7XHJcblxyXG5pbXBvcnQgR3JhcGhpYyBmcm9tIFwiZXNyaS9HcmFwaGljXCI7XHJcblxyXG5cclxuaW1wb3J0IGRvbVJlYWR5IGZyb20gXCJkb2pvL2RvbVJlYWR5IVwiO1xyXG5cclxuXHJcbmNvbnN0IGdyYXBoaWM9IFJlYWN0LmNyZWF0ZUNsYXNzKHtcclxuXHJcblx0Y29tcG9uZW50V2lsbE1vdW50KCkge1xyXG5cdFx0dGhpcy5lbGVtZW50ID0gbmV3IEdyYXBoaWModGhpcy5wcm9wcyk7XHJcblx0XHR0aGlzLnByb3BzLmxheWVyLmFkZCh0aGlzLmVsZW1lbnQpO1xyXG5cdH0sXHJcblxyXG5cdHJlbmRlcjogZnVuY3Rpb24oKSB7ICBcclxuXHRcdHJldHVybiBudWxsO1xyXG5cdH1cclxuXHJcbn0pO1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgZ3JhcGhpYztcclxuIl19