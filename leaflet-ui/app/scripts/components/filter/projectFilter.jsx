import React from 'react';
import {connect} from 'react-redux';
import {
  clearAllResults,
  searchProjectsByText,
  toggleProjectSelection,
  selectAllMatchedProject,
  clearAllProjectSelected,
  applyProjectSelected,
  setKeyword
} from '../../actions/projectSearch';
import {applyFilter} from '../../actions/filters';
import {Input} from 'react-bootstrap';
import {collectValues} from '../../util/filterUtil';
import translate from '../../util/translate';
import ProjectLink from '../project/projectLink'
import {Pagination} from 'react-bootstrap';
import {getActivePage} from '../../util/paginatorUtil';
import {Tooltip, OverlayTrigger} from 'react-bootstrap';

require('./projectFilter.scss');

let typingTimer;                  //timer identifier
const doneTypingInterval = 900;  // time in ms
const pageSize = 10;

class ProjectFilter extends React.Component {
  
  constructor() {
    super();
    this.state = {'keyword': '', 'idsSelected': [], 'showResults': true, 'page': 0};
  }
  
  componentWillMount() {
    const { projectSearch: { keyword, results } } = this.props;
    
    // try to fetch the project list if we already have a keyword - usually this can be the case for a shared map.
    if (keyword !== undefined && keyword !== "" && results.content === undefined) {
      this.doneTyping();
    }
  }
  
  validateState() {
    const length = this.props.projectSearch.keyword.length;
    const tiping = this.state.tiping;
    if (length > 0) {
      if (tiping) {
        return 'error';
      } else {
        return 'success';
      }
    }
  }
  
  validateSelection(id) {
    let selected = false;
    this.props.projectSearch.selected.map((it) => {
      if (it.id == id) {
        selected = true;
      }
    });
    if (!selected) {
      return 'selectable';
    } else {
      return 'selectable selected';
    }
  }
  
  handleChange(e) {
    let keyword = e.target.value;
    this.setState({tiping: true});
    this.props.onSetKeyword(keyword);
    clearTimeout(typingTimer);
    typingTimer = setTimeout(this.doneTyping.bind(this), doneTypingInterval);
  }
  
  doneTyping() {
    this.setState({tiping: false});
    let filters = collectValues(this.props.filters);
    Object.assign(filters, {'pt': this.props.projectSearch.keyword});
    this.props.onTriggerSearch(filters);
  }
  
  handleSelection(project) {
    this.props.onToggleSelection(project);
  }
  
  selectAllMatched() {
    this.props.onSelectAllMatched();
  }
  
  clearAllSelection() {
    this.props.onClearAll();
  }
  
  showSelected() {
    this.setState({'showResults': false, 'page': 0});
  }
  
  showResults() {
    this.setState({'showResults': true, 'page': 0});
  }
  
  applySelection() {
    const {projectSearch, filters} = this.props;
    let projectFilter = Object.assign({}, projectSearch, {applied: projectSearch.selected});//move selected to applied
    let filtersCollected = collectValues(filters, projectFilter);
    this.props.onFilterByProjects(filtersCollected);
  }
  
  getInput() {
    const { keyword } = this.props.projectSearch;
    
    return (<Input className={keyword.length == 0 ? 'keyword-input-empty' : 'keyword-input-filled'}
                   type="text"
                   value={keyword}
                   placeholder={translate('toolview.projectsearch.placeholder')}
                   bsStyle={this.validateState()}
                   bsSize="small" ref="keyword"
                   onChange={this.handleChange.bind(this)}/>)
  }
  
