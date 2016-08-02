import React from 'react';
import { connect } from 'react-redux';
import InputRange from 'react-input-range';
import { clearAllResults, searchProjectsByText, toggleProjectSelection, selectAllMatchedProject, clearAllProjectSelected } from '../../actions/projectSearch';
import { applyFilter } from '../../actions/filters';
import { fetchStats } from '../../actions/stats';
import { Input } from 'react-bootstrap';
import { cloneDeep,collectValues } from '../../util/filterUtil';
require('./projectFilter.scss');

var typingTimer;                //timer identifier
var doneTypingInterval = 900;  //time in ms (5 seconds)

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
 const tiping = this.state.tiping;
 if (length>0){
  if (tiping){
   return 'error';
 } else {
   return 'success';
 }
}
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
 this.setState({keyword: keyword, tiping: true});
 clearTimeout(typingTimer);
 typingTimer = setTimeout(this.doneTyping.bind(this), doneTypingInterval);	    
}

doneTyping () {
 this.setState({tiping: false});
 let filters = collectValues(this.props.filters);
 Object.assign(filters, {'pt': this.state.keyword});
 this.props.onTriggerSearch(filters);
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

clearResults(){
  this.props.onClearResults();
}

showSelected(){
  this.setState({'showResults': false});
}

showResults(){
  this.setState({'showResults': true});
}

dropLongTitle(title){
  if (title.length>90){
   return title.substr(0, 80)+"...";
 }
 return title;
}

applySelection(){
  let filters = collectValues(this.props.filters, this.props.projectSearch);
  this.props.onFilterByProjects(filters);
}





getInput(){
  const {keyword}=this.state;
  return  (<Input className={keyword.length==0? 'keyword-input-empty' : 'keyword-input-filled'} 
   type="text" 
   value={keyword}  
   placeholder="Search for Projects (Please enter at least 3 characters)"  
   bsStyle={this.validateState()}   
   bsSize="small"  ref="keyword"   
   onChange={this.handleChange.bind(this)}/>)
}


getActions(){
  return (  
   <div className="project-search-actions">
  
   <a href="#" onClick={this.selectAllMatched.bind(this)}><div className="btn btn-xs btn-all"></div>select all</a>
  
   <span>/</span>
   {this.state.showResults?<a href="#" onClick={this.showSelected.bind(this)}>selected ({this.props.projectSearch.selected.length})</a>:<a href="#" onClick={this.showResults.bind(this)}>all</a>}
  
   <span>/</span>
  
   <a href="#" onClick={this.applySelection.bind(this)}><div className="btn btn-xs btn-apply"></div> apply</a>
  
   <span>/</span>

   <a href="#" onClick={this.clearAllSelection.bind(this)}><div className="btn btn-xs btn-clear"></div>clear all</a>
   </div>                       
   )
}
getNoSelection(items){
  debugger;
  if (items && items.length == 0){
    return <div>No selection</div>
  }
}
getLoading(isFetching){
  if (isFetching){
    return (<div className="message">Loading </div>)
  }
}
getRow(item){
  return( 
    <div className="row">
    <div className="col1">
    <div className={this.validateSelection(item.id)} onClick={this.handleSelection.bind(this, item)} />
    </div>
    <div className="col2">
    <div className="item-text" onClick={this.handleSelection.bind(this, item)}>
    {this.dropLongTitle(item.title)}
    </div>
    </div>
    </div>)
}

getContent(content,size){
  if(content && content.length >0){
    return <div>{content.map((item,idx)=>(idx<10)?this.getRow(item):null)}</div>
  }
}



render() {
  const {showResults}= this.state;
  const {projectSearch} = this.props;
  const {results,selected} = projectSearch;
  const {content,first,isFetching,last,lastUpdate,number,numberOfElements,size,sort,totalElements,totalPages} = results
  
  const items=showResults?this.getContent(content,10):this.getContent(selected,10);   
  
  const loading=showResults?this.getLoading(isFetching):null;
  
  const noSelecion=!showResults?this.getNoSelection(items):null;

  return (
   <div className="project-search">
   <div className="project-search-keyword">
   <h2>Search over {this.props.stats.projectCount} results</h2>
   <div className="">
   {this.getInput()}
   </div>
   {this.getActions()}	        	
   </div>

   <div className="project-search-results">
   {loading}
   {noSelecion}
   {items} 
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

    onClearResults: () => {
      dispatch(clearAllResults());
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
