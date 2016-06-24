import * as Constants from '../constants/constants';
import Settings from '../util/settings';
import Connector from '../connector/connector';

export const togglePanelExpand = () => {
  return {
    type: Constants.TOGGLE_PANEL_EXPANDED
  }
}
