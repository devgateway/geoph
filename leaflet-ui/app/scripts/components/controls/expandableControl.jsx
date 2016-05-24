import React from 'react';
require('./expandableControl.scss');

export default class Header extends React.Component {

  constructor() {
    super();
    this.state = {'expanded': false};
  }

  toggleView() {
    this.setState({'expanded': !this.state.expanded});
  }

  render() {
    return (
      <div className={(this.state.expanded==true)?"expandable-control":"expandable-control collapsed"}>
        <div className="title" onClick={this.toggleView.bind(this)}>
          <div className="icon"><div/></div>
          {this.props.title}
          <div className="toggle">
            <span>{(this.state.expanded==true)?'-':'+'}</span>
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
}

