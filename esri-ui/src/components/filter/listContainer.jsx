
import { connect } from 'react-redux'
import { fetchFilterListIfNeeded, selectAllFilterList, selectFilterItem } from 'app/actions/index'
import FilterTabsComponent from 'app/components/filter/filterTabs'
import * as Constants from 'app/constants/constants';

const mapStateToProps = (state, props) => {
  return {
    filters: state.filters
  }
}

export default connect(mapStateToProps)(FilterTabsComponent);;
 