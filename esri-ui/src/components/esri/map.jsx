import React from 'react';
import ReactDOM from 'react/react-dom';
import Map from 'esri/Map';

const map = new Map({ basemap: 'streets'});


const MapComponent = React.createClass({

 componentWillMount() {
  this.map=new Map(this.props);
},

render: function() {

  const map= this.map;    
  const children = map ? React.Children.map(this.props.children, child => {return child ? React.cloneElement(child, {map}) : null;}) : null;
  return (
    <div className={this.props.className} style={this.props.style}>
      {children}
    </div>
    );
  }
});

export default MapComponent;
