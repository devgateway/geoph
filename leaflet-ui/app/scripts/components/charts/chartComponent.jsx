import React from 'react';
import ReactDOM from 'react-dom';
import Plotly  from 'react-plotlyjs';
import { connect } from 'react-redux';
require("./charts.scss");

var pieColors = ["#f6eff7","#d0d1e6","#a6bddb","#67a9cf","#3690c0","#02818a","#016450"];

export default class ChartComponent extends React.Component {

	constructor() {
	    super();
	    this.state = {'chartType': 'bar', 'measType': 'funding'};
	}

  	componentDidMount() {
		
	}

	parseDataForPiechart(){
		const {chartData, dimension, measure, width, height} = this.props;
		let meas = measure && this.state.measType=='funding'? measure : 'projectCount';
		let labels = [];
		let values = [];
		if (chartData && chartData.map){
			chartData.map((i) => {
				if (meas=='projectCount'){
					if (i[meas] && i[meas].length>0 && parseInt(i[meas])>0){
						let label = i[dimension].length>35? i[dimension].substr(0,32)+'...' : i[dimension];
						labels.push(this.capitalizeName(label));
						values.push(i[meas]);
					}
				} else {
					if (i[meas.measure][meas.type] && i[meas.measure][meas.type].length>0 && parseFloat(i[meas.measure][meas.type])>0){
						let label = i[dimension].length>35? i[dimension].substr(0,32)+'...' : i[dimension];
						labels.push(this.capitalizeName(label));
						values.push(i[meas.measure][meas.type]);
					}
				}
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
						x:[0.25,1],
						y:[0,1]
					},
			    }
		    ],
			'layout': {         
		      	'height': height || 250,
				'width': width || 550,
				'margin':{
					't':20,
					'b':20,
					'l':0, 
					'r':10
				},
				//'autosize': true,
				'legend':{
					x:-0.5,
					y:1,
					bgcolor:"rgba(0, 0, 0, 0)",
					font:{
						size:10
					}
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
		let meas = measure && this.state.measType=='funding'? measure : 'projectCount';
		let itemNames = [];
		let values = [];
		if (chartData  && chartData.map){
			chartData.map((i) => {
				if (meas=='projectCount'){
					if (i[meas] && i[meas].length>0 && parseInt(i[meas])>0){
						itemNames.push(this.capitalizeName(i[dimension]));
						values.push(i[meas]);
					}
				} else {
					if (i[meas.measure][meas.type] && i[meas.measure][meas.type].length>0 && parseFloat(i[meas.measure][meas.type])>0){
						itemNames.push(this.capitalizeName(i[dimension]));
						values.push(i[meas.measure][meas.type]);
					}
				}
			});
		}
		return {
			'data': [
				{
					type: 'bar',   
			        x: itemNames,  
			        y: values,    
			        //name: meas,
					"marker":{  
					 	"color": '#93C364'
					}
				}
			],
			'layout': { 
				xaxis:{
					showticklabels:false,
				},                
		      	'height': height || 250,
				'width': width || 550,
				'autosize': false,
				'margin':{
					't':20,
					'b':35
				}
		    },
			'config': {
		    	'modeBarButtonsToRemove': ['sendDataToCloud','hoverCompareCartesian'],
				'showLink': false,
				'displayModeBar': false
		    }
		}
	}

	capitalizeName(str) {
		if (!str || str.length==0){
			return "";
		}
		return str[0].toUpperCase() + str.replace(/ ([a-z])/g, function(a, b) {
			return ' ' + b.toUpperCase();
		}).slice(1);
	}

	setChartType(ev){
		this.setState({'chartType': ev.target.value});
	}

	setMeasType(ev){
		this.setState({'measType': ev.target.value});
	}

	hasValuesOK(chartData){
		if (chartData.data[0].type == 'bar'){
			return chartData.data[0].x && chartData.data[0].x.length>0
		} else {
			return chartData.data[0].values && chartData.data[0].values.length>0
		}
	}

	render() {
		var chartData;
		if (this.props.chartType){
			chartData = this.props.chartType=='bar'? this.parseDataForBarchart() : this.parseDataForPiechart();
		} else {
			chartData = this.state.chartType=='bar'? this.parseDataForBarchart() : this.parseDataForPiechart();
		}
		return (
	    	<div className="chart">
	    		<div className="chart-title">
	    			{this.props.title || ""}
	    		</div>
	    		{!this.props.chartType?
	    			<div className="chart-type-selector">
	    				<div className="chart-type-option"><input type="radio" 
							value='bar'
							checked={this.state.chartType ==='bar'} 
							onChange={this.setChartType.bind(this)} />Bar chart
                        </div>
                        <div className="chart-type-option"><input type="radio"  
							value='pie' 
							checked={this.state.chartType === 'pie'} 
							onChange={this.setChartType.bind(this)} />Pie chart
                        </div>					  
					</div>
	    		: null}  
	    		{this.props.showMeasureSelector?
	    		<div className="chart-measure-selector">
	    				<div className="chart-type-option"><input type="radio" 
							value='funding'
							checked={this.state.measType ==='funding'} 
							onChange={this.setMeasType.bind(this)} />Funding
                        </div>
                        <div className="chart-type-option"><input type="radio"  
							value='projectCount' 
							checked={this.state.measType === 'projectCount'} 
							onChange={this.setMeasType.bind(this)} />Project Count
                        </div>					  
					</div>
	    		: null}	
	    		{!this.hasValuesOK(chartData)?
	    			<div className="no-data">
			    		<h4>No data to show</h4>
			    	</div>
	    		:
	    			<div>
			      		<Plotly className="" data={chartData.data} layout={chartData.layout} config={chartData.config}/>
			      	</div>	
	    		}	    			  			   	    			      
	      	</div>
	    );
  	}
}

