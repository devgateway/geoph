define(['exports', 'react-redux', 'app/constants/constants', 'app/actions/index', 'app/components/lan/translator', 'app/components/lan/switcher'], function (exports, _reactRedux, _constants, _index, _translator, _switcher) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});
	exports.LangSwitcher = exports.Message = undefined;

	var Constants = _interopRequireWildcard(_constants);

	var _translator2 = _interopRequireDefault(_translator);

	var _switcher2 = _interopRequireDefault(_switcher);

	function _interopRequireDefault(obj) {
		return obj && obj.__esModule ? obj : {
			default: obj
		};
	}

	function _interopRequireWildcard(obj) {
		if (obj && obj.__esModule) {
			return obj;
		} else {
			var newObj = {};

			if (obj != null) {
				for (var key in obj) {
					if (Object.prototype.hasOwnProperty.call(obj, key)) newObj[key] = obj[key];
				}
			}

			newObj.default = obj;
			return newObj;
		}
	}

	/*Pass stat to properties Message */
	var Message = (0, _reactRedux.connect)(function (state, props) {
		return {
			lan: state.language
		};
	})(_translator2.default);

	/* pass sate as properties and dispachers to  LangSwitcher*/
	var LangSwitcher = (0, _reactRedux.connect)(function (state, ownProps) {
		return {
			selected: state.language
		};
	}, function (dispatch, ownProps) {
		return {
			onChangeLanguage: function onChangeLanguage(lang) {
				dispatch((0, _index.setLanguage)(lang));
			}
		};
	})(_switcher2.default);

	exports.Message = Message;
	exports.LangSwitcher = LangSwitcher;
});
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNvbXBvbmVudHNcXGxhblxcY29udGFpbmVyLmpzeCJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7Ozs7OztLQUNZOzs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFNWixLQUFNLFVBQVUseUJBQVEsVUFBQyxLQUFELEVBQVEsS0FBUixFQUFrQjtBQUN6QyxTQUFPO0FBQ04sUUFBSyxNQUFNLFFBQU47R0FETixDQUR5QztFQUFsQixDQUFSLHNCQUFWOzs7QUFRTixLQUFNLGVBQ0wseUJBQVEsVUFBQyxLQUFELEVBQVEsUUFBUixFQUFxQjtBQUM1QixTQUFPO0FBQ04sYUFBVSxNQUFNLFFBQU47R0FEWCxDQUQ0QjtFQUFyQixFQUlMLFVBQUMsUUFBRCxFQUFXLFFBQVgsRUFBd0I7QUFDMUIsU0FBTztBQUNOLHFCQUFrQiwwQkFBQyxJQUFELEVBQVU7QUFDM0IsYUFBUyx3QkFBWSxJQUFaLENBQVQsRUFEMkI7SUFBVjtHQURuQixDQUQwQjtFQUF4QixDQUpILG9CQURLOztTQWVKO1NBQ0EiLCJmaWxlIjoiY29tcG9uZW50c1xcbGFuXFxjb250YWluZXIuanN4Iiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IHsgY29ubmVjdCB9IGZyb20gJ3JlYWN0LXJlZHV4JztcclxuaW1wb3J0ICogYXMgQ29uc3RhbnRzIGZyb20gJ2FwcC9jb25zdGFudHMvY29uc3RhbnRzJztcclxuaW1wb3J0IHsgc2V0TGFuZ3VhZ2UgfSBmcm9tICdhcHAvYWN0aW9ucy9pbmRleCdcclxuaW1wb3J0IE1lc3NhZ2VDb21wb25lbnQgZnJvbSAnYXBwL2NvbXBvbmVudHMvbGFuL3RyYW5zbGF0b3InO1xyXG5pbXBvcnQgTGFuZ1N3aXRjaGVyQ29tcG9uZW50IGZyb20gJ2FwcC9jb21wb25lbnRzL2xhbi9zd2l0Y2hlcic7XHJcblxyXG4vKlBhc3Mgc3RhdCB0byBwcm9wZXJ0aWVzIE1lc3NhZ2UgKi9cclxuY29uc3QgTWVzc2FnZSA9IGNvbm5lY3QoKHN0YXRlLCBwcm9wcykgPT4ge1xyXG5cdHJldHVybiB7XHJcblx0XHRsYW46IHN0YXRlLmxhbmd1YWdlXHJcblx0fVxyXG59KShNZXNzYWdlQ29tcG9uZW50KTtcclxuXHJcblxyXG4vKiBwYXNzIHNhdGUgYXMgcHJvcGVydGllcyBhbmQgZGlzcGFjaGVycyB0byAgTGFuZ1N3aXRjaGVyKi9cclxuY29uc3QgTGFuZ1N3aXRjaGVyID1cclxuXHRjb25uZWN0KChzdGF0ZSwgb3duUHJvcHMpID0+IHtcclxuXHRcdHJldHVybiB7XHJcblx0XHRcdHNlbGVjdGVkOiBzdGF0ZS5sYW5ndWFnZVxyXG5cdFx0fVxyXG5cdH0sIChkaXNwYXRjaCwgb3duUHJvcHMpID0+IHtcclxuXHRcdHJldHVybiB7XHJcblx0XHRcdG9uQ2hhbmdlTGFuZ3VhZ2U6IChsYW5nKSA9PiB7XHJcblx0XHRcdFx0ZGlzcGF0Y2goc2V0TGFuZ3VhZ2UobGFuZykpXHJcblx0XHRcdH1cclxuXHRcdH1cclxuXHR9KShMYW5nU3dpdGNoZXJDb21wb25lbnQpO1xyXG5cclxuXHJcbmV4cG9ydCB7XHJcblx0IE1lc3NhZ2UsXHJcblx0IExhbmdTd2l0Y2hlclxyXG59Il19