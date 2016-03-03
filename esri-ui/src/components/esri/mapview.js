import React from 'react';
import ReactDOM from 'react/react-dom';
import MapView from 'esri/views/MapView';
import domReady from "dojo/domReady!";


const SceneViewComponent = React.createClass({

 componentDidMount() {
   const node = ReactDOM.findDOMNode(this.refs.mapView);
   this.view= new MapView({container: node,...this.props});
 },

 getInitialState(){
  return {ready:false}
},

render: function() {  
  const view=this.view;
  const children = this.view ? React.Children.map(this.props.children, child => {return child ? React.cloneElement(child, {view}) : null;}) : null;
  return (<div ref='mapView'>{children}</div>);
}
});

export default SceneViewComponent;
