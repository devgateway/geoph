import React from 'react';
import { connect } from 'react-redux';
import InputRange from 'react-input-range';
import { setFilterRange, fetchFilterDataIfNeeded } from '../../actions/filters';
import { formatValue, formatAndRoundValue, roundValue } from '../../util/transactionUtil';
import { getLogSliderValue, getLogSliderPosition } from '../../util/filterUtil';
import translate from '../../util/translate.js';
import Slider from 'rc-slider';
require('rc-slider/assets/index.css');

class FilterSlider extends React.Component {

	constructor() {
	    super();
	    this.state = {'errorMessage': ''};
	}

	componentDidMount() {
		this.props.onLoadFilterData(this.props.filterType);		
	}

	handleValuesChange(component, values) {
		this.setState({'values': values});
		this.props.onRangeChange({filterType: this.props.filterType, minSelected: values.min, maxSelected: values.max});		
	}

  	handleValuesChange2(values) {
  		const [min, max] = values;
  		const {valueMin, valueMax} = this.props;
  		let minSelected = roundValue(getLogSliderValue(valueMin, valueMax, min));
		let maxSelected = roundValue(getLogSliderValue(valueMin, valueMax, max));
		console.log('handleSelected: '+minSelected+' - '+maxSelected);
  		this.props.onRangeChange({filterType: this.props.filterType, minSelected: minSelected, maxSelected: maxSelected});		
	}

	percentFormatter(v) {
	  return v + ' %';
	}

	currencyFormatter(v) {
	  return formatValue(v) + ' PHP';
	}

	logslider(position) {
		// position will be between 0 and 100
		var minp = 0;
		var maxp = 100;

		// The result should be between min and max values
		var minv = Math.log(this.props.valueMin+1);
		var maxv = Math.log(this.props.valueMax);

		// calculate adjustment factor
		var scale = (maxv-minv) / (maxp-minp);

		return Math.exp(minv + scale*(position-minp));
	}
	

	getMarks(){
		const {valueMin, valueMax} = this.props;
		return {
			0: formatAndRoundValue(getLogSliderValue(valueMin, valueMax, 0)),
			10: formatAndRoundValue(getLogSliderValue(valueMin, valueMax, 10)),
			20: formatAndRoundValue(getLogSliderValue(valueMin, valueMax, 20)),
			30: formatAndRoundValue(getLogSliderValue(valueMin, valueMax, 30)),
			40: formatAndRoundValue(getLogSliderValue(valueMin, valueMax, 40)),
			50: formatAndRoundValue(getLogSliderValue(valueMin, valueMax, 50)),
			60: formatAndRoundValue(getLogSliderValue(valueMin, valueMax, 60)),
			70: formatAndRoundValue(getLogSliderValue(valueMin, valueMax, 70)),
			80: formatAndRoundValue(getLogSliderValue(valueMin, valueMax, 80)),
			90: formatAndRoundValue(getLogSliderValue(valueMin, valueMax, 90)),
			100: formatAndRoundValue(getLogSliderValue(valueMin, valueMax, 100)),			
		}
	}

  	render() {
  		let minSelected = this.props.minSelected!=undefined? this.props.minSelected : this.props.valueMin;
  		let maxSelected = this.props.maxSelected!=undefined? this.props.maxSelected : this.props.valueMax;
  		let values = {'min': minSelected, 'max': maxSelected};
  		const {valueMin, valueMax} = this.props;
  		debugger;
		console.log('position: '+getLogSliderPosition(valueMin, valueMax, minSelected)+' - '+getLogSliderPosition(valueMin, valueMax, maxSelected));
  		return (
	        <div className="range-filter-container">
        		<div className="range-filter">
	        		{/*<InputRange
				        maxValue={this.props.valueMax} 
				        minValue={this.props.valueMin}
				        value={values}
				        onChange={this.handleValuesChange.bind(this)} />*/}
				    <Slider 
				    	range 
				    	value={[getLogSliderPosition(valueMin, valueMax, minSelected), getLogSliderPosition(valueMin, valueMax, maxSelected)]} 
				        step={null}
				        marks={this.getMarks()}
				    	onChange={this.handleValuesChange2.bind(this)} />
		        </div>	
		        <div className="range-filter-selection">		     
			        <div className="">
		        		<span>{translate('filters.physical.minimum')}: <b>{formatAndRoundValue(minSelected) + this.props.valueSymbol} </b></span>
			        </div>
		        	<div className="">
		        		<span>{translate('filters.physical.maximum')}: <b>{formatAndRoundValue(maxSelected) + this.props.valueSymbol}</b></span>
			        </div>
	        	</div>
	        	{this.state.errorMessage.length>0?
					<div className="error-message">
		        		<b>Error: </b>
		        		{this.state.errorMessage}
			        </div>
		        : null}   
		    </div>   
	    );
  	}
}


const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onLoadFilterData: (type) => {
      dispatch(fetchFilterDataIfNeeded(type));
    },

    onRangeChange: (filterRange) => {
      dispatch(setFilterRange(filterRange));
    }
  }
}

export default connect(null,mapDispatchToProps)(FilterSlider);
