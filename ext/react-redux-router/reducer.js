define(['exports'], function (exports) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.routerReducer = routerReducer;

  var _extends = Object.assign || function (target) {
    for (var i = 1; i < arguments.length; i++) {
      var source = arguments[i];

      for (var key in source) {
        if (Object.prototype.hasOwnProperty.call(source, key)) {
          target[key] = source[key];
        }
      }
    }

    return target;
  };

  /**
   * This action type will be dispatched when your history
   * receives a location change.
   */
  var LOCATION_CHANGE = exports.LOCATION_CHANGE = '@@router/LOCATION_CHANGE';

  var initialState = {
    locationBeforeTransitions: null
  };

  /**
   * This reducer will update the state with the most recent location history
   * has transitioned to. This may not be in sync with the router, particularly
   * if you have asynchronously-loaded routes, so reading from and relying on
   * this state it is discouraged.
   */
  function routerReducer() {
    var state = arguments.length <= 0 || arguments[0] === undefined ? initialState : arguments[0];
    var _ref = arguments[1];
    var type = _ref.type;
    var payload = _ref.payload;

    if (type === LOCATION_CHANGE) {
      return _extends({}, state, { locationBeforeTransitions: payload });
    }

    return state;
  }
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInJlYWN0LXJlZHV4LXJvdXRlclxccmVkdWNlci5qcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7Ozs7VUFnQmdCOzs7Ozs7Ozs7Ozs7Ozs7Ozs7OztBQVpULE1BQU0sNENBQWtCLDBCQUFsQjs7QUFFYixNQUFNLGVBQWU7QUFDbkIsK0JBQTJCLElBQTNCO0dBREk7Ozs7Ozs7O0FBVUMsV0FBUyxhQUFULEdBQWdFO1FBQXpDLDhEQUFRLDRCQUFpQzs7UUFBakIsaUJBQWlCO1FBQVgsdUJBQVc7O0FBQ3JFLFFBQUksU0FBUyxlQUFULEVBQTBCO0FBQzVCLDBCQUFZLFNBQU8sMkJBQTJCLE9BQTNCLEdBQW5CLENBRDRCO0tBQTlCOztBQUlBLFdBQU8sS0FBUCxDQUxxRTtHQUFoRSIsImZpbGUiOiJyZWFjdC1yZWR1eC1yb3V0ZXJcXHJlZHVjZXIuanMiLCJzb3VyY2VzQ29udGVudCI6WyIvKipcclxuICogVGhpcyBhY3Rpb24gdHlwZSB3aWxsIGJlIGRpc3BhdGNoZWQgd2hlbiB5b3VyIGhpc3RvcnlcclxuICogcmVjZWl2ZXMgYSBsb2NhdGlvbiBjaGFuZ2UuXHJcbiAqL1xyXG5leHBvcnQgY29uc3QgTE9DQVRJT05fQ0hBTkdFID0gJ0BAcm91dGVyL0xPQ0FUSU9OX0NIQU5HRSdcclxuXHJcbmNvbnN0IGluaXRpYWxTdGF0ZSA9IHtcclxuICBsb2NhdGlvbkJlZm9yZVRyYW5zaXRpb25zOiBudWxsXHJcbn1cclxuXHJcbi8qKlxyXG4gKiBUaGlzIHJlZHVjZXIgd2lsbCB1cGRhdGUgdGhlIHN0YXRlIHdpdGggdGhlIG1vc3QgcmVjZW50IGxvY2F0aW9uIGhpc3RvcnlcclxuICogaGFzIHRyYW5zaXRpb25lZCB0by4gVGhpcyBtYXkgbm90IGJlIGluIHN5bmMgd2l0aCB0aGUgcm91dGVyLCBwYXJ0aWN1bGFybHlcclxuICogaWYgeW91IGhhdmUgYXN5bmNocm9ub3VzbHktbG9hZGVkIHJvdXRlcywgc28gcmVhZGluZyBmcm9tIGFuZCByZWx5aW5nIG9uXHJcbiAqIHRoaXMgc3RhdGUgaXQgaXMgZGlzY291cmFnZWQuXHJcbiAqL1xyXG5leHBvcnQgZnVuY3Rpb24gcm91dGVyUmVkdWNlcihzdGF0ZSA9IGluaXRpYWxTdGF0ZSwgeyB0eXBlLCBwYXlsb2FkIH0pIHtcclxuICBpZiAodHlwZSA9PT0gTE9DQVRJT05fQ0hBTkdFKSB7XHJcbiAgICByZXR1cm4geyAuLi5zdGF0ZSwgbG9jYXRpb25CZWZvcmVUcmFuc2l0aW9uczogcGF5bG9hZCB9XHJcbiAgfVxyXG5cclxuICByZXR1cm4gc3RhdGVcclxufVxyXG4iXX0=