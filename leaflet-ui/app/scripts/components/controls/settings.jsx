import React from 'react';
import { connect } from 'react-redux'
import { setFundingType } from '../../actions/settings'
import translate from '../../util/translate.js';
import ReactCSSTransitionGroup from 'react/lib/ReactCSSTransitionGroup';

require('./settings.scss');

const Settings =React.createClass({

  getInitialState() {
    return {'showSettings': false};
  },

  setFunding(ev){
    this.props.onSetFundingType(ev.target.value);
    this.setState({'fundingType': ev.target.value});
  },

  changeFundingMeasure(ev){
    this.props.onSetFundingType({measure: ev.target.value, type: this.props.fundingType.type});
  },

  changeFundingType(ev){
    this.props.onSetFundingType({measure: this.props.fundingType.measure, type: ev.target.value});
  },

 
  render() {
    const {visible}=this.props;

    return (
     <div>
        {visible?
           
      
          <div className="settings-container">
            <h2>{translate('header.settings.fundingtype')}</h2>
           
           <div className="list">
            <div className="setting">
                <select className='form-control input-sm' value={this.props.fundingType.measure} onChange={this.changeFundingMeasure}>
                  <option value='commitments'>{translate('header.settings.commitments')}</option>
                  <option value='disbursements'>{translate('header.settings.disbursements')}</option>
                  <option value='expenditures'>{translate('header.settings.expenditures')}</option>
                </select>
           
              </div>   
                 <div className="setting">
                <select className='form-control input-sm' value={this.props.fundingType.type} onChange={this.changeFundingType}>
                  <option value='actual'>{translate('header.settings.actual')}</option>
                  <option value='cancelled'>{translate('header.settings.cancelled')}</option>
                  <option value='target'>{translate('header.settings.target')}</option>
                </select>
              </div>            
            </div> 
          </div>


        : null}
      </div>
    );
  }
});

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onSetFundingType: (fundingType) => {
      dispatch(setFundingType(fundingType));
    }
  }
}

const mapStateToProps = (state, props) => {
  return {
    fundingType: state.settings.fundingType,
    language: state.language
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Settings);
