import {getVisibles, getPath, plainList} from './layersUtil';
import translate from './translate';

const collect = (options) => {
  let values = [];
  //first level iteration
  options.forEach((item) => {
    if (item.selected) {
      values.push(item.id); //use values.push(item.name);  for debug purpose instead of id
    }
    if (item.items) { //next levels iterations
      let nested = collect(item.items);
      values = values.concat(nested);
    }
  });
  return values;
};

const collectRange = (options) => {
  return {'minSelected': options.minSelected, 'maxSelected': options.maxSelected};
};

const getBgColorFromCssName = (cls) => {
  const div = document.createElement("div");
  div.style.display = "none";
  document.body.appendChild(div);
  div.className = cls;
  
  const color = getComputedStyle(div).getPropertyValue("background-color");
  div.parentNode.removeChild(div)
  const matchColors = /rgb\((\d{1,3}), (\d{1,3}), (\d{1,3})\)/;
  const match = matchColors.exec(color);
  if (match !== null) {
    return {r: match[1], g: match[2], b: match[3]};
  }
  
  return null;
};

/**
 * Functions that collects the necessary data in order to restore a map that was saved.
 */
export const collectValuesToSave = (state) => {
  // easy way to detect if we want to save a comparison.
  const isCompare = state.compare.size !== 0;
  
  // the main map state
  const map = state.map;
  const filters = state.filters.filterMain;
  const projectSearch = state.projectSearch;
  const settings = state.settings;
  
  // if we don't have a comparison the the *data* field is just an object
  if (!isCompare) {
    return createDataObjectToSave(map, filters, projectSearch, settings);
  } else {
    // if we have a comparison then the *data* field is an array of objects - each map with it's on data.
    const mapCompare = state.compare.get("map");
    const filtersCompare = state.compare.get("filters").filterMain;
    const projectSearchCompare = state.compare.get("projectSearch");
    const settingsCompare = state.compare.get("settings");
    
    return [
      createDataObjectToSave(map, filters, projectSearch, settings),
      createDataObjectToSave(mapCompare, filtersCompare, projectSearchCompare, settingsCompare)
    ];
  }
};

/**
 * Functions that builds the *data* object that will be send to the server.
 */
const createDataObjectToSave = (map, filters, projectSearch, settings) => {
  const params = {};
  const filterParams = {};
  let selection;
  
  for (let param in filters) {
    let options = filters[param].items;
    if (filters[param].isRange) {
      selection = collectRange(filters[param]);
      if (selection.minSelected) {
        filterParams[param + '_min'] = selection.minSelected;
      }
      if (selection.maxSelected) {
        filterParams[param + '_max'] = selection.maxSelected;
      }
    } else {
      selection = collect(options);
      if (selection.length > 0) {
        filterParams[param] = selection;
      }
    }
  }
  
  if (projectSearch) {
    let idsSelected = [];
    projectSearch.selected.map(it => idsSelected.push(it.id));
    Object.assign(filterParams, {'pr': idsSelected});
  }
  
  Object.assign(params, {'filters': filterParams});
  
  if (map) {
    let layers = getVisibles(map.get('layers')).toJS();
    let visibleLayers = [];
    layers.map((layer) => {
      const {legends, name, keyName, id} = layer;
      let nameLabel = keyName ? translate("toolview.layers." + keyName) : name;
      
      const coloredLegend = legends.map((l) => {
        const {cls} = l;
        const color = getBgColorFromCssName(cls);
        return {...l, color}
      });
      visibleLayers.push({id, name: nameLabel, legends: coloredLegend});
    });
    Object.assign(params, {'visibleLayers': visibleLayers});
  }
  
  let layersPlain = plainList(map.get('layers'));
  layersPlain.forEach(function (l) {
    map = map.setIn(getPath(l.get('id'), ['data']), {});
  });
  
  map = map.set('defaultBounds', map.get('bounds')); //move bounds value to defaultBounds to be used as default on restore
  Object.assign(params, {'map': map});
  Object.assign(params, {'settings': settings});
  
  return params;
};