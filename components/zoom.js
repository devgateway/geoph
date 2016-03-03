define(['exports', 'react', 'esri/core/watchUtils', 'esri/widgets/Zoom/ZoomViewModel'], function (exports, _react, _watchUtils, _ZoomViewModel) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });

  var _react2 = _interopRequireDefault(_react);

  var _watchUtils2 = _interopRequireDefault(_watchUtils);

  var _ZoomViewModel2 = _interopRequireDefault(_ZoomViewModel);

  function _interopRequireDefault(obj) {
    return obj && obj.__esModule ? obj : {
      default: obj
    };
  }

  var Zoom = _react2.default.createClass({
    displayName: 'Zoom',
    getInitialState: function getInitialState() {
      return {
        vm: new _ZoomViewModel2.default(),
        updating: false,
        maxZoomed: false,
        minZoomed: false
      };
    },
    getDefaultProps: function getDefaultProps() {
      return {
        view: {}
      };
    },
    componentDidMount: function componentDidMount() {
      var _this = this;

      this.props.view.then(function (view) {
        _this.state.vm.view = view;
        _watchUtils2.default.init(view, 'zoom', function (val) {
          _this.setState({
            maxZoomed: val === view.constraints.maxZoom,
            minZoomed: val === view.constraints.minZoom
          });
        });
        _watchUtils2.default.init(view, 'stationary', function (updating) {
          _this.setState({ updating: updating });
        });
      });
    },
    zoomIn: function zoomIn() {
      if (!this.state.maxZoomed) {
        this.state.vm.zoomIn();
      }
    },
    zoomOut: function zoomOut() {
      if (!this.state.minZoomed) {
        this.state.vm.zoomOut();
      }
    },
    render: function render() {

      var btnstyle = this.state.updating ? 'zoom-btns' : 'zoom-btns view-busy';
      var maxstate = this.state.maxZoomed ? 'button raised grey narrow disable' : 'button raised grey narrow';
      var minstate = this.state.minZoomed ? 'button raised grey narrow disable' : 'button raised grey narrow';

      return _react2.default.createElement(
        'div',
        { className: btnstyle },
        _react2.default.createElement(
          'div',
          { className: maxstate, onClick: this.zoomIn },
          _react2.default.createElement(
            'div',
            { className: 'center' },
            _react2.default.createElement(
              'i',
              { className: 'material-icons' },
              'add'
            )
          )
        ),
        _react2.default.createElement(
          'div',
          { className: minstate, onClick: this.zoomOut },
          _react2.default.createElement(
            'div',
            { className: 'center' },
            _react2.default.createElement(
              'i',
              { className: 'material-icons' },
              'remove'
            )
          )
        )
      );
    }
  });

  exports.default = Zoom;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXHpvb20uanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFJQSxNQUFNLE9BQU8sZ0JBQU0sV0FBTixDQUFrQjs7QUFFN0IsZ0RBQWtCO0FBQ2hCLGFBQU87QUFDTCxZQUFJLDZCQUFKO0FBQ0Esa0JBQVUsS0FBVjtBQUNBLG1CQUFXLEtBQVg7QUFDQSxtQkFBVyxLQUFYO09BSkYsQ0FEZ0I7S0FGVztBQVc3QixnREFBa0I7QUFDaEIsYUFBTztBQUNMLGNBQU0sRUFBTjtPQURGLENBRGdCO0tBWFc7QUFpQjdCLG9EQUFvQjs7O0FBQ2xCLFdBQUssS0FBTCxDQUFXLElBQVgsQ0FBZ0IsSUFBaEIsQ0FBcUIsZ0JBQVE7QUFDM0IsY0FBSyxLQUFMLENBQVcsRUFBWCxDQUFjLElBQWQsR0FBcUIsSUFBckIsQ0FEMkI7QUFFM0IsNkJBQVcsSUFBWCxDQUFnQixJQUFoQixFQUFzQixNQUF0QixFQUE4QixVQUFDLEdBQUQsRUFBUztBQUNyQyxnQkFBSyxRQUFMLENBQWM7QUFDWix1QkFBVyxRQUFRLEtBQUssV0FBTCxDQUFpQixPQUFqQjtBQUNuQix1QkFBVyxRQUFRLEtBQUssV0FBTCxDQUFpQixPQUFqQjtXQUZyQixFQURxQztTQUFULENBQTlCLENBRjJCO0FBUTNCLDZCQUFXLElBQVgsQ0FBZ0IsSUFBaEIsRUFBc0IsWUFBdEIsRUFBb0MsVUFBQyxRQUFELEVBQWM7QUFDaEQsZ0JBQUssUUFBTCxDQUFjLEVBQUUsa0JBQUYsRUFBZCxFQURnRDtTQUFkLENBQXBDLENBUjJCO09BQVIsQ0FBckIsQ0FEa0I7S0FqQlM7QUFnQzdCLDhCQUFTO0FBQ1AsVUFBSSxDQUFDLEtBQUssS0FBTCxDQUFXLFNBQVgsRUFBc0I7QUFDekIsYUFBSyxLQUFMLENBQVcsRUFBWCxDQUFjLE1BQWQsR0FEeUI7T0FBM0I7S0FqQzJCO0FBc0M3QixnQ0FBVTtBQUNSLFVBQUksQ0FBQyxLQUFLLEtBQUwsQ0FBVyxTQUFYLEVBQXNCO0FBQ3pCLGFBQUssS0FBTCxDQUFXLEVBQVgsQ0FBYyxPQUFkLEdBRHlCO09BQTNCO0tBdkMyQjtBQTRDN0IsOEJBQVM7O0FBRVAsVUFBSSxXQUFXLEtBQUssS0FBTCxDQUFXLFFBQVgsR0FBc0IsV0FBdEIsR0FBb0MscUJBQXBDLENBRlI7QUFHUCxVQUFJLFdBQVcsS0FBSyxLQUFMLENBQVcsU0FBWCxHQUF1QixtQ0FBdkIsR0FBNkQsMkJBQTdELENBSFI7QUFJUCxVQUFJLFdBQVcsS0FBSyxLQUFMLENBQVcsU0FBWCxHQUF1QixtQ0FBdkIsR0FBNkQsMkJBQTdELENBSlI7O0FBTVAsYUFDRTs7VUFBSyxXQUFXLFFBQVgsRUFBTDtRQUNFOztZQUFLLFdBQVcsUUFBWCxFQUFxQixTQUFTLEtBQUssTUFBTCxFQUFuQztVQUNFOztjQUFLLFdBQVUsUUFBVixFQUFMO1lBQXdCOztnQkFBRyxXQUFVLGdCQUFWLEVBQUg7O2FBQXhCO1dBREY7U0FERjtRQUlFOztZQUFLLFdBQVcsUUFBWCxFQUFxQixTQUFTLEtBQUssT0FBTCxFQUFuQztVQUNFOztjQUFLLFdBQVUsUUFBVixFQUFMO1lBQXdCOztnQkFBRyxXQUFVLGdCQUFWLEVBQUg7O2FBQXhCO1dBREY7U0FKRjtPQURGLENBTk87S0E1Q29CO0dBQWxCLENBQVA7O29CQWdFUyIsImZpbGUiOiJjb21wb25lbnRzXFx6b29tLmpzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCB3YXRjaFV0aWxzIGZyb20gJ2VzcmkvY29yZS93YXRjaFV0aWxzJztcclxuaW1wb3J0IFpvb21WaWV3TW9kZWwgZnJvbSAnZXNyaS93aWRnZXRzL1pvb20vWm9vbVZpZXdNb2RlbCc7XHJcblxyXG5jb25zdCBab29tID0gUmVhY3QuY3JlYXRlQ2xhc3Moe1xyXG5cclxuICBnZXRJbml0aWFsU3RhdGUoKSB7XHJcbiAgICByZXR1cm4ge1xyXG4gICAgICB2bTogbmV3IFpvb21WaWV3TW9kZWwoKSxcclxuICAgICAgdXBkYXRpbmc6IGZhbHNlLFxyXG4gICAgICBtYXhab29tZWQ6IGZhbHNlLFxyXG4gICAgICBtaW5ab29tZWQ6IGZhbHNlXHJcbiAgICB9O1xyXG4gIH0sXHJcblxyXG4gIGdldERlZmF1bHRQcm9wcygpIHtcclxuICAgIHJldHVybiB7XHJcbiAgICAgIHZpZXc6IHt9XHJcbiAgICB9XHJcbiAgfSxcclxuXHJcbiAgY29tcG9uZW50RGlkTW91bnQoKSB7XHJcbiAgICB0aGlzLnByb3BzLnZpZXcudGhlbih2aWV3ID0+IHtcclxuICAgICAgdGhpcy5zdGF0ZS52bS52aWV3ID0gdmlldztcclxuICAgICAgd2F0Y2hVdGlscy5pbml0KHZpZXcsICd6b29tJywgKHZhbCkgPT4ge1xyXG4gICAgICAgIHRoaXMuc2V0U3RhdGUoe1xyXG4gICAgICAgICAgbWF4Wm9vbWVkOiB2YWwgPT09IHZpZXcuY29uc3RyYWludHMubWF4Wm9vbSxcclxuICAgICAgICAgIG1pblpvb21lZDogdmFsID09PSB2aWV3LmNvbnN0cmFpbnRzLm1pblpvb21cclxuICAgICAgICB9KTtcclxuICAgICAgfSk7XHJcbiAgICAgIHdhdGNoVXRpbHMuaW5pdCh2aWV3LCAnc3RhdGlvbmFyeScsICh1cGRhdGluZykgPT4ge1xyXG4gICAgICAgIHRoaXMuc2V0U3RhdGUoeyB1cGRhdGluZyB9KTtcclxuICAgICAgfSk7XHJcbiAgICB9KTtcclxuICB9LFxyXG5cclxuICB6b29tSW4oKSB7XHJcbiAgICBpZiAoIXRoaXMuc3RhdGUubWF4Wm9vbWVkKSB7XHJcbiAgICAgIHRoaXMuc3RhdGUudm0uem9vbUluKCk7XHJcbiAgICB9XHJcbiAgfSxcclxuXHJcbiAgem9vbU91dCgpIHtcclxuICAgIGlmICghdGhpcy5zdGF0ZS5taW5ab29tZWQpIHtcclxuICAgICAgdGhpcy5zdGF0ZS52bS56b29tT3V0KCk7XHJcbiAgICB9XHJcbiAgfSxcclxuXHJcbiAgcmVuZGVyKCkge1xyXG5cclxuICAgIGxldCBidG5zdHlsZSA9IHRoaXMuc3RhdGUudXBkYXRpbmcgPyAnem9vbS1idG5zJyA6ICd6b29tLWJ0bnMgdmlldy1idXN5JztcclxuICAgIGxldCBtYXhzdGF0ZSA9IHRoaXMuc3RhdGUubWF4Wm9vbWVkID8gJ2J1dHRvbiByYWlzZWQgZ3JleSBuYXJyb3cgZGlzYWJsZScgOiAnYnV0dG9uIHJhaXNlZCBncmV5IG5hcnJvdyc7XHJcbiAgICBsZXQgbWluc3RhdGUgPSB0aGlzLnN0YXRlLm1pblpvb21lZCA/ICdidXR0b24gcmFpc2VkIGdyZXkgbmFycm93IGRpc2FibGUnIDogJ2J1dHRvbiByYWlzZWQgZ3JleSBuYXJyb3cnO1xyXG5cclxuICAgIHJldHVybiAoXHJcbiAgICAgIDxkaXYgY2xhc3NOYW1lPXtidG5zdHlsZX0+XHJcbiAgICAgICAgPGRpdiBjbGFzc05hbWU9e21heHN0YXRlfSBvbkNsaWNrPXt0aGlzLnpvb21Jbn0+XHJcbiAgICAgICAgICA8ZGl2IGNsYXNzTmFtZT1cImNlbnRlclwiPjxpIGNsYXNzTmFtZT1cIm1hdGVyaWFsLWljb25zXCI+YWRkPC9pPjwvZGl2PlxyXG4gICAgICAgIDwvZGl2PlxyXG4gICAgICAgIDxkaXYgY2xhc3NOYW1lPXttaW5zdGF0ZX0gb25DbGljaz17dGhpcy56b29tT3V0fT5cclxuICAgICAgICAgIDxkaXYgY2xhc3NOYW1lPVwiY2VudGVyXCI+PGkgY2xhc3NOYW1lPVwibWF0ZXJpYWwtaWNvbnNcIj5yZW1vdmU8L2k+PC9kaXY+XHJcbiAgICAgICAgPC9kaXY+XHJcbiAgICAgIDwvZGl2PlxyXG4gICAgKTtcclxuXHJcbiAgfVxyXG59KTtcclxuXHJcbmV4cG9ydCBkZWZhdWx0IFpvb207XHJcbiJdfQ==