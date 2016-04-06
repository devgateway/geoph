import React from 'react';
import ReactDOM from 'react-dom';
import { Chart } from 'react-d3-core';
import { PieTooltip, LineTooltip } from 'react-d3-tooltip';
import { connect } from 'react-redux'


var lineChartData = [
			{"name":"Darron Weissnat IV","BMI":20.72,"age":39,"birthday":"2005-01-03T00:00:00.000Z","city":"East Russel","married":false,"index":0}
			,
			{"name":"Pablo Ondricka","BMI":19.32,"age":38,"birthday":"1974-05-13T00:00:00.000Z","city":"Lake Edytheville","married":false,"index":1}
			,
			{"name":"Mr. Stella Kiehn Jr.","BMI":16.8,"age":34,"birthday":"2003-07-25T00:00:00.000Z","city":"Lake Veronicaburgh","married":false,"index":2}
			,
			{"name":"Lavon Hilll I","BMI":20.57,"age":12,"birthday":"1994-10-26T00:00:00.000Z","city":"Annatown","married":true,"index":3}
			,
			{"name":"Clovis Pagac","BMI":24.28,"age":26,"birthday":"1995-11-10T00:00:00.000Z","city":"South Eldredtown","married":false,"index":4}
			,
			{"name":"Gaylord Paucek","BMI":24.41,"age":30,"birthday":"1975-06-12T00:00:00.000Z","city":"Koeppchester","married":true,"index":5}
			,
			{"name":"Ashlynn Kuhn MD","BMI":23.77,"age":32,"birthday":"1985-08-09T00:00:00.000Z","city":"West Josiemouth","married":false,"index":6}
			,
			{"name":"Fern Schmeler IV","BMI":27.33,"age":26,"birthday":"2005-02-10T00:00:00.000Z","city":"West Abigaleside","married":true,"index":7}
			,
			{"name":"Enid Weber","BMI":18.72,"age":17,"birthday":"1998-11-30T00:00:00.000Z","city":"Zackton","married":true,"index":8}
			,
			{"name":"Leatha O'Hara","BMI":17.68,"age":42,"birthday":"2010-10-17T00:00:00.000Z","city":"Lake Matilda","married":false,"index":9}
			,
			{"name":"Korbin Steuber","BMI":16.35,"age":39,"birthday":"1975-06-30T00:00:00.000Z","city":"East Armandofort","married":true,"index":10}
		];

var pieChartData = [
			{'age': '<5', 'population':2704659}
			,
			{'age': '5-13', 'population':4499890}
			,
			{'age': '14-17', 'population':2159981}
			,
			{'age': '18-24', 'population':3853788}
			,
			{'age': '25-44', 'population':14106543}
			,
			{'age': '45-64', 'population':8819342}
			,
			{'age': '≥65', 'population':612463}
		];

var pieChartSeries = [
			{"field": "<5", "name": "less than 5"},
			{"field": "5-13", "name": "5 to 13"},
			{"field": "14-17", "name": "14 to 17"},
			{"field": "18-24", "name": "18 to 24"},
			{"field": "25-44", "name": "25 to 44"},
			{"field": "45-64", "name": "45 to 64"}
		];		


class TestChart extends React.Component {

	constructor() {
	    super();
	    this.state = {};
	}

  	componentDidMount() {
	
	}

	render() {
		var width = 300,
		    height = 300,
		    margins = {left: 100, right: 100, top: 50, bottom: 50},
		    title = "Pie Chart With Tooltip",
		    // value accessor
		    value = function(d) {
		      return d.population;
		    },
		    // name accessor
		    name = function(d) {
		      return d.age;
		    }
  		return (
    	<div>
	        <PieTooltip
	        	margins= {margins}
				title= {title}
				data= {pieChartData}
				width= {width}
				height= {height}
				chartSeries= {pieChartSeries}
				value = {value}
				name = {name} />
		</div>
      	);
  	}
}

const mapDispatchToProps = (dispatch, ownProps) => {
 return {
    onFilterApply: () => {
      dispatch(applyFilter());
    }
  }
}

export default connect(null,mapDispatchToProps)(TestChart);;
