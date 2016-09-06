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

  render() {
    const {legends = [], name, keyName}=this.props;
    return (
      <div className=''>
        <div className='legend-layer-name'>
          {keyName?<Message prefix={prefix} k={keyName}/>:<span>{name}</span>}
        </div>
        <div className='legend-list'>
          {legends.map((legend)=>{
            return(
              <div className='legend-item' key={legend.cls}>
                <div className={legend.cls}/>
                <div className='legend-label'>{legend.label}</div>
              </div>
            )
          })}
        </div>
      </div>
    )
  }
}

class Legends extends React.Component {

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
            {layersVisible.map((layer)=>{
              const {legends, name, keyName} = layer;
              return(
                <LegendList {...layer} key={layer.id}/>
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
  };
}

export default connect(stateToProps,mapDispatchToProps)(Legends);

