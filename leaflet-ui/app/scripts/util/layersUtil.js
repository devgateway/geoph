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
	let total = 0;
	let values = features.map(function(f) { 
		if (valueProperty=='funding'){
			let val = f.properties[measure][type]||0
			total += val;
			return val
		} else {
			let val = f.properties[valueProperty]||0
			total += val;
			return val
		}
	});
	return {values, total};
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
    const {thresholds, cssProvider, valueProperty} = layer;
	const {values, total} = getValues(features, valueProperty, fundingType);//isolate features values 
	const breaks = (thresholds > values.length)? values.length : thresholds;
	let classProvider = null;
	if (cssProvider){
  		classProvider = new cssProvider(values,breaks);
  		let jenkValues = classProvider.getDomain(breaks);
  		let legendList = [];
  		if (jenkValues && total>0){
  			for (var i = 0; i < jenkValues.length-1; i++) {
		    	let cls = 'legend-'+classes+i+'-9';
		    	let label = formatValue(parseInt(jenkValues[i]))+' - '+formatValue(parseInt(jenkValues[i+1]));
		    	if (jenkValues[i]+jenkValues[i+1]>0){ //ignores '0 - 0' labels
			    	legends.push({cls, label});
			    }
		  	};
  		} else {
  			let cls = 'legend-'+classes+'-none';//put none class for show zero values in gray
  			legends.push({cls, 'label': '0'});
  		}
	  	
  	} else {
  		let cls = 'legend-'+classes+'4-9';
  		legends.push({cls, 'label': ''});
	}
    return {legends, features: fillFeaturesWithClass(features, classProvider, fundingType, valueProperty, classes)};
}

const fillFeaturesWithClass=(features, classProvider, fundingType, valueProperty, classes)=>{
	let featuresWithClass = features.slice();
	featuresWithClass.map((feature)=>{
		const {measure, type} = fundingType;
		let className = ''
	    if (!classProvider){
	      	className = classes + '4-9';
	    } else {
	    	const value = valueProperty=='funding'? feature.properties[measure][type] : feature.properties[valueProperty];
	    	if (value){
	    		className = classes + classProvider.getCssClass(value);
	    	} else {
	    		className = classes + '-none';
	    	}    		
	    }
    	Object.assign(feature.properties, {className});
	});	
	return featuresWithClass;
}