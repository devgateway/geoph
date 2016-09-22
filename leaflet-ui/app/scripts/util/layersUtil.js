import Immutable from 'immutable';
import {formatValue} from './format.js'

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
		let val;
		if (valueProperty=='funding'){
			val=  f.properties[measure][type];
		} else {
			val=  f.properties[valueProperty];
		}
		return val || 0
	});
	return values.filter((val)=>{return val>0});//return array with values (zero values removed)
}

export const createCSSProviderInstance=(thresholds, values, cssProvider)=>{
	const valuesUnique = [...new Set(values)];
	const breaks = (thresholds >= valuesUnique.length)? valuesUnique.length-1 : thresholds;
	return (cssProvider)? new cssProvider(valuesUnique, breaks):null;
}


export const createSimpleLegend=(cssPrefix,css)=>{

}

export const createLegendsByDomain=(domain,cssPrefix,css)=>{
	const classNames=`legend-${cssPrefix} `
	let legends;
	if (domain && domain.length == 1){
		const cls = `${classNames}${css}0-9`;
		const label = `0 - ${formatValue(parseInt(domain[0]))}`;
		legends=[{cls,label}];
	} else if (domain && domain.length > 1){
		legends=domain.map((val,i,arr)=>{			
			const cls = `${classNames}${css}${i}-9`;
			const start=formatValue(parseInt(val));
			const end =formatValue(parseInt(arr[i+1]));
			let label =`${start} - ${end}`;
			return {cls,label};
		});
		legends.pop();//removes the last element because is and invalid range added
	} else {
		let cls = `${classNames}${css}-none`;//put none class for show zero values in gray
		legends=[{cls, 'label': '0'}]
	}
	return legends;
}


const getFeatureValue=(feature,valueProperty,measure, type)=>{
	return (valueProperty=='funding')? feature.properties[measure][type] : feature.properties[valueProperty];
}

export const getStyledGeoJson=(geojson,layerSettings,classProviderInstance)=>{
	debugger;
	const {features}=geojson;
	const {valueProperty, size, border, popupId='defaultPopup', layerName,fundingType,cssPrefix,css,labelFunc}= layerSettings;
	const {measure, type} = fundingType;
	const classes = `${cssPrefix} ${css}`;

	const newFeatures=features.map((feature)=>{
		let className = ''
		const value = getFeatureValue(feature,valueProperty,measure, type);
		const label=labelFunc?labelFunc(feature,value):null;

		if (value){
			className= classProviderInstance.getCssClass(value);
		} else {
			className = '-none';
		}    



		const newFeature=Object.assign({},feature);
		Object.assign(newFeature.properties,{className:`${classes}${className}`,size, border, layerName,popupId,valueProperty,value,label});//Assign extra data to feature properties
		return newFeature;
	});	

	return Object.assign(geojson,{features:newFeatures});
}
