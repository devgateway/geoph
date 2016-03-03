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
    getInitialState: function getInitialState() {
      return { mode: '3d' };
    },
    toggleView: function toggleView() {
      if (this.state.mode == '3d') {
        this.setState({ mode: '2d' });
      } else {
        this.setState({ mode: '3d' });
      }
    },
    render: function render() {
      if (this.state.mode == '3d') {
        return _react2.default.createElement(
          'div',
          null,
          _react2.default.createElement(
            'div',
            null,
            this.state.mode,
            ' mode  ',
            _react2.default.createElement(
              'a',
              { href: '#map', onClick: this.toggleView },
              ' toggle view'
            )
          ),
          _react2.default.createElement(
            _map2.default,
            { className: 'map', basemap: 'streets' },
            _react2.default.createElement(_sceneView2.default, { center: [-112, 38], zoom: 1 })
          )
        );
      } else {

        return _react2.default.createElement(
          'div',
          null,
          _react2.default.createElement(
            'div',
            null,
            this.state.mode,
            ' mode  ',
            _react2.default.createElement(
              'a',
              { href: '#map', onClick: this.toggleView },
              ' toggle mode'
            )
          ),
          _react2.default.createElement(
            _map2.default,
            { className: 'map', basemap: 'streets' },
            _react2.default.createElement(_mapview2.default, { center: [-112, 38], zoom: 7 })
          )
        );
      }
    }
  });

  exports.default = MapComponent;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXE1hcHZpZXcuanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7OztBQVNBLE1BQU0sZUFBZSxnQkFBTSxXQUFOLENBQWtCOztBQUdyQyxnREFBaUI7QUFDakIsYUFBTyxFQUFDLE1BQUssSUFBTCxFQUFSLENBRGlCO0tBSG9CO0FBUXZDLHNDQUFZO0FBQ1YsVUFBSSxLQUFLLEtBQUwsQ0FBVyxJQUFYLElBQWlCLElBQWpCLEVBQXNCO0FBQ3hCLGFBQUssUUFBTCxDQUFjLEVBQUMsTUFBSyxJQUFMLEVBQWYsRUFEd0I7T0FBMUIsTUFFSztBQUNILGFBQUssUUFBTCxDQUFjLEVBQUMsTUFBSyxJQUFMLEVBQWYsRUFERztPQUZMO0tBVHFDO0FBZ0J2Qyw4QkFBUztBQUNSLFVBQUksS0FBSyxLQUFMLENBQVcsSUFBWCxJQUFpQixJQUFqQixFQUFzQjtBQUN6QixlQUNFOzs7VUFDQTs7O1lBQU0sS0FBSyxLQUFMLENBQVcsSUFBWDtxQkFBTjtZQUE2Qjs7Z0JBQUcsTUFBSyxNQUFMLEVBQVksU0FBUyxLQUFLLFVBQUwsRUFBeEI7O2FBQTdCO1dBREE7VUFFQTs7Y0FBSyxXQUFVLEtBQVYsRUFBaUIsU0FBUSxTQUFSLEVBQXRCO1lBQ0UscURBQVcsUUFBUSxDQUFDLENBQUMsR0FBRCxFQUFNLEVBQVAsQ0FBUixFQUFvQixNQUFNLENBQU4sRUFBL0IsQ0FERjtXQUZBO1NBREYsQ0FEeUI7T0FBMUIsTUFRUzs7QUFFUCxlQUNDOzs7VUFDQTs7O1lBQU0sS0FBSyxLQUFMLENBQVcsSUFBWDtxQkFBTjtZQUE2Qjs7Z0JBQUcsTUFBSyxNQUFMLEVBQVksU0FBUyxLQUFLLFVBQUwsRUFBeEI7O2FBQTdCO1dBREE7VUFFQTs7Y0FBSyxXQUFVLEtBQVYsRUFBaUIsU0FBUSxTQUFSLEVBQXRCO1lBQ0QsbURBQVMsUUFBUSxDQUFDLENBQUMsR0FBRCxFQUFNLEVBQVAsQ0FBUixFQUFvQixNQUFNLENBQU4sRUFBN0IsQ0FEQztXQUZBO1NBREQsQ0FGTztPQVJUO0tBakJzQztHQUFsQixDQUFmOztvQkF1Q1MiLCJmaWxlIjoiY29tcG9uZW50c1xcTWFwdmlldy5qc3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgUmVhY3RET00gZnJvbSAncmVhY3QvcmVhY3QtZG9tJztcclxuaW1wb3J0IE1hcCBmcm9tICdhcHAvY29tcG9uZW50cy9lc3JpL21hcCc7XHJcbmltcG9ydCBTY2VuZVZpZXcgZnJvbSAnYXBwL2NvbXBvbmVudHMvZXNyaS9zY2VuZVZpZXcnO1xyXG5pbXBvcnQgTWFwVmlldyBmcm9tICdhcHAvY29tcG9uZW50cy9lc3JpL21hcHZpZXcnO1xyXG5cclxuaW1wb3J0IFpvb20gZnJvbSAnYXBwL2NvbXBvbmVudHMvZXNyaS93aWRnZXRzL3pvb20nO1xyXG5pbXBvcnQgQmFzZU1hcFRvZ2dsZSBmcm9tICdhcHAvY29tcG9uZW50cy9lc3JpL3dpZGdldHMvYmFzZW1hcHRvZ2dsZSc7XHJcblxyXG5jb25zdCBNYXBDb21wb25lbnQgPSBSZWFjdC5jcmVhdGVDbGFzcyh7XHJcblxyXG5cclxuICBnZXRJbml0aWFsU3RhdGUoKXtcclxuICByZXR1cm4ge21vZGU6JzNkJ31cclxufSxcclxuXHJcblxyXG50b2dnbGVWaWV3KCl7XHJcbiAgaWYgKHRoaXMuc3RhdGUubW9kZT09JzNkJyl7XHJcbiAgICB0aGlzLnNldFN0YXRlKHttb2RlOicyZCd9KVxyXG4gIH1lbHNle1xyXG4gICAgdGhpcy5zZXRTdGF0ZSh7bW9kZTonM2QnfSlcclxuICB9XHJcbn0sXHJcblxyXG5yZW5kZXIoKSB7XHJcblx0aWYgKHRoaXMuc3RhdGUubW9kZT09JzNkJyl7XHJcbiAgcmV0dXJuIChcclxuICAgIDxkaXY+XHJcbiAgICA8ZGl2Pnt0aGlzLnN0YXRlLm1vZGV9IG1vZGUgIDxhIGhyZWY9XCIjbWFwXCIgb25DbGljaz17dGhpcy50b2dnbGVWaWV3fT4gdG9nZ2xlIHZpZXc8L2E+PC9kaXY+XHJcbiAgICA8TWFwIGNsYXNzTmFtZT1cIm1hcFwiICBiYXNlbWFwPVwic3RyZWV0c1wiPlxyXG4gICAgICA8U2NlbmVWaWV3IGNlbnRlcj17Wy0xMTIsIDM4XX0gem9vbT17MX0+PC9TY2VuZVZpZXc+XHJcbiAgICA8L01hcD5cclxuICAgIDwvZGl2PlxyXG4gICAgKX1lbHNle1xyXG5cclxuICAgcmV0dXJuIChcclxuICAgIDxkaXY+XHJcbiAgICA8ZGl2Pnt0aGlzLnN0YXRlLm1vZGV9IG1vZGUgIDxhIGhyZWY9XCIjbWFwXCIgb25DbGljaz17dGhpcy50b2dnbGVWaWV3fT4gdG9nZ2xlIG1vZGU8L2E+PC9kaXY+XHJcbiAgICA8TWFwIGNsYXNzTmFtZT1cIm1hcFwiICBiYXNlbWFwPVwic3RyZWV0c1wiPlxyXG4gIFx0PE1hcFZpZXcgY2VudGVyPXtbLTExMiwgMzhdfSB6b29tPXs3fT48L01hcFZpZXc+XHJcbiAgICA8L01hcD5cclxuICAgIDwvZGl2PlxyXG4gICAgKVxyXG4gICB9XHJcbn1cclxufSk7XHJcblxyXG5leHBvcnQgZGVmYXVsdCBNYXBDb21wb25lbnQ7XHJcbiJdfQ==