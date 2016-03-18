define(['exports', 'react', 'react-redux', 'app/actions/filters'], function (exports, _react, _reactRedux, _filters) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _react2 = _interopRequireDefault(_react);

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

	var FilterList = function (_React$Component) {
		_inherits(FilterList, _React$Component);

		function FilterList() {
			_classCallCheck(this, FilterList);

			return _possibleConstructorReturn(this, Object.getPrototypeOf(FilterList).apply(this, arguments));
		}

		_createClass(FilterList, [{
			key: 'render',
			value: function render() {
				var _this2 = this;

				if (this.props.expanded) {
					return _react2.default.createElement(
						'div',
						null,
						_react2.default.createElement(
							'ul',
							{ style: { left: '25', listStyleType: 'none' } },
							this.props.items.map(function (item) {
								return _react2.default.createElement(
									'li',
									{ key: item.id },
									_react2.default.createElement(ItemConnected, _extends({ filterType: _this2.props.filterType }, item))
								);
							})
						)
					);
				} else {
					return null;
				}
			}
		}]);

		return FilterList;
	}(_react2.default.Component);

	var FilterItem = function (_React$Component2) {
		_inherits(FilterItem, _React$Component2);

		function FilterItem() {
			_classCallCheck(this, FilterItem);

			var _this3 = _possibleConstructorReturn(this, Object.getPrototypeOf(FilterItem).call(this));

			_this3.state = { 'expanded': true, 'partialSelected': false };
			return _this3;
		}

		_createClass(FilterItem, [{
			key: 'componentDidMount',
			value: function componentDidMount() {
				if (this.props.loadList) {
					this.props.onLoadFilterList(this.props.filterType);
				}
			}
		}, {
			key: 'toggleExpanded',
			value: function toggleExpanded() {
				this.setState({ 'expanded': !this.state.expanded });
			}
		}, {
			key: 'handleChange',
			value: function handleChange() {
				var _props = this.props;
				var id = _props.id;
				var filterType = _props.filterType;
				var selected = _props.selected;

				if (this.props.id) {
					this.props.onItemChange({ id: id, filterType: filterType, selected: !selected });
				} else {
					this.props.onChangeAllFilterList({ filterType: filterType, selected: !selected });
				}
			}
		}, {
			key: 'render',
			value: function render() {
				var selectionClass = "selectable " + (this.props.selected ? "selected" : this.props.selectedCounter > 0 ? "half-fill" : "");
				return _react2.default.createElement(
					'div',
					null,
					_react2.default.createElement(
						'div',
						{ className: 'filterItemInfo' },
						_react2.default.createElement('div', { className: selectionClass, onClick: this.handleChange.bind(this) }),
						_react2.default.createElement(
							'div',
							{ className: 'toggle-nav', onClick: this.handleChange.bind(this) },
							this.props.name || this.props.description
						),
						this.props.items && this.props.items.length > 0 ? _react2.default.createElement(
							'div',
							null,
							_react2.default.createElement(
								'div',
								{ className: 'counter' },
								'(',
								this.props.selectedCounter,
								'/',
								this.props.totalCounter,
								')'
							),
							_react2.default.createElement(
								'div',
								{ className: this.state.expanded ? "expanded open" : "expanded closed", onClick: this.toggleExpanded.bind(this) },
								this.state.expanded ? "-" : "+"
							)
						) : null
					),
					_react2.default.createElement(
						'div',
						null,
						this.props.items && this.props.items.length > 0 ? _react2.default.createElement(FilterList, _extends({ expanded: this.state.expanded }, this.props)) : null
					)
				);
			}
		}]);

		return FilterItem;
	}(_react2.default.Component);

	var mapDispatchToProps = function mapDispatchToProps(dispatch, ownProps) {
		return {
			onLoadFilterList: function onLoadFilterList(type) {
				dispatch((0, _filters.fetchFilterListIfNeeded)(type));
			},

			onItemChange: function onItemChange(filterItem) {
				dispatch((0, _filters.selectFilterItem)(filterItem));
			},

			onChangeAllFilterList: function onChangeAllFilterList(filterItem) {
				dispatch((0, _filters.selectAllFilterList)(filterItem));
			}
		};
	};

	var ItemConnected = (0, _reactRedux.connect)(null, mapDispatchToProps)(FilterItem);
	exports.default = ItemConnected;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGZpbHRlclxcZmlsdGVySXRlbUxpc3QuanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7O0tBS007Ozs7Ozs7Ozs7OzRCQUVNOzs7QUFDUixRQUFJLEtBQUssS0FBTCxDQUFXLFFBQVgsRUFBb0I7QUFDdEIsWUFDSTs7O01BQ0M7O1NBQUksT0FBTyxFQUFDLE1BQU0sSUFBTixFQUFZLGVBQWUsTUFBZixFQUFwQixFQUFKO09BQ0MsS0FBSyxLQUFMLENBQVcsS0FBWCxDQUFpQixHQUFqQixDQUFxQixVQUFDLElBQUQsRUFBVTtBQUMvQixlQUFPOztXQUFJLEtBQUssS0FBSyxFQUFMLEVBQVQ7U0FDTiw4QkFBQyxhQUFELGFBQWUsWUFBWSxPQUFLLEtBQUwsQ0FBVyxVQUFYLElBQTJCLEtBQXRELENBRE07U0FBUCxDQUQrQjtRQUFWLENBRHRCO09BREQ7TUFESixDQURzQjtLQUF4QixNQVlRO0FBQ04sWUFBTyxJQUFQLENBRE07S0FaUjs7OztTQUhFO0dBQW1CLGdCQUFNLFNBQU47O0tBcUJuQjs7O0FBRUwsV0FGSyxVQUVMLEdBQWM7eUJBRlQsWUFFUzs7dUVBRlQsd0JBRVM7O0FBRVYsVUFBSyxLQUFMLEdBQWEsRUFBQyxZQUFZLElBQVosRUFBa0IsbUJBQW1CLEtBQW5CLEVBQWhDLENBRlU7O0dBQWQ7O2VBRks7O3VDQU9lO0FBQ25CLFFBQUksS0FBSyxLQUFMLENBQVcsUUFBWCxFQUFvQjtBQUN2QixVQUFLLEtBQUwsQ0FBVyxnQkFBWCxDQUE0QixLQUFLLEtBQUwsQ0FBVyxVQUFYLENBQTVCLENBRHVCO0tBQXhCOzs7O29DQUtlO0FBQ2YsU0FBSyxRQUFMLENBQWMsRUFBQyxZQUFZLENBQUMsS0FBSyxLQUFMLENBQVcsUUFBWCxFQUE1QixFQURlOzs7O2tDQUlEO2lCQUNxQixLQUFLLEtBQUwsQ0FEckI7UUFDUCxlQURPO1FBQ0gsK0JBREc7UUFDUywyQkFEVDs7QUFFZCxRQUFJLEtBQUssS0FBTCxDQUFXLEVBQVgsRUFBYztBQUNqQixVQUFLLEtBQUwsQ0FBVyxZQUFYLENBQXdCLEVBQUMsTUFBRCxFQUFLLHNCQUFMLEVBQWlCLFVBQVUsQ0FBQyxRQUFELEVBQW5ELEVBRGlCO0tBQWxCLE1BRU87QUFDTixVQUFLLEtBQUwsQ0FBVyxxQkFBWCxDQUFpQyxFQUFDLHNCQUFELEVBQWEsVUFBVSxDQUFDLFFBQUQsRUFBeEQsRUFETTtLQUZQOzs7OzRCQU9VO0FBQ1IsUUFBSSxpQkFBaUIsaUJBQWlCLEtBQUssS0FBTCxDQUFXLFFBQVgsR0FBcUIsVUFBckIsR0FBa0MsS0FBSyxLQUFMLENBQVcsZUFBWCxHQUEyQixDQUEzQixHQUE4QixXQUE5QixHQUE0QyxFQUE1QyxDQUFuRCxDQURiO0FBRVAsV0FDSTs7O0tBQ0M7O1FBQUssV0FBVSxnQkFBVixFQUFMO01BQ0MsdUNBQUssV0FBVyxjQUFYLEVBQTJCLFNBQVMsS0FBSyxZQUFMLENBQWtCLElBQWxCLENBQXVCLElBQXZCLENBQVQsRUFBaEMsQ0FERDtNQUVDOztTQUFLLFdBQVUsWUFBVixFQUF1QixTQUFTLEtBQUssWUFBTCxDQUFrQixJQUFsQixDQUF1QixJQUF2QixDQUFULEVBQTVCO09BQ0UsS0FBSyxLQUFMLENBQVcsSUFBWCxJQUFtQixLQUFLLEtBQUwsQ0FBVyxXQUFYO09BSHRCO01BS0UsS0FBSyxLQUFMLENBQVcsS0FBWCxJQUFvQixLQUFLLEtBQUwsQ0FBVyxLQUFYLENBQWlCLE1BQWpCLEdBQXdCLENBQXhCLEdBQ3BCOzs7T0FDQzs7VUFBSyxXQUFVLFNBQVYsRUFBTDs7UUFDRyxLQUFLLEtBQUwsQ0FBVyxlQUFYO1dBREg7UUFDZ0MsS0FBSyxLQUFMLENBQVcsWUFBWDtXQURoQztRQUREO09BSUM7O1VBQUssV0FBVyxLQUFLLEtBQUwsQ0FBVyxRQUFYLEdBQXFCLGVBQXJCLEdBQXVDLGlCQUF2QyxFQUEwRCxTQUFTLEtBQUssY0FBTCxDQUFvQixJQUFwQixDQUF5QixJQUF6QixDQUFULEVBQTFFO1FBQ0UsS0FBSyxLQUFMLENBQVcsUUFBWCxHQUFxQixHQUFyQixHQUEyQixHQUEzQjtRQUxIO09BREEsR0FTQyxJQVREO01BTkg7S0FpQkM7OztNQUNFLEtBQUssS0FBTCxDQUFXLEtBQVgsSUFBb0IsS0FBSyxLQUFMLENBQVcsS0FBWCxDQUFpQixNQUFqQixHQUF3QixDQUF4QixHQUNwQiw4QkFBQyxVQUFELGFBQVksVUFBVSxLQUFLLEtBQUwsQ0FBVyxRQUFYLElBQXlCLEtBQUssS0FBTCxDQUEvQyxDQURBLEdBRUYsSUFGRTtNQWxCSDtLQURKLENBRk87Ozs7U0ExQk47R0FBbUIsZ0JBQU0sU0FBTjs7QUF5RHpCLEtBQU0scUJBQXFCLFNBQXJCLGtCQUFxQixDQUFDLFFBQUQsRUFBVyxRQUFYLEVBQXdCO0FBQ2pELFNBQU87QUFDTCxxQkFBa0IsMEJBQUMsSUFBRCxFQUFVO0FBQzFCLGFBQVMsc0NBQXdCLElBQXhCLENBQVQsRUFEMEI7SUFBVjs7QUFJbEIsaUJBQWMsc0JBQUMsVUFBRCxFQUFnQjtBQUM1QixhQUFTLCtCQUFpQixVQUFqQixDQUFULEVBRDRCO0lBQWhCOztBQUlkLDBCQUF1QiwrQkFBQyxVQUFELEVBQWdCO0FBQ3JDLGFBQVMsa0NBQW9CLFVBQXBCLENBQVQsRUFEcUM7SUFBaEI7R0FUekIsQ0FEaUQ7RUFBeEI7O0FBZ0IzQixLQUFNLGdCQUFnQix5QkFBUSxJQUFSLEVBQWEsa0JBQWIsRUFBaUMsVUFBakMsQ0FBaEI7bUJBQ1MiLCJmaWxlIjoiY29tcG9uZW50c1xcZmlsdGVyXFxmaWx0ZXJJdGVtTGlzdC5qc3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG4vL2ltcG9ydCBGaWx0ZXJMaXN0IGZyb20gJ2FwcC9jb21wb25lbnRzL2ZpbHRlci9saXN0JztcclxuaW1wb3J0IHsgY29ubmVjdCB9IGZyb20gJ3JlYWN0LXJlZHV4J1xyXG5pbXBvcnQgeyBzZWxlY3RBbGxGaWx0ZXJMaXN0LCBzZWxlY3RGaWx0ZXJJdGVtLCBmZXRjaEZpbHRlckxpc3RJZk5lZWRlZCB9IGZyb20gJ2FwcC9hY3Rpb25zL2ZpbHRlcnMnXHJcblxyXG5jbGFzcyBGaWx0ZXJMaXN0IGV4dGVuZHMgUmVhY3QuQ29tcG9uZW50IHtcclxuXHJcbiAgXHRyZW5kZXIoKSB7XHJcbiAgXHRcdGlmICh0aGlzLnByb3BzLmV4cGFuZGVkKXtcclxuXHQgICAgXHRyZXR1cm4gKFxyXG5cdFx0ICAgICAgICA8ZGl2PlxyXG5cdFx0ICAgICAgICBcdDx1bCBzdHlsZT17e2xlZnQ6ICcyNScsIGxpc3RTdHlsZVR5cGU6ICdub25lJ319Plx0ICAgICAgICBcdFxyXG5cdFx0ICAgICAgICBcdHt0aGlzLnByb3BzLml0ZW1zLm1hcCgoaXRlbSkgPT4ge1xyXG5cdFx0XHRcdCAgICAgICAgcmV0dXJuIDxsaSBrZXk9e2l0ZW0uaWR9PiBcclxuXHRcdFx0XHQgICAgICAgIFx0PEl0ZW1Db25uZWN0ZWQgZmlsdGVyVHlwZT17dGhpcy5wcm9wcy5maWx0ZXJUeXBlfSB7Li4uaXRlbX0gLz5cclxuXHRcdFx0XHQgICAgICAgIDwvbGk+XHJcblx0XHRcdFx0ICAgIH0pfVxyXG5cdFx0XHRcdCAgICA8L3VsPlxyXG5cdFx0XHQgICAgPC9kaXY+XHJcblx0ICAgICAgXHQpO1xyXG5cdCAgICB9IGVsc2Uge1xyXG5cdCAgICBcdHJldHVybiBudWxsO1xyXG5cdCAgICB9XHJcbiAgXHR9XHJcbn1cclxuXHJcbmNsYXNzIEZpbHRlckl0ZW0gZXh0ZW5kcyBSZWFjdC5Db21wb25lbnQge1xyXG5cclxuXHRjb25zdHJ1Y3RvcigpIHtcclxuXHQgICAgc3VwZXIoKTtcclxuXHQgICAgdGhpcy5zdGF0ZSA9IHsnZXhwYW5kZWQnOiB0cnVlLCAncGFydGlhbFNlbGVjdGVkJzogZmFsc2V9O1xyXG5cdH1cclxuXHJcblx0Y29tcG9uZW50RGlkTW91bnQoKSB7XHJcblx0XHRpZiAodGhpcy5wcm9wcy5sb2FkTGlzdCl7XHJcblx0XHRcdHRoaXMucHJvcHMub25Mb2FkRmlsdGVyTGlzdCh0aGlzLnByb3BzLmZpbHRlclR5cGUpO1xyXG5cdFx0fVxyXG5cdH1cclxuXHJcblx0dG9nZ2xlRXhwYW5kZWQoKXtcclxuXHRcdHRoaXMuc2V0U3RhdGUoeydleHBhbmRlZCc6ICF0aGlzLnN0YXRlLmV4cGFuZGVkfSk7XHJcblx0fVxyXG5cclxuXHRoYW5kbGVDaGFuZ2UoKSB7XHJcblx0XHRjb25zdCB7aWQsIGZpbHRlclR5cGUsIHNlbGVjdGVkfSA9IHRoaXMucHJvcHM7XHJcblx0XHRpZiAodGhpcy5wcm9wcy5pZCl7XHJcblx0XHRcdHRoaXMucHJvcHMub25JdGVtQ2hhbmdlKHtpZCwgZmlsdGVyVHlwZSwgc2VsZWN0ZWQ6ICFzZWxlY3RlZH0pO1xyXG5cdFx0fSBlbHNlIHtcclxuXHRcdFx0dGhpcy5wcm9wcy5vbkNoYW5nZUFsbEZpbHRlckxpc3Qoe2ZpbHRlclR5cGUsIHNlbGVjdGVkOiAhc2VsZWN0ZWR9KTtcclxuXHRcdH1cclxuXHR9XHJcblxyXG4gIFx0cmVuZGVyKCkge1xyXG4gIFx0XHRsZXQgc2VsZWN0aW9uQ2xhc3MgPSBcInNlbGVjdGFibGUgXCIgKyAodGhpcy5wcm9wcy5zZWxlY3RlZD8gXCJzZWxlY3RlZFwiIDogdGhpcy5wcm9wcy5zZWxlY3RlZENvdW50ZXI+MD8gXCJoYWxmLWZpbGxcIiA6IFwiXCIpO1xyXG4gICAgXHRyZXR1cm4gKFxyXG5cdCAgICAgICAgPGRpdj5cclxuXHQgICAgICAgIFx0PGRpdiBjbGFzc05hbWU9XCJmaWx0ZXJJdGVtSW5mb1wiPlxyXG5cdCAgICAgICAgXHRcdDxkaXYgY2xhc3NOYW1lPXtzZWxlY3Rpb25DbGFzc30gb25DbGljaz17dGhpcy5oYW5kbGVDaGFuZ2UuYmluZCh0aGlzKX0gLz5cclxuXHRcdCAgICAgICAgXHQ8ZGl2IGNsYXNzTmFtZT1cInRvZ2dsZS1uYXZcIiBvbkNsaWNrPXt0aGlzLmhhbmRsZUNoYW5nZS5iaW5kKHRoaXMpfT5cclxuXHRcdCAgICAgICAgXHRcdHt0aGlzLnByb3BzLm5hbWUgfHwgdGhpcy5wcm9wcy5kZXNjcmlwdGlvbn1cclxuXHRcdCAgICAgICAgXHQ8L2Rpdj5cclxuXHRcdCAgICAgICAgXHR7dGhpcy5wcm9wcy5pdGVtcyAmJiB0aGlzLnByb3BzLml0ZW1zLmxlbmd0aD4wPyBcclxuXHRcdCAgICAgICAgXHRcdDxkaXY+XHJcblx0XHRcdCAgICAgICAgXHRcdDxkaXYgY2xhc3NOYW1lPVwiY291bnRlclwiPlxyXG5cdFx0XHRcdCAgICAgICAgXHRcdCh7dGhpcy5wcm9wcy5zZWxlY3RlZENvdW50ZXJ9L3t0aGlzLnByb3BzLnRvdGFsQ291bnRlcn0pXHJcblx0XHRcdFx0ICAgICAgICBcdDwvZGl2PlxyXG5cdFx0XHRcdCAgICAgICAgXHQ8ZGl2IGNsYXNzTmFtZT17dGhpcy5zdGF0ZS5leHBhbmRlZD8gXCJleHBhbmRlZCBvcGVuXCIgOiBcImV4cGFuZGVkIGNsb3NlZFwifSBvbkNsaWNrPXt0aGlzLnRvZ2dsZUV4cGFuZGVkLmJpbmQodGhpcyl9PlxyXG5cdFx0XHRcdCAgICAgICAgXHRcdHt0aGlzLnN0YXRlLmV4cGFuZGVkPyBcIi1cIiA6IFwiK1wifVxyXG5cdFx0XHRcdCAgICAgICAgXHQ8L2Rpdj5cclxuXHRcdFx0XHQgICAgICAgIDwvZGl2PlxyXG5cdFx0ICAgICAgICBcdDogbnVsbH1cdCBcclxuXHRcdCAgICAgICAgPC9kaXY+XHRcclxuXHRcdCAgICAgICAgPGRpdj5cclxuXHQgICAgICAgIFx0XHR7dGhpcy5wcm9wcy5pdGVtcyAmJiB0aGlzLnByb3BzLml0ZW1zLmxlbmd0aD4wPyBcclxuXHRcdCAgICAgICAgXHRcdDxGaWx0ZXJMaXN0IGV4cGFuZGVkPXt0aGlzLnN0YXRlLmV4cGFuZGVkfSB7Li4udGhpcy5wcm9wc30gLz5cclxuXHRcdFx0XHQgICAgOiBudWxsfVx0IFxyXG5cdFx0ICAgICAgICA8L2Rpdj4gICAgICAgXHJcblx0ICAgICAgICA8L2Rpdj5cclxuICAgICAgXHQpO1xyXG4gIFx0fVxyXG59XHJcblxyXG5cclxuY29uc3QgbWFwRGlzcGF0Y2hUb1Byb3BzID0gKGRpc3BhdGNoLCBvd25Qcm9wcykgPT4ge1xyXG4gIHJldHVybiB7XHJcbiAgICBvbkxvYWRGaWx0ZXJMaXN0OiAodHlwZSkgPT4ge1xyXG4gICAgICBkaXNwYXRjaChmZXRjaEZpbHRlckxpc3RJZk5lZWRlZCh0eXBlKSk7XHJcbiAgICB9LFxyXG5cclxuICAgIG9uSXRlbUNoYW5nZTogKGZpbHRlckl0ZW0pID0+IHtcclxuICAgICAgZGlzcGF0Y2goc2VsZWN0RmlsdGVySXRlbShmaWx0ZXJJdGVtKSk7XHJcbiAgICB9LFxyXG5cclxuICAgIG9uQ2hhbmdlQWxsRmlsdGVyTGlzdDogKGZpbHRlckl0ZW0pID0+IHtcclxuICAgICAgZGlzcGF0Y2goc2VsZWN0QWxsRmlsdGVyTGlzdChmaWx0ZXJJdGVtKSk7XHJcbiAgICB9XHJcbiAgfVxyXG59XHJcblxyXG5jb25zdCBJdGVtQ29ubmVjdGVkID0gY29ubmVjdChudWxsLG1hcERpc3BhdGNoVG9Qcm9wcykoRmlsdGVySXRlbSk7XHJcbmV4cG9ydCBkZWZhdWx0IEl0ZW1Db25uZWN0ZWQ7XHJcbiJdfQ==