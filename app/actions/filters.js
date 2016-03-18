define(['exports', 'app/constants/constants', 'app/connector/connector.js'], function (exports, _constants, _connector) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.selectAllFilterList = exports.selectFilterItem = exports.fetchFilterListIfNeeded = exports.shouldFetchFilterList = exports.fetchFilterList = exports.receiveFilterList = exports.requestFilterList = undefined;

  var Constants = _interopRequireWildcard(_constants);

  var _connector2 = _interopRequireDefault(_connector);

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

  var requestFilterList = exports.requestFilterList = function requestFilterList(filter) {
    return {
      type: Constants.REQUEST_FILTER_LIST,
      filter: filter
    };
  };

  var receiveFilterList = exports.receiveFilterList = function receiveFilterList(filterType, data) {
    return {
      type: Constants.RECEIVE_FILTER_LIST,
      filterType: filterType,
      data: data,
      receivedAt: Date.now()
    };
  };

  var fetchFilterList = exports.fetchFilterList = function fetchFilterList(filterType) {
    return function (dispatch) {
      dispatch(requestFilterList(filterType));
      return _connector2.default.getFilterList(filterType).then(function (req) {
        return dispatch(receiveFilterList(filterType, req));
      });
    };
  };

  var shouldFetchFilterList = exports.shouldFetchFilterList = function shouldFetchFilterList(state, filterType) {
    var list = state.filters[filterType];
    if (!list) {
      return true;
    } else if (list.isFetching) {
      return false;
    } else {
      return false;
    }
  };

  var fetchFilterListIfNeeded = exports.fetchFilterListIfNeeded = function fetchFilterListIfNeeded(filterType) {
    return function (dispatch, getState) {
      if (shouldFetchFilterList(getState(), filterType)) {
        return dispatch(fetchFilterList(filterType));
      }
    };
  };

  var selectFilterItem = exports.selectFilterItem = function selectFilterItem(filterItem) {
    return {
      type: Constants.SELECT_FILTER_ITEM,
      filterType: filterItem.filterType,
      item: filterItem
    };
  };

  var selectAllFilterList = exports.selectAllFilterList = function selectAllFilterList(filterItem) {
    return {
      type: Constants.SELECT_ALL_FILTER_LIST,
      filterType: filterItem.filterType,
      item: filterItem
    };
  };
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImFjdGlvbnNcXGZpbHRlcnMuanMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7TUFBWTs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7O0FBR0wsTUFBTSxnREFBb0IsU0FBcEIsaUJBQW9CLENBQUMsTUFBRCxFQUFZO0FBQzNDLFdBQU87QUFDTCxZQUFNLFVBQVUsbUJBQVY7QUFDTixvQkFGSztLQUFQLENBRDJDO0dBQVo7O0FBTzFCLE1BQU0sZ0RBQW9CLFNBQXBCLGlCQUFvQixDQUFDLFVBQUQsRUFBYSxJQUFiLEVBQXNCO0FBQ3JELFdBQU87QUFDTCxZQUFNLFVBQVUsbUJBQVY7QUFDTiw0QkFGSztBQUdMLFlBQU0sSUFBTjtBQUNBLGtCQUFZLEtBQUssR0FBTCxFQUFaO0tBSkYsQ0FEcUQ7R0FBdEI7O0FBUzFCLE1BQU0sNENBQWtCLFNBQWxCLGVBQWtCLENBQUMsVUFBRCxFQUFnQjtBQUM3QyxXQUFPLG9CQUFZO0FBQ2pCLGVBQVMsa0JBQWtCLFVBQWxCLENBQVQsRUFEaUI7QUFFakIsYUFBTyxvQkFBVSxhQUFWLENBQXdCLFVBQXhCLEVBQ0osSUFESSxDQUNDO2VBQU8sU0FBUyxrQkFBa0IsVUFBbEIsRUFBOEIsR0FBOUIsQ0FBVDtPQUFQLENBRFIsQ0FGaUI7S0FBWixDQURzQztHQUFoQjs7QUFReEIsTUFBTSx3REFBd0IsU0FBeEIscUJBQXdCLENBQUMsS0FBRCxFQUFRLFVBQVIsRUFBdUI7QUFDMUQsUUFBTSxPQUFPLE1BQU0sT0FBTixDQUFjLFVBQWQsQ0FBUCxDQURvRDtBQUUxRCxRQUFJLENBQUMsSUFBRCxFQUFPO0FBQ1QsYUFBTyxJQUFQLENBRFM7S0FBWCxNQUVPLElBQUksS0FBSyxVQUFMLEVBQWlCO0FBQzFCLGFBQU8sS0FBUCxDQUQwQjtLQUFyQixNQUVBO0FBQ0wsYUFBTyxLQUFQLENBREs7S0FGQTtHQUo0Qjs7QUFXOUIsTUFBTSw0REFBMEIsU0FBMUIsdUJBQTBCLENBQUMsVUFBRCxFQUFnQjtBQUNyRCxXQUFPLFVBQUMsUUFBRCxFQUFXLFFBQVgsRUFBd0I7QUFDN0IsVUFBSSxzQkFBc0IsVUFBdEIsRUFBa0MsVUFBbEMsQ0FBSixFQUFtRDtBQUNqRCxlQUFPLFNBQVMsZ0JBQWdCLFVBQWhCLENBQVQsQ0FBUCxDQURpRDtPQUFuRDtLQURLLENBRDhDO0dBQWhCOztBQVFoQyxNQUFNLDhDQUFtQixTQUFuQixnQkFBbUIsQ0FBQyxVQUFELEVBQWdCO0FBQzlDLFdBQU87QUFDTCxZQUFNLFVBQVUsa0JBQVY7QUFDTixrQkFBWSxXQUFXLFVBQVg7QUFDWixZQUFNLFVBQU47S0FIRixDQUQ4QztHQUFoQjs7QUFRekIsTUFBTSxvREFBc0IsU0FBdEIsbUJBQXNCLENBQUMsVUFBRCxFQUFnQjtBQUNqRCxXQUFPO0FBQ0wsWUFBTSxVQUFVLHNCQUFWO0FBQ04sa0JBQVksV0FBVyxVQUFYO0FBQ1osWUFBTSxVQUFOO0tBSEYsQ0FEaUQ7R0FBaEIiLCJmaWxlIjoiYWN0aW9uc1xcZmlsdGVycy5qcyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCAqIGFzIENvbnN0YW50cyBmcm9tICdhcHAvY29uc3RhbnRzL2NvbnN0YW50cyc7XHJcbmltcG9ydCBDb25uZWN0b3IgZnJvbSAnYXBwL2Nvbm5lY3Rvci9jb25uZWN0b3IuanMnO1xyXG5cclxuZXhwb3J0IGNvbnN0IHJlcXVlc3RGaWx0ZXJMaXN0ID0gKGZpbHRlcikgPT4ge1xyXG4gIHJldHVybiB7XHJcbiAgICB0eXBlOiBDb25zdGFudHMuUkVRVUVTVF9GSUxURVJfTElTVCxcclxuICAgIGZpbHRlclxyXG4gIH1cclxufVxyXG5cclxuZXhwb3J0IGNvbnN0IHJlY2VpdmVGaWx0ZXJMaXN0ID0gKGZpbHRlclR5cGUsIGRhdGEpID0+IHtcclxuICByZXR1cm4ge1xyXG4gICAgdHlwZTogQ29uc3RhbnRzLlJFQ0VJVkVfRklMVEVSX0xJU1QsXHJcbiAgICBmaWx0ZXJUeXBlLFxyXG4gICAgZGF0YTogZGF0YSxcclxuICAgIHJlY2VpdmVkQXQ6IERhdGUubm93KClcclxuICB9XHJcbn1cclxuXHJcbmV4cG9ydCBjb25zdCBmZXRjaEZpbHRlckxpc3QgPSAoZmlsdGVyVHlwZSkgPT4ge1xyXG4gIHJldHVybiBkaXNwYXRjaCA9PiB7XHJcbiAgICBkaXNwYXRjaChyZXF1ZXN0RmlsdGVyTGlzdChmaWx0ZXJUeXBlKSlcclxuICAgIHJldHVybiBDb25uZWN0b3IuZ2V0RmlsdGVyTGlzdChmaWx0ZXJUeXBlKVxyXG4gICAgICAudGhlbihyZXEgPT4gZGlzcGF0Y2gocmVjZWl2ZUZpbHRlckxpc3QoZmlsdGVyVHlwZSwgcmVxKSkpXHJcbiAgfVxyXG59XHJcblxyXG5leHBvcnQgY29uc3Qgc2hvdWxkRmV0Y2hGaWx0ZXJMaXN0ID0gKHN0YXRlLCBmaWx0ZXJUeXBlKSA9PiB7XHJcbiAgY29uc3QgbGlzdCA9IHN0YXRlLmZpbHRlcnNbZmlsdGVyVHlwZV1cclxuICBpZiAoIWxpc3QpIHtcclxuICAgIHJldHVybiB0cnVlXHJcbiAgfSBlbHNlIGlmIChsaXN0LmlzRmV0Y2hpbmcpIHtcclxuICAgIHJldHVybiBmYWxzZVxyXG4gIH0gZWxzZSB7XHJcbiAgICByZXR1cm4gZmFsc2VcclxuICB9XHJcbn1cclxuXHJcbmV4cG9ydCBjb25zdCBmZXRjaEZpbHRlckxpc3RJZk5lZWRlZCA9IChmaWx0ZXJUeXBlKSA9PiB7XHJcbiAgcmV0dXJuIChkaXNwYXRjaCwgZ2V0U3RhdGUpID0+IHtcclxuICAgIGlmIChzaG91bGRGZXRjaEZpbHRlckxpc3QoZ2V0U3RhdGUoKSwgZmlsdGVyVHlwZSkpIHtcclxuICAgICAgcmV0dXJuIGRpc3BhdGNoKGZldGNoRmlsdGVyTGlzdChmaWx0ZXJUeXBlKSlcclxuICAgIH1cclxuICB9XHJcbn1cclxuXHJcbmV4cG9ydCBjb25zdCBzZWxlY3RGaWx0ZXJJdGVtID0gKGZpbHRlckl0ZW0pID0+IHtcclxuICByZXR1cm4ge1xyXG4gICAgdHlwZTogQ29uc3RhbnRzLlNFTEVDVF9GSUxURVJfSVRFTSxcclxuICAgIGZpbHRlclR5cGU6IGZpbHRlckl0ZW0uZmlsdGVyVHlwZSxcclxuICAgIGl0ZW06IGZpbHRlckl0ZW1cclxuICB9XHJcbn1cclxuXHJcbmV4cG9ydCBjb25zdCBzZWxlY3RBbGxGaWx0ZXJMaXN0ID0gKGZpbHRlckl0ZW0pID0+IHtcclxuICByZXR1cm4ge1xyXG4gICAgdHlwZTogQ29uc3RhbnRzLlNFTEVDVF9BTExfRklMVEVSX0xJU1QsXHJcbiAgICBmaWx0ZXJUeXBlOiBmaWx0ZXJJdGVtLmZpbHRlclR5cGUsXHJcbiAgICBpdGVtOiBmaWx0ZXJJdGVtXHJcbiAgfVxyXG59XHJcbiJdfQ==