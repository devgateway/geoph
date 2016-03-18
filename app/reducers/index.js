define(['exports', 'redux', './language', './filters', './map', 'react-redux-router'], function (exports, _redux, _language, _filters, _map, _reactReduxRouter) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });

  var _language2 = _interopRequireDefault(_language);

  var _filters2 = _interopRequireDefault(_filters);

  var _map2 = _interopRequireDefault(_map);

  function _interopRequireDefault(obj) {
    return obj && obj.__esModule ? obj : {
      default: obj
    };
  }

  /*reducer names should match with a state property*/

  var geophApp = (0, _redux.combineReducers)({
    language: _language2.default,
    filters: _filters2.default,
    map: _map2.default,
    routing: _reactReduxRouter.routerReducer
  });

  exports.default = geophApp;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInJlZHVjZXJzXFxpbmRleC5qcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFTQSxNQUFNLFdBQVcsNEJBQWdCO0FBQy9CLGdDQUQrQjtBQUUvQiw4QkFGK0I7QUFHL0Isc0JBSCtCO0FBSS9CLDRDQUorQjtHQUFoQixDQUFYOztvQkFPUyIsImZpbGUiOiJyZWR1Y2Vyc1xcaW5kZXguanMiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgeyBjb21iaW5lUmVkdWNlcnMgfSBmcm9tICdyZWR1eCc7XHJcbmltcG9ydCBsYW5ndWFnZSBmcm9tICcuL2xhbmd1YWdlJztcclxuaW1wb3J0IGZpbHRlcnMgZnJvbSAnLi9maWx0ZXJzJztcclxuaW1wb3J0IG1hcCBmcm9tICcuL21hcCc7XHJcblxyXG5pbXBvcnQge3JvdXRlclJlZHVjZXJ9ICBmcm9tICdyZWFjdC1yZWR1eC1yb3V0ZXInO1xyXG5cclxuLypyZWR1Y2VyIG5hbWVzIHNob3VsZCBtYXRjaCB3aXRoIGEgc3RhdGUgcHJvcGVydHkqL1xyXG5cclxuY29uc3QgZ2VvcGhBcHAgPSBjb21iaW5lUmVkdWNlcnMoe1xyXG4gIGxhbmd1YWdlLFxyXG4gIGZpbHRlcnMsXHJcbiAgbWFwLFxyXG4gIHJvdXRpbmc6IHJvdXRlclJlZHVjZXJcclxufSlcclxuXHJcbmV4cG9ydCBkZWZhdWx0IGdlb3BoQXBwIl19