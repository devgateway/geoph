import React from 'react';
import ReactDOM from 'react-dom';
import Plotly  from 'react-plotlyjs';
import { connect } from 'react-redux';
require("./charts.scss");

var pieColors = ["#f6eff7","#d0d1e6","#a6bddb","#67a9cf","#3690c0","#02818a","#016450"];

class ChartComponent extends React.Component {

	constructor() {
	    super();
	    this.state = {'chartType': 'bar'};
	}

  	componentDidMount() {
	
	}

	parseDataForPiechart(){
		const {chartData, dimension, measure, width, height} = this.props;
		let labels = [];
		let values = [];
		if (chartData){
			chartData.map((i) => {
				labels.push(i[dimension]);
				values.push(i[measure]);
			});
		}
		return {
			'data': [
	      		{
			        'type': 'pie',      
			        'labels': labels,  
			        'values': values, 
			        'marker':{
			        	'colors': pieColors,
			        	'line': {'width': 0.5,'color': 'rgb(102, 102, 102)'}
			        },
			        'hole': 0.45,
			        'textposition': 'none',
			        'domain':{
						x:[0,0.8],
						y:[0,1]
					},
			    }
		    ],
			'layout': {         
		      	'height': height || 450,
				'width': width || 550,
				'margin':{
					't':0,
					'b':200,
					'l':0
				},
				//'autosize': true,
				'legend':{
					x:0.7,
					y:1
				}
			},
			'config': {
		    	'modeBarButtonsToRemove': ['sendDataToCloud','hoverCompareCartesian'],
				'showLink': false,
				'displayModeBar': false
		    }
		}
	}

	parseDataForBarchart(){
		const {chartData, dimension, measure, width, height} = this.props;
		let itemNames = [];
		let values = [];
		if (chartData){
			chartData.map((i) => {
				itemNames.push(i[dimension]);
				values.push(i[measure]);
			});
		}
		debugger;
		return {
			'data': [
				{
					type: 'bar',   
			        x: itemNames,  
			        y: values,    
			        name: measure,
					"marker":{  
					 	"color": '#93C364'
					}
				}
			],
			'layout': { 
				xaxis:{
					showticklabels:false,
				},                
		      	'height': height || 450,
				'width': width || 550,
				'autosize': false,
				'margin':{
					't':0,
					'b':200
				}
		    },
			'config': {
		    	'modeBarButtonsToRemove': ['sendDataToCloud','hoverCompareCartesian'],
				'showLink': false,
				'displayModeBar': false
		    }
		}
	}

	setChartType(ev){
		this.setState({'chartType': ev.target.value})
	}

	render() {
		var chartData = this.props.chartType || (this.state.chartType=='bar'? this.parseDataForBarchart() : this.parseDataForPiechart());
	    return (
	    	<div>
	    		{!this.props.chartType?
	    			<div className="chart-type-selector">
	    				<div><input type="radio" 
							value='bar'
							checked={this.state.chartType ==='bar'} 
							onChange={this.setChartType.bind(this)} />Bar chart
                        </div>
                        <div><input type="radio"  
							value='pie' 
							checked={this.state.chartType === 'pie'} 
							onChange={this.setChartType.bind(this)} />Pie chart
                        </div>					  
					</div>
	    		: null}	    			    	
	    		<div>
		      		<Plotly className="" data={chartData.data} layout={chartData.layout} config={chartData.config}/>
		      	</div>
	      	</div>
	    );
  	}
}

export default connect()(ChartComponent);;
