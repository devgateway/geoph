import React from 'react'
import { connect } from 'react-redux'
import {Map} from 'immutable'


export default class BaseForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {data: Map()};
    }

    handleChangeValue(property, value) {
        this.props.onPropertyChange(property, value);
        let newErrors = Object.assign({}, this.props.errors);
        this.setErrors(this.validateField(newErrors, property, value));

    }

    validateValue(value = '') {
        if (typeof value == "string") {
            return (value.trim() != '' && value.length > 0)
        } else {
            return value != null && typeof value != 'undefined';
        }
    }

    validateField(errors, property, value) {
        let newErrors = Object.assign({}, errors)
        newErrors[property] = !this.validateValue(value);
        return newErrors;
    }

    setErrors(errors) {
        this.props.onValidate(errors);
    }

}

