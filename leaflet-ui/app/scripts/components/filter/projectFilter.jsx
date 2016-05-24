import React from 'react';
import { connect } from 'react-redux';
import InputRange from 'react-input-range';
import { searchProjectsByText } from '../../actions/projectSearch';
import { applyFilter } from '../../actions/filters';
import { Input } from 'react-bootstrap';
import { cloneDeep,collectValues } from '../../util/filterUtil';
require('./projectFilter.scss');


class ProjectFilter extends React.Component {

	constructor() {
	    super();
	    this.state = {'keyword': ''};
	}

	componentDidMount() {
				
	}

	validationState() {
    	const length = this.state.keyword.length;
    	if (length > 3) return 'success';
    	else if (length > 0) return 'error';
  	}

  	handleChange(e) {
    	let keyword = e.target.value;
		this.setState({ keyword: keyword });
  		if (keyword.length>3){
  			let filters = collectValues(this.props.filters);
  			Object.assign(filters,{'page':1, 'size': 3, 'pt': keyword});
  			debugger;			
  			this.props.onTriggerSearch(filters);
  		} else {
			//should clear here?
		}
  	}

	render() {
		let projectSearchResults = this.props.projectSearchResults;
		return (
	        <div className="project-search">
        		<div className="project-search-keyword">
        			<div className="">
			          	<Input className={this.state.keyword.length==0? 'keyword-input-empty' : 'keyword-input-filled'} type="text" value={this.state.keyword}  
				            placeholder="Search for Projects (Please enter at least 3 characters)"  
				            bsStyle={this.validationState()}   
				            bsSize="small"  ref="keyword"   
				            onChange={this.handleChange.bind(this)}/>
			        </div>	        	
		        </div>	        	
	        	<div className="project-search-results">
	        	{projectSearchResults.isFetching?
	        		<div>Loading Data</div>
	        	: projectSearchResults.content?
	        		projectSearchResults.content.length==0?
	        			<div>No Results</div>
	        		: 
		        		<ul>
		        		{projectSearchResults.content.map((item) => {
		        			return <li key={item.id}> 
					        	{item.title}
					        </li>
		        		})}
		        		</ul>
	        	: null}
		        </div>			     		        
		    </div>   
	    );
  	}
}


const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onTriggerSearch: (filters) => {
      dispatch(searchProjectsByText(filters));
    },

    onFilterByProjects: (filters) => {
      dispatch(applyFilter(filters));
    }
  }
}

const mapStateToProps = (state, props) => {
  return {
    filters: state.filters.filterMain, 
    language: state.language,
    projectSearchResults: state.projectSearch
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(ProjectFilter);
