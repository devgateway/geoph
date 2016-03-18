define(['exports', 'react', 'app/util/translate', 'i18next'], function (exports, _react, _translate, _i18next) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _react2 = _interopRequireDefault(_react);

	var _translate2 = _interopRequireDefault(_translate);

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

	var LangSwitcher = function (_React$Component) {
		_inherits(LangSwitcher, _React$Component);

		function LangSwitcher() {
			_classCallCheck(this, LangSwitcher);

			return _possibleConstructorReturn(this, Object.getPrototypeOf(LangSwitcher).apply(this, arguments));
		}

		_createClass(LangSwitcher, [{
			key: 'changeLanguage',
			value: function changeLanguage(evt) {
				var _this2 = this;

				var lan = evt.target.value;
				_i18next2.default.changeLanguage(lan, function (err, t) {
					_this2.props.onChangeLanguage(lan);
				});
			}
		}, {
			key: 'render',
			value: function render() {

				return _react2.default.createElement(
					'div',
					{ className: 'nav navbar-rigth lan-selector-container' },
					_react2.default.createElement(
						'select',
						{ value: this.props.selected, name: 'lan', className: 'pull-right', onChange: this.changeLanguage.bind(this) },
						_react2.default.createElement(
							'option',
							{ value: 'en' },
							(0, _translate2.default)('header.language.english')
						),
						_react2.default.createElement(
							'option',
							{ value: 'es' },
							(0, _translate2.default)('header.language.spanish')
						)
					)
				);
			}
		}]);

		return LangSwitcher;
	}(_react2.default.Component);

	exports.default = LangSwitcher;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGxhblxcc3dpdGNoZXIuanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7S0FJcUI7Ozs7Ozs7Ozs7O2tDQUVMLEtBQUk7OztBQUNsQixRQUFJLE1BQUksSUFBSSxNQUFKLENBQVcsS0FBWCxDQURVO0FBRWxCLHNCQUFRLGNBQVIsQ0FBdUIsR0FBdkIsRUFBNEIsVUFBQyxHQUFELEVBQU0sQ0FBTixFQUFZO0FBQ3ZDLFlBQUssS0FBTCxDQUFXLGdCQUFYLENBQTRCLEdBQTVCLEVBRHVDO0tBQVosQ0FBNUIsQ0FGa0I7Ozs7NEJBT1Y7O0FBRVIsV0FDQzs7T0FBSyxXQUFVLHlDQUFWLEVBQUw7S0FDQzs7UUFBUyxPQUFPLEtBQUssS0FBTCxDQUFXLFFBQVgsRUFBcUIsTUFBSyxLQUFMLEVBQVcsV0FBVSxZQUFWLEVBQXVCLFVBQVUsS0FBSyxjQUFMLENBQW9CLElBQXBCLENBQXlCLElBQXpCLENBQVYsRUFBdkU7TUFDQzs7U0FBUSxPQUFNLElBQU4sRUFBUjtPQUFvQix5QkFBVSx5QkFBVixDQUFwQjtPQUREO01BRUM7O1NBQVEsT0FBTSxJQUFOLEVBQVI7T0FBb0IseUJBQVUseUJBQVYsQ0FBcEI7T0FGRDtNQUREO0tBREQsQ0FGUTs7OztTQVRXO0dBQXFCLGdCQUFNLFNBQU47O21CQUFyQiIsImZpbGUiOiJjb21wb25lbnRzXFxsYW5cXHN3aXRjaGVyLmpzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCB0cmFuc2xhdGUgZnJvbSAnYXBwL3V0aWwvdHJhbnNsYXRlJztcclxuaW1wb3J0IGkxOG5leHQgZnJvbSAnaTE4bmV4dCc7XHJcblxyXG5leHBvcnQgZGVmYXVsdCBjbGFzcyBMYW5nU3dpdGNoZXIgZXh0ZW5kcyBSZWFjdC5Db21wb25lbnQge1xyXG5cclxuXHRjaGFuZ2VMYW5ndWFnZShldnQpe1xyXG5cdFx0bGV0IGxhbj1ldnQudGFyZ2V0LnZhbHVlO1xyXG5cdFx0aTE4bmV4dC5jaGFuZ2VMYW5ndWFnZShsYW4sIChlcnIsIHQpID0+IHtcclxuXHRcdFx0dGhpcy5wcm9wcy5vbkNoYW5nZUxhbmd1YWdlKGxhbik7XHJcblx0XHR9KTtcclxuXHR9XHJcblxyXG5cdHJlbmRlcigpIHtcclxuXHJcblx0XHRyZXR1cm4gKFxyXG5cdFx0XHQ8ZGl2IGNsYXNzTmFtZT1cIm5hdiBuYXZiYXItcmlndGggbGFuLXNlbGVjdG9yLWNvbnRhaW5lclwiPlxyXG5cdFx0XHRcdDxzZWxlY3QgIHZhbHVlPXt0aGlzLnByb3BzLnNlbGVjdGVkfSBuYW1lPVwibGFuXCIgY2xhc3NOYW1lPVwicHVsbC1yaWdodFwiIG9uQ2hhbmdlPXt0aGlzLmNoYW5nZUxhbmd1YWdlLmJpbmQodGhpcyl9PlxyXG5cdFx0XHRcdFx0PG9wdGlvbiB2YWx1ZT1cImVuXCI+e3RyYW5zbGF0ZSgnaGVhZGVyLmxhbmd1YWdlLmVuZ2xpc2gnKX08L29wdGlvbj5cclxuXHRcdFx0XHRcdDxvcHRpb24gdmFsdWU9XCJlc1wiPnt0cmFuc2xhdGUoJ2hlYWRlci5sYW5ndWFnZS5zcGFuaXNoJyl9PC9vcHRpb24+XHJcblx0XHRcdFx0PC9zZWxlY3Q+XHJcblx0XHRcdDwvZGl2PlxyXG5cdFx0XHQpO1xyXG5cdH1cclxufSJdfQ==