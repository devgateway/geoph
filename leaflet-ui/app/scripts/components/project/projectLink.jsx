import React from 'react';
import { connect } from 'react-redux';
import { openProjectPage } from '../../actions/project';

class ProjectLink extends React.Component {
	
	constructor() {
		super();
	}

	openProject(){
		const {onOpenProject, id} = this.props;
		onOpenProject(id);
	}

	render() {	
		const {id, title} = this.props;
		return (
			<a onClick={this.openProject.bind(this)}>{title}</a>
		)
	}
}

const mapStateToProps = (state, props) => {
  return {
    language: state.language
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onOpenProject: (id) => {
      dispatch(openProjectPage(id));
    }
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(ProjectLink);
