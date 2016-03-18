import React from 'react';
import ReactDOM from 'react/react-dom';
import EsriGraphicsLayer from 'esri/layers/GraphicsLayer';
import Layer from 'app/components/esri/layers/Layer';

import SimpleMarkerSymbol from 'esri/symbols/SimpleMarkerSymbol';
import SimpleLineSymbol   from 'esri/symbols/SimpleLineSymbol';

import TextSymbol   from 'esri/symbols/TextSymbol';

import PopupTemplate from 'esri/PopupTemplate';

import Point from 'esri/geometry/Point';

import SpatialReference from 'esri/geometry/SpatialReference';

import Graphic from "esri/Graphic";
import domReady from "dojo/domReady!";


      //Create a symbol for drawing the point
      var markerSymbol = new SimpleMarkerSymbol({
        size:34,
        color: [255, 193, 7,0.8],
        outline: new SimpleLineSymbol({
          style:'short-dash',
          color: [255, 152, 0, 0.6],
          width: 3
        })
      });

      const defaultSpatialReference = new SpatialReference({wkid: 4326});

      class GeoJsonLayer extends Layer{

       componentWillMount() {
         this.layer = new EsriGraphicsLayer({popupTemplate: new PopupTemplate({
          title: "{Name}",
          content: "{*}"
        })});
       }


       componentWillUpdate(nextProps, nextState) {
         this.loadGeoJson(nextProps.data);
       }

       loadGeoJson(json){
        const features=Terraformer.ArcGIS.convert(json);
        const graphics=features.map((f)=>{
          this.layer.add(this.createGraphic(f));
        });

        this.props.map.add(this.layer);
      }



      createGraphic(arcgisJson) {
        const g=Graphic;
        let graphic= g.fromJSON(arcgisJson);
        graphic.symbol=markerSymbol;
        /*graphic.popupTemplate= new PopupTemplate({
          title: "{Name}",
          content: "{*}"
        })*/
        return graphic;
      }
    }


    export default GeoJsonLayer;
