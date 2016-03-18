define(['exports', './actions'], function (exports, _actions) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = routerMiddleware;

  function _toConsumableArray(arr) {
    if (Array.isArray(arr)) {
      for (var i = 0, arr2 = Array(arr.length); i < arr.length; i++) {
        arr2[i] = arr[i];
      }

      return arr2;
    } else {
      return Array.from(arr);
    }
  }

  /**
   * This middleware captures CALL_HISTORY_METHOD actions to redirect to the
   * provided history object. This will prevent these actions from reaching your
   * reducer or any middleware that comes after this one.
   */
  function routerMiddleware(history) {
    return function () {
      return function (next) {
        return function (action) {
          if (action.type !== _actions.CALL_HISTORY_METHOD) {
            return next(action);
          }

          var _action$payload = action.payload;
          var method = _action$payload.method;
          var args = _action$payload.args;

          history[method].apply(history, _toConsumableArray(args));
        };
      };
    };
  }
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInJlYWN0LXJlZHV4LXJvdXRlclxcbWlkZGxld2FyZS5qcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7Ozs7b0JBT3dCOzs7Ozs7Ozs7Ozs7Ozs7Ozs7O0FBQVQsV0FBUyxnQkFBVCxDQUEwQixPQUExQixFQUFtQztBQUNoRCxXQUFPO2FBQU07ZUFBUSxrQkFBVTtBQUM3QixjQUFJLE9BQU8sSUFBUCxpQ0FBSixFQUF5QztBQUN2QyxtQkFBTyxLQUFLLE1BQUwsQ0FBUCxDQUR1QztXQUF6Qzs7Z0NBSXNDLE9BQTlCLFFBTHFCO2NBS1YsZ0NBTFU7Y0FLRiw0QkFMRTs7QUFNN0Isa0JBQVEsT0FBUixtQ0FBbUIsS0FBbkIsRUFONkI7U0FBVjtPQUFSO0tBQU4sQ0FEeUM7R0FBbkMiLCJmaWxlIjoicmVhY3QtcmVkdXgtcm91dGVyXFxtaWRkbGV3YXJlLmpzIiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IHsgQ0FMTF9ISVNUT1JZX01FVEhPRCB9IGZyb20gJy4vYWN0aW9ucydcclxuXHJcbi8qKlxyXG4gKiBUaGlzIG1pZGRsZXdhcmUgY2FwdHVyZXMgQ0FMTF9ISVNUT1JZX01FVEhPRCBhY3Rpb25zIHRvIHJlZGlyZWN0IHRvIHRoZVxyXG4gKiBwcm92aWRlZCBoaXN0b3J5IG9iamVjdC4gVGhpcyB3aWxsIHByZXZlbnQgdGhlc2UgYWN0aW9ucyBmcm9tIHJlYWNoaW5nIHlvdXJcclxuICogcmVkdWNlciBvciBhbnkgbWlkZGxld2FyZSB0aGF0IGNvbWVzIGFmdGVyIHRoaXMgb25lLlxyXG4gKi9cclxuZXhwb3J0IGRlZmF1bHQgZnVuY3Rpb24gcm91dGVyTWlkZGxld2FyZShoaXN0b3J5KSB7XHJcbiAgcmV0dXJuICgpID0+IG5leHQgPT4gYWN0aW9uID0+IHtcclxuICAgIGlmIChhY3Rpb24udHlwZSAhPT0gQ0FMTF9ISVNUT1JZX01FVEhPRCkge1xyXG4gICAgICByZXR1cm4gbmV4dChhY3Rpb24pXHJcbiAgICB9XHJcblxyXG4gICAgY29uc3QgeyBwYXlsb2FkOiB7IG1ldGhvZCwgYXJncyB9IH0gPSBhY3Rpb25cclxuICAgIGhpc3RvcnlbbWV0aG9kXSguLi5hcmdzKVxyXG4gIH1cclxufVxyXG4iXX0=