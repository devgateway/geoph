import {getVisibles, getPath, plainList} from './layersUtil';
import translate from './translate';

const collect=(options)=>{
  let values=[];
  //first level iteration
  options.forEach((item)=>{
    if (item.selected){
      values.push(item.id); //use values.push(item.name);  for debug purpose instead of id
    }
    if (item.items){ //next levels iterations
      let nested=collect(item.items);
      values=values.concat(nested);
    }
  });
  return values;
}

const collectRange=(options)=>{
  return {'minSelected': options.minSelected, 'maxSelected': options.maxSelected};
}


const getBgColorFromCssName=(cls)=>{
  var div = document.createElement("div");
  div.style.display = "none";
  document.body.appendChild(div);
  div.className = cls;
  
  var color=getComputedStyle(div).getPropertyValue("background-color");
  div.parentNode.removeChild(div)
  var matchColors = /rgb\((\d{1,3}), (\d{1,3}), (\d{1,3})\)/;
  var match = matchColors.exec(color);
  if (match !== null) {
    return {r: match[1],g:match[2] ,b:match[3]};
  }
  
  return null;
}


export const collectValuesToSave = (state)=>{
  console.log('collectValuesToSave');
  let filters=state.filters.filterMain;
  let projectSearch=state.projectSearch;
  let map=state.map;
  let settings=state.settings;
  let params={};
  let filterParams={};
  let selection;
  for(let param in filters){
    let options=filters[param].items;
    if (filters[param].isRange){
      selection=collectRange(filters[param]);
      if(selection.minSelected){
        filterParams[param+'_min']=selection.minSelected;
      }
      if(selection.maxSelected){
        filterParams[param+'_max']=selection.maxSelected;
      }
    } else {
      selection=collect(options);
      if(selection.length > 0){
        filterParams[param]=selection;
      }
    }
  }
  if (projectSearch){
    let idsSelected = [];
    projectSearch.selected.map(it => idsSelected.push(it.id));
    Object.assign(filterParams, {'pr': idsSelected});
  }
  Object.assign(params, {'filters': filterParams});
  if(map){
    let layers = getVisibles(map.get('layers')).toJS();
    let visibleLayers = [];
    layers.map((layer)=>{
      const {legends, name, keyName, id} = layer;
      let nameLabel =  keyName? translate("toolview.layers."+keyName) : name;
      
      var coloredLegend=legends.map((l)=>{
        const {cls}=l;
        const color=getBgColorFromCssName(cls)
        return {...l,color}
      })
      visibleLayers.push({id, name: nameLabel, legends:coloredLegend});
    })
    Object.assign(params, {'visibleLayers': visibleLayers});
  }
  let layersPlain = plainList(map.get('layers'));
  layersPlain.forEach(function(l){
    map = map.setIn(getPath(l.get('id'), ['data']), {});
  })
  map = map.set('defaultBounds', map.get('bounds'));//move bounds value to defaultBounds to be used as default on restore
  Object.assign(params, {'map': map});
  Object.assign(params, {'settings': settings});
  
  return params;
}