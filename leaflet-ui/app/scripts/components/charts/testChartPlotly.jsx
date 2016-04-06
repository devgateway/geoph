import React from 'react';
import ReactDOM from 'react-dom';
import Plotly  from 'react-plotlyjs';
import { connect } from 'react-redux'

var mockData = [
    {
        "projectCount": "47",
        "loan": "53711096",
        "code": "ADB",
        "pmc": "28301559",
        "name": "Asian Development Bank",
        "id": "1",
        "transactionCount": "55",
        "grant": "30654935"
    },
    {
        "projectCount": "38",
        "loan": "24501471",
        "code": "Germany",
        "pmc": "20027042",
        "name": "Germany",
        "id": "2",
        "transactionCount": "44",
        "grant": "32695546"
    },
    {
        "projectCount": "45",
        "loan": "33549503",
        "code": "GOJ/JICA",
        "pmc": "14477020",
        "name": "Japan International Cooperation Agency",
        "id": "3",
        "transactionCount": "50",
        "grant": "36975086"
    },
    {
        "projectCount": "34",
        "loan": "35095593",
        "code": "WB",
        "pmc": "14529427",
        "name": "World Bank",
        "id": "4",
        "transactionCount": "38",
        "grant": "21076584"
    },



    {
        "projectCount": "47",
        "loan": "53711096",
        "code": "ADB",
        "pmc": "28301559",
        "name": "Mock Bank",
        "id": "1",
        "transactionCount": "55",
        "grant": "30654935"
    },
    {
        "projectCount": "38",
        "loan": "24501471",
        "code": "Germany",
        "pmc": "20027042",
        "name": "Mock Germany",
        "id": "2",
        "transactionCount": "44",
        "grant": "32695546"
    },
    {
        "projectCount": "45",
        "loan": "33549503",
        "code": "GOJ/JICA",
        "pmc": "14477020",
        "name": "Mock International Cooperation Agency",
        "id": "3",
        "transactionCount": "50",
        "grant": "36975086"
    },
    {
        "projectCount": "34",
        "loan": "35095593",
        "code": "WB",
        "pmc": "14529427",
        "name": "Mock World Bank",
        "id": "4",
        "transactionCount": "38",
        "grant": "21076584"
    },



    {
        "projectCount": "47",
        "loan": "53711096",
        "code": "ADB",
        "pmc": "28301559",
        "name": "Mock Development Bank",
        "id": "1",
        "transactionCount": "55",
        "grant": "30654935"
    },
    {
        "projectCount": "38",
        "loan": "24501471",
        "code": "Germany",
        "pmc": "20027042",
        "name": "Mock Germany2",
        "id": "2",
        "transactionCount": "44",
        "grant": "32695546"
    },
    {
        "projectCount": "45",
        "loan": "33549503",
        "code": "GOJ/JICA",
        "pmc": "14477020",
        "name": "Mock International Cooperation Agency2",
        "id": "3",
        "transactionCount": "50",
        "grant": "36975086"
    },
    {
        "projectCount": "34",
        "loan": "35095593",
        "code": "WB",
        "pmc": "14529427",
        "name": "Mock World Bank2",
        "id": "4",
        "transactionCount": "38",
        "grant": "21076584"
    },


    {
        "projectCount": "47",
        "loan": "53711096",
        "code": "ADB",
        "pmc": "28301559",
        "name": "Mock Development Bank3",
        "id": "1",
        "transactionCount": "55",
        "grant": "30654935"
    },
    {
        "projectCount": "38",
        "loan": "24501471",
        "code": "Germany",
        "pmc": "20027042",
        "name": "Mock Germany3",
        "id": "2",
        "transactionCount": "44",
        "grant": "32695546"
    },
    {
        "projectCount": "45",
        "loan": "33549503",
        "code": "GOJ/JICA",
        "pmc": "14477020",
        "name": "Mock Japan International Cooperation Agency3",
        "id": "3",
        "transactionCount": "50",
        "grant": "36975086"
    },
    {
        "projectCount": "34",
        "loan": "35095593",
        "code": "WB",
        "pmc": "14529427",
        "name": "Mock World Bank3",
        "id": "4",
        "transactionCount": "38",
        "grant": "21076584"
    },


    {
        "projectCount": "47",
        "loan": "53711096",
        "code": "ADB",
        "pmc": "28301559",
        "name": "Asian Development Bank 4",
        "id": "1",
        "transactionCount": "55",
        "grant": "30654935"
    },
    {
        "projectCount": "38",
        "loan": "24501471",
        "code": "Germany",
        "pmc": "20027042",
        "name": "Germany 4",
        "id": "2",
        "transactionCount": "44",
        "grant": "32695546"
    },
    {
        "projectCount": "45",
        "loan": "33549503",
        "code": "GOJ/JICA",
        "pmc": "14477020",
        "name": "Japan International Cooperation Agency 4",
        "id": "3",
        "transactionCount": "50",
        "grant": "36975086"
    },
    {
        "projectCount": "34",
        "loan": "35095593",
        "code": "WB",
        "pmc": "14529427",
        "name": "World Bank 4",
        "id": "4",
        "transactionCount": "38",
        "grant": "21076584"
    },


    {
        "projectCount": "47",
        "loan": "53711096",
        "code": "ADB",
        "pmc": "28301559",
        "name": "Asian Development Bank 5",
        "id": "1",
        "transactionCount": "55",
        "grant": "30654935"
    },
    {
        "projectCount": "38",
        "loan": "24501471",
        "code": "Germany",
        "pmc": "20027042",
        "name": "Germany 5",
        "id": "2",
        "transactionCount": "44",
        "grant": "32695546"
    },
    {
        "projectCount": "45",
        "loan": "33549503",
        "code": "GOJ/JICA",
        "pmc": "14477020",
        "name": "Japan International Cooperation Agency 5",
        "id": "3",
        "transactionCount": "50",
        "grant": "36975086"
    },
    {
        "projectCount": "34",
        "loan": "35095593",
        "code": "WB",
        "pmc": "14529427",
        "name": "World Bank 5",
        "id": "4",
        "transactionCount": "38",
        "grant": "21076584"
    },

    {
        "projectCount": "47",
        "loan": "53711096",
        "code": "ADB",
        "pmc": "28301559",
        "name": "Asian Development Bank 6",
        "id": "1",
        "transactionCount": "55",
        "grant": "30654935"
    },
    {
        "projectCount": "38",
        "loan": "24501471",
        "code": "Germany",
        "pmc": "20027042",
        "name": "Germany 6",
        "id": "2",
        "transactionCount": "44",
        "grant": "32695546"
    },
    {
        "projectCount": "45",
        "loan": "33549503",
        "code": "GOJ/JICA",
        "pmc": "14477020",
        "name": "Japan International Cooperation Agency 6",
        "id": "3",
        "transactionCount": "50",
        "grant": "36975086"
    },
    {
        "projectCount": "34",
        "loan": "35095593",
        "code": "WB",
        "pmc": "14529427",
        "name": "World Bank 6",
        "id": "4",
        "transactionCount": "38",
        "grant": "21076584"
    }
];

