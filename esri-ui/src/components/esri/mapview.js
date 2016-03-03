import React from 'react';
import ReactDOM from 'react/react-dom';
import MapView from 'esri/views/MapView';



const SceneViewComponent = React.createClass({

 componentDidMount() {
   this.view= new MapView({container: ReactDOM.findDOMNode(this),...this.props});
   this.setState({ready:true})
 },

 getInitialState(){
  return {ready:false}
},



render: function() {  
  const view=this.view;
  if (!this.state.ready){
    return (<div></div>);
  }else{
    const children = this.view ? React.Children.map(this.props.children, child => {return child ? React.cloneElement(child, {view}) : null;}) : null;

    return (<div>{children}</div>);
  }
}
});

export default SceneViewComponent;
