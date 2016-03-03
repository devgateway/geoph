define(['exports', 'react', 'react/react-dom', 'esri/views/SceneView'], function (exports, _react, _reactDom, _SceneView) {
   'use strict';

   Object.defineProperty(exports, "__esModule", {
      value: true
   });

   var _react2 = _interopRequireDefault(_react);

   var _reactDom2 = _interopRequireDefault(_reactDom);

   var _SceneView2 = _interopRequireDefault(_SceneView);

   function _interopRequireDefault(obj) {
      return obj && obj.__esModule ? obj : {
         default: obj
      };
   }

   var _extends = Object.assign || function (target) {
      for (var i = 1; i < arguments.length; i++) {
         var source = arguments[i];

         for (var key in source) {
            if (Object.prototype.hasOwnProperty.call(source, key)) {
               target[key] = source[key];
            }
         }
      }

      return target;
   };

   var SceneViewComponent = _react2.default.createClass({
      displayName: 'SceneViewComponent',
      componentDidMount: function componentDidMount() {
         var node = _reactDom2.default.findDOMNode(this.refs.mapView);
         this.view = new _SceneView2.default(_extends({ container: node }, this.props));
      },
      render: function render() {
         var children = this.view ? _react2.default.Children.map(this.props.children, function (child) {
            return child ? _react2.default.cloneElement(child, { view: view }) : null;
         }) : null;

         return _react2.default.createElement(
            'div',
            { className: 'mapView', ref: 'mapView' },
            children
         );
      }
   });

   exports.default = SceneViewComponent;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGVzcmlcXHNjZW5lVmlldy5qc3giXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7O0FBTUEsT0FBTSxxQkFBcUIsZ0JBQU0sV0FBTixDQUFrQjs7QUFFNUMsc0RBQW9CO0FBQ2xCLGFBQUksT0FBTyxtQkFBUyxXQUFULENBQXFCLEtBQUssSUFBTCxDQUFVLE9BQVYsQ0FBNUIsQ0FEYztBQUVsQixjQUFLLElBQUwsR0FBVyxtQ0FBZSxXQUFXLElBQVgsSUFBbUIsS0FBSyxLQUFMLENBQWxDLENBQVgsQ0FGa0I7T0FGd0I7QUFTN0MsZ0NBQVE7QUFDTCxhQUFNLFdBQVcsS0FBSyxJQUFMLEdBQVksZ0JBQU0sUUFBTixDQUFlLEdBQWYsQ0FBbUIsS0FBSyxLQUFMLENBQVcsUUFBWCxFQUFxQixpQkFBUztBQUFDLG1CQUFPLFFBQVEsZ0JBQU0sWUFBTixDQUFtQixLQUFuQixFQUEwQixFQUFDLFVBQUQsRUFBMUIsQ0FBUixHQUE0QyxJQUE1QyxDQUFSO1VBQVQsQ0FBcEQsR0FBMkgsSUFBM0gsQ0FEWjs7QUFHTCxnQkFBUTs7Y0FBSyxXQUFVLFNBQVYsRUFBb0IsS0FBSSxTQUFKLEVBQXpCO1lBQXdDLFFBQXhDO1VBQVIsQ0FISztPQVRxQztJQUFsQixDQUFyQjs7cUJBbUJTIiwiZmlsZSI6ImNvbXBvbmVudHNcXGVzcmlcXHNjZW5lVmlldy5qc3giLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgUmVhY3QgZnJvbSAncmVhY3QnO1xyXG5pbXBvcnQgUmVhY3RET00gZnJvbSAncmVhY3QvcmVhY3QtZG9tJztcclxuaW1wb3J0IFNjZW5lVmlldyBmcm9tICdlc3JpL3ZpZXdzL1NjZW5lVmlldyc7XHJcblxyXG5cclxuXHJcbmNvbnN0IFNjZW5lVmlld0NvbXBvbmVudCA9IFJlYWN0LmNyZWF0ZUNsYXNzKHtcclxuXHJcbiBjb21wb25lbnREaWRNb3VudCgpIHtcclxuICAgdmFyIG5vZGUgPSBSZWFjdERPTS5maW5kRE9NTm9kZSh0aGlzLnJlZnMubWFwVmlldyk7XHJcbiAgIHRoaXMudmlldz0gbmV3IFNjZW5lVmlldyh7Y29udGFpbmVyOiBub2RlLC4uLnRoaXMucHJvcHN9KTtcclxuICBcclxufSxcclxuXHJcblxyXG5yZW5kZXIoKXtcclxuICAgY29uc3QgY2hpbGRyZW4gPSB0aGlzLnZpZXcgPyBSZWFjdC5DaGlsZHJlbi5tYXAodGhpcy5wcm9wcy5jaGlsZHJlbiwgY2hpbGQgPT4ge3JldHVybiBjaGlsZCA/IFJlYWN0LmNsb25lRWxlbWVudChjaGlsZCwge3ZpZXd9KSA6IG51bGw7fSkgOiBudWxsO1xyXG5cclxuICAgcmV0dXJuICg8ZGl2IGNsYXNzTmFtZT0nbWFwVmlldycgcmVmPSdtYXBWaWV3Jz57Y2hpbGRyZW59PC9kaXY+KVxyXG59XHJcblxyXG59KTtcclxuXHJcblxyXG5cclxuZXhwb3J0IGRlZmF1bHQgU2NlbmVWaWV3Q29tcG9uZW50O1xyXG4iXX0=