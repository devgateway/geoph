import React from 'react';
import { connect } from 'react-redux';
import InputRange from 'react-input-range';
import { clearAllResults, searchProjectsByText, toggleProjectSelection, selectAllMatchedProject, clearAllProjectSelected } from '../../actions/projectSearch';
import { applyFilter } from '../../actions/filters';
import { fetchStats } from '../../actions/stats';
import { Input } from 'react-bootstrap';
import { cloneDeep,collectValues } from '../../util/filterUtil';
import translate from '../../util/translate';
import ProjectLink from '../project/projectLink'
import { Pagination, Grid, Row, Col } from 'react-bootstrap';
import {getActivePage} from '../../util/paginatorUtil';
require('./projectFilter.scss');

var typingTimer;                //timer identifier
var doneTypingInterval = 900;  //time in ms (5 seconds)
var pageSize = 10
class ProjectFilter extends React.Component {

	constructor() {
    super();
    this.state = {'keyword': '', 'idsSelected': [], 'showResults': true, 'page': 0};
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
    this.setState({'showResults': false, 'page': 0});
  }

  showResults(){
    this.setState({'showResults': true, 'page': 0});
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
     placeholder={translate('toolview.projectsearch.placeholder')}  
     bsStyle={this.validateState()}   
     bsSize="small"  ref="keyword"   
     onChange={this.handleChange.bind(this)}/>)
  }

  getActions(){
    return (  
     <div className="project-search-actions">
        <a id="ps-selectall" href="#" onClick={this.selectAllMatched.bind(this)}><div className="btn btn-xs btn-all"></div><span>{translate('toolview.projectsearch.selectall')}</span></a>
        <span>/</span>
        {this.state.showResults?
          <a id="ps-selectedresults" href="#" onClick={this.showSelected.bind(this)}>{translate('toolview.projectsearch.selectedresults')} ({this.props.projectSearch.selected.length}) </a>:
          <a id="ps-searchresults" href="#" onClick={this.showResults.bind(this)}>{translate('toolview.projectsearch.searchresults')}</a>}
        <span>/</span>
        <a id="ps-apply" href="#" onClick={this.applySelection.bind(this)}><div className="btn btn-xs btn-apply"></div> <span>{translate('toolview.projectsearch.apply')}</span></a>
        <span>/</span>
        <a id="ps-clearall" href="#" onClick={this.clearAllSelection.bind(this)}><div className="btn btn-xs btn-clear"></div><span>{translate('toolview.projectsearch.clearall')}</span></a>
      </div>                       
    )
  }

  getNoSelection(items){
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
          <div className="item-text">
            <ProjectLink {...item}/>
          </div>
        </div>
      </div>)
  }

  getContent(content, size){
    if(content && content.length >0){
      const {page} = this.state;
      let subSet = content.slice(page*pageSize, (page+1)*pageSize);
      debugger;
      return (
        <div>{subSet.map((item)=>this.getRow(item))}</div>
      )
    }
  }

/*
  getActivePage(eventKey, totalPages, number){
  //workaround for fix bootstrap paginator issues
    let activePage = 0;
    switch(eventKey.target.innerText) {
      case "»":
        activePage = totalPages-1;
        break;
      case "›":
        activePage = number+1;
        break;
      case "«":
        activePage = 0;
        break;
      case "‹":
        activePage = number-1;
        break;
      default :
        activePage = parseInt(eventKey.target.innerHTML)-1;
        break;
    }
    return activePage;
}
*/
  handlePageChange(eventKey, items) {
    let totalPages = Math.ceil(items.length/pageSize);
    let pg = getActivePage(eventKey, totalPages, this.state.page);
    debugger;
    this.setState({'page': pg});
    //eventKey.stopPropagation();
  }

  render() {
    const {showResults, page}= this.state;
    const {projectSearch} = this.props;
    const {results, selected} = projectSearch;
    const {content, first, isFetching, last, lastUpdate, number, numberOfElements, size, sort, totalElements, totalPages} = results
    const items = (showResults? content : selected) || [];   
    const itemsPaginated = this.getContent(items);   
    const loading = showResults? this.getLoading(isFetching) : null;
    const noSelecion = !showResults? this.getNoSelection(items) : null;
    debugger;
    return (
      <div id="ps-container" className="project-search">
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
          {itemsPaginated} 
        </div>
        <div className="projects-paginator">
          <Pagination 
            bsSize="small"
            prev
            next
            first
            last
            ellipsis
            boundaryLinks
            maxButtons={3}
            items={Math.ceil(items.length/pageSize)}
            activePage={page+1}
            onSelect={(eventKey) => {this.handlePageChange(eventKey, items, page)}} />
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
