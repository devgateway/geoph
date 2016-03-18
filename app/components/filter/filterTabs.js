define(['exports', 'react', 'react-bootstrap', 'app/components/filter/filterItemList', 'react-redux'], function (exports, _react, _reactBootstrap, _filterItemList, _reactRedux) {
		'use strict';

		Object.defineProperty(exports, "__esModule", {
				value: true
		});

		var _react2 = _interopRequireDefault(_react);

		var _filterItemList2 = _interopRequireDefault(_filterItemList);

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

		var FilterTabContent = function (_React$Component) {
				_inherits(FilterTabContent, _React$Component);

				function FilterTabContent() {
						_classCallCheck(this, FilterTabContent);

						var _this = _possibleConstructorReturn(this, Object.getPrototypeOf(FilterTabContent).call(this));

						_this.state = {};
						return _this;
				}

				_createClass(FilterTabContent, [{
						key: 'componentDidMount',
						value: function componentDidMount() {}
				}, {
						key: 'render',
						value: function render() {
								return _react2.default.createElement(
										'div',
										{ className: 'tab-container' },
										_react2.default.createElement(
												_reactBootstrap.Tabs,
												{ defaultActiveKey: 1 },
												_react2.default.createElement(
														_reactBootstrap.Tab,
														{ className: 'filter-tab-content', eventKey: 1, title: 'Funding' },
														_react2.default.createElement(
																_reactBootstrap.Tabs,
																{ defaultActiveKey: 1, position: 'left', tabWidth: 3 },
																_react2.default.createElement(
																		_reactBootstrap.Tab,
																		{ className: 'filter-list-content', eventKey: 1, title: 'Funding Source' },
																		'Funding Source Tab content'
																),
																_react2.default.createElement(
																		_reactBootstrap.Tab,
																		{ className: 'filter-list-content', eventKey: 2, title: 'Funding Type (ODA)' },
																		'Funding Type (ODA) Tab content'
																),
																_react2.default.createElement(
																		_reactBootstrap.Tab,
																		{ className: 'filter-list-content', eventKey: 3, title: 'Financing Institution' },
																		_react2.default.createElement(_filterItemList2.default, _extends({ loadList: true, filterType: 'fa' }, this.props.filters["fa"]))
																)
														)
												),
												_react2.default.createElement(
														_reactBootstrap.Tab,
														{ className: 'filter-tab-content', eventKey: 2, title: 'Agencies' },
														_react2.default.createElement(
																_reactBootstrap.Tabs,
																{ defaultActiveKey: 1, position: 'left', tabWidth: 3 },
																_react2.default.createElement(
																		_reactBootstrap.Tab,
																		{ className: 'filter-list-content', eventKey: 1, title: 'Implementing Agency' },
																		_react2.default.createElement(_filterItemList2.default, _extends({ loadList: true, filterType: 'ia' }, this.props.filters["ia"]))
																)
														)
												),
												_react2.default.createElement(
														_reactBootstrap.Tab,
														{ className: 'filter-tab-content', eventKey: 3, title: 'Sectors' },
														_react2.default.createElement(
																_reactBootstrap.Tabs,
																{ defaultActiveKey: 1, position: 'left', tabWidth: 3 },
																_react2.default.createElement(
																		_reactBootstrap.Tab,
																		{ className: 'filter-list-content', eventKey: 1, title: 'Sectors' },
																		_react2.default.createElement(_filterItemList2.default, _extends({ loadList: true, filterType: 'st' }, this.props.filters["st"]))
																),
																_react2.default.createElement(
																		_reactBootstrap.Tab,
																		{ className: 'filter-list-content', eventKey: 2, title: 'Philippines Development Priority' },
																		'Philippines Development Priority Tab content'
																)
														)
												),
												_react2.default.createElement(
														_reactBootstrap.Tab,
														{ className: 'filter-tab-content', eventKey: 4, title: 'Locations' },
														_react2.default.createElement(
																_reactBootstrap.Tabs,
																{ defaultActiveKey: 1, position: 'left', tabWidth: 3 },
																_react2.default.createElement(
																		_reactBootstrap.Tab,
																		{ className: 'filter-list-content', eventKey: 1, title: 'Coverage Scope' },
																		'Coverage Scope Tab content'
																),
																_react2.default.createElement(
																		_reactBootstrap.Tab,
																		{ className: 'filter-list-content', eventKey: 2, title: 'Coverage' },
																		'Coverage Tab content'
																)
														)
												),
												_react2.default.createElement(
														_reactBootstrap.Tab,
														{ className: 'filter-tab-content', eventKey: 5, title: 'Dates' },
														_react2.default.createElement(
																_reactBootstrap.Tabs,
																{ defaultActiveKey: 1, position: 'left', tabWidth: 3 },
																_react2.default.createElement(
																		_reactBootstrap.Tab,
																		{ className: 'filter-list-content', eventKey: 1, title: 'Implementation period' },
																		'Implementation period Tab content'
																),
																_react2.default.createElement(
																		_reactBootstrap.Tab,
																		{ className: 'filter-list-content', eventKey: 2, title: 'Loan validity period' },
																		'Loan validity period Tab content'
																)
														)
												),
												_react2.default.createElement(
														_reactBootstrap.Tab,
														{ className: 'filter-tab-content', eventKey: 6, title: 'Financial ranges' },
														_react2.default.createElement(
																_reactBootstrap.Tabs,
																{ defaultActiveKey: 1, position: 'left', tabWidth: 3 },
																_react2.default.createElement(
																		_reactBootstrap.Tab,
																		{ className: 'filter-list-content', eventKey: 1, title: 'Financial Amount' },
																		'Financial Amount Tab content'
																),
																_react2.default.createElement(
																		_reactBootstrap.Tab,
																		{ className: 'filter-list-content', eventKey: 2, title: 'Physical and Financial Performance' },
																		'Physical and Financial Performance Tab content'
																)
														)
												)
										)
								);
						}
				}]);

				return FilterTabContent;
		}(_react2.default.Component);

		var mapStateToProps = function mapStateToProps(state, props) {
				return {
						filters: state.filters
				};
		};

		exports.default = (0, _reactRedux.connect)(mapStateToProps)(FilterTabContent);
		;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGZpbHRlclxcZmlsdGVyVGFicy5qc3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7OztNQUtNOzs7QUFFTCxhQUZLLGdCQUVMLEdBQWM7NEJBRlQsa0JBRVM7O3lFQUZULDhCQUVTOztBQUVWLFlBQUssS0FBTCxHQUFhLEVBQWIsQ0FGVTs7S0FBZDs7aUJBRks7OzBDQU9pQjs7OytCQUlYO0FBQ1IsZUFDRTs7WUFBSyxXQUFVLGVBQVYsRUFBTDtVQUNDOztjQUFNLGtCQUFrQixDQUFsQixFQUFOO1lBQ0M7O2dCQUFLLFdBQVUsb0JBQVYsRUFBK0IsVUFBVSxDQUFWLEVBQWEsT0FBTSxTQUFOLEVBQWpEO2NBQ1k7O2tCQUFNLGtCQUFrQixDQUFsQixFQUFxQixVQUFTLE1BQVQsRUFBZ0IsVUFBVSxDQUFWLEVBQTNDO2dCQUNiOztvQkFBSyxXQUFVLHFCQUFWLEVBQWdDLFVBQVUsQ0FBVixFQUFhLE9BQU0sZ0JBQU4sRUFBbEQ7O2lCQURhO2dCQUlWOztvQkFBSyxXQUFVLHFCQUFWLEVBQWdDLFVBQVUsQ0FBVixFQUFhLE9BQU0sb0JBQU4sRUFBbEQ7O2lCQUpVO2dCQU9WOztvQkFBSyxXQUFVLHFCQUFWLEVBQWdDLFVBQVUsQ0FBVixFQUFhLE9BQU0sdUJBQU4sRUFBbEQ7a0JBQ0YsbUVBQWUsVUFBVSxJQUFWLEVBQWdCLFlBQVcsSUFBWCxJQUFvQixLQUFLLEtBQUwsQ0FBVyxPQUFYLENBQW1CLElBQW5CLEVBQW5ELENBREU7aUJBUFU7ZUFEWjthQUREO1lBY1U7O2dCQUFLLFdBQVUsb0JBQVYsRUFBK0IsVUFBVSxDQUFWLEVBQWEsT0FBTSxVQUFOLEVBQWpEO2NBQ0c7O2tCQUFNLGtCQUFrQixDQUFsQixFQUFxQixVQUFTLE1BQVQsRUFBZ0IsVUFBVSxDQUFWLEVBQTNDO2dCQUNiOztvQkFBSyxXQUFVLHFCQUFWLEVBQWdDLFVBQVUsQ0FBVixFQUFhLE9BQU0scUJBQU4sRUFBbEQ7a0JBQ0MsbUVBQWUsVUFBVSxJQUFWLEVBQWdCLFlBQVcsSUFBWCxJQUFvQixLQUFLLEtBQUwsQ0FBVyxPQUFYLENBQW1CLElBQW5CLEVBQW5ELENBREQ7aUJBRGE7ZUFESDthQWRWO1lBcUJVOztnQkFBSyxXQUFVLG9CQUFWLEVBQStCLFVBQVUsQ0FBVixFQUFhLE9BQU0sU0FBTixFQUFqRDtjQUNHOztrQkFBTSxrQkFBa0IsQ0FBbEIsRUFBcUIsVUFBUyxNQUFULEVBQWdCLFVBQVUsQ0FBVixFQUEzQztnQkFDYjs7b0JBQUssV0FBVSxxQkFBVixFQUFnQyxVQUFVLENBQVYsRUFBYSxPQUFNLFNBQU4sRUFBbEQ7a0JBQ0MsbUVBQWUsVUFBVSxJQUFWLEVBQWdCLFlBQVcsSUFBWCxJQUFvQixLQUFLLEtBQUwsQ0FBVyxPQUFYLENBQW1CLElBQW5CLEVBQW5ELENBREQ7aUJBRGE7Z0JBSVY7O29CQUFLLFdBQVUscUJBQVYsRUFBZ0MsVUFBVSxDQUFWLEVBQWEsT0FBTSxrQ0FBTixFQUFsRDs7aUJBSlU7ZUFESDthQXJCVjtZQStCVTs7Z0JBQUssV0FBVSxvQkFBVixFQUErQixVQUFVLENBQVYsRUFBYSxPQUFNLFdBQU4sRUFBakQ7Y0FDRzs7a0JBQU0sa0JBQWtCLENBQWxCLEVBQXFCLFVBQVMsTUFBVCxFQUFnQixVQUFVLENBQVYsRUFBM0M7Z0JBQ2I7O29CQUFLLFdBQVUscUJBQVYsRUFBZ0MsVUFBVSxDQUFWLEVBQWEsT0FBTSxnQkFBTixFQUFsRDs7aUJBRGE7Z0JBSVY7O29CQUFLLFdBQVUscUJBQVYsRUFBZ0MsVUFBVSxDQUFWLEVBQWEsT0FBTSxVQUFOLEVBQWxEOztpQkFKVTtlQURIO2FBL0JWO1lBeUNVOztnQkFBSyxXQUFVLG9CQUFWLEVBQStCLFVBQVUsQ0FBVixFQUFhLE9BQU0sT0FBTixFQUFqRDtjQUNHOztrQkFBTSxrQkFBa0IsQ0FBbEIsRUFBcUIsVUFBUyxNQUFULEVBQWdCLFVBQVUsQ0FBVixFQUEzQztnQkFDYjs7b0JBQUssV0FBVSxxQkFBVixFQUFnQyxVQUFVLENBQVYsRUFBYSxPQUFNLHVCQUFOLEVBQWxEOztpQkFEYTtnQkFJVjs7b0JBQUssV0FBVSxxQkFBVixFQUFnQyxVQUFVLENBQVYsRUFBYSxPQUFNLHNCQUFOLEVBQWxEOztpQkFKVTtlQURIO2FBekNWO1lBbURVOztnQkFBSyxXQUFVLG9CQUFWLEVBQStCLFVBQVUsQ0FBVixFQUFhLE9BQU0sa0JBQU4sRUFBakQ7Y0FDRzs7a0JBQU0sa0JBQWtCLENBQWxCLEVBQXFCLFVBQVMsTUFBVCxFQUFnQixVQUFVLENBQVYsRUFBM0M7Z0JBQ2I7O29CQUFLLFdBQVUscUJBQVYsRUFBZ0MsVUFBVSxDQUFWLEVBQWEsT0FBTSxrQkFBTixFQUFsRDs7aUJBRGE7Z0JBSVY7O29CQUFLLFdBQVUscUJBQVYsRUFBZ0MsVUFBVSxDQUFWLEVBQWEsT0FBTSxvQ0FBTixFQUFsRDs7aUJBSlU7ZUFESDthQW5EVjtXQUREO1NBREYsQ0FEUTs7OztXQVhOO0lBQXlCLGdCQUFNLFNBQU47O0FBa0YvQixNQUFNLGtCQUFrQixTQUFsQixlQUFrQixDQUFDLEtBQUQsRUFBUSxLQUFSLEVBQWtCO0FBQ3hDLFdBQU87QUFDTCxlQUFTLE1BQU0sT0FBTjtLQURYLENBRHdDO0dBQWxCOztvQkFNVCx5QkFBUSxlQUFSLEVBQXlCLGdCQUF6QjtBQUEyQyIsImZpbGUiOiJjb21wb25lbnRzXFxmaWx0ZXJcXGZpbHRlclRhYnMuanN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IHtUYWJzLCBUYWIsIEJ1dHRvbiwgTGFiZWx9IGZyb20gJ3JlYWN0LWJvb3RzdHJhcCc7XHJcbmltcG9ydCBJdGVtQ29tcG9uZW50IGZyb20gJ2FwcC9jb21wb25lbnRzL2ZpbHRlci9maWx0ZXJJdGVtTGlzdCdcclxuaW1wb3J0IHsgY29ubmVjdCB9IGZyb20gJ3JlYWN0LXJlZHV4J1xyXG5cclxuY2xhc3MgRmlsdGVyVGFiQ29udGVudCBleHRlbmRzIFJlYWN0LkNvbXBvbmVudCB7XHJcblxyXG5cdGNvbnN0cnVjdG9yKCkge1xyXG5cdCAgICBzdXBlcigpO1xyXG5cdCAgICB0aGlzLnN0YXRlID0ge307XHJcblx0fVxyXG5cclxuICBcdGNvbXBvbmVudERpZE1vdW50KCkge1xyXG5cdFx0XHJcblx0fVxyXG5cclxuICBcdHJlbmRlcigpIHtcclxuICBcdFx0cmV0dXJuIChcclxuXHQgICAgXHQ8ZGl2IGNsYXNzTmFtZT1cInRhYi1jb250YWluZXJcIj5cclxuXHQgICAgXHRcdDxUYWJzIGRlZmF1bHRBY3RpdmVLZXk9ezF9PlxyXG5cdCAgICBcdFx0XHQ8VGFiIGNsYXNzTmFtZT1cImZpbHRlci10YWItY29udGVudFwiIGV2ZW50S2V5PXsxfSB0aXRsZT1cIkZ1bmRpbmdcIj5cclxuXHQgICAgICAgICAgICAgICAgICBcdDxUYWJzIGRlZmF1bHRBY3RpdmVLZXk9ezF9IHBvc2l0aW9uPVwibGVmdFwiIHRhYldpZHRoPXszfT5cclxuXHRcdFx0XHRcdFx0XHQ8VGFiIGNsYXNzTmFtZT1cImZpbHRlci1saXN0LWNvbnRlbnRcIiBldmVudEtleT17MX0gdGl0bGU9XCJGdW5kaW5nIFNvdXJjZVwiPlxyXG5cdFx0XHRcdFx0XHRcdFx0RnVuZGluZyBTb3VyY2UgVGFiIGNvbnRlbnRcclxuXHRcdFx0XHRcdFx0ICAgIDwvVGFiPlxyXG5cdFx0XHRcdFx0XHQgICAgPFRhYiBjbGFzc05hbWU9XCJmaWx0ZXItbGlzdC1jb250ZW50XCIgZXZlbnRLZXk9ezJ9IHRpdGxlPVwiRnVuZGluZyBUeXBlIChPREEpXCI+XHJcblx0XHRcdFx0XHRcdFx0XHRGdW5kaW5nIFR5cGUgKE9EQSkgVGFiIGNvbnRlbnRcclxuXHRcdFx0XHRcdFx0ICAgIDwvVGFiPlxyXG5cdFx0XHRcdFx0XHQgICAgPFRhYiBjbGFzc05hbWU9XCJmaWx0ZXItbGlzdC1jb250ZW50XCIgZXZlbnRLZXk9ezN9IHRpdGxlPVwiRmluYW5jaW5nIEluc3RpdHV0aW9uXCI+XHJcblx0XHRcdFx0XHRcdFx0XHQ8SXRlbUNvbXBvbmVudCBsb2FkTGlzdD17dHJ1ZX0gZmlsdGVyVHlwZT1cImZhXCIgey4uLnRoaXMucHJvcHMuZmlsdGVyc1tcImZhXCJdfSAvPlxyXG5cdFx0XHRcdFx0XHQgICAgPC9UYWI+XHJcblx0XHRcdFx0XHRcdDwvVGFicz5cclxuXHQgICAgICAgICAgICAgICAgPC9UYWI+XHJcblx0ICAgICAgICAgICAgICAgIDxUYWIgY2xhc3NOYW1lPVwiZmlsdGVyLXRhYi1jb250ZW50XCIgZXZlbnRLZXk9ezJ9IHRpdGxlPVwiQWdlbmNpZXNcIj5cclxuXHQgICAgICAgICAgICAgICAgICBcdDxUYWJzIGRlZmF1bHRBY3RpdmVLZXk9ezF9IHBvc2l0aW9uPVwibGVmdFwiIHRhYldpZHRoPXszfT5cclxuXHRcdFx0XHRcdFx0XHQ8VGFiIGNsYXNzTmFtZT1cImZpbHRlci1saXN0LWNvbnRlbnRcIiBldmVudEtleT17MX0gdGl0bGU9XCJJbXBsZW1lbnRpbmcgQWdlbmN5XCI+XHJcblx0XHRcdFx0XHRcdFx0XHQ8SXRlbUNvbXBvbmVudCBsb2FkTGlzdD17dHJ1ZX0gZmlsdGVyVHlwZT1cImlhXCIgey4uLnRoaXMucHJvcHMuZmlsdGVyc1tcImlhXCJdfSAvPlxyXG5cdFx0XHRcdFx0XHQgICAgPC9UYWI+XHRcdFx0XHRcdFx0ICAgXHJcblx0XHRcdFx0XHRcdDwvVGFicz5cclxuXHQgICAgICAgICAgICAgICAgPC9UYWI+XHJcblx0ICAgICAgICAgICAgICAgIDxUYWIgY2xhc3NOYW1lPVwiZmlsdGVyLXRhYi1jb250ZW50XCIgZXZlbnRLZXk9ezN9IHRpdGxlPVwiU2VjdG9yc1wiPlxyXG5cdCAgICAgICAgICAgICAgICAgIFx0PFRhYnMgZGVmYXVsdEFjdGl2ZUtleT17MX0gcG9zaXRpb249XCJsZWZ0XCIgdGFiV2lkdGg9ezN9PlxyXG5cdFx0XHRcdFx0XHRcdDxUYWIgY2xhc3NOYW1lPVwiZmlsdGVyLWxpc3QtY29udGVudFwiIGV2ZW50S2V5PXsxfSB0aXRsZT1cIlNlY3RvcnNcIj5cclxuXHRcdFx0XHRcdFx0XHRcdDxJdGVtQ29tcG9uZW50IGxvYWRMaXN0PXt0cnVlfSBmaWx0ZXJUeXBlPVwic3RcIiB7Li4udGhpcy5wcm9wcy5maWx0ZXJzW1wic3RcIl19IC8+XHJcblx0XHRcdFx0XHRcdCAgICA8L1RhYj5cclxuXHRcdFx0XHRcdFx0ICAgIDxUYWIgY2xhc3NOYW1lPVwiZmlsdGVyLWxpc3QtY29udGVudFwiIGV2ZW50S2V5PXsyfSB0aXRsZT1cIlBoaWxpcHBpbmVzIERldmVsb3BtZW50IFByaW9yaXR5XCI+XHJcblx0XHRcdFx0XHRcdFx0XHRQaGlsaXBwaW5lcyBEZXZlbG9wbWVudCBQcmlvcml0eSBUYWIgY29udGVudFxyXG5cdFx0XHRcdFx0XHQgICAgPC9UYWI+XHJcblx0XHRcdFx0XHRcdDwvVGFicz5cclxuXHQgICAgICAgICAgICAgICAgPC9UYWI+XHJcblx0ICAgICAgICAgICAgICAgIDxUYWIgY2xhc3NOYW1lPVwiZmlsdGVyLXRhYi1jb250ZW50XCIgZXZlbnRLZXk9ezR9IHRpdGxlPVwiTG9jYXRpb25zXCI+XHJcblx0ICAgICAgICAgICAgICAgICAgXHQ8VGFicyBkZWZhdWx0QWN0aXZlS2V5PXsxfSBwb3NpdGlvbj1cImxlZnRcIiB0YWJXaWR0aD17M30+XHJcblx0XHRcdFx0XHRcdFx0PFRhYiBjbGFzc05hbWU9XCJmaWx0ZXItbGlzdC1jb250ZW50XCIgZXZlbnRLZXk9ezF9IHRpdGxlPVwiQ292ZXJhZ2UgU2NvcGVcIj5cclxuXHRcdFx0XHRcdFx0XHRcdENvdmVyYWdlIFNjb3BlIFRhYiBjb250ZW50XHJcblx0XHRcdFx0XHRcdCAgICA8L1RhYj5cclxuXHRcdFx0XHRcdFx0ICAgIDxUYWIgY2xhc3NOYW1lPVwiZmlsdGVyLWxpc3QtY29udGVudFwiIGV2ZW50S2V5PXsyfSB0aXRsZT1cIkNvdmVyYWdlXCI+XHJcblx0XHRcdFx0XHRcdFx0XHRDb3ZlcmFnZSBUYWIgY29udGVudFxyXG5cdFx0XHRcdFx0XHQgICAgPC9UYWI+XHJcblx0XHRcdFx0XHRcdDwvVGFicz5cclxuXHQgICAgICAgICAgICAgICAgPC9UYWI+XHJcblx0ICAgICAgICAgICAgICAgIDxUYWIgY2xhc3NOYW1lPVwiZmlsdGVyLXRhYi1jb250ZW50XCIgZXZlbnRLZXk9ezV9IHRpdGxlPVwiRGF0ZXNcIj5cclxuXHQgICAgICAgICAgICAgICAgICBcdDxUYWJzIGRlZmF1bHRBY3RpdmVLZXk9ezF9IHBvc2l0aW9uPVwibGVmdFwiIHRhYldpZHRoPXszfT5cclxuXHRcdFx0XHRcdFx0XHQ8VGFiIGNsYXNzTmFtZT1cImZpbHRlci1saXN0LWNvbnRlbnRcIiBldmVudEtleT17MX0gdGl0bGU9XCJJbXBsZW1lbnRhdGlvbiBwZXJpb2RcIj5cclxuXHRcdFx0XHRcdFx0XHRcdEltcGxlbWVudGF0aW9uIHBlcmlvZCBUYWIgY29udGVudFxyXG5cdFx0XHRcdFx0XHQgICAgPC9UYWI+XHJcblx0XHRcdFx0XHRcdCAgICA8VGFiIGNsYXNzTmFtZT1cImZpbHRlci1saXN0LWNvbnRlbnRcIiBldmVudEtleT17Mn0gdGl0bGU9XCJMb2FuIHZhbGlkaXR5IHBlcmlvZFwiPlxyXG5cdFx0XHRcdFx0XHRcdFx0TG9hbiB2YWxpZGl0eSBwZXJpb2QgVGFiIGNvbnRlbnRcclxuXHRcdFx0XHRcdFx0ICAgIDwvVGFiPlxyXG5cdFx0XHRcdFx0XHQ8L1RhYnM+XHJcblx0ICAgICAgICAgICAgICAgIDwvVGFiPlxyXG5cdCAgICAgICAgICAgICAgICA8VGFiIGNsYXNzTmFtZT1cImZpbHRlci10YWItY29udGVudFwiIGV2ZW50S2V5PXs2fSB0aXRsZT1cIkZpbmFuY2lhbCByYW5nZXNcIj5cclxuXHQgICAgICAgICAgICAgICAgICBcdDxUYWJzIGRlZmF1bHRBY3RpdmVLZXk9ezF9IHBvc2l0aW9uPVwibGVmdFwiIHRhYldpZHRoPXszfT5cclxuXHRcdFx0XHRcdFx0XHQ8VGFiIGNsYXNzTmFtZT1cImZpbHRlci1saXN0LWNvbnRlbnRcIiBldmVudEtleT17MX0gdGl0bGU9XCJGaW5hbmNpYWwgQW1vdW50XCI+XHJcblx0XHRcdFx0XHRcdFx0XHRGaW5hbmNpYWwgQW1vdW50IFRhYiBjb250ZW50XHJcblx0XHRcdFx0XHRcdCAgICA8L1RhYj5cclxuXHRcdFx0XHRcdFx0ICAgIDxUYWIgY2xhc3NOYW1lPVwiZmlsdGVyLWxpc3QtY29udGVudFwiIGV2ZW50S2V5PXsyfSB0aXRsZT1cIlBoeXNpY2FsIGFuZCBGaW5hbmNpYWwgUGVyZm9ybWFuY2VcIj5cclxuXHRcdFx0XHRcdFx0XHRcdFBoeXNpY2FsIGFuZCBGaW5hbmNpYWwgUGVyZm9ybWFuY2UgVGFiIGNvbnRlbnRcclxuXHRcdFx0XHRcdFx0ICAgIDwvVGFiPlxyXG5cdFx0XHRcdFx0XHQ8L1RhYnM+XHJcblx0ICAgICAgICAgICAgICAgIDwvVGFiPlxyXG5cdFx0XHRcdDwvVGFicz5cclxuICAgICAgICAgICAgPC9kaXY+XHJcblx0ICAgICk7XHJcblx0ICAgXHJcbiAgXHR9XHJcbn1cclxuXHJcbmNvbnN0IG1hcFN0YXRlVG9Qcm9wcyA9IChzdGF0ZSwgcHJvcHMpID0+IHtcclxuICByZXR1cm4ge1xyXG4gICAgZmlsdGVyczogc3RhdGUuZmlsdGVyc1xyXG4gIH1cclxufVxyXG5cclxuZXhwb3J0IGRlZmF1bHQgY29ubmVjdChtYXBTdGF0ZVRvUHJvcHMpKEZpbHRlclRhYkNvbnRlbnQpOztcclxuIl19