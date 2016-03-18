define(['exports', 'react', 'react/react-dom', 'esri/layers/GraphicsLayer', 'app/components/esri/layers/Layer', 'esri/symbols/SimpleMarkerSymbol', 'esri/symbols/SimpleLineSymbol', 'esri/symbols/TextSymbol', 'esri/PopupTemplate', 'esri/geometry/Point', 'esri/geometry/SpatialReference', 'esri/Graphic', 'dojo/domReady!'], function (exports, _react, _reactDom, _GraphicsLayer, _Layer2, _SimpleMarkerSymbol, _SimpleLineSymbol, _TextSymbol, _PopupTemplate, _Point, _SpatialReference, _Graphic, _domReady) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });

  var _react2 = _interopRequireDefault(_react);

  var _reactDom2 = _interopRequireDefault(_reactDom);

  var _GraphicsLayer2 = _interopRequireDefault(_GraphicsLayer);

  var _Layer3 = _interopRequireDefault(_Layer2);

  var _SimpleMarkerSymbol2 = _interopRequireDefault(_SimpleMarkerSymbol);

  var _SimpleLineSymbol2 = _interopRequireDefault(_SimpleLineSymbol);

  var _TextSymbol2 = _interopRequireDefault(_TextSymbol);

  var _PopupTemplate2 = _interopRequireDefault(_PopupTemplate);

  var _Point2 = _interopRequireDefault(_Point);

  var _SpatialReference2 = _interopRequireDefault(_SpatialReference);

  var _Graphic2 = _interopRequireDefault(_Graphic);

  var _domReady2 = _interopRequireDefault(_domReady);

  function _interopRequireDefault(obj) {
    return obj && obj.__esModule ? obj : {
      default: obj
    };
  }

  function _classCallCheck(instance, Constructor) {
    if (!(instance instanceof Constructor)) {
      throw new TypeError("Cannot call a class as a function");
    }
  }

  var _createClass = function () {
    function defineProperties(target, props) {
      for (var i = 0; i < props.length; i++) {
        var descriptor = props[i];
        descriptor.enumerable = descriptor.enumerable || false;
        descriptor.configurable = true;
        if ("value" in descriptor) descriptor.writable = true;
        Object.defineProperty(target, descriptor.key, descriptor);
      }
    }

    return function (Constructor, protoProps, staticProps) {
      if (protoProps) defineProperties(Constructor.prototype, protoProps);
      if (staticProps) defineProperties(Constructor, staticProps);
      return Constructor;
    };
  }();

  function _possibleConstructorReturn(self, call) {
    if (!self) {
      throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
    }

    return call && (typeof call === "object" || typeof call === "function") ? call : self;
  }

  function _inherits(subClass, superClass) {
    if (typeof superClass !== "function" && superClass !== null) {
      throw new TypeError("Super expression must either be null or a function, not " + typeof superClass);
    }

    subClass.prototype = Object.create(superClass && superClass.prototype, {
      constructor: {
        value: subClass,
        enumerable: false,
        writable: true,
        configurable: true
      }
    });
    if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass;
  }

  //Create a symbol for drawing the point
  var markerSymbol = new _SimpleMarkerSymbol2.default({
    size: 34,
    color: [255, 193, 7, 0.8],
    outline: new _SimpleLineSymbol2.default({
      style: 'short-dash',
      color: [255, 152, 0, 0.6],
      width: 3
    })
  });

  var defaultSpatialReference = new _SpatialReference2.default({ wkid: 4326 });

  var GeoJsonLayer = function (_Layer) {
    _inherits(GeoJsonLayer, _Layer);

    function GeoJsonLayer() {
      _classCallCheck(this, GeoJsonLayer);

      return _possibleConstructorReturn(this, Object.getPrototypeOf(GeoJsonLayer).apply(this, arguments));
    }

    _createClass(GeoJsonLayer, [{
      key: 'componentWillMount',
      value: function componentWillMount() {
        this.layer = new _GraphicsLayer2.default({ popupTemplate: new _PopupTemplate2.default({
            title: "{Name}",
            content: "{*}"
          }) });
      }
    }, {
      key: 'componentWillUpdate',
      value: function componentWillUpdate(nextProps, nextState) {
        this.loadGeoJson(nextProps.data);
      }
    }, {
      key: 'loadGeoJson',
      value: function loadGeoJson(json) {
        var _this2 = this;

        var features = Terraformer.ArcGIS.convert(json);
        var graphics = features.map(function (f) {
          _this2.layer.add(_this2.createGraphic(f));
        });

        this.props.map.add(this.layer);
      }
    }, {
      key: 'createGraphic',
      value: function createGraphic(arcgisJson) {
        var g = _Graphic2.default;
        var graphic = g.fromJSON(arcgisJson);
        graphic.symbol = markerSymbol;
        /*graphic.popupTemplate= new PopupTemplate({
          title: "{Name}",
          content: "{*}"
        })*/
        return graphic;
      }
    }]);

    return GeoJsonLayer;
  }(_Layer3.default);

  exports.default = GeoJsonLayer;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmktZHluYW1pY1xcbGF5ZXJzXFxnZW9Kc29ubGF5ZXIuanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7O0FBcUJNLE1BQUksZUFBZSxpQ0FBdUI7QUFDeEMsVUFBSyxFQUFMO0FBQ0EsV0FBTyxDQUFDLEdBQUQsRUFBTSxHQUFOLEVBQVcsQ0FBWCxFQUFhLEdBQWIsQ0FBUDtBQUNBLGFBQVMsK0JBQXFCO0FBQzVCLGFBQU0sWUFBTjtBQUNBLGFBQU8sQ0FBQyxHQUFELEVBQU0sR0FBTixFQUFXLENBQVgsRUFBYyxHQUFkLENBQVA7QUFDQSxhQUFPLENBQVA7S0FITyxDQUFUO0dBSGlCLENBQWY7O0FBVUosTUFBTSwwQkFBMEIsK0JBQXFCLEVBQUMsTUFBTSxJQUFOLEVBQXRCLENBQTFCOztNQUVBOzs7Ozs7Ozs7OzsyQ0FFZ0I7QUFDbkIsYUFBSyxLQUFMLEdBQWEsNEJBQXNCLEVBQUMsZUFBZSw0QkFBa0I7QUFDcEUsbUJBQU8sUUFBUDtBQUNBLHFCQUFTLEtBQVQ7V0FGa0QsQ0FBZixFQUF2QixDQUFiLENBRG1COzs7OzBDQVFELFdBQVcsV0FBVztBQUN4QyxhQUFLLFdBQUwsQ0FBaUIsVUFBVSxJQUFWLENBQWpCLENBRHdDOzs7O2tDQUk5QixNQUFLOzs7QUFDaEIsWUFBTSxXQUFTLFlBQVksTUFBWixDQUFtQixPQUFuQixDQUEyQixJQUEzQixDQUFULENBRFU7QUFFaEIsWUFBTSxXQUFTLFNBQVMsR0FBVCxDQUFhLFVBQUMsQ0FBRCxFQUFLO0FBQy9CLGlCQUFLLEtBQUwsQ0FBVyxHQUFYLENBQWUsT0FBSyxhQUFMLENBQW1CLENBQW5CLENBQWYsRUFEK0I7U0FBTCxDQUF0QixDQUZVOztBQU1oQixhQUFLLEtBQUwsQ0FBVyxHQUFYLENBQWUsR0FBZixDQUFtQixLQUFLLEtBQUwsQ0FBbkIsQ0FOZ0I7Ozs7b0NBV0osWUFBWTtBQUN4QixZQUFNLHFCQUFOLENBRHdCO0FBRXhCLFlBQUksVUFBUyxFQUFFLFFBQUYsQ0FBVyxVQUFYLENBQVQsQ0FGb0I7QUFHeEIsZ0JBQVEsTUFBUixHQUFlLFlBQWY7Ozs7O0FBSHdCLGVBUWpCLE9BQVAsQ0FSd0I7Ozs7V0F6QnBCOzs7b0JBc0NPIiwiZmlsZSI6ImNvbXBvbmVudHNcXGVzcmktZHluYW1pY1xcbGF5ZXJzXFxnZW9Kc29ubGF5ZXIuanN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IFJlYWN0IGZyb20gJ3JlYWN0JztcclxuaW1wb3J0IFJlYWN0RE9NIGZyb20gJ3JlYWN0L3JlYWN0LWRvbSc7XHJcbmltcG9ydCBFc3JpR3JhcGhpY3NMYXllciBmcm9tICdlc3JpL2xheWVycy9HcmFwaGljc0xheWVyJztcclxuaW1wb3J0IExheWVyIGZyb20gJ2FwcC9jb21wb25lbnRzL2VzcmkvbGF5ZXJzL0xheWVyJztcclxuXHJcbmltcG9ydCBTaW1wbGVNYXJrZXJTeW1ib2wgZnJvbSAnZXNyaS9zeW1ib2xzL1NpbXBsZU1hcmtlclN5bWJvbCc7XHJcbmltcG9ydCBTaW1wbGVMaW5lU3ltYm9sICAgZnJvbSAnZXNyaS9zeW1ib2xzL1NpbXBsZUxpbmVTeW1ib2wnO1xyXG5cclxuaW1wb3J0IFRleHRTeW1ib2wgICBmcm9tICdlc3JpL3N5bWJvbHMvVGV4dFN5bWJvbCc7XHJcblxyXG5pbXBvcnQgUG9wdXBUZW1wbGF0ZSBmcm9tICdlc3JpL1BvcHVwVGVtcGxhdGUnO1xyXG5cclxuaW1wb3J0IFBvaW50IGZyb20gJ2VzcmkvZ2VvbWV0cnkvUG9pbnQnO1xyXG5cclxuaW1wb3J0IFNwYXRpYWxSZWZlcmVuY2UgZnJvbSAnZXNyaS9nZW9tZXRyeS9TcGF0aWFsUmVmZXJlbmNlJztcclxuXHJcbmltcG9ydCBHcmFwaGljIGZyb20gXCJlc3JpL0dyYXBoaWNcIjtcclxuaW1wb3J0IGRvbVJlYWR5IGZyb20gXCJkb2pvL2RvbVJlYWR5IVwiO1xyXG5cclxuXHJcbiAgICAgIC8vQ3JlYXRlIGEgc3ltYm9sIGZvciBkcmF3aW5nIHRoZSBwb2ludFxyXG4gICAgICB2YXIgbWFya2VyU3ltYm9sID0gbmV3IFNpbXBsZU1hcmtlclN5bWJvbCh7XHJcbiAgICAgICAgc2l6ZTozNCxcclxuICAgICAgICBjb2xvcjogWzI1NSwgMTkzLCA3LDAuOF0sXHJcbiAgICAgICAgb3V0bGluZTogbmV3IFNpbXBsZUxpbmVTeW1ib2woe1xyXG4gICAgICAgICAgc3R5bGU6J3Nob3J0LWRhc2gnLFxyXG4gICAgICAgICAgY29sb3I6IFsyNTUsIDE1MiwgMCwgMC42XSxcclxuICAgICAgICAgIHdpZHRoOiAzXHJcbiAgICAgICAgfSlcclxuICAgICAgfSk7XHJcblxyXG4gICAgICBjb25zdCBkZWZhdWx0U3BhdGlhbFJlZmVyZW5jZSA9IG5ldyBTcGF0aWFsUmVmZXJlbmNlKHt3a2lkOiA0MzI2fSk7XHJcblxyXG4gICAgICBjbGFzcyBHZW9Kc29uTGF5ZXIgZXh0ZW5kcyBMYXllcntcclxuXHJcbiAgICAgICBjb21wb25lbnRXaWxsTW91bnQoKSB7XHJcbiAgICAgICAgIHRoaXMubGF5ZXIgPSBuZXcgRXNyaUdyYXBoaWNzTGF5ZXIoe3BvcHVwVGVtcGxhdGU6IG5ldyBQb3B1cFRlbXBsYXRlKHtcclxuICAgICAgICAgIHRpdGxlOiBcIntOYW1lfVwiLFxyXG4gICAgICAgICAgY29udGVudDogXCJ7Kn1cIlxyXG4gICAgICAgIH0pfSk7XHJcbiAgICAgICB9XHJcblxyXG5cclxuICAgICAgIGNvbXBvbmVudFdpbGxVcGRhdGUobmV4dFByb3BzLCBuZXh0U3RhdGUpIHtcclxuICAgICAgICAgdGhpcy5sb2FkR2VvSnNvbihuZXh0UHJvcHMuZGF0YSk7XHJcbiAgICAgICB9XHJcblxyXG4gICAgICAgbG9hZEdlb0pzb24oanNvbil7XHJcbiAgICAgICAgY29uc3QgZmVhdHVyZXM9VGVycmFmb3JtZXIuQXJjR0lTLmNvbnZlcnQoanNvbik7XHJcbiAgICAgICAgY29uc3QgZ3JhcGhpY3M9ZmVhdHVyZXMubWFwKChmKT0+e1xyXG4gICAgICAgICAgdGhpcy5sYXllci5hZGQodGhpcy5jcmVhdGVHcmFwaGljKGYpKTtcclxuICAgICAgICB9KTtcclxuXHJcbiAgICAgICAgdGhpcy5wcm9wcy5tYXAuYWRkKHRoaXMubGF5ZXIpO1xyXG4gICAgICB9XHJcblxyXG5cclxuXHJcbiAgICAgIGNyZWF0ZUdyYXBoaWMoYXJjZ2lzSnNvbikge1xyXG4gICAgICAgIGNvbnN0IGc9R3JhcGhpYztcclxuICAgICAgICBsZXQgZ3JhcGhpYz0gZy5mcm9tSlNPTihhcmNnaXNKc29uKTtcclxuICAgICAgICBncmFwaGljLnN5bWJvbD1tYXJrZXJTeW1ib2w7XHJcbiAgICAgICAgLypncmFwaGljLnBvcHVwVGVtcGxhdGU9IG5ldyBQb3B1cFRlbXBsYXRlKHtcclxuICAgICAgICAgIHRpdGxlOiBcIntOYW1lfVwiLFxyXG4gICAgICAgICAgY29udGVudDogXCJ7Kn1cIlxyXG4gICAgICAgIH0pKi9cclxuICAgICAgICByZXR1cm4gZ3JhcGhpYztcclxuICAgICAgfVxyXG4gICAgfVxyXG5cclxuXHJcbiAgICBleHBvcnQgZGVmYXVsdCBHZW9Kc29uTGF5ZXI7XHJcbiJdfQ==