import React from 'react';
import { connect } from 'react-redux'
import { setFundingType } from '../../actions/settings'
import translate from '../../util/translate.js';

require('./settings.scss');

class Settings extends React.Component {
  constructor(props) {
    super(props);
    
    this.state = {'showSettings': false};
  }
  
  changeFundingMeasure(ev) {
    let measure = ev.target.value;
    if (measure === 'commitments'){
      this.props.onSetFundingType({measure, type: 'actual'});
    } else {
      this.props.onSetFundingType({measure, type: this.props.fundingType.type});
    }
  }
  
  changeFundingType(ev){
    this.props.onSetFundingType({measure: this.props.fundingType.measure, type: ev.target.value});
  }
  
  render() {
    const {visible, fundingType={}} = this.props;
    const {measure, type} = fundingType;
    return (
      <div>
        {visible?
          <div className="settings-container">
            <h2>{translate('header.settings.fundingtype')}</h2>
            
            <div className="list">
              <div className="setting">
                <select className='form-control input-sm' value={type} onChange={this.changeFundingType.bind(this)}>
                  <option value='actual'>{translate('header.settings.actual')}</option>
                  {measure!='commitments'?
                    <option value='cancelled'>{translate('header.settings.cancelled')}</option>
                    : null}
                  {measure!='commitments'?
                    <option value='target'>{translate('header.settings.target')}</option>
                    : null}
                </select>
              </div>
            </div>
            <div className="setting">
              <select className='form-control input-sm' value={measure} onChange={this.changeFundingMeasure.bind(this)}>
                <option value='commitments'>{translate('header.settings.commitments')}</option>
                <option value='disbursements'>{translate('header.settings.disbursements')}</option>
                <option value='expenditures'>{translate('header.settings.expenditures')}</option>
              </select>
            </div>
          </div>
          : null}
      </div>
    );
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onSetFundingType: (fundingType) => {
      dispatch(setFundingType(fundingType));
    }
  }
};

const mapStateToProps = (state, props) => {
  return {
    fundingType: state.settings.fundingType,
    language: state.language
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(Settings);
