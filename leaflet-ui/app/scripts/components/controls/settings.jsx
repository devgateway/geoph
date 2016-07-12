import React from 'react';
import { connect } from 'react-redux'
import { setFundingType } from '../../actions/settings'
import translate from '../../util/translate.js';
import onClickOutside from 'react-onclickoutside'
require('./settings.scss');

const Settings = onClickOutside(React.createClass({

  getInitialState() {
    return {'showSettings': false};
  },

  toggleSettingsView() {
    this.setState({'showSettings': !this.state.showSettings});
  },

  setFunding(ev){
    this.props.onSetFundingType(ev.target.value);
    this.setState({'fundingType': ev.target.value});
  },

  changeFundingMeasure(ev){
    this.props.onSetFundingType({measure: ev.target.value, type: this.props.fundingType.type});
    //this.setState({'fundingType': ev.target.value});
  },

  changeFundingType(ev){
    this.props.onSetFundingType({measure: this.props.fundingType.measure, type: ev.target.value});
    //this.setState({'fundingType': ev.target.value});
  },

  handleClickOutside (evt) {
    if (this.state.showSettings){
      this.setState({'showSettings': false});
    }
  },

  render() {
    return (
      <li ><div className="options-icons settings" onClick={this.toggleSettingsView}></div><span onClick={this.toggleSettingsView}>{translate('header.settings.title')}</span>
        {this.state.showSettings?
          <div className="settings-container">
            <h2>{translate('header.settings.fundingtype')}</h2>
            <br />
            <div className="chart-type-selector">
              <div className="chart-type-option">
                <select className='form-control input-sm' value={this.props.fundingType.measure} onChange={this.changeFundingMeasure}>
                  <option value='commitments'>{translate('header.settings.commitments')}</option>
                  <option value='disbursements'>{translate('header.settings.disbursements')}</option>
                  <option value='expenditures'>{translate('header.settings.expenditures')}</option>
                </select>
              </div>
              <div className="chart-type-option">
                <select className='form-control input-sm' value={this.props.fundingType.type} onChange={this.changeFundingType}>
                  <option value='actual'>{translate('header.settings.actual')}</option>
                  <option value='cancelled'>{translate('header.settings.cancelled')}</option>
                  <option value='target'>{translate('header.settings.target')}</option>
                </select>
              </div>               
            </div> 
          </div>
        : null}
      </li>
    );
  }
}));

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onSetFundingType: (fundingType) => {
      dispatch(setFundingType(fundingType));
    }
  }
}

const mapStateToProps = (state, props) => {
  return {
    fundingType: state.settings.fundingType
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Settings);
