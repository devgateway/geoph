import React from 'react';
import { connect } from 'react-redux'
import { setFundingType } from '../../actions/settings'
require('./settings.scss');

class Settings extends React.Component {

  constructor() {
    super();
    this.state = {'showSettings': false};
  }

  toggleSettingsView() {
    this.setState({'showSettings': !this.state.showSettings});
  }

  setFunding(ev){
    this.props.onSetFundingType(ev.target.value);
    this.setState({'fundingType': ev.target.value});
  }

  changeFundingMeasure(ev){
    this.props.onSetFundingType({measure: ev.target.value, type: this.props.fundingType.type});
    //this.setState({'fundingType': ev.target.value});
  }

  changeFundingType(ev){
    this.props.onSetFundingType({measure: this.props.fundingType.measure, type: ev.target.value});
    //this.setState({'fundingType': ev.target.value});
  }

  render() {
    return (
      <li ><div className="options-icons settings" onClick={this.toggleSettingsView.bind(this)}></div><span onClick={this.toggleSettingsView.bind(this)}>Settings</span>
        {this.state.showSettings?
          <div className="settings-container">
            <h2>Funding Type </h2>
            <br />
            <div className="chart-type-selector">
              <div className="chart-type-option">
                <select className='form-control input-sm' value={this.props.fundingType.measure} onChange={this.changeFundingMeasure.bind(this)}>
                  <option value='commitments'>Commitments</option>
                  <option value='disbursements'>Disbursements</option>
                  <option value='expenditures'>Expenditures</option>
                </select>
              </div>
              <div className="chart-type-option">
                <select className='form-control input-sm' value={this.props.fundingType.type} onChange={this.changeFundingType.bind(this)}>
                  <option value='actual'>Actual</option>
                  <option value='cancelled'>Cancelled</option>
                  <option value='target'>Target</option>
                </select>
              </div>               
            </div> 
          </div>
        : null}
      </li>
    );
  }
}

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

export default connect(mapStateToProps, mapDispatchToProps)(Settings);;
