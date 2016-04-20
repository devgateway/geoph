import React from 'react';
import { connect } from 'react-redux'
import InputRange from 'react-input-range'
import { setFilterRange, fetchFilterDataIfNeeded } from '../../actions/filters.js'


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
  		let minSelected = this.props.minSelected || (this.props.items? this.props.items[0].minValue : 0);
  		let maxSelected = this.props.maxSelected || (this.props.items? this.props.items[0].maxValue : 10);
  		let values = {'min': minSelected, 'max': maxSelected};
  		//
  		return (
	        <div className="range-filter-container">
        		<div className="">
	        		<span>Min: <b>{minSelected  + this.props.valueSymbol} </b></span>
		        </div>
	        	<div className="">
	        		<span>Max: <b>{maxSelected + this.props.valueSymbol}</b></span>
		        </div>
	        	<div className="range-filter">
	        		<InputRange
				        maxValue={this.props.items? this.props.items[0].maxValue : 10}
				        minValue={this.props.items? this.props.items[0].minValue : 0}
				        value={values}
				        onChange={this.handleValuesChange.bind(this)} />
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
