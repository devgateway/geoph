import React from 'react';
import { Tooltip, OverlayTrigger } from 'react-bootstrap';
import translate from '../../util/translate.js';

require('./expandableControl.scss');

const Expandable = React.createClass({
  
  getInitialState() {
    return {'expanded': this.props.defaultExpanded || false};
  },
  
  toggleView() {
    this.setState({'expanded': !this.state.expanded});
  },
  
  render() {
    const { expanded } = this.state;
    const { title, children, tooltipText } = this.props;
    return (
      <div className={(expanded == true) ? "expandable-control" : "expandable-control collapsed"}>
        
        <OverlayTrigger delayShow={1000} placement="top"
                        overlay={(<Tooltip id={tooltipText}>{translate(tooltipText)}</Tooltip>)}>
          <div className="title" onClick={this.toggleView}>
            {title}
            <div className="toggle">
              <span>{(expanded == true) ? '–' : '+'}</span>
            </div>
          </div>
        </OverlayTrigger>
        
        {(this.state.expanded === true) ?
          <div className="content">
            {children}
          </div>
          : null}
      </div>
    );
  }
});

export default Expandable;