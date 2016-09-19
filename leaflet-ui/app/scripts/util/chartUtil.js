import translate from './translate.js';
import {capitalizeString, ellipseString} from './stringUtil.js';
import {formatValue} from './format';

export const parseDataChart=(chartType, props, chartContainerRef)=>{
    const {chartData, dimension, measure, width, height, hiddenlabels} = props;
    const {data, itemsToShow, measureType} = chartData;
  
    let labels = [];
    let values = [];
    let text = [];
    let totalAmount = 0;
    if (data && Array.isArray(data)){
      let others = 0;
      let dataSorted = sortDataByValue(data, measureType, measure);
      dataSorted.forEach((item, index) => {
        if (index < itemsToShow){
          if (measureType=='projectCount'){
            if (item.projectCount && parseInt(item.projectCount)>0){
              let label = ellipseString(capitalizeString(item[dimension]), 35);
              labels.push(label);
              values.push(item.projectCount);
              if(hiddenlabels.indexOf(label)==-1){
                totalAmount = totalAmount+parseInt(item.projectCount);
              }
              text.push(translate('chartview.projectcount')+": " + item.projectCount);
            }
          } else {
            if (item.trxAmounts[measure.measure][measure.type] && parseFloat(item.trxAmounts[measure.measure][measure.type])>0){
              let label = ellipseString(capitalizeString(item[dimension]), 35);
              labels.push(label);
              values.push(item.trxAmounts[measure.measure][measure.type]);
              if(hiddenlabels.indexOf(label)==-1){
                totalAmount = totalAmount+parseInt(item.trxAmounts[measure.measure][measure.type]);
              }
              text.push(translate('header.settings.'+measure.type) + " " + translate('header.settings.'+measure.measure) + " ₱ " + formatValue(parseFloat(item.trxAmounts[measure.measure][measure.type])));
            }
          }
        } else {
          if (measureType=='projectCount'){
            if (item.projectCount && item.projectCount>0){
              others = others + parseInt(item.projectCount);
            }
          } else {
            if (item.trxAmounts[measure.measure][measure.type] && parseFloat(item.trxAmounts[measure.measure][measure.type])>0){
              others = others + parseInt(item.trxAmounts[measure.measure][measure.type]);
            }
          }
        }
      });
      if (others>0 && chartType=='pie'){ 
        labels.push(translate('chartview.others'));
        values.push(others);
        if (hiddenlabels.indexOf(translate('chartview.others'))==-1){
          totalAmount = totalAmount+parseInt(others);
        }
        if (measureType=='projectCount'){
          text.push(translate('chartview.projectcount')+": " + others);
        } else {
          text.push(translate('header.settings.'+measure.type) + " " + translate('header.settings.'+measure.measure) + " ₱ " + formatValue(parseFloat(others)));
        }        
      }
    }
    
    if (chartType=='pie'){
      return {
        'data': [
          {
            'type': 'pie',      
            'labels': labels, 
            'values': values, 
            'text': text, 
            'marker':{
              'line': {'width': 0.5,'color': 'rgb(102, 102, 102)'}
            },
            'textposition': 'none',
            'domain':{
              x:[0,0.5],
              y:[0,1]
            },
            'hoverinfo': 'label+text+percent',
            'sort': true,
          }
        ],
        'layout': {         
          'height': height || 250, 
          'width': chartContainerRef? chartContainerRef.offsetWidthwidth : (width || 550),
          'margin':{
            't':5,
            'b':20,
            'l':10, 
            'r':10
          },
          'hiddenlabels': hiddenlabels, 
          'legend':{
            x:0.5,
            y:1,
            xanchor:"left",
            yanchor:"top",
            bgcolor:"rgba(0, 0, 0, 0)",
            font:{
              size:10
            }
          }
        },
        'config': {
          'modeBarButtonsToRemove': ['sendDataToCloud','hoverCompareCartesian', 'zoom2d', 'pan2d', 'select2d', 'lasso2d', 'zoomIn2d', 'zoomOut2d', 'autoScale2d', 'resetScale2d', 'hoverClosestCartesian'],
          'showLink': false
        },
        'totalAmount': totalAmount
      }
    } else {
      return {
        'data': [
          {
            type: 'bar',   
            x: labels,
            y: values,    
            text: text,
            "marker":{  
              "color": '#2b9ff6'
            },
            hoverinfo: 'text+x'
          }
        ],
        'layout': { 
          xaxis:{
            showticklabels:false,
          },                
          'height': height || 250,
          'width': width || (chartContainerRef? chartContainerRef.offsetWidth : 550),
          'autosize': false,
          'margin':{
            't':5,
            'b':35,
            'l':40, 
            'r':20
          }
        },
        'config': {
          'modeBarButtonsToRemove': ['sendDataToCloud','hoverCompareCartesian', 'zoom2d', 'pan2d', 'select2d', 'lasso2d', 'zoomIn2d', 'zoomOut2d', 'autoScale2d', 'resetScale2d', 'hoverClosestCartesian'],
          'showLink': false
        },
        'totalAmount': totalAmount
      }
    }
}

const sortDataByValue=(data, measureType, measure)=>{
  data.sort(function (a, b) {
    if (measureType=='projectCount'){
      return parseInt(b.projectCount) - parseInt(a.projectCount);
    } else {
      return parseInt(b.trxAmounts[measure.measure][measure.type]) - parseInt(a.trxAmounts[measure.measure][measure.type]);       
    }
  });
  return data.slice(0);
}


