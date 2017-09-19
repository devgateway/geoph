import React from 'react';
import { connect } from 'react-redux'
import { selectAllFilterList, selectFilterItem } from '../../actions/filters.js';

class FilterList extends React.Component {
  
  render() {
    if (this.props.expanded) {
      return (
        <div>
          <ul style={{left: '25', listStyleType: 'none'}}>
            {this.props.items.map((item) => {
              return <li key={item.id}>
                <ItemConnected filterType={this.props.filterType} showCode={this.props.showCode} {...item} />
              </li>
            })}
          </ul>
        </div>
      );
    } else {
      return null;
    }
  }
}

class FilterItem extends React.Component {
  
  constructor() {
    super();
    this.state = {'expanded': true, 'partialSelected': false};
  }
  
  toggleExpanded() {
    this.setState({'expanded': !this.state.expanded});
  }
  
  handleChange() {
    const {id, filterType, selected} = this.props;
    if (this.props.id) {
      this.props.onItemChange({id, filterType, selected: !selected});
    } else {
      this.props.onChangeAllFilterList({filterType, selected: !selected});
    }
  }
  
  render() {
    let selectionClass = "selectable " +
      (this.props.selected ? "selected" : (!this.props.selectedCounter || this.props.selectedCounter == 0) ? "" : this.props.selectedCounter == this.props.totalCounter ? "selected" : "half-fill");
    if (this.props.hide) {
      return null;
    }
    return (
      <div>
        <div className="filterItemInfo">
          <div className={selectionClass} onClick={this.handleChange.bind(this)}/>
          <div className="toggle-nav" onClick={this.handleChange.bind(this)}>
            {this.props.showCode && this.props.code ?
              (this.props.name || this.props.title) + " (" + this.props.code + ")"
              :
              (this.props.name || this.props.title)
            }
            {this.props.items && this.props.items.length > 0 ?
              <div>
                <div className="counter">
                  ({this.props.selectedCounter}/{this.props.totalCounter})
                </div>
                <div className={this.state.expanded ? "expanded open" : "expanded closed"}
                     onClick={this.toggleExpanded.bind(this)}>
                  {this.state.expanded ? "-" : "+"}
                </div>
              </div>
              : null}
          </div>
        </div>
        <div>
          {this.props.items && this.props.items.length > 0 ?
            <FilterList expanded={this.state.expanded} {...this.props} />
            : null}
        </div>
      </div>
    );
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onItemChange: (filterItem) => {
      dispatch(selectFilterItem(filterItem));
    },
    
    onChangeAllFilterList: (filterItem) => {
      dispatch(selectAllFilterList(filterItem));
    }
  }
};

const ItemConnected = connect(null,mapDispatchToProps)(FilterItem);
export default ItemConnected;
