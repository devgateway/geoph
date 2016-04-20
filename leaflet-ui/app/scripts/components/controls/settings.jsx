import React from 'react';
import { connect } from 'react-redux'
import { setFundingType } from '../../actions/settings'
require('./settings.scss');

class Settings extends React.Component {

  constructor() {
    super();
    this.state = {'showSettings': false, 'fundingType': 'pd'};
  }

  toggleSettingsView() {
    this.setState({'showSettings': !this.state.showSettings});
  }

  setFunding(ev){
    this.props.onSetFundingType(ev.target.value);
    this.setState({'fundingType': ev.target.value});
  }

  render() {
    return (
      <li ><div className="options-icons settings" onClick={this.toggleSettingsView.bind(this)}></div><span onClick={this.toggleSettingsView.bind(this)}>Settings</span>
        {this.state.showSettings?
          <div className="settings-container">
            <h2>Funding Type </h2>
            <br />
            <div className="chart-type-selector">
              <div className="chart-type-option"><input type="radio" 
                value='ac'
                checked={this.state.fundingType ==='ac'} 
                onChange={this.setFunding.bind(this)} />Actual commitments
              </div>
              <div className="chart-type-option"><input type="radio"  
                value='ad' 
                checked={this.state.fundingType === 'ad'} 
                onChange={this.setFunding.bind(this)} />Actual disbursements
              </div> 
              <div className="chart-type-option"><input type="radio" 
                value='pc'
                checked={this.state.fundingType ==='pc'} 
                onChange={this.setFunding.bind(this)} />Planned commitments
              </div>
              <div className="chart-type-option"><input type="radio"  
                value='pd' 
                checked={this.state.fundingType === 'pd'} 
                onChange={this.setFunding.bind(this)} />Planned disbursements
              </div>            
            </div> 
            <hr /> 
          </div>
        : null}
      </li>
    );
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onSetFundingType: (type) => {
      dispatch(setFundingType(type));
    }
  }
}

const mapStateToProps = (state, props) => {
  return {
    settings: state.settings
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Settings);;
