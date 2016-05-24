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
	    this.state = {'keyword': '', 'idsSelected': []};
	}

	componentDidMount() {
				
	}

	validateState() {
    	const length = this.state.keyword.length;
    	if (length > 3) return 'success';
    	else if (length > 0) return 'error';
  	}

  	validateSelection(id) {
    	let idsSelected = this.state.idsSelected;
    	if (idsSelected.indexOf(id)==-1){
  			return 'selectable';
  		} else {
  			return 'selectable selected';
  		}
  	}

  	handleChange(e) {
    	let keyword = e.target.value;
		this.setState({ keyword: keyword });
  		if (keyword.length>3){
  			let filters = collectValues(this.props.filters);
  			Object.assign(filters,{'page':1, 'size': 10, 'pt': keyword});
  			this.props.onTriggerSearch(filters);
  		} else {
			//should clear here?
		}
  	}

  	handleSelection(id){
  		let idsSelected = this.state.idsSelected;
  		if (idsSelected.indexOf(id)==-1){
  			idsSelected.push(id);
  		} else {
  			idsSelected.splice(idsSelected.indexOf(id), 1);
  		}
  		this.setState({'idsSelected': idsSelected});
  		this.applySelection();
  	}

	applySelection(){
  		let filters = collectValues(this.props.filters);
		Object.assign(filters, {'pr': this.state.idsSelected});
		this.props.onFilterByProjects(filters);
  	}

	render() {
		let projectSearchResults = this.props.projectSearchResults;
		return (
	        <div className="project-search">
        		<div className="project-search-keyword">
        			<div className="">
			          	<Input className={this.state.keyword.length==0? 'keyword-input-empty' : 'keyword-input-filled'} type="text" value={this.state.keyword}  
				            placeholder="Search for Projects (Please enter at least 3 characters)"  
				            bsStyle={this.validateState()}   
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
		        		projectSearchResults.content.map((item) => {
		        			return <div className="filterItemInfo">
				        		<div className={this.validateSelection(item.id)} onClick={this.handleSelection.bind(this, item.id)} />
					        	<div className="toggle-nav item-text" onClick={this.handleSelection.bind(this, item.id)}>
					        		{item.title}
					        	</div>
					        </div>
		        		})
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
