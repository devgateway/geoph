define(['exports', './reducer', './actions', './sync', './middleware'], function (exports, _reducer, _actions, _sync, _middleware) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.routerMiddleware = exports.routerActions = exports.goForward = exports.goBack = exports.go = exports.replace = exports.push = exports.CALL_HISTORY_METHOD = exports.routerReducer = exports.LOCATION_CHANGE = exports.syncHistoryWithStore = undefined;
  Object.defineProperty(exports, 'LOCATION_CHANGE', {
    enumerable: true,
    get: function () {
      return _reducer.LOCATION_CHANGE;
    }
  });
  Object.defineProperty(exports, 'routerReducer', {
    enumerable: true,
    get: function () {
      return _reducer.routerReducer;
    }
  });
  Object.defineProperty(exports, 'CALL_HISTORY_METHOD', {
    enumerable: true,
    get: function () {
      return _actions.CALL_HISTORY_METHOD;
    }
  });
  Object.defineProperty(exports, 'push', {
    enumerable: true,
    get: function () {
      return _actions.push;
    }
  });
  Object.defineProperty(exports, 'replace', {
    enumerable: true,
    get: function () {
      return _actions.replace;
    }
  });
  Object.defineProperty(exports, 'go', {
    enumerable: true,
    get: function () {
      return _actions.go;
    }
  });
  Object.defineProperty(exports, 'goBack', {
    enumerable: true,
    get: function () {
      return _actions.goBack;
    }
  });
  Object.defineProperty(exports, 'goForward', {
    enumerable: true,
    get: function () {
      return _actions.goForward;
    }
  });
  Object.defineProperty(exports, 'routerActions', {
    enumerable: true,
    get: function () {
      return _actions.routerActions;
    }
  });

  var _sync2 = _interopRequireDefault(_sync);

  var _middleware2 = _interopRequireDefault(_middleware);

  function _interopRequireDefault(obj) {
    return obj && obj.__esModule ? obj : {
      default: obj
    };
  }

  exports.syncHistoryWithStore = _sync2.default;
  exports.routerMiddleware = _middleware2.default;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInJlYWN0LXJlZHV4LXJvdXRlclxcaW5kZXguanMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7OztzQkFDUzs7Ozs7O3NCQUFpQjs7Ozs7O3NCQUd4Qjs7Ozs7O3NCQUNBOzs7Ozs7c0JBQU07Ozs7OztzQkFBUzs7Ozs7O3NCQUFJOzs7Ozs7c0JBQVE7Ozs7OztzQkFDM0I7Ozs7Ozs7Ozs7Ozs7O1VBTks7VUFRQSIsImZpbGUiOiJyZWFjdC1yZWR1eC1yb3V0ZXJcXGluZGV4LmpzIiwic291cmNlc0NvbnRlbnQiOlsiZXhwb3J0IHN5bmNIaXN0b3J5V2l0aFN0b3JlIGZyb20gJy4vc3luYydcclxuZXhwb3J0IHsgTE9DQVRJT05fQ0hBTkdFLCByb3V0ZXJSZWR1Y2VyIH0gZnJvbSAnLi9yZWR1Y2VyJ1xyXG5cclxuZXhwb3J0IHtcclxuICBDQUxMX0hJU1RPUllfTUVUSE9ELFxyXG4gIHB1c2gsIHJlcGxhY2UsIGdvLCBnb0JhY2ssIGdvRm9yd2FyZCxcclxuICByb3V0ZXJBY3Rpb25zXHJcbn0gZnJvbSAnLi9hY3Rpb25zJ1xyXG5leHBvcnQgcm91dGVyTWlkZGxld2FyZSBmcm9tICcuL21pZGRsZXdhcmUnXHJcbiJdfQ==