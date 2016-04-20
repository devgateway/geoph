export const plainLayers=(layers)=>{
  return layers.map((l)=>{
    if (l.layers){
      return plainLayers(l.layers);
    }else{
      return l;
    }
  })

}

export  const getLayerById=(id,layers)=>{
  return plainLayers(layers).find((l)=>l.id=id);
}


export const updateLayer=(layers,id,props)=>{
    layers.forEach(function(l){
        if (l.id==id){
          Object.assign(l,props)
          return;
        }else if (l.layers){
          updateLayer(l.layers,id,props);
        }
    })

return layers;
}

export const toggleLayerProperty=(id,state,property)=>{

  let newState=Object.assign({},state);
  let target=getLayerById(id,newState.layers);
  updateLayer(newState.layers, id,{visible:!target[property]});
  return newState
}