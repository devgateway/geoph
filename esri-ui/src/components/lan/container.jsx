import { connect } from 'react-redux';
import * as Constants from 'app/constants/constants';
import { setLanguage } from 'app/actions/index'
import MessageComponent from 'app/components/lan/translator';
import LangSwitcherComponent from 'app/components/lan/switcher';

/*Pass stat to properties Message */
const Message = connect((state, props) => {
	return {
		lan: state.language
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