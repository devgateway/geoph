import React from 'react';
import { connect } from 'react-redux';
import InputRange from 'react-input-range';
import { setFilterRange, fetchFilterDataIfNeeded } from '../../actions/filters';
import { formatValue, formatAndRoundValue, roundValue } from '../../util/transactionUtil';
import { getLogSliderValue, getLogSliderPosition } from '../../util/filterUtil';
import translate from '../../util/translate.js';
import Slider from 'rc-slider';
require('rc-slider/assets/index.css');

class FilterSliderWithMarks extends React.Component {

	constructor() {
	    super();
	    this.state = {'errorMessage': ''};
	}

	componentDidMount() {
		this.props.onLoadFilterData(this.props.filterType);		
	}

	handleMarksChange(values) {
  		const [min, max] = values;
  		const {valueMin, valueMax} = this.props;
  		let minSelected = roundValue(getLogSliderValue(valueMin, valueMax, min));
		let maxSelected = roundValue(getLogSliderValue(valueMin, valueMax, max));
		this.props.onRangeChange({filterType: this.props.filterType, minSelected: minSelected, maxSelected: maxSelected});		
	}
	
	handleValuesChange(values) {
  		const [min, max] = values;
  		this.props.onRangeChange({filterType: this.props.filterType, minSelected: min, maxSelected: max});		
	}

	percentFormatter(v) {
	  return v + ' %';
	}

	currencyFormatter(v) {
	  return '₱ '+formatValue(v);
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
	

	getLogMarks(){
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

  	getMarks(){
		const {valueMin, valueMax} = this.props;
		let ret = {};
		ret[valueMin] = valueMin;
		ret[valueMax] = valueMax;
		return ret
	}

  	render() {
  		let minSelected = this.props.minSelected!=undefined? this.props.minSelected : this.props.valueMin;
  		let maxSelected = this.props.maxSelected!=undefined? this.props.maxSelected : this.props.valueMax;
  		let values = {'min': minSelected, 'max': maxSelected};
  		const {valueMin, valueMax} = this.props;
  		return (
	        <div className="range-filter-container">
        		<div className="range-filter">
        			{this.props.logMarks?
	        		<Slider 
				    	range 
				    	value={[getLogSliderPosition(valueMin, valueMax, minSelected), getLogSliderPosition(valueMin, valueMax, maxSelected)]} 
				        step={null}
				        marks={this.getLogMarks()}
				    	onChange={this.handleMarksChange.bind(this)} />
        			:
        			<Slider 
				    	range 
				    	value={[minSelected, maxSelected]} 
				        min={valueMin}
				        max={valueMax}
				        step={1}
				    	marks={this.getMarks()}
				    	onChange={this.handleValuesChange.bind(this)} />
        			}
        		</div>	
		        <div className="range-filter-selection">		     
			        <div className="">
		        		<span>{translate('filters.physical.minimum')}: <b>{(this.props.valueSymbolPre || "") + formatAndRoundValue(minSelected) + (this.props.valueSymbolPost || "")} </b></span>
			        </div>
		        	<div className="">
		        		<span>{translate('filters.physical.maximum')}: <b>{(this.props.valueSymbolPre || "") + formatAndRoundValue(maxSelected) + (this.props.valueSymbolPost || "")}</b></span>
			        </div>
	        	</div>
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

export default connect(null,mapDispatchToProps)(FilterSliderWithMarks);
