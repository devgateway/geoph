define(['exports', 'react', 'esri/core/watchUtils', 'esri/widgets/BasemapToggle/BasemapToggleViewModel'], function (exports, _react, _watchUtils, _BasemapToggleViewModel) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });

  var _react2 = _interopRequireDefault(_react);

  var _watchUtils2 = _interopRequireDefault(_watchUtils);

  var _BasemapToggleViewModel2 = _interopRequireDefault(_BasemapToggleViewModel);

  function _interopRequireDefault(obj) {
    return obj && obj.__esModule ? obj : {
      default: obj
    };
  }

  function bgImage(url) {
    return {
      backgroundImage: 'url(' + url + ')'
    };
  }

  var BasemapToggle = _react2.default.createClass({
    displayName: 'BasemapToggle',
    getInitialState: function getInitialState() {
      return {
        vm: new _BasemapToggleViewModel2.default(),
        secondaryThumbnailUrl: ''
      };
    },
    getDefaultProps: function getDefaultProps() {
      return {
        view: {},
        secondaryThumbnailUrl: '',
        currentThumbnailUrl: '',
        updating: false
      };
    },
    componentDidMount: function componentDidMount() {
      var _this = this;

      this.props.view.then(function (view) {

        _this.state.vm.view = view;
        _this.state.vm.secondaryBasemap = _this.props.secondaryBasemap;

        var _state$vm = _this.state.vm;
        var secondaryBasemap = _state$vm.secondaryBasemap;
        var currentBasemap = _state$vm.currentBasemap;


        var info = _this.state.vm.getBasemapInfo(_this.props.secondaryBasemap || 'topo');

        _this.setState({
          secondaryThumbnailUrl: info.thumbnailUrl,
          currentThumbnailUrl: _this.state.vm.currentBasemap.thumbnailUrl
        });

        _watchUtils2.default.watch(_this.state.vm, 'secondaryBasemap', _this.updateThumbnails);
        _watchUtils2.default.init(view, 'stationary', function (updating) {
          _this.setState({ updating: updating });
        });
      });
    },
    updateThumbnails: function updateThumbnails(secondary, current) {
      var secInfo = this.state.vm.getBasemapInfo(secondary);
      var curInfo = this.state.vm.getBasemapInfo(current);
      this.setState({
        secondaryThumbnailUrl: secInfo.thumbnailUrl,
        currentThumbnailUrl: curInfo.thumbnailUrl
      });
    },
    toggle: function toggle() {
      this.state.vm.toggle();
    },
    render: function render() {

      var currentThumbnailStyle = bgImage(this.state.currentThumbnailUrl);

      var secondaryThumbnailStyle = bgImage(this.state.secondaryThumbnailUrl);

      var style = this.state.updating ? 'basemap-container' : 'basemap-container view-busy';

      return _react2.default.createElement(
        'div',
        { className: style },
        _react2.default.createElement('div', { className: 'basemap-item basemap-item-secondary', onClick: this.toggle, style: secondaryThumbnailStyle }),
        _react2.default.createElement('div', { className: 'basemap-item basemap-item-current', style: currentThumbnailStyle })
      );
    }
  });

  exports.default = BasemapToggle;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGJhc2VtYXB0b2dnbGUuanN4Il0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFJQSxXQUFTLE9BQVQsQ0FBaUIsR0FBakIsRUFBc0I7QUFDcEIsV0FBTztBQUNMLHVCQUFpQixTQUFTLEdBQVQsR0FBZSxHQUFmO0tBRG5CLENBRG9CO0dBQXRCOztBQU1BLE1BQU0sZ0JBQWdCLGdCQUFNLFdBQU4sQ0FBa0I7O0FBRXRDLGdEQUFrQjtBQUNoQixhQUFPO0FBQ0wsWUFBSSxzQ0FBSjtBQUNBLCtCQUF1QixFQUF2QjtPQUZGLENBRGdCO0tBRm9CO0FBU3RDLGdEQUFrQjtBQUNoQixhQUFPO0FBQ0wsY0FBTSxFQUFOO0FBQ0EsK0JBQXVCLEVBQXZCO0FBQ0EsNkJBQXFCLEVBQXJCO0FBQ0Esa0JBQVUsS0FBVjtPQUpGLENBRGdCO0tBVG9CO0FBa0J0QyxvREFBb0I7OztBQUNsQixXQUFLLEtBQUwsQ0FBVyxJQUFYLENBQWdCLElBQWhCLENBQXFCLGdCQUFROztBQUUzQixjQUFLLEtBQUwsQ0FBVyxFQUFYLENBQWMsSUFBZCxHQUFxQixJQUFyQixDQUYyQjtBQUczQixjQUFLLEtBQUwsQ0FBVyxFQUFYLENBQWMsZ0JBQWQsR0FBaUMsTUFBSyxLQUFMLENBQVcsZ0JBQVgsQ0FITjs7d0JBS2dCLE1BQUssS0FBTCxDQUFXLEVBQVgsQ0FMaEI7WUFLckIsOENBTHFCO1lBS0gsMENBTEc7OztBQU8zQixZQUFJLE9BQU8sTUFBSyxLQUFMLENBQVcsRUFBWCxDQUFjLGNBQWQsQ0FBNkIsTUFBSyxLQUFMLENBQVcsZ0JBQVgsSUFBK0IsTUFBL0IsQ0FBcEMsQ0FQdUI7O0FBUzNCLGNBQUssUUFBTCxDQUFjO0FBQ1osaUNBQXVCLEtBQUssWUFBTDtBQUN2QiwrQkFBcUIsTUFBSyxLQUFMLENBQVcsRUFBWCxDQUFjLGNBQWQsQ0FBNkIsWUFBN0I7U0FGdkIsRUFUMkI7O0FBYzNCLDZCQUFXLEtBQVgsQ0FBaUIsTUFBSyxLQUFMLENBQVcsRUFBWCxFQUFlLGtCQUFoQyxFQUFvRCxNQUFLLGdCQUFMLENBQXBELENBZDJCO0FBZTNCLDZCQUFXLElBQVgsQ0FBZ0IsSUFBaEIsRUFBc0IsWUFBdEIsRUFBb0MsVUFBQyxRQUFELEVBQWM7QUFDaEQsZ0JBQUssUUFBTCxDQUFjLEVBQUUsa0JBQUYsRUFBZCxFQURnRDtTQUFkLENBQXBDLENBZjJCO09BQVIsQ0FBckIsQ0FEa0I7S0FsQmtCO0FBeUN0QyxnREFBaUIsV0FBVyxTQUFTO0FBQ25DLFVBQUksVUFBVSxLQUFLLEtBQUwsQ0FBVyxFQUFYLENBQWMsY0FBZCxDQUE2QixTQUE3QixDQUFWLENBRCtCO0FBRW5DLFVBQUksVUFBVSxLQUFLLEtBQUwsQ0FBVyxFQUFYLENBQWMsY0FBZCxDQUE2QixPQUE3QixDQUFWLENBRitCO0FBR25DLFdBQUssUUFBTCxDQUFjO0FBQ1osK0JBQXVCLFFBQVEsWUFBUjtBQUN2Qiw2QkFBcUIsUUFBUSxZQUFSO09BRnZCLEVBSG1DO0tBekNDO0FBa0R0Qyw4QkFBUztBQUNQLFdBQUssS0FBTCxDQUFXLEVBQVgsQ0FBYyxNQUFkLEdBRE87S0FsRDZCO0FBc0R0Qyw4QkFBUzs7QUFFUCxVQUFJLHdCQUF3QixRQUFRLEtBQUssS0FBTCxDQUFXLG1CQUFYLENBQWhDLENBRkc7O0FBSVAsVUFBSSwwQkFBMEIsUUFBUSxLQUFLLEtBQUwsQ0FBVyxxQkFBWCxDQUFsQyxDQUpHOztBQU1QLFVBQUksUUFBUSxLQUFLLEtBQUwsQ0FBVyxRQUFYLEdBQXNCLG1CQUF0QixHQUE0Qyw2QkFBNUMsQ0FOTDs7QUFRUCxhQUVFOztVQUFLLFdBQVcsS0FBWCxFQUFMO1FBQ0UsdUNBQUssV0FBVSxxQ0FBVixFQUFnRCxTQUFTLEtBQUssTUFBTCxFQUFhLE9BQU8sdUJBQVAsRUFBM0UsQ0FERjtRQUVFLHVDQUFLLFdBQVUsbUNBQVYsRUFBOEMsT0FBTyxxQkFBUCxFQUFuRCxDQUZGO09BRkYsQ0FSTztLQXRENkI7R0FBbEIsQ0FBaEI7O29CQTJFUyIsImZpbGUiOiJjb21wb25lbnRzXFxiYXNlbWFwdG9nZ2xlLmpzeCIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCBSZWFjdCBmcm9tICdyZWFjdCc7XHJcbmltcG9ydCB3YXRjaFV0aWxzIGZyb20gJ2VzcmkvY29yZS93YXRjaFV0aWxzJztcclxuaW1wb3J0IEJhc2VtYXBUb2dnbGVWaWV3TW9kZWwgZnJvbSAnZXNyaS93aWRnZXRzL0Jhc2VtYXBUb2dnbGUvQmFzZW1hcFRvZ2dsZVZpZXdNb2RlbCc7XHJcblxyXG5mdW5jdGlvbiBiZ0ltYWdlKHVybCkge1xyXG4gIHJldHVybiB7XHJcbiAgICBiYWNrZ3JvdW5kSW1hZ2U6ICd1cmwoJyArIHVybCArICcpJ1xyXG4gIH07XHJcbn1cclxuXHJcbmNvbnN0IEJhc2VtYXBUb2dnbGUgPSBSZWFjdC5jcmVhdGVDbGFzcyh7XHJcblxyXG4gIGdldEluaXRpYWxTdGF0ZSgpIHtcclxuICAgIHJldHVybiB7XHJcbiAgICAgIHZtOiBuZXcgQmFzZW1hcFRvZ2dsZVZpZXdNb2RlbCgpLFxyXG4gICAgICBzZWNvbmRhcnlUaHVtYm5haWxVcmw6ICcnXHJcbiAgICB9O1xyXG4gIH0sXHJcblxyXG4gIGdldERlZmF1bHRQcm9wcygpIHtcclxuICAgIHJldHVybiB7XHJcbiAgICAgIHZpZXc6IHt9LFxyXG4gICAgICBzZWNvbmRhcnlUaHVtYm5haWxVcmw6ICcnLFxyXG4gICAgICBjdXJyZW50VGh1bWJuYWlsVXJsOiAnJyxcclxuICAgICAgdXBkYXRpbmc6IGZhbHNlXHJcbiAgICB9XHJcbiAgfSxcclxuXHJcbiAgY29tcG9uZW50RGlkTW91bnQoKSB7XHJcbiAgICB0aGlzLnByb3BzLnZpZXcudGhlbih2aWV3ID0+IHtcclxuXHJcbiAgICAgIHRoaXMuc3RhdGUudm0udmlldyA9IHZpZXc7XHJcbiAgICAgIHRoaXMuc3RhdGUudm0uc2Vjb25kYXJ5QmFzZW1hcCA9IHRoaXMucHJvcHMuc2Vjb25kYXJ5QmFzZW1hcDtcclxuXHJcbiAgICAgIGxldCB7IHNlY29uZGFyeUJhc2VtYXAsIGN1cnJlbnRCYXNlbWFwIH0gPSB0aGlzLnN0YXRlLnZtO1xyXG5cclxuICAgICAgbGV0IGluZm8gPSB0aGlzLnN0YXRlLnZtLmdldEJhc2VtYXBJbmZvKHRoaXMucHJvcHMuc2Vjb25kYXJ5QmFzZW1hcCB8fCAndG9wbycpO1xyXG5cclxuICAgICAgdGhpcy5zZXRTdGF0ZSh7XHJcbiAgICAgICAgc2Vjb25kYXJ5VGh1bWJuYWlsVXJsOiBpbmZvLnRodW1ibmFpbFVybCxcclxuICAgICAgICBjdXJyZW50VGh1bWJuYWlsVXJsOiB0aGlzLnN0YXRlLnZtLmN1cnJlbnRCYXNlbWFwLnRodW1ibmFpbFVybFxyXG4gICAgICB9KTtcclxuXHJcbiAgICAgIHdhdGNoVXRpbHMud2F0Y2godGhpcy5zdGF0ZS52bSwgJ3NlY29uZGFyeUJhc2VtYXAnLCB0aGlzLnVwZGF0ZVRodW1ibmFpbHMpO1xyXG4gICAgICB3YXRjaFV0aWxzLmluaXQodmlldywgJ3N0YXRpb25hcnknLCAodXBkYXRpbmcpID0+IHtcclxuICAgICAgICB0aGlzLnNldFN0YXRlKHsgdXBkYXRpbmcgfSk7XHJcbiAgICAgIH0pO1xyXG5cclxuICAgIH0pO1xyXG4gIH0sXHJcblxyXG4gIHVwZGF0ZVRodW1ibmFpbHMoc2Vjb25kYXJ5LCBjdXJyZW50KSB7XHJcbiAgICBsZXQgc2VjSW5mbyA9IHRoaXMuc3RhdGUudm0uZ2V0QmFzZW1hcEluZm8oc2Vjb25kYXJ5KTtcclxuICAgIGxldCBjdXJJbmZvID0gdGhpcy5zdGF0ZS52bS5nZXRCYXNlbWFwSW5mbyhjdXJyZW50KTtcclxuICAgIHRoaXMuc2V0U3RhdGUoe1xyXG4gICAgICBzZWNvbmRhcnlUaHVtYm5haWxVcmw6IHNlY0luZm8udGh1bWJuYWlsVXJsLFxyXG4gICAgICBjdXJyZW50VGh1bWJuYWlsVXJsOiBjdXJJbmZvLnRodW1ibmFpbFVybFxyXG4gICAgfSk7XHJcbiAgfSxcclxuXHJcbiAgdG9nZ2xlKCkge1xyXG4gICAgdGhpcy5zdGF0ZS52bS50b2dnbGUoKTtcclxuICB9LFxyXG5cclxuICByZW5kZXIoKSB7XHJcblxyXG4gICAgbGV0IGN1cnJlbnRUaHVtYm5haWxTdHlsZSA9IGJnSW1hZ2UodGhpcy5zdGF0ZS5jdXJyZW50VGh1bWJuYWlsVXJsKTtcclxuXHJcbiAgICBsZXQgc2Vjb25kYXJ5VGh1bWJuYWlsU3R5bGUgPSBiZ0ltYWdlKHRoaXMuc3RhdGUuc2Vjb25kYXJ5VGh1bWJuYWlsVXJsKTtcclxuXHJcbiAgICBsZXQgc3R5bGUgPSB0aGlzLnN0YXRlLnVwZGF0aW5nID8gJ2Jhc2VtYXAtY29udGFpbmVyJyA6ICdiYXNlbWFwLWNvbnRhaW5lciB2aWV3LWJ1c3knO1xyXG5cclxuICAgIHJldHVybiAoXHJcblxyXG4gICAgICA8ZGl2IGNsYXNzTmFtZT17c3R5bGV9PlxyXG4gICAgICAgIDxkaXYgY2xhc3NOYW1lPSdiYXNlbWFwLWl0ZW0gYmFzZW1hcC1pdGVtLXNlY29uZGFyeScgb25DbGljaz17dGhpcy50b2dnbGV9IHN0eWxlPXtzZWNvbmRhcnlUaHVtYm5haWxTdHlsZX0+PC9kaXY+XHJcbiAgICAgICAgPGRpdiBjbGFzc05hbWU9J2Jhc2VtYXAtaXRlbSBiYXNlbWFwLWl0ZW0tY3VycmVudCcgc3R5bGU9e2N1cnJlbnRUaHVtYm5haWxTdHlsZX0+PC9kaXY+XHJcbiAgICAgIDwvZGl2PlxyXG5cclxuICAgICk7XHJcblxyXG4gIH1cclxuXHJcbn0pO1xyXG5cclxuZXhwb3J0IGRlZmF1bHQgQmFzZW1hcFRvZ2dsZTsiXX0=