/*TODO:define how to pass range and dates filters*/

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
};

const collectRange=(options)=>{
  return {'minSelected': options.minSelected, 'maxSelected': options.maxSelected};
};

export const collectValues = (filters, projectSearch)=>{
  let params={};
  let selection;
  for(let param in filters){
    let options=filters[param].items;
    if (filters[param].isRange){
      selection=collectRange(filters[param]);
      if(selection.minSelected!=undefined){
        params[param+'_min']=selection.minSelected;
      }
      if(selection.maxSelected!=undefined){
        params[param+'_max']=selection.maxSelected;
      }
    } else {
      selection=collect(options);
      if(selection.length > 0){
        params[param]=selection;
      }
    }
  }
  
  if (projectSearch){
    let idsSelected = [];
    projectSearch.applied.map(it => idsSelected.push(it.id));
    Object.assign(params, {'pr': idsSelected});
  }
  return params;
};

export const cloneDeep = (objectToBeCloned) => {
  if (!(objectToBeCloned instanceof Object)) {
    return objectToBeCloned;
  }
  var objectClone;
  // Filter out special objects.
  var Constructor = objectToBeCloned.constructor;
  switch (Constructor) {
    // Implement other special objects here.
    case RegExp:
      objectClone = new Constructor(objectToBeCloned);
      break;
    case Date:
      objectClone = new Constructor(objectToBeCloned.getTime());
      break;
    default:
      objectClone = new Constructor();
  }
  // Clone each property.
  for (var prop in objectToBeCloned) {
    objectClone[prop] = cloneDeep(objectToBeCloned[prop]);
  }
  return objectClone;
};

const collectSelectionIntoChildren=(item, selection)=>{
  let childrenSelected = false;
  let childSelection = [];
  if (item.items && item.items.length>0){
    item.items.forEach((it) => {
      if (collectSelectionIntoChildren(it, childSelection)){
        childrenSelected = true;
      }
    });
  }
  if (item.selected || childrenSelected){
    let sel = {'name': item.name};
    if (childrenSelected){
      Object.assign(sel, {'childSelection': childSelection});
    }
    selection.push(sel);
    return true;
  } else {
    return false;
  }
};

const collectSelectedNames=(items)=>{
  let selection = [];
  items.forEach((item) => {
    collectSelectionIntoChildren(item, selection);
  });
  return selection;
};

export const getSelectedFilterNames=(filters)=>{
  let params={};
  let selection;
  for(let param in filters){
    let items = filters[param].items;
    if (filters[param].isRange){
      if(filters[param].minSelected || filters[param].maxSelected){
        params[param]={'from': filters[param].minSelected, 'to': filters[param].maxSelected};
      }
    } else {
      selection = collectSelectedNames(items);
      if(selection.length > 0){
        params[param]=selection;
      }
    }
  }
  return params;
};

export const getLogSliderValue=(minval, maxval, position)=>{
  if (position==0){
    return 0;
  }
  let minpos = 0, maxpos=100;
  let minlval = Math.log(minval || 1);
  let maxlval = Math.log(maxval || 100000);
  let scale = (maxlval - minlval) / (maxpos - minpos);
  return Math.exp((position - minpos) * scale + minlval);
};

export const getLogSliderPosition=(minval, maxval, value)=>{
  if (value==0){
    return 0;
  }
  let minpos=0, maxpos=100;
  let minlval = Math.log(minval || 1);
  let maxlval = Math.log(maxval || 100000);
  let scale = (maxlval - minlval) / (maxpos - minpos);
  return minpos + (Math.log(value) - minlval) / scale;
};

export const markApplied = (filters, type) => {
  let ret = false;
  if (!type || Array.isArray(type)){
    for (var fltr in filters) {
      if (type){
        if (type.indexOf(fltr)!=-1){
          if (filterApplied(filters[fltr])){
            ret = true;
          }
        }
      } else {
        if (filterApplied(filters[fltr])){
          ret = true;
        }
      }
    }
  } else {
    ret = filterApplied(filters[type]);
  }
  return ret? " (•)" : "";
};

const filterApplied = (filter) => {
  if (filter.isRange){
    return (filter.minSelected || filter.maxSelected)? true : false;
  } else {
    return (filter.selectedCounter>0)? true : false;
  }
};
