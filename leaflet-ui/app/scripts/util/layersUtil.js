import Immutable from 'immutable';
import {formatValue} from './transactionUtil'

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
    return filtered;
}

export const mergeAllLayersFeatures=(layers, fundingType, map)=>{
    let maxSize=0, maxBorder=0;
    let allLayersFeatures = [];
    layers.map((layer)=>{
      	const {data, size, border, valueProperty, type, popupId, name} = layer;
      	if (data && data.features){    
	        maxSize = size>maxSize? size : maxSize;
	        maxBorder = border>maxBorder? border : maxBorder;
	        let fts = layer.type=="points"? filter(data.features, valueProperty, fundingType, map) : data.features; 
	        fts.map((feature)=>{
	          Object.assign(feature.properties, 
	            {valueProperty: valueProperty, size, border, popupId: popupId || 'defaultPopup', layerName: name});//Assign class data to feature properties
	        })
	        allLayersFeatures = allLayersFeatures.concat(fts);
      	}	
    })
    return {size: maxSize, border: maxBorder, allLayersFeatures};
}

export const createLegendsAndClassesForLayer=(layer, features, fundingType)=>{
   	let legends = [];
	let classes = layer.cssPrefix+' '+layer.settings.css;
    let featuresWithClass = features.slice();
    const values = getValues(features, layer.valueProperty, fundingType);//isolate features values 
	const {thresholds, cssProvider} = layer;
	const breaks = (thresholds > values.length)? values.length : thresholds;
	let classProvider = null;
	if (cssProvider){
  		classProvider = new cssProvider(values,breaks);
  		let jenkValues = classProvider.getDomain(breaks);
  		let legendList = [];
	  	for (var i = 0; i < jenkValues.length-1; i++) {
	    	let cls = 'legend-'+classes+i+'-9';
	    	let label = formatValue(parseInt(jenkValues[i]))+' - '+formatValue(parseInt(jenkValues[i+1]));
	    	legends.push({cls, label});
	  	};
  	} else {
  		let cls = 'legend-'+classes+'4-9';
  		legends.push({cls, 'label': ''});
	}
	featuresWithClass.map((feature)=>{
		const {measure, type} = fundingType;
		let className = ''
	    if (!classProvider){
	      	className = classes + '4-9';
	    } else {
	    	const value = layer.valueProperty=='funding'? feature.properties[measure][type] : feature.properties[layer.valueProperty];
    		className = classes + classProvider.getCssClass(value);
	    }
    	Object.assign(feature.properties, {className});
	});
    return {legends, features: featuresWithClass};
}
