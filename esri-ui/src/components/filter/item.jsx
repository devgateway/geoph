import React from 'react';
import FilterList from 'app/components/filter/list';

export default class FilterItem extends React.Component {

	constructor() {
	    super();
	    this.state = {'expanded': true, 'partialSelected': false};
	}

	handleChange() {
		let {id, type, selected} = this.props;
		if (id){
			this.props.onItemChange({id, type, selected: !selected});
		} else {
			this.props.onChangeAllFilterList({type, selected: !selected});
		}
	}

  	handleChildChange() {
  		//aka viene cuando uno del listado hijo se selecciona y hay que ver que hacer (probablemente un forceUpdate)
		
	}

  	render() {

  		let selectionClass = "selectable " + (this.props.selected? "selected" : this.state.partialSelected? "half-fill" : "");
    	return (
	        <div>
	        	<div className={selectionClass} onClick={this.handleChange.bind(this)} />
	        	<div className="toggle-nav" onClick={this.handleChange.bind(this)}>
	        		{this.props.name || this.props.description}
	        	</div>
	        	{this.props.items? "" 
	        		<div className="counter">
		        		(0/0)
		        	</div>
		        	<div className={this.state.expanded? "expanded open" : "expanded closed"}>
		        		{this.state.expanded? "-" : "+"}
		        	</div>
		        	<FilterList {...this.props} onChildChange={this.handleChildChange.bind(this)} parentSelected={this.props.selected}/>
	        	: null}	        
	        </div>
      	);
  	}
}