define(['exports', 'app/constants/constants', 'app/util/AjaxUtil', 'app/util/Settings'], function (exports, _constants, _AjaxUtil, _Settings) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.setLanguage = undefined;

  var Constants = _interopRequireWildcard(_constants);

  var _AjaxUtil2 = _interopRequireDefault(_AjaxUtil);

  var _Settings2 = _interopRequireDefault(_Settings);

  function _interopRequireDefault(obj) {
    return obj && obj.__esModule ? obj : {
      default: obj
    };
  }

  function _interopRequireWildcard(obj) {
    if (obj && obj.__esModule) {
      return obj;
    } else {
      var newObj = {};

      if (obj != null) {
        for (var key in obj) {
          if (Object.prototype.hasOwnProperty.call(obj, key)) newObj[key] = obj[key];
        }
      }

      newObj.default = obj;
      return newObj;
    }
  }

  var setLanguage = exports.setLanguage = function setLanguage(lang) {
    return {
      type: Constants.SET_APP_LANGUAGE,
      lang: lang
    };
  };
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImFjdGlvbnNcXGluZGV4LmpzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7O01BQVk7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7O0FBSUwsTUFBTSxvQ0FBYyxTQUFkLFdBQWMsQ0FBQyxJQUFELEVBQVU7QUFDbkMsV0FBTztBQUNMLFlBQU0sVUFBVSxnQkFBVjtBQUNOLGdCQUZLO0tBQVAsQ0FEbUM7R0FBViIsImZpbGUiOiJhY3Rpb25zXFxpbmRleC5qcyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCAqIGFzIENvbnN0YW50cyBmcm9tICdhcHAvY29uc3RhbnRzL2NvbnN0YW50cyc7XHJcbmltcG9ydCBBamF4VXRpbCBmcm9tICdhcHAvdXRpbC9BamF4VXRpbCc7XHJcbmltcG9ydCBTZXR0aW5ncyBmcm9tICdhcHAvdXRpbC9TZXR0aW5ncyc7XHJcblxyXG5leHBvcnQgY29uc3Qgc2V0TGFuZ3VhZ2UgPSAobGFuZykgPT4ge1xyXG4gIHJldHVybiB7XHJcbiAgICB0eXBlOiBDb25zdGFudHMuU0VUX0FQUF9MQU5HVUFHRSxcclxuICAgIGxhbmcgXHJcbiAgfVxyXG59XHJcblxyXG4iXX0=