 import geostats from  './geostats.js';
 import d3 from 'd3';



  

const JenksCssProvider=(cssPrefix)=>{
return	class JenksCssProvider{

 	constructor(values,breaks){
 		console.log(breaks)
 		this.geo=new geostats(values);
 		
 		console.log(this.getDomain(breaks));
 		console.log(this.getRange(breaks+1));
 		
 		this.generator = d3.scale.threshold().domain(this.getDomain(breaks)).range(this.getRange(breaks+2));  

 	}


 	getRange(size){
		return d3.range(size).map(function(i) { return  cssPrefix + i + "-9"; });
 	}

 	getDomain(breaks){
 		return this.geo.getClassJenks(breaks);
 	}

 	getCssClass(value){
 		let cssClass=this.generator(value);
 		if (!cssClass){
 			
 		}
 		return cssClass;
 	}

 }
}
 export default  JenksCssProvider
