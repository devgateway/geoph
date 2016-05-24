import * as Constants from '../constants/constants';

const projectSearch = (state = {}, action) => {
  let projectSearchResults;
  switch (action.type) {
    case Constants.REQUEST_PROJECT_BY_TEXT:
      projectSearchResults = {isFetching: true};
      return Object.assign({}, projectSearchResults);
    case Constants.RECEIVE_PROJECT_BY_TEXT:
      projectSearchResults = {lastUpdate: action.receivedAt, isFetching: false};
      Object.assign(projectSearchResults, action.data);
      return Object.assign({}, projectSearchResults);
    default:
      return state
  }
}


export default projectSearch