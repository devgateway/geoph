define(['exports', 'app/constants/constants'], function (exports, _constants) {
  'use strict';

  Object.defineProperty(exports, "__esModule", {
    value: true
  });


  var map = function map() {
    var state = arguments.length <= 0 || arguments[0] === undefined ? {
      layers: {
        projects: {
          'name': 'Project Layers',
          'data': ''
        }
      }
    } : arguments[0];
    var action = arguments[1];


    console.log(state);
    switch (action.type) {
      case _constants.LOAD_PROJECT_GEOJSON_SUCCESS:
        var newState = Object.assign({}, state);
        return Object.assign(newState, {
          layers: {
            projects: {
              data: action.data,
              name: 'Project Layers'
            } }
        });
      case _constants.LOAD_PEOJECT_GEOJSON_FAILED:
        return null;
      default:
        return state;

    }
  };

  exports.default = map;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInJlZHVjZXJzXFxtYXAuanMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7QUFFQSxNQUFNLE1BQU0sU0FBTixHQUFNLEdBT0U7UUFQRCw4REFBUTtBQUNuQixjQUFRO0FBQ04sa0JBQVU7QUFDUixrQkFBUSxnQkFBUjtBQUNBLGtCQUFRLEVBQVI7U0FGRjtPQURGO3FCQU1ZO1FBQVgsc0JBQVc7OztBQUVaLFlBQVEsR0FBUixDQUFZLEtBQVosRUFGWTtBQUdaLFlBQVEsT0FBTyxJQUFQO0FBQ047QUFDQSxZQUFJLFdBQVMsT0FBTyxNQUFQLENBQWMsRUFBZCxFQUFrQixLQUFsQixDQUFULENBREo7QUFFQSxlQUFRLE9BQU8sTUFBUCxDQUFjLFFBQWQsRUFBd0I7QUFDOUIsa0JBQU87QUFDTCxzQkFBVTtBQUNSLG9CQUFNLE9BQU8sSUFBUDtBQUNOLG9CQUFNLGdCQUFOO2FBRkYsRUFERjtTQURNLENBQVIsQ0FGQTtBQURGLGlEQVVFO0FBQ0EsZUFBTyxJQUFQLENBREE7QUFWRjtBQWFFLGVBQU8sS0FBUCxDQURBOztBQVpGLEtBSFk7R0FQRjs7b0JBK0JKIiwiZmlsZSI6InJlZHVjZXJzXFxtYXAuanMiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQge0xPQURfUFJPSkVDVF9HRU9KU09OX1NVQ0NFU1MsTE9BRF9QRU9KRUNUX0dFT0pTT05fRkFJTEVEIH0gZnJvbSAnYXBwL2NvbnN0YW50cy9jb25zdGFudHMnO1xyXG5cclxuY29uc3QgbWFwID0gKHN0YXRlID0ge1xyXG4gIGxheWVyczoge1xyXG4gICAgcHJvamVjdHM6IHtcclxuICAgICAgJ25hbWUnOiAnUHJvamVjdCBMYXllcnMnLFxyXG4gICAgICAnZGF0YSc6ICcnXHJcbiAgICB9XHJcbiAgfVxyXG59LCBhY3Rpb24pID0+IHtcclxuXHJcbiAgY29uc29sZS5sb2coc3RhdGUpO1xyXG4gIHN3aXRjaCAoYWN0aW9uLnR5cGUpIHtcclxuICAgIGNhc2UgTE9BRF9QUk9KRUNUX0dFT0pTT05fU1VDQ0VTUzpcclxuICAgIGxldCBuZXdTdGF0ZT1PYmplY3QuYXNzaWduKHt9LCBzdGF0ZSlcclxuICAgIHJldHVybiAgT2JqZWN0LmFzc2lnbihuZXdTdGF0ZSwge1xyXG4gICAgICBsYXllcnM6e1xyXG4gICAgICAgIHByb2plY3RzOiB7XHJcbiAgICAgICAgICBkYXRhOiBhY3Rpb24uZGF0YSxcclxuICAgICAgICAgIG5hbWU6ICdQcm9qZWN0IExheWVycydcclxuICAgICAgICB9fVxyXG4gICAgICB9KTtcclxuICAgIGNhc2UgTE9BRF9QRU9KRUNUX0dFT0pTT05fRkFJTEVEOlxyXG4gICAgcmV0dXJuIG51bGw7XHJcbiAgICBkZWZhdWx0OlxyXG4gICAgcmV0dXJuIHN0YXRlXHJcblxyXG4gIH1cclxufVxyXG5cclxuXHJcblxyXG5leHBvcnRcclxuZGVmYXVsdCBtYXA7Il19