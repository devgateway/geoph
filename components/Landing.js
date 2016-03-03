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
                { to: 'map2d' },
                '2d Map '
              )
            ),
            _react2.default.createElement(
              'li',
              null,
              _react2.default.createElement(
                _reactRouter.Link,
                { to: 'map3d' },
                '3d Map '
              ),
              ' '
            )
          )
        );
      }
    }]);

    return Header;
  }(_react2.default.Component);

  exports.default = Header;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXExhbmRpbmcuanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7OztNQUdxQjs7O0FBRW5CLGFBRm1CLE1BRW5CLEdBQWM7NEJBRkssUUFFTDs7b0VBRkssb0JBRUw7S0FBZDs7aUJBRm1COzsrQkFNVjtBQUNQLGVBQ0U7O1lBQUssV0FBVSxNQUFWLEVBQUw7VUFDQTs7O1lBQ0M7OztjQUFJOztrQkFBTSxJQUFHLE9BQUgsRUFBTjs7ZUFBSjthQUREO1lBRUU7OztjQUFJOztrQkFBTSxJQUFHLE9BQUgsRUFBTjs7ZUFBSjs7YUFGRjtXQURBO1NBREYsQ0FETzs7OztXQU5VO0lBQWUsZ0JBQU0sU0FBTjs7b0JBQWYiLCJmaWxlIjoiY29tcG9uZW50c1xcTGFuZGluZy5qc3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgeyBMaW5rICB9IGZyb20gJ3JlYWN0LXJvdXRlcic7XHJcblxyXG5leHBvcnQgZGVmYXVsdCBjbGFzcyBIZWFkZXIgZXh0ZW5kcyBSZWFjdC5Db21wb25lbnQge1xyXG5cclxuICBjb25zdHJ1Y3RvcigpIHtcclxuICAgIHN1cGVyKCk7XHJcbiAgfVxyXG5cclxuICByZW5kZXIoKSB7XHJcbiAgICByZXR1cm4gKFxyXG4gICAgICA8ZGl2IGNsYXNzTmFtZT1cInJvb3RcIj5cclxuICAgICAgPHVsPlxyXG4gICAgICAgPGxpPjxMaW5rIHRvPVwibWFwMmRcIj4yZCBNYXAgPC9MaW5rPjwvbGk+IFxyXG4gICAgICAgIDxsaT48TGluayB0bz1cIm1hcDNkXCI+M2QgTWFwIDwvTGluaz4gPC9saT5cclxuICAgICAgPC91bD5cclxuICAgICAgPC9kaXY+XHJcbiAgICAgIClcclxuICB9XHJcbn1cclxuIl19