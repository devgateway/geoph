import Immutable from 'immutable';


export const getPath=(id,paths)=>{
 let path=[];
 id.split('-').forEach(pos=>{
  path.push("layers");
  path.push(parseInt(pos));
});

 if (paths){
  path=path.concat(paths);
}
return path;
}


const plainList=(layers, accumulator)=>{
 accumulator=accumulator || [];
   layers.forEach(l=>{
 		if (l.get('layers')){
 			return plainList(l.get('layers'),accumulator);	
 		}else{
 			accumulator.push(l);
 		}
 })
  return new Immutable.List(accumulator);
}

export const getDefaults=(layers)=>{
	const list=plainList(layers);
	return list.filter(function(l){return l.get('default')})
}

export const getShapeLayers=(layers)=>{
	const list=plainList(layers);
	return list.filter(function(l){return l.get('type')=='shapes'})
}

export const getVisibles=(layers)=>{
	const list=plainList(layers);
	return list.filter(function(l){return l.get('visible')})
}

export const getValues=(features, valueProperty, fundingType)=>{
	const {measure, type} = fundingType;
	return features.map(function(f) { return valueProperty=='funding'? f.properties[measure][type]||0 : f.properties[valueProperty]||0});
}

export const filter=(data, valueProperty, fundingType, map)=>{
    var bounds = map.getBounds();
    const {measure, type} = fundingType;
    const filtered = data.filter((f)=>f.geometry?bounds.contains(L.geoJson(f).getBounds()):false).sort((f)=>{valueProperty=='funding'? f.properties[measure][type] : f.properties[valueProperty]})
    //console.log('Removed =>'+(data.length - filtered.length));
    return filtered;
  }

export const mergeAllLayersFeatures=(layers, fundingType, map)=>{
    let size=0, border=0;
    let allLayersFeatures = [];
    layers.map((layer)=>{
      let prefix=layer.cssPrefix;
      let css=layer.settings.css;
      let classes=prefix+' '+css ;
      Object.assign(layer, {classes});
      if (layer.data && layer.data.features){    
        const values = getValues(layer.data.features, layer.valueProperty, fundingType);//isolate features values 
        const {thresholds,cssProvider} = layer;
        const breaks = (thresholds > values.length)?values.length:thresholds;
        let classProvider = cssProvider? new cssProvider(values,breaks): null;
    	size = layer.size>size? layer.size : size;
        border = layer.border>border? layer.border : border;
        let fts = layer.type=="points"? filter(layer.data.features, layer.valueProperty, fundingType, map) : layer.data.features; 
        fts.map((feature)=>{
          Object.assign(feature.properties, 
            {classes: classes, cssProvider: classProvider, valueProperty: layer.valueProperty, size: layer.size, border: layer.border, popupId: layer.popupId || 'defaultPopup', layerName: layer.name});//Assign class data to feature properties
        })
        allLayersFeatures = allLayersFeatures.concat(fts);
      }
    })
    return {size, border, allLayersFeatures};
}

export const createLegendsFromLayers=(layers, fundingType)=>{
   	let legends = [];
    layers.map((layer)=>{
		let css=layer.settings.css;
		let legend = {name: layer.name, keyName: layer.keyName};
		if (layer.data && layer.data.features){    
			const values = this.getValues(layer.data.features, layer.valueProperty, fundingType);//isolate features values 
			const {thresholds,cssProvider} = layer;
			const breaks = (thresholds > values.length)?values.length:thresholds;
			if (cssProvider){
		  		let classProvider = new cssProvider(values,breaks);
		  		let jenkValues = classProvider.getDomain(breaks-1);
		  		let legendList = [];
			  	for (var i = 0; i < jenkValues.length; i++) {
			    	let cls = 'legend '+css+i+'-9';
			    	let label = ''+(i==0?'0':jenkValues[i-1])+' - '+jenkValues[i];
			    	legendList.push({cls, label});
			  	};
		  		Object.assign(legend, {legendList});
			} else {
		  		let cls = 'legend '+css+'4-9';
		  		Object.assign(legend, {legendList: [{cls, 'label': ''}]});
			}
		}
	    legends.push(legend);
    })
    return legends;
}