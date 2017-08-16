import React from 'react';
import { connect } from 'react-redux';
import { browserHistory } from 'react-router';
import translate from '../../util/translate.js';
require('./about.scss');

class About extends React.Component {
  
  render() {
    const {language} = this.props;
    return(
			<div className="about-container">
				<div className="bs-callout bs-callout-info">
					<div className="about-phrase">{translate("about.phrase1")}</div>
					<div className="about-phrase">{translate("about.phrase2")}</div>
					<div className="about-phrase">{translate("about.phrase3")}</div>
				</div>
				<div className="back-button">
					<button className="btn btn-sm btn-success pull-right" onClick={browserHistory.goBack}>Back</button>
				</div>
			</div>
    )
  }
}

const mapStateToProps = (state) => {
  return {language: state.language}
};

export default connect(mapStateToProps)(About);

