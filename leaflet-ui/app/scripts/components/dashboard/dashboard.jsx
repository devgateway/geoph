import React from 'react';
import {Link} from 'react-router';
import {connect} from 'react-redux';
import Messages from '../messages/messages.jsx'
import {getMapList, edit, remove} from '../../actions/dashboard.js';
import Moment from 'moment';
import translate from '../../util/translate.js';

require("./dashboard.scss");

const pageSize = 5;

class Item extends React.Component {
  render() {
    const {description, name, mapKey, type, base64preview, creationDate, loggedIn} = this.props;
    return (
      <div className="item">
        <Link to={`/map/${mapKey}`}>
          <div className="preview">
            <img src={`data:image/png;base64,${base64preview}`}/>
          </div>
          <div className="title">
            {name}
          </div>
        </Link>
        <div className="description">
          {description}
        </div>
        <div className="created">
          {Moment(creationDate).format("YYYY-MM-DD") || "No creation date"}
        </div>
        {loggedIn ?
          <div className="visible">
            {type == "dashboard" ? "YES" : "NO"}
          </div>
          : null}
        <div className="actions">
          {this.props.children}
        </div>
      </div>
    )
  }
}

class AdminActions extends React.Component {
  render() {
    const {id, mapKey, onDelete, onEdit} = this.props;
    return (
      <div>
        <a href={`#/map/${mapKey}`} className="btn btn-sm btn-info">{translate('admin.dashboards.edit')}</a>
        <button className="btn btn-sm btn-danger"
                onClick={_ => onDelete(mapKey)}>{translate('admin.dashboards.delete')}</button>
      </div>
    )
  }
}

class Dashboard extends React.Component {
  constructor() {
    super();
    this.state = {'activePage': 0};
  }
  
  componentWillMount() {
    this.getListData(0);
  }
  
  getListData(activePage) {
    let params = {
      'type': this.props.loggedIn ? 'all' : 'dashboard',
      'page': activePage,
      'size': pageSize
    };
    this.props.onGetList(params);
    this.setState({activePage: activePage});
  }
  
  render() {
    const {results = [], first, last, totalPages, loggedIn} = this.props;
    const {activePage} = this.state;
    
    return (
      <div className="dashboard-main">
        <Messages {...this.props}/>
        <div className="list-header">
          <div className="preview"/>
          <div className="title">
            Map Title
          </div>
          <div className="description">
            Description
          </div>
          <div className="created">
            Created
          </div>
          {loggedIn ?
            <div className="visible">
              Visible to all
            </div>
            : null}
          {loggedIn ?
            <div className="actions">
              Actions
            </div>
            : null}
        </div>
        {results.map(d => {
          return (
            <Item {...d} loggedIn={loggedIn} mapKey={d.key}>
              {loggedIn ? <AdminActions {...this.props} mapKey={d.key}/> : null}
            </Item>
          )
        })}
        <div className="pager">
          <button className="btn btn-sm btn-default" disabled={first ? "disabled" : ""}
                  onClick={this.getListData.bind(this, activePage - 1)}>{"<"}</button>
          <div className={"pager-state"}>{"page " + (activePage + 1) + " of " + (totalPages || "1")}</div>
          
          <button className="btn btn-sm btn-default" disabled={last ? "disabled" : ""}
                  onClick={this.getListData.bind(this, activePage + 1)}>{">"}</button>
        </div>
      </div>
    )
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onGetList: (params) => {
      dispatch(getMapList(params))
    },
    onEdit: (key) => {
      dispatch(edit(key))
    },
    onDelete: (key) => {
      dispatch(remove(key))
    }
  }
};

const mapStateToProps = (state, props) => {
  const { results, first, last, totalPages } = state.dashboard.toJS();
  return {
    language: state.language,
    results,
    first,
    last,
    totalPages
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(Dashboard);