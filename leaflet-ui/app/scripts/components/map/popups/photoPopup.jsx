import React from 'react';
import { connect } from 'react-redux'
import onClickOutside from 'react-onclickoutside'

require('./projectLayerPopup.scss');

class PhotoPopup extends React.Component {
  handleClickOutside(evt) {
    if (this.props.onClosePopup) {
      this.props.onClosePopup();
    }
  };
  
  render() {
    const {feature} = this.props;
    if (!feature) {
      return null;
    }
    
    const { properties } = feature;
    const { urls, project_id, project_title } = properties;
    
    return (
      <div className="photo-popup-container">
        <div className="popup-title">
          <h2>{project_id} {project_title}</h2>
        </div>
        <div className="images">
          {urls.map(url => {
            return <div><img width="300" src={url}/></div>
          })}
        </div>
      
      </div>
    )
  }
}

const mapStateToProps = (state, props) => {
  return {
    language: state.language
  }
};

export default connect(mapStateToProps)(onClickOutside(PhotoPopup));

