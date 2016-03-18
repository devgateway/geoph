define(['exports', 'react', 'react/react-dom', 'app/components/esri/map', 'app/components/esri/sceneView', 'app/components/esri/mapview', 'app/components/esri/layers/graphicsLayer', 'app/components/esri-dynamic/layers/geoJsonLayer', 'app/components/esri/graphic', 'app/components/esri/geometry/extend', 'app/components/esri/popupTemplate'], function (exports, _react, _reactDom, _map, _sceneView, _mapview, _graphicsLayer, _geoJsonLayer, _graphic, _extend, _popupTemplate) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _react2 = _interopRequireDefault(_react);

	var _reactDom2 = _interopRequireDefault(_reactDom);

	var _map2 = _interopRequireDefault(_map);

	var _sceneView2 = _interopRequireDefault(_sceneView);

	var _mapview2 = _interopRequireDefault(_mapview);

	var _graphicsLayer2 = _interopRequireDefault(_graphicsLayer);

	var _geoJsonLayer2 = _interopRequireDefault(_geoJsonLayer);

	var _graphic2 = _interopRequireDefault(_graphic);

	var _extend2 = _interopRequireDefault(_extend);

	var _popupTemplate2 = _interopRequireDefault(_popupTemplate);

	function _interopRequireDefault(obj) {
		return obj && obj.__esModule ? obj : {
			default: obj
		};
	}

	var MapComponent = _react2.default.createClass({
		displayName: 'MapComponent',
		getInitialState: function getInitialState() {
			return {
				'sceneView': false
			};
		},
		render: function render() {
			var view = void 0;
			if (this.state.sceneView) {
				view = _react2.default.createElement(_sceneView2.default, null);
			} else {
				view = _react2.default.createElement(
					_mapview2.default,
					null,
					_react2.default.createElement(_extend2.default, { xmin: 12925933.579460911, ymin: 278072.4096790361, xmax: 14706610.590391904, ymax: 2291117.986596903, spatialReference: 102100 })
				);
			}

			return _react2.default.createElement(
				_map2.default,
				{ className: 'map', basemap: 'streets' },
				_react2.default.createElement(_geoJsonLayer2.default, { data: this.props.layers.projects.data, name: this.props.layers.projects.name }),
				view
			);
		}
	});

	exports.default = MapComponent;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXG1hcFxcbWFwLmpzeCJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFlQSxLQUFNLGVBQWUsZ0JBQU0sV0FBTixDQUFrQjs7QUFHdEMsOENBQWtCO0FBQ2pCLFVBQU87QUFDTixpQkFBWSxLQUFaO0lBREQsQ0FEaUI7R0FIb0I7QUFTdEMsNEJBQVE7QUFDUCxPQUFJLGFBQUosQ0FETztBQUVQLE9BQUksS0FBSyxLQUFMLENBQVcsU0FBWCxFQUFzQjtBQUN6QixXQUFNLHdEQUFOLENBRHlCO0lBQTFCLE1BRU87QUFDTixXQUFPOzs7S0FBUyxrREFBUyxNQUFPLGtCQUFQLEVBQTJCLE1BQU8saUJBQVAsRUFBMEIsTUFBTyxrQkFBUCxFQUEyQixNQUFPLGlCQUFQLEVBQTBCLGtCQUFrQixNQUFsQixFQUFuSCxDQUFUO0tBQVAsQ0FETTtJQUZQOztBQU1BLFVBQ0M7O01BQUssV0FBVSxLQUFWLEVBQWdCLFNBQVEsU0FBUixFQUFyQjtJQUNDLHdEQUFjLE1BQU0sS0FBSyxLQUFMLENBQVcsTUFBWCxDQUFrQixRQUFsQixDQUEyQixJQUEzQixFQUFpQyxNQUFNLEtBQUssS0FBTCxDQUFXLE1BQVgsQ0FBa0IsUUFBbEIsQ0FBMkIsSUFBM0IsRUFBM0QsQ0FERDtJQUdFLElBSEY7SUFERCxDQVJPO0dBVDhCO0VBQWxCLENBQWY7O21CQTJCUyIsImZpbGUiOiJjb21wb25lbnRzXFxtYXBcXG1hcC5qc3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgUmVhY3RET00gZnJvbSAncmVhY3QvcmVhY3QtZG9tJztcclxuaW1wb3J0IE1hcCBmcm9tICdhcHAvY29tcG9uZW50cy9lc3JpL21hcCc7XHJcblxyXG5pbXBvcnQgU2NlbmVWaWV3IGZyb20gJ2FwcC9jb21wb25lbnRzL2Vzcmkvc2NlbmVWaWV3JztcclxuaW1wb3J0IE1hcFZpZXcgZnJvbSAnYXBwL2NvbXBvbmVudHMvZXNyaS9tYXB2aWV3JztcclxuaW1wb3J0IEdyYXBoaWNzTGF5ZXIgZnJvbSAnYXBwL2NvbXBvbmVudHMvZXNyaS9sYXllcnMvZ3JhcGhpY3NMYXllcic7XHJcbmltcG9ydCBHZW9Kc29uTGF5ZXIgZnJvbSAnYXBwL2NvbXBvbmVudHMvZXNyaS1keW5hbWljL2xheWVycy9nZW9Kc29uTGF5ZXInO1xyXG5cclxuaW1wb3J0IEdyYXBoaWMgZnJvbSAnYXBwL2NvbXBvbmVudHMvZXNyaS9ncmFwaGljJztcclxuaW1wb3J0IEV4dGVudCBmcm9tICdhcHAvY29tcG9uZW50cy9lc3JpL2dlb21ldHJ5L2V4dGVuZCdcclxuXHJcblxyXG5pbXBvcnQgUG9wdXBUZW1wbGF0ZSBmcm9tICdhcHAvY29tcG9uZW50cy9lc3JpL3BvcHVwVGVtcGxhdGUnO1xyXG5cclxuY29uc3QgTWFwQ29tcG9uZW50ID0gUmVhY3QuY3JlYXRlQ2xhc3Moe1xyXG5cclxuXHJcblx0Z2V0SW5pdGlhbFN0YXRlKCkge1xyXG5cdFx0cmV0dXJuIHtcclxuXHRcdFx0J3NjZW5lVmlldyc6ZmFsc2UgIFxyXG5cdFx0fTtcclxuXHR9LFxyXG5cclxuXHRyZW5kZXIoKXtcclxuXHRcdGxldCB2aWV3O1xyXG5cdFx0aWYgKHRoaXMuc3RhdGUuc2NlbmVWaWV3KSB7XHJcblx0XHRcdHZpZXc9IDxTY2VuZVZpZXcvPjtcclxuXHRcdH0gZWxzZSB7XHJcblx0XHRcdHZpZXcgPSA8TWFwVmlldz48RXh0ZW50ICB4bWluPSB7MTI5MjU5MzMuNTc5NDYwOTExfSB5bWluPSB7Mjc4MDcyLjQwOTY3OTAzNjF9IHhtYXg9IHsxNDcwNjYxMC41OTAzOTE5MDR9IHltYXg9IHsyMjkxMTE3Ljk4NjU5NjkwM30gc3BhdGlhbFJlZmVyZW5jZT17MTAyMTAwfS8+PC9NYXBWaWV3PjtcclxuXHRcdH1cclxuXHJcblx0XHRyZXR1cm4gKFxyXG5cdFx0XHQ8TWFwIGNsYXNzTmFtZT1cIm1hcFwiIGJhc2VtYXA9XCJzdHJlZXRzXCIgPlxyXG5cdFx0XHRcdDxHZW9Kc29uTGF5ZXIgZGF0YT17dGhpcy5wcm9wcy5sYXllcnMucHJvamVjdHMuZGF0YX0gbmFtZT17dGhpcy5wcm9wcy5sYXllcnMucHJvamVjdHMubmFtZX0+XHJcblx0XHRcdFx0PC9HZW9Kc29uTGF5ZXI+XHJcblx0XHRcdFx0e3ZpZXd9XHJcblx0XHRcdDwvTWFwPilcclxuXHR9XHJcbn0pO1xyXG5cclxuXHJcbmV4cG9ydCBkZWZhdWx0IE1hcENvbXBvbmVudDtcclxuIl19