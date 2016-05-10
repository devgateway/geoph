import React from 'react';
import { Link  } from 'react-router';
import  Popup from './projectLayerPopup';

export default class MiddlePopup extends React.Component {

  constructor() {
    super();
  }

  render() {
    return (
      <div>
        <Popup />
      </div>
      )
  }
}
