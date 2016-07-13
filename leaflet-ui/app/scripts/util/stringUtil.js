
export const capitalizeString=(str)=>{
   if (!str || str.length==0){
        return "";
    }
    return str[0].toUpperCase() + str.replace(/ ([a-z])/g, function(a, b) {
        return ' ' + b.toUpperCase();
    }).slice(1);
}
