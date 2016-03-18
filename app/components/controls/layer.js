define(['exports', 'react', 'react-redux', 'app/actions/map', 'app/constants/constants'], function (exports, _react, _reactRedux, _map, _constants) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});
	exports.LayerControl = undefined;

	var _react2 = _interopRequireDefault(_react);

	var Constants = _interopRequireWildcard(_constants);

	function _interopRequireWildcard(obj) {
		if (obj && obj.__esModule) {
			return obj;
		} else {
			var newObj = {};

			if (obj != null) {
				for (var key in obj) {
					if (Object.prototype.hasOwnProperty.call(obj, key)) newObj[key] = obj[key];
				}
			}

			newObj.default = obj;
			return newObj;
		}
	}

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

	function _possibleConstructorReturn(self, call) {
		if (!self) {
			throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
		}

		return call && (typeof call === "object" || typeof call === "function") ? call : self;
	}

	function _inherits(subClass, superClass) {
		if (typeof superClass !== "function" && superClass !== null) {
			throw new TypeError("Super expression must either be null or a function, not " + typeof superClass);
		}

		subClass.prototype = Object.create(superClass && superClass.prototype, {
			constructor: {
				value: subClass,
				enumerable: false,
				writable: true,
				configurable: true
			}
		});
		if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass;
	}

	var Component = function (_React$Component) {
		_inherits(Component, _React$Component);

		function Component() {
			_classCallCheck(this, Component);

			return _possibleConstructorReturn(this, Object.getPrototypeOf(Component).apply(this, arguments));
		}

		_createClass(Component, [{
			key: 'componentDidMount',
			value: function componentDidMount() {
				this.props.onLoadProjects('region');
			}
		}, {
			key: 'onChangeLevel',
			value: function onChangeLevel(e) {
				debugger;
				this.props.onLoadProjects(e.target.value);
			}
		}, {
			key: 'render',
			value: function render() {
				return _react2.default.createElement(
					'div',
					null,
					_react2.default.createElement(
						'select',
						{ name: 'level', onChange: this.onChangeLevel.bind(this) },
						_react2.default.createElement(
							'option',
							{ value: 'region' },
							'Region'
						),
						_react2.default.createElement(
							'option',
							{ value: 'department' },
							'Department'
						),
						_react2.default.createElement(
							'option',
							{ value: 'municipalities' },
							'Municipalities'
						)
					)
				);
			}
		}]);

		return Component;
	}(_react2.default.Component);

	var stateToProps = function stateToProps(state, props) {
		return state.map;
	};

	var dispatchToProps = function dispatchToProps(dispatch, ownProps) {
		return {
			onLoadProjects: function onLoadProjects(level) {
				dispatch((0, _map.loadProjects)(level));
			}
		};
	};
	/*Connect map component to redux state*/
	var LayerControl = (0, _reactRedux.connect)(stateToProps, dispatchToProps)(Component);

	exports.LayerControl = LayerControl;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGNvbnRyb2xzXFxsYXllci5qc3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7OztLQUdZOzs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7O0tBRU47Ozs7Ozs7Ozs7O3VDQUVjO0FBQ2xCLFNBQUssS0FBTCxDQUFXLGNBQVgsQ0FBMEIsUUFBMUIsRUFEa0I7Ozs7aUNBSUwsR0FBRTtBQUNmLGFBRGU7QUFFZixTQUFLLEtBQUwsQ0FBVyxjQUFYLENBQTBCLEVBQUUsTUFBRixDQUFTLEtBQVQsQ0FBMUIsQ0FGZTs7Ozs0QkFLUjtBQUNQLFdBQVE7OztLQUNQOztRQUFRLE1BQUssT0FBTCxFQUFhLFVBQVUsS0FBSyxhQUFMLENBQW1CLElBQW5CLENBQXdCLElBQXhCLENBQVYsRUFBckI7TUFDQTs7U0FBUSxPQUFNLFFBQU4sRUFBUjs7T0FEQTtNQUVBOztTQUFRLE9BQU0sWUFBTixFQUFSOztPQUZBO01BR0E7O1NBQVEsT0FBTSxnQkFBTixFQUFSOztPQUhBO01BRE87S0FBUixDQURPOzs7O1NBWEg7R0FBa0IsZ0JBQU0sU0FBTjs7QUF5QnhCLEtBQU0sZUFBZSxTQUFmLFlBQWUsQ0FBQyxLQUFELEVBQVEsS0FBUixFQUFrQjtBQUNyQyxTQUFPLE1BQU0sR0FBTixDQUQ4QjtFQUFsQjs7QUFLckIsS0FBTSxrQkFBa0IsU0FBbEIsZUFBa0IsQ0FBQyxRQUFELEVBQVcsUUFBWCxFQUF3QjtBQUM5QyxTQUFPO0FBQ0wsbUJBQWdCLHdCQUFDLEtBQUQsRUFBVztBQUN6QixhQUFTLHVCQUFhLEtBQWIsQ0FBVCxFQUR5QjtJQUFYO0dBRGxCLENBRDhDO0VBQXhCOztBQVF4QixLQUFNLGVBQWEseUJBQVEsWUFBUixFQUFxQixlQUFyQixFQUFzQyxTQUF0QyxDQUFiOztTQUdFIiwiZmlsZSI6ImNvbXBvbmVudHNcXGNvbnRyb2xzXFxsYXllci5qc3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgeyBjb25uZWN0IH0gZnJvbSAncmVhY3QtcmVkdXgnXHJcbmltcG9ydCB7bG9hZFByb2plY3RzfSBmcm9tICdhcHAvYWN0aW9ucy9tYXAnXHJcbmltcG9ydCAqIGFzIENvbnN0YW50cyBmcm9tICdhcHAvY29uc3RhbnRzL2NvbnN0YW50cyc7XHJcblxyXG5jbGFzcyBDb21wb25lbnQgZXh0ZW5kcyBSZWFjdC5Db21wb25lbnQge1xyXG5cdFxyXG5cdGNvbXBvbmVudERpZE1vdW50KCl7XHJcblx0XHR0aGlzLnByb3BzLm9uTG9hZFByb2plY3RzKCdyZWdpb24nKTtcclxuXHR9XHJcblxyXG5cdG9uQ2hhbmdlTGV2ZWwoZSl7XHJcblx0XHRkZWJ1Z2dlcjtcclxuXHRcdHRoaXMucHJvcHMub25Mb2FkUHJvamVjdHMoZS50YXJnZXQudmFsdWUpO1xyXG5cdH1cclxuXHJcblx0cmVuZGVyKCl7XHJcblx0XHRyZXR1cm4gKDxkaXY+XHJcblx0XHRcdDxzZWxlY3QgbmFtZT0nbGV2ZWwnIG9uQ2hhbmdlPXt0aGlzLm9uQ2hhbmdlTGV2ZWwuYmluZCh0aGlzKX0+XHJcblx0XHRcdDxvcHRpb24gdmFsdWU9J3JlZ2lvbic+UmVnaW9uPC9vcHRpb24+XHJcblx0XHRcdDxvcHRpb24gdmFsdWU9J2RlcGFydG1lbnQnPkRlcGFydG1lbnQ8L29wdGlvbj5cclxuXHRcdFx0PG9wdGlvbiB2YWx1ZT0nbXVuaWNpcGFsaXRpZXMnPk11bmljaXBhbGl0aWVzPC9vcHRpb24+XHJcblx0XHRcdDwvc2VsZWN0PlxyXG5cdFx0XHQ8L2Rpdj4pXHJcblx0fVxyXG59XHJcblxyXG5cclxuXHJcblxyXG5jb25zdCBzdGF0ZVRvUHJvcHMgPSAoc3RhdGUsIHByb3BzKSA9PiB7XHJcbiAgcmV0dXJuIHN0YXRlLm1hcCAgXHJcbn1cclxuXHJcblxyXG5jb25zdCBkaXNwYXRjaFRvUHJvcHMgPSAoZGlzcGF0Y2gsIG93blByb3BzKSA9PiB7XHJcbiAgcmV0dXJuIHtcclxuICAgIG9uTG9hZFByb2plY3RzOiAobGV2ZWwpID0+IHtcclxuICAgICAgZGlzcGF0Y2gobG9hZFByb2plY3RzKGxldmVsKSk7XHJcbiAgICB9LFxyXG4gIH1cclxufVxyXG4vKkNvbm5lY3QgbWFwIGNvbXBvbmVudCB0byByZWR1eCBzdGF0ZSovXHJcbmNvbnN0IExheWVyQ29udHJvbD1jb25uZWN0KHN0YXRlVG9Qcm9wcyxkaXNwYXRjaFRvUHJvcHMpKENvbXBvbmVudCk7XHJcblxyXG5cclxuZXhwb3J0IHtMYXllckNvbnRyb2x9O1xyXG4gIl19