define(['exports', 'react', 'react-router'], function (exports, _react, _reactRouter) {
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

  var Header = function (_React$Component) {
    _inherits(Header, _React$Component);

    function Header() {
      _classCallCheck(this, Header);

      return _possibleConstructorReturn(this, Object.getPrototypeOf(Header).call(this));
    }

    _createClass(Header, [{
      key: 'render',
      value: function render() {
        return _react2.default.createElement(
          'div',
          { className: 'root' },
          _react2.default.createElement(
            'ul',
            null,
            _react2.default.createElement(
              'li',
              null,
              _react2.default.createElement(
                _reactRouter.Link,
                { to: 'map' },
                'Go to map'
              )
            )
          )
        );
      }
    }]);

    return Header;
  }(_react2.default.Component);

  exports.default = Header;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXExhbmRpbmcuanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7OztNQUdxQjs7O0FBRW5CLGFBRm1CLE1BRW5CLEdBQWM7NEJBRkssUUFFTDs7b0VBRkssb0JBRUw7S0FBZDs7aUJBRm1COzsrQkFNVjtBQUNQLGVBQ0U7O1lBQUssV0FBVSxNQUFWLEVBQUw7VUFDQTs7O1lBQ0M7OztjQUFJOztrQkFBTSxJQUFHLEtBQUgsRUFBTjs7ZUFBSjthQUREO1dBREE7U0FERixDQURPOzs7O1dBTlU7SUFBZSxnQkFBTSxTQUFOOztvQkFBZiIsImZpbGUiOiJjb21wb25lbnRzXFxMYW5kaW5nLmpzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCB7IExpbmsgIH0gZnJvbSAncmVhY3Qtcm91dGVyJztcclxuXHJcbmV4cG9ydCBkZWZhdWx0IGNsYXNzIEhlYWRlciBleHRlbmRzIFJlYWN0LkNvbXBvbmVudCB7XHJcblxyXG4gIGNvbnN0cnVjdG9yKCkge1xyXG4gICAgc3VwZXIoKTtcclxuICB9XHJcblxyXG4gIHJlbmRlcigpIHtcclxuICAgIHJldHVybiAoXHJcbiAgICAgIDxkaXYgY2xhc3NOYW1lPVwicm9vdFwiPlxyXG4gICAgICA8dWw+XHJcbiAgICAgICA8bGk+PExpbmsgdG89XCJtYXBcIj5HbyB0byBtYXA8L0xpbms+PC9saT4gXHJcbiAgICAgICA8L3VsPlxyXG4gICAgICA8L2Rpdj5cclxuICAgICAgKVxyXG4gIH1cclxufVxyXG4iXX0=