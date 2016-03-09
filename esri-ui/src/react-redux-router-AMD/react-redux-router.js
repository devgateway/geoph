// fid-umd {"name":"Unknown"}

(function (name, root, factory) {
    function isObject(x) { return typeof x === "object"; }
    if (isObject(module) && isObject(module.exports)) {
        module.exports = factory();
    } else if (isObject(exports)) {
        exports[name] = factory();
    } else if (isObject(root.define) && root.define.amd) {
        root.define(name, [], factory);
    } else if (isObject(root.modulejs)) {
        root.modulejs.define(name, factory);
    } else if (isObject(root.YUI)) {
        root.YUI.add(name, function (Y) { Y[name] = factory(); });
    } else {
        root[name] = factory();
    }
}("react-redux-router", this, function () {
    // fid-umd end

const defaultSelectLocationState = state => state.routing

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
function syncHistoryWithStore(history, store, {
  selectLocationState = defaultSelectLocationState,
  adjustUrlOnReplay = true
} = {}) {
  // Ensure that the reducer is mounted on the store and functioning properly.
  if (typeof selectLocationState(store.getState()) === 'undefined') {
    throw new Error(
      'Expected the routing state to be available either as `state.routing` ' +
      'or as the custom expression you can specify as `selectLocationState` ' +
      'in the `syncHistoryWithStore()` options. ' +
      'Ensure you have added the `routerReducer` to your store\'s ' +
      'reducers via `combineReducers` or whatever method you use to isolate ' +
      'your reducers.'
    )
  }

  let initialLocation
  let currentLocation
  let isTimeTraveling
  let unsubscribeFromStore
  let unsubscribeFromHistory

  // What does the store say about current location?
  const getLocationInStore = (useInitialIfEmpty) => {
    const locationState = selectLocationState(store.getState())
    return locationState.locationBeforeTransitions ||
      (useInitialIfEmpty ? initialLocation : undefined)
  }

  // If the store is replayed, update the URL in the browser to match.
  if (adjustUrlOnReplay) {
    const handleStoreChange = () => {
      const locationInStore = getLocationInStore(true)
      if (currentLocation === locationInStore) {
        return
      }

      // Update address bar to reflect store state
      isTimeTraveling = true
      currentLocation = locationInStore
      history.transitionTo({
        ...locationInStore,
        action: 'PUSH'
      })
      isTimeTraveling = false
    }

    unsubscribeFromStore = store.subscribe(handleStoreChange)
    handleStoreChange()
  }

  // Whenever location changes, dispatch an action to get it in the store
  const handleLocationChange = (location) => {
    // ... unless we just caused that location change
    if (isTimeTraveling) {
      return
    }

    // Remember where we are
    currentLocation = location

    // Are we being called for the first time?
    if (!initialLocation) {
      // Remember as a fallback in case state is reset
      initialLocation = location

      // Respect persisted location, if any
      if (getLocationInStore()) {
        return
      }
    }

    // Tell the store to update by dispatching an action
    store.dispatch({
      type: LOCATION_CHANGE,
      payload: location
    })
  }
  unsubscribeFromHistory = history.listen(handleLocationChange)

  // The enhanced history uses store as source of truth
  return {
    ...history,
    // The listeners are subscribed to the store instead of history
    listen(listener) {
      // Copy of last location.
      let lastPublishedLocation = getLocationInStore(true)

      // Keep track of whether we unsubscribed, as Redux store
      // only applies changes in subscriptions on next dispatch
      let unsubscribed = false
      const unsubscribeFromStore = store.subscribe(() => {
        const currentLocation = getLocationInStore(true)
        if (currentLocation === lastPublishedLocation) {
          return
        }
        lastPublishedLocation = currentLocation
        if (!unsubscribed) {
          listener(lastPublishedLocation)
        }
      })

      // History listeners expect a synchronous call. Make the first call to the
      // listener after subscribing to the store, in case the listener causes a
      // location change (e.g. when it redirects)
      listener(lastPublishedLocation)

      // Let user unsubscribe later
      return () => {
        unsubscribed = true
        unsubscribeFromStore()
      }
    },

    // It also provides a way to destroy internal listeners
    unsubscribe() {
      if (adjustUrlOnReplay) {
        unsubscribeFromStore()
      }
      unsubscribeFromHistory()
    }
  }
}

const LOCATION_CHANGE = '@@router/LOCATION_CHANGE'

const initialState = {
  locationBeforeTransitions: null
}

/**
 * This reducer will update the state with the most recent location history
 * has transitioned to. This may not be in sync with the router, particularly
 * if you have asynchronously-loaded routes, so reading from and relying on
 * this state it is discouraged.
 */
function routerReducer(state = initialState, { type, payload }) {
  if (type === LOCATION_CHANGE) {
    return { ...state, locationBeforeTransitions: payload }
  }

  return state
}

const CALL_HISTORY_METHOD = '@@router/CALL_HISTORY_METHOD'

/**
 * This middleware captures CALL_HISTORY_METHOD actions to redirect to the
 * provided history object. This will prevent these actions from reaching your
 * reducer or any middleware that comes after this one.
 */
function routerMiddleware(history) {
  return () => next => action => {
    if (action.type !== CALL_HISTORY_METHOD) {
      return next(action)
    }

    const { payload: { method, args } } = action
    history[method](...args)
  }
}

    // fid-umd post
}));
// fid-umd post-end
