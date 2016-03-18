define(['exports', 'react', 'react/react-dom', 'esri/views/MapView', 'dojo/domReady!'], function (exports, _react, _reactDom, _MapView, _domReady) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _react2 = _interopRequireDefault(_react);

	var _reactDom2 = _interopRequireDefault(_reactDom);

	var _MapView2 = _interopRequireDefault(_MapView);

	var _domReady2 = _interopRequireDefault(_domReady);

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

	var ReactMapView = _react2.default.createClass({
		displayName: 'ReactMapView',
		componentWillMount: function componentWillMount() {},
		componentDidMount: function componentDidMount() {

			var node = _reactDom2.default.findDOMNode(this.refs.mapView);
			this.view = new _MapView2.default(_extends({ container: node }, this.props));
			window.view = this.view;
			this.view.then(function () {
				this.forceUpdate(); //render child elements
			}.bind(this), function () {
				console.log('Map view has failed');
			});

			this.view.watch('zoom', function (zoom) {
				//console.log(zoom);
			});
			this.view.watch('animation', function (response) {
				if (response && response.state === "running") {
					console.log("Animation in progress");
				} else {
					console.log("No animation");
				}
			});
		},


		render: function render() {
			var view = this.view;
			var children = this.view ? _react2.default.Children.map(this.props.children, function (child) {
				return child ? _react2.default.cloneElement(child, { view: view }) : null;
			}) : null;
			return _react2.default.createElement(
				'div',
				{ ref: 'mapView' },
				children
			);
		}
	});

	exports.default = ReactMapView;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmlcXG1hcHZpZXcuanMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFNQSxLQUFNLGVBQWUsZ0JBQU0sV0FBTixDQUFrQjs7QUFFdEMsb0RBQW9CLEVBRmtCO0FBS3RDLGtEQUFvQjs7QUFFbkIsT0FBTSxPQUFPLG1CQUFTLFdBQVQsQ0FBcUIsS0FBSyxJQUFMLENBQVUsT0FBVixDQUE1QixDQUZhO0FBR25CLFFBQUssSUFBTCxHQUFXLGlDQUFhLFdBQVcsSUFBWCxJQUFtQixLQUFLLEtBQUwsQ0FBaEMsQ0FBWCxDQUhtQjtBQUluQixVQUFPLElBQVAsR0FBWSxLQUFLLElBQUwsQ0FKTztBQUtuQixRQUFLLElBQUwsQ0FBVSxJQUFWLENBQWUsWUFBVTtBQUN4QixTQUFLLFdBQUw7QUFEd0IsSUFBVixDQUViLElBRmEsQ0FFUixJQUZRLENBQWYsRUFFYSxZQUFVO0FBQ3RCLFlBQVEsR0FBUixDQUFZLHFCQUFaLEVBRHNCO0lBQVYsQ0FGYixDQUxtQjs7QUFXbkIsUUFBSyxJQUFMLENBQVUsS0FBVixDQUFnQixNQUFoQixFQUF1QixVQUFDLElBQUQsRUFBUTs7SUFBUixDQUF2QixDQVhtQjtBQWNuQixRQUFLLElBQUwsQ0FBVSxLQUFWLENBQWdCLFdBQWhCLEVBQTRCLFVBQUMsUUFBRCxFQUFZO0FBQ3ZDLFFBQUcsWUFBWSxTQUFTLEtBQVQsS0FBbUIsU0FBbkIsRUFBNkI7QUFDM0MsYUFBUSxHQUFSLENBQVksdUJBQVosRUFEMkM7S0FBNUMsTUFHSTtBQUNILGFBQVEsR0FBUixDQUFZLGNBQVosRUFERztLQUhKO0lBRDJCLENBQTVCLENBZG1CO0dBTGtCOzs7QUE4QnRDLFVBQVEsa0JBQVc7QUFDbEIsT0FBTSxPQUFLLEtBQUssSUFBTCxDQURPO0FBRWxCLE9BQU0sV0FBVyxLQUFLLElBQUwsR0FBWSxnQkFBTSxRQUFOLENBQWUsR0FBZixDQUFtQixLQUFLLEtBQUwsQ0FBVyxRQUFYLEVBQXFCLGlCQUFTO0FBQUMsV0FBTyxRQUFRLGdCQUFNLFlBQU4sQ0FBbUIsS0FBbkIsRUFBMEIsRUFBQyxVQUFELEVBQTFCLENBQVIsR0FBNEMsSUFBNUMsQ0FBUjtJQUFULENBQXBELEdBQTJILElBQTNILENBRkM7QUFHbEIsVUFBUTs7TUFBSyxLQUFJLFNBQUosRUFBTDtJQUFvQixRQUFwQjtJQUFSLENBSGtCO0dBQVg7RUE5QlksQ0FBZjs7bUJBcUNTIiwiZmlsZSI6ImNvbXBvbmVudHNcXGVzcmlcXG1hcHZpZXcuanMiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgUmVhY3RET00gZnJvbSAncmVhY3QvcmVhY3QtZG9tJztcclxuaW1wb3J0IE1hcFZpZXcgZnJvbSAnZXNyaS92aWV3cy9NYXBWaWV3JztcclxuaW1wb3J0IGRvbVJlYWR5IGZyb20gXCJkb2pvL2RvbVJlYWR5IVwiO1xyXG5cclxuXHJcbmNvbnN0IFJlYWN0TWFwVmlldyA9IFJlYWN0LmNyZWF0ZUNsYXNzKHtcclxuXHJcblx0Y29tcG9uZW50V2lsbE1vdW50KCl7XHJcblx0fSxcclxuXHJcblx0Y29tcG9uZW50RGlkTW91bnQoKSB7XHJcblxyXG5cdFx0Y29uc3Qgbm9kZSA9IFJlYWN0RE9NLmZpbmRET01Ob2RlKHRoaXMucmVmcy5tYXBWaWV3KTtcclxuXHRcdHRoaXMudmlldz0gbmV3IE1hcFZpZXcoe2NvbnRhaW5lcjogbm9kZSwuLi50aGlzLnByb3BzfSk7XHJcblx0XHR3aW5kb3cudmlldz10aGlzLnZpZXc7XHJcblx0XHR0aGlzLnZpZXcudGhlbihmdW5jdGlvbigpe1xyXG5cdFx0XHR0aGlzLmZvcmNlVXBkYXRlKCk7IC8vcmVuZGVyIGNoaWxkIGVsZW1lbnRzXHJcblx0XHR9LmJpbmQodGhpcyksZnVuY3Rpb24oKXtcclxuXHRcdFx0Y29uc29sZS5sb2coJ01hcCB2aWV3IGhhcyBmYWlsZWQnKTtcclxuXHRcdH0pXHJcblxyXG5cdFx0dGhpcy52aWV3LndhdGNoKCd6b29tJywoem9vbSk9PntcclxuXHRcdC8vY29uc29sZS5sb2coem9vbSk7XHJcblx0XHR9KVxyXG5cdFx0dGhpcy52aWV3LndhdGNoKCdhbmltYXRpb24nLChyZXNwb25zZSk9PntcclxuXHRcdFx0aWYocmVzcG9uc2UgJiYgcmVzcG9uc2Uuc3RhdGUgPT09IFwicnVubmluZ1wiKXtcclxuXHRcdFx0XHRjb25zb2xlLmxvZyhcIkFuaW1hdGlvbiBpbiBwcm9ncmVzc1wiKTtcclxuXHRcdFx0fVxyXG5cdFx0XHRlbHNle1xyXG5cdFx0XHRcdGNvbnNvbGUubG9nKFwiTm8gYW5pbWF0aW9uXCIpO1xyXG5cdFx0XHR9XHJcblx0XHR9KVxyXG5cdH0sXHJcblxyXG5cclxuXHRyZW5kZXI6IGZ1bmN0aW9uKCkgeyAgXHJcblx0XHRjb25zdCB2aWV3PXRoaXMudmlldztcclxuXHRcdGNvbnN0IGNoaWxkcmVuID0gdGhpcy52aWV3ID8gUmVhY3QuQ2hpbGRyZW4ubWFwKHRoaXMucHJvcHMuY2hpbGRyZW4sIGNoaWxkID0+IHtyZXR1cm4gY2hpbGQgPyBSZWFjdC5jbG9uZUVsZW1lbnQoY2hpbGQsIHt2aWV3fSkgOiBudWxsO30pIDogbnVsbDtcclxuXHRcdHJldHVybiAoPGRpdiByZWY9J21hcFZpZXcnPntjaGlsZHJlbn08L2Rpdj4pO1xyXG5cdH1cclxufSk7XHJcblxyXG5leHBvcnQgZGVmYXVsdCBSZWFjdE1hcFZpZXc7XHJcbiJdfQ==