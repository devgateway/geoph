import React from 'react';
import { connect } from 'react-redux'
import Chart from '../charts/chartComponent'
import { fetchChartData } from '../../actions/charts.js'

class Charts extends React.Component {

  constructor() {
    super();
  }

  componentDidMount() {
    this.props.onLoadChartData('fundingAgency', {});
    this.props.onLoadChartData('sector', {});
    this.props.onLoadChartData('impAgency', {});    
  }

  render() {
    return (
      <div className="chart-view">
        <p>Explore this in-depth profile of Philippines to find out overall lorem ipsum dolor sit amet, consectetur elit. </p>
        <div className="charts-container">
          <Chart chartData={this.props.charts.fundingAgency? this.props.charts.fundingAgency.data : null}
            title="Funding Agency Chart" 
            measure="grant" 
            dimension="name"/>
          <Chart chartData={this.props.charts.impAgency? this.props.charts.impAgency.data : null}
            title="Implementing Agency Chart" 
            measure="grant"  
            dimension="name"/>
          <Chart chartData={this.props.charts.sector? this.props.charts.sector.data : null}
            title="Sector Chart" 
            measure="grant"  
            dimension="name"/>
        </div>  
      </div>
    )
  }
}


const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onLoadChartData: (chart) => {
      dispatch(fetchChartData(chart));
    }
  }
}

const mapStateToProps = (state, props) => {
  return {
    charts: state.charts, language: state.language
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Charts);;



