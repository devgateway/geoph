import React from 'react';
import { connect } from 'react-redux';
import InputRange from 'react-input-range';
import { setFilterRange, fetchFilterDataIfNeeded } from '../../actions/filters';
import { formatValue } from '../../util/transactionUtil';
import translate from '../../util/translate.js';


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

  	render() {
  		let minSelected = this.props.minSelected || this.props.valueMin;
  		let maxSelected = this.props.maxSelected || this.props.valueMax;
  		let values = {'min': minSelected, 'max': maxSelected};

  		return (
	        <div className="range-filter-container">
        		<div className="range-filter">
	        		<InputRange
				        maxValue={this.props.valueMax}
				        minValue={this.props.valueMin}
				        value={values}
				        onChange={this.handleValuesChange.bind(this)} />
		        </div>	
		        <div className="range-filter-selection">		     
			        <div className="">
		        		<span>{translate('filters.physical.minimum')}: <b>{formatValue(minSelected) + this.props.valueSymbol} </b></span>
			        </div>
		        	<div className="">
		        		<span>{translate('filters.physical.maximum')}: <b>{formatValue(maxSelected) + this.props.valueSymbol}</b></span>
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
