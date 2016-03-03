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
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmlcXHdpZGdldHNcXHpvb20uanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFJQSxNQUFNLE9BQU8sZ0JBQU0sV0FBTixDQUFrQjs7QUFFN0IsZ0RBQWtCO0FBQ2hCLGFBQU87QUFDTCxZQUFJLDZCQUFKO0FBQ0Esa0JBQVUsS0FBVjtBQUNBLG1CQUFXLEtBQVg7QUFDQSxtQkFBVyxLQUFYO09BSkYsQ0FEZ0I7S0FGVztBQVc3QixnREFBa0I7QUFDaEIsYUFBTztBQUNMLGNBQU0sRUFBTjtPQURGLENBRGdCO0tBWFc7QUFpQjdCLG9EQUFvQjs7O0FBQ25CLFdBQUssS0FBTCxDQUFXLElBQVgsQ0FBZ0IsSUFBaEIsQ0FBcUIsZ0JBQVE7QUFDMUIsY0FBSyxLQUFMLENBQVcsRUFBWCxDQUFjLElBQWQsR0FBcUIsSUFBckIsQ0FEMEI7QUFFMUIsNkJBQVcsSUFBWCxDQUFnQixJQUFoQixFQUFzQixNQUF0QixFQUE4QixVQUFDLEdBQUQsRUFBUztBQUNyQyxnQkFBSyxRQUFMLENBQWM7QUFDWix1QkFBVyxRQUFRLEtBQUssV0FBTCxDQUFpQixPQUFqQjtBQUNuQix1QkFBVyxRQUFRLEtBQUssV0FBTCxDQUFpQixPQUFqQjtXQUZyQixFQURxQztTQUFULENBQTlCLENBRjBCO0FBUTFCLDZCQUFXLElBQVgsQ0FBZ0IsSUFBaEIsRUFBc0IsWUFBdEIsRUFBb0MsVUFBQyxRQUFELEVBQWM7QUFDaEQsZ0JBQUssUUFBTCxDQUFjLEVBQUUsa0JBQUYsRUFBZCxFQURnRDtTQUFkLENBQXBDLENBUjBCO09BQVIsQ0FBckIsQ0FEbUI7S0FqQlM7QUFnQzdCLDhCQUFTO0FBQ1AsVUFBSSxDQUFDLEtBQUssS0FBTCxDQUFXLFNBQVgsRUFBc0I7QUFDekIsYUFBSyxLQUFMLENBQVcsRUFBWCxDQUFjLE1BQWQsR0FEeUI7T0FBM0I7S0FqQzJCO0FBc0M3QixnQ0FBVTtBQUNSLFVBQUksQ0FBQyxLQUFLLEtBQUwsQ0FBVyxTQUFYLEVBQXNCO0FBQ3pCLGFBQUssS0FBTCxDQUFXLEVBQVgsQ0FBYyxPQUFkLEdBRHlCO09BQTNCO0tBdkMyQjtBQTRDN0IsOEJBQVM7O0FBRVAsVUFBSSxXQUFXLEtBQUssS0FBTCxDQUFXLFFBQVgsR0FBc0IsV0FBdEIsR0FBb0MscUJBQXBDLENBRlI7QUFHUCxVQUFJLFdBQVcsS0FBSyxLQUFMLENBQVcsU0FBWCxHQUF1QixtQ0FBdkIsR0FBNkQsMkJBQTdELENBSFI7QUFJUCxVQUFJLFdBQVcsS0FBSyxLQUFMLENBQVcsU0FBWCxHQUF1QixtQ0FBdkIsR0FBNkQsMkJBQTdELENBSlI7O0FBTVAsYUFDRTs7VUFBSyxXQUFXLFFBQVgsRUFBTDtRQUNFOztZQUFLLFdBQVcsUUFBWCxFQUFxQixTQUFTLEtBQUssTUFBTCxFQUFuQztVQUNFOztjQUFLLFdBQVUsUUFBVixFQUFMO1lBQXdCOztnQkFBRyxXQUFVLGdCQUFWLEVBQUg7O2FBQXhCO1dBREY7U0FERjtRQUlFOztZQUFLLFdBQVcsUUFBWCxFQUFxQixTQUFTLEtBQUssT0FBTCxFQUFuQztVQUNFOztjQUFLLFdBQVUsUUFBVixFQUFMO1lBQXdCOztnQkFBRyxXQUFVLGdCQUFWLEVBQUg7O2FBQXhCO1dBREY7U0FKRjtPQURGLENBTk87S0E1Q29CO0dBQWxCLENBQVA7O29CQWdFUyIsImZpbGUiOiJjb21wb25lbnRzXFxlc3JpXFx3aWRnZXRzXFx6b29tLmpzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCB3YXRjaFV0aWxzIGZyb20gJ2VzcmkvY29yZS93YXRjaFV0aWxzJztcclxuaW1wb3J0IFpvb21WaWV3TW9kZWwgZnJvbSAnZXNyaS93aWRnZXRzL1pvb20vWm9vbVZpZXdNb2RlbCc7XHJcblxyXG5jb25zdCBab29tID0gUmVhY3QuY3JlYXRlQ2xhc3Moe1xyXG5cclxuICBnZXRJbml0aWFsU3RhdGUoKSB7XHJcbiAgICByZXR1cm4ge1xyXG4gICAgICB2bTogbmV3IFpvb21WaWV3TW9kZWwoKSxcclxuICAgICAgdXBkYXRpbmc6IGZhbHNlLFxyXG4gICAgICBtYXhab29tZWQ6IGZhbHNlLFxyXG4gICAgICBtaW5ab29tZWQ6IGZhbHNlXHJcbiAgICB9O1xyXG4gIH0sXHJcblxyXG4gIGdldERlZmF1bHRQcm9wcygpIHtcclxuICAgIHJldHVybiB7XHJcbiAgICAgIHZpZXc6IHt9XHJcbiAgICB9XHJcbiAgfSxcclxuXHJcbiAgY29tcG9uZW50RGlkTW91bnQoKSB7XHJcbiAgIHRoaXMucHJvcHMudmlldy50aGVuKHZpZXcgPT4ge1xyXG4gICAgICB0aGlzLnN0YXRlLnZtLnZpZXcgPSB2aWV3O1xyXG4gICAgICB3YXRjaFV0aWxzLmluaXQodmlldywgJ3pvb20nLCAodmFsKSA9PiB7XHJcbiAgICAgICAgdGhpcy5zZXRTdGF0ZSh7XHJcbiAgICAgICAgICBtYXhab29tZWQ6IHZhbCA9PT0gdmlldy5jb25zdHJhaW50cy5tYXhab29tLFxyXG4gICAgICAgICAgbWluWm9vbWVkOiB2YWwgPT09IHZpZXcuY29uc3RyYWludHMubWluWm9vbVxyXG4gICAgICAgIH0pO1xyXG4gICAgICB9KTtcclxuICAgICAgd2F0Y2hVdGlscy5pbml0KHZpZXcsICdzdGF0aW9uYXJ5JywgKHVwZGF0aW5nKSA9PiB7XHJcbiAgICAgICAgdGhpcy5zZXRTdGF0ZSh7IHVwZGF0aW5nIH0pO1xyXG4gICAgICB9KTtcclxuICAgIH0pO1xyXG4gIH0sXHJcblxyXG4gIHpvb21JbigpIHtcclxuICAgIGlmICghdGhpcy5zdGF0ZS5tYXhab29tZWQpIHtcclxuICAgICAgdGhpcy5zdGF0ZS52bS56b29tSW4oKTtcclxuICAgIH1cclxuICB9LFxyXG5cclxuICB6b29tT3V0KCkge1xyXG4gICAgaWYgKCF0aGlzLnN0YXRlLm1pblpvb21lZCkge1xyXG4gICAgICB0aGlzLnN0YXRlLnZtLnpvb21PdXQoKTtcclxuICAgIH1cclxuICB9LFxyXG5cclxuICByZW5kZXIoKSB7XHJcblxyXG4gICAgbGV0IGJ0bnN0eWxlID0gdGhpcy5zdGF0ZS51cGRhdGluZyA/ICd6b29tLWJ0bnMnIDogJ3pvb20tYnRucyB2aWV3LWJ1c3knO1xyXG4gICAgbGV0IG1heHN0YXRlID0gdGhpcy5zdGF0ZS5tYXhab29tZWQgPyAnYnV0dG9uIHJhaXNlZCBncmV5IG5hcnJvdyBkaXNhYmxlJyA6ICdidXR0b24gcmFpc2VkIGdyZXkgbmFycm93JztcclxuICAgIGxldCBtaW5zdGF0ZSA9IHRoaXMuc3RhdGUubWluWm9vbWVkID8gJ2J1dHRvbiByYWlzZWQgZ3JleSBuYXJyb3cgZGlzYWJsZScgOiAnYnV0dG9uIHJhaXNlZCBncmV5IG5hcnJvdyc7XHJcblxyXG4gICAgcmV0dXJuIChcclxuICAgICAgPGRpdiBjbGFzc05hbWU9e2J0bnN0eWxlfT5cclxuICAgICAgICA8ZGl2IGNsYXNzTmFtZT17bWF4c3RhdGV9IG9uQ2xpY2s9e3RoaXMuem9vbUlufT5cclxuICAgICAgICAgIDxkaXYgY2xhc3NOYW1lPVwiY2VudGVyXCI+PGkgY2xhc3NOYW1lPVwibWF0ZXJpYWwtaWNvbnNcIj5hZGQ8L2k+PC9kaXY+XHJcbiAgICAgICAgPC9kaXY+XHJcbiAgICAgICAgPGRpdiBjbGFzc05hbWU9e21pbnN0YXRlfSBvbkNsaWNrPXt0aGlzLnpvb21PdXR9PlxyXG4gICAgICAgICAgPGRpdiBjbGFzc05hbWU9XCJjZW50ZXJcIj48aSBjbGFzc05hbWU9XCJtYXRlcmlhbC1pY29uc1wiPnJlbW92ZTwvaT48L2Rpdj5cclxuICAgICAgICA8L2Rpdj5cclxuICAgICAgPC9kaXY+XHJcbiAgICApO1xyXG5cclxuICB9XHJcbn0pO1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgWm9vbTtcclxuIl19