
import { connect } from 'react-redux'
import { selectAllFilterList, selectFilterItem, fetchFilterListIfNeeded } from 'app/actions/index'
import ItemComponent from 'app/components/filter/item'
import Constants from 'app/constants/constants';

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onLoadFilterList: (type) => {
      dispatch(fetchFilterListIfNeeded(type));
    },

    onItemChange: (filterItem) => {
      dispatch(selectFilterItem(filterItem));
    },

    onChangeAllFilterList: (filterItem) => {
      dispatch(selectAllFilterList(filterItem));
    }
  }
}

export default connect(null,mapDispatchToProps)(ItemComponent);
 