import {PropTypes} from 'react';
import {geoJson, latlng, marker, divIcon} from 'leaflet';
import {MapLayer} from 'react-leaflet';
import React from 'react';
import d3 from 'd3';
import { render, unmountComponentAtNode } from 'react-dom';
import { fetchPopupData } from '../../../actions/popup.js'
import { connect } from 'react-redux'
import {collectValues} from '../../../util/filterUtil';

/**
 * @author Sebas
 */
class D3Layer extends MapLayer {

  static propTypes = {
    higthligthStyleProvider: React.PropTypes.func,
  };


  constructor() {
    super();
  }

  componentWillMount() {
    super.componentWillMount();
    this.leafletElement = geoJson();
    this.svg = d3.select(this.props.map.getPanes().overlayPane).append("svg"); 
    this.svg.style("z-index",this.props.zIndex);
    //this.svg= d3.select(this.props.map._container).select("svg"),
    this.g = this.svg.append("g").attr("class", "leaflet-zoom-hide");
    this.props.map.on('moveend', this.mapmove.bind(this));
    this.mapmove();//trigger first update
  }

  componentDidUpdate(nextProps, nextState) {
    const {data, ...props} = this.props;
    this.mapmove();
  }

  componentWillUnmount() {
    //this.props.map.off('moveend');
    this.svg.remove();    
  }

  getValues(features){
    const valprop=this.props.valueProperty;
    return features.map(function(f) { return +f.properties[valprop]});
  }



  renderPaths(data){
    this.filed=this.props.valueProperty;
    this.minSize=this.props.minSize;
    this.maxSize=this.props.maxSize;
    this.sizeFactor=this.props.sizeFactor;
  
   var  map=this.props.map;

    // Use Leaflet to implement a D3 geometric transformation.
    function projectPoint(x, y) {
      var point = map.latLngToLayerPoint(new L.LatLng(y, x));
      this.stream.point(point.x, point.y);
    }

    var transform = d3.geo.transform({ point: projectPoint});
    this.path = d3.geo.path().projection(transform);

    this.path.pointRadius((d)=>{
      let size= this.props.map.getZoom()* this.sizeFactor * d.properties[this.filed];
      return (size < this.minSize)?this.minSize:(size>this.maxSize)?this.maxSize:size;
    });
    
    let features=data.features;//;this.filter(data.features);

    var shapes = this.g.selectAll("path").data(features);
    shapes.enter().append("path");
    shapes.exit().remove();
    shapes.attr("class", function(f) {return this.getClass(f);}.bind(this));
    shapes.attr("d", this.path);
    shapes.on("click",this.onClick.bind(this)); 
    this.setSvgSize(data);

  }


  setSvgSize(data){
    var s=this.maxSize*3; //s*2 to have the full size of the marker but we should also include borders so *3 just in case! not the best!
    var r=s/2;

    var bounds = this.path.bounds(data),topLeft = bounds[0],bottomRight = bounds[1];
    var width=(bottomRight[0] - topLeft[0])+s;
    var height=bottomRight[1] - topLeft[1]+s;
    var left= topLeft[0]-r + "px";
    var top=topLeft[1]-r + "px";
    var translateX=-(topLeft[0]-r) ;
    var translateY=-(topLeft[1]-r)   ;
    this.svg.attr("width",width).attr("height",height ).style("left",left).style("top", top);
    this.g.attr("transform", "translate(" + translateX + "," + translateY+ ")");
  }

  filter(data){
    var bounds=this.props.map.getBounds();
    const filtered = data.filter((f)=>bounds.contains(L.geoJson(f).getBounds())).sort((f)=>{f.properties.projectCount})
    console.log('Removed =>'+(data.length - filtered.length));
    return filtered;

  }


  onClick(properties){
    L.DomEvent._getEvent().stopPropagation();
    this.getPopupContent(properties);
  }


  mapmove(e) {
    if (this.props.data && this.props.data.features){
      this.values=this.getValues(this.props.data.features);//isolate features values 
      this.renderPaths(this.props.data);
    } else {
      console.log('Dataset is empty');
    }
  }

  getPopupContent(feature) {
    this.setState({popupFeature: feature});
    let filters = collectValues(this.props.filters, this.props.projectSearch);
    Object.assign(filters, {'lo': [feature.properties.id]});
    this.props.onGetPopupData(filters);
  }

  renderPopupContent(charts) {
    if (!this.state){
      return null;
    }
    let feature = this.state.popupFeature;
    let popup = L.popup({maxWidth:"400", minWidth:"400", maxHeight:"280"})
    .setLatLng(L.latLng(feature.geometry.coordinates[1],feature.geometry.coordinates[0]))
    .openOn(this.props.map);
    Object.assign(feature, {'charts': charts, 'fundingType': this.props.fundingType});
    if (this.props.children) {
      render(React.cloneElement(React.Children.only(this.props.children), feature), popup._contentNode);
      popup._updateLayout();
      popup._updatePosition();
      popup._adjustPan();
    } 
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.charts && this.props.charts && nextProps.charts.lastUpdate && (nextProps.charts.lastUpdate != this.props.charts.lastUpdate)){
        this.renderPopupContent(nextProps.charts)
    }
  }

  getClass(d){
    
    if (this.props.cssProvider){
      if (!this.cssProvider)
        this.cssProvider=new this.props.cssProvider(this.values,this.props.thresholds);
      var className=this.props.classes+this.cssProvider.getCssClass(d.properties[this.props.valueProperty]);
      
      return className;  
    }

  }

  render() {
    return this.renderChildrenWithProps({
      popupContainer: this.leafletElement,
    });
  }

}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onGetPopupData: (filters) => {
      dispatch(fetchPopupData(filters));
    }
  }
}

const mapStateToProps = (state, props) => {
  return {
    fundingType: state.settings.fundingType,
    charts: state.popup,
    filters: state.filters.filterMain,
    projectSearch: state.projectSearch
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(D3Layer);;