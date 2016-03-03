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

  var SceneViewComponent = _react2.default.createClass({
    displayName: 'SceneViewComponent',
    componentDidMount: function componentDidMount() {
      var node = _reactDom2.default.findDOMNode(this.refs.mapView);
      this.view = new _MapView2.default(_extends({ container: node }, this.props));
    },
    getInitialState: function getInitialState() {
      return { ready: false };
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

  exports.default = SceneViewComponent;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmlcXG1hcHZpZXcuanMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFNQSxNQUFNLHFCQUFxQixnQkFBTSxXQUFOLENBQWtCOztBQUU1QyxvREFBb0I7QUFDbEIsVUFBTSxPQUFPLG1CQUFTLFdBQVQsQ0FBcUIsS0FBSyxJQUFMLENBQVUsT0FBVixDQUE1QixDQURZO0FBRWxCLFdBQUssSUFBTCxHQUFXLGlDQUFhLFdBQVcsSUFBWCxJQUFtQixLQUFLLEtBQUwsQ0FBaEMsQ0FBWCxDQUZrQjtLQUZ3QjtBQU81QyxnREFBaUI7QUFDaEIsYUFBTyxFQUFDLE9BQU0sS0FBTixFQUFSLENBRGdCO0tBUDJCOzs7QUFXN0MsWUFBUSxrQkFBVztBQUNqQixVQUFNLE9BQUssS0FBSyxJQUFMLENBRE07QUFFakIsVUFBTSxXQUFXLEtBQUssSUFBTCxHQUFZLGdCQUFNLFFBQU4sQ0FBZSxHQUFmLENBQW1CLEtBQUssS0FBTCxDQUFXLFFBQVgsRUFBcUIsaUJBQVM7QUFBQyxlQUFPLFFBQVEsZ0JBQU0sWUFBTixDQUFtQixLQUFuQixFQUEwQixFQUFDLFVBQUQsRUFBMUIsQ0FBUixHQUE0QyxJQUE1QyxDQUFSO09BQVQsQ0FBcEQsR0FBMkgsSUFBM0gsQ0FGQTtBQUdqQixhQUFROztVQUFLLEtBQUksU0FBSixFQUFMO1FBQW9CLFFBQXBCO09BQVIsQ0FIaUI7S0FBWDtHQVhtQixDQUFyQjs7b0JBa0JTIiwiZmlsZSI6ImNvbXBvbmVudHNcXGVzcmlcXG1hcHZpZXcuanMiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgUmVhY3RET00gZnJvbSAncmVhY3QvcmVhY3QtZG9tJztcclxuaW1wb3J0IE1hcFZpZXcgZnJvbSAnZXNyaS92aWV3cy9NYXBWaWV3JztcclxuaW1wb3J0IGRvbVJlYWR5IGZyb20gXCJkb2pvL2RvbVJlYWR5IVwiO1xyXG5cclxuXHJcbmNvbnN0IFNjZW5lVmlld0NvbXBvbmVudCA9IFJlYWN0LmNyZWF0ZUNsYXNzKHtcclxuXHJcbiBjb21wb25lbnREaWRNb3VudCgpIHtcclxuICAgY29uc3Qgbm9kZSA9IFJlYWN0RE9NLmZpbmRET01Ob2RlKHRoaXMucmVmcy5tYXBWaWV3KTtcclxuICAgdGhpcy52aWV3PSBuZXcgTWFwVmlldyh7Y29udGFpbmVyOiBub2RlLC4uLnRoaXMucHJvcHN9KTtcclxuIH0sXHJcblxyXG4gZ2V0SW5pdGlhbFN0YXRlKCl7XHJcbiAgcmV0dXJuIHtyZWFkeTpmYWxzZX1cclxufSxcclxuXHJcbnJlbmRlcjogZnVuY3Rpb24oKSB7ICBcclxuICBjb25zdCB2aWV3PXRoaXMudmlldztcclxuICBjb25zdCBjaGlsZHJlbiA9IHRoaXMudmlldyA/IFJlYWN0LkNoaWxkcmVuLm1hcCh0aGlzLnByb3BzLmNoaWxkcmVuLCBjaGlsZCA9PiB7cmV0dXJuIGNoaWxkID8gUmVhY3QuY2xvbmVFbGVtZW50KGNoaWxkLCB7dmlld30pIDogbnVsbDt9KSA6IG51bGw7XHJcbiAgcmV0dXJuICg8ZGl2IHJlZj0nbWFwVmlldyc+e2NoaWxkcmVufTwvZGl2Pik7XHJcbn1cclxufSk7XHJcblxyXG5leHBvcnQgZGVmYXVsdCBTY2VuZVZpZXdDb21wb25lbnQ7XHJcbiJdfQ==