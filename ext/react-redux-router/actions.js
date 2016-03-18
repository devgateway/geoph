define(['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  /**
   * This action type will be dispatched by the history actions below.
   * If you're writing a middleware to watch for navigation events, be sure to
   * look for actions of this type.
   */
  var CALL_HISTORY_METHOD = exports.CALL_HISTORY_METHOD = '@@router/CALL_HISTORY_METHOD';

  function updateLocation(method) {
    return function () {
      for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
        args[_key] = arguments[_key];
      }

      return {
        type: CALL_HISTORY_METHOD,
        payload: { method: method, args: args }
      };
    };
  }

  /**
   * These actions correspond to the history API.
   * The associated routerMiddleware will capture these events before they get to
   * your reducer and reissue them as the matching function on your history.
   */
  var push = exports.push = updateLocation('push');
  var replace = exports.replace = updateLocation('replace');
  var go = exports.go = updateLocation('go');
  var goBack = exports.goBack = updateLocation('goBack');
  var goForward = exports.goForward = updateLocation('goForward');

  var routerActions = exports.routerActions = { push: push, replace: replace, go: go, goBack: goBack, goForward: goForward };
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInJlYWN0LXJlZHV4LXJvdXRlclxcYWN0aW9ucy5qcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7Ozs7Ozs7OztBQUtPLE1BQU0sb0RBQXNCLDhCQUF0Qjs7QUFFYixXQUFTLGNBQVQsQ0FBd0IsTUFBeEIsRUFBZ0M7QUFDOUIsV0FBTzt3Q0FBSTs7OzthQUFVO0FBQ25CLGNBQU0sbUJBQU47QUFDQSxpQkFBUyxFQUFFLGNBQUYsRUFBVSxVQUFWLEVBQVQ7O0tBRkssQ0FEdUI7R0FBaEM7Ozs7Ozs7QUFZTyxNQUFNLHNCQUFPLGVBQWUsTUFBZixDQUFQO0FBQ04sTUFBTSw0QkFBVSxlQUFlLFNBQWYsQ0FBVjtBQUNOLE1BQU0sa0JBQUssZUFBZSxJQUFmLENBQUw7QUFDTixNQUFNLDBCQUFTLGVBQWUsUUFBZixDQUFUO0FBQ04sTUFBTSxnQ0FBWSxlQUFlLFdBQWYsQ0FBWjs7QUFFTixNQUFNLHdDQUFnQixFQUFFLFVBQUYsRUFBUSxnQkFBUixFQUFpQixNQUFqQixFQUFxQixjQUFyQixFQUE2QixvQkFBN0IsRUFBaEIiLCJmaWxlIjoicmVhY3QtcmVkdXgtcm91dGVyXFxhY3Rpb25zLmpzIiwic291cmNlc0NvbnRlbnQiOlsiLyoqXHJcbiAqIFRoaXMgYWN0aW9uIHR5cGUgd2lsbCBiZSBkaXNwYXRjaGVkIGJ5IHRoZSBoaXN0b3J5IGFjdGlvbnMgYmVsb3cuXHJcbiAqIElmIHlvdSdyZSB3cml0aW5nIGEgbWlkZGxld2FyZSB0byB3YXRjaCBmb3IgbmF2aWdhdGlvbiBldmVudHMsIGJlIHN1cmUgdG9cclxuICogbG9vayBmb3IgYWN0aW9ucyBvZiB0aGlzIHR5cGUuXHJcbiAqL1xyXG5leHBvcnQgY29uc3QgQ0FMTF9ISVNUT1JZX01FVEhPRCA9ICdAQHJvdXRlci9DQUxMX0hJU1RPUllfTUVUSE9EJ1xyXG5cclxuZnVuY3Rpb24gdXBkYXRlTG9jYXRpb24obWV0aG9kKSB7XHJcbiAgcmV0dXJuICguLi5hcmdzKSA9PiAoe1xyXG4gICAgdHlwZTogQ0FMTF9ISVNUT1JZX01FVEhPRCxcclxuICAgIHBheWxvYWQ6IHsgbWV0aG9kLCBhcmdzIH1cclxuICB9KVxyXG59XHJcblxyXG4vKipcclxuICogVGhlc2UgYWN0aW9ucyBjb3JyZXNwb25kIHRvIHRoZSBoaXN0b3J5IEFQSS5cclxuICogVGhlIGFzc29jaWF0ZWQgcm91dGVyTWlkZGxld2FyZSB3aWxsIGNhcHR1cmUgdGhlc2UgZXZlbnRzIGJlZm9yZSB0aGV5IGdldCB0b1xyXG4gKiB5b3VyIHJlZHVjZXIgYW5kIHJlaXNzdWUgdGhlbSBhcyB0aGUgbWF0Y2hpbmcgZnVuY3Rpb24gb24geW91ciBoaXN0b3J5LlxyXG4gKi9cclxuZXhwb3J0IGNvbnN0IHB1c2ggPSB1cGRhdGVMb2NhdGlvbigncHVzaCcpXHJcbmV4cG9ydCBjb25zdCByZXBsYWNlID0gdXBkYXRlTG9jYXRpb24oJ3JlcGxhY2UnKVxyXG5leHBvcnQgY29uc3QgZ28gPSB1cGRhdGVMb2NhdGlvbignZ28nKVxyXG5leHBvcnQgY29uc3QgZ29CYWNrID0gdXBkYXRlTG9jYXRpb24oJ2dvQmFjaycpXHJcbmV4cG9ydCBjb25zdCBnb0ZvcndhcmQgPSB1cGRhdGVMb2NhdGlvbignZ29Gb3J3YXJkJylcclxuXHJcbmV4cG9ydCBjb25zdCByb3V0ZXJBY3Rpb25zID0geyBwdXNoLCByZXBsYWNlLCBnbywgZ29CYWNrLCBnb0ZvcndhcmQgfVxyXG4iXX0=