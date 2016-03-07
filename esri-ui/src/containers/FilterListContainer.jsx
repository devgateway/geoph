
import { connect } from 'react-redux'
import { fetchFilterListIfNeeded, selectAllFilterList, selectFilterItem } from 'app/actions/index.js'
import FilterList from 'app/components/FilterList'
import Constants from 'app/constants/constants.es6';

const mapStateToProps = (state, ownProps) => {
  debugger;
  return {
    filter: state.filters[ownProps.type],
    type: ownProps.type
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onSelectFilterItem: (filterItem) => {
      dispatch(selectFilterItem(filterItem))
    },
    onSelectAllFilterList: (filterItem) => {
      dispatch(selectAllFilterList(filterItem))
    },
    onLoadFilterList: (type) => {
      dispatch(fetchFilterListIfNeeded(type))
    }
  }
}

const FilterListContainer = connect(
  mapStateToProps,
  mapDispatchToProps
)(FilterList)

export default FilterListContainer