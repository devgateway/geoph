import { connect } from 'react-redux';
import * as Constants from '../../constants/constants.js';
import { setLanguage } from '../../actions/index.js'
import MessageComponent from './translator.jsx';
import LangSwitcherComponent from './switcher.jsx';

/*Pass stat to properties Message */
const Message = connect((state, props) => {

	return {
		lan: state.language,
	}
})(MessageComponent);


/* pass sate as properties and dispachers to  LangSwitcher*/
const LangSwitcher =
	connect((state, ownProps) => {
		return {
			selected: state.language
		}
	}, (dispatch, ownProps) => {
		return {
			onChangeLanguage: (lang) => {
				dispatch(setLanguage(lang))
			}
		}
	})(LangSwitcherComponent);


export {
	 Message,
	 LangSwitcher
}