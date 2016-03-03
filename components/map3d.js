define(['exports', 'react', 'react/react-dom', 'app/components/esri/map', 'app/components/esri/sceneView', 'app/components/esri/mapview', 'app/components/esri/widgets/zoom', 'app/components/esri/widgets/basemaptoggle'], function (exports, _react, _reactDom, _map, _sceneView, _mapview, _zoom, _basemaptoggle) {
       'use strict';

       Object.defineProperty(exports, "__esModule", {
              value: true
       });

       var _react2 = _interopRequireDefault(_react);

       var _reactDom2 = _interopRequireDefault(_reactDom);

       var _map2 = _interopRequireDefault(_map);

       var _sceneView2 = _interopRequireDefault(_sceneView);

       var _mapview2 = _interopRequireDefault(_mapview);

       var _zoom2 = _interopRequireDefault(_zoom);

       var _basemaptoggle2 = _interopRequireDefault(_basemaptoggle);

       function _interopRequireDefault(obj) {
              return obj && obj.__esModule ? obj : {
                     default: obj
              };
       }

       var MapComponent = _react2.default.createClass({
              displayName: 'MapComponent',
              render: function render() {
                     return _react2.default.createElement(
                            'div',
                            null,
                            _react2.default.createElement(
                                   _map2.default,
                                   { className: 'map', basemap: 'streets' },
                                   _react2.default.createElement(
                                          GraphicsLayer,
                                          null,
                                          _react2.default.createElement(Graphic, { geometry: point, symbol: markerSymbol }),
                                          _react2.default.createElement(Graphic, { geometry: point1, symbol: markerSymbol }),
                                          _react2.default.createElement(Graphic, { geometry: point2, symbol: markerSymbol })
                                   ),
                                   _react2.default.createElement(_sceneView2.default, { center: [-112, 38], zoom: 1 })
                            )
                     );
              }
       });

       exports.default = MapComponent;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXG1hcDNkLmpzeCJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFTQSxXQUFNLGVBQWUsZ0JBQU0sV0FBTixDQUFrQjs7QUFHdkMsd0NBQVM7QUFDUiw0QkFDRzs7OzRCQUNBOztxQ0FBSyxXQUFVLEtBQVYsRUFBaUIsU0FBUSxTQUFSLEVBQXRCO21DQUNLO0FBQUMsdURBQUQ7OzBDQUNPLDhCQUFDLE9BQUQsSUFBUyxVQUFVLEtBQVYsRUFBaUIsUUFBUSxZQUFSLEVBQTFCLENBRFA7MENBRU8sOEJBQUMsT0FBRCxJQUFTLFVBQVUsTUFBVixFQUFrQixRQUFRLFlBQVIsRUFBM0IsQ0FGUDswQ0FHTyw4QkFBQyxPQUFELElBQVMsVUFBVSxNQUFWLEVBQWtCLFFBQVEsWUFBUixFQUEzQixDQUhQO29DQURMO21DQU9FLHFEQUFXLFFBQVEsQ0FBQyxDQUFDLEdBQUQsRUFBTSxFQUFQLENBQVIsRUFBb0IsTUFBTSxDQUFOLEVBQS9CLENBUEY7NkJBREE7c0JBREgsQ0FEUTtlQUg4QjtRQUFsQixDQUFmOzt5QkFvQlMiLCJmaWxlIjoiY29tcG9uZW50c1xcbWFwM2QuanN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IFJlYWN0RE9NIGZyb20gJ3JlYWN0L3JlYWN0LWRvbSc7XHJcbmltcG9ydCBNYXAgZnJvbSAnYXBwL2NvbXBvbmVudHMvZXNyaS9tYXAnO1xyXG5pbXBvcnQgU2NlbmVWaWV3IGZyb20gJ2FwcC9jb21wb25lbnRzL2Vzcmkvc2NlbmVWaWV3JztcclxuaW1wb3J0IE1hcFZpZXcgZnJvbSAnYXBwL2NvbXBvbmVudHMvZXNyaS9tYXB2aWV3JztcclxuXHJcbmltcG9ydCBab29tIGZyb20gJ2FwcC9jb21wb25lbnRzL2Vzcmkvd2lkZ2V0cy96b29tJztcclxuaW1wb3J0IEJhc2VtYXB0b2dnbGUgIGZyb20gJ2FwcC9jb21wb25lbnRzL2Vzcmkvd2lkZ2V0cy9iYXNlbWFwdG9nZ2xlJztcclxuXHJcbmNvbnN0IE1hcENvbXBvbmVudCA9IFJlYWN0LmNyZWF0ZUNsYXNzKHtcclxuXHJcblxyXG5yZW5kZXIoKSB7XHJcblx0cmV0dXJuIChcclxuICAgIDxkaXY+XHJcbiAgICA8TWFwIGNsYXNzTmFtZT1cIm1hcFwiICBiYXNlbWFwPVwic3RyZWV0c1wiPlxyXG4gICAgICAgICA8R3JhcGhpY3NMYXllcj5cclxuICAgICAgICAgICAgICAgIDxHcmFwaGljIGdlb21ldHJ5PXtwb2ludH0gc3ltYm9sPXttYXJrZXJTeW1ib2x9Lz5cclxuICAgICAgICAgICAgICAgIDxHcmFwaGljIGdlb21ldHJ5PXtwb2ludDF9IHN5bWJvbD17bWFya2VyU3ltYm9sfS8+XHJcbiAgICAgICAgICAgICAgICA8R3JhcGhpYyBnZW9tZXRyeT17cG9pbnQyfSBzeW1ib2w9e21hcmtlclN5bWJvbH0vPlxyXG4gICAgICAgICAgICA8L0dyYXBoaWNzTGF5ZXI+XHJcblxyXG4gICAgICA8U2NlbmVWaWV3IGNlbnRlcj17Wy0xMTIsIDM4XX0gem9vbT17MX0+PC9TY2VuZVZpZXc+XHJcbiAgICA8L01hcD5cclxuICAgIDwvZGl2PlxyXG4gICAgKVxyXG59XHJcbn0pO1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgTWFwQ29tcG9uZW50O1xyXG4iXX0=