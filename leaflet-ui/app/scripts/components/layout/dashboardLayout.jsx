import React from 'react';
import { connect } from 'react-redux'
import Header from './header.jsx';
import Footer from './footer.jsx';
import Menu from '../menu/default.jsx';
import AdminMenu from '../admin/menu.jsx';

require("./root.scss");

class DashboardLayout extends React.Component {
  render() {
    const { loggedIn } = this.props;
    const newChildren = React.Children.map(this.props.children, child => React.cloneElement(child, { loggedIn }));
    let title = "";
    
    switch (newChildren[0].props.route.path) {
      case "/admin":
        title = "Admin Section";
        break;
      case "/about":
        title = "About";
        break;
      default:
        title = "Executive Dashboards";
        break;
    }
    
    return (
      <div className="root">
        <Header>
          <Menu title={title}>
            {loggedIn ? <AdminMenu/> : null}
          </Menu>
        </Header>
        <div className="dashboard">
          {newChildren}
        </div>
        <Footer/>
      </div>
    )
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onLogin: (loginData) => dispatch(login(loginData))
  }
};

const mapStateToProps = (state, props) => {
  const { security } = state;
  const { accountNonExpired, accountNonLocked, enabled, credentialsNonExpired } = security.toObject();
  const loggedIn = accountNonExpired && accountNonLocked && enabled && credentialsNonExpired;
  return { loggedIn }
};

export default connect(mapStateToProps, mapDispatchToProps)(DashboardLayout);

