import React from 'react';
import { Link  } from 'react-router';
import ItemComponent from './filterItemList'
import SearchComponent from './filterSearch'

export default class Header extends React.Component {

  render() {
    let filterType = this.props.filterType;
    return (
      <div className="">
        <SearchComponent filterType={filterType} />
        <div className="filter-item-list">
          <ItemComponent loadList={true} {...this.props} />
        </div>
      </div>
      )
  }
}