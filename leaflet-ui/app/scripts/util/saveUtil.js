
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

const collectBaseMap=(options)=>{
    return {
        'name': options._root.entries[0][1]._root.entries[0][1], 
        'url': options._root.entries[0][1]._root.entries[1][1]
    };
}


export const collectValuesToSave = (state)=>{
    console.log('collectValuesToSave');
    let filters=state.filters.filterMain;
    let projectSearch=state.projectSearch;
    let map=state.map;
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
    if(map){ 
        Object.assign(params, {'basemap': collectBaseMap(map)});
    }
    return params;
}