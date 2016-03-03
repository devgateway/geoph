import React from 'react';
import ReactDOM from 'react/react-dom';
import Map from 'app/components/esri/map';
import SceneView from 'app/components/esri/sceneView';
import MapView from 'app/components/esri/mapview';

import Zoom from 'app/components/esri/widgets/zoom';
import Basemaptoggle  from 'app/components/esri/widgets/basemaptoggle';

const MapComponent = React.createClass({


render() {
	return (
    <div>
    <Map className="map"  basemap="streets">
         <GraphicsLayer>
                <Graphic geometry={point} symbol={markerSymbol}/>
                <Graphic geometry={point1} symbol={markerSymbol}/>
                <Graphic geometry={point2} symbol={markerSymbol}/>
            </GraphicsLayer>

      <SceneView center={[-112, 38]} zoom={1}></SceneView>
    </Map>
    </div>
    )
}
});

export default MapComponent;
