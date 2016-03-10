import React from 'react';
import FilterItem from './FilterItem';

export default class FilterList extends React.Component {

	componentDidMount() {
		this.props.onLoadFilterList(this.props.type);
	}

	handleItemSelectionChange(item) {
	    this.props.onSelectFilterItem({filterType: this.props.type, item});
	}

  	handleAllSelectionChange(selected) {
	    this.props.onSelectAllFilterList({filterType: this.props.type, selected});
	}

  	render() {
    	return (
	        <div>
	        	<ul>
		        	{(this.props.filter && this.props.filter.items) ? this.props.filter.items.map(item => 
			          <li key={item.id}>
			          	<FilterItem {...item} onItemChange={this.handleItemSelectionChange.bind(this)}/>
			          </li>
			        ): null}
		        </ul>
	        </div>
      	);
  	}
}