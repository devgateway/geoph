
import { connect } from 'react-redux';
import Message from 'app/components/Message.jsx';
import i18next from 'i18next';

const mapStateToProps = (state, ownProps) => {
  return {
    message: i18next.t(ownProps.k, {lng: state.language})
  }
}

const Translate = (k) => {
	return i18next.t(k);
}

const LangMessage = connect(
  mapStateToProps
)(Message)

export {LangMessage, Translate}