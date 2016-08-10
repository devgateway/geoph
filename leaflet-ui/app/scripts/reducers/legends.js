import * as Constants from '../constants/constants';
import {Map} from 'immutable'
import {createLegendsFromLayers} from '../util/legends';

const legends =(state = new Map({'legends':[]}), action) => {

  switch (action.type) {    
    case Constants.CREATE_LEGENDS:
        const {layers, fundingType} = action
        return state.set('legends', createLegendsFromLayers(layers, fundingType));
    default:
        return state
  }
}

export default legends;