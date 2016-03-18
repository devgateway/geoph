define(['exports', 'react', 'react/react-dom', 'esri/views/SceneView'], function (exports, _react, _reactDom, _SceneView) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _react2 = _interopRequireDefault(_react);

	var _reactDom2 = _interopRequireDefault(_reactDom);

	var _SceneView2 = _interopRequireDefault(_SceneView);

	function _interopRequireDefault(obj) {
		return obj && obj.__esModule ? obj : {
			default: obj
		};
	}

	var _extends = Object.assign || function (target) {
		for (var i = 1; i < arguments.length; i++) {
			var source = arguments[i];

			for (var key in source) {
				if (Object.prototype.hasOwnProperty.call(source, key)) {
					target[key] = source[key];
				}
			}
		}

		return target;
	};

	var SceneViewComponent = _react2.default.createClass({
		displayName: 'SceneViewComponent',
		componentDidMount: function componentDidMount() {
			var node = _reactDom2.default.findDOMNode(this.refs.mapView);
			this.view = new _SceneView2.default(_extends({ container: node }, this.props));
		},
		render: function render() {
			var children = this.view ? _react2.default.Children.map(this.props.children, function (child) {
				return child ? _react2.default.cloneElement(child, { view: view }) : null;
			}) : null;

			return _react2.default.createElement(
				'div',
				{ className: 'mapView', ref: 'mapView' },
				children
			);
		}
	});

	exports.default = SceneViewComponent;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmlcXHNjZW5lVmlldy5qc3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7O0FBTUEsS0FBTSxxQkFBcUIsZ0JBQU0sV0FBTixDQUFrQjs7QUFFNUMsa0RBQW9CO0FBQ25CLE9BQUksT0FBTyxtQkFBUyxXQUFULENBQXFCLEtBQUssSUFBTCxDQUFVLE9BQVYsQ0FBNUIsQ0FEZTtBQUVuQixRQUFLLElBQUwsR0FBVyxtQ0FBZSxXQUFXLElBQVgsSUFBbUIsS0FBSyxLQUFMLENBQWxDLENBQVgsQ0FGbUI7R0FGd0I7QUFTNUMsNEJBQVE7QUFDUCxPQUFNLFdBQVcsS0FBSyxJQUFMLEdBQVksZ0JBQU0sUUFBTixDQUFlLEdBQWYsQ0FBbUIsS0FBSyxLQUFMLENBQVcsUUFBWCxFQUFxQixpQkFBUztBQUFDLFdBQU8sUUFBUSxnQkFBTSxZQUFOLENBQW1CLEtBQW5CLEVBQTBCLEVBQUMsVUFBRCxFQUExQixDQUFSLEdBQTRDLElBQTVDLENBQVI7SUFBVCxDQUFwRCxHQUEySCxJQUEzSCxDQURWOztBQUdQLFVBQVE7O01BQUssV0FBVSxTQUFWLEVBQW9CLEtBQUksU0FBSixFQUF6QjtJQUF3QyxRQUF4QztJQUFSLENBSE87R0FUb0M7RUFBbEIsQ0FBckI7O21CQW1CUyIsImZpbGUiOiJjb21wb25lbnRzXFxlc3JpXFxzY2VuZVZpZXcuanN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IFJlYWN0RE9NIGZyb20gJ3JlYWN0L3JlYWN0LWRvbSc7XHJcbmltcG9ydCBTY2VuZVZpZXcgZnJvbSAnZXNyaS92aWV3cy9TY2VuZVZpZXcnO1xyXG5cclxuXHJcblxyXG5jb25zdCBTY2VuZVZpZXdDb21wb25lbnQgPSBSZWFjdC5jcmVhdGVDbGFzcyh7XHJcblxyXG5cdGNvbXBvbmVudERpZE1vdW50KCkge1xyXG5cdFx0dmFyIG5vZGUgPSBSZWFjdERPTS5maW5kRE9NTm9kZSh0aGlzLnJlZnMubWFwVmlldyk7XHJcblx0XHR0aGlzLnZpZXc9IG5ldyBTY2VuZVZpZXcoe2NvbnRhaW5lcjogbm9kZSwuLi50aGlzLnByb3BzfSk7XHJcblx0XHRcclxuXHR9LFxyXG5cclxuXHJcblx0cmVuZGVyKCl7XHJcblx0XHRjb25zdCBjaGlsZHJlbiA9IHRoaXMudmlldyA/IFJlYWN0LkNoaWxkcmVuLm1hcCh0aGlzLnByb3BzLmNoaWxkcmVuLCBjaGlsZCA9PiB7cmV0dXJuIGNoaWxkID8gUmVhY3QuY2xvbmVFbGVtZW50KGNoaWxkLCB7dmlld30pIDogbnVsbDt9KSA6IG51bGw7XHJcblxyXG5cdFx0cmV0dXJuICg8ZGl2IGNsYXNzTmFtZT0nbWFwVmlldycgcmVmPSdtYXBWaWV3Jz57Y2hpbGRyZW59PC9kaXY+KVxyXG5cdH1cclxuXHJcbn0pO1xyXG5cclxuXHJcblxyXG5leHBvcnQgZGVmYXVsdCBTY2VuZVZpZXdDb21wb25lbnQ7XHJcbiJdfQ==