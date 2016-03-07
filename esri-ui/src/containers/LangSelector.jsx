
import { connect } from 'react-redux'
import { setLanguage } from 'app/actions/index'
import LangSwitcher from 'app/components/LangSwitcher'
import Constants from 'app/constants/constants';

const mapStateToProps = (state, ownProps) => {
  return {
    selected: state.language
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onChangeLanguaje: (lang) => {
      dispatch(setLanguage(lang))
    }
  }
}

const LangSelector = connect(
  mapStateToProps,
  mapDispatchToProps
)(LangSwitcher)

export default LangSelector