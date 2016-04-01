/*TODO:define how to pass range and dates filters*/


const selected=(values)=>{
	return values.filter((it)=>{return it.selected || (it.selectedCounter && it.selectedCounter > 0)});
}

const collect=(options)=>{
	let values=[];
   //first level iteration 
   options.forEach((item)=>{
		if (item.selected || (item.selectedCounter > 0)){ //to add a partial selected item check it.selectedCounter > 0 or selected(item.items).length > 0 
			values.push(item.id); //use values.push(item.name);  for debug purpose instead of id 
		}
		if (item.items){ //next levels iterations
			let nested=collect(selected(item.items));
			values=values.concat(nested);
		}
	});
   return values;
}

const collectRange=(options)=>{
	let values=[];
   	if(options.minSelected && options.maxSelected){
   		values.push(options.minSelected);
   		values.push(options.maxSelected);
   	}
   return values;
}

export const collectValues=filters=>{
	let params={};
	
	for(let param in filters){
		let options=filters[param].items;
		let selection=filters[param].isRange? collectRange(filters[param]) : collect(selected(options));
		if(selection.length > 0){
			params[param]=selection;			
		}
	}
	console.log(params)
	return params;

}


