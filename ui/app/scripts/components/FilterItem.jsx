import React from 'react';

export default class FilterItem extends React.Component {

	handleChange(ev) {
		let selected = ev.target.checked;
		this.props.onItemChange({id: this.props.id, selected: selected});
	}

  	render() {
    	return (
	        <div>
	        	<input type="checkbox" checked={this.props.selected} onChange={this.handleChange.bind(this)} />
	        		{this.props.name}
	        </div>
      	);
  	}
}