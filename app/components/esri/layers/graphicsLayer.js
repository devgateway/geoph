define(['exports', 'react', 'react/react-dom', 'esri/layers/GraphicsLayer', 'app/components/esri/layers/Layer', 'esri/Graphic', 'dojo/domReady!'], function (exports, _react, _reactDom, _GraphicsLayer, _Layer2, _Graphic, _domReady) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _react2 = _interopRequireDefault(_react);

	var _reactDom2 = _interopRequireDefault(_reactDom);

	var _GraphicsLayer2 = _interopRequireDefault(_GraphicsLayer);

	var _Layer3 = _interopRequireDefault(_Layer2);

	var _Graphic2 = _interopRequireDefault(_Graphic);

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

	var GraphicsLayer = function (_Layer) {
		_inherits(GraphicsLayer, _Layer);

		function GraphicsLayer() {
			_classCallCheck(this, GraphicsLayer);

			return _possibleConstructorReturn(this, Object.getPrototypeOf(GraphicsLayer).apply(this, arguments));
		}

		_createClass(GraphicsLayer, [{
			key: 'componentWillMount',
			value: function componentWillMount() {
				this.layer = new _GraphicsLayer2.default();
				this.props.map.add(this.layer);
			}
		}]);

		return GraphicsLayer;
	}(_Layer3.default);

	exports.default = GraphicsLayer;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmlcXGxheWVyc1xcZ3JhcGhpY3NMYXllci5qc3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7OztLQVFNOzs7Ozs7Ozs7Ozt3Q0FDZ0I7QUFDcEIsU0FBSyxLQUFMLEdBQWEsNkJBQWIsQ0FEb0I7QUFFcEIsU0FBSyxLQUFMLENBQVcsR0FBWCxDQUFlLEdBQWYsQ0FBbUIsS0FBSyxLQUFMLENBQW5CLENBRm9COzs7O1NBRGhCOzs7bUJBUVMiLCJmaWxlIjoiY29tcG9uZW50c1xcZXNyaVxcbGF5ZXJzXFxncmFwaGljc0xheWVyLmpzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCBSZWFjdERPTSBmcm9tICdyZWFjdC9yZWFjdC1kb20nO1xyXG5pbXBvcnQgRXNyaUdyYXBoaWNzTGF5ZXIgZnJvbSAnZXNyaS9sYXllcnMvR3JhcGhpY3NMYXllcic7XHJcbmltcG9ydCBMYXllciBmcm9tICdhcHAvY29tcG9uZW50cy9lc3JpL2xheWVycy9MYXllcic7XHJcbmltcG9ydCBHcmFwaGljIGZyb20gXCJlc3JpL0dyYXBoaWNcIjtcclxuaW1wb3J0IGRvbVJlYWR5IGZyb20gXCJkb2pvL2RvbVJlYWR5IVwiO1xyXG5cclxuXHJcbmNsYXNzIEdyYXBoaWNzTGF5ZXIgZXh0ZW5kcyBMYXllcntcclxuXHRjb21wb25lbnRXaWxsTW91bnQoKSB7XHJcblx0XHR0aGlzLmxheWVyID0gbmV3IEVzcmlHcmFwaGljc0xheWVyKCk7XHJcblx0XHR0aGlzLnByb3BzLm1hcC5hZGQodGhpcy5sYXllcilcclxuXHR9XHJcblxyXG59XHJcblxyXG5leHBvcnQgZGVmYXVsdCBHcmFwaGljc0xheWVyO1xyXG4iXX0=