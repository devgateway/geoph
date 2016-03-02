
import { connect } from 'react-redux'
import { setLanguage } from '../actions'
import LangSwitcher from '../components/LangSwitcher'
import Constants from '../constants/constants.es6';

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