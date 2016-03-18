define(['exports', 'react', 'i18next'], function (exports, _react, _i18next) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _react2 = _interopRequireDefault(_react);

	var _i18next2 = _interopRequireDefault(_i18next);

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

	var Translator = function (_React$Component) {
		_inherits(Translator, _React$Component);

		function Translator() {
			_classCallCheck(this, Translator);

			return _possibleConstructorReturn(this, Object.getPrototypeOf(Translator).apply(this, arguments));
		}

		_createClass(Translator, [{
			key: 'render',
			value: function render() {
				var e = _i18next2.default;
				console.log(e.t(this.props.k, this.props.lan));
				return _react2.default.createElement(
					'span',
					{ className: this.props.className },
					_i18next2.default.t(this.props.k, this.props.lan)
				);
			}
		}]);

		return Translator;
	}(_react2.default.Component);

	exports.default = Translator;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGxhblxcdHJhbnNsYXRvci5qc3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7S0FHcUI7Ozs7Ozs7Ozs7OzRCQUNaO0FBQ1AsUUFBTSxxQkFBTixDQURPO0FBRVAsWUFBUSxHQUFSLENBQVksRUFBRSxDQUFGLENBQUksS0FBSyxLQUFMLENBQVcsQ0FBWCxFQUFhLEtBQUssS0FBTCxDQUFXLEdBQVgsQ0FBN0IsRUFGTztBQUdQLFdBQU87O09BQU0sV0FBVyxLQUFLLEtBQUwsQ0FBVyxTQUFYLEVBQWpCO0tBRUwsa0JBQVEsQ0FBUixDQUFVLEtBQUssS0FBTCxDQUFXLENBQVgsRUFBYSxLQUFLLEtBQUwsQ0FBVyxHQUFYLENBRmxCO0tBQVAsQ0FITzs7OztTQURZO0dBQW1CLGdCQUFNLFNBQU47O21CQUFuQiIsImZpbGUiOiJjb21wb25lbnRzXFxsYW5cXHRyYW5zbGF0b3IuanN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IGkxOG5leHQgZnJvbSAnaTE4bmV4dCc7XHJcblxyXG5leHBvcnQgZGVmYXVsdCBjbGFzcyBUcmFuc2xhdG9yIGV4dGVuZHMgUmVhY3QuQ29tcG9uZW50IHtcclxuXHRyZW5kZXIoKXtcclxuXHRcdGNvbnN0IGU9aTE4bmV4dDtcclxuXHRcdGNvbnNvbGUubG9nKGUudCh0aGlzLnByb3BzLmssdGhpcy5wcm9wcy5sYW4pKTtcclxuXHRcdHJldHVybiA8c3BhbiBjbGFzc05hbWU9e3RoaXMucHJvcHMuY2xhc3NOYW1lfT5cclxuXHRcdFx0XHJcblx0XHRcdHtpMThuZXh0LnQodGhpcy5wcm9wcy5rLHRoaXMucHJvcHMubGFuKX1cclxuXHJcblx0XHQ8L3NwYW4+XHRcdFxyXG5cdH1cclxufSJdfQ==