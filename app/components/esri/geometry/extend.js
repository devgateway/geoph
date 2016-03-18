define(['exports', 'react', 'react/react-dom', 'esri/geometry/Extent'], function (exports, _react, _reactDom, _Extent) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _react2 = _interopRequireDefault(_react);

	var _reactDom2 = _interopRequireDefault(_reactDom);

	var _Extent2 = _interopRequireDefault(_Extent);

	function _interopRequireDefault(obj) {
		return obj && obj.__esModule ? obj : {
			default: obj
		};
	}

	var extend = _react2.default.createClass({
		displayName: 'extend',
		componentWillMount: function componentWillMount() {
			var _props = this.props;
			var xmax = _props.xmax;
			var xmin = _props.xmin;
			var ymin = _props.ymin;
			var ymax = _props.ymax;
			var spatialReference = _props.spatialReference;

			this.extent = new _Extent2.default({ xmax: xmax, xmin: xmin, ymin: ymin, ymax: ymax, spatialReference: spatialReference });
			this.props.view.extent = this.extent;
		},


		render: function render() {
			return _react2.default.createElement('div', null);
		}

	});

	exports.default = extend;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmlcXGdlb21ldHJ5XFxleHRlbmQuanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFNQSxLQUFNLFNBQVEsZ0JBQU0sV0FBTixDQUFrQjs7QUFFL0Isb0RBQXFCO2dCQUMyQixLQUFLLEtBQUwsQ0FEM0I7T0FDYixtQkFEYTtPQUNSLG1CQURRO09BQ0gsbUJBREc7T0FDRSxtQkFERjtPQUNPLDJDQURQOztBQUVwQixRQUFLLE1BQUwsR0FBYSxxQkFBVyxFQUFDLFVBQUQsRUFBTSxVQUFOLEVBQVcsVUFBWCxFQUFnQixVQUFoQixFQUFxQixrQ0FBckIsRUFBWCxDQUFiLENBRm9CO0FBR3BCLFFBQUssS0FBTCxDQUFXLElBQVgsQ0FBZ0IsTUFBaEIsR0FBd0IsS0FBSyxNQUFMLENBSEo7R0FGVTs7O0FBVy9CLFVBQVEsa0JBQVc7QUFDbEIsVUFBUSwwQ0FBUixDQURrQjtHQUFYOztFQVhLLENBQVI7O21CQWlCUyIsImZpbGUiOiJjb21wb25lbnRzXFxlc3JpXFxnZW9tZXRyeVxcZXh0ZW5kLmpzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCBSZWFjdERPTSBmcm9tICdyZWFjdC9yZWFjdC1kb20nO1xyXG5pbXBvcnQgRXh0ZW50IGZyb20gXCJlc3JpL2dlb21ldHJ5L0V4dGVudFwiO1xyXG5cclxuXHJcblxyXG5jb25zdCBleHRlbmQ9IFJlYWN0LmNyZWF0ZUNsYXNzKHtcclxuXHJcblx0Y29tcG9uZW50V2lsbE1vdW50KCkge1xyXG5cdFx0Y29uc3Qge3htYXgseG1pbix5bWluLHltYXgsc3BhdGlhbFJlZmVyZW5jZX0gPSB0aGlzLnByb3BzO1xyXG5cdFx0dGhpcy5leHRlbnQ9IG5ldyBFeHRlbnQoe3htYXgseG1pbix5bWluLHltYXgsc3BhdGlhbFJlZmVyZW5jZX0pO1xyXG5cdFx0dGhpcy5wcm9wcy52aWV3LmV4dGVudCA9dGhpcy5leHRlbnQ7XHJcblxyXG5cdFx0XHJcblx0fSxcclxuXHJcblxyXG5cdHJlbmRlcjogZnVuY3Rpb24oKSB7ICBcclxuXHRcdHJldHVybiAoPGRpdi8+KTtcclxuXHR9XHJcblxyXG59KTtcclxuXHJcbmV4cG9ydCBkZWZhdWx0IGV4dGVuZDtcclxuIl19