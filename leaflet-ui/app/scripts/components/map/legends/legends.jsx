import React from 'react';
import {toggleLegendsView} from '../../../actions/map'
import { connect } from 'react-redux'
import translate from '../../../util/translate.js';
import {Message} from '../../lan/'
import {getVisibles} from '../../../util/layersUtil.js';
import {Tooltip, OverlayTrigger} from 'react-bootstrap';
const prefix="toolview.layers";

require('./legends.scss');

class LegendList extends React.Component {

  toggleView(){
    const {onToggleView, id} = this.props;
    onToggleView(id);
  }

  render() {
    const {legends = [], name, keyName, expanded, onToggleView, id}=this.props;
    return (
      <div className=''>
        <div className='legend-layer-name' onClick={this.toggleView.bind(this)}>
         <div className="name-container">
          {keyName?<Message prefix={prefix} k={keyName}/>:<span>{name}</span>}
          </div>
          <div className='legend-collapse' onClick={this.toggleView.bind(this)}>
          {expanded? "" : "+"}
        </div>
        </div>
        
      {expanded?  <div className='legend-list'>
          {expanded?
            legends.map((legend)=>{
              return(
                <div className='legend-item' key={legend.cls}>
                  <div className={legend.cls}/>
                  <div className='legend-label'>{legend.labelKey? translate(legend.labelKey) : legend.label}</div>
                </div>
              )
            })
          : null}
        </div>:null}        
      </div>
    )
  }
}

class Legends extends React.Component {

  constructor() {
      super();
      this.state = {};
  }

  toggleView(id){
    this.setState({onView: id});
  }

  render() {
    const {map, layers}=this.props;
    let layersVisible = getVisibles(layers).toJS();
    const  visible = map.get('legends').get('visible');
    return (
      <div className='legends-container'>
        <OverlayTrigger delayShow={1000} placement="top" overlay={(<Tooltip id="help.legends">{translate('help.legends')}</Tooltip>)}>
          <div className='show-legends-button' onClick={this.props.onToggleView}>
            <div className='show-legends-button-icon' />
          </div>
        </OverlayTrigger>
        {visible?
          <div className='legends-content'>
            {layersVisible.map((layer, idx)=>{
              const {legends, name, keyName, id} = layer;
              const {onView} = this.state;
              const expanded = (!onView&&idx==0)||(onView==id)? true : false
              return(
                <LegendList {...layer} key={id} onToggleView={this.toggleView.bind(this)} expanded={(!onView&&idx==0)||(onView==id)}/>
              )
            })}
          </div>
        : null}
      </div>
    )
  }
}


const mapDispatchToProps=(dispatch,ownProps)=>{
  return {
    onToggleView: () => {
      dispatch(toggleLegendsView());
    }
  }
}

const stateToProps = (state,props) => { 
  return {
    map: state.map,
    language: state.language
  };
}

export default connect(stateToProps,mapDispatchToProps)(Legends);

