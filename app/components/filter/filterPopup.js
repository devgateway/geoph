define(['exports', 'react', 'react-dom', 'react-bootstrap', 'app/components/filter/filterTabs'], function (exports, _react, _reactDom, _reactBootstrap, _filterTabs) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _react2 = _interopRequireDefault(_react);

	var _reactDom2 = _interopRequireDefault(_reactDom);

	var _filterTabs2 = _interopRequireDefault(_filterTabs);

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

	var FilterPopup = function (_React$Component) {
		_inherits(FilterPopup, _React$Component);

		function FilterPopup() {
			_classCallCheck(this, FilterPopup);

			var _this = _possibleConstructorReturn(this, Object.getPrototypeOf(FilterPopup).call(this));

			_this.state = { 'showModal': false };
			return _this;
		}

		_createClass(FilterPopup, [{
			key: 'componentDidMount',
			value: function componentDidMount() {}
		}, {
			key: 'showFilterPopup',
			value: function showFilterPopup() {
				this.setState({ 'showModal': true });
			}
		}, {
			key: 'hideFilterPopup',
			value: function hideFilterPopup() {
				this.setState({ 'showModal': false });
			}
		}, {
			key: 'reset',
			value: function reset() {}
		}, {
			key: 'cancel',
			value: function cancel() {
				this.setState({ 'showModal': false });
			}
		}, {
			key: 'apply',
			value: function apply() {}
		}, {
			key: 'render',
			value: function render() {
				var filters = this.state.filterMap || [];
				return _react2.default.createElement(
					'div',
					null,
					_react2.default.createElement(
						_reactBootstrap.Button,
						{ className: '', onClick: this.showFilterPopup.bind(this) },
						'Filters'
					),
					_react2.default.createElement(
						_reactBootstrap.Modal,
						{ bsSize: 'large', 'aria-labelledby': 'contained-modal-title-lg',
							show: this.state.showModal, onHide: this.hideFilterPopup.bind(this) },
						_react2.default.createElement(
							_reactBootstrap.Modal.Header,
							null,
							_react2.default.createElement(
								_reactBootstrap.Modal.Title,
								null,
								'Filters'
							)
						),
						_react2.default.createElement(
							_reactBootstrap.Modal.Body,
							null,
							_react2.default.createElement(_filterTabs2.default, null)
						),
						_react2.default.createElement(
							_reactBootstrap.Modal.Footer,
							null,
							_react2.default.createElement(
								_reactBootstrap.Button,
								{ className: 'btn btn-sm', bsStyle: 'danger', onClick: this.reset.bind(this) },
								'Reset'
							),
							_react2.default.createElement(
								_reactBootstrap.Button,
								{ className: 'btn btn-sm', bsStyle: 'warning', onClick: this.cancel.bind(this) },
								'Cancel'
							),
							_react2.default.createElement(
								_reactBootstrap.Button,
								{ className: 'btn btn-sm', bsStyle: 'success', onClick: this.apply.bind(this) },
								'Apply'
							)
						)
					)
				);
			}
		}]);

		return FilterPopup;
	}(_react2.default.Component);

	exports.default = FilterPopup;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGZpbHRlclxcZmlsdGVyUG9wdXAuanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7S0FLcUI7OztBQUVwQixXQUZvQixXQUVwQixHQUFjO3lCQUZNLGFBRU47O3NFQUZNLHlCQUVOOztBQUVWLFNBQUssS0FBTCxHQUFhLEVBQUMsYUFBYSxLQUFiLEVBQWQsQ0FGVTs7R0FBZDs7ZUFGb0I7O3VDQU9FOzs7cUNBSUo7QUFDZCxTQUFLLFFBQUwsQ0FBYyxFQUFDLGFBQWEsSUFBYixFQUFmLEVBRGM7Ozs7cUNBSUU7QUFDaEIsU0FBSyxRQUFMLENBQWMsRUFBQyxhQUFhLEtBQWIsRUFBZixFQURnQjs7OzsyQkFJVjs7OzRCQUlDO0FBQ1AsU0FBSyxRQUFMLENBQWMsRUFBQyxhQUFhLEtBQWIsRUFBZixFQURPOzs7OzJCQUlEOzs7NEJBSUM7QUFDUixRQUFJLFVBQVUsS0FBSyxLQUFMLENBQVcsU0FBWCxJQUF3QixFQUF4QixDQUROO0FBRVIsV0FDQzs7O0tBQ0k7O1FBQVEsV0FBVSxFQUFWLEVBQWEsU0FBUyxLQUFLLGVBQUwsQ0FBcUIsSUFBckIsQ0FBMEIsSUFBMUIsQ0FBVCxFQUFyQjs7TUFESjtLQUVGOztRQUFPLFFBQU8sT0FBUCxFQUFlLG1CQUFnQiwwQkFBaEI7QUFDckIsYUFBTSxLQUFLLEtBQUwsQ0FBVyxTQUFYLEVBQXNCLFFBQVEsS0FBSyxlQUFMLENBQXFCLElBQXJCLENBQTBCLElBQTFCLENBQVIsRUFEN0I7TUFFQzs2QkFBTyxNQUFQOztPQUNDOzhCQUFPLEtBQVA7OztRQUREO09BRkQ7TUFPQzs2QkFBTyxJQUFQOztPQUNDLHlEQUREO09BUEQ7TUFVQzs2QkFBTyxNQUFQOztPQUNDOztVQUFRLFdBQVUsWUFBVixFQUF1QixTQUFRLFFBQVIsRUFBaUIsU0FBUyxLQUFLLEtBQUwsQ0FBVyxJQUFYLENBQWdCLElBQWhCLENBQVQsRUFBaEQ7O1FBREQ7T0FFTzs7VUFBUSxXQUFVLFlBQVYsRUFBdUIsU0FBUSxTQUFSLEVBQWtCLFNBQVMsS0FBSyxNQUFMLENBQVksSUFBWixDQUFpQixJQUFqQixDQUFULEVBQWpEOztRQUZQO09BR087O1VBQVEsV0FBVSxZQUFWLEVBQXVCLFNBQVEsU0FBUixFQUFrQixTQUFTLEtBQUssS0FBTCxDQUFXLElBQVgsQ0FBZ0IsSUFBaEIsQ0FBVCxFQUFqRDs7UUFIUDtPQVZEO01BRkU7S0FERCxDQUZROzs7O1NBL0JTO0dBQW9CLGdCQUFNLFNBQU47O21CQUFwQiIsImZpbGUiOiJjb21wb25lbnRzXFxmaWx0ZXJcXGZpbHRlclBvcHVwLmpzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCBSZWFjdERPTSBmcm9tICdyZWFjdC1kb20nO1xyXG5pbXBvcnQge01vZGFsLCBCdXR0b24sIFRhYnMsIFRhYn0gZnJvbSAncmVhY3QtYm9vdHN0cmFwJztcclxuaW1wb3J0IEZpbHRlclRhYnMgZnJvbSAnYXBwL2NvbXBvbmVudHMvZmlsdGVyL2ZpbHRlclRhYnMnO1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgY2xhc3MgRmlsdGVyUG9wdXAgZXh0ZW5kcyBSZWFjdC5Db21wb25lbnQge1xyXG5cclxuXHRjb25zdHJ1Y3RvcigpIHtcclxuXHQgICAgc3VwZXIoKTtcclxuXHQgICAgdGhpcy5zdGF0ZSA9IHsnc2hvd01vZGFsJzogZmFsc2V9O1xyXG5cdH1cclxuXHJcbiAgXHRjb21wb25lbnREaWRNb3VudCgpIHtcclxuXHRcclxuXHR9XHJcblxyXG5cdHNob3dGaWx0ZXJQb3B1cCgpIHtcclxuXHQgICAgdGhpcy5zZXRTdGF0ZSh7J3Nob3dNb2RhbCc6IHRydWV9KTtcclxuXHR9XHJcblxyXG4gIFx0aGlkZUZpbHRlclBvcHVwKCkge1xyXG5cdCAgICB0aGlzLnNldFN0YXRlKHsnc2hvd01vZGFsJzogZmFsc2V9KTtcclxuXHR9XHJcblxyXG4gIFx0cmVzZXQoKSB7XHJcblx0ICAgXHJcblx0fVxyXG5cclxuICBcdGNhbmNlbCgpIHtcclxuXHQgICAgdGhpcy5zZXRTdGF0ZSh7J3Nob3dNb2RhbCc6IGZhbHNlfSk7XHJcblx0fVxyXG5cclxuICBcdGFwcGx5KCkge1xyXG5cdCAgICBcclxuXHR9XHJcblxyXG4gIFx0cmVuZGVyKCkge1xyXG4gIFx0XHRsZXQgZmlsdGVycyA9IHRoaXMuc3RhdGUuZmlsdGVyTWFwIHx8IFtdO1xyXG4gIFx0XHRyZXR1cm4gKFxyXG4gICAgXHQ8ZGl2PlxyXG5cdCAgICAgICAgPEJ1dHRvbiBjbGFzc05hbWU9Jycgb25DbGljaz17dGhpcy5zaG93RmlsdGVyUG9wdXAuYmluZCh0aGlzKX0+RmlsdGVyczwvQnV0dG9uPlxyXG5cdFx0XHQ8TW9kYWwgYnNTaXplPSdsYXJnZScgYXJpYS1sYWJlbGxlZGJ5PSdjb250YWluZWQtbW9kYWwtdGl0bGUtbGcnXHJcblx0XHRcdCBzaG93PXt0aGlzLnN0YXRlLnNob3dNb2RhbH0gb25IaWRlPXt0aGlzLmhpZGVGaWx0ZXJQb3B1cC5iaW5kKHRoaXMpfT5cclxuXHRcdFx0XHQ8TW9kYWwuSGVhZGVyPlxyXG5cdFx0XHRcdFx0PE1vZGFsLlRpdGxlPlxyXG5cdFx0XHRcdFx0XHRGaWx0ZXJzXHJcblx0XHRcdFx0XHQ8L01vZGFsLlRpdGxlPlxyXG5cdFx0XHRcdDwvTW9kYWwuSGVhZGVyPlxyXG5cdFx0XHRcdDxNb2RhbC5Cb2R5PlxyXG5cdFx0XHRcdFx0PEZpbHRlclRhYnMgLz5cclxuXHRcdFx0XHQ8L01vZGFsLkJvZHk+XHJcblx0XHRcdFx0PE1vZGFsLkZvb3Rlcj5cclxuXHRcdFx0XHRcdDxCdXR0b24gY2xhc3NOYW1lPVwiYnRuIGJ0bi1zbVwiIGJzU3R5bGU9J2Rhbmdlcicgb25DbGljaz17dGhpcy5yZXNldC5iaW5kKHRoaXMpfT5SZXNldDwvQnV0dG9uPlxyXG4gICAgICAgIFx0XHRcdDxCdXR0b24gY2xhc3NOYW1lPVwiYnRuIGJ0bi1zbVwiIGJzU3R5bGU9J3dhcm5pbmcnIG9uQ2xpY2s9e3RoaXMuY2FuY2VsLmJpbmQodGhpcyl9PkNhbmNlbDwvQnV0dG9uPlxyXG4gICAgICAgIFx0XHRcdDxCdXR0b24gY2xhc3NOYW1lPVwiYnRuIGJ0bi1zbVwiIGJzU3R5bGU9J3N1Y2Nlc3MnIG9uQ2xpY2s9e3RoaXMuYXBwbHkuYmluZCh0aGlzKX0+QXBwbHk8L0J1dHRvbj5cclxuXHRcdFx0XHQ8L01vZGFsLkZvb3Rlcj5cclxuXHRcdFx0PC9Nb2RhbD5cclxuXHRcdDwvZGl2PlxyXG4gICAgICBcdCk7XHJcbiAgXHR9XHJcbn0iXX0=