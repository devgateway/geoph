import React from 'react';
import { connect } from 'react-redux';
import { Tooltip, OverlayTrigger } from 'react-bootstrap';
import { savedMapsChange } from '../../../actions/dashboard';

require("./savedMaps.scss");

class SavedMaps extends React.Component {
  static propTypes = {
    savedMaps:        React.PropTypes.array.isRequired,
    savedMapsChange:  React.PropTypes.func.isRequired
  };
  
  render() {
    const { savedMaps, savedMapsChange } = this.props;
    
    return (<div className="saved-maps">
        {
          savedMaps.map((map, index) => {
            return (
              <div className="saved-maps-item control control--radio" key={map.id}>
                <input checked={map.selected || false} type="radio" id={"radio_" + map.id}
                       value={map.name}
                       onChange={() => {
                         const selected = map.selected;
                         if (!selected) {
                           savedMapsChange(index, map.key);
                         }
                       }}/>
                
                <OverlayTrigger delayShow={1000} placement="bottom" overlay={<Tooltip id={map.id}>{map.description}</Tooltip>}>
                  <label htmlFor={"radio_" + map.id} className={map.selected ? "selected" : ""}>
                    <img src={`data:image/png;base64, ${map.base64preview}`}/>
                    &nbsp;{map.name}
                  </label>
                </OverlayTrigger>
                
                <div className="control__indicator"></div>
              </div>
            
            )
          })
        }
      </div>
    )
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    savedMapsChange: (index, key) => dispatch(savedMapsChange(index, key))
  }
};

const stateToProps = (state, props) => {
  const { results } = state.dashboard.toJS();
  
  return {
    savedMaps: results
  }
};

export default connect(stateToProps, mapDispatchToProps)(SavedMaps);
