import React from 'react';
import ReactDOM from 'react-dom';
import {Modal} from 'react-bootstrap';
import {connect} from 'react-redux';

class NoResultsPopup extends React.Component {	
	constructor() {
	    super();
	    this.state = {show: false};
	}
	
	close (){
		this.setState({show: false})
	}

	componentWillReceiveProps(nextProps){
		if (nextProps.stats.lastUpdated != this.props.stats.lastUpdated){
			if (nextProps.stats.data.national.projectCount==0 && nextProps.stats.data.regional.projectCount==0){
				this.setState({show: true});
			}
		}
	}

	render() {
		const {show} = this.state;
		return (
    		<Modal animation={false} aria-labelledby='contained-modal-title-lg'  
    		show={show} onHide={this.close.bind(this)}>
				<Modal.Header closeButton >
					<Modal.Title>
						No Results
					</Modal.Title>
				</Modal.Header>
				<Modal.Body className="disclaimer-content">
					<div className="bs-callout bs-callout-error">
						There are not projects that match with filter criteria.
					</div>	
				</Modal.Body>
			</Modal>
		)
	}	
}

const mapStateToProps = (state, props) => {
	return {stats: state.stats.toJS().global}
}

export default connect(mapStateToProps)(NoResultsPopup);