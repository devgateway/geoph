define(['exports', 'react', 'esri/core/watchUtils', 'esri/widgets/Attribution/AttributionViewModel'], function (exports, _react, _watchUtils, _AttributionViewModel) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });

  var _react2 = _interopRequireDefault(_react);

  var _watchUtils2 = _interopRequireDefault(_watchUtils);

  var _AttributionViewModel2 = _interopRequireDefault(_AttributionViewModel);

  function _interopRequireDefault(obj) {
    return obj && obj.__esModule ? obj : {
      default: obj
    };
  }

  var Attribution = _react2.default.createClass({
    displayName: 'Attribution',
    getInitialState: function getInitialState() {
      return {
        vm: new _AttributionViewModel2.default(),
        attribution: '',
        updating: false
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
        _watchUtils2.default.watch(_this.state.vm, 'attributionText', function (attribution) {
          _this.setState({ attribution: attribution });
        });
        _watchUtils2.default.init(view, 'stationary', function (updating) {
          _this.setState({ updating: updating });
        });
      });
    },
    render: function render() {

      var style = this.state.updating ? 'attribution' : 'attribution view-busy';

      return _react2.default.createElement(
        'span',
        { className: style, ref: 'mainNode' },
        this.state.attribution
      );
    }
  });

  exports.default = Attribution;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmlcXHdpZGdldHNcXGF0dHJpYnV0aW9uLmpzeCJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7Ozs7Ozs7Ozs7Ozs7Ozs7O0FBSUEsTUFBTSxjQUFjLGdCQUFNLFdBQU4sQ0FBa0I7O0FBRXBDLGdEQUFrQjtBQUNoQixhQUFPO0FBQ0wsWUFBSSxvQ0FBSjtBQUNBLHFCQUFhLEVBQWI7QUFDQSxrQkFBVSxLQUFWO09BSEYsQ0FEZ0I7S0FGa0I7QUFVcEMsZ0RBQWtCO0FBQ2hCLGFBQU87QUFDTCxjQUFNLEVBQU47T0FERixDQURnQjtLQVZrQjtBQWdCcEMsb0RBQW9COzs7QUFDbEIsV0FBSyxLQUFMLENBQVcsSUFBWCxDQUFnQixJQUFoQixDQUFxQixnQkFBUTtBQUMzQixjQUFLLEtBQUwsQ0FBVyxFQUFYLENBQWMsSUFBZCxHQUFxQixJQUFyQixDQUQyQjtBQUUzQiw2QkFBVyxLQUFYLENBQWlCLE1BQUssS0FBTCxDQUFXLEVBQVgsRUFBZSxpQkFBaEMsRUFBbUQsVUFBQyxXQUFELEVBQWlCO0FBQ2xFLGdCQUFLLFFBQUwsQ0FBYyxFQUFFLHdCQUFGLEVBQWQsRUFEa0U7U0FBakIsQ0FBbkQsQ0FGMkI7QUFLM0IsNkJBQVcsSUFBWCxDQUFnQixJQUFoQixFQUFzQixZQUF0QixFQUFvQyxVQUFDLFFBQUQsRUFBYztBQUNoRCxnQkFBSyxRQUFMLENBQWMsRUFBRSxrQkFBRixFQUFkLEVBRGdEO1NBQWQsQ0FBcEMsQ0FMMkI7T0FBUixDQUFyQixDQURrQjtLQWhCZ0I7QUE0QnBDLDhCQUFTOztBQUVQLFVBQUksUUFBUSxLQUFLLEtBQUwsQ0FBVyxRQUFYLEdBQXNCLGFBQXRCLEdBQXNDLHVCQUF0QyxDQUZMOztBQUlQLGFBRUU7O1VBQU0sV0FBVyxLQUFYLEVBQWtCLEtBQUksVUFBSixFQUF4QjtRQUF3QyxLQUFLLEtBQUwsQ0FBVyxXQUFYO09BRjFDLENBSk87S0E1QjJCO0dBQWxCLENBQWQ7O29CQTBDUyIsImZpbGUiOiJjb21wb25lbnRzXFxlc3JpXFx3aWRnZXRzXFxhdHRyaWJ1dGlvbi5qc3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgd2F0Y2hVdGlscyBmcm9tICdlc3JpL2NvcmUvd2F0Y2hVdGlscyc7XHJcbmltcG9ydCBBdHRyaWJ1dGlvblZpZXdNb2RlbCBmcm9tICdlc3JpL3dpZGdldHMvQXR0cmlidXRpb24vQXR0cmlidXRpb25WaWV3TW9kZWwnO1xyXG5cclxuY29uc3QgQXR0cmlidXRpb24gPSBSZWFjdC5jcmVhdGVDbGFzcyh7XHJcblxyXG4gIGdldEluaXRpYWxTdGF0ZSgpIHtcclxuICAgIHJldHVybiB7XHJcbiAgICAgIHZtOiBuZXcgQXR0cmlidXRpb25WaWV3TW9kZWwoKSxcclxuICAgICAgYXR0cmlidXRpb246ICcnLFxyXG4gICAgICB1cGRhdGluZzogZmFsc2VcclxuICAgIH07XHJcbiAgfSxcclxuXHJcbiAgZ2V0RGVmYXVsdFByb3BzKCkge1xyXG4gICAgcmV0dXJuIHtcclxuICAgICAgdmlldzoge31cclxuICAgIH1cclxuICB9LFxyXG5cclxuICBjb21wb25lbnREaWRNb3VudCgpIHtcclxuICAgIHRoaXMucHJvcHMudmlldy50aGVuKHZpZXcgPT4ge1xyXG4gICAgICB0aGlzLnN0YXRlLnZtLnZpZXcgPSB2aWV3O1xyXG4gICAgICB3YXRjaFV0aWxzLndhdGNoKHRoaXMuc3RhdGUudm0sICdhdHRyaWJ1dGlvblRleHQnLCAoYXR0cmlidXRpb24pID0+IHtcclxuICAgICAgICB0aGlzLnNldFN0YXRlKHsgYXR0cmlidXRpb24gfSk7XHJcbiAgICAgIH0pO1xyXG4gICAgICB3YXRjaFV0aWxzLmluaXQodmlldywgJ3N0YXRpb25hcnknLCAodXBkYXRpbmcpID0+IHtcclxuICAgICAgICB0aGlzLnNldFN0YXRlKHsgdXBkYXRpbmcgfSk7XHJcbiAgICAgIH0pO1xyXG4gICAgfSk7XHJcbiAgfSxcclxuXHJcbiAgcmVuZGVyKCkge1xyXG5cclxuICAgIGxldCBzdHlsZSA9IHRoaXMuc3RhdGUudXBkYXRpbmcgPyAnYXR0cmlidXRpb24nIDogJ2F0dHJpYnV0aW9uIHZpZXctYnVzeSc7XHJcblxyXG4gICAgcmV0dXJuIChcclxuXHJcbiAgICAgIDxzcGFuIGNsYXNzTmFtZT17c3R5bGV9IHJlZj0nbWFpbk5vZGUnPnt0aGlzLnN0YXRlLmF0dHJpYnV0aW9ufTwvc3Bhbj5cclxuXHJcbiAgICApO1xyXG5cclxuICB9XHJcblxyXG59KTtcclxuXHJcbmV4cG9ydCBkZWZhdWx0IEF0dHJpYnV0aW9uOyJdfQ==