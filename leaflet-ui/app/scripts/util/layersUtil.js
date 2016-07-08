
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