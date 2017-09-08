import React from 'react';
import onClickOutside from 'react-onclickoutside';
import { Tooltip, OverlayTrigger } from 'react-bootstrap';
import translate from '../../util/translate';

class MenuItem extends React.Component {
  
  handleClickOutside(evt) {
    const { id } = this.props;
    const active = this.props[id];
    
    if (active && id !== "compare") { // don't deactivate the compare when clicking somewhere else.
      this.props.onDeactivate(this.props.id);
    }
  }
  
  handleClick() {
    const { id } = this.props;
    const active = this.props[id];
    
    if (!active) {
      this.props.onActivate(this.props.id);
    } else {
      if (this.props.id !== "compare") { // don't deactivate the compare
        this.props.onDeactivate(this.props.id);
      }
    }
  }
  
  render() {
    const {id, label, className, onDeactivate, tooltipKey} = this.props;
    const active = this.props[id];
    
    const tooltip = (
      <Tooltip id={tooltipKey}>{translate(tooltipKey)}</Tooltip>
    );
    
    return (
      <li className={active ? `active ${className}` : className}>
        <OverlayTrigger delayShow={1000} placement="bottom" overlay={tooltip}>
          <div className="link" onClick={this.handleClick.bind(this)}>
            <div className={"options-icons " + className}></div>
            <span>{label}</span>
          </div>
        </OverlayTrigger>
        {
          React.Children.map(this.props.children, (element) => {
            return React.cloneElement(element, {
              visible: active,
              onHide: () => {
                onDeactivate(id);
              }
            });
          })
        }
      </li>
    )
  }
}

export default onClickOutside(MenuItem);
