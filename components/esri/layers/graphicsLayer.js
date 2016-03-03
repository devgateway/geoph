define(['exports', 'react', 'react/react-dom', 'esri/layers/GraphicsLayer', 'esri/Graphic', 'dojo/domReady!'], function (exports, _react, _reactDom, _GraphicsLayer, _Graphic, _domReady) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _react2 = _interopRequireDefault(_react);

	var _reactDom2 = _interopRequireDefault(_reactDom);

	var _GraphicsLayer2 = _interopRequireDefault(_GraphicsLayer);

	var _Graphic2 = _interopRequireDefault(_Graphic);

	var _domReady2 = _interopRequireDefault(_domReady);

	function _interopRequireDefault(obj) {
		return obj && obj.__esModule ? obj : {
			default: obj
		};
	}

	var graphicsLayer = _react2.default.createClass({
		displayName: 'graphicsLayer',
		componentWillMount: function componentWillMount() {
			this.element = new _GraphicsLayer2.default();
			this.props.map.add(this.element);
		},
		getClonedChildrenWithMap: function getClonedChildrenWithMap(extra) {
			var _props = this.props;
			var children = _props.children;
			var map = _props.map;

			var props = Object.assign({ map: map }, extra);

			return _react2.default.Children.map(children, function (child) {
				return child ? _react2.default.cloneElement(child, props) : null;
			});
		},


		renderChildrenWithProps: function renderChildrenWithProps(props) {
			var children = this.getClonedChildrenWithMap(props);
			return _react2.default.createElement(
				'div',
				{ style: { display: 'none' } },
				children
			);
		},

		render: function render() {
			debugger;
			return this.renderChildrenWithProps({ layer: this.element });
		}
	});

	exports.default = graphicsLayer;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmlcXGxheWVyc1xcZ3JhcGhpY3NMYXllci5qc3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFTQSxLQUFNLGdCQUFnQixnQkFBTSxXQUFOLENBQWtCOztBQUV2QyxvREFBcUI7QUFDcEIsUUFBSyxPQUFMLEdBQWUsNkJBQWYsQ0FEb0I7QUFFcEIsUUFBSyxLQUFMLENBQVcsR0FBWCxDQUFlLEdBQWYsQ0FBbUIsS0FBSyxPQUFMLENBQW5CLENBRm9CO0dBRmtCO0FBUXZDLDhEQUF5QixPQUFPO2dCQUNMLEtBQUssS0FBTCxDQURLO09BQ3ZCLDJCQUR1QjtPQUNiLGlCQURhOztBQUUvQixPQUFNLFFBQVEsT0FBTyxNQUFQLENBQWMsRUFBQyxRQUFELEVBQWQsRUFBcUIsS0FBckIsQ0FBUixDQUZ5Qjs7QUFJL0IsVUFBTyxnQkFBTSxRQUFOLENBQWUsR0FBZixDQUFtQixRQUFuQixFQUE2QixpQkFBUztBQUM1QyxXQUFPLFFBQVEsZ0JBQU0sWUFBTixDQUFtQixLQUFuQixFQUEwQixLQUExQixDQUFSLEdBQTJDLElBQTNDLENBRHFDO0lBQVQsQ0FBcEMsQ0FKK0I7R0FSTzs7O0FBaUJ2QywyQkFBeUIsaUNBQVMsS0FBVCxFQUFnQjtBQUN4QyxPQUFNLFdBQVUsS0FBSyx3QkFBTCxDQUE4QixLQUE5QixDQUFWLENBRGtDO0FBRXhDLFVBQU87O01BQUssT0FBTyxFQUFDLFNBQVMsTUFBVCxFQUFSLEVBQUw7SUFBZ0MsUUFBaEM7SUFBUCxDQUZ3QztHQUFoQjs7QUFLekIsNEJBQVM7QUFDUixZQURRO0FBRVIsVUFBTyxLQUFLLHVCQUFMLENBQTZCLEVBQUMsT0FBTSxLQUFLLE9BQUwsRUFBcEMsQ0FBUCxDQUZRO0dBdEI4QjtFQUFsQixDQUFoQjs7bUJBNkJTIiwiZmlsZSI6ImNvbXBvbmVudHNcXGVzcmlcXGxheWVyc1xcZ3JhcGhpY3NMYXllci5qc3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgUmVhY3RET00gZnJvbSAncmVhY3QvcmVhY3QtZG9tJztcclxuaW1wb3J0IEdyYXBoaWNzTGF5ZXIgZnJvbSAnZXNyaS9sYXllcnMvR3JhcGhpY3NMYXllcic7XHJcbmltcG9ydCBHcmFwaGljIGZyb20gXCJlc3JpL0dyYXBoaWNcIjtcclxuXHJcblxyXG5pbXBvcnQgZG9tUmVhZHkgZnJvbSBcImRvam8vZG9tUmVhZHkhXCI7XHJcblxyXG5cclxuY29uc3QgZ3JhcGhpY3NMYXllciA9IFJlYWN0LmNyZWF0ZUNsYXNzKHtcclxuXHJcblx0Y29tcG9uZW50V2lsbE1vdW50KCkge1xyXG5cdFx0dGhpcy5lbGVtZW50ID0gbmV3IEdyYXBoaWNzTGF5ZXIoKTtcclxuXHRcdHRoaXMucHJvcHMubWFwLmFkZCh0aGlzLmVsZW1lbnQpXHJcblx0fSxcclxuXHJcblxyXG5cdGdldENsb25lZENoaWxkcmVuV2l0aE1hcChleHRyYSkge1xyXG5cdFx0Y29uc3QgeyBjaGlsZHJlbiwgbWFwIH0gPSB0aGlzLnByb3BzO1xyXG5cdFx0Y29uc3QgcHJvcHMgPSBPYmplY3QuYXNzaWduKHttYXB9LCBleHRyYSk7XHJcblxyXG5cdFx0cmV0dXJuIFJlYWN0LkNoaWxkcmVuLm1hcChjaGlsZHJlbiwgY2hpbGQgPT4ge1xyXG5cdFx0XHRyZXR1cm4gY2hpbGQgPyBSZWFjdC5jbG9uZUVsZW1lbnQoY2hpbGQsIHByb3BzKSA6IG51bGw7XHJcblx0XHR9KTtcclxuXHR9LFxyXG5cclxuXHRyZW5kZXJDaGlsZHJlbldpdGhQcm9wczogZnVuY3Rpb24ocHJvcHMpIHtcclxuXHRcdGNvbnN0IGNoaWxkcmVuID10aGlzLmdldENsb25lZENoaWxkcmVuV2l0aE1hcChwcm9wcyk7XHJcblx0XHRyZXR1cm4gPGRpdiBzdHlsZT17e2Rpc3BsYXk6ICdub25lJ319PntjaGlsZHJlbn08L2Rpdj47XHJcblx0fSxcclxuXHJcblx0cmVuZGVyKCkge1xyXG5cdFx0ZGVidWdnZXI7XHJcblx0XHRyZXR1cm4gdGhpcy5yZW5kZXJDaGlsZHJlbldpdGhQcm9wcyh7bGF5ZXI6dGhpcy5lbGVtZW50fSk7XHJcblx0fVxyXG5cclxufSk7XHJcblxyXG5leHBvcnQgZGVmYXVsdCBncmFwaGljc0xheWVyO1xyXG4iXX0=