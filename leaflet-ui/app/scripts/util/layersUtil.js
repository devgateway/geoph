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