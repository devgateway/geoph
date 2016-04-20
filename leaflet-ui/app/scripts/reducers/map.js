import { LAYER_LOAD_SUCCESS,LAYER_LOAD_FAILURE , TOGGLE_LAYER} from '../constants/constants';

import Immutable from 'immutable';

const defaultState = Immutable.fromJS({
  layers: [{
    id: '0',
    keyName: 'projects',
    layers: [{
      id: '02',
      ep:'PROJECT_GEOJSON',
      settings:{'level':'region'},
      keyName: 'project'
    }]
  }, {
    id: '1',
    keyName: 'stats',
    layers: [{
      id: '10',
      ep:'FUDNING_GEOJSON',
      settings:{'quality':0.01},
      keyName: 'funding'
    }, {
      id: '11',
      keyName: 'indicators',
      layers: [{
        id: '111',
        keyName: 'poverty'
      }, {
        id: '112',
        keyName: 'population'
      }]
    }]
  }, {
    id: '2',
    keyName: 'geophotos'
  }]
});



const setPropsToLayer=(layers,id,properties)=>{
  return layers.map((l)=>{
    
    if (l.get('id')==id){
      return l.merge(properties);
    }else if(l.get('layers')){
       l=l.set('layers', setPropsToLayer(l.get('layers'),id,properties))
      return l;
    }
    
      return l;
    
  });
}

const setProps=(layers,props)=>{
  return layers.map((l)=>{
    if (l.get('layers')){
      l=l.set('layers',setProps(l.get('layers'),props));
    }
    return l.merge(props);

  })
}


const toogle=(layers,id,property)=>{

  return layers.map((l)=>{
    if (l.get('id')==id || id==null){
            l=l.set(property,!l.get(property)); //toggle property of current layer
            if (l.get('layers')){
              //if it is a group update child with curren parrent value
              l=l.set('layers', setProps(l.get('layers'),{'visible':l.get('visible')})) 
            }
            return l;

          }else if(l.get('layers')){
           return l.set('layers',toogle(l.get('layers'),id,property))
         }
         return l;
       })
}


const map = (state = defaultState, action) => {
  
  switch (action.type) {
    case TOGGLE_LAYER:
      return state.set('layers',toogle(state.get('layers'),action.id,'visible'));
    case LAYER_LOAD_SUCCESS:
      let newState= state.set('layers',setPropsToLayer(state.get('layers'),action.id,{data:action.data}));
      
      return newState;
    case LAYER_LOAD_FAILURE:
      default:
    return state
  }
}


export default map;

