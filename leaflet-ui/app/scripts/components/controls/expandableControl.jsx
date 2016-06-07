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
    return (
      <div className={(this.state.expanded==true)?"expandable-control":"expandable-control collapsed"}>
        <div className="title" onClick={this.toggleView}>
          <div className="icon"><div className={this.props.iconClass}/></div>
          {this.props.title}
          <div className="toggle">
            <span>{(this.state.expanded==true)?'–':'+'}</span>
          </div>
        </div>
        {(this.state.expanded==true)? 
          <div className="expanded-content">
            {this.props.children}
          </div>
          :null}
      </div>
    );
  }
})

export default Expandable;