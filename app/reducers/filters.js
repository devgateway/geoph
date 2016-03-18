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

  function _defineProperty(obj, key, value) {
    if (key in obj) {
      Object.defineProperty(obj, key, {
        value: value,
        enumerable: true,
        configurable: true,
        writable: true
      });
    } else {
      obj[key] = value;
    }

    return obj;
  }

  var filters = function filters() {
    var state = arguments.length <= 0 || arguments[0] === undefined ? {} : arguments[0];
    var action = arguments[1];

    switch (action.type) {
      case Constants.SELECT_FILTER_ITEM:
      case Constants.SELECT_ALL_FILTER_LIST:
      case Constants.RECEIVE_FILTER_LIST:
      case Constants.REQUEST_FILTER_LIST:
        var fl = filter(state[action.filterType], action);
        updateFilterCounters(fl);
        return Object.assign({}, state, _defineProperty({}, action.filterType, fl));
      default:
        return state;
    }
  };

  var filter = function filter() {
    var state = arguments.length <= 0 || arguments[0] === undefined ? {
      isFetching: false,
      items: []
    } : arguments[0];
    var action = arguments[1];

    switch (action.type) {
      case Constants.REQUEST_FILTER_LIST:
        return Object.assign({}, state, {
          isFetching: true
        });
      case Constants.RECEIVE_FILTER_LIST:
        return Object.assign({}, state, action.data, {
          isFetching: false,
          lastUpdated: action.receivedAt
        });

      case Constants.SELECT_FILTER_ITEM:
        return Object.assign({}, state, {
          isFetching: false,
          items: state.items.map(function (i) {
            return filterItem(i, action);
          })
        });
      case Constants.SELECT_ALL_FILTER_LIST:
        return Object.assign({}, state, {
          isFetching: false,
          selected: action.item.selected,
          items: state.items.map(function (i) {
            return filterItem(i, action);
          })
        });
      default:
        return state;
    }
  };

  var filterItem = function filterItem() {
    var state = arguments.length <= 0 || arguments[0] === undefined ? {
      selected: false
    } : arguments[0];
    var action = arguments[1];

    var copyState = Object.assign({}, state);
    switch (action.type) {
      case Constants.SELECT_FILTER_ITEM:
        updateFilterSelection(copyState, action.item.id, action.item.selected);
        return copyState;
      case Constants.SELECT_ALL_FILTER_LIST:
        updateFilterSelection(copyState, 'all', action.item.selected);
        return copyState;
      default:
        return state;
    }
  };

  //This function iterates over all children items and select the given one
  var updateFilterSelection = function updateFilterSelection(item, id, selection) {
    if (item.id === id || 'all' === id) {
      updateItemAndChildren(item, selection);
    } else if (item.items && item.items.length > 0) {
      item.items.map(function (it) {
        return updateFilterSelection(it, id, selection);
      });
    }
  };

  var updateItemAndChildren = function updateItemAndChildren(item, selection) {
    Object.assign(item, { 'selected': selection });
    if (item.items && item.items.length > 0) {
      item.items.map(function (it) {
        return updateItemAndChildren(it, selection);
      });
    }
  };

  //This function add the total and selected counter fields to each object that has children
  var updateFilterCounters = function updateFilterCounters(filterObject) {
    if (filterObject.items && filterObject.items.length > 0) {
      Object.assign(filterObject, { 'totalCounter': filterObject.items.length });
      Object.assign(filterObject, { 'selectedCounter': filterObject.items.filter(function (it) {
          return it.selected;
        }).length });
      filterObject.items.forEach(function (item) {
        updateFilterCounters(item);
      });
    }
  };

  exports.default = filters;
});
//# sourceMappingURL=data:application/json;base64,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QUMsNkJBQXFCLElBQXJCLEVBQUQ7T0FBVixDQUEzQixDQUhvRDtLQUF0RDtHQUQyQjs7b0JBU2QiLCJmaWxlIjoicmVkdWNlcnNcXGZpbHRlcnMuanMiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgKiBhcyBDb25zdGFudHMgZnJvbSAnYXBwL2NvbnN0YW50cy9jb25zdGFudHMnO1xyXG5cclxuY29uc3QgZmlsdGVycyA9IChzdGF0ZSA9IHt9LCBhY3Rpb24pID0+IHtcclxuXHRzd2l0Y2ggKGFjdGlvbi50eXBlKSB7XHJcbiAgICBjYXNlIENvbnN0YW50cy5TRUxFQ1RfRklMVEVSX0lURU06XHJcbiAgICBjYXNlIENvbnN0YW50cy5TRUxFQ1RfQUxMX0ZJTFRFUl9MSVNUOlxyXG4gICAgY2FzZSBDb25zdGFudHMuUkVDRUlWRV9GSUxURVJfTElTVDpcclxuICAgIGNhc2UgQ29uc3RhbnRzLlJFUVVFU1RfRklMVEVSX0xJU1Q6XHJcbiAgICAgIGxldCBmbCA9IGZpbHRlcihzdGF0ZVthY3Rpb24uZmlsdGVyVHlwZV0sIGFjdGlvbik7XHJcbiAgICAgIHVwZGF0ZUZpbHRlckNvdW50ZXJzKGZsKTtcclxuICAgICAgcmV0dXJuIE9iamVjdC5hc3NpZ24oe30sIHN0YXRlLCB7XHJcbiAgICAgICAgW2FjdGlvbi5maWx0ZXJUeXBlXTogZmxcclxuICAgICAgfSlcclxuICAgIGRlZmF1bHQ6XHJcbiAgICAgIHJldHVybiBzdGF0ZVxyXG4gIH1cclxufVxyXG5cclxuY29uc3QgZmlsdGVyID0gKHN0YXRlID0ge1xyXG4gIGlzRmV0Y2hpbmc6IGZhbHNlLFxyXG4gIGl0ZW1zOiBbXVxyXG59LCBhY3Rpb24pID0+IHtcclxuICBzd2l0Y2ggKGFjdGlvbi50eXBlKSB7XHJcbiAgICBjYXNlIENvbnN0YW50cy5SRVFVRVNUX0ZJTFRFUl9MSVNUOlxyXG4gICAgICByZXR1cm4gT2JqZWN0LmFzc2lnbih7fSwgc3RhdGUsIHtcclxuICAgICAgICBpc0ZldGNoaW5nOiB0cnVlLFxyXG4gICAgICB9KVxyXG4gICAgY2FzZSBDb25zdGFudHMuUkVDRUlWRV9GSUxURVJfTElTVDpcclxuICAgICAgcmV0dXJuIE9iamVjdC5hc3NpZ24oe30sIHN0YXRlLCBhY3Rpb24uZGF0YSwge1xyXG4gICAgICAgIGlzRmV0Y2hpbmc6IGZhbHNlLFxyXG4gICAgICAgIGxhc3RVcGRhdGVkOiBhY3Rpb24ucmVjZWl2ZWRBdFxyXG4gICAgICB9KVxyXG5cclxuICAgIGNhc2UgQ29uc3RhbnRzLlNFTEVDVF9GSUxURVJfSVRFTTpcclxuICAgICAgcmV0dXJuIE9iamVjdC5hc3NpZ24oe30sIHN0YXRlLCB7XHJcbiAgICAgICAgICBpc0ZldGNoaW5nOiBmYWxzZSxcclxuICAgICAgICAgIGl0ZW1zOiBzdGF0ZS5pdGVtcy5tYXAoaSA9PiBmaWx0ZXJJdGVtKGksIGFjdGlvbikpXHJcbiAgICAgIH0pXHJcbiAgICBjYXNlIENvbnN0YW50cy5TRUxFQ1RfQUxMX0ZJTFRFUl9MSVNUOlxyXG4gICAgXHRyZXR1cm4gT2JqZWN0LmFzc2lnbih7fSwgc3RhdGUsIHtcclxuXHQgICAgICAgIGlzRmV0Y2hpbmc6IGZhbHNlLFxyXG4gICAgICAgICAgc2VsZWN0ZWQ6IGFjdGlvbi5pdGVtLnNlbGVjdGVkLFxyXG5cdCAgICAgICAgaXRlbXM6IHN0YXRlLml0ZW1zLm1hcChpID0+IGZpbHRlckl0ZW0oaSwgYWN0aW9uKSlcclxuXHQgICAgfSlcclxuICAgIGRlZmF1bHQ6XHJcbiAgICAgIHJldHVybiBzdGF0ZVxyXG4gIH1cclxufVxyXG5cclxuY29uc3QgZmlsdGVySXRlbSA9IChzdGF0ZSA9IHtcclxuICBzZWxlY3RlZDogZmFsc2VcclxufSwgYWN0aW9uKSA9PiB7XHJcbiAgbGV0IGNvcHlTdGF0ZSA9IE9iamVjdC5hc3NpZ24oe30sIHN0YXRlKTsgXHJcbiAgc3dpdGNoIChhY3Rpb24udHlwZSkge1xyXG4gICAgY2FzZSBDb25zdGFudHMuU0VMRUNUX0ZJTFRFUl9JVEVNOlxyXG4gICAgICB1cGRhdGVGaWx0ZXJTZWxlY3Rpb24oY29weVN0YXRlLCBhY3Rpb24uaXRlbS5pZCwgYWN0aW9uLml0ZW0uc2VsZWN0ZWQpOyBcclxuICAgICAgcmV0dXJuIGNvcHlTdGF0ZVxyXG4gICAgY2FzZSBDb25zdGFudHMuU0VMRUNUX0FMTF9GSUxURVJfTElTVDpcclxuICAgICAgdXBkYXRlRmlsdGVyU2VsZWN0aW9uKGNvcHlTdGF0ZSwgJ2FsbCcsIGFjdGlvbi5pdGVtLnNlbGVjdGVkKTsgXHJcbiAgICAgIHJldHVybiBjb3B5U3RhdGVcclxuICAgIGRlZmF1bHQ6XHJcbiAgICAgIHJldHVybiBzdGF0ZVxyXG4gIH1cclxufVxyXG5cclxuLy9UaGlzIGZ1bmN0aW9uIGl0ZXJhdGVzIG92ZXIgYWxsIGNoaWxkcmVuIGl0ZW1zIGFuZCBzZWxlY3QgdGhlIGdpdmVuIG9uZVxyXG5jb25zdCB1cGRhdGVGaWx0ZXJTZWxlY3Rpb24gPSAoaXRlbSwgaWQsIHNlbGVjdGlvbikgPT4geyBcclxuICBpZiAoaXRlbS5pZCA9PT0gaWQgfHwgJ2FsbCcgPT09IGlkKXtcclxuICAgIHVwZGF0ZUl0ZW1BbmRDaGlsZHJlbihpdGVtLCBzZWxlY3Rpb24pO1xyXG4gIH0gZWxzZSBpZiAoaXRlbS5pdGVtcyAmJiBpdGVtLml0ZW1zLmxlbmd0aD4wKXtcclxuICAgIGl0ZW0uaXRlbXMubWFwKGl0ID0+IHVwZGF0ZUZpbHRlclNlbGVjdGlvbihpdCwgaWQsIHNlbGVjdGlvbikpO1xyXG4gIH1cclxufVxyXG5cclxuY29uc3QgdXBkYXRlSXRlbUFuZENoaWxkcmVuID0gKGl0ZW0sIHNlbGVjdGlvbikgPT4geyBcclxuICBPYmplY3QuYXNzaWduKGl0ZW0sIHsnc2VsZWN0ZWQnOiBzZWxlY3Rpb259KTtcclxuICBpZiAoaXRlbS5pdGVtcyAmJiBpdGVtLml0ZW1zLmxlbmd0aD4wKXtcclxuICAgIGl0ZW0uaXRlbXMubWFwKGl0ID0+IHVwZGF0ZUl0ZW1BbmRDaGlsZHJlbihpdCwgc2VsZWN0aW9uKSk7XHJcbiAgfSAgXHJcbn1cclxuXHJcbi8vVGhpcyBmdW5jdGlvbiBhZGQgdGhlIHRvdGFsIGFuZCBzZWxlY3RlZCBjb3VudGVyIGZpZWxkcyB0byBlYWNoIG9iamVjdCB0aGF0IGhhcyBjaGlsZHJlblxyXG5jb25zdCB1cGRhdGVGaWx0ZXJDb3VudGVycyA9IChmaWx0ZXJPYmplY3QpID0+IHsgXHJcbiAgaWYgKGZpbHRlck9iamVjdC5pdGVtcyAmJiBmaWx0ZXJPYmplY3QuaXRlbXMubGVuZ3RoPjApe1xyXG4gICAgT2JqZWN0LmFzc2lnbihmaWx0ZXJPYmplY3QsIHsndG90YWxDb3VudGVyJzogZmlsdGVyT2JqZWN0Lml0ZW1zLmxlbmd0aH0pO1xyXG4gICAgT2JqZWN0LmFzc2lnbihmaWx0ZXJPYmplY3QsIHsnc2VsZWN0ZWRDb3VudGVyJzogZmlsdGVyT2JqZWN0Lml0ZW1zLmZpbHRlcigoaXQpID0+IHtyZXR1cm4gaXQuc2VsZWN0ZWR9KS5sZW5ndGh9KTtcclxuICAgIGZpbHRlck9iamVjdC5pdGVtcy5mb3JFYWNoKChpdGVtKSA9PiB7dXBkYXRlRmlsdGVyQ291bnRlcnMoaXRlbSl9KTtcclxuICB9XHJcbn1cclxuXHJcblxyXG5leHBvcnQgZGVmYXVsdCBmaWx0ZXJzIl19