  getActions() {
    return (
      <div className="project-search-actions">
        <OverlayTrigger delayShow={1000} placement="top" overlay={(
          <Tooltip id="help.projectsearch.selectall">{translate('help.projectsearch.selectall')}</Tooltip>)}>
          <div className="action-item" onClick={this.selectAllMatched.bind(this)}>
            <div className="btn btn-xs btn-all"></div>
            <span>{translate('toolview.projectsearch.selectall')}</span></div>
        </OverlayTrigger>
        <span>/</span>
        {this.state.showResults ?
          <OverlayTrigger delayShow={1000} placement="top" overlay={(<Tooltip
            id="help.projectsearch.selectedresults">{translate('help.projectsearch.selectedresults')}</Tooltip>)}>
            <div className="action-item" onClick={this.showSelected.bind(this)}>{translate('toolview.projectsearch.selectedresults')}
              ({this.props.projectSearch.selected.length}) </div>
          </OverlayTrigger>
          :
          <OverlayTrigger delayShow={1000} placement="top" overlay={(
            <Tooltip id="help.projectsearch.searchresults">{translate('help.projectsearch.searchresults')}</Tooltip>)}>
            <div className="action-item" onClick={this.showResults.bind(this)}>{translate('toolview.projectsearch.searchresults')}</div>
          </OverlayTrigger>
        }
        <span>/</span>
        <OverlayTrigger delayShow={1000} placement="top" overlay={(
          <Tooltip id="help.projectsearch.apply">{translate('help.projectsearch.apply')}</Tooltip>)}>
          <div className="action-item" onClick={this.applySelection.bind(this)}>
            <div className="btn btn-xs btn-apply"></div>
            <span>{translate('toolview.projectsearch.apply')}</span></div>
        </OverlayTrigger>
        <span>/</span>
        <OverlayTrigger delayShow={1000} placement="top" overlay={(
          <Tooltip id="help.projectsearch.clearall">{translate('help.projectsearch.clearall')}</Tooltip>)}>
          <div className="action-item" onClick={this.clearAllSelection.bind(this)}>
            <div className="btn btn-xs btn-clear"></div>
            <span>{translate('toolview.projectsearch.clearall')}</span></div>
        </OverlayTrigger>
      </div>
    )
  }
  
  getNoSelection(items) {
    if (items && items.length == 0) {
      return <div>No selection</div>
    }
  }
  
  getLoading(isFetching) {
    if (isFetching) {
      return (<div className="message">Loading </div>)
    }
  }
  
  getRow(item) {
    return (
      <div className="row" key={item.id}>
        <div className="col1">
          <div className={this.validateSelection(item.id)} onClick={this.handleSelection.bind(this, item)}/>
        </div>
        <div className="col2">
          <div className="item-text">
            <ProjectLink {...item}/>
          </div>
        </div>
      </div>)
  }
  
  getContent(content, size) {
    if (content && content.length > 0) {
      const {page} = this.state;
      let subSet = content.slice(page * pageSize, (page + 1) * pageSize);
      
      return (
        <div>{subSet.map((item) => this.getRow(item))}</div>
      )
    }
  }
  
  handlePageChange(eventKey, items) {
    let totalPages = Math.ceil(items.length / pageSize);
    let pg = getActivePage(eventKey, totalPages, this.state.page);
    this.setState({'page': pg});
  }
  
  render() {
    const { showResults, page } = this.state;
    const { projectSearch, stats } = this.props;
    const { results, selected } = projectSearch;
    const { content, isFetching } = results;
    const items = (showResults ? content : selected) || [];
    const itemsPaginated = this.getContent(items);
    const loading = showResults ? this.getLoading(isFetching) : null;
    const noSelecion = !showResults ? this.getNoSelection(items) : null;
    
    return (
      <div id="ps-container" className="project-search">
        <div className="project-search-keyword">
          <h2>Search over {stats.getIn(["global", "data"]).regional.projectCount} results</h2>
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
            items={Math.ceil(items.length / pageSize)}
            activePage={page + 1}
            onSelect={(eventKey) => {
              this.handlePageChange(eventKey, items, page)
            }}/>
        </div>
      </div>
    );
  }
}


const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onSetKeyword: (keyword) => {
      dispatch(setKeyword(keyword));
    },
    
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
      dispatch(applyProjectSelected());
      dispatch(applyFilter(filters));
    }
  }
};

const mapStateToProps = (state, props) => {
  return {
    filters: state.filters.filterMain,
    language: state.language,
    projectSearch: state.projectSearch,
    stats: state.stats
  }
};

export default connect(mapStateToProps, mapDispatchToProps)(ProjectFilter);
