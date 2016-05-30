import React from 'react';
import { connect } from 'react-redux'
import Chart from '../charts/chartComponent'
import { fetchChartData } from '../../actions/charts.js'
import {collectValues} from '../../util/filterUtil';

class Charts extends React.Component {

  constructor() {
    super();
  }

  componentDidMount() {
    let filters = collectValues(this.props.filters, this.props.projectSearch);
    this.props.onLoadChartData(filters);    
  }

  render() {
    let charts = this.props.charts? this.props.charts : {}
    return (
      <div className="chart-view">
        <p>Explore this in-depth profile of Philippines to find out overall lorem ipsum dolor sit amet, consectetur elit. </p>
        <div className="charts-container">
          <Chart chartData={charts.fundingAgency? charts.fundingAgency.data : []}
            title="Funding Agency Chart" 
            measure={this.props.fundingType} 
            showMeasureSelector={true}
            dimension="name"/>
          <Chart chartData={charts.implementingAgency? charts.implementingAgency.data : []}
            title="Implementing Agency Chart" 
            measure={this.props.fundingType} 
            showMeasureSelector={true}
            dimension="name"/>
          <Chart chartData={charts.physicalStatus? charts.physicalStatus.data : []}
            title="Physical Status Chart" 
            measure={this.props.fundingType} 
            showMeasureSelector={true}
            dimension="name"/>
          <Chart chartData={charts.sector? charts.sector.data : []}
            title="Sector Chart" 
            measure={this.props.fundingType} 
            showMeasureSelector={true}
            dimension="name"/>
        </div>  
      </div>
    )
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onLoadChartData: (filters) => {
      dispatch(fetchChartData(filters));
    }
  }
}

const mapStateToProps = (state, props) => {
  return {
    charts: state.charts, 
    language: state.language,
    fundingType: state.settings.fundingType,
    filters: state.filters.filterMain,
    projectsSelected: state.projectSearch
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Charts);;



