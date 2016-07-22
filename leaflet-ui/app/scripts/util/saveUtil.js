
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

export const collectValuesToSave = (state)=>{
    console.log('collectValuesToSave');
    let filters=state.filters.filterMain;
    let projectSearch=state.projectSearch;
    let map=state.map;
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
    ////console.log(params)
    if (projectSearch){
        let idsSelected = [];
        projectSearch.selected.map(it => idsSelected.push(it.id));
        Object.assign(filterParams, {'pr': idsSelected});     
    }
    Object.assign(params, {'filters': filterParams});
    if(map){ 
        let mapJS = map.toJS();
        let bounds = map.toJS().bounds;
        let basemap = map.toJS().basemap;
        let allLayers = map.toJS().layers;
        let visibleLayers = [];
        map.toJS().layers.map((f)=>f.layers.map((e)=>{if(e.visible){visibleLayers.push(e.id)}}));
        Object.assign(params, {'map': {
            'bounds' : bounds,
            'basemap' : basemap,
            'visibleLayers' : visibleLayers
        }});
    }
    debugger;
    return params;
}