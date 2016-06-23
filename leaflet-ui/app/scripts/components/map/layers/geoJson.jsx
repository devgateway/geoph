import {PropTypes} from 'react';
import {geoJson,latlng,marker,divIcon} from 'leaflet';
import {Path} from 'react-leaflet';
import React from 'react';


/**
 * This class implements a geojson layer which automatically recreates him self everytime the geosjon data changes
 */
 export default class GeoJson extends Path {

  constructor() {
    super();
  }

  static propTypes = {
    autoZoom: React.PropTypes.bool
  };


  componentWillMount() {
    super.componentWillMount();
    this.props.map.on('zoomend',function(){
      console.log(this.getBounds());
    })
    this._create();

  }

  /**
   * return the possible holder of the layer
   */
   _parent() {
    return (this.props.layerGroup || this.props.layerControl || this.props.map);
  }


  /**
   * Remove this layer from parent
   */
   _remove() {
    this._parent().removeLayer(this.leafletElement);
  }

  _add() {
    this._parent().addLayer(this.leafletElement, this.props.name);
  }

  _create() {

    const { data, ...props } = this.props;

    const geoJsonOptions=Object.assign(props, {
      'pointToLayer': this.pointToLayer.bind(this),
      'onEachFeature': this.onEachFeature.bind(this),
      'style': this.style.bind(this)
    });

    this.leafletElement = geoJson(data, geoJsonOptions);

    if (this.props.autoZoom && data!=null){
      this.props.map.fitBounds(this.leafletElement.getBounds());
    }
    this._add();
  }

  _update() {
    this._remove();
    this._create();
  }

  componentWillUnmount() {
    super.componentWillUnmount();
    this._remove(); //remove this layer while unmounting the component 
  }

  componentDidUpdate(prevProps) {
    const {data, map, ...props} = this.props;
    
    if (this.props.data != prevProps.data) { //we should do a better work to detect data changes 
      this._update();
    }

    this.setStyleIfChanged(prevProps, this.props);
  }


  style() {
  }

  onEachFeature(feature, layer) {
  }



  pointToLayer(feature, latlng) {
    let icon = divIcon({
      iconSize: [30, 30],
      className: `marker location-marker size${feature.properties.projectCount}`,
      html: `<div class='text'>${feature.properties.projectCount}</div>`
    });
    let mark = marker(latlng, {
      icon: icon
    });
    return mark
  }

  onEachFeature(feature, layer) {
    layer.on({
      click: this.onFeatureClick.bind(this)
    });
  }

  onFeatureClick(e) {
    if (this.props.onFeatureClick) {
      this.props.onFeatureClick(e);
    }
  }




}