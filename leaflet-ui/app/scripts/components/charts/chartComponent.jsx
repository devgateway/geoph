import React from 'react';
import ReactDOM from 'react-dom';
import Plotly  from 'react-plotlyjs';
import { connect } from 'react-redux';
import { Button, Label } from 'react-bootstrap';
import * as Constants from '../../constants/constants';

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
		let meas = measure && this.props.chartData.measureType=='funding'? measure : 'projectCount';
		let labels = [];
		let values = [];
		if (chartData.data && chartData.data.map){
			let others = 0;
			this.sortDataByValue(chartData.data, meas);
			chartData.data.map((i, idx) => {
				if (idx<this.props.chartData.itemsToShow){
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
				} else {
					if (meas=='projectCount'){
						if (i[meas] && i[meas].length>0 && parseInt(i[meas])>0){
							others = others + parseInt(i[meas]);
						}
					} else {
						if (i[meas.measure][meas.type] && i[meas.measure][meas.type].length>0 && parseFloat(i[meas.measure][meas.type])>0){
							others = others + parseInt(i[meas.measure][meas.type]);
						}
					}
				}
			});
			if (others>0){
				labels.push("Others");
				values.push(others);
			}
		}
		return {
			'data': [
	      		{
			        'type': 'pie',      
			        'labels': labels,  
			        'values': values, 
			        'marker':{
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
				'width': width || (this.refs.chartContainer? this.refs.chartContainer.offsetWidth : 550),
				'margin':{
					't':5,
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
		let meas = measure && this.props.chartData.measureType=='funding'? measure : 'projectCount';
		let itemNames = [];
		let values = [];
		if (chartData.data  && chartData.data.map){
			let others = 0;
			this.sortDataByValue(chartData.data, meas);
			chartData.data.map((i, idx) => {
				if (idx<this.props.chartData.itemsToShow){
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
				} else {
					if (meas=='projectCount'){
						if (i[meas] && i[meas].length>0 && parseInt(i[meas])>0){
							others = others + parseInt(i[meas]);
						}
					} else {
						if (i[meas.measure][meas.type] && i[meas.measure][meas.type].length>0 && parseFloat(i[meas.measure][meas.type])>0){
							others = others + parseInt(i[meas.measure][meas.type]);
						}
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
				'width': width || (this.refs.chartContainer? this.refs.chartContainer.offsetWidth : 550),
				'autosize': false,
				'margin':{
					't':5,
					'b':35,
					'l':40, 
					'r':20
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
		//this.setState({'chartType': ev.target.value});
		this.props.onChangeType(this.props.chart, ev.target.value);
	}

	setMeasType(ev){
		//this.setState({'measType': ev.target.value});
		this.props.onChangeMeasure(this.props.chart, ev.target.value);
	}

	setItemsToShow(value){ 
		let val = this.props.chartData.itemsToShow;
		if (value=="less"){
			if (this.props.chartData.itemsToShow > Constants.CHART_ITEMS_STEP_AMOUNT){
				val = this.props.chartData.itemsToShow - Constants.CHART_ITEMS_STEP_AMOUNT;
			}	
		} else {
			if (this.props.chartData.itemsToShow < this.props.chartData.data.length){
				val = this.props.chartData.itemsToShow + Constants.CHART_ITEMS_STEP_AMOUNT;
			}
		}
		this.props.onChangeItemToShow(this.props.chart, val);
	}

	hasValuesOK(chartData){
		if (chartData.data[0].type == 'bar'){
			return chartData.data[0].x && chartData.data[0].x.length>0
		} else {
			return chartData.data[0].values && chartData.data[0].values.length>0
		}
	}

	sortDataByValue(data, measure){
		data.sort(function (a, b) {
			if (measure=='projectCount'){
				return parseInt(b[measure]) - parseInt(a[measure]);
			} else {
				return parseInt(b[measure.measure][measure.type]) - parseInt(a[measure.measure][measure.type]);				
			}
		});
	}

	handleResize(e) {
		this.forceUpdate();
	}

	componentDidMount() {
		window.addEventListener('resize', this.handleResize.bind(this));
	}

	componentWillUnmount() {
		window.removeEventListener('resize', this.handleResize.bind(this));
	}
  
	render() {
		let chartData = this.props.chartData;
		let measure = this.props.measure;
		let chartInfo;
		if (this.props.chartType){
			chartInfo = this.props.chartType=='bar'? this.parseDataForBarchart() : this.parseDataForPiechart();
		} else {
			chartInfo = chartData.chartType=='bar'? this.parseDataForBarchart() : this.parseDataForPiechart();
		}
		return (
	    	<div className="chart" ref="chartContainer">
	    		<div className="chart-title">
	    			{this.props.title || ""}
	    		</div>
	    		{this.props.onChangeType?
	    			<div className="chart-type-selector">
	    				<div className="chart-type-option"><input type="radio" 
							value='bar'
							checked={chartData.chartType ==='bar'} 
							onChange={this.setChartType.bind(this)} />Bar chart
                        </div>
                        <div className="chart-type-option"><input type="radio"  
							value='pie' 
							checked={chartData.chartType === 'pie'} 
							onChange={this.setChartType.bind(this)} />Pie chart
                        </div>					  
					</div>
	    		: null}  
	    		{this.props.onChangeItemToShow?
	    			<div className="chart-items-selector">
	    				<Label className={chartData.itemsToShow > Constants.CHART_ITEMS_STEP_AMOUNT? "item-qty-selector" : "item-qty-selector-disabled"}
	    					bsStyle="info" onClick={this.setItemsToShow.bind(this, "less")}>–{Constants.CHART_ITEMS_STEP_AMOUNT}</Label>
	    				<Label className={chartData.data && (chartData.itemsToShow < chartData.data.length)? "item-qty-selector" : "item-qty-selector-disabled"} 
	    					bsStyle="info" onClick={this.setItemsToShow.bind(this, "more")}>+{Constants.CHART_ITEMS_STEP_AMOUNT}</Label>
	    			</div>
	    		: null}	
	    		{this.props.onChangeMeasure?
	    			<div className="chart-measure-selector">
	    				<div className="chart-type-option"><input type="radio" 
							value='funding'
							checked={chartData.measureType ==='funding'} 
							onChange={this.setMeasType.bind(this)} />Funding
                        </div>
                        <div className="chart-type-option"><input type="radio"  
							value='projectCount' 
							checked={chartData.measureType === 'projectCount'} 
							onChange={this.setMeasType.bind(this)} />Project Count
                        </div>					  
					</div>
	    		: null}	
	    		{!this.hasValuesOK(chartInfo)?
	    			<div className="no-data">
			    		NO DATA AVALABLE
			    	</div>
	    		:
	    			<div>
			      		<Plotly className="" data={chartInfo.data} layout={chartInfo.layout} config={chartInfo.config}/>
			      	</div>	
	    		}
	    		    			  			   	    			      
	      	</div>
	    );
  	}
}

