define(['exports', 'react-redux', 'app/actions/map', 'app/components/map/map', 'app/constants/constants'], function (exports, _reactRedux, _map, _map2, _constants) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.Map = undefined;

  var _map3 = _interopRequireDefault(_map2);

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

  function _interopRequireDefault(obj) {
    return obj && obj.__esModule ? obj : {
      default: obj
    };
  }

  var stateToProps = function stateToProps(state, props) {
    return state.map;
  };

  /*Connect map component to redux state*/
  var Map = (0, _reactRedux.connect)(stateToProps)(_map3.default);

  exports.Map = Map;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXG1hcFxcaW5kZXguanMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7OztNQUlZOzs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7O0FBRVosTUFBTSxlQUFlLFNBQWYsWUFBZSxDQUFDLEtBQUQsRUFBUSxLQUFSLEVBQWtCO0FBQ3JDLFdBQU8sTUFBTSxHQUFOLENBRDhCO0dBQWxCOzs7QUFNckIsTUFBTSxNQUFJLHlCQUFRLFlBQVIsZ0JBQUo7O1VBRUUiLCJmaWxlIjoiY29tcG9uZW50c1xcbWFwXFxpbmRleC5qcyIsInNvdXJjZXNDb250ZW50IjpbIlxyXG5pbXBvcnQgeyBjb25uZWN0IH0gZnJvbSAncmVhY3QtcmVkdXgnXHJcbmltcG9ydCB7bG9hZFByb2plY3RzfSBmcm9tICdhcHAvYWN0aW9ucy9tYXAnXHJcbmltcG9ydCBNYXBDb21wb25lbnQgZnJvbSAnYXBwL2NvbXBvbmVudHMvbWFwL21hcCdcclxuaW1wb3J0ICogYXMgQ29uc3RhbnRzIGZyb20gJ2FwcC9jb25zdGFudHMvY29uc3RhbnRzJztcclxuXHJcbmNvbnN0IHN0YXRlVG9Qcm9wcyA9IChzdGF0ZSwgcHJvcHMpID0+IHtcclxuICByZXR1cm4gc3RhdGUubWFwICBcclxufVxyXG5cclxuXHJcbi8qQ29ubmVjdCBtYXAgY29tcG9uZW50IHRvIHJlZHV4IHN0YXRlKi9cclxuY29uc3QgTWFwPWNvbm5lY3Qoc3RhdGVUb1Byb3BzKShNYXBDb21wb25lbnQpO1xyXG5cclxuZXhwb3J0IHtNYXB9O1xyXG4gIl19