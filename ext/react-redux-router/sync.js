define(['exports', './reducer'], function (exports, _reducer) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });
  exports.default = syncHistoryWithStore;

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

  var defaultSelectLocationState = function defaultSelectLocationState(state) {
    return state.routing;
  };

  /**
   * This function synchronizes your history state with the Redux store.
   * Location changes flow from history to the store. An enhanced history is
   * returned with a listen method that responds to store updates for location.
   *
   * When this history is provided to the router, this means the location data
   * will flow like this:
   * history.push -> store.dispatch -> enhancedHistory.listen -> router
   * This ensures that when the store state changes due to a replay or other
   * event, the router will be updated appropriately and can transition to the
   * correct router state.
   */
  function syncHistoryWithStore(history, store) {
    var _ref = arguments.length <= 2 || arguments[2] === undefined ? {} : arguments[2];

    var _ref$selectLocationSt = _ref.selectLocationState;
    var selectLocationState = _ref$selectLocationSt === undefined ? defaultSelectLocationState : _ref$selectLocationSt;
    var _ref$adjustUrlOnRepla = _ref.adjustUrlOnReplay;
    var adjustUrlOnReplay = _ref$adjustUrlOnRepla === undefined ? true : _ref$adjustUrlOnRepla;

    // Ensure that the reducer is mounted on the store and functioning properly.
    if (typeof selectLocationState(store.getState()) === 'undefined') {
      throw new Error('Expected the routing state to be available either as `state.routing` ' + 'or as the custom expression you can specify as `selectLocationState` ' + 'in the `syncHistoryWithStore()` options. ' + 'Ensure you have added the `routerReducer` to your store\'s ' + 'reducers via `combineReducers` or whatever method you use to isolate ' + 'your reducers.');
    }

    var initialLocation = void 0;
    var currentLocation = void 0;
    var isTimeTraveling = void 0;
    var unsubscribeFromStore = void 0;
    var unsubscribeFromHistory = void 0;

    // What does the store say about current location?
    var getLocationInStore = function getLocationInStore(useInitialIfEmpty) {
      var locationState = selectLocationState(store.getState());
      return locationState.locationBeforeTransitions || (useInitialIfEmpty ? initialLocation : undefined);
    };

    // If the store is replayed, update the URL in the browser to match.
    if (adjustUrlOnReplay) {
      var handleStoreChange = function handleStoreChange() {
        var locationInStore = getLocationInStore(true);
        if (currentLocation === locationInStore) {
          return;
        }

        // Update address bar to reflect store state
        isTimeTraveling = true;
        currentLocation = locationInStore;
        history.transitionTo(_extends({}, locationInStore, {
          action: 'PUSH'
        }));
        isTimeTraveling = false;
      };

      unsubscribeFromStore = store.subscribe(handleStoreChange);
      handleStoreChange();
    }

    // Whenever location changes, dispatch an action to get it in the store
    var handleLocationChange = function handleLocationChange(location) {
      // ... unless we just caused that location change
      if (isTimeTraveling) {
        return;
      }

      // Remember where we are
      currentLocation = location;

      // Are we being called for the first time?
      if (!initialLocation) {
        // Remember as a fallback in case state is reset
        initialLocation = location;

        // Respect persisted location, if any
        if (getLocationInStore()) {
          return;
        }
      }

      // Tell the store to update by dispatching an action
      store.dispatch({
        type: _reducer.LOCATION_CHANGE,
        payload: location
      });
    };
    unsubscribeFromHistory = history.listen(handleLocationChange);

    // The enhanced history uses store as source of truth
    return _extends({}, history, {
      listen: function listen(listener) {
        // Copy of last location.
        var lastPublishedLocation = getLocationInStore(true);

        // Keep track of whether we unsubscribed, as Redux store
        // only applies changes in subscriptions on next dispatch
        var unsubscribed = false;
        var unsubscribeFromStore = store.subscribe(function () {
          var currentLocation = getLocationInStore(true);
          if (currentLocation === lastPublishedLocation) {
            return;
          }
          lastPublishedLocation = currentLocation;
          if (!unsubscribed) {
            listener(lastPublishedLocation);
          }
        });

        // History listeners expect a synchronous call. Make the first call to the
        // listener after subscribing to the store, in case the listener causes a
        // location change (e.g. when it redirects)
        listener(lastPublishedLocation);

        // Let user unsubscribe later
        return function () {
          unsubscribed = true;
          unsubscribeFromStore();
        };
      },
      unsubscribe: function unsubscribe() {
        if (adjustUrlOnReplay) {
          unsubscribeFromStore();
        }
        unsubscribeFromHistory();
      }
    });
  }
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInJlYWN0LXJlZHV4LXJvdXRlclxcc3luYy5qcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7Ozs7b0JBZ0J3Qjs7Ozs7Ozs7Ozs7Ozs7OztBQWR4QixNQUFNLDZCQUE2QixTQUE3QiwwQkFBNkI7V0FBUyxNQUFNLE9BQU47R0FBVDs7Ozs7Ozs7Ozs7Ozs7QUFjcEIsV0FBUyxvQkFBVCxDQUE4QixPQUE5QixFQUF1QyxLQUF2QyxFQUdQO3FFQUFKLGtCQUFJOztxQ0FGTixvQkFFTTtRQUZOLDREQUFzQixtREFFaEI7cUNBRE4sa0JBQ007UUFETiwwREFBb0IsNkJBQ2Q7OztBQUVOLFFBQUksT0FBTyxvQkFBb0IsTUFBTSxRQUFOLEVBQXBCLENBQVAsS0FBaUQsV0FBakQsRUFBOEQ7QUFDaEUsWUFBTSxJQUFJLEtBQUosQ0FDSiwwRUFDQSx1RUFEQSxHQUVBLDJDQUZBLEdBR0EsNkRBSEEsR0FJQSx1RUFKQSxHQUtBLGdCQUxBLENBREYsQ0FEZ0U7S0FBbEU7O0FBV0EsUUFBSSx3QkFBSixDQWJNO0FBY04sUUFBSSx3QkFBSixDQWRNO0FBZU4sUUFBSSx3QkFBSixDQWZNO0FBZ0JOLFFBQUksNkJBQUosQ0FoQk07QUFpQk4sUUFBSSwrQkFBSjs7O0FBakJNLFFBb0JBLHFCQUFxQixTQUFyQixrQkFBcUIsQ0FBQyxpQkFBRCxFQUF1QjtBQUNoRCxVQUFNLGdCQUFnQixvQkFBb0IsTUFBTSxRQUFOLEVBQXBCLENBQWhCLENBRDBDO0FBRWhELGFBQU8sY0FBYyx5QkFBZCxLQUNKLG9CQUFvQixlQUFwQixHQUFzQyxTQUF0QyxDQURJLENBRnlDO0tBQXZCOzs7QUFwQnJCLFFBMkJGLGlCQUFKLEVBQXVCO0FBQ3JCLFVBQU0sb0JBQW9CLFNBQXBCLGlCQUFvQixHQUFNO0FBQzlCLFlBQU0sa0JBQWtCLG1CQUFtQixJQUFuQixDQUFsQixDQUR3QjtBQUU5QixZQUFJLG9CQUFvQixlQUFwQixFQUFxQztBQUN2QyxpQkFEdUM7U0FBekM7OztBQUY4Qix1QkFPOUIsR0FBa0IsSUFBbEIsQ0FQOEI7QUFROUIsMEJBQWtCLGVBQWxCLENBUjhCO0FBUzlCLGdCQUFRLFlBQVIsY0FDSztBQUNILGtCQUFRLE1BQVI7VUFGRixFQVQ4QjtBQWE5QiwwQkFBa0IsS0FBbEIsQ0FiOEI7T0FBTixDQURMOztBQWlCckIsNkJBQXVCLE1BQU0sU0FBTixDQUFnQixpQkFBaEIsQ0FBdkIsQ0FqQnFCO0FBa0JyQiwwQkFsQnFCO0tBQXZCOzs7QUEzQk0sUUFpREEsdUJBQXVCLFNBQXZCLG9CQUF1QixDQUFDLFFBQUQsRUFBYzs7QUFFekMsVUFBSSxlQUFKLEVBQXFCO0FBQ25CLGVBRG1CO09BQXJCOzs7QUFGeUMscUJBT3pDLEdBQWtCLFFBQWxCOzs7QUFQeUMsVUFVckMsQ0FBQyxlQUFELEVBQWtCOztBQUVwQiwwQkFBa0IsUUFBbEI7OztBQUZvQixZQUtoQixvQkFBSixFQUEwQjtBQUN4QixpQkFEd0I7U0FBMUI7T0FMRjs7O0FBVnlDLFdBcUJ6QyxDQUFNLFFBQU4sQ0FBZTtBQUNiLHNDQURhO0FBRWIsaUJBQVMsUUFBVDtPQUZGLEVBckJ5QztLQUFkLENBakR2QjtBQTJFTiw2QkFBeUIsUUFBUSxNQUFSLENBQWUsb0JBQWYsQ0FBekI7OztBQTNFTSx3QkErRUQ7QUFFSCw4QkFBTyxVQUFVOztBQUVmLFlBQUksd0JBQXdCLG1CQUFtQixJQUFuQixDQUF4Qjs7OztBQUZXLFlBTVgsZUFBZSxLQUFmLENBTlc7QUFPZixZQUFNLHVCQUF1QixNQUFNLFNBQU4sQ0FBZ0IsWUFBTTtBQUNqRCxjQUFNLGtCQUFrQixtQkFBbUIsSUFBbkIsQ0FBbEIsQ0FEMkM7QUFFakQsY0FBSSxvQkFBb0IscUJBQXBCLEVBQTJDO0FBQzdDLG1CQUQ2QztXQUEvQztBQUdBLGtDQUF3QixlQUF4QixDQUxpRDtBQU1qRCxjQUFJLENBQUMsWUFBRCxFQUFlO0FBQ2pCLHFCQUFTLHFCQUFULEVBRGlCO1dBQW5CO1NBTjJDLENBQXZDOzs7OztBQVBTLGdCQXFCZixDQUFTLHFCQUFUOzs7QUFyQmUsZUF3QlIsWUFBTTtBQUNYLHlCQUFlLElBQWYsQ0FEVztBQUVYLGlDQUZXO1NBQU4sQ0F4QlE7O0FBK0JqQiwwQ0FBYztBQUNaLFlBQUksaUJBQUosRUFBdUI7QUFDckIsaUNBRHFCO1NBQXZCO0FBR0EsaUNBSlk7O01BbENoQixDQTlFTTtHQUhPIiwiZmlsZSI6InJlYWN0LXJlZHV4LXJvdXRlclxcc3luYy5qcyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7IExPQ0FUSU9OX0NIQU5HRSB9IGZyb20gJy4vcmVkdWNlcidcclxuXHJcbmNvbnN0IGRlZmF1bHRTZWxlY3RMb2NhdGlvblN0YXRlID0gc3RhdGUgPT4gc3RhdGUucm91dGluZ1xyXG5cclxuLyoqXHJcbiAqIFRoaXMgZnVuY3Rpb24gc3luY2hyb25pemVzIHlvdXIgaGlzdG9yeSBzdGF0ZSB3aXRoIHRoZSBSZWR1eCBzdG9yZS5cclxuICogTG9jYXRpb24gY2hhbmdlcyBmbG93IGZyb20gaGlzdG9yeSB0byB0aGUgc3RvcmUuIEFuIGVuaGFuY2VkIGhpc3RvcnkgaXNcclxuICogcmV0dXJuZWQgd2l0aCBhIGxpc3RlbiBtZXRob2QgdGhhdCByZXNwb25kcyB0byBzdG9yZSB1cGRhdGVzIGZvciBsb2NhdGlvbi5cclxuICpcclxuICogV2hlbiB0aGlzIGhpc3RvcnkgaXMgcHJvdmlkZWQgdG8gdGhlIHJvdXRlciwgdGhpcyBtZWFucyB0aGUgbG9jYXRpb24gZGF0YVxyXG4gKiB3aWxsIGZsb3cgbGlrZSB0aGlzOlxyXG4gKiBoaXN0b3J5LnB1c2ggLT4gc3RvcmUuZGlzcGF0Y2ggLT4gZW5oYW5jZWRIaXN0b3J5Lmxpc3RlbiAtPiByb3V0ZXJcclxuICogVGhpcyBlbnN1cmVzIHRoYXQgd2hlbiB0aGUgc3RvcmUgc3RhdGUgY2hhbmdlcyBkdWUgdG8gYSByZXBsYXkgb3Igb3RoZXJcclxuICogZXZlbnQsIHRoZSByb3V0ZXIgd2lsbCBiZSB1cGRhdGVkIGFwcHJvcHJpYXRlbHkgYW5kIGNhbiB0cmFuc2l0aW9uIHRvIHRoZVxyXG4gKiBjb3JyZWN0IHJvdXRlciBzdGF0ZS5cclxuICovXHJcbmV4cG9ydCBkZWZhdWx0IGZ1bmN0aW9uIHN5bmNIaXN0b3J5V2l0aFN0b3JlKGhpc3RvcnksIHN0b3JlLCB7XHJcbiAgc2VsZWN0TG9jYXRpb25TdGF0ZSA9IGRlZmF1bHRTZWxlY3RMb2NhdGlvblN0YXRlLFxyXG4gIGFkanVzdFVybE9uUmVwbGF5ID0gdHJ1ZVxyXG59ID0ge30pIHtcclxuICAvLyBFbnN1cmUgdGhhdCB0aGUgcmVkdWNlciBpcyBtb3VudGVkIG9uIHRoZSBzdG9yZSBhbmQgZnVuY3Rpb25pbmcgcHJvcGVybHkuXHJcbiAgaWYgKHR5cGVvZiBzZWxlY3RMb2NhdGlvblN0YXRlKHN0b3JlLmdldFN0YXRlKCkpID09PSAndW5kZWZpbmVkJykge1xyXG4gICAgdGhyb3cgbmV3IEVycm9yKFxyXG4gICAgICAnRXhwZWN0ZWQgdGhlIHJvdXRpbmcgc3RhdGUgdG8gYmUgYXZhaWxhYmxlIGVpdGhlciBhcyBgc3RhdGUucm91dGluZ2AgJyArXHJcbiAgICAgICdvciBhcyB0aGUgY3VzdG9tIGV4cHJlc3Npb24geW91IGNhbiBzcGVjaWZ5IGFzIGBzZWxlY3RMb2NhdGlvblN0YXRlYCAnICtcclxuICAgICAgJ2luIHRoZSBgc3luY0hpc3RvcnlXaXRoU3RvcmUoKWAgb3B0aW9ucy4gJyArXHJcbiAgICAgICdFbnN1cmUgeW91IGhhdmUgYWRkZWQgdGhlIGByb3V0ZXJSZWR1Y2VyYCB0byB5b3VyIHN0b3JlXFwncyAnICtcclxuICAgICAgJ3JlZHVjZXJzIHZpYSBgY29tYmluZVJlZHVjZXJzYCBvciB3aGF0ZXZlciBtZXRob2QgeW91IHVzZSB0byBpc29sYXRlICcgK1xyXG4gICAgICAneW91ciByZWR1Y2Vycy4nXHJcbiAgICApXHJcbiAgfVxyXG5cclxuICBsZXQgaW5pdGlhbExvY2F0aW9uXHJcbiAgbGV0IGN1cnJlbnRMb2NhdGlvblxyXG4gIGxldCBpc1RpbWVUcmF2ZWxpbmdcclxuICBsZXQgdW5zdWJzY3JpYmVGcm9tU3RvcmVcclxuICBsZXQgdW5zdWJzY3JpYmVGcm9tSGlzdG9yeVxyXG5cclxuICAvLyBXaGF0IGRvZXMgdGhlIHN0b3JlIHNheSBhYm91dCBjdXJyZW50IGxvY2F0aW9uP1xyXG4gIGNvbnN0IGdldExvY2F0aW9uSW5TdG9yZSA9ICh1c2VJbml0aWFsSWZFbXB0eSkgPT4ge1xyXG4gICAgY29uc3QgbG9jYXRpb25TdGF0ZSA9IHNlbGVjdExvY2F0aW9uU3RhdGUoc3RvcmUuZ2V0U3RhdGUoKSlcclxuICAgIHJldHVybiBsb2NhdGlvblN0YXRlLmxvY2F0aW9uQmVmb3JlVHJhbnNpdGlvbnMgfHxcclxuICAgICAgKHVzZUluaXRpYWxJZkVtcHR5ID8gaW5pdGlhbExvY2F0aW9uIDogdW5kZWZpbmVkKVxyXG4gIH1cclxuXHJcbiAgLy8gSWYgdGhlIHN0b3JlIGlzIHJlcGxheWVkLCB1cGRhdGUgdGhlIFVSTCBpbiB0aGUgYnJvd3NlciB0byBtYXRjaC5cclxuICBpZiAoYWRqdXN0VXJsT25SZXBsYXkpIHtcclxuICAgIGNvbnN0IGhhbmRsZVN0b3JlQ2hhbmdlID0gKCkgPT4ge1xyXG4gICAgICBjb25zdCBsb2NhdGlvbkluU3RvcmUgPSBnZXRMb2NhdGlvbkluU3RvcmUodHJ1ZSlcclxuICAgICAgaWYgKGN1cnJlbnRMb2NhdGlvbiA9PT0gbG9jYXRpb25JblN0b3JlKSB7XHJcbiAgICAgICAgcmV0dXJuXHJcbiAgICAgIH1cclxuXHJcbiAgICAgIC8vIFVwZGF0ZSBhZGRyZXNzIGJhciB0byByZWZsZWN0IHN0b3JlIHN0YXRlXHJcbiAgICAgIGlzVGltZVRyYXZlbGluZyA9IHRydWVcclxuICAgICAgY3VycmVudExvY2F0aW9uID0gbG9jYXRpb25JblN0b3JlXHJcbiAgICAgIGhpc3RvcnkudHJhbnNpdGlvblRvKHtcclxuICAgICAgICAuLi5sb2NhdGlvbkluU3RvcmUsXHJcbiAgICAgICAgYWN0aW9uOiAnUFVTSCdcclxuICAgICAgfSlcclxuICAgICAgaXNUaW1lVHJhdmVsaW5nID0gZmFsc2VcclxuICAgIH1cclxuXHJcbiAgICB1bnN1YnNjcmliZUZyb21TdG9yZSA9IHN0b3JlLnN1YnNjcmliZShoYW5kbGVTdG9yZUNoYW5nZSlcclxuICAgIGhhbmRsZVN0b3JlQ2hhbmdlKClcclxuICB9XHJcblxyXG4gIC8vIFdoZW5ldmVyIGxvY2F0aW9uIGNoYW5nZXMsIGRpc3BhdGNoIGFuIGFjdGlvbiB0byBnZXQgaXQgaW4gdGhlIHN0b3JlXHJcbiAgY29uc3QgaGFuZGxlTG9jYXRpb25DaGFuZ2UgPSAobG9jYXRpb24pID0+IHtcclxuICAgIC8vIC4uLiB1bmxlc3Mgd2UganVzdCBjYXVzZWQgdGhhdCBsb2NhdGlvbiBjaGFuZ2VcclxuICAgIGlmIChpc1RpbWVUcmF2ZWxpbmcpIHtcclxuICAgICAgcmV0dXJuXHJcbiAgICB9XHJcblxyXG4gICAgLy8gUmVtZW1iZXIgd2hlcmUgd2UgYXJlXHJcbiAgICBjdXJyZW50TG9jYXRpb24gPSBsb2NhdGlvblxyXG5cclxuICAgIC8vIEFyZSB3ZSBiZWluZyBjYWxsZWQgZm9yIHRoZSBmaXJzdCB0aW1lP1xyXG4gICAgaWYgKCFpbml0aWFsTG9jYXRpb24pIHtcclxuICAgICAgLy8gUmVtZW1iZXIgYXMgYSBmYWxsYmFjayBpbiBjYXNlIHN0YXRlIGlzIHJlc2V0XHJcbiAgICAgIGluaXRpYWxMb2NhdGlvbiA9IGxvY2F0aW9uXHJcblxyXG4gICAgICAvLyBSZXNwZWN0IHBlcnNpc3RlZCBsb2NhdGlvbiwgaWYgYW55XHJcbiAgICAgIGlmIChnZXRMb2NhdGlvbkluU3RvcmUoKSkge1xyXG4gICAgICAgIHJldHVyblxyXG4gICAgICB9XHJcbiAgICB9XHJcblxyXG4gICAgLy8gVGVsbCB0aGUgc3RvcmUgdG8gdXBkYXRlIGJ5IGRpc3BhdGNoaW5nIGFuIGFjdGlvblxyXG4gICAgc3RvcmUuZGlzcGF0Y2goe1xyXG4gICAgICB0eXBlOiBMT0NBVElPTl9DSEFOR0UsXHJcbiAgICAgIHBheWxvYWQ6IGxvY2F0aW9uXHJcbiAgICB9KVxyXG4gIH1cclxuICB1bnN1YnNjcmliZUZyb21IaXN0b3J5ID0gaGlzdG9yeS5saXN0ZW4oaGFuZGxlTG9jYXRpb25DaGFuZ2UpXHJcblxyXG4gIC8vIFRoZSBlbmhhbmNlZCBoaXN0b3J5IHVzZXMgc3RvcmUgYXMgc291cmNlIG9mIHRydXRoXHJcbiAgcmV0dXJuIHtcclxuICAgIC4uLmhpc3RvcnksXHJcbiAgICAvLyBUaGUgbGlzdGVuZXJzIGFyZSBzdWJzY3JpYmVkIHRvIHRoZSBzdG9yZSBpbnN0ZWFkIG9mIGhpc3RvcnlcclxuICAgIGxpc3RlbihsaXN0ZW5lcikge1xyXG4gICAgICAvLyBDb3B5IG9mIGxhc3QgbG9jYXRpb24uXHJcbiAgICAgIGxldCBsYXN0UHVibGlzaGVkTG9jYXRpb24gPSBnZXRMb2NhdGlvbkluU3RvcmUodHJ1ZSlcclxuXHJcbiAgICAgIC8vIEtlZXAgdHJhY2sgb2Ygd2hldGhlciB3ZSB1bnN1YnNjcmliZWQsIGFzIFJlZHV4IHN0b3JlXHJcbiAgICAgIC8vIG9ubHkgYXBwbGllcyBjaGFuZ2VzIGluIHN1YnNjcmlwdGlvbnMgb24gbmV4dCBkaXNwYXRjaFxyXG4gICAgICBsZXQgdW5zdWJzY3JpYmVkID0gZmFsc2VcclxuICAgICAgY29uc3QgdW5zdWJzY3JpYmVGcm9tU3RvcmUgPSBzdG9yZS5zdWJzY3JpYmUoKCkgPT4ge1xyXG4gICAgICAgIGNvbnN0IGN1cnJlbnRMb2NhdGlvbiA9IGdldExvY2F0aW9uSW5TdG9yZSh0cnVlKVxyXG4gICAgICAgIGlmIChjdXJyZW50TG9jYXRpb24gPT09IGxhc3RQdWJsaXNoZWRMb2NhdGlvbikge1xyXG4gICAgICAgICAgcmV0dXJuXHJcbiAgICAgICAgfVxyXG4gICAgICAgIGxhc3RQdWJsaXNoZWRMb2NhdGlvbiA9IGN1cnJlbnRMb2NhdGlvblxyXG4gICAgICAgIGlmICghdW5zdWJzY3JpYmVkKSB7XHJcbiAgICAgICAgICBsaXN0ZW5lcihsYXN0UHVibGlzaGVkTG9jYXRpb24pXHJcbiAgICAgICAgfVxyXG4gICAgICB9KVxyXG5cclxuICAgICAgLy8gSGlzdG9yeSBsaXN0ZW5lcnMgZXhwZWN0IGEgc3luY2hyb25vdXMgY2FsbC4gTWFrZSB0aGUgZmlyc3QgY2FsbCB0byB0aGVcclxuICAgICAgLy8gbGlzdGVuZXIgYWZ0ZXIgc3Vic2NyaWJpbmcgdG8gdGhlIHN0b3JlLCBpbiBjYXNlIHRoZSBsaXN0ZW5lciBjYXVzZXMgYVxyXG4gICAgICAvLyBsb2NhdGlvbiBjaGFuZ2UgKGUuZy4gd2hlbiBpdCByZWRpcmVjdHMpXHJcbiAgICAgIGxpc3RlbmVyKGxhc3RQdWJsaXNoZWRMb2NhdGlvbilcclxuXHJcbiAgICAgIC8vIExldCB1c2VyIHVuc3Vic2NyaWJlIGxhdGVyXHJcbiAgICAgIHJldHVybiAoKSA9PiB7XHJcbiAgICAgICAgdW5zdWJzY3JpYmVkID0gdHJ1ZVxyXG4gICAgICAgIHVuc3Vic2NyaWJlRnJvbVN0b3JlKClcclxuICAgICAgfVxyXG4gICAgfSxcclxuXHJcbiAgICAvLyBJdCBhbHNvIHByb3ZpZGVzIGEgd2F5IHRvIGRlc3Ryb3kgaW50ZXJuYWwgbGlzdGVuZXJzXHJcbiAgICB1bnN1YnNjcmliZSgpIHtcclxuICAgICAgaWYgKGFkanVzdFVybE9uUmVwbGF5KSB7XHJcbiAgICAgICAgdW5zdWJzY3JpYmVGcm9tU3RvcmUoKVxyXG4gICAgICB9XHJcbiAgICAgIHVuc3Vic2NyaWJlRnJvbUhpc3RvcnkoKVxyXG4gICAgfVxyXG4gIH1cclxufVxyXG4iXX0=