import React from 'react';
import { connect } from 'react-redux';
import InputRange from 'react-input-range';
import { searchProjectsByText, toggleProjectSelection, selectAllMatchedProject, clearAllProjectSelected } from '../../actions/projectSearch';
import { applyFilter } from '../../actions/filters';
import { fetchStats } from '../../actions/stats';
import { Input } from 'react-bootstrap';
import { cloneDeep,collectValues } from '../../util/filterUtil';
require('./projectFilter.scss');


class ProjectFilter extends React.Component {

	constructor() {
	    super();
	    this.state = {'keyword': '', 'idsSelected': [], 'showResults': true};
	}

	componentDidMount() {
		let filters = collectValues(this.props.filters);
		this.props.onGetStats(filters);		
	}

	validateState() {
    	const length = this.state.keyword.length;
    	if (length > 3) return 'success';
    	else if (length > 0) return 'error';
  	}

  	validateSelection(id) {
  		let selected = false;
  		this.props.projectSearch.selected.map((it) => {
  			if (it.id==id){
  				selected = true;
  			}
  		});
    	if (!selected){
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
  			Object.assign(filters, {'pt': keyword});
  			this.props.onTriggerSearch(filters);
  		} else {
			//should clear here?
		}
  	}

  	handleSelection(project){
  		this.props.onToggleSelection(project);  		
  	}

  	selectAllMatched(){
  		this.props.onSelectAllMatched();
  	}

	clearAllSelection(){
  		this.props.onClearAll();
  	}

  	showSelected(){
  		this.setState({'showResults': false});
  	}

  	showResults(){
		this.setState({'showResults': true});
  	}

  	dropLongTitle(title){
  		if (title.length>60){
  			return title.substr(0, 58)+"...";
  		}
  		return title;
  	}

	applySelection(){
  		let filters = collectValues(this.props.filters);
  		let idsSelected = [];
  		this.props.projectSearch.selected.map(it => idsSelected.push(it.id));
		Object.assign(filters, {'pr': idsSelected});
		this.props.onFilterByProjects(filters);
  	}

	render() {
		let projectSearchResults = this.props.projectSearch.results;
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
		        <div className="project-search-actions">
		        	{this.state.showResults?
		        		<a href="#" onClick={this.showSelected.bind(this)}>
		        			Show Selected ({this.props.projectSearch.selected.length}/{this.props.stats.projectCount})
		        		</a>
		        	:
		        		<a href="#" onClick={this.showResults.bind(this)}>Show Search Results</a>
		        	}/
        			<a href="#" onClick={this.applySelection.bind(this)}>Apply Selection</a> /	 
        			<a href="#" onClick={this.selectAllMatched.bind(this)}>Select all matched</a> /	 
        			<a href="#" onClick={this.clearAllSelection.bind(this)}>Clear all</a>	 
		        </div>	        		        		        	
	        	<div className="project-search-results">
	        	{this.state.showResults?
	        		this.state.keyword.length>3?
		        		projectSearchResults.isFetching?
		        		<div>Loading Data</div>
			        	: projectSearchResults.content?
			        		projectSearchResults.content.length==0?
			        			<div>No Results</div>
			        		: 
				        		projectSearchResults.content.map((item, idx) => {
				        			if (idx<9){
					        			return <div className="filterItemInfo">
							        		<div className={this.validateSelection(item.id)} onClick={this.handleSelection.bind(this, item)} />
								        	<div className="toggle-nav item-text" onClick={this.handleSelection.bind(this, item)}>
								        		{this.dropLongTitle(item.title)}
								        	</div>
								        </div>
								    } else {
								    	return null;
								    }
				        		})
				        : null
				    : null
				:
					this.props.projectSearch.selected.length==0?
	        			<div>No Projects Selected</div>
	        		: 
		        		this.props.projectSearch.selected.map((item, idx) => {
		        			if (idx<9){
			        			return <div className="filterItemInfo">
					        		<div className={this.validateSelection(item.id)} onClick={this.handleSelection.bind(this, item)} />
						        	<div className="toggle-nav item-text" onClick={this.handleSelection.bind(this, item)}>
						        		{this.dropLongTitle(item.title)}
						        	</div>
						        </div>
						    } else {
						    	return null;
						    }
		        		})
		        }
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

    onToggleSelection: (project) => {
      dispatch(toggleProjectSelection(project));
    },

    onSelectAllMatched: () => {
      dispatch(selectAllMatchedProject());
    },

    onClearAll: () => {
      dispatch(clearAllProjectSelected());
    },

    onFilterByProjects: (filters) => {
      dispatch(applyFilter(filters));
    },

    onGetStats: (filters) => {
      dispatch(fetchStats(filters));
    }
  }
}

const mapStateToProps = (state, props) => {
  return {
    filters: state.filters.filterMain, 
    language: state.language,
    projectSearch: state.projectSearch,
    stats: state.stats
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(ProjectFilter);
