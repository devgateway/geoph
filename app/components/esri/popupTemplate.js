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

	var PopupTemplate = _react2.default.createClass({
		displayName: 'PopupTemplate',
		componentWillMount: function componentWillMount() {

			this.popupTemplate = new PopupTemplate(this.props);
			this.popupTemplate.content = "<p>As of 2015, <b>{MARRIEDRATE}%</b> of the" + " population in this zip code is married.</p>" + "<ul><li>{MARRIED_CY} people are married</li>" + "<li>{NEVMARR_CY} have never married</li>" + "<li>{DIVORCD_CY} are divorced</li><ul>";

			this.props.layer.popupTemplate = this.popupTemplate;
		},
		componentDidMount: function componentDidMount() {
			debugger;
			//popupTemplate.content=
		},
		render: function render() {
			return _react2.default.createElement('div', { style: { 'display': 'none' } });
		}
	});

	exports.default = PopupTemplate;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmlcXHBvcHVwVGVtcGxhdGUuanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFLQSxLQUFNLGdCQUFnQixnQkFBTSxXQUFOLENBQWtCOztBQUV2QyxvREFBb0I7O0FBRW5CLFFBQUssYUFBTCxHQUFtQixJQUFJLGFBQUosQ0FBa0IsS0FBSyxLQUFMLENBQXJDLENBRm1CO0FBR25CLFFBQUssYUFBTCxDQUFtQixPQUFuQixHQUE2QixnREFDdEIsOENBRHNCLEdBRXRCLDhDQUZzQixHQUd0QiwwQ0FIc0IsR0FJdEIsd0NBSnNCLENBSFY7O0FBU25CLFFBQUssS0FBTCxDQUFXLEtBQVgsQ0FBaUIsYUFBakIsR0FBK0IsS0FBSyxhQUFMLENBVFo7R0FGbUI7QUFjdkMsa0RBQW9CO0FBQ25COztBQURtQixHQWRtQjtBQW1CdkMsNEJBQVM7QUFDUixVQUFPLHVDQUFLLE9BQU8sRUFBQyxXQUFXLE1BQVgsRUFBUixFQUFMLENBQVAsQ0FEUTtHQW5COEI7RUFBbEIsQ0FBaEI7O21CQXlCUyIsImZpbGUiOiJjb21wb25lbnRzXFxlc3JpXFxwb3B1cFRlbXBsYXRlLmpzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCBSZWFjdERPTSBmcm9tICdyZWFjdC9yZWFjdC1kb20nO1xyXG5pbXBvcnQgZG9tUmVhZHkgZnJvbSBcImRvam8vZG9tUmVhZHkhXCI7XHJcblxyXG5cclxuY29uc3QgUG9wdXBUZW1wbGF0ZSA9IFJlYWN0LmNyZWF0ZUNsYXNzKHtcclxuXHJcblx0Y29tcG9uZW50V2lsbE1vdW50KCl7XHJcblx0XHJcblx0XHR0aGlzLnBvcHVwVGVtcGxhdGU9bmV3IFBvcHVwVGVtcGxhdGUodGhpcy5wcm9wcylcclxuXHRcdHRoaXMucG9wdXBUZW1wbGF0ZS5jb250ZW50ID0gXCI8cD5BcyBvZiAyMDE1LCA8Yj57TUFSUklFRFJBVEV9JTwvYj4gb2YgdGhlXCIgK1xyXG5cdFx0XHRcdFx0XHRcdFx0XHRcIiBwb3B1bGF0aW9uIGluIHRoaXMgemlwIGNvZGUgaXMgbWFycmllZC48L3A+XCIgK1xyXG5cdFx0XHRcdFx0XHRcdFx0XHRcIjx1bD48bGk+e01BUlJJRURfQ1l9IHBlb3BsZSBhcmUgbWFycmllZDwvbGk+XCIgK1xyXG5cdFx0XHRcdFx0XHRcdFx0XHRcIjxsaT57TkVWTUFSUl9DWX0gaGF2ZSBuZXZlciBtYXJyaWVkPC9saT5cIiArXHJcblx0XHRcdFx0XHRcdFx0XHRcdFwiPGxpPntESVZPUkNEX0NZfSBhcmUgZGl2b3JjZWQ8L2xpPjx1bD5cIjtcclxuXHJcblx0XHR0aGlzLnByb3BzLmxheWVyLnBvcHVwVGVtcGxhdGU9dGhpcy5wb3B1cFRlbXBsYXRlO1xyXG5cdH0sXHJcblxyXG5cdGNvbXBvbmVudERpZE1vdW50KCkge1xyXG5cdFx0ZGVidWdnZXI7XHJcblx0XHQvL3BvcHVwVGVtcGxhdGUuY29udGVudD1cclxuXHR9LFxyXG5cclxuXHRyZW5kZXIoKSB7IFxyXG5cdFx0cmV0dXJuIDxkaXYgc3R5bGU9e3snZGlzcGxheSc6ICdub25lJ319PjwvZGl2PjsgXHJcblx0fVxyXG5cclxufSk7XHJcblxyXG5leHBvcnQgZGVmYXVsdCBQb3B1cFRlbXBsYXRlO1xyXG4iXX0=