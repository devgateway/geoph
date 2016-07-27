import React from 'react';
import { connect } from 'react-redux';
import i18next from 'i18next';
import {changeProperty,updateErrors,saveMap}  from '../../actions/saveAndRestoreMap';
import onClickOutside from 'react-onclickoutside';
import { Input } from 'react-bootstrap';
import BaseForm from '../admin/baseForm.jsx'
import HttpError from '../messages/httpError.jsx'
import {Map} from 'immutable'
import {Messages} from '../messages/messages.jsx'

require('./save.scss');



class SaveForm extends BaseForm {

	constructor(props) {
		super(props);
		this.state = {data: Map()};
	}

	submit() {
		
		var errors = this.validate();
		let hasError = false;
		Object.keys(errors).forEach(key => hasError = hasError || errors[key]);
		if (hasError) {
			this.setErrors(errors);
		} else {
			if (this.props.onSaveMap) {
				this.props.onSaveMap();
			}
		}
	}

	validate() {
		const {name,description=''} = this.props;
		let errors = {}
		errors = this.validateField(errors, 'name', name);
		errors = this.validateField(errors, 'description', description);
		return errors;

	}


	render() {
		const {errors={},httpError,name,description,status}=this.props;
		return (
			<form>
			<div className="share-container">
			<h2>Save Map</h2>
			<div>
				{httpError?<HttpError error={httpError}/>:null}
			</div>

			<div className={errors.name?"form-group has-error":"form-group"}>
			<input className="form-control"  type="text" value={name}  onChange={(e)=>{this.handleChangeValue('name',e.target.value)}}/>
			</div> 
			<div  className={errors.description?"form-group has-error":"form-group"}>
			<textarea className="form-control"  onChange={(e)=>{this.handleChangeValue('description',e.target.value)}}/>
			</div> 
			<div className="form-group">
			<button className="btn btn-sm btn-success" onClick={this.submit.bind(this)}>Save</button>       
			</div>
			</div>
			</form>
			);
	}
};

const Container=React.createClass({
	

	render() {
		const {visible}=this.props;
		return (
			<div>
			{visible?<SaveForm {...this.props}/>:null}
			</div>
			);
	}
})



//const Save = onClickOutside(FloatingDialog);


const mapDispatchToProps = (dispatch, ownProps) => {
	return {
		onSaveMap: (dataToSave) => {
			dispatch(saveMap(dataToSave));
		},
		onPropertyChange: (property, value)=> {
			dispatch(changeProperty(property, value));
		},
		onValidate: (errors)=> {
			dispatch(updateErrors(errors));
		},



	}
}

const mapStateToProps = (state, props) => {
	const {name,description,errors,httpError,status} = state.saveMap.toJS();
	return {name,description,errors,httpError,status}
}

export default connect(mapStateToProps,mapDispatchToProps)(Container);