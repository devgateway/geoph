import React from 'react';
import ItemComponent from './filterItemList'
import HelpIcon from './filterHelpIcon'
import SearchComponent from './filterSearch'

export default class Header extends React.Component {
  
  render() {
    const { filterType, helpTextKey } = this.props;
    return (
      <div className="">
        <SearchComponent filterType={filterType}/>
        <HelpIcon helpTextKey={helpTextKey}/>
        <div className="filter-item-list">
          <ItemComponent loadList={true} {...this.props} />
        </div>
      </div>
    )
  }
}