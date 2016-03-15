
import { connect } from 'react-redux'
import { selectAllFilterList, selectFilterItem } from 'app/actions/index'
import ItemComponent from 'app/components/filter/item'
import Constants from 'app/constants/constants';

const mapStateToProps = (state, props) => {
  return {
    filter: state.filters[props.type]//aka filtrar por id el item del listado
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
}

export default connect(mapStateToProps,mapDispatchToProps)(ItemComponent);
 