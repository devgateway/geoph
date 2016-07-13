import translate from './translate.js';

export const parseDataForBarchart=(props)=>{
  
}

export const parseDataForPiechart=(props)=>{
    const {chartData, dimension, measure, width, height} = props;
    const {data, itemsToShow, measureType} = chartData;
  
    let labels = [];
    let values = [];
    let text = [];
    let totalAmount = 0;
    if (data.isArray){
      let others = 0;
      let dataSorted = sortDataByValue(data, measureType, measure);
      dataSorted.forEach((item, index) => {
        if (index < itemsToShow){
          if (measureType=='projectCount'){
            if (i.projectCount && parseInt(i.projectCount)>0){
              let label = this.capitalizeName(i[dimension].length>35? i[dimension].substr(0,32)+'...' : i[dimension]);
              labels.push(label);
              values.push(label);
              if(this.state.hiddenlabels.indexOf(label)==-1){
                totalAmount = totalAmount+parseInt(i[meas]);
              }
              text.push(translate('chartview.projectcount')+": " + i[meas]);
            }
          } else {
            if (i.trxAmounts[meas.measure][meas.type] && parseFloat(i.trxAmounts[meas.measure][meas.type])>0){
              let label = this.capitalizeName(i[dimension].length>35? i[dimension].substr(0,32)+'...' : i[dimension]);
              labels.push(label);
              values.push(i.trxAmounts[meas.measure][meas.type]);
              if(this.state.hiddenlabels.indexOf(label)==-1){
                totalAmount = totalAmount+parseInt(i.trxAmounts[meas.measure][meas.type]);
              }
              text.push(translate('header.settings.'+meas.type) + " " + translate('header.settings.'+meas.measure) + " PHP: " + formatValue(parseFloat(i.trxAmounts[meas.measure][meas.type])));
            }
          }
        } else {
          if (meas=='projectCount'){
            if (i[meas] && i[meas].length>0 && parseInt(i[meas])>0){
              others = others + parseInt(i[meas]);
            }
          } else {
            if (i.trxAmounts[meas.measure][meas.type] && parseFloat(i.trxAmounts[meas.measure][meas.type])>0){
              others = others + parseInt(i.trxAmounts[meas.measure][meas.type]);
            }
          }
        }
      });
      if (others>0){ 
        labels.push(translate('chartview.others'));
        values.push(others);
        if(this.state.hiddenlabels.indexOf(translate('chartview.others'))==-1){
          totalAmount = totalAmount+parseInt(others);
        }
        text.push(translate('header.settings.'+meas.type) + " " + translate('header.settings.'+meas.measure) + " PHP: " + formatValue(parseFloat(others)));
      }
    }
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
          //'direction':"clockwise",
          //'rotation': 270
          }
        ],
      'layout': {         
            'height': height || 250, 
        'width': width || (this.refs.chartContainer? this.refs.chartContainer.offsetWidth : 550),
        'margin':{
          't':5,
          'b':20,
          'l':10, 
          'r':10
        },
        //'showlegend':false,
        'hiddenlabels': this.state.hiddenlabels, 
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
}

const sortDataByValue(data, measureType, measure){
    data.sort(function (a, b) {
      if (measureType=='projectCount'){
        return parseInt(b.projectCount) - parseInt(a.projectCount);
      } else {
        return parseInt(b.trxAmounts[measure.measure][measure.type]) - parseInt(a.trxAmounts[measure.measure][measure.type]);       
      }
    });
    return data.slice(0);
  }


