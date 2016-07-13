import React from 'react';
import ReactDOM from 'react-dom';
import Plotly  from 'react-plotlyjs';
import { connect } from 'react-redux';
import { ButtonGroup, Button, Label } from 'react-bootstrap';
import * as Constants from '../../constants/constants';
import {formatValue} from '../../util/transactionUtil';
import translate from '../../util/translate.js';
require("./charts.scss");

var pieColors = ["#f6eff7","#d0d1e6","#a6bddb","#67a9cf","#3690c0","#02818a","#016450"];

export default class ChartComponent extends React.Component {

	constructor() {
	    super();
	    this.state = {'chartType': 'bar', 'measType': 'funding', 'hiddenlabels': []};
	}

  	componentDidMount() {
		
	}

	parseDataForPiechart(){
		const {chartData, dimension, measure, width, height} = this.props;
		let meas = measure && this.props.chartData.measureType=='funding'? measure : 'projectCount';
		let labels = [];
		let values = [];
		let text = [];
		let totalAmount = 0;
		if (chartData.data && chartData.data.map){
			let others = 0;
			this.sortDataByValue(chartData.data, meas);
			chartData.data.map((i, idx) => {
				if (idx<this.props.chartData.itemsToShow){
					if (meas=='projectCount'){
						if (i[meas] && parseInt(i[meas])>0){
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

	parseDataForBarchart(){
		const {chartData, dimension, measure, width, height} = this.props;
		let meas = measure && this.props.chartData.measureType=='funding'? measure : 'projectCount';
		let itemNames = [];
		let values = [];
		let text = [];
		let totalAmount = 0;
		if (chartData.data  && chartData.data.map){
			let others = 0;
			this.sortDataByValue(chartData.data, meas);
			chartData.data.map((i, idx) => {
				if (idx<this.props.chartData.itemsToShow){
					if (meas=='projectCount'){
						if (i[meas] && parseInt(i[meas])>0){
							let label = this.capitalizeName(i[dimension].length>35? i[dimension].substr(0,32)+'...' : i[dimension]);
							itemNames.push(label);
							values.push(i[meas]);
							if(this.state.hiddenlabels.indexOf(label)==-1){
								totalAmount = totalAmount+parseInt(i[meas]);
							}
							text.push(translate('chartview.projectcount')+": " + i[meas]);
						}
					} else {
						if (i.trxAmounts[meas.measure][meas.type] && parseFloat(i.trxAmounts[meas.measure][meas.type])>0){
							let label = this.capitalizeName(i[dimension].length>35? i[dimension].substr(0,32)+'...' : i[dimension]);
							itemNames.push(label);
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
				itemNames.push(translate('chartview.others'));
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
					type: 'bar',   
			        x: itemNames,
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
		    	'modeBarButtonsToRemove': ['sendDataToCloud','hoverCompareCartesian', 'zoom2d', 'pan2d', 'select2d', 'lasso2d', 'zoomIn2d', 'zoomOut2d', 'autoScale2d', 'resetScale2d', 'hoverClosestCartesian'],
				'showLink': false
		    },
		    'totalAmount': totalAmount
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

	setChartType(type){
		this.props.onChangeType(this.props.chart, type);
	}

	setMeasType(type){
		this.props.onChangeMeasure(this.props.chart, type);
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
				return parseInt(b.trxAmounts[measure.measure][measure.type]) - parseInt(a.trxAmounts[measure.measure][measure.type]);				
			}
		});
		return data.slice(0);
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

	onChartClick(evt){
		if (evt.currentTarget && evt.currentTarget.layout.hiddenlabels.length != this.state.hiddenlabels.length){
			this.setState({'hiddenlabels': evt.currentTarget.layout.hiddenlabels});
		}
	}
  
  	getTotalFormatted(value){
  		if (this.props.chartData.measureType=='projectCount'){
  			return formatValue(value) +" "+translate('chartview.projects');
  		} else {
  			return " PHP "+formatValue(value);
  		}
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
	    		{this.props.title?
		    		<div className="chart-title">
		    			<div className="chart-title-icon"></div>
		    			<div className="chart-title-text">
		    				<div className="title">
		    					{this.props.title || ""}
		    				</div>
		    				<div className="subtitle"> 
		    					{measure? translate('header.settings.'+measure.type) + " " + translate('header.settings.'+measure.measure) : ""}
		    				</div>
		    			</div>		    			
		    		</div>
		    	: <div className="chart-title"/>}
	    		{this.props.onChangeItemToShow?
	    			<div className="chart-items-selector">
	    				<Button disabled={chartData.itemsToShow > Constants.CHART_ITEMS_STEP_AMOUNT? false : true} onClick={this.setItemsToShow.bind(this, "less")}>
	    					<span>{"<"}</span><span className="less-items">{translate('chartview.less')}</span>
	    				</Button>
	    				<Button disabled={chartData.data && (chartData.itemsToShow < chartData.data.length)? false : true} onClick={this.setItemsToShow.bind(this, "more")}>
	    					<span className="more-items">{translate('chartview.more')}</span><span>{">"}</span>
	    				</Button>
	    			</div>
	    		: null}	
	    		{this.props.onChangeType?
	    			<div className="chart-type-selector">
	    				<div className="toggle-button-pair">
						    <div className={chartData.chartType ==='bar'? "active" : ""} onClick={this.setChartType.bind(this, 'bar')} title={translate('chartview.barchart')}>
						    	<div className={chartData.chartType ==='bar'? "chart-bar-icon" : "chart-bar-icon-disabled"}></div>
						    </div>
						    <div className={chartData.chartType ==='pie'? "active" : ""} onClick={this.setChartType.bind(this, 'pie')} title={translate('chartview.piechart')}>
						    	<div className={chartData.chartType ==='pie'? "chart-pie-icon" : "chart-pie-icon-disabled"}></div>
						    </div>
						</div>	    			    						  
					</div>
	    		: null}  
	    		{this.props.onChangeMeasure?
	    			<div className="chart-measure-selector">
	    				<div className="toggle-button-pair">
						    <div className={chartData.measureType ==='funding'? "active" : ""} onClick={this.setMeasType.bind(this, 'funding')} title={translate('chartview.funding')}>
						    	<div className={chartData.measureType ==='funding'? "chart-funding-icon" : "chart-funding-icon-disabled"}></div>
						    </div>
						    <div className={chartData.measureType ==='projectCount'? "active" : ""} onClick={this.setMeasType.bind(this, 'projectCount')} title={translate('chartview.projectcount')}>
						    	<div className={chartData.measureType ==='projectCount'? "chart-projects-icon" : "chart-projects-icon-disabled"}></div>
						    </div>
						</div>		  
					</div>
	    		: null}	
	    		{this.props.showTotalHeader?
	    			<div className="total-funding-chart">{translate('infowindow.tab.totalamount')}: <div>{this.getTotalFormatted(chartInfo.totalAmount)}</div></div>
                : null}	
	    		{!this.hasValuesOK(chartInfo)?
	    			<div className="no-data">
			    		{translate('chartview.nodata')}
			    	</div>
	    		:
	    			<div>
			      		<Plotly onClick={this.onChartClick.bind(this)} className="" data={chartInfo.data} layout={chartInfo.layout} config={chartInfo.config}/>
			      	</div>	
	    		}
	    		    			  			   	    			      
	      	</div>
	    );
  	}
}

