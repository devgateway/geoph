define(['exports', 'react', 'react/react-dom', 'dojo/domReady!'], function (exports, _react, _reactDom, _domReady) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _react2 = _interopRequireDefault(_react);

	var _reactDom2 = _interopRequireDefault(_reactDom);

	var _domReady2 = _interopRequireDefault(_domReady);

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

	var Layer = function (_React$Component) {
		_inherits(Layer, _React$Component);

		function Layer() {
			_classCallCheck(this, Layer);

			return _possibleConstructorReturn(this, Object.getPrototypeOf(Layer).apply(this, arguments));
		}

		_createClass(Layer, [{
			key: 'getClonedChildrenWithMap',
			value: function getClonedChildrenWithMap(extra) {
				var _props = this.props;
				var children = _props.children;
				var map = _props.map;

				var props = Object.assign({ map: map }, extra);

				return _react2.default.Children.map(children, function (child) {
					return child ? _react2.default.cloneElement(child, props) : null;
				});
			}
		}, {
			key: 'renderChildrenWithProps',
			value: function renderChildrenWithProps(props) {
				var children = this.getClonedChildrenWithMap(props);
				return _react2.default.createElement(
					'div',
					{ style: { display: 'none' } },
					children
				);
			}
		}, {
			key: 'render',
			value: function render() {
				return this.renderChildrenWithProps({ layer: this.layer });
			}
		}]);

		return Layer;
	}(_react2.default.Component);

	exports.default = Layer;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmlcXGxheWVyc1xcbGF5ZXIuanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7S0FJTTs7Ozs7Ozs7Ozs7NENBRW9CLE9BQU87aUJBQ0wsS0FBSyxLQUFMLENBREs7UUFDdkIsMkJBRHVCO1FBQ2IsaUJBRGE7O0FBRS9CLFFBQU0sUUFBUSxPQUFPLE1BQVAsQ0FBYyxFQUFDLFFBQUQsRUFBZCxFQUFxQixLQUFyQixDQUFSLENBRnlCOztBQUkvQixXQUFPLGdCQUFNLFFBQU4sQ0FBZSxHQUFmLENBQW1CLFFBQW5CLEVBQTZCLGlCQUFTO0FBQzVDLFlBQU8sUUFBUSxnQkFBTSxZQUFOLENBQW1CLEtBQW5CLEVBQTBCLEtBQTFCLENBQVIsR0FBMkMsSUFBM0MsQ0FEcUM7S0FBVCxDQUFwQyxDQUorQjs7OzsyQ0FTUixPQUFPO0FBQzlCLFFBQU0sV0FBVSxLQUFLLHdCQUFMLENBQThCLEtBQTlCLENBQVYsQ0FEd0I7QUFFOUIsV0FBTzs7T0FBSyxPQUFPLEVBQUMsU0FBUyxNQUFULEVBQVIsRUFBTDtLQUFnQyxRQUFoQztLQUFQLENBRjhCOzs7OzRCQUt0QjtBQUNSLFdBQU8sS0FBSyx1QkFBTCxDQUE2QixFQUFDLE9BQU0sS0FBSyxLQUFMLEVBQXBDLENBQVAsQ0FEUTs7OztTQWhCSjtHQUFjLGdCQUFNLFNBQU47O21CQXNCTCIsImZpbGUiOiJjb21wb25lbnRzXFxlc3JpXFxsYXllcnNcXGxheWVyLmpzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCBSZWFjdERPTSBmcm9tICdyZWFjdC9yZWFjdC1kb20nO1xyXG5pbXBvcnQgZG9tUmVhZHkgZnJvbSBcImRvam8vZG9tUmVhZHkhXCI7XHJcblxyXG5jbGFzcyBMYXllciBleHRlbmRzIFJlYWN0LkNvbXBvbmVudHtcclxuXHJcblx0Z2V0Q2xvbmVkQ2hpbGRyZW5XaXRoTWFwKGV4dHJhKSB7XHJcblx0XHRjb25zdCB7IGNoaWxkcmVuLCBtYXAgfSA9IHRoaXMucHJvcHM7XHJcblx0XHRjb25zdCBwcm9wcyA9IE9iamVjdC5hc3NpZ24oe21hcH0sIGV4dHJhKTtcclxuXHJcblx0XHRyZXR1cm4gUmVhY3QuQ2hpbGRyZW4ubWFwKGNoaWxkcmVuLCBjaGlsZCA9PiB7XHJcblx0XHRcdHJldHVybiBjaGlsZCA/IFJlYWN0LmNsb25lRWxlbWVudChjaGlsZCwgcHJvcHMpIDogbnVsbDtcclxuXHRcdH0pO1xyXG5cdH1cclxuXHJcblx0cmVuZGVyQ2hpbGRyZW5XaXRoUHJvcHMocHJvcHMpIHtcclxuXHRcdGNvbnN0IGNoaWxkcmVuID10aGlzLmdldENsb25lZENoaWxkcmVuV2l0aE1hcChwcm9wcyk7XHJcblx0XHRyZXR1cm4gPGRpdiBzdHlsZT17e2Rpc3BsYXk6ICdub25lJ319PntjaGlsZHJlbn08L2Rpdj47XHJcblx0fVxyXG5cclxuXHRyZW5kZXIoKSB7XHJcblx0XHRyZXR1cm4gdGhpcy5yZW5kZXJDaGlsZHJlbldpdGhQcm9wcyh7bGF5ZXI6dGhpcy5sYXllcn0pO1xyXG5cdH1cclxuXHJcbn1cclxuXHJcbmV4cG9ydCBkZWZhdWx0IExheWVyO1xyXG4iXX0=