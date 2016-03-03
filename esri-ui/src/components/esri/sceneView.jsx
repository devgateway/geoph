import React from 'react';
import ReactDOM from 'react/react-dom';
import SceneView from 'esri/views/SceneView';



const SceneViewComponent = React.createClass({

 componentDidMount() {
   var node = ReactDOM.findDOMNode(this.refs.mapView);
   this.view= new SceneView({container: node,...this.props});
  
},


render(){
   const children = this.view ? React.Children.map(this.props.children, child => {return child ? React.cloneElement(child, {view}) : null;}) : null;

   return (<div className='mapView' ref='mapView'>{children}</div>)
}

});



export default SceneViewComponent;
