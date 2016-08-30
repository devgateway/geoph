
export const encodeImages=(string, length)=>{

    /*
     for(var i=0;i < document.images.length;i++) {
     
     var img = new Image();
     img.setAttribute('crossOrigin', 'anonymous');

     img.onload = function() {
     var canvas = document.createElement('canvas');
     var ctx = canvas.getContext('2d');
     ctx.drawImage(img,0,0, img.target.width, img.target.height);
     var imgStr = canvas.toDataURL("image/png", "");
     img.target.src=imgStr;

     }

     img.target=document.images[i]
     img.src =document.images[i].src;
     }
     */
}

export const getMapElementProperties=()=>{
   
    const element=document.getElementsByClassName("map")[0];
	
	var node=element.cloneNode(true)
    	node.getElementsByClassName("leaflet-control-zoom")[0].remove();
    const {clientWidth,clientHeight,offsetWidth,offsetHeight}=element;

    return {outerHTML:node.outerHTML,clientWidth,clientHeight,offsetWidth,offsetHeight };
}
