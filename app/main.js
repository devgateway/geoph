define(['babel-polyfill', 'react', 'react/react-dom', 'i18next', 'i18next-xhr-backend', 'react-redux', 'app/store/configureStore', 'react-router', 'react-redux-router', 'app/routes', 'app/util/AjaxUtil', 'app/util/settings'], function (_babelPolyfill, _react, _reactDom, _i18next, _i18nextXhrBackend, _reactRedux, _configureStore, _reactRouter, _reactReduxRouter, _routes, _AjaxUtil, _settings) {
  'use strict';

  var _babelPolyfill2 = _interopRequireDefault(_babelPolyfill);

  var _react2 = _interopRequireDefault(_react);

  var _i18next2 = _interopRequireDefault(_i18next);

  var _i18nextXhrBackend2 = _interopRequireDefault(_i18nextXhrBackend);

  var _configureStore2 = _interopRequireDefault(_configureStore);

  var _routes2 = _interopRequireDefault(_routes);

  var _AjaxUtil2 = _interopRequireDefault(_AjaxUtil);

  var _settings2 = _interopRequireDefault(_settings);

  function _interopRequireDefault(obj) {
    return obj && obj.__esModule ? obj : {
      default: obj
    };
  }

  //import es6Promise from 'es6-promise';


  var store = (0, _configureStore2.default)({}, _reactRouter.browserHistory);
  var history = (0, _reactReduxRouter.syncHistoryWithStore)(_reactRouter.browserHistory, store);

  _AjaxUtil2.default.get('conf/settings.json').then(function (conf) {
    _settings2.default.initialize(conf.data);
    var options = _settings2.default.get('I18N', 'OPTIONS');

    _i18next2.default.use(_i18nextXhrBackend2.default).init(options, function (err, t) {
      (0, _reactDom.render)(_react2.default.createElement(
        _reactRedux.Provider,
        { store: store },
        _react2.default.createElement(_reactRouter.Router, { history: history, routes: _routes2.default })
      ), document.getElementById('root'));
    });
  });
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIm1haW4uanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFvQkEsTUFBTSxRQUFRLDhCQUFlLEVBQWYsOEJBQVI7QUFDTixNQUFNLFVBQVUseUVBQXFDLEtBQXJDLENBQVY7O0FBRU4scUJBQVMsR0FBVCxDQUFhLG9CQUFiLEVBQW1DLElBQW5DLENBQXdDLFVBQUMsSUFBRCxFQUFRO0FBQzlDLHVCQUFRLFVBQVIsQ0FBbUIsS0FBSyxJQUFMLENBQW5CLENBRDhDO0FBRTlDLFFBQU0sVUFBVSxtQkFBUSxHQUFSLENBQVksTUFBWixFQUFvQixTQUFwQixDQUFWLENBRndDOztBQUk5QyxzQkFBUSxHQUFSLDhCQUFpQixJQUFqQixDQUFzQixPQUF0QixFQUErQixVQUFDLEdBQUQsRUFBTSxDQUFOLEVBQVk7QUFDekMsNEJBQ0U7O1VBQVUsT0FBTyxLQUFQLEVBQVY7UUFDRSxxREFBUSxTQUFTLE9BQVQsRUFBa0IsMEJBQTFCLENBREY7T0FERixFQUlLLFNBQVMsY0FBVCxDQUF3QixNQUF4QixDQUpMLEVBRHlDO0tBQVosQ0FBL0IsQ0FKOEM7R0FBUixDQUF4QyIsImZpbGUiOiJtYWluLmpzeCIsInNvdXJjZXNDb250ZW50IjpbIi8vaW1wb3J0IGVzNlByb21pc2UgZnJvbSAnZXM2LXByb21pc2UnO1xyXG5pbXBvcnQgYmFiZWxQb2x5ZmlsbCBmcm9tICdiYWJlbC1wb2x5ZmlsbCc7XHJcblxyXG5pbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgeyByZW5kZXIgfSBmcm9tICdyZWFjdC9yZWFjdC1kb20nO1xyXG5cclxuaW1wb3J0IGkxOG5leHQgZnJvbSAnaTE4bmV4dCc7XHJcbmltcG9ydCBYSFIgZnJvbSAnaTE4bmV4dC14aHItYmFja2VuZCc7XHJcblxyXG5pbXBvcnQgeyBQcm92aWRlciB9IGZyb20gJ3JlYWN0LXJlZHV4J1xyXG5pbXBvcnQgY29uZmlndXJlU3RvcmUgZnJvbSAnYXBwL3N0b3JlL2NvbmZpZ3VyZVN0b3JlJztcclxuXHJcbmltcG9ydCB7IFJvdXRlcixicm93c2VySGlzdG9yeSB9IGZyb20gJ3JlYWN0LXJvdXRlcic7XHJcbmltcG9ydCB7c3luY0hpc3RvcnlXaXRoU3RvcmV9IGZyb20gJ3JlYWN0LXJlZHV4LXJvdXRlcic7XHJcbmltcG9ydCByb3V0ZXMgZnJvbSAnYXBwL3JvdXRlcyc7XHJcblxyXG5pbXBvcnQgQWpheFV0aWwgZnJvbSAnYXBwL3V0aWwvQWpheFV0aWwnO1xyXG5pbXBvcnQgU2V0dGluZyBmcm9tICdhcHAvdXRpbC9zZXR0aW5ncyc7XHJcblxyXG5cclxuY29uc3Qgc3RvcmUgPSBjb25maWd1cmVTdG9yZSh7fSwgYnJvd3Nlckhpc3RvcnkpO1xyXG5jb25zdCBoaXN0b3J5ID0gc3luY0hpc3RvcnlXaXRoU3RvcmUoYnJvd3Nlckhpc3RvcnksIHN0b3JlKTtcclxuXHJcbkFqYXhVdGlsLmdldCgnY29uZi9zZXR0aW5ncy5qc29uJykudGhlbigoY29uZik9PntcclxuICBTZXR0aW5nLmluaXRpYWxpemUoY29uZi5kYXRhKTtcclxuICBjb25zdCBvcHRpb25zID0gU2V0dGluZy5nZXQoJ0kxOE4nLCAnT1BUSU9OUycpO1xyXG5cclxuICBpMThuZXh0LnVzZShYSFIpLmluaXQob3B0aW9ucywgKGVyciwgdCkgPT4ge1xyXG4gICAgcmVuZGVyKChcclxuICAgICAgPFByb3ZpZGVyIHN0b3JlPXtzdG9yZX0+XHJcbiAgICAgICAgPFJvdXRlciBoaXN0b3J5PXtoaXN0b3J5fSByb3V0ZXM9e3JvdXRlc30gLz5cclxuICAgICAgPC9Qcm92aWRlcj5cclxuICAgICAgKSwgZG9jdW1lbnQuZ2V0RWxlbWVudEJ5SWQoJ3Jvb3QnKSlcclxuICB9KTtcclxuXHJcbn0pXHJcblxyXG4iXX0=