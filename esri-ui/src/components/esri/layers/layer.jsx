import React from 'react';
import ReactDOM from 'react/react-dom';
import domReady from "dojo/domReady!";

class Layer extends React.Component{

	getClonedChildrenWithMap(extra) {
		const { children, map } = this.props;
		const props = Object.assign({map}, extra);

		return React.Children.map(children, child => {
			return child ? React.cloneElement(child, props) : null;
		});
	}

	renderChildrenWithProps(props) {
		const children =this.getClonedChildrenWithMap(props);
		return <div style={{display: 'none'}}>{children}</div>;
	}

	render() {
		return this.renderChildrenWithProps({layer:this.element});
	}

}

export default Layer;
