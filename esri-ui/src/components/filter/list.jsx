import React from 'react';
import ItemComponent from 'app/components/filter/item';

export default class FilterList extends React.Component {

  	render() {
    	return (
	        <div>
	        	<ItemComponent type="st" {...this.props.items[0]} />
	        	{this.props.items.map((item) => {
			        return <div key={item.id}> 
			        	
			        </div>
			    })}
		    </div>
      	);
  	}
}