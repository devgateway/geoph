define(['exports', 'react', 'react/react-dom', 'esri/geometry/Point'], function (exports, _react, _reactDom, _Point) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _react2 = _interopRequireDefault(_react);

	var _reactDom2 = _interopRequireDefault(_reactDom);

	var _Point2 = _interopRequireDefault(_Point);

	function _interopRequireDefault(obj) {
		return obj && obj.__esModule ? obj : {
			default: obj
		};
	}

	var point = _react2.default.createClass({
		displayName: 'point',
		componentWillMount: function componentWillMount() {
			this.element = new _Point2.default({
				longitude: this.props.longitude,
				latitude: this.props.latitude
			});
		},


		render: function render() {
			return _react2.default.createElement('div', null);
		}

	});

	exports.default = point;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmlcXGdlb21ldHJ5XFxwb2ludC5qc3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7Ozs7Ozs7OztBQU1BLEtBQU0sUUFBTyxnQkFBTSxXQUFOLENBQWtCOztBQUU5QixvREFBcUI7QUFDcEIsUUFBSyxPQUFMLEdBQWUsb0JBQVU7QUFDbkIsZUFBVyxLQUFLLEtBQUwsQ0FBVyxTQUFYO0FBQ1gsY0FBVSxLQUFLLEtBQUwsQ0FBVyxRQUFYO0lBRkQsQ0FBZixDQURvQjtHQUZTOzs7QUFVOUIsVUFBUSxrQkFBVztBQUNsQixVQUFRLDBDQUFSLENBRGtCO0dBQVg7O0VBVkksQ0FBUDs7bUJBZ0JTIiwiZmlsZSI6ImNvbXBvbmVudHNcXGVzcmlcXGdlb21ldHJ5XFxwb2ludC5qc3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgUmVhY3RET00gZnJvbSAncmVhY3QvcmVhY3QtZG9tJztcclxuaW1wb3J0IFBvaW50IGZyb20gXCJlc3JpL2dlb21ldHJ5L1BvaW50XCI7XHJcblxyXG5cclxuXHJcbmNvbnN0IHBvaW50PSBSZWFjdC5jcmVhdGVDbGFzcyh7XHJcblxyXG5cdGNvbXBvbmVudFdpbGxNb3VudCgpIHtcclxuXHRcdHRoaXMuZWxlbWVudCA9IG5ldyBQb2ludCh7XHJcbiAgICAgICAgbG9uZ2l0dWRlOiB0aGlzLnByb3BzLmxvbmdpdHVkZSxcclxuICAgICAgICBsYXRpdHVkZTogdGhpcy5wcm9wcy5sYXRpdHVkZVxyXG4gICAgICB9KTtcclxuXHJcblx0fSxcclxuXHJcblx0cmVuZGVyOiBmdW5jdGlvbigpIHsgIFxyXG5cdFx0cmV0dXJuICg8ZGl2PjwvZGl2Pik7XHJcblx0fVxyXG5cclxufSk7XHJcblxyXG5leHBvcnQgZGVmYXVsdCBwb2ludDtcclxuIl19