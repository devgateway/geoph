define(['exports', 'react', 'react-router', 'app/components/filter/filterPopup', 'app/components/controls/layer'], function (exports, _react, _reactRouter, _filterPopup, _layer) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });

  var _react2 = _interopRequireDefault(_react);

  var _filterPopup2 = _interopRequireDefault(_filterPopup);

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
      key: 'levelChanged',
      value: function levelChanged(evt) {
        alert(evt.target.value);
      }
    }, {
      key: 'render',
      value: function render() {
        return _react2.default.createElement(
          'div',
          { className: 'panel' },
          _react2.default.createElement(_filterPopup2.default, null),
          _react2.default.createElement(_layer.LayerControl, null)
        );
      }
    }]);

    return Header;
  }(_react2.default.Component);

  exports.default = Header;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGxheW91dFxccGFuZWwuanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7O01BSXFCOzs7QUFFbkIsYUFGbUIsTUFFbkIsR0FBYzs0QkFGSyxRQUVMOztvRUFGSyxvQkFFTDtLQUFkOztpQkFGbUI7O21DQU9OLEtBQUk7QUFDZixjQUFNLElBQUksTUFBSixDQUFXLEtBQVgsQ0FBTixDQURlOzs7OytCQUlSO0FBQ1AsZUFDRTs7WUFBSyxXQUFVLE9BQVYsRUFBTDtVQUNBLDBEQURBO1VBR0Esd0RBSEE7U0FERixDQURPOzs7O1dBWFU7SUFBZSxnQkFBTSxTQUFOOztvQkFBZiIsImZpbGUiOiJjb21wb25lbnRzXFxsYXlvdXRcXHBhbmVsLmpzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCB7IExpbmsgIH0gZnJvbSAncmVhY3Qtcm91dGVyJztcclxuaW1wb3J0IEZpbHRlclBvcHVwIGZyb20gJ2FwcC9jb21wb25lbnRzL2ZpbHRlci9maWx0ZXJQb3B1cCdcclxuaW1wb3J0IHtMYXllckNvbnRyb2x9IGZyb20gJ2FwcC9jb21wb25lbnRzL2NvbnRyb2xzL2xheWVyJztcclxuZXhwb3J0IGRlZmF1bHQgY2xhc3MgSGVhZGVyIGV4dGVuZHMgUmVhY3QuQ29tcG9uZW50IHtcclxuXHJcbiAgY29uc3RydWN0b3IoKSB7XHJcbiAgICBzdXBlcigpO1xyXG4gIH1cclxuXHJcblxyXG4gIGxldmVsQ2hhbmdlZChldnQpe1xyXG4gICAgYWxlcnQoZXZ0LnRhcmdldC52YWx1ZSk7XHJcbiAgfVxyXG5cclxuICByZW5kZXIoKSB7XHJcbiAgICByZXR1cm4gKFxyXG4gICAgICA8ZGl2IGNsYXNzTmFtZT1cInBhbmVsXCI+XHJcbiAgICAgIDxGaWx0ZXJQb3B1cC8+XHJcblxyXG4gICAgICA8TGF5ZXJDb250cm9sLz5cclxuICAgICAgPC9kaXY+XHJcbiAgICAgIClcclxuICB9XHJcbn1cclxuIl19