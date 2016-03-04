import React from 'react';
import Menu from'react-burger-menu';

export default class SlideOutMenu extends React.Component {

  render() {
    return (
        <Menu.slide right={this.props.right} id={this.props.id}>
          <a className="menu-item" href="#">Organisations</a>
          <a className="menu-item" href="#">Sectors</a>
          <a className="menu-item" href="#">Locations</a>
        </Menu.slide>
      );
  }
}