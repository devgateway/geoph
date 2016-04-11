import React from 'react';
import { connect } from 'react-redux'
import Chart from '../charts/chartComponent'
import { fetchChartData } from '../../actions/charts.js'

class Charts extends React.Component {

  constructor() {
    super();
  }

  componentDidMount() {
    this.props.onLoadChartData('fundingAgency');
  }

  render() {
    debugger;
  	return (
    	<div className="chart-view">
          <p>Explore this in-depth profile of Philippines to find out overall lorem ipsum dolor sit amet, consectetur elit. </p>
          <Chart chartData={this.props.charts.fundingAgency? this.props.charts.fundingAgency.data : null} 
            measure="grant" 
            dimension="name"
            width="350"
            height="350"/>
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



