define(['exports', 'react', 'react/react-dom', 'app/components/esri/map', 'app/components/esri/sceneView', 'app/components/esri/mapview', 'app/components/esri/widgets/zoom', 'app/components/esri/widgets/basemaptoggle', 'app/components/esri/layers/graphicsLayer', 'app/components/esri/graphic', 'esri/geometry/Point', 'esri/symbols/SimpleMarkerSymbol', 'esri/symbols/SimpleLineSymbol'], function (exports, _react, _reactDom, _map, _sceneView, _mapview, _zoom, _basemaptoggle, _graphicsLayer, _graphic, _Point, _SimpleMarkerSymbol, _SimpleLineSymbol) {
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

  var _graphicsLayer2 = _interopRequireDefault(_graphicsLayer);

  var _graphic2 = _interopRequireDefault(_graphic);

  var _Point2 = _interopRequireDefault(_Point);

  var _SimpleMarkerSymbol2 = _interopRequireDefault(_SimpleMarkerSymbol);

  var _SimpleLineSymbol2 = _interopRequireDefault(_SimpleLineSymbol);

  function _interopRequireDefault(obj) {
    return obj && obj.__esModule ? obj : {
      default: obj
    };
  }

  var point = new _Point2.default({
    longitude: -49.97,
    latitude: 41.73
  });

  var point1 = new _Point2.default({
    longitude: -48.97,
    latitude: 19.73
  });

  var point2 = new _Point2.default({
    longitude: -39.97,
    latitude: 45.73
  });

  var markerSymbol = new _SimpleMarkerSymbol2.default({
    color: [226, 119, 40],
    outline: new _SimpleLineSymbol2.default({
      color: [255, 255, 255],
      width: 2
    })
  });

  var MapComponent = _react2.default.createClass({
    displayName: 'MapComponent',
    getInitialState: function getInitialState() {
      return { mode: '3d' };
    },
    toggle: function toggle() {
      if (this.state.mode == '3d') {
        this.setState({ mode: '2d' });
      } else {
        this.setState({ mode: '3d' });
      }
    },
    render: function render() {
      if (this.state.mode == '3d') {
        return this.render3d();
      } else {
        return this.render2d();
      }
    },
    render3d: function render3d() {

      return _react2.default.createElement(
        'div',
        null,
        _react2.default.createElement(
          'a',
          { href: 'javascript:void()', onClick: this.toggle },
          'Toggle view'
        ),
        _react2.default.createElement(
          'div',
          { id: 'map3d' },
          _react2.default.createElement(
            _map2.default,
            { className: 'map', basemap: 'streets' },
            _react2.default.createElement(
              _graphicsLayer2.default,
              null,
              _react2.default.createElement(_graphic2.default, { geometry: point, symbol: markerSymbol }),
              _react2.default.createElement(_graphic2.default, { geometry: point1, symbol: markerSymbol }),
              _react2.default.createElement(_graphic2.default, { geometry: point2, symbol: markerSymbol })
            ),
            _react2.default.createElement(
              _sceneView2.default,
              { center: [-48, 19], zoom: 3 },
              _react2.default.createElement(_basemaptoggle2.default, null),
              _react2.default.createElement(_zoom2.default, null)
            )
          )
        )
      );
    },
    render2d: function render2d() {

      return _react2.default.createElement(
        'div',
        { id: 'map2d' },
        _react2.default.createElement(
          'a',
          { href: 'javascript:void()', onClick: this.toggle },
          'Toggle view'
        ),
        _react2.default.createElement(
          _map2.default,
          { className: 'map', basemap: 'streets' },
          _react2.default.createElement(
            _graphicsLayer2.default,
            null,
            _react2.default.createElement(_graphic2.default, { geometry: point, symbol: markerSymbol }),
            _react2.default.createElement(_graphic2.default, { geometry: point1, symbol: markerSymbol }),
            _react2.default.createElement(_graphic2.default, { geometry: point2, symbol: markerSymbol })
          ),
          _react2.default.createElement(
            _mapview2.default,
            { center: [-48, 19], zoom: 3 },
            _react2.default.createElement(_basemaptoggle2.default, null),
            _react2.default.createElement(_zoom2.default, null)
          )
        )
      );
    }
  });

  exports.default = MapComponent;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXG1hcDJkLmpzeCJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7O0FBZ0JBLE1BQU0sUUFBUSxvQkFBVTtBQUN0QixlQUFXLENBQUMsS0FBRDtBQUNYLGNBQVUsS0FBVjtHQUZZLENBQVI7O0FBS04sTUFBTSxTQUFTLG9CQUFVO0FBQ3ZCLGVBQVcsQ0FBQyxLQUFEO0FBQ1gsY0FBVSxLQUFWO0dBRmEsQ0FBVDs7QUFNTixNQUFNLFNBQVMsb0JBQVU7QUFDdkIsZUFBVyxDQUFDLEtBQUQ7QUFDWCxjQUFVLEtBQVY7R0FGYSxDQUFUOztBQU1OLE1BQU0sZUFBZSxpQ0FBdUI7QUFDMUMsV0FBTyxDQUFDLEdBQUQsRUFBTSxHQUFOLEVBQVcsRUFBWCxDQUFQO0FBQ0EsYUFBUywrQkFBcUI7QUFDNUIsYUFBTyxDQUFDLEdBQUQsRUFBTSxHQUFOLEVBQVcsR0FBWCxDQUFQO0FBQ0EsYUFBTyxDQUFQO0tBRk8sQ0FBVDtHQUZtQixDQUFmOztBQVFOLE1BQU0sZUFBZSxnQkFBTSxXQUFOLENBQWtCOztBQUd0QyxnREFBaUI7QUFDaEIsYUFBTyxFQUFDLE1BQUssSUFBTCxFQUFSLENBRGdCO0tBSHFCO0FBT3ZDLDhCQUFRO0FBQ1AsVUFBSSxLQUFLLEtBQUwsQ0FBVyxJQUFYLElBQWlCLElBQWpCLEVBQXNCO0FBQ3pCLGFBQUssUUFBTCxDQUFjLEVBQUMsTUFBSyxJQUFMLEVBQWYsRUFEeUI7T0FBMUIsTUFFSTtBQUNILGFBQUssUUFBTCxDQUFjLEVBQUMsTUFBSyxJQUFMLEVBQWYsRUFERztPQUZKO0tBUnNDO0FBZXZDLDhCQUFRO0FBQ04sVUFBSSxLQUFLLEtBQUwsQ0FBVyxJQUFYLElBQWlCLElBQWpCLEVBQXNCO0FBQ3pCLGVBQU8sS0FBSyxRQUFMLEVBQVAsQ0FEeUI7T0FBMUIsTUFFSTtBQUNKLGVBQU8sS0FBSyxRQUFMLEVBQVAsQ0FESTtPQUZKO0tBaEJxQztBQXVCdkMsa0NBQVc7O0FBRVYsYUFDQzs7O1FBQ0E7O1lBQUcsTUFBSyxtQkFBTCxFQUF5QixTQUFTLEtBQUssTUFBTCxFQUFyQzs7U0FEQTtRQUVBOztZQUFLLElBQUcsT0FBSCxFQUFMO1VBRUE7O2NBQUssV0FBVSxLQUFWLEVBQWlCLFNBQVEsU0FBUixFQUF0QjtZQUNBOzs7Y0FDQSxtREFBUyxVQUFVLEtBQVYsRUFBaUIsUUFBUSxZQUFSLEVBQTFCLENBREE7Y0FFQSxtREFBUyxVQUFVLE1BQVYsRUFBa0IsUUFBUSxZQUFSLEVBQTNCLENBRkE7Y0FHQSxtREFBUyxVQUFVLE1BQVYsRUFBa0IsUUFBUSxZQUFSLEVBQTNCLENBSEE7YUFEQTtZQU9BOztnQkFBVyxRQUFRLENBQUMsQ0FBQyxFQUFELEVBQUssRUFBTixDQUFSLEVBQW1CLE1BQU0sQ0FBTixFQUE5QjtjQUNBLDREQURBO2NBRUEsbURBRkE7YUFQQTtXQUZBO1NBRkE7T0FERCxDQUZVO0tBdkI0QjtBQWdEdkMsa0NBQVc7O0FBRVYsYUFDQzs7VUFBSyxJQUFHLE9BQUgsRUFBTDtRQUNBOztZQUFHLE1BQUssbUJBQUwsRUFBeUIsU0FBUyxLQUFLLE1BQUwsRUFBckM7O1NBREE7UUFFQTs7WUFBSyxXQUFVLEtBQVYsRUFBaUIsU0FBUSxTQUFSLEVBQXRCO1VBQ0E7OztZQUNBLG1EQUFTLFVBQVUsS0FBVixFQUFpQixRQUFRLFlBQVIsRUFBMUIsQ0FEQTtZQUVBLG1EQUFTLFVBQVUsTUFBVixFQUFrQixRQUFRLFlBQVIsRUFBM0IsQ0FGQTtZQUdBLG1EQUFTLFVBQVUsTUFBVixFQUFrQixRQUFRLFlBQVIsRUFBM0IsQ0FIQTtXQURBO1VBT0E7O2NBQVMsUUFBUSxDQUFDLENBQUMsRUFBRCxFQUFLLEVBQU4sQ0FBUixFQUFtQixNQUFNLENBQU4sRUFBNUI7WUFDQSw0REFEQTtZQUVBLG1EQUZBO1dBUEE7U0FGQTtPQURELENBRlU7S0FoRDRCO0dBQWxCLENBQWY7O29CQXdFUyIsImZpbGUiOiJjb21wb25lbnRzXFxtYXAyZC5qc3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgUmVhY3RET00gZnJvbSAncmVhY3QvcmVhY3QtZG9tJztcclxuaW1wb3J0IE1hcCBmcm9tICdhcHAvY29tcG9uZW50cy9lc3JpL21hcCc7XHJcbmltcG9ydCBTY2VuZVZpZXcgZnJvbSAnYXBwL2NvbXBvbmVudHMvZXNyaS9zY2VuZVZpZXcnO1xyXG5pbXBvcnQgTWFwVmlldyBmcm9tICdhcHAvY29tcG9uZW50cy9lc3JpL21hcHZpZXcnO1xyXG5cclxuaW1wb3J0IFpvb20gZnJvbSAnYXBwL2NvbXBvbmVudHMvZXNyaS93aWRnZXRzL3pvb20nO1xyXG5pbXBvcnQgQmFzZU1hcFRvZ2dsZSBmcm9tICdhcHAvY29tcG9uZW50cy9lc3JpL3dpZGdldHMvYmFzZW1hcHRvZ2dsZSc7XHJcbmltcG9ydCBHcmFwaGljc0xheWVyIGZyb20gJ2FwcC9jb21wb25lbnRzL2VzcmkvbGF5ZXJzL2dyYXBoaWNzTGF5ZXInO1xyXG5pbXBvcnQgR3JhcGhpYyBmcm9tICdhcHAvY29tcG9uZW50cy9lc3JpL2dyYXBoaWMnO1xyXG5cclxuaW1wb3J0IFBvaW50IGZyb20gXCJlc3JpL2dlb21ldHJ5L1BvaW50XCI7XHJcbmltcG9ydCBTaW1wbGVNYXJrZXJTeW1ib2wgZnJvbSBcImVzcmkvc3ltYm9scy9TaW1wbGVNYXJrZXJTeW1ib2xcIjtcclxuaW1wb3J0IFNpbXBsZUxpbmVTeW1ib2wgZnJvbSBcImVzcmkvc3ltYm9scy9TaW1wbGVMaW5lU3ltYm9sXCI7XHJcblxyXG5cclxuY29uc3QgcG9pbnQgPSBuZXcgUG9pbnQoe1xyXG4gIGxvbmdpdHVkZTogLTQ5Ljk3LFxyXG4gIGxhdGl0dWRlOiA0MS43M1xyXG59KTtcclxuXHJcbmNvbnN0IHBvaW50MSA9IG5ldyBQb2ludCh7XHJcbiAgbG9uZ2l0dWRlOiAtNDguOTcsXHJcbiAgbGF0aXR1ZGU6IDE5LjczXHJcbn0pO1xyXG5cclxuXHJcbmNvbnN0IHBvaW50MiA9IG5ldyBQb2ludCh7XHJcbiAgbG9uZ2l0dWRlOiAtMzkuOTcsXHJcbiAgbGF0aXR1ZGU6IDQ1LjczXHJcbn0pO1xyXG5cclxuXHJcbmNvbnN0IG1hcmtlclN5bWJvbCA9IG5ldyBTaW1wbGVNYXJrZXJTeW1ib2woe1xyXG4gIGNvbG9yOiBbMjI2LCAxMTksIDQwXSxcclxuICBvdXRsaW5lOiBuZXcgU2ltcGxlTGluZVN5bWJvbCh7XHJcbiAgICBjb2xvcjogWzI1NSwgMjU1LCAyNTVdLFxyXG4gICAgd2lkdGg6IDJcclxuICB9KVxyXG59KTtcclxuXHJcbmNvbnN0IE1hcENvbXBvbmVudCA9IFJlYWN0LmNyZWF0ZUNsYXNzKHtcclxuXHJcblxyXG4gZ2V0SW5pdGlhbFN0YXRlKCl7XHJcbiAgcmV0dXJuIHttb2RlOiczZCd9O1xyXG59LFxyXG5cclxudG9nZ2xlKCl7XHJcbiBpZiAodGhpcy5zdGF0ZS5tb2RlPT0nM2QnKXtcclxuICB0aGlzLnNldFN0YXRlKHttb2RlOicyZCd9KVxyXG59ZWxzZXtcclxuICB0aGlzLnNldFN0YXRlKHttb2RlOiczZCd9KVxyXG59XHJcbn0sXHJcblxyXG5yZW5kZXIoKXtcclxuICBpZiAodGhpcy5zdGF0ZS5tb2RlPT0nM2QnKXtcclxuICAgcmV0dXJuIHRoaXMucmVuZGVyM2QoKTtcclxuIH1lbHNle1xyXG4gIHJldHVybiB0aGlzLnJlbmRlcjJkKCk7XHJcbn1cclxufSxcclxuXHJcbnJlbmRlcjNkKCkge1xyXG5cclxuIHJldHVybiAoXHJcbiAgPGRpdj5cclxuICA8YSBocmVmPVwiamF2YXNjcmlwdDp2b2lkKClcIiBvbkNsaWNrPXt0aGlzLnRvZ2dsZX0+VG9nZ2xlIHZpZXc8L2E+XHJcbiAgPGRpdiBpZD1cIm1hcDNkXCI+XHJcblxyXG4gIDxNYXAgY2xhc3NOYW1lPVwibWFwXCIgIGJhc2VtYXA9XCJzdHJlZXRzXCI+XHJcbiAgPEdyYXBoaWNzTGF5ZXI+XHJcbiAgPEdyYXBoaWMgZ2VvbWV0cnk9e3BvaW50fSBzeW1ib2w9e21hcmtlclN5bWJvbH0vPlxyXG4gIDxHcmFwaGljIGdlb21ldHJ5PXtwb2ludDF9IHN5bWJvbD17bWFya2VyU3ltYm9sfS8+XHJcbiAgPEdyYXBoaWMgZ2VvbWV0cnk9e3BvaW50Mn0gc3ltYm9sPXttYXJrZXJTeW1ib2x9Lz5cclxuICA8L0dyYXBoaWNzTGF5ZXI+XHJcblxyXG4gIDxTY2VuZVZpZXcgY2VudGVyPXtbLTQ4LCAxOV19IHpvb209ezN9PlxyXG4gIDxCYXNlTWFwVG9nZ2xlLz5cclxuICA8Wm9vbS8+XHJcbiAgPC9TY2VuZVZpZXc+XHJcblxyXG4gIDwvTWFwPlxyXG4gIDwvZGl2PlxyXG4gIDwvZGl2PlxyXG4gIClcclxufSxcclxuXHJcbnJlbmRlcjJkKCkge1xyXG5cclxuIHJldHVybiAoXHJcbiAgPGRpdiBpZD1cIm1hcDJkXCI+XHJcbiAgPGEgaHJlZj1cImphdmFzY3JpcHQ6dm9pZCgpXCIgb25DbGljaz17dGhpcy50b2dnbGV9PlRvZ2dsZSB2aWV3PC9hPlxyXG4gIDxNYXAgY2xhc3NOYW1lPVwibWFwXCIgIGJhc2VtYXA9XCJzdHJlZXRzXCI+XHJcbiAgPEdyYXBoaWNzTGF5ZXI+XHJcbiAgPEdyYXBoaWMgZ2VvbWV0cnk9e3BvaW50fSBzeW1ib2w9e21hcmtlclN5bWJvbH0vPlxyXG4gIDxHcmFwaGljIGdlb21ldHJ5PXtwb2ludDF9IHN5bWJvbD17bWFya2VyU3ltYm9sfS8+XHJcbiAgPEdyYXBoaWMgZ2VvbWV0cnk9e3BvaW50Mn0gc3ltYm9sPXttYXJrZXJTeW1ib2x9Lz5cclxuICA8L0dyYXBoaWNzTGF5ZXI+XHJcblxyXG4gIDxNYXBWaWV3IGNlbnRlcj17Wy00OCwgMTldfSB6b29tPXszfT5cclxuICA8QmFzZU1hcFRvZ2dsZS8+XHJcbiAgPFpvb20vPlxyXG4gIDwvTWFwVmlldz5cclxuXHJcbiAgPC9NYXA+XHJcbiAgPC9kaXY+XHJcbiAgKVxyXG59XHJcblxyXG59KTtcclxuXHJcbmV4cG9ydCBkZWZhdWx0IE1hcENvbXBvbmVudDtcclxuIl19