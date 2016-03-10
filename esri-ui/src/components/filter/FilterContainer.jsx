
import { connect } from 'react-redux'
import { fetchFilterListIfNeeded, selectAllFilterList, selectFilterItem } from 'app/actions/index'
import FilterListComponent from 'app/components/FilterList'
import Constants from 'app/constants/constants';

const mapStateToProps = (state, props) => {
  return {
    filter: state.filters[props.type]
  }
}


const mapDispatchToProps = (dispatch, ownProps) => {
  return {

    onSelectFilterItem: (filterItem) => {
      dispatch(selectFilterItem(filterItem));
    },

    onSelectAllFilterList: (filterItem) => {
      dispatch(selectAllFilterList(filterItem));
    },
    onLoadFilterList: (type) => {
      dispatch(fetchFilterListIfNeeded(type));
    }
  }
}

const FilterList=connect(mapStateToProps,mapDispatchToProps)(FilterListComponent);

export  {FilterList};
 