define(['exports', 'redux', 'redux-thunk', 'react-redux-router', 'app/reducers/index'], function (exports, _redux, _reduxThunk, _reactReduxRouter, _index) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = configureStore;

  var _reduxThunk2 = _interopRequireDefault(_reduxThunk);

  var _index2 = _interopRequireDefault(_index);

  function _interopRequireDefault(obj) {
    return obj && obj.__esModule ? obj : {
      default: obj
    };
  }

  function configureStore(initialState, browserHistory) {

    var historyMiddleware = (0, _reactReduxRouter.routerMiddleware)(browserHistory);
    var middleware = (0, _redux.applyMiddleware)(_reduxThunk2.default, historyMiddleware);
    var store = middleware(_redux.createStore)(_index2.default, initialState);

    return store;
  }
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInN0b3JlXFxjb25maWd1cmVTdG9yZS5qcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7Ozs7b0JBUXdCOzs7Ozs7Ozs7Ozs7QUFBVCxXQUFTLGNBQVQsQ0FBd0IsWUFBeEIsRUFBc0MsY0FBdEMsRUFBc0Q7O0FBRW5FLFFBQUksb0JBQW9CLHdDQUFpQixjQUFqQixDQUFwQixDQUYrRDtBQUduRSxRQUFJLGFBQWEsa0RBQWlDLGlCQUFqQyxDQUFiLENBSCtEO0FBSW5FLFFBQU0sUUFBUSxnREFBcUMsWUFBckMsQ0FBUixDQUo2RDs7QUFNbkUsV0FBTyxLQUFQLENBTm1FO0dBQXREIiwiZmlsZSI6InN0b3JlXFxjb25maWd1cmVTdG9yZS5qcyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7IGFwcGx5TWlkZGxld2FyZSwgY29tcG9zZSwgY3JlYXRlU3RvcmUgfSBmcm9tICdyZWR1eCc7XHJcblxyXG5pbXBvcnQgdGh1bmtNaWRkbGV3YXJlIGZyb20gJ3JlZHV4LXRodW5rJztcclxuXHJcbmltcG9ydCB7cm91dGVyTWlkZGxld2FyZX0gZnJvbSAncmVhY3QtcmVkdXgtcm91dGVyJztcclxuXHJcbmltcG9ydCByb290UmVkdWNlciBmcm9tICdhcHAvcmVkdWNlcnMvaW5kZXgnO1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgZnVuY3Rpb24gY29uZmlndXJlU3RvcmUoaW5pdGlhbFN0YXRlLCBicm93c2VySGlzdG9yeSkge1xyXG4gIFxyXG4gIGxldCBoaXN0b3J5TWlkZGxld2FyZSA9IHJvdXRlck1pZGRsZXdhcmUoYnJvd3Nlckhpc3RvcnkpO1xyXG4gIGxldCBtaWRkbGV3YXJlID0gYXBwbHlNaWRkbGV3YXJlKHRodW5rTWlkZGxld2FyZSwgaGlzdG9yeU1pZGRsZXdhcmUpO1xyXG4gIGNvbnN0IHN0b3JlID0gbWlkZGxld2FyZShjcmVhdGVTdG9yZSkocm9vdFJlZHVjZXIsIGluaXRpYWxTdGF0ZSk7XHJcbiAgXHJcbiAgcmV0dXJuIHN0b3JlO1xyXG59Il19