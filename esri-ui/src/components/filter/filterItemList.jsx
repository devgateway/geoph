import React from 'react';
//import FilterList from 'app/components/filter/list';
import { connect } from 'react-redux'
import { selectAllFilterList, selectFilterItem, fetchFilterListIfNeeded } from 'app/actions/filters'

class FilterList extends React.Component {

  	render() {
  		if (this.props.expanded){
	    	return (
		        <div>
		        	<ul style={{left: '25', listStyleType: 'none'}}>	        	
		        	{this.props.items.map((item) => {
				        return <li key={item.id}> 
				        	<ItemConnected filterType={this.props.filterType} {...item} />
				        </li>
				    })}
				    </ul>
			    </div>
	      	);
	    } else {
	    	return null;
	    }
  	}
}

class FilterItem extends React.Component {

	constructor() {
	    super();
	    this.state = {'expanded': true, 'partialSelected': false};
	}

	componentDidMount() {
		if (this.props.loadList){
			this.props.onLoadFilterList(this.props.filterType);
		}
	}

	toggleExpanded(){
		this.setState({'expanded': !this.state.expanded});
	}

	handleChange() {
		const {id, filterType, selected} = this.props;
		if (this.props.id){
			this.props.onItemChange({id, filterType, selected: !selected});
		} else {
			this.props.onChangeAllFilterList({filterType, selected: !selected});
		}
	}

  	render() {
  		let selectionClass = "selectable " + (this.props.selected? "selected" : this.props.selectedCounter>0? "half-fill" : "");
    	return (
	        <div>
	        	<div className="filterItemInfo">
	        		<div className={selectionClass} onClick={this.handleChange.bind(this)} />
		        	<div className="toggle-nav" onClick={this.handleChange.bind(this)}>
		        		{this.props.name || this.props.description}
		        	</div>
		        	{this.props.items && this.props.items.length>0? 
		        		<div>
			        		<div className="counter">
				        		({this.props.selectedCounter}/{this.props.totalCounter})
				        	</div>
				        	<div className={this.state.expanded? "expanded open" : "expanded closed"} onClick={this.toggleExpanded.bind(this)}>
				        		{this.state.expanded? "-" : "+"}
				        	</div>
				        </div>
		        	: null}	 
		        </div>	
		        <div>
	        		{this.props.items && this.props.items.length>0? 
		        		<FilterList expanded={this.state.expanded} {...this.props} />
				    : null}	 
		        </div>       
	        </div>
      	);
  	}
}


const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onLoadFilterList: (type) => {
      dispatch(fetchFilterListIfNeeded(type));
    },

    onItemChange: (filterItem) => {
      dispatch(selectFilterItem(filterItem));
    },

    onChangeAllFilterList: (filterItem) => {
      dispatch(selectAllFilterList(filterItem));
    }
  }
}

const ItemConnected = connect(null,mapDispatchToProps)(FilterItem);
export default ItemConnected;
