define(['exports', 'react', 'react-router', 'app/components/map/index'], function (exports, _react, _reactRouter, _index) {
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

  var Landing = function (_React$Component) {
    _inherits(Landing, _React$Component);

    function Landing() {
      _classCallCheck(this, Landing);

      return _possibleConstructorReturn(this, Object.getPrototypeOf(Landing).call(this));
    }

    _createClass(Landing, [{
      key: 'render',
      value: function render() {
        return _react2.default.createElement(
          'div',
          { className: 'landing' },
          _react2.default.createElement(
            'div',
            { className: 'main' },
            _react2.default.createElement(_index.Map, null)
          )
        );
      }
    }]);

    return Landing;
  }(_react2.default.Component);

  exports.default = Landing;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGxheW91dFxcbGFuZGluZy5qc3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7O01BSXFCOzs7QUFFbkIsYUFGbUIsT0FFbkIsR0FBYzs0QkFGSyxTQUVMOztvRUFGSyxxQkFFTDtLQUFkOztpQkFGbUI7OytCQU1WO0FBQ1AsZUFBUTs7WUFBSyxXQUFVLFNBQVYsRUFBTDtVQUNFOztjQUFLLFdBQVUsTUFBVixFQUFMO1lBQ0UsK0NBREY7V0FERjtTQUFSLENBRE87Ozs7V0FOVTtJQUFnQixnQkFBTSxTQUFOOztvQkFBaEIiLCJmaWxlIjoiY29tcG9uZW50c1xcbGF5b3V0XFxsYW5kaW5nLmpzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCB7IExpbmsgIH0gZnJvbSAncmVhY3Qtcm91dGVyJztcclxuaW1wb3J0IHtNYXB9IGZyb20gJ2FwcC9jb21wb25lbnRzL21hcC9pbmRleCc7XHJcblxyXG5leHBvcnQgZGVmYXVsdCBjbGFzcyBMYW5kaW5nIGV4dGVuZHMgUmVhY3QuQ29tcG9uZW50IHtcclxuXHJcbiAgY29uc3RydWN0b3IoKSB7XHJcbiAgICBzdXBlcigpO1xyXG4gIH1cclxuXHJcbiAgcmVuZGVyKCkge1xyXG4gICAgcmV0dXJuICg8ZGl2IGNsYXNzTmFtZT1cImxhbmRpbmdcIj5cclxuICAgICAgICAgICAgICA8ZGl2IGNsYXNzTmFtZT1cIm1haW5cIj5cclxuICAgICAgICAgICAgICAgIDxNYXAvPlxyXG4gICAgICAgICAgICAgIDwvZGl2PlxyXG4gICAgICAgICAgICAgICAgXHJcbiAgICAgICAgICAgICAgPC9kaXY+XHJcbiAgICAgICAgICApXHJcbiAgICB9XHJcbn1cclxuIl19