import React from 'react'
import { connect } from 'react-redux'
import {changeStep, changeProperty, updateErrors, upload, downloadTemplate} from '../../actions/indicators.js'
import {Map} from 'immutable'
import { Link } from 'react-router'
import BaseForm from './baseForm.jsx'
import Messages from '../messages/messages.jsx'
import Connector from '../../connector/connector';

var Dropzone = require('react-dropzone');

class SelectTemplate extends BaseForm {
    getDownloadTemplateURL(level){
        return Connector.getDownloadTemplateURL(level);
    }

    render() {
        const {template,onStepChange}=this.props;

        return (
            <form>
                <div className="form-group">
                    <h1>Template</h1>
                    <select value={template} className="form-control" name="template"
                            onChange={(e)=>{this.handleChangeValue('template',e.target.value)}}>
                        <option value="Region">Regional</option>
                        <option value="Province">Province</option>
                        <option value="Municipality">Municipal</option>
                    </select>
                </div>
                <div className="form-group ">
                    <a target="_blank" href={this.getDownloadTemplateURL(this.props.template)}>
                        <input type="button" className="btn btn-xs btn-info" value="Download Template"></input>
                    </a>
                    <input type="button" className="btn btn-xs btn-success pull-right"
                        onClick={()=>{onStepChange('indicator')}} value="Next"></input>
                </div>
            </form>
        )
    }
};


class AddIndicator extends BaseForm {

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
            this.props.onUpload();
        }
    }

    validate() {
        const {template,name='',file} = this.props;
        let errors = {}
        errors = this.validateField(errors, 'template', template);
        errors = this.validateField(errors, 'name', name);
        errors = this.validateField(errors, 'file', file);
        return errors;

    }

    onDrop(files) {
        this.handleChangeValue('file', files[0])
    }

    render() {
    
    const {errors={}}=this.props;
      return (
            <form id="add-indicator-form">
            <h1>Indicator Info </h1>
                 <div className={errors.name?"form-group has-error":"form-group"}>
                    <label >Name</label>
                    <input value={this.props.name}
                           onChange={(e)=>{this.handleChangeValue('name',e.target.value)}} type="name"
                           className="form-control" id="name" placeholder="Type Name"/>
                </div>
                {/*<div className={errors.color?"form-group has-error":"form-group"}>
                    <label >Default Color</label>
                    <ul className="colors pull-right">
                        <li className={this.props.css=="red"?"scheme red active":"scheme red "}
                            onClick={()=>{this.handleChangeValue('css','red')}}></li>
                        <li className={this.props.css=="yellow"?"scheme yellow active":"scheme yellow "}
                            onClick={()=>{this.handleChangeValue('css','yellow')}}></li>
                        <li className={this.props.css=="green"?"scheme green active":"scheme green "}
                            onClick={()=>{this.handleChangeValue('css','green')}}></li>
                        <li className={this.props.css=="orange"?"scheme orange active":"scheme orange "}
                            onClick={()=>{this.handleChangeValue('css','orange')}}></li>
                        <li className={this.props.css=="blue"?"scheme blue active":"scheme blue "}
                            onClick={()=>{this.handleChangeValue('css','blue')}}></li>
                    </ul>
                </div>*/}
                <div className={errors.file?"form-group has-error":"form-group"}>
                    <label>File</label>

                    <Dropzone className="drop-zone" multiple={false} onDrop={this.onDrop.bind(this)}>
                        <div>{this.props.file ? this.props.file.name : <div>Drop a file or click this area</div>}</div>
                    </Dropzone>
                </div>
                <div className="form-group ">
                    <input type="button" className="btn btn-xs btn-success"
                           onClick={()=>{this.props.onStepChange('template')}} value="Back"></input>
                    <input type="button" className="btn btn-xs btn-info pull-right" value="Save"
                           onClick={this.submit.bind(this)}></input>

                </div>
            </form>
        )
    }
};

class Indicator extends React.Component {
   
    getView() {        
        switch (this.props.step) {
            case 'loading':
                return <div className="loading-css"><div></div></div>
            case 'template':
                return <SelectTemplate {...this.props}/>
            case 'indicator':
                return <AddIndicator {...this.props}/>
        }
    }

    render() {
        return (
            <div className="admin-page">                
                <Messages {...this.props}/>                
                {this.getView()}
            </div>
        )
    }
};


const mapDispatchToProps = (dispatch, ownProps) => {
    return {
        onStepChange: (step)=> {
            dispatch(changeStep(step));
        },
        onPropertyChange: (property, value)=> {
            dispatch(changeProperty(property, value));
        },
        onValidate: (errors)=> {
            dispatch(updateErrors(errors));
        },
        onUpload: ()=> {
            dispatch(upload());
        }
    }
};

const mapStateToProps = (state, props) => {
        const {indicators} = state;
        return {...indicators.toObject()};
};

export default connect(mapStateToProps, mapDispatchToProps)(Indicator);