var barColors = {
	'grant': 'rgb(0, 67, 88)',
	'loan': 'rgb(31, 138, 112)',
	'pcm': 'rgb(190, 219, 57)',
	'projects': 'rgb(0, 67, 88)'
};

var pieColors = ["#f6eff7","#d0d1e6","#a6bddb","#67a9cf","#3690c0","#02818a","#016450"];

class TestChart extends React.Component {

	constructor() {
	    super();
	    this.state = {};
	}

  	componentDidMount() {
	
	}

	parseDataForPiechart(graphData, type){
		let labels = [];
		let values = [];
		graphData.map((i) => {
			labels.push(i.name);
			values.push(i[type]);
		});
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
			        //'name': 'Pie chart example' 
		      	}
		    ],
			'layout': {         
		      	'height': 250,
				'width': 450,
				'autosize': true
			},
			'config': {
		    	'modeBarButtonsToRemove': ['sendDataToCloud','hoverCompareCartesian'],
				'showLink': false,
				'displayModeBar': false
		    }
		}
	}

	parseDataForBarchart(graphData, type){
		let data = [];
		let itemNames = [];
		let values = [];
		graphData.map((i) => {
			itemNames.push(i.name);
			values.push(i[type]);
		});
		let values2 = [];
		graphData.map((i) => {
			values2.push(i['loan']);
		});
		return {
			'data': [
				{
					type: 'bar',   
			        x: itemNames,  
			        y: values,    
			        name: type,
					"marker":{  
					 	"color": barColors[type]
					}
				},
				{
					type: 'bar',   
			        x: itemNames,  
			        y: values2,    
			        name: 'loan',
					"marker":{  
					 	"color": barColors['loan']
					}
				},
			],
			'layout': { 
				xaxis:{
					showticklabels:false,
				},                
		      	'height': 450,
				'width': 550,
				'autosize': true
		    },
			'config': {
		    	'modeBarButtonsToRemove': ['sendDataToCloud','hoverCompareCartesian'],
				'showLink': false,
				'displayModeBar': false
		    }
		}
	}

	render() {
		var barData = this.parseDataForBarchart(mockData, 'grant');
	    var pieData = this.parseDataForPiechart(mockData, 'grant');
	    return (
	    	<div>
	    		<div>
		      		<Plotly className="" data={barData.data} layout={barData.layout} config={barData.config}/>
		      	</div>
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
