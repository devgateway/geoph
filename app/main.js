define(['babel-polyfill', 'react', 'react/react-dom', 'i18next', 'i18next-xhr-backend', 'react-redux', 'app/store/configureStore', 'react-router', 'react-redux-router', 'app/routes', 'app/util/ajax', 'app/util/setting'], function (_babelPolyfill, _react, _reactDom, _i18next, _i18nextXhrBackend, _reactRedux, _configureStore, _reactRouter, _reactReduxRouter, _routes, _ajax, _setting) {
  'use strict';

  var _babelPolyfill2 = _interopRequireDefault(_babelPolyfill);

  var _react2 = _interopRequireDefault(_react);

  var _i18next2 = _interopRequireDefault(_i18next);

  var _i18nextXhrBackend2 = _interopRequireDefault(_i18nextXhrBackend);

  var _configureStore2 = _interopRequireDefault(_configureStore);

  var _routes2 = _interopRequireDefault(_routes);

  var _ajax2 = _interopRequireDefault(_ajax);

  var _setting2 = _interopRequireDefault(_setting);

  function _interopRequireDefault(obj) {
    return obj && obj.__esModule ? obj : {
      default: obj
    };
  }

  //import es6Promise from 'es6-promise';


  var store = (0, _configureStore2.default)({}, _reactRouter.browserHistory);
  var history = (0, _reactReduxRouter.syncHistoryWithStore)(_reactRouter.browserHistory, store);

  _ajax2.default.get('conf/settings.json').then(function (conf) {
    _setting2.default.initialize(conf.data);
    var options = _setting2.default.get('I18N', 'OPTIONS');

    _i18next2.default.use(_i18nextXhrBackend2.default).init(options, function (err, t) {
      (0, _reactDom.render)(_react2.default.createElement(
        _reactRedux.Provider,
        { store: store },
        _react2.default.createElement(_reactRouter.Router, { history: history, routes: _routes2.default })
      ), document.getElementById('root'));
    });
  });
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIm1haW4uanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFvQkEsTUFBTSxRQUFRLDhCQUFlLEVBQWYsOEJBQVI7QUFDTixNQUFNLFVBQVUseUVBQXFDLEtBQXJDLENBQVY7O0FBRU4saUJBQVMsR0FBVCxDQUFhLG9CQUFiLEVBQW1DLElBQW5DLENBQXdDLFVBQUMsSUFBRCxFQUFRO0FBQzlDLHNCQUFRLFVBQVIsQ0FBbUIsS0FBSyxJQUFMLENBQW5CLENBRDhDO0FBRTlDLFFBQU0sVUFBVSxrQkFBUSxHQUFSLENBQVksTUFBWixFQUFvQixTQUFwQixDQUFWLENBRndDOztBQUk5QyxzQkFBUSxHQUFSLDhCQUFpQixJQUFqQixDQUFzQixPQUF0QixFQUErQixVQUFDLEdBQUQsRUFBTSxDQUFOLEVBQVk7QUFDekMsNEJBQ0U7O1VBQVUsT0FBTyxLQUFQLEVBQVY7UUFDRSxxREFBUSxTQUFTLE9BQVQsRUFBa0IsMEJBQTFCLENBREY7T0FERixFQUlLLFNBQVMsY0FBVCxDQUF3QixNQUF4QixDQUpMLEVBRHlDO0tBQVosQ0FBL0IsQ0FKOEM7R0FBUixDQUF4QyIsImZpbGUiOiJtYWluLmpzeCIsInNvdXJjZXNDb250ZW50IjpbIi8vaW1wb3J0IGVzNlByb21pc2UgZnJvbSAnZXM2LXByb21pc2UnO1xyXG5pbXBvcnQgYmFiZWxQb2x5ZmlsbCBmcm9tICdiYWJlbC1wb2x5ZmlsbCc7XHJcblxyXG5pbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgeyByZW5kZXIgfSBmcm9tICdyZWFjdC9yZWFjdC1kb20nO1xyXG5cclxuaW1wb3J0IGkxOG5leHQgZnJvbSAnaTE4bmV4dCc7XHJcbmltcG9ydCBYSFIgZnJvbSAnaTE4bmV4dC14aHItYmFja2VuZCc7XHJcblxyXG5pbXBvcnQgeyBQcm92aWRlciB9IGZyb20gJ3JlYWN0LXJlZHV4J1xyXG5pbXBvcnQgY29uZmlndXJlU3RvcmUgZnJvbSAnYXBwL3N0b3JlL2NvbmZpZ3VyZVN0b3JlJztcclxuXHJcbmltcG9ydCB7IFJvdXRlcixicm93c2VySGlzdG9yeSB9IGZyb20gJ3JlYWN0LXJvdXRlcic7XHJcbmltcG9ydCB7c3luY0hpc3RvcnlXaXRoU3RvcmV9IGZyb20gJ3JlYWN0LXJlZHV4LXJvdXRlcic7XHJcbmltcG9ydCByb3V0ZXMgZnJvbSAnYXBwL3JvdXRlcyc7XHJcblxyXG5pbXBvcnQgQWpheFV0aWwgZnJvbSAnYXBwL3V0aWwvYWpheCc7XHJcbmltcG9ydCBTZXR0aW5nIGZyb20gJ2FwcC91dGlsL3NldHRpbmcnO1xyXG5cclxuXHJcbmNvbnN0IHN0b3JlID0gY29uZmlndXJlU3RvcmUoe30sIGJyb3dzZXJIaXN0b3J5KTtcclxuY29uc3QgaGlzdG9yeSA9IHN5bmNIaXN0b3J5V2l0aFN0b3JlKGJyb3dzZXJIaXN0b3J5LCBzdG9yZSk7XHJcblxyXG5BamF4VXRpbC5nZXQoJ2NvbmYvc2V0dGluZ3MuanNvbicpLnRoZW4oKGNvbmYpPT57XHJcbiAgU2V0dGluZy5pbml0aWFsaXplKGNvbmYuZGF0YSk7XHJcbiAgY29uc3Qgb3B0aW9ucyA9IFNldHRpbmcuZ2V0KCdJMThOJywgJ09QVElPTlMnKTtcclxuXHJcbiAgaTE4bmV4dC51c2UoWEhSKS5pbml0KG9wdGlvbnMsIChlcnIsIHQpID0+IHtcclxuICAgIHJlbmRlcigoXHJcbiAgICAgIDxQcm92aWRlciBzdG9yZT17c3RvcmV9PlxyXG4gICAgICAgIDxSb3V0ZXIgaGlzdG9yeT17aGlzdG9yeX0gcm91dGVzPXtyb3V0ZXN9IC8+XHJcbiAgICAgIDwvUHJvdmlkZXI+XHJcbiAgICAgICksIGRvY3VtZW50LmdldEVsZW1lbnRCeUlkKCdyb290JykpXHJcbiAgfSk7XHJcblxyXG59KVxyXG5cclxuIl19