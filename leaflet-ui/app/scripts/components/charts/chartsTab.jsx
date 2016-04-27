import React from 'react';
import { connect } from 'react-redux'
import Chart from '../charts/chartComponent'
import { fetchChartData } from '../../actions/charts.js'

class Charts extends React.Component {

  constructor() {
    super();
  }

  componentDidMount() {
    this.props.onLoadChartData();    
  }

  render() {
    return (
      <div className="chart-view">
        <p>Explore this in-depth profile of Philippines to find out overall lorem ipsum dolor sit amet, consectetur elit. </p>
        <div className="charts-container">
          <Chart chartData={this.props.charts && this.props.charts.fundingAgency? this.props.charts.fundingAgency.data : []}
            title="Funding Agency Chart" 
            measure={this.props.fundingType} 
            dimension="name"/>
          <Chart chartData={this.props.charts && this.props.charts.implementingAgency? this.props.charts.implementingAgency.data : []}
            title="Implementing Agency Chart" 
            measure={this.props.fundingType} 
            dimension="name"/>
          <Chart chartData={this.props.charts && this.props.charts.physicalStatus? this.props.charts.physicalStatus.data : []}
            title="Physical Status Chart" 
            measure={this.props.fundingType} 
            dimension="name"/>
          <Chart chartData={this.props.charts && this.props.charts.sector? this.props.charts.sector.data : []}
            title="Sector Chart" 
            measure={this.props.fundingType} 
            dimension="name"/>
        </div>  
      </div>
    )
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onLoadChartData: () => {
      dispatch(fetchChartData());
    }
  }
}

const mapStateToProps = (state, props) => {
  return {
    charts: state.charts, 
    language: state.language,
    fundingType: state.settings.fundingType
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Charts);;



