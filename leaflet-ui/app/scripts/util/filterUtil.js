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
}

const collectRange=(options)=>{
	return {'minSelected': options.minSelected, 'maxSelected': options.maxSelected};
}

export const collectValues = (filters, projectSearch)=>{
	let params={};
	let selection;
	for(let param in filters){
		let options=filters[param].items;
		if (filters[param].isRange){
			selection=collectRange(filters[param]);
			if(selection.minSelected){				
				params[param+'_min']=selection.minSelected;			
			}
			if(selection.maxSelected){				
				params[param+'_max']=selection.maxSelected;			
			}
		} else {
			selection=collect(options);
			if(selection.length > 0){
				params[param]=selection;			
			}
		}
	}
	////console.log(params)
	if (projectSearch){
		let idsSelected = [];
	  	projectSearch.selected.map(it => idsSelected.push(it.id));
	  	Object.assign(params, {'pr': idsSelected});		
	}
	return params;
}

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
}

