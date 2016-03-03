define(['react', 'react-router', 'react/react-dom', 'app/components/Landing', 'app/components/Header', 'app/components/Footer', 'app/components/map2d', 'app/components/map3d'], function (_react, _reactRouter, _reactDom, _Landing, _Header, _Footer, _map2d, _map3d) {
  'use strict';

  var _react2 = _interopRequireDefault(_react);

  var _Landing2 = _interopRequireDefault(_Landing);

  var _Header2 = _interopRequireDefault(_Header);

  var _Footer2 = _interopRequireDefault(_Footer);

  var _map2d2 = _interopRequireDefault(_map2d);

  var _map3d2 = _interopRequireDefault(_map3d);

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

  var App = function (_React$Component) {
    _inherits(App, _React$Component);

    function App() {
      _classCallCheck(this, App);

      return _possibleConstructorReturn(this, Object.getPrototypeOf(App).apply(this, arguments));
    }

    _createClass(App, [{
      key: 'render',
      value: function render() {
        return _react2.default.createElement(
          'div',
          { className: 'app' },
          _react2.default.createElement(_Header2.default, null),
          this.props.children,
          _react2.default.createElement(_Footer2.default, null)
        );
      }
    }]);

    return App;
  }(_react2.default.Component);

  var NoMatch = function (_React$Component2) {
    _inherits(NoMatch, _React$Component2);

    function NoMatch() {
      _classCallCheck(this, NoMatch);

      return _possibleConstructorReturn(this, Object.getPrototypeOf(NoMatch).apply(this, arguments));
    }

    _createClass(NoMatch, [{
      key: 'render',
      value: function render() {
        return _react2.default.createElement(
          'h1',
          null,
          'Not found'
        );
      }
    }]);

    return NoMatch;
  }(_react2.default.Component);

  (0, _reactDom.render)(_react2.default.createElement(
    _reactRouter.Router,
    { history: _reactRouter.hashHistory },
    _react2.default.createElement(
      _reactRouter.Route,
      { path: '/', component: App },
      _react2.default.createElement(_reactRouter.IndexRoute, { component: _Landing2.default }),
      _react2.default.createElement(_reactRouter.Route, { path: 'map2d', component: _map2d2.default }),
      _react2.default.createElement(_reactRouter.Route, { path: 'map3d', component: _map3d2.default })
    ),
    _react2.default.createElement(_reactRouter.Route, { path: '*', component: NoMatch })
  ), document.getElementById('root'));
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIm1haW4uanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7OztNQWFPOzs7Ozs7Ozs7OzsrQkFDSztBQUNSLGVBQ0U7O1lBQUssV0FBVSxLQUFWLEVBQUw7VUFDQyxxREFERDtVQUVJLEtBQUssS0FBTCxDQUFXLFFBQVg7VUFDSCxxREFIRDtTQURGLENBRFE7Ozs7V0FETDtJQUFZLGdCQUFNLFNBQU47O01BZ0JiOzs7Ozs7Ozs7OzsrQkFDSTtBQUNOLGVBQU87Ozs7U0FBUCxDQURNOzs7O1dBREo7SUFBZ0IsZ0JBQU0sU0FBTjs7QUFNckIsd0JBQ0s7O01BQVEsbUNBQVI7SUFFRTs7UUFBTyxNQUFLLEdBQUwsRUFBUyxXQUFXLEdBQVgsRUFBaEI7TUFDRSx5REFBWSw4QkFBWixDQURGO01BRUksb0RBQU8sTUFBSyxPQUFMLEVBQWEsNEJBQXBCLENBRko7TUFHSSxvREFBTyxNQUFLLE9BQUwsRUFBYSw0QkFBcEIsQ0FISjtLQUZGO0lBT0Esb0RBQU8sTUFBSyxHQUFMLEVBQVMsV0FBVyxPQUFYLEVBQWhCLENBUEE7R0FETCxFQVdRLFNBQVMsY0FBVCxDQUF3QixNQUF4QixDQVhSIiwiZmlsZSI6Im1haW4uanN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IHsgUm91dGVyLCBSb3V0ZSAsUmVkaXJlY3QsSW5kZXhSb3V0ZSAsaGFzaEhpc3Rvcnl9IGZyb20gJ3JlYWN0LXJvdXRlcic7XHJcbmltcG9ydCB7IHJlbmRlciB9IGZyb20gJ3JlYWN0L3JlYWN0LWRvbSc7XHJcbmltcG9ydCBMYW5kaW5nIGZyb20gJ2FwcC9jb21wb25lbnRzL0xhbmRpbmcnO1xyXG5pbXBvcnQgSGVhZGVyIGZyb20gJ2FwcC9jb21wb25lbnRzL0hlYWRlcidcclxuaW1wb3J0IEZvb3RlciBmcm9tICdhcHAvY29tcG9uZW50cy9Gb290ZXInXHJcbmltcG9ydCBNYXAyZCBmcm9tICdhcHAvY29tcG9uZW50cy9tYXAyZCc7XHJcbmltcG9ydCBNYXAzZCBmcm9tICdhcHAvY29tcG9uZW50cy9tYXAzZCc7XHJcbi8qTGF5b3V0IGVsZW1lbnRzKi9cclxuXHJcbi8qKlxyXG4gKiBSb290IHZpZXdcclxuICovXHJcbiBjbGFzcyBBcHAgZXh0ZW5kcyBSZWFjdC5Db21wb25lbnQge1xyXG4gICByZW5kZXIoKSB7XHJcbiAgICByZXR1cm4gKFxyXG4gICAgICA8ZGl2IGNsYXNzTmFtZT1cImFwcFwiPlxyXG4gICAgICAgPEhlYWRlci8+XHJcbiAgICAgICAgIHt0aGlzLnByb3BzLmNoaWxkcmVufVxyXG4gICAgICAgPEZvb3Rlci8+XHJcbiAgICAgIDwvZGl2PlxyXG4gICAgICApXHJcbiAgfVxyXG59XHJcblxyXG5cclxuLypcclxuTm90IGZvdW5kIHZpZXdcclxuKi9cclxuY2xhc3MgTm9NYXRjaCBleHRlbmRzIFJlYWN0LkNvbXBvbmVudHtcclxuICByZW5kZXIoKXtcclxuICAgIHJldHVybiA8aDE+Tm90IGZvdW5kPC9oMT5cclxuICB9XHJcbn1cclxuXHJcbiByZW5kZXIoKFxyXG4gICAgICA8Um91dGVyIGhpc3Rvcnk9e2hhc2hIaXN0b3J5fSA+XHJcblxyXG4gICAgICAgIDxSb3V0ZSBwYXRoPVwiL1wiIGNvbXBvbmVudD17QXBwfT5cclxuICAgICAgICAgIDxJbmRleFJvdXRlIGNvbXBvbmVudD17TGFuZGluZ30gLz5cclxuICAgICAgICAgICAgPFJvdXRlIHBhdGg9XCJtYXAyZFwiIGNvbXBvbmVudD17TWFwMmR9Lz5cclxuICAgICAgICAgICAgPFJvdXRlIHBhdGg9XCJtYXAzZFwiIGNvbXBvbmVudD17TWFwM2R9Lz5cclxuICAgICAgICA8L1JvdXRlPlxyXG4gICAgICA8Um91dGUgcGF0aD1cIipcIiBjb21wb25lbnQ9e05vTWF0Y2h9Lz5cclxuXHJcbiAgICAgIDwvUm91dGVyPlxyXG4gICAgICApLCBkb2N1bWVudC5nZXRFbGVtZW50QnlJZCgncm9vdCcpKVxyXG5cclxuIl19