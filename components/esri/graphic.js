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
			debugger;
			this.element = new _Graphic2.default(this.props);
			this.props.layer.add(this.element);
		},


		render: function render() {
			return null;
		}

	});

	exports.default = graphic;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmlcXGdyYXBoaWMuanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7OztBQVNBLEtBQU0sVUFBUyxnQkFBTSxXQUFOLENBQWtCOztBQUVoQyxvREFBcUI7QUFDcEIsWUFEb0I7QUFFcEIsUUFBSyxPQUFMLEdBQWUsc0JBQVksS0FBSyxLQUFMLENBQTNCLENBRm9CO0FBR3BCLFFBQUssS0FBTCxDQUFXLEtBQVgsQ0FBaUIsR0FBakIsQ0FBcUIsS0FBSyxPQUFMLENBQXJCLENBSG9CO0dBRlc7OztBQVFoQyxVQUFRLGtCQUFXO0FBQ2xCLFVBQU8sSUFBUCxDQURrQjtHQUFYOztFQVJNLENBQVQ7O21CQWNTIiwiZmlsZSI6ImNvbXBvbmVudHNcXGVzcmlcXGdyYXBoaWMuanN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IFJlYWN0RE9NIGZyb20gJ3JlYWN0L3JlYWN0LWRvbSc7XHJcblxyXG5pbXBvcnQgR3JhcGhpYyBmcm9tIFwiZXNyaS9HcmFwaGljXCI7XHJcblxyXG5cclxuaW1wb3J0IGRvbVJlYWR5IGZyb20gXCJkb2pvL2RvbVJlYWR5IVwiO1xyXG5cclxuXHJcbmNvbnN0IGdyYXBoaWM9IFJlYWN0LmNyZWF0ZUNsYXNzKHtcclxuXHJcblx0Y29tcG9uZW50V2lsbE1vdW50KCkge1xyXG5cdFx0ZGVidWdnZXI7XHJcblx0XHR0aGlzLmVsZW1lbnQgPSBuZXcgR3JhcGhpYyh0aGlzLnByb3BzKTtcclxuXHRcdHRoaXMucHJvcHMubGF5ZXIuYWRkKHRoaXMuZWxlbWVudCk7XHJcblx0fSxcclxuXHJcblx0cmVuZGVyOiBmdW5jdGlvbigpIHsgIFxyXG5cdFx0cmV0dXJuIG51bGw7XHJcblx0fVxyXG5cclxufSk7XHJcblxyXG5leHBvcnQgZGVmYXVsdCBncmFwaGljO1xyXG4iXX0=