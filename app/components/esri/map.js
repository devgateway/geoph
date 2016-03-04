define(['exports', 'react', 'react/react-dom', 'esri/Map'], function (exports, _react, _reactDom, _Map) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });

  var _react2 = _interopRequireDefault(_react);

  var _reactDom2 = _interopRequireDefault(_reactDom);

  var _Map2 = _interopRequireDefault(_Map);

  function _interopRequireDefault(obj) {
    return obj && obj.__esModule ? obj : {
      default: obj
    };
  }

  var map = new _Map2.default({ basemap: 'streets' });

  var MapComponent = _react2.default.createClass({
    displayName: 'MapComponent',
    componentWillMount: function componentWillMount() {
      this.element = new _Map2.default(this.props);
    },


    render: function render() {
      var _this = this;

      var children = this.element ? _react2.default.Children.map(this.props.children, function (child) {
        return child ? _react2.default.cloneElement(child, { map: _this.element }) : null;
      }) : null;
      return _react2.default.createElement(
        'div',
        { className: this.props.className, style: this.props.style },
        children
      );
    }
  });

  exports.default = MapComponent;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmlcXG1hcC5qc3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7Ozs7Ozs7OztBQUlBLE1BQU0sTUFBTSxrQkFBUSxFQUFFLFNBQVMsU0FBVCxFQUFWLENBQU47O0FBR04sTUFBTSxlQUFlLGdCQUFNLFdBQU4sQ0FBa0I7O0FBRXRDLHNEQUFxQjtBQUNwQixXQUFLLE9BQUwsR0FBYSxrQkFBUSxLQUFLLEtBQUwsQ0FBckIsQ0FEb0I7S0FGaUI7OztBQU12QyxZQUFRLGtCQUFXOzs7QUFFakIsVUFBTSxXQUFXLEtBQUssT0FBTCxHQUFlLGdCQUFNLFFBQU4sQ0FBZSxHQUFmLENBQW1CLEtBQUssS0FBTCxDQUFXLFFBQVgsRUFBcUIsaUJBQVM7QUFBQyxlQUFPLFFBQVEsZ0JBQU0sWUFBTixDQUFtQixLQUFuQixFQUEwQixFQUFDLEtBQUksTUFBSyxPQUFMLEVBQS9CLENBQVIsR0FBd0QsSUFBeEQsQ0FBUjtPQUFULENBQXZELEdBQTBJLElBQTFJLENBRkE7QUFHakIsYUFDRTs7VUFBSyxXQUFXLEtBQUssS0FBTCxDQUFXLFNBQVgsRUFBc0IsT0FBTyxLQUFLLEtBQUwsQ0FBVyxLQUFYLEVBQTdDO1FBQ0csUUFESDtPQURGLENBSGlCO0tBQVg7R0FOYSxDQUFmOztvQkFpQlMiLCJmaWxlIjoiY29tcG9uZW50c1xcZXNyaVxcbWFwLmpzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCBSZWFjdERPTSBmcm9tICdyZWFjdC9yZWFjdC1kb20nO1xyXG5pbXBvcnQgTWFwIGZyb20gJ2VzcmkvTWFwJztcclxuXHJcbmNvbnN0IG1hcCA9IG5ldyBNYXAoeyBiYXNlbWFwOiAnc3RyZWV0cyd9KTtcclxuXHJcblxyXG5jb25zdCBNYXBDb21wb25lbnQgPSBSZWFjdC5jcmVhdGVDbGFzcyh7XHJcblxyXG4gY29tcG9uZW50V2lsbE1vdW50KCkge1xyXG4gIHRoaXMuZWxlbWVudD1uZXcgTWFwKHRoaXMucHJvcHMpO1xyXG59LFxyXG5cclxucmVuZGVyOiBmdW5jdGlvbigpIHtcclxuXHJcbiAgY29uc3QgY2hpbGRyZW4gPSB0aGlzLmVsZW1lbnQgPyBSZWFjdC5DaGlsZHJlbi5tYXAodGhpcy5wcm9wcy5jaGlsZHJlbiwgY2hpbGQgPT4ge3JldHVybiBjaGlsZCA/IFJlYWN0LmNsb25lRWxlbWVudChjaGlsZCwge21hcDp0aGlzLmVsZW1lbnR9KSA6IG51bGw7fSkgOiBudWxsO1xyXG4gIHJldHVybiAoXHJcbiAgICA8ZGl2IGNsYXNzTmFtZT17dGhpcy5wcm9wcy5jbGFzc05hbWV9IHN0eWxlPXt0aGlzLnByb3BzLnN0eWxlfT5cclxuICAgICAge2NoaWxkcmVufVxyXG4gICAgPC9kaXY+XHJcbiAgICApO1xyXG4gIH1cclxufSk7XHJcblxyXG5leHBvcnQgZGVmYXVsdCBNYXBDb21wb25lbnQ7XHJcbiJdfQ==