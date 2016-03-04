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
            _react2.default.createElement(_sceneView2.default, { center: [-48, 19], zoom: 3 })
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
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXG1hcC5qc3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7OztBQWdCQSxNQUFNLFFBQVEsb0JBQVU7QUFDdEIsZUFBVyxDQUFDLEtBQUQ7QUFDWCxjQUFVLEtBQVY7R0FGWSxDQUFSOztBQUtOLE1BQU0sU0FBUyxvQkFBVTtBQUN2QixlQUFXLENBQUMsS0FBRDtBQUNYLGNBQVUsS0FBVjtHQUZhLENBQVQ7O0FBTU4sTUFBTSxTQUFTLG9CQUFVO0FBQ3ZCLGVBQVcsQ0FBQyxLQUFEO0FBQ1gsY0FBVSxLQUFWO0dBRmEsQ0FBVDs7QUFNTixNQUFNLGVBQWUsaUNBQXVCO0FBQzFDLFdBQU8sQ0FBQyxHQUFELEVBQU0sR0FBTixFQUFXLEVBQVgsQ0FBUDtBQUNBLGFBQVMsK0JBQXFCO0FBQzVCLGFBQU8sQ0FBQyxHQUFELEVBQU0sR0FBTixFQUFXLEdBQVgsQ0FBUDtBQUNBLGFBQU8sQ0FBUDtLQUZPLENBQVQ7R0FGbUIsQ0FBZjs7QUFRTixNQUFNLGVBQWUsZ0JBQU0sV0FBTixDQUFrQjs7QUFHdEMsZ0RBQWlCO0FBQ2hCLGFBQU8sRUFBQyxNQUFLLElBQUwsRUFBUixDQURnQjtLQUhxQjtBQU92Qyw4QkFBUTtBQUNQLFVBQUksS0FBSyxLQUFMLENBQVcsSUFBWCxJQUFpQixJQUFqQixFQUFzQjtBQUN6QixhQUFLLFFBQUwsQ0FBYyxFQUFDLE1BQUssSUFBTCxFQUFmLEVBRHlCO09BQTFCLE1BRUk7QUFDSCxhQUFLLFFBQUwsQ0FBYyxFQUFDLE1BQUssSUFBTCxFQUFmLEVBREc7T0FGSjtLQVJzQztBQWV2Qyw4QkFBUTtBQUNOLFVBQUksS0FBSyxLQUFMLENBQVcsSUFBWCxJQUFpQixJQUFqQixFQUFzQjtBQUN6QixlQUFPLEtBQUssUUFBTCxFQUFQLENBRHlCO09BQTFCLE1BRUk7QUFDSixlQUFPLEtBQUssUUFBTCxFQUFQLENBREk7T0FGSjtLQWhCcUM7QUF1QnZDLGtDQUFXOztBQUVWLGFBQ0M7OztRQUNBOztZQUFHLE1BQUssbUJBQUwsRUFBeUIsU0FBUyxLQUFLLE1BQUwsRUFBckM7O1NBREE7UUFFQTs7WUFBSyxJQUFHLE9BQUgsRUFBTDtVQUVBOztjQUFLLFdBQVUsS0FBVixFQUFpQixTQUFRLFNBQVIsRUFBdEI7WUFDQTs7O2NBQ0UsbURBQVMsVUFBVSxLQUFWLEVBQWlCLFFBQVEsWUFBUixFQUExQixDQURGO2NBRUUsbURBQVMsVUFBVSxNQUFWLEVBQWtCLFFBQVEsWUFBUixFQUEzQixDQUZGO2NBR0UsbURBQVMsVUFBVSxNQUFWLEVBQWtCLFFBQVEsWUFBUixFQUEzQixDQUhGO2FBREE7WUFPQSxxREFBVyxRQUFRLENBQUMsQ0FBQyxFQUFELEVBQUssRUFBTixDQUFSLEVBQW1CLE1BQU0sQ0FBTixFQUE5QixDQVBBO1dBRkE7U0FGQTtPQURELENBRlU7S0F2QjRCO0FBK0N2QyxrQ0FBVzs7QUFFVixhQUNDOztVQUFLLElBQUcsT0FBSCxFQUFMO1FBQ0E7O1lBQUcsTUFBSyxtQkFBTCxFQUF5QixTQUFTLEtBQUssTUFBTCxFQUFyQzs7U0FEQTtRQUVBOztZQUFLLFdBQVUsS0FBVixFQUFpQixTQUFRLFNBQVIsRUFBdEI7VUFDQTs7O1lBQ0UsbURBQVMsVUFBVSxLQUFWLEVBQWlCLFFBQVEsWUFBUixFQUExQixDQURGO1lBRUUsbURBQVMsVUFBVSxNQUFWLEVBQWtCLFFBQVEsWUFBUixFQUEzQixDQUZGO1lBR0UsbURBQVMsVUFBVSxNQUFWLEVBQWtCLFFBQVEsWUFBUixFQUEzQixDQUhGO1dBREE7VUFPQTs7Y0FBUyxRQUFRLENBQUMsQ0FBQyxFQUFELEVBQUssRUFBTixDQUFSLEVBQW1CLE1BQU0sQ0FBTixFQUE1QjtZQUNBLDREQURBO1lBRUEsbURBRkE7V0FQQTtTQUZBO09BREQsQ0FGVTtLQS9DNEI7R0FBbEIsQ0FBZjs7b0JBdUVTIiwiZmlsZSI6ImNvbXBvbmVudHNcXG1hcC5qc3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgUmVhY3RET00gZnJvbSAncmVhY3QvcmVhY3QtZG9tJztcclxuaW1wb3J0IE1hcCBmcm9tICdhcHAvY29tcG9uZW50cy9lc3JpL21hcCc7XHJcbmltcG9ydCBTY2VuZVZpZXcgZnJvbSAnYXBwL2NvbXBvbmVudHMvZXNyaS9zY2VuZVZpZXcnO1xyXG5pbXBvcnQgTWFwVmlldyBmcm9tICdhcHAvY29tcG9uZW50cy9lc3JpL21hcHZpZXcnO1xyXG5cclxuaW1wb3J0IFpvb20gZnJvbSAnYXBwL2NvbXBvbmVudHMvZXNyaS93aWRnZXRzL3pvb20nO1xyXG5pbXBvcnQgQmFzZU1hcFRvZ2dsZSBmcm9tICdhcHAvY29tcG9uZW50cy9lc3JpL3dpZGdldHMvYmFzZW1hcHRvZ2dsZSc7XHJcbmltcG9ydCBHcmFwaGljc0xheWVyIGZyb20gJ2FwcC9jb21wb25lbnRzL2VzcmkvbGF5ZXJzL2dyYXBoaWNzTGF5ZXInO1xyXG5pbXBvcnQgR3JhcGhpYyBmcm9tICdhcHAvY29tcG9uZW50cy9lc3JpL2dyYXBoaWMnO1xyXG5cclxuaW1wb3J0IFBvaW50IGZyb20gXCJlc3JpL2dlb21ldHJ5L1BvaW50XCI7XHJcbmltcG9ydCBTaW1wbGVNYXJrZXJTeW1ib2wgZnJvbSBcImVzcmkvc3ltYm9scy9TaW1wbGVNYXJrZXJTeW1ib2xcIjtcclxuaW1wb3J0IFNpbXBsZUxpbmVTeW1ib2wgZnJvbSBcImVzcmkvc3ltYm9scy9TaW1wbGVMaW5lU3ltYm9sXCI7XHJcblxyXG5cclxuY29uc3QgcG9pbnQgPSBuZXcgUG9pbnQoe1xyXG4gIGxvbmdpdHVkZTogLTQ5Ljk3LFxyXG4gIGxhdGl0dWRlOiA0MS43M1xyXG59KTtcclxuXHJcbmNvbnN0IHBvaW50MSA9IG5ldyBQb2ludCh7XHJcbiAgbG9uZ2l0dWRlOiAtNDguOTcsXHJcbiAgbGF0aXR1ZGU6IDE5LjczXHJcbn0pO1xyXG5cclxuXHJcbmNvbnN0IHBvaW50MiA9IG5ldyBQb2ludCh7XHJcbiAgbG9uZ2l0dWRlOiAtMzkuOTcsXHJcbiAgbGF0aXR1ZGU6IDQ1LjczXHJcbn0pO1xyXG5cclxuXHJcbmNvbnN0IG1hcmtlclN5bWJvbCA9IG5ldyBTaW1wbGVNYXJrZXJTeW1ib2woe1xyXG4gIGNvbG9yOiBbMjI2LCAxMTksIDQwXSxcclxuICBvdXRsaW5lOiBuZXcgU2ltcGxlTGluZVN5bWJvbCh7XHJcbiAgICBjb2xvcjogWzI1NSwgMjU1LCAyNTVdLFxyXG4gICAgd2lkdGg6IDJcclxuICB9KVxyXG59KTtcclxuXHJcbmNvbnN0IE1hcENvbXBvbmVudCA9IFJlYWN0LmNyZWF0ZUNsYXNzKHtcclxuXHJcblxyXG4gZ2V0SW5pdGlhbFN0YXRlKCl7XHJcbiAgcmV0dXJuIHttb2RlOiczZCd9O1xyXG59LFxyXG5cclxudG9nZ2xlKCl7XHJcbiBpZiAodGhpcy5zdGF0ZS5tb2RlPT0nM2QnKXtcclxuICB0aGlzLnNldFN0YXRlKHttb2RlOicyZCd9KVxyXG59ZWxzZXtcclxuICB0aGlzLnNldFN0YXRlKHttb2RlOiczZCd9KVxyXG59XHJcbn0sXHJcblxyXG5yZW5kZXIoKXtcclxuICBpZiAodGhpcy5zdGF0ZS5tb2RlPT0nM2QnKXtcclxuICAgcmV0dXJuIHRoaXMucmVuZGVyM2QoKTtcclxuIH1lbHNle1xyXG4gIHJldHVybiB0aGlzLnJlbmRlcjJkKCk7XHJcbn1cclxufSxcclxuXHJcbnJlbmRlcjNkKCkge1xyXG5cclxuIHJldHVybiAoXHJcbiAgPGRpdj5cclxuICA8YSBocmVmPVwiamF2YXNjcmlwdDp2b2lkKClcIiBvbkNsaWNrPXt0aGlzLnRvZ2dsZX0+VG9nZ2xlIHZpZXc8L2E+XHJcbiAgPGRpdiBpZD1cIm1hcDNkXCI+XHJcblxyXG4gIDxNYXAgY2xhc3NOYW1lPVwibWFwXCIgIGJhc2VtYXA9XCJzdHJlZXRzXCI+XHJcbiAgPEdyYXBoaWNzTGF5ZXI+XHJcbiAgICA8R3JhcGhpYyBnZW9tZXRyeT17cG9pbnR9IHN5bWJvbD17bWFya2VyU3ltYm9sfS8+XHJcbiAgICA8R3JhcGhpYyBnZW9tZXRyeT17cG9pbnQxfSBzeW1ib2w9e21hcmtlclN5bWJvbH0vPlxyXG4gICAgPEdyYXBoaWMgZ2VvbWV0cnk9e3BvaW50Mn0gc3ltYm9sPXttYXJrZXJTeW1ib2x9Lz5cclxuICA8L0dyYXBoaWNzTGF5ZXI+XHJcblxyXG4gIDxTY2VuZVZpZXcgY2VudGVyPXtbLTQ4LCAxOV19IHpvb209ezN9PlxyXG4gICBcclxuICA8L1NjZW5lVmlldz5cclxuXHJcbiAgPC9NYXA+XHJcbiAgPC9kaXY+XHJcbiAgPC9kaXY+XHJcbiAgKVxyXG59LFxyXG5cclxucmVuZGVyMmQoKSB7XHJcblxyXG4gcmV0dXJuIChcclxuICA8ZGl2IGlkPVwibWFwMmRcIj5cclxuICA8YSBocmVmPVwiamF2YXNjcmlwdDp2b2lkKClcIiBvbkNsaWNrPXt0aGlzLnRvZ2dsZX0+VG9nZ2xlIHZpZXc8L2E+XHJcbiAgPE1hcCBjbGFzc05hbWU9XCJtYXBcIiAgYmFzZW1hcD1cInN0cmVldHNcIj5cclxuICA8R3JhcGhpY3NMYXllcj5cclxuICAgIDxHcmFwaGljIGdlb21ldHJ5PXtwb2ludH0gc3ltYm9sPXttYXJrZXJTeW1ib2x9Lz5cclxuICAgIDxHcmFwaGljIGdlb21ldHJ5PXtwb2ludDF9IHN5bWJvbD17bWFya2VyU3ltYm9sfS8+XHJcbiAgICA8R3JhcGhpYyBnZW9tZXRyeT17cG9pbnQyfSBzeW1ib2w9e21hcmtlclN5bWJvbH0vPlxyXG4gIDwvR3JhcGhpY3NMYXllcj5cclxuXHJcbiAgPE1hcFZpZXcgY2VudGVyPXtbLTQ4LCAxOV19IHpvb209ezN9PlxyXG4gIDxCYXNlTWFwVG9nZ2xlLz5cclxuICA8Wm9vbS8+XHJcbiAgPC9NYXBWaWV3PlxyXG5cclxuICA8L01hcD5cclxuICA8L2Rpdj5cclxuICApXHJcbn1cclxuXHJcbn0pO1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgTWFwQ29tcG9uZW50O1xyXG4iXX0=