import React from 'react';
import FilterItem from 'app/components/filter/item';

export default class FilterList extends React.Component {

	componentDidMount() {
		this.props.onLoadFilterList(this.props.type);
	}

	componentWillReceiveProps: function(nextProps) {
    if (nextProps.baseMap && nextProps.baseMap!=this.props.baseMap) {
      console.log('map->_mapLeaflet>componentWillReceiveProps Change Map ' + nextProps.baseMap);
      this.setBaseMap(nextProps.baseMap);
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
		        	{this.props.items.map(item => 
			          <li key={item.id}>
			          	<FilterItem {...item} onItemChange={this.handleItemSelectionChange.bind(this)}/>
			          </li>
			        ): null}
		        </ul>
	        </div>
      	);
  	}
}