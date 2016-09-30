
export const formatValue=(value, decimals)=>{
  let v;
  let dec = decimals==undefined? 3 : decimals;
  if (value==0){
    return "0";
  } else if (value>1000000000){
    v = (value/1000000000).toFixed(dec);
    return ""+v+"B";
  } else if (value>1000000){
    v = (value/1000000).toFixed(dec);
    return ""+v+"M";
  } else if (value>1000){
    v = (value/1000).toFixed(dec);
    return ""+v+"K";
  } else {
    if (Number.isInteger(value)){
      return ""+value;
    } else {
      return ""+value.toFixed(dec);
    }    
  }
}

export const roundValue=(value)=>{
  let val = 0
  for (var i = 2; i<10; i++) {
    let roundMult = 10^(i-1);
    if (value<10^i){
      val = Math.ceil(value/roundMult)*roundMult;
    }
  };
  return val;
}

export const formatAndRoundValue=(value)=>{
  let val;
  if (value==0){
    return "0";
  } else if (value>=1000000000){
    val = (value/1000000000);
    return ""+roundValue(val)+"B";
  } else if (value>=1000000){
    val = (value/1000000);
    return ""+roundValue(val)+"M";
  } else if (value>=1000){
    val = (value/1000);
    return ""+roundValue(val)+"K";
  } else {
    val = value;
    return ""+roundValue(val);
  }
}