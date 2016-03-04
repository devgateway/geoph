define(['react', 'react-router', 'react/react-dom', 'app/components/Landing', 'app/components/Header', 'app/components/Footer', 'app/components/map'], function (_react, _reactRouter, _reactDom, _Landing, _Header, _Footer, _map) {
  'use strict';

  var _react2 = _interopRequireDefault(_react);

  var _Landing2 = _interopRequireDefault(_Landing);

  var _Header2 = _interopRequireDefault(_Header);

  var _Footer2 = _interopRequireDefault(_Footer);

  var _map2 = _interopRequireDefault(_map);

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
      _react2.default.createElement(_reactRouter.Route, { path: 'map', component: _map2.default })
    ),
    _react2.default.createElement(_reactRouter.Route, { path: '*', component: NoMatch })
  ), document.getElementById('root'));
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIm1haW4uanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7TUFhTzs7Ozs7Ozs7Ozs7K0JBQ0s7QUFDUixlQUNFOztZQUFLLFdBQVUsS0FBVixFQUFMO1VBQ0MscURBREQ7VUFFSSxLQUFLLEtBQUwsQ0FBVyxRQUFYO1VBQ0gscURBSEQ7U0FERixDQURROzs7O1dBREw7SUFBWSxnQkFBTSxTQUFOOztNQWdCYjs7Ozs7Ozs7Ozs7K0JBQ0k7QUFDTixlQUFPOzs7O1NBQVAsQ0FETTs7OztXQURKO0lBQWdCLGdCQUFNLFNBQU47O0FBTXJCLHdCQUNLOztNQUFRLG1DQUFSO0lBRUU7O1FBQU8sTUFBSyxHQUFMLEVBQVMsV0FBVyxHQUFYLEVBQWhCO01BQ0UseURBQVksOEJBQVosQ0FERjtNQUVJLG9EQUFPLE1BQUssS0FBTCxFQUFXLDBCQUFsQixDQUZKO0tBRkY7SUFPQSxvREFBTyxNQUFLLEdBQUwsRUFBUyxXQUFXLE9BQVgsRUFBaEIsQ0FQQTtHQURMLEVBV1EsU0FBUyxjQUFULENBQXdCLE1BQXhCLENBWFIiLCJmaWxlIjoibWFpbi5qc3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgeyBSb3V0ZXIsIFJvdXRlICxSZWRpcmVjdCxJbmRleFJvdXRlICxoYXNoSGlzdG9yeX0gZnJvbSAncmVhY3Qtcm91dGVyJztcclxuaW1wb3J0IHsgcmVuZGVyIH0gZnJvbSAncmVhY3QvcmVhY3QtZG9tJztcclxuaW1wb3J0IExhbmRpbmcgZnJvbSAnYXBwL2NvbXBvbmVudHMvTGFuZGluZyc7XHJcbmltcG9ydCBIZWFkZXIgZnJvbSAnYXBwL2NvbXBvbmVudHMvSGVhZGVyJ1xyXG5pbXBvcnQgRm9vdGVyIGZyb20gJ2FwcC9jb21wb25lbnRzL0Zvb3RlcidcclxuaW1wb3J0IE1hcCBmcm9tICdhcHAvY29tcG9uZW50cy9tYXAnO1xyXG5cclxuLypMYXlvdXQgZWxlbWVudHMqL1xyXG5cclxuLyoqXHJcbiAqIFJvb3Qgdmlld1xyXG4gKi9cclxuIGNsYXNzIEFwcCBleHRlbmRzIFJlYWN0LkNvbXBvbmVudCB7XHJcbiAgIHJlbmRlcigpIHtcclxuICAgIHJldHVybiAoXHJcbiAgICAgIDxkaXYgY2xhc3NOYW1lPVwiYXBwXCI+XHJcbiAgICAgICA8SGVhZGVyLz5cclxuICAgICAgICAge3RoaXMucHJvcHMuY2hpbGRyZW59XHJcbiAgICAgICA8Rm9vdGVyLz5cclxuICAgICAgPC9kaXY+XHJcbiAgICAgIClcclxuICB9XHJcbn1cclxuXHJcblxyXG4vKlxyXG5Ob3QgZm91bmQgdmlld1xyXG4qL1xyXG5jbGFzcyBOb01hdGNoIGV4dGVuZHMgUmVhY3QuQ29tcG9uZW50e1xyXG4gIHJlbmRlcigpe1xyXG4gICAgcmV0dXJuIDxoMT5Ob3QgZm91bmQ8L2gxPlxyXG4gIH1cclxufVxyXG5cclxuIHJlbmRlcigoXHJcbiAgICAgIDxSb3V0ZXIgaGlzdG9yeT17aGFzaEhpc3Rvcnl9ID5cclxuXHJcbiAgICAgICAgPFJvdXRlIHBhdGg9XCIvXCIgY29tcG9uZW50PXtBcHB9PlxyXG4gICAgICAgICAgPEluZGV4Um91dGUgY29tcG9uZW50PXtMYW5kaW5nfSAvPlxyXG4gICAgICAgICAgICA8Um91dGUgcGF0aD1cIm1hcFwiIGNvbXBvbmVudD17TWFwfS8+XHJcbiAgICAgICAgICAgIFxyXG4gICAgICAgIDwvUm91dGU+XHJcbiAgICAgIDxSb3V0ZSBwYXRoPVwiKlwiIGNvbXBvbmVudD17Tm9NYXRjaH0vPlxyXG5cclxuICAgICAgPC9Sb3V0ZXI+XHJcbiAgICAgICksIGRvY3VtZW50LmdldEVsZW1lbnRCeUlkKCdyb290JykpXHJcblxyXG4iXX0=