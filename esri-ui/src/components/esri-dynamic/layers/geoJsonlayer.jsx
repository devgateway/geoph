import React from 'react';
import ReactDOM from 'react/react-dom';
import EsriGraphicsLayer from 'esri/layers/GraphicsLayer';
import Layer from 'app/components/esri/layers/Layer';

import SimpleMarkerSymbol from 'esri/symbols/SimpleMarkerSymbol';
import SimpleLineSymbol   from 'esri/symbols/SimpleLineSymbol';

import TextSymbol   from 'esri/symbols/TextSymbol';


import Point from 'esri/geometry/Point';

import SpatialReference from 'esri/geometry/SpatialReference';

import Graphic from "esri/Graphic";
import domReady from "dojo/domReady!";

      //Create a symbol for drawing the point
      var markerSymbol = new SimpleMarkerSymbol({
        color: [226, 119, 40],
        outline: new SimpleLineSymbol({
          color: [255, 255, 255],
          width: 2
      })
    });

      var label=new TextSymbol({
        text:'S',
        color:[0, 255, 123, 0.7]
      });

      var point = new Point({
        longitude: -49.97,
        latitude: 41.73
    });




      const defaultSpatialReference = new SpatialReference({wkid: 4326});

      class GeoJsonLayer extends Layer{


        componentWillMount() {
            this.layer = new EsriGraphicsLayer();
            this.props.map.add(this.layer);


        }


        componentWillUpdate(nextProps, nextState) {
         this.loadGeoJson(nextProps.data);
     }

     loadGeoJson(json){
        debugger;
        const features=Terraformer.ArcGIS.convert(json);
        
        const graphics=features.map((f)=>{
            this.layer.add(this.createGraphic(f));
        });

    }

 

    createGraphic(arcgisJson) {
        const g=Graphic;
        let graphic= g.fromJSON(arcgisJson);
        graphic.symbol=markerSymbol;
        return graphic;
    }
}


export default GeoJsonLayer;
