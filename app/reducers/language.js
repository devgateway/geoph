define(['exports', 'app/constants/constants'], function (exports, _constants) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });

  var Constants = _interopRequireWildcard(_constants);

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

  var language = function language() {
    var state = arguments.length <= 0 || arguments[0] === undefined ? 'en' : arguments[0];
    var action = arguments[1];

    console.log("lang: " + action.lang);
    switch (action.type) {
      case Constants.SET_APP_LANGUAGE:
        return action.lang;
      default:
        return state;
    }
  };

  exports.default = language;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInJlZHVjZXJzXFxsYW5ndWFnZS5qcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7Ozs7O01BQVk7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFFWixNQUFNLFdBQVcsU0FBWCxRQUFXLEdBQTBCO1FBQXpCLDhEQUFRLG9CQUFpQjtRQUFYLHNCQUFXOztBQUMxQyxZQUFRLEdBQVIsQ0FBWSxXQUFTLE9BQU8sSUFBUCxDQUFyQixDQUQwQztBQUV6QyxZQUFRLE9BQU8sSUFBUDtBQUNOLFdBQUssVUFBVSxnQkFBVjtBQUNILGVBQU8sT0FBTyxJQUFQLENBRFQ7QUFERjtBQUlJLGVBQU8sS0FBUCxDQURGO0FBSEYsS0FGeUM7R0FBMUI7O29CQVVGIiwiZmlsZSI6InJlZHVjZXJzXFxsYW5ndWFnZS5qcyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCAqIGFzIENvbnN0YW50cyBmcm9tICdhcHAvY29uc3RhbnRzL2NvbnN0YW50cyc7XHJcblxyXG5jb25zdCBsYW5ndWFnZSA9IChzdGF0ZSA9ICdlbicsIGFjdGlvbikgPT4ge1xyXG5cdGNvbnNvbGUubG9nKFwibGFuZzogXCIrYWN0aW9uLmxhbmcpO1xyXG4gIHN3aXRjaCAoYWN0aW9uLnR5cGUpIHtcclxuICAgIGNhc2UgQ29uc3RhbnRzLlNFVF9BUFBfTEFOR1VBR0U6XHJcbiAgICAgIHJldHVybiBhY3Rpb24ubGFuZztcclxuICAgIGRlZmF1bHQ6XHJcbiAgICAgIHJldHVybiBzdGF0ZVxyXG4gIH1cclxufVxyXG5cclxuZXhwb3J0IGRlZmF1bHQgbGFuZ3VhZ2UiXX0=