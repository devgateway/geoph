import React from 'react';
require('./expandableControl.scss');

const Expandable = React.createClass({

  getInitialState() {
    return {'expanded': this.props.defaultExpanded || false};
  },

  toggleView() {
    this.setState({'expanded': !this.state.expanded});
  },

  render() {
  	const {expanded}=this.state;
    return (
      <div className={(expanded==true)?"expandable-control":"expandable-control collapsed"}>
       
        <div className="title" onClick={this.toggleView}>
          {this.props.title}
          <div className="toggle">
            <span>{(expanded==true)?'–':'+'}</span>
          </div>
        </div>
        
        {(this.state.expanded==true)? 
          <div className="content">
            {this.props.children}
          </div>
          :null}
      </div>
    );
  }
})

export default Expandable;