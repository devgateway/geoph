import React from 'react';
import { connect } from 'react-redux';
import {changeProperty,updateErrors,saveMap}  from '../../actions/saveAndRestoreMap';
import BaseForm from '../admin/baseForm.jsx'
import {Map} from 'immutable'
import Messages from '../messages/messages.jsx'

require('./save.scss');

class SaveForm extends BaseForm {
  
  constructor(props) {
    super(props);
    this.state = {data: Map(), saving: false};
  }
  
  submit() {
    let errors = this.validate();
    let hasError = false;
    Object.keys(errors).forEach(key => hasError = hasError || errors[key]);
    if (hasError) {
      this.setErrors(errors);
    } else {
      if (this.props.onSaveMap) {
        this.props.onSaveMap();
        this.setState({saving: true});
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
    const {errors={}, httpError, name, description, status, id, saving, type}=this.props;
    return (
      <div className="save-container">
        <h2>Save Map</h2>
        {saving?
          <div className="loading-css"><div></div></div>
          :
          <div>
            <div>
              <Messages {...this.props}/>
            </div>
            <div className={errors.name?"form-group has-error":"form-group"}>
              <input className="form-control" placeholder="Enter a name"  type="text" value={name}  onChange={(e)=>{this.handleChangeValue('name',e.target.value)}}/>
            </div>
            <div  className={errors.description?"form-group has-error":"form-group"}>
              <textarea placeholder="Enter a description" className="form-control" value={description} onChange={(e)=>{this.handleChangeValue('description',e.target.value)}}/>
            </div>
            <div className="dashboard-checkbox" onClick={this.handleChangeValue.bind(this, 'type', type=="dashboard"? "save" : "dashboard")} >
              <div className={type=="dashboard"? "selectable selected" : "selectable"} />
              <div className="toggle-nav">
                Show in executive dashboard for all users
              </div>
            </div>
            <div className="form-group">
              <button className="btn btn-sm btn-success" onClick={this.submit.bind(this)}>Save</button>
            </div>
          </div>
        }
      
      </div>
    );
  }
}

class Container extends React.Component {
  render() {
    const {visible}=this.props;
    
    return (
      <div>
        {visible?<SaveForm {...this.props}/>:null}
      </div>
    );
  }
}

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
};

const mapStateToProps = (state, props) => {
  const {name, description, errors, httpError, status, id, saving, type} = state.saveMap.toJS();
  return {name, description, errors, httpError, status, id, saving, type}
};

export default connect(mapStateToProps,mapDispatchToProps)(Container);