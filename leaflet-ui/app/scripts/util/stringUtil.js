
export const capitalizeString=(string)=>{
   if (!string || string.length==0){
        return "";
    }
    return string[0].toUpperCase() + string.replace(/ ([a-z])/g, function(a, b) {
        return ' ' + b.toUpperCase();
    }).slice(1);
}

export const ellipseString=(string, length)=>{
    return string.length>length? string.substr(0,length-3)+'...' : string;
}